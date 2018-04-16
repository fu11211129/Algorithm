import java.util.*;

public class TextJustification {

    public static int mx = (1 << 31) - 1;

    static int minCost(String[] words, int M) {
        int n = words.length;
        int[][] f = new int[n + 1][n + 1];
        int[][] extra = new int[n + 1][n + 1];
        int[] cost = new int[n + 1];

        for(int i=1; i<=n; ++i) {
            extra[i][i] = M - words[i-1].length();
            for(int j=i+1; j<=n; ++j) {
                extra[i][j] = extra[i][j-1] - words[j-1].length() - 1;
            }
        }

        for(int i=1; i<=n; ++i) {
            for(int j=i; j<=n; ++j) {
                if(extra[i][j] < 0) {
                    f[i][j] = mx;

                } else if(j == n && extra[i][j] >= 0) {
                    f[i][j] = 0;

                } else {
                    f[i][j] = extra[i][j] * extra[i][j] * extra[i][j];
                }
            }
        }

        cost[0] = 0;
        for(int i=1; i<=n; ++i) {
            cost[i] = (1 << 31) - 1;
            for(int pre=1; pre<=i; ++pre) {
                if(cost[pre-1] != mx && f[pre][i] != mx && cost[pre-1] + f[pre][i] < cost[i]) {
                    cost[i] = cost[pre-1] + f[pre][i];
                }
            }
        }

        return cost[n];
    }

    public static void main(String[] args) {
        String[] words = new String[] {"aaa", "bb", "cc", "ddddd"};
        int M = 6;
        int cost = minCost(words, M);
        System.out.println(cost);
    }
}
