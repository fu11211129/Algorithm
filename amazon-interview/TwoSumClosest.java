import java.util.*;

public class TwoSumClosest {
    public int twoSumCloset(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            return -1;
        }

        if (nums.length == 2) {
            return target - nums[0] - nums[1];
        }
        Arrays.sort(nums);
        int pl = 0;
        int pr = nums.length - 1;

        int minDiff = Integer.MAX_VALUE;
        while (pl < pr) {
            int sum = nums[pl] + nums[pr];
            int diff = Math.abs(sum - target);
            if (diff == 0) {
                return 0;
            }
            if (diff < minDiff ) {
                minDiff = diff;
            }
            if (sum > target) {
                pr--;
            } else {
                pl++;
            }
        }
        return minDiff;
    }

    public static void main(String[] args) {
        int[] nums = new int[] {-1, 2, 1, -4};
        int target = 4;
        TwoSumClosest sol = new TwoSumClosest();
        int mindif = sol.twoSumCloset(nums, target);
        System.out.println(mindif);
    }
}
