// You are given a m x n 2D grid initialized with these three possible values.
//
// -1 - A wall or an obstacle.
// 0 - A gate.
// INF - Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
// Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.
//
// For example, given the 2D grid:
// INF  -1  0  INF
// INF INF INF  -1
// INF  -1 INF  -1
//   0  -1 INF INF
// 
// After running your function, the 2D grid should be:
//   3  -1   0   1
//   2   2   1  -1
//   1  -1   2  -1
//   0  -1   3   4

public class WallsAndGates {
    private int[][] dirs = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public void wallsAndGates(int[][] rooms) {
        int m = rooms.length;
        if(m == 0) return;

        int n = rooms[0].length;

        Queue<Position> Q = new LinkedList<> ();
        for(int i=0; i<m; ++i) {
            for(int j=0; j<n; ++j) {
                if(rooms[i][j] == 0) {
                    Q.offer(new Position(i, j));
                }
            }
        }

        while(!Q.isEmpty()) {
            Position curr = Q.poll();
            int x = curr.x;
            int y = curr.y;

            for(int i=0; i<dirs.length; ++i) {
                int nx = x + dirs[i][0];
                int ny = y + dirs[i][1];

                if(nx < 0 || ny < 0 || nx >= m || ny >= n || rooms[nx][ny] != Integer.MAX_VALUE) continue;
                Q.offer(new Position(nx, ny));
                rooms[nx][ny] = rooms[x][y] + 1;
            }
        }
    }

    class Position {
        int x;
        int y;

        public Position(int xx, int yy) {
            x = xx;
            y = yy;
        }
    }
}
