import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.*;
import java.util.*;
import java.io.Serializable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class MyHashMap<K, V> extends AbstractMap<K, V> implements ConcurrentMap<K, V>, Serializable {
    private static final long serialVersionUID = 7249069246763182397L;

    static final int DEFAULT_INITIAL_CAPACITY = 16;

    static final float DEFAULT_LOAD_FACTOR = 0.75f;

    static final int DEFAULT_CONCURRENCY_LEVEL = 16;

    static final int MAXIMUM_CAPACITY = 1 << 30;

    static final int MAX_SEGMENTS = 1 << 16; // slightly conservative

    static final int RETRIES_BEFORE_LOCK = 2;

    final int segmentMask;

    final int segmentShift;

    final Segment<K,V>[] segments;

    private static int hash(int h) {
        // Spread bits to regularize both segment and index locations,
        // using variant of single-word Wang/Jenkins hash.
        h += (h <<  15) ^ 0xffffcd7d;
        h ^= (h >>> 10);
        h += (h <<   3);
        h ^= (h >>>  6);
        h += (h <<   2) + (h << 14);
        return h ^ (h >>> 16);
    }

    final Segment<K,V> segmentFor(int hash) {
        return segments[(hash >>> segmentShift) & segmentMask];
    }

    /* ---------------- Inner Classes -------------- */

    static final class MyHashEntry<K,V> {
        final K key;
        final int hash;
        volatile V value;
        final MyHashEntry<K,V> next;

        MyHashEntry(K key, int hash, MyHashEntry<K,V> next, V value) {
            this.key = key;
            this.hash = hash;
            this.next = next;
            this.value = value;
        }

        @SuppressWarnings("unchecked")
        static final <K,V> MyHashEntry<K,V>[] newArray(int i) {
            return new MyHashEntry[i];
        }
    }


    static final class Segment<K,V> extends ReentrantLock implements Serializable {

        private static final long serialVersionUID = 2249069246763182397L;

        transient volatile int count;

        transient int modCount;

        transient int threshold;

        transient volatile MyHashEntry<K,V>[] table;

        final float loadFactor;

        Segment(int initialCapacity, float lf) {
            loadFactor = lf;
            setTable(MyHashEntry.<K,V>newArray(initialCapacity));
        }

        @SuppressWarnings("unchecked")
        static final <K,V> Segment<K,V>[] newArray(int i) {
            return new Segment[i];
        }

        void setTable(MyHashEntry<K,V>[] newTable) {
            threshold = (int)(newTable.length * loadFactor);
            table = newTable;
        }

        MyHashEntry<K,V> getFirst(int hash) {
            MyHashEntry<K,V>[] tab = table;
            return tab[hash & (tab.length - 1)];
        }

        V readValueUnderLock(MyHashEntry<K,V> e) {
            lock();
            try {
                return e.value;
            } finally {
                unlock();
            }
        }

        /* Specialized implementations of map methods */

        V get(Object key, int hash) {
            if (count != 0) { // read-volatile
                MyHashEntry<K,V> e = getFirst(hash);
                while (e != null) {
                    if (e.hash == hash && key.equals(e.key)) {
                        V v = e.value;
                        if (v != null)
                            return v;
                        return readValueUnderLock(e); // recheck
                    }
                    e = e.next;
                }
            }
            return null;
        }

        boolean containsKey(Object key, int hash) {
            if (count != 0) { // read-volatile
                MyHashEntry<K,V> e = getFirst(hash);
                while (e != null) {
                    if (e.hash == hash && key.equals(e.key))
                        return true;
                    e = e.next;
                }
            }
            return false;
        }

        V put(K key, int hash, V value, boolean onlyIfAbsent) {
            lock();
            try {
                int c = count;
                if (c++ > threshold) // ensure capacity
                    rehash();
                MyHashEntry<K,V>[] tab = table;
                int index = hash & (tab.length - 1);
                MyHashEntry<K,V> first = tab[index];
                MyHashEntry<K,V> e = first;
                while (e != null && (e.hash != hash || !key.equals(e.key)))
                    e = e.next;

                V oldValue;
                if (e != null) {
                    oldValue = e.value;
                    if (!onlyIfAbsent)
                        e.value = value;
                }
                else {
                    oldValue = null;
                    ++modCount;
                    tab[index] = new MyHashEntry<K,V>(key, hash, first, value);
                    count = c; // write-volatile
                }
                return oldValue;
            } finally {
                unlock();
            }
        }

        void rehash() {
            MyHashEntry<K,V>[] oldTable = table;
            int oldCapacity = oldTable.length;
            if (oldCapacity >= MAXIMUM_CAPACITY)
                return;

            MyHashEntry<K,V>[] newTable = MyHashEntry.newArray(oldCapacity<<1);
            threshold = (int)(newTable.length * loadFactor);
            int sizeMask = newTable.length - 1;
            for (int i = 0; i < oldCapacity ; i++) {
                // We need to guarantee that any existing reads of old Map can
                //  proceed. So we cannot yet null out each bin.
                MyHashEntry<K,V> e = oldTable[i];

                if (e != null) {
                    MyHashEntry<K,V> next = e.next;
                    int idx = e.hash & sizeMask;

                    //  Single node on list
                    if (next == null)
                        newTable[idx] = e;

                    else {
                        // Reuse trailing consecutive sequence at same slot
                        MyHashEntry<K,V> lastRun = e;
                        int lastIdx = idx;
                        for (MyHashEntry<K,V> last = next;
                             last != null;
                             last = last.next) {
                            int k = last.hash & sizeMask;
                            if (k != lastIdx) {
                                lastIdx = k;
                                lastRun = last;
                            }
                        }
                        newTable[lastIdx] = lastRun;

                        // Clone all remaining nodes
                        for (MyHashEntry<K,V> p = e; p != lastRun; p = p.next) {
                            int k = p.hash & sizeMask;
                            MyHashEntry<K,V> n = newTable[k];
                            newTable[k] = new MyHashEntry<K,V>(p.key, p.hash,
                                                             n, p.value);
                        }
                    }
                }
            }
            table = newTable;
        }

        /**
         * Remove; match on key only if value null, else match both.
         */
        V remove(Object key, int hash, Object value) {
            lock();
            try {
                int c = count - 1;
                MyHashEntry<K,V>[] tab = table;
                int index = hash & (tab.length - 1);
                MyHashEntry<K,V> first = tab[index];
                MyHashEntry<K,V> e = first;
                while (e != null && (e.hash != hash || !key.equals(e.key)))
                    e = e.next;

                V oldValue = null;
                if (e != null) {
                    V v = e.value;
                    if (value == null || value.equals(v)) {
                        oldValue = v;
                        // All entries following removed node can stay
                        // in list, but all preceding ones need to be
                        // cloned.
                        ++modCount;
                        MyHashEntry<K,V> newFirst = e.next;
                        for (MyHashEntry<K,V> p = first; p != e; p = p.next)
                            newFirst = new MyHashEntry<K,V>(p.key, p.hash,
                                                          newFirst, p.value);
                        tab[index] = newFirst;
                        count = c; // write-volatile
                    }
                }
                return oldValue;
            } finally {
                unlock();
            }
        }

        void clear() {
            if (count != 0) {
                lock();
                try {
                    MyHashEntry<K,V>[] tab = table;
                    for (int i = 0; i < tab.length ; i++)
                        tab[i] = null;
                    ++modCount;
                    count = 0; // write-volatile
                } finally {
                    unlock();
                }
            }
        }
    }



    /* ---------------- Public operations -------------- */

    public MyHashMap(int initialCapacity,
                             float loadFactor, int concurrencyLevel) {
        if (!(loadFactor > 0) || initialCapacity < 0 || concurrencyLevel <= 0)
            throw new IllegalArgumentException();

        if (concurrencyLevel > MAX_SEGMENTS)
            concurrencyLevel = MAX_SEGMENTS;

        // Find power-of-two sizes best matching arguments
        int sshift = 0;
        int ssize = 1;
        while (ssize < concurrencyLevel) {
            ++sshift;
            ssize <<= 1;
        }
        segmentShift = 32 - sshift;
        segmentMask = ssize - 1;
        this.segments = Segment.newArray(ssize);

        if (initialCapacity > MAXIMUM_CAPACITY)
            initialCapacity = MAXIMUM_CAPACITY;
        int c = initialCapacity / ssize;
        if (c * ssize < initialCapacity)
            ++c;
        int cap = 1;
        while (cap < c)
            cap <<= 1;

        for (int i = 0; i < this.segments.length; ++i)
            this.segments[i] = new Segment<K,V>(cap, loadFactor);
    }

    public MyHashMap() {
        this(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR, DEFAULT_CONCURRENCY_LEVEL);
    }

    public boolean isEmpty() {
        final Segment<K,V>[] segments = this.segments;
        int[] mc = new int[segments.length];
        int mcsum = 0;
        for (int i = 0; i < segments.length; ++i) {
            if (segments[i].count != 0)
                return false;
            else
                mcsum += mc[i] = segments[i].modCount;
        }

        if (mcsum != 0) {
            for (int i = 0; i < segments.length; ++i) {
                if (segments[i].count != 0 ||
                    mc[i] != segments[i].modCount)
                    return false;
            }
        }
        return true;
    }

    public V get(Object key) {
        int hash = hash(key.hashCode());
        return segmentFor(hash).get(key, hash);
    }

    public boolean containsKey(Object key) {
        int hash = hash(key.hashCode());
        return segmentFor(hash).containsKey(key, hash);
    }


    public V put(K key, V value) {
        if (value == null)
            throw new NullPointerException();
        int hash = hash(key.hashCode());
        return segmentFor(hash).put(key, hash, value, false);
    }

    public V remove(Object key) {
        int hash = hash(key.hashCode());
        return segmentFor(hash).remove(key, hash, null);
    }

    public boolean remove(Object key, Object value) {
        int hash = hash(key.hashCode());
        if (value == null)
            return false;
        return segmentFor(hash).remove(key, hash, value) != null;
    }

    public void clear() {
        for (int i = 0; i < segments.length; ++i)
            segments[i].clear();
    }



	@Override
	public V putIfAbsent(K key, V value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V replace(K key, V value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<java.util.Map.Entry<K, V>> entrySet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean replace(K key, V oldValue, V newValue) {
		// TODO Auto-generated method stub
		return false;
	}




}
