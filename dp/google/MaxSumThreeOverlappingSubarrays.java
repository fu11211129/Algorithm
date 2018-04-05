import java.util.*;

public class MaxSumThreeOverlappingSubarrays {

    public static void main(String[] args) {
        MaxSumThreeOverlappingSubarrays mt = new MaxSumThreeOverlappingSubarrays();
        int[] nums = new int[] {7,13,20,19,19,2,10,1,1,19};
        int k = 3;
        int[] result = mt.maxSumOfThreeSubarrays(nums, k);
        System.out.println(result[0] + " " + result[1] + " " + result[2]);
    }

    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int n = nums.length;
        int[] sum = new int[n];
        // left[i] = i => max(sum[i-k+1:i])
        int[] left = new int[n];
        // right[i] = i => max(sum[i:i+k-1])
        int[] right = new int[n];

        sum[0] = nums[0];
        for(int i=1; i<n; ++i) sum[i] = sum[i-1]+ nums[i];

        left[k-1] = k-1;
        int leftMaxSum = sum[k-1] - sum[0] + nums[0];
        for(int i=k; i<n; ++i) {
            int currSum = getSum(i-k+1, i, sum, nums);
            if(currSum > leftMaxSum) {
                left[i] = i;
                leftMaxSum = currSum;
            } else {
                left[i] = left[i-1];
            }
        }

        right[n-k] = n-k;
        int rightMaxSum = getSum(n-k, n-1, sum, nums);
        for(int i=n-k-1; i>=0; --i) {
            int currSum = getSum(i, i+k-1, sum, nums);
            if(currSum > rightMaxSum) {
                rightMaxSum = currSum;
                right[i] = i;
            } else {
                right[i] = right[i+1];
            }
        }

        int resultSum = -(1 << 31);
        int[] result = new int[3];
        // i >= k
        // i + 2k - 1 <= n-1
        for(int i=k; i<=n-2*k; ++i) {
            // (i-1) [i : i+k-1] (i+k)
            int l = left[i-1], r = right[i+k];
            int currWindowSum = getSum(i, i+k-1, sum, nums);
            int leftMaxWindowSum = getSum(l-k+1, l, sum, nums);
            int rightMaxWindowSum = getSum(r, r+k-1, sum, nums);

            if(leftMaxWindowSum + currWindowSum + rightMaxWindowSum > resultSum) {
                resultSum = leftMaxWindowSum + currWindowSum + rightMaxWindowSum;
                result[0] = l-k+1;
                result[1] = i;
                result[2] = r;
            }
        }

        System.out.println(resultSum);
        return result;
    }

    public int getSum(int x, int y, int[] sum, int[] nums) {
        return sum[y] - sum[x] + nums[x];
    }
}
