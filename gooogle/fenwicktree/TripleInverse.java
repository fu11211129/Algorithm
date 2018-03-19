import java.util.*;

public class TripleInverse {

    static int[] getRank(int[] nums) {
        int n = nums.length;
        int[] temp = new int[n];
        for(int i=0; i<n; ++i) temp[i] = nums[i];

        Arrays.sort(temp);
        for(int i=0; i<n; ++i) {
            nums[i] = Arrays.binarySearch(temp, nums[i]) + 1;
        }
        return nums;
    }

    public static void main(String[] args) {
        int[] nums = new int[] {8, 4, 2, 1};
        int[] rank = getRank(nums);

        int n = nums.length;
        int[] leftLarge = new int[n];
        int[] rightSmall = new int[n];

        FenwickTree ft = new FenwickTree(n);
        // ft.sum(x):
        // the number of items smaller than or equals to x so far
        for(int i=0; i<n; ++i) {
            leftLarge[i] = i - ft.getSum(rank[i]);
            ft.insert(rank[i], 1);
        }

        ft = new FenwickTree(n);
        for(int i=n-1; i>=0; --i) {
            rightSmall[i] = ft.getSum(rank[i] - 1);
            ft.insert(rank[i], 1);
        }

        int result = 0;
        for(int i=0; i<n; ++i) {
            result += leftLarge[i] * rightSmall[i];
        }

        System.out.println(result);
    }

    static class FenwickTree {
        public int n;
        public int[] sum;

        public FenwickTree(int nn) {
            this.n = nn;
            this.sum = new int[nn + 1];
            Arrays.fill(sum, 0);
        }

        private int lowbit(int x) {
            return x & (-x);
        }

        public void insert(int pos, int val) {
            while(pos <= n) {
                sum[pos] += val;
                pos += lowbit(pos);
            }
        }

        // retrieve the sum[1:pos]
        public int getSum(int pos) {
            int result = 0;
            while(pos > 0) {
                result += sum[pos];
                pos -= lowbit(pos);
            }
            return result;
        }
    }
}
