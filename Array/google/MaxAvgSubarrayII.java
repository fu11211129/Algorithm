public class MaxAvgSubarrayII {
    public static void main(String[] args) {

    }
    
    private boolean check(double est, int[] nums, int k) {
        double sum = 0.0;
        for(int i=0; i<k; ++i) sum += 1.0 * nums[i] - est;

        // if sum of first leading k elements >= 0, meaning we find such a subarray whose sum >= 0.0
        if(sum >= 0.0) return true;

        double mi_pre_sum = 0;
        double pre_k_sum = 0;

        for(int i=k; i<nums.length; ++i) {
            sum += 1.0 * nums[i] - est;
            pre_k_sum += 1.0 * nums[i-k] - est;
            mi_pre_sum = Math.min(mi_pre_sum, pre_k_sum);

            // get the max sum.
            if(sum >= mi_pre_sum) return true;
        }

        return false;
    }

    public double findMaxAverage(int[] nums, int k) {
        if(nums == null || nums.length == 0) return 0;

        int n = nums.length;
        double mx = Double.MIN_VALUE, mi = Double.MAX_VALUE;
        for(int x: nums) {
            mx = Math.max(mx, (double)x);
            mi = Math.min(mi, (double)x);
        }

        if(mx == mi) return mx;

        double err = Double.MAX_VALUE;
        double pre_est = mx;
        double est = 0.0;

        while(err > 0.00001) {
            est = (mx + mi) * 0.5;
            if(check(est, nums, k)) {
                mi = est;
            } else {
                mx = est;
            }

            err = Math.abs(est - pre_est);
            pre_est = est;
        }

        return mx;
    }
}
