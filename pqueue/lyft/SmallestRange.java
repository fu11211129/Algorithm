// You have k lists of sorted integers in ascending order. Find the smallest range that includes at least one number from each of the k lists.
//
// We define the range [a,b] is smaller than range [c,d] if b-a < d-c or a < c if b-a == d-c.
//
// Example 1:
// Input:[[4,10,15,24,26], [0,9,12,20], [5,18,22,30]]
// Output: [20,24]
// Explanation:
// List 1: [4, 10, 15, 24,26], 24 is in range [20,24].
// List 2: [0, 9, 12, 20], 20 is in range [20,24].
// List 3: [5, 18, 22, 30], 22 is in range [20,24].
// Note:
// The given list may contain duplicates, so ascending order means >= here.
// 1 <= k <= 3500
// -105 <= value of elements <= 105.
// For Java users, please note that the input type has been changed to List<List<Integer>>. And after you reset the code template, you'll see this point.
import java.util.*;

public class SmallestRange {

    public static void main(String[] args) {
        SmallestRange sr = new SmallestRange();
        List<List<Integer> > nums = new ArrayList<> ();
        nums.add(Arrays.asList(4,10,15,24,26));
        nums.add(Arrays.asList(0,9,12,20));
        nums.add(Arrays.asList(5,18,22,30));
        int[] result = sr.smallestRange(nums);
        System.out.println(result[0] + " " + result[1]);
    }

    public int[] smallestRange(List<List<Integer> > nums) {
        int n = nums.size();
        PriorityQueue<Element> pq = new PriorityQueue<Element> (new Comparator<Element> () {
            @Override
            public int compare(Element a, Element b) {
                return a.val - b.val;
            }
        });

        int curMax = Integer.MIN_VALUE;
        for(int i=0; i<n; ++i) {
            List<Integer> line = nums.get(i);
            int smallest = line.get(0);
            pq.offer(new Element(i, 0, smallest));
            curMax = Math.max(curMax, smallest);
        }

        int range = Integer.MAX_VALUE;
        int start = -1;
        int end = -1;
        // maintain such a pq that has at-least one element per row
        while(pq.size() == n) {
            Element e = pq.poll();
            if(curMax - e.val < range) {
                range = curMax - e.val;
                start = e.val;
                end = curMax;
            }

            // if there are elements remained in this row.
            if(e.col + 1 < nums.get(e.row).size()) {
                int next = nums.get(e.row).get(e.col + 1);
                pq.offer(new Element(e.row, e.col + 1, next));
                curMax = Math.max(curMax, next);
            }
        }

        return new int[] {start, end};
    }

    class Element {
        public int val;
        public int row;
        public int col;

        public Element(int r, int c, int v) {
            val = v;
            row = r;
            col = c;
        }
    }
}
