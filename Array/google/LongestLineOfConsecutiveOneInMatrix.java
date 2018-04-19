import java.util.*;

public class LongestLineOfConsecutiveOneInMatrix {
    public int longestLine(int[][] M) {
        if (M.length == 0)
            return 0;
        int ones = 0;
        int[][][] dp = new int[M.length][M[0].length][4];
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M[0].length; j++) {
                if (M[i][j] == 1) {
                    dp[i][j][0] = j > 0 ? dp[i][j - 1][0] + 1 : 1;
                    dp[i][j][1] = i > 0 ? dp[i - 1][j][1] + 1 : 1;
                    dp[i][j][2] = (i > 0 && j > 0) ? dp[i - 1][j - 1][2] + 1 : 1;
                    dp[i][j][3] = (i > 0 && j < M[0].length - 1) ? dp[i - 1][j + 1][3]+1 : 1;
                    ones = Math.max(ones, Math.max(Math.max(dp[i][j][0], dp[i][j][1]), Math.max(dp[i][j][2], dp[i][j][3])));
                }
            }
        }
        return ones;
    }

    private int max(int v, int a, int b, int c, int d) {
        return Math.max(v, Math.max(Math.max(a, b), Math.max(c, d)));
    }

    public static void main(String[] args) {
        LongestLineOfConsecutiveOneInMatrix sol = new LongestLineOfConsecutiveOneInMatrix();
        int re = sol.longestLine(new int[][] {
            {0, 1, 0, 1, 1},
            {1, 1, 0, 0, 1},
            {0, 0, 0, 1, 0},
            {1, 0, 1, 1, 1},
            {1, 0, 0, 0, 1}
        });
        System.out.println(re);
    }
}
