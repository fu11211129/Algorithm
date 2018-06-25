public class RangeSum1DMutable {

    public FenwickTree ft;
    public int[] A;

    public NumArray(int[] nums) {
        int N = nums.length;
        ft = new FenwickTree(N);
        A = new int[N];
        for(int i=0; i<N; ++i) {
            ft.update(i + 1, nums[i]);
            A[i] = nums[i];
        }
    }

    public void update(int i, int val) {
        int preVal = A[i];
        int delta = val - preVal;
        ft.update(i + 1, delta);
        A[i] = val;
    }

    public int sumRange(int i, int j) {
        int s1 = ft.getSum(i);
        int s2 = ft.getSum(j + 1);
        return s2 - s1;
    }

    class FenwickTree {
        int N;
        int[] sum;

        public FenwickTree(int n) {
            N = n;
            sum = new int[N + 1];
        }

        public void update(int x, int delta) {
            for(int i=x; i<=N; i+=lowbit(i)) sum[i] += delta;
        }

        public int lowbit(int i) {
            return i & (-i);
        }

        public int getSum(int x) {
            int re = 0;
            for(int i=x; i>=1; i-=lowbit(i)) re += sum[i];
            return re;
        }
    }
}
