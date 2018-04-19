// leetcode 786
public class KthSmallestPrimeFraction {

    private static final double err = 1e-9;

    private int smaller(double [] A, double val) {
        int cnt = 0;
        // fix q as A[j], binary search p as A[i]
        for (int j = 1; j < A.length; ++j) {
            // find the last element that is smaller than val
            int lo = 0;
            int hi = j - 1;
            while (lo < hi) {
                int mid = (lo + hi + 1) / 2;
                if (A[mid] / A[j] < val) {
                    lo = mid;
                } else {
                    hi = mid - 1;
                }
            }
            if (lo == hi) {
                cnt += ((A[lo] / A[j] < val) ? lo + 1 : lo);
            }
        }
        return cnt;
    }

    public int[] kthSmallestPrimeFraction(int[] AA, int K) {
        // binary search the value
        double [] A = new double [AA.length];
        Set<Integer> nums = new HashSet<>();
        for (int i = 0; i < A.length; ++i) {
            A[i] = AA[i];
            nums.add(AA[i]);
        }
        double lo = 1.0 / 30000;
        double hi = 29999.0 / 30000;

        // time Complexity: O(log(10^9)) because err = 1e-9
        while (hi - lo > err) {
            double mid = (lo + hi) / 2;
            int cnt = smaller(A, mid); // how many fractions smaller than mid
            if (cnt > K - 1) {
                hi = mid;
            } else {
                lo = mid;
            }
        }

        for (int i = 0; i < A.length; ++i) {
            // search for each possible q (A[i])
            int p = (int)Math.round(lo * A[i]);
            if (p < A[i] && nums.contains(p) && Math.abs(p/A[i] - lo) < err) {
                return new int[] {p, AA[i]};
            }
        }
        // unreachable
        assert(false);
        return null;
    }
}
