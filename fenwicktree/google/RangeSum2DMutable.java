public class RangeSum2DMutable {
    private int[][] nums;
    private FenwickTree ft = null;

    public RangeSum2DMutable(int[][] matrix) {
        int m = matrix.length;
        int n = m == 0? 0: matrix[0].length;
        nums = new int[m][n];
        ft = new FenwickTree(m, n);

        for(int i=0; i<m; ++i) {
            for(int j=0; j<n; ++j) {
                nums[i][j] = matrix[i][j];
                int delta = nums[i][j];
                ft.update(i+1, j+1, delta);
            }
        }
    }

    public void update(int row, int col, int val) {
        int delta = val - nums[row][col];
        nums[row][col] = val;
        ft.update(row+1, col+1, delta);
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return ft.getSum(row2+1, col2+1) - ft.getSum(row1, col2+1) - ft.getSum(row2+1, col1) + ft.getSum(row1, col1);
    }

    class FenwickTree {
        public int numRows;
        public int numCols;
        public int[][] sum;

        public FenwickTree(int nR, int nC) {
            numRows = nR;
            numCols = nC;
            sum = new int[nR + 1][nC + 1];
        }

        public void update(int r, int c, int delta) {
            for(int i=r; i<=numRows; i+=i&(-i)) {
                for(int j=c; j<=numCols; j+=j&(-j)) {
                    sum[i][j] += delta;
                }
            }
        }

        public int getSum(int r, int c) {
            int result = 0;
            for(int i=r; i>0; i-=i&(-i)) {
                for(int j=c; j>0; j-=j&(-j)) {
                    result += sum[i][j];
                }
            }
            return result;
        }
    }
}
