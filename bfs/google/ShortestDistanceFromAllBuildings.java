public class ShortestDistanceFromAllBuildings {
    private int[][] dirs = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int shortestDistance(int[][] grid) {
        int m = grid.length;
        if(m == 0) return -1;
        int n = grid[0].length;

        // dist[i][j] = shortest distance from (i, j) -> one of the buildings.
        int[][] dist = new int[m][n];

        // access[i][j] = how many building can be reached from position of (i, j)
        int[][] access = new int[m][n];

        int buildings = 0;
        for(int i=0; i<m; ++i) {
            for(int j=0; j<n; ++j) {
                if(grid[i][j] == 1) {
                    buildings += 1;
                    bfs(i, j, m, n, access, dist, grid);
                }
            }
        }

        int mind = (1 << 31) - 1;
        for(int i=0; i<m; ++i) {
            for(int j=0; j<n; ++j) {
                if(grid[i][j] == 0 && access[i][j] == buildings && dist[i][j] < mind) {
                    mind = dist[i][j];
                }
            }
        }

        return mind == (1 << 31) - 1? -1: mind;
    }

    private void bfs(int i, int j, int m, int n, int[][] access, int[][] dist, int[][] grid) {
        Queue<int[]> Q = new LinkedList<> ();
        Q.offer(new int[] {i, j});
        boolean[][] used = new boolean[m][n];
        int level = 0;

        while(!Q.isEmpty()) {
            int size = Q.size();

            while(size > 0) {
                int[] curr = Q.poll();
                int x = curr[0], y = curr[1];
                access[x][y] += 1;
                dist[x][y] += level;

                for(int k=0; k<dirs.length; ++k) {
                    int nx = x + dirs[k][0], ny = y + dirs[k][1];
                    if(nx >= 0 && ny >= 0 && nx < m && ny < n && !used[nx][ny] && grid[nx][ny] == 0) {
                        Q.offer(new int[] {nx, ny});
                        used[nx][ny] = true;
                    }
                }

                size -= 1;
            }
            level += 1;
        }
    }    
}
