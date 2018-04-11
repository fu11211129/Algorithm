import java.util.*;

public class NumberOfSquaresInBinaryMatrix {

    public static void main(String[] args) {
        char[][] mat = new char[][] {
            {'1', '0', '1', '0', '0'},
            {'1', '0', '1', '1', '1'},
            {'1', '1', '1', '1', '1'},
            {'1', '0', '0', '1', '0'}
        };

        int re = numberOfSquares(mat);
        System.out.println(re);
    }

    static int numberOfSquares(char[][] matrix) {
        if(matrix == null || matrix.length == 0) return 0;

        int m = matrix.length, n = matrix[0].length;
        int[][] dp = new int[m][n];
        int rs = 0;

        for(int i=0; i<m; ++i) {
            dp[i][n-1] = matrix[i][n-1] == '1'? 1: 0;
            // if(dp[i][n-1] == 1) rs = 1;
        }

        for(int i=0; i<n; ++i) {
            dp[m-1][i] = matrix[m-1][i] == '1'? 1: 0;
            // if(dp[m-1][i] == 1) rs = 1;
        }

        for(int i=m-2; i>=0; --i) {
            for(int j=n-2; j>=0; --j) {
                if(matrix[i][j] == '1') {
                    dp[i][j] = 1;
                    if(dp[i+1][j] > 0 && dp[i][j+1] > 0 && dp[i+1][j+1] > 0) dp[i][j] += 1;
                    // dp[i][j] = Math.min(dp[i+1][j], Math.min(dp[i][j+1], dp[i+1][j+1])) + 1;
                    // rs = Math.max(dp[i][j] * dp[i][j], rs);
                }
            }
        }

        for(int i=0; i<m; ++i) {
            for(int j=0; j<n; ++j) {
                if(dp[i][j] >= 1) {
                    System.out.println(i + "," + j + "\t" + dp[i][j]);
                    rs += dp[i][j];
                }
            }
        }
        return rs;
    }
}
