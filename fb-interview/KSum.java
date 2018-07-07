import java.util.*;

public class KSum {
    public static void main(String[] args) {
        int[] A = new int[] {1, 2, 3, 5, 7, 8, 11, 4, 6, 10};
        int n = A.length;
        int k = 3;
        int V = 20;
        int[][] f = new int[k + 1][V + 1];
        f[0][0] = 1;

        for(int i = 1; i <= n; i += 1) {
            for(int j = 1; j <= k; j += 1) {
                for(int v = V; v >= A[i - 1]; v -= 1) {
                    f[j][v] += f[j - 1][v - A[i - 1]];
                }
            }
        }

        System.out.println(f[k][n]);

        Arrays.sort(A);
        System.out.println(Arrays.toString(A));

        int counts = 0;
        for(int i = 0; i <= n - 3; i += 1) {
            for(int j = i + 1; j <= n - 2; j += 1) {
                for(int p = j + 1; p <= n - 1; p += 1) {
                    if(A[i] + A[j] + A[p] == V) {
                        counts += 1;
                        System.out.println(A[i] + " " + A[j] + " " + A[p]);
                    }
                }
            }
        }
        System.out.println(counts);
    }
}
