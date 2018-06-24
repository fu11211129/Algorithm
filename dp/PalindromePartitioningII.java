public class PalindromePartitioningII {
    public int minCut(String s) {
        if(s == null || s.length() <= 1) return 0;

        int n = s.length();
        boolean[][] isParlindrome = new boolean[n][n];
        for(int i=0; i<n; ++i) {
            for(int j=0; j<=i; ++j) {
                isParlindrome[i][j] = true;
            }
        }
        for(int le=2; le<=n; ++le) {
            for(int i=0; i+le<=n; ++i) {
                int j = i+le-1;
                if(isParlindrome[i+1][j-1] && s.charAt(i) == s.charAt(j)) {
                    isParlindrome[i][j] = true;
                }
            }
        }

        int[] dp = new int[n];
        for(int i=0; i<n; ++i) {
            dp[i] = isParlindrome[0][i]? 0: i;
            for(int pre=0; pre<=i-1; ++pre) {
                if(isParlindrome[pre+1][i]) {
                    dp[i] = Math.min(dp[i], dp[pre]+1);
                }
                dp[i] = Math.min(dp[i], dp[pre]+i-pre);
            }
        }
        return dp[n-1];
    }    
}
