import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Arrays;

public class MultiIterator<E> implements Iterator {

    List<Iterator<E>> iterators = new LinkedList<>();
    Iterator<E> current = null;

    public MultiIterator(Iterator<Iterator<E>> iterator) {
        // copy the iterators into a list
        while (iterator.hasNext()) {
            iterators.add(iterator.next());
        }
    }

    @Override
    public boolean hasNext() {
        boolean result = false;
        if (iterators.isEmpty() && (current == null || !current.hasNext())) {
            return false;
        }

        if (current == null) {
            current = iterators.remove(0);
        }

        while (!current.hasNext() && !iterators.isEmpty()) {
            current = iterators.remove(0);
        }

        if (current.hasNext()) {
            result = true;
        }
        return result;
    }

    @Override
    public E next() {
        if (current == null) {
            try {
                current = iterators.remove(0);
            } catch (IndexOutOfBoundsException e) {
                throw new NoSuchElementException();
            }
        }
        E result = current.next(); // if this method was called without checking 'hasNext' this line might raise NoSuchElementException which is fine
        iterators.add(current);
        current = iterators.remove(0);
        return result;
    }

    // test
    public static void main(String[] args) {
        List<Integer> a = new LinkedList<>();
        a.addAll(Arrays.asList(1, 7, 13, 17));

        List<Integer> b = new LinkedList<>();
        b.addAll(Arrays.asList(2, 8, 14, 18));

        List<Integer> c = new LinkedList<>();
        c.addAll(Arrays.asList(3, 9));

        List<Integer> d = new LinkedList<>();
        d.addAll(Arrays.asList(4, 10, 15));

        List<Integer> e = new LinkedList<>();
        e.addAll(Arrays.asList(5, 11));

        List<Integer> f = new LinkedList<>();
        f.addAll(Arrays.asList(6, 12, 16, 19));

        List<Iterator<Integer>> iterators = new LinkedList<>();
        iterators.add(a.iterator());
        iterators.add(b.iterator());
        iterators.add(c.iterator());
        iterators.add(d.iterator());
        iterators.add(e.iterator());
        iterators.add(f.iterator());
        MultiIterator<Integer> it = new MultiIterator<>(iterators.iterator());
        while (it.hasNext()) {
            System.out.print(it.next() + ","); // prints: 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,
        }
    }
}
