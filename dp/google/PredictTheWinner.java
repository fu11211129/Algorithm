public class PredictTheWinner {
    public boolean PredictTheWinner(int[] nums) {
        int n = nums.length;
        Integer[][] f = new Integer[n][n];

        return winnerUtil(nums, 0, nums.length-1, f) >= 0;
    }

    public int winnerUtil(int[] nums, int s, int e, Integer[][] f) {
        if(s == e) return nums[s];
        if(f[s][e] != null) return f[s][e];

        f[s][e] = Math.max(nums[e] - winnerUtil(nums, s, e-1, f), nums[s] - winnerUtil(nums, s+1, e, f));
        return f[s][e];
    }

    public static void main(String[] args) {
        PredictTheWinner pt = new PredictTheWinner();
        int[] nums = new int[] {1, 5, 233, 7};
        boolean win = pt.PredictTheWinner(nums);
        System.out.println(win);

        win = pt.PredictTheWinner(new int[] {1, 5, 2});
        System.out.println(win);
    }
}
