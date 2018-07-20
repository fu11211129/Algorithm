import java.util.*;

public class MaxSlidingWindow {
    static List<Integer> minSlidingWindow(List<Integer> nums, int k) {
        int n = nums.size();
        if(n == 0 || k > n) return new ArrayList<> ();

        List<Integer> re = new ArrayList<> ();
        LinkedList<Integer> st = new LinkedList<> ();

        for(int i = 0; i < n; i += 1) {
            while(!st.isEmpty() && nums.get(st.peekFirst()) > nums.get(i)) st.pollFirst();
            st.addFirst(i);
            while(!st.isEmpty() && i - st.peekLast() + 1 > k) st.pollLast();
            if(i >= k - 1) re.add(nums.get(st.peekLast()));
        }

        return re;
    }

    public static void main(String[] args) {
        List<Integer> nums = Arrays.asList(1, 3, -1, -3, 5, 3, 6, 7);
        int k = 3;
        List<Integer> re = minSlidingWindow(nums, k);
        System.out.println(re);
    }
}
