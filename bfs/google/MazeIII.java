import java.util.*;

public class MazeIII {

    private int[][] dirs = new int[][] {{1, 0}, {0, -1}, {0, 1}, {-1, 0}};
    private String[] dirmap = new String[] {"d", "l", "r", "u"};

    public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
        int m = maze.length;
        if(m == 0) return "impossible";
        int n = maze[0].length;

        if(ball[0] == hole[0] && ball[1] == hole[1]) return "";

        Queue<Cell> Q = new LinkedList<> ();
        boolean[][][] used = new boolean[m][n][4];
        for(int i=0; i<dirs.length; ++i) {
            if(canRoll(ball[0], ball[1], i, maze, used)) {
                Q.offer(new Cell(ball[0], ball[1], i, dirmap[i]));
                used[ball[0]][ball[1]][i] = true;
            }
        }

        while(!Q.isEmpty()) {
            int size = Q.size();

            for(int k=0; k<size; ++k) {
                Cell c = Q.poll();
                int nx = c.x + dirs[c.dir][0];
                int ny = c.y + dirs[c.dir][1];

                if(nx == hole[0] && ny == hole[1]) return c.route;

                if(canRoll(nx, ny, c.dir, maze, used)) {
                    used[nx][ny][c.dir] = true;
                    //String route = c.route + dirmap[c.dir];
                    Q.offer(new Cell(nx, ny, c.dir, c.route));

                } else {
                    if(canRoll(nx, ny, (c.dir + 1) % 4, maze, used)) {
                        used[nx][ny][(c.dir + 1) % 4] = true;
                        String route = c.route + dirmap[(c.dir + 1) % 4];
                        Q.offer(new Cell(nx, ny, (c.dir + 1) % 4, route));
                    }

                    if(canRoll(nx, ny, (c.dir + 2) % 4, maze, used)) {
                        used[nx][ny][(c.dir + 2) % 4] = true;
                        String route = c.route + dirmap[(c.dir + 2) % 4];
                        Q.offer(new Cell(nx, ny, (c.dir + 2) % 4, route));
                    }

                    if(canRoll(nx, ny, (c.dir + 3) % 4, maze, used)) {
                        used[nx][ny][(c.dir + 3) % 4] = true;
                        String route = c.route + dirmap[(c.dir + 3) % 4];
                        Q.offer(new Cell(nx, ny, (c.dir + 3) % 4, route));
                    }
                }
            }
        }

        return "impossible";
    }

    // if the ball locates at (nx, y), check if it can move forward with given direction
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
        String route;

        public Cell(int xx, int yy, int di, String ro) {
            x = xx;
            y = yy;
            dir = di;
            route = ro;
        }
    }

    public static void main(String[] args) {
        MazeIII maze3 = new MazeIII();

        int[][] maze = new int[][] {
            {0, 0, 0, 0, 0},
            {1, 1, 0, 0, 1},
            {0, 0, 0, 0, 0},
            {0, 1, 0, 0, 1},
            {0, 1, 0, 0, 0}
        };

        int[] ball = new int[] {4, 3};
        int[] hole = new int[] {0, 1};
        String result = maze3.findShortestWay(maze, ball, hole);

        System.out.println(result);
    }
}
