// Input 1: a maze represented by a 2D array
//
// 0 0 1 0 0
// 0 0 0 0 0
// 0 0 0 1 0
// 1 1 0 1 1
// 0 0 0 0 0
//
// Input 2: start coordinate (rowStart, colStart) = (0, 4)
// Input 3: destination coordinate (rowDest, colDest) = (3, 2)
//
// Output: -1
// Explanation: There is no way for the ball to stop at the destination.

public class MazeII {

    private int[][] dirs = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        int m = maze.length;
        if(m == 0) return -1;
        int n = maze[0].length;

        int[][] dist = new int[m][n];
        boolean[][][] used = new boolean[m][n][4];
        int sx = start[0], sy = start[1];
        Queue<Cell> Q = new LinkedList<> ();

        for(int i=0; i<dirs.length; ++i) {
            if(canRoll(sx, sy, i, maze, used)) {
                Q.offer(new Cell(sx, sy, i));
                used[sx][sy][i] = true;
            }
        }

        int level = 0;
        while(!Q.isEmpty()) {
            int size = Q.size();

            level += 1;

            for(int k=0; k<size; ++k) {
                Cell c = Q.poll();
                int nx = c.x + dirs[c.dir][0];
                int ny = c.y + dirs[c.dir][1];

                if(nx == destination[0] && ny == destination[1] && !canRoll(nx, ny, c.dir, maze, used)) return level;


                if(canRoll(nx, ny, c.dir, maze, used)) {
                    used[nx][ny][c.dir] = true;
                    Q.offer(new Cell(nx, ny, c.dir));

                } else {
                    if(canRoll(nx, ny, (c.dir + 1) % 4, maze, used)) {
                        used[nx][ny][(c.dir + 1) % 4] = true;
                        Q.offer(new Cell(nx, ny, (c.dir + 1) % 4));
                    }

                    if(canRoll(nx, ny, (c.dir + 2) % 4, maze, used)) {
                        used[nx][ny][(c.dir + 2) % 4] = true;
                        Q.offer(new Cell(nx, ny, (c.dir + 2) % 4));
                    }

                    if(canRoll(nx, ny, (c.dir + 3) % 4, maze, used)) {
                        used[nx][ny][(c.dir + 3) % 4] = true;
                        Q.offer(new Cell(nx, ny, (c.dir + 3) % 4));

                    }

                }
            }

        }

        return -1;
    }

    // if the ball locates at (x, y), check if it can move forward with given direction
    public boolean canRoll(int nx, int ny, int d, int[][] maze, boolean[][][] used) {
        int nnx = nx + dirs[d][0];
        int nny = ny + dirs[d][1];
        if(nnx < 0 || nny < 0 || nnx >= maze.length || nny >= maze[0].length || maze[nnx][nny] == 1 || used[nx][ny][d]) return false;
        return true;
    }

    class Cell {
        int x;
        int y;
        int dir;

        public Cell(int xx, int yy, int di) {
            x = xx;
            y = yy;
            dir = di;
        }
    }
}
