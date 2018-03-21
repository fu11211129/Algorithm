import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Arrays;
import java.util.Queue;

public class MultiIteratorVertical<E> implements Iterator {

    Queue<Iterator<E>> iterators = new LinkedList<> ();
    Iterator<E> current = null;

    @Override
    public boolean hasNext() {
        boolean result = false;
        if (iterators.isEmpty() && (current == null || !current.hasNext())) {
            return false;
        }

        if (current == null) {
            current = iterators.poll();
        }

        while (!current.hasNext() && !iterators.isEmpty()) {
            current = iterators.poll();
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
                current = iterators.poll();
            } catch (IndexOutOfBoundsException e) {
                throw new NoSuchElementException();
            }
        }
        E result = current.next(); // if this method was called without checking 'hasNext' this line might raise NoSuchElementException which is fine
        iterators.offer(current);
        current = iterators.poll();
        return result;
    }

    public static void main(String[] args) {
        // int[] nums = new int[200];
        // int e = 0;
        // for(int i=0; i<nums.length; ++i) nums[i] = ++e;
        //
        List<Iterator<Integer>> its = new LinkedList<>();
        // for(int k=0; k<200; ++k) {
        //     List<Integer> each = new LinkedList<> ();
        //     for(int i=0; i<nums.length; ++i) each.add(nums[i]);
        //     its.add(each.iterator());
        // }

        its.add(Arrays.asList(1, 7, 13, 17).iterator());
        its.add(Arrays.asList(2, 8, 14, 18).iterator());
        its.add(Arrays.asList(3, 9).iterator());
        its.add(Arrays.asList(4, 10, 15).iterator());
        its.add(Arrays.asList(5, 11).iterator());
        its.add(Arrays.asList(6, 12, 16, 19).iterator());

        MultiIterator<Integer> it = new MultiIterator<>(its.iterator());
        long prev = System.currentTimeMillis();
        int times = 0;
        while (it.hasNext()) {
            int val = it.next(); // prints: 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,
            System.out.print(val + " ");
        } System.out.println();
        long curr = System.currentTimeMillis();

        System.out.println((curr - prev) + " ms");
    }
}
