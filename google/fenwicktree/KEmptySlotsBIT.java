import java.util.*;

public class KEmptySlotsBIT {

    public static void main(String[] args) {
        int[] flowers = new int[] {1, 3, 2};
        int k = 1;
        int result = solution(flowers, k);

        System.out.println(result);
    }

    static int solution(int[] flowers, int k) {
        int n = flowers.length;

        int[] arr = new int[n + 1];
        FenwickTree ft = new FenwickTree(n);


        for(int day=0; day<n; ++day) {
            int pos = flowers[day];
            arr[pos] = 1;
            ft.insert(pos, 1);
            int leftPos = pos - k - 1;
            int rightPos = pos + k + 1;

            if(leftPos >= 0 && arr[leftPos] == 1 && ft.getSum(pos) - ft.getSum(leftPos - 1) == 2) return day + 1;

            if(rightPos <= n && arr[rightPos] == 1 && ft.getSum(rightPos) - ft.getSum(pos - 1) == 2) return day + 1;
        }

        return -1;
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
