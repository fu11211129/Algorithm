public class FindMaxCross {
    public int getMaxCross(int[][] A) {
        int m = A.length;
        int n = A[0].length;

        int[][] L = new int[m][n];
        int[][] R = new int[m][n];
        int[][] U = new int[m][n];
        int[][] D = new int[m][n];

        Cross[i,j] = L[i,j] + R[i,j] + U[i,j] + D[i,j] - 3;

        return max(Cross[i,j]);
    }
}
