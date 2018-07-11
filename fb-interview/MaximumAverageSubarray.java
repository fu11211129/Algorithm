public class MaximumAverageSubarray {
    public double findMaxAverage(int[] nums, int k) {
        int n = nums.length;
        if(n == 1) return nums[0] * 1.0;

        double L = Double.MAX_VALUE;
        double R = Double.MIN_VALUE;
        int[] sum = new int[n];
        double err = Double.MAX_VALUE;

        for(int i = 0; i < n; i += 1) {
            double dv = (double)nums[i];
            L = Math.min(L, dv);
            R = Math.max(R, dv);
            sum[i] = i == 0? nums[i]: sum[i - 1] + nums[i];
        }

        double prev = R;

        while(R - L > 1e-9) {
            // given a guess "mid"
            double mid = (L + R) * 0.5;

            // find the largest subarray with length >= k

            // if the first leading k elements satisfy the condition,
            // "mid" is a good guess, the continue search for "bigger" average.
            if(window(sum, nums, 0, k - 1, mid) >= 0.0) {
                L = mid;
                continue;
            }

            boolean exist = false;
            double minPresum = Double.MAX_VALUE;
            double presum = 0.0;
            for(int i = k; i < n; i += 1) {
                presum += nums[i - k] * 1.0 - mid;
                minPresum = Math.min(minPresum, presum);

                if(window(sum, nums, 0, i, mid) >= 0.0 || window(sum, nums, 0, i, mid) - minPresum >= 0.0) {
                    exist = true;
                    break;
                }
            }

            if(exist) L = mid;
            else R = mid; // if "mid" is not a good guess, try a smaller average.
        }

        return L;
    }

    public double window(int[] sum, int[] nums, int i, int j, double of) {
        return (sum[j] - sum[i] + nums[i]) * 1.0 - (j - i + 1) * of * 1.0;
    }
}
