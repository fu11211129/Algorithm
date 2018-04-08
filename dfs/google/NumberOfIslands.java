public class NumberOfIslands {
    public int[][] dir = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public void dfs(char[][] grid, int x, int y) {
        int m = grid.length, n = grid[0].length;

        for(int i=0; i<dir.length; ++i) {
            int nx = x + dir[i][0], ny = y + dir[i][1];
            if(nx < 0 || ny < 0 || nx >= m || ny >= n || grid[nx][ny] != '1') continue;
            grid[nx][ny] = '#';
            dfs(grid, nx, ny);
        }
    }

    public int numIslands(char[][] grid) {
        if(grid == null || grid.length == 0) return 0;

        int m = grid.length, n = grid[0].length;
        int count = 0;

        for(int i=0; i<m; ++i) {
            for(int j=0; j<n; ++j) {
                if(grid[i][j] == '1') {
                    count += 1;
                    grid[i][j] = '#';
                    dfs(grid, i, j);
                }
            }
        }

        return count;
    }

}
