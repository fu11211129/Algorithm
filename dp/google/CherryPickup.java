import java.util.*;

public class CherryPickup {
    public int cherryPickup(int[][] grid) {
        int n = grid.length;
        int[][][] f = new int[n][n][n];
        for(int i=0; i<n; ++i) {
            for(int j=0; j<n; ++j) Arrays.fill(f[i][j], Integer.MIN_VALUE);
        }

        int result = dp(f, grid, n-1, n-1, n-1);
        return Math.max(result, 0);
    }

    // when player 1 stands at (x1, y1) and players stands at (x2, y2),
    // the maximal number of cherries get so far
    static int dp(int[][][] f, int[][] grid, int x1, int y1, int x2) {
        int y2 = x1 + y1 - x2;

        if(x1 < 0 || x2 < 0 || y1 < 0 || y2 < 0 || grid[x1][y1] == -1 || grid[x2][y2] == -1) return -1;

        // in case both two players stand at (0, 0), neither of them move forward
        // so just return the number of cherries at cell (0, 0)
        if(x1 == 0 && y1 == 0) return grid[x1][y1];

        // if the subsolution has been calculated before, just query it from dp table
        if(f[x1][y1][x2] != Integer.MIN_VALUE) return f[x1][y1][x2];

        int result = max(dp(f, grid, x1-1, y1, x2-1), dp(f, grid, x1, y1-1, x2-1),
                dp(f, grid, x1-1, y1, x2), dp(f, grid, x1, y1-1, x2));

        // if result < 0, means both of two players get stuck at (x1, y1) & (x2, y2) respectively.
        if(result < 0) return f[x1][y1][x2] = -1;

        result += grid[x1][y1];
        // if two players stand at the same place, only count the number of cherries at cell (i, j) once.
        if(x1 != x2) result += grid[x2][y2];
        return f[x1][y1][x2] = result;
    }

    static int max(int a, int b, int c, int d) {
        return Math.max(Math.max(a, Math.max(b, c)), d);
    }
}
