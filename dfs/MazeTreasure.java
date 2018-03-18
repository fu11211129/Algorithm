import java.util.*;

public class MazeTreasure {
    public static void main(String[] args) {
        // String[] maze = new String[] {
        //     "S.X.",
        //     "a.X.",
        //     "..XG",
        //     "...."
        // };

        char[][] maze = new char[][] {
            {'S','.','X','a'},
            {'.','a','B','C'},
            {'b','.','C','G'}
        };

        boolean success = findTreasure(maze);

        System.out.println(success);
        //
        // maze = new String[] {
        //     "S.Xa",
        //     ".aXB",
        //     "b.AG",
        // };
        //
        // success = findTreasure(maze);
    }

    static boolean findTreasure(char[][] maze) {
        int[] door = new int[256];
        int[] key = new int[256];
        Cell start = null;
        Cell treasure = null;
        int m = maze.length;
        int n = maze[0].length;

        for(int r=0; r<m; ++r) {
            for(int i=0; i<n; ++i) {
                char c = maze[r][i];
                //if(Character.isUpperCase(c)) door[c] += 1;
                if(c == 'S') start = new Cell(r, i);
                if(c == 'G') treasure = new Cell(r, i);
            }
        }

        //System.out.println(start.x + ", " + start.y + "\n" + treasure.x + ", " + treasure.y);

        if(start == null || treasure == null) return false;

        Queue<Cell> Q = new LinkedList<> ();
        Set<Integer> used = new HashSet<> ();
        int prevKeys = -1;

        while(true) {

            Q.clear();
            used.clear();
            Q.offer(start);
            used.add(start.x * n + start.y);

            while(!Q.isEmpty()) {
                Cell curr = Q.poll();
                System.out.print("("+curr.x + ", " + curr.y + ")" + " => ");
                if(curr.x == treasure.x && curr.y == treasure.y) return true;

                for(int i=0; i<dirs.length; ++i) {
                    int nx = curr.x + dirs[i][0], ny = curr.y + dirs[i][1];
                    if(nx < 0 || nx >= m || ny < 0 || ny >= n) continue;

                    int id = nx * n + ny;
                    char c = maze[nx][ny];

                    if(used.contains(id) || c == 'X') continue;
                    if(c >= 'A' && c <= 'E') { // a door
                        if(key[Character.toLowerCase(c)] == 0) continue;
                        else if(key[c] > 0){
                            maze[nx][ny] = '.';
                            key[c] -= 1;
                        }
                    }
                    if(c >= 'a' && c <= 'e') {
                        key[c] += 1;
                        maze[nx][ny] = '.';
                    }

                    Q.offer(new Cell(nx, ny));
                    used.add(id);
                }
            }

            int keys = 0;
            for(int i=0; i<256; ++i) keys += key[i];

            pln(maze);
            // t -= 1;
            if(keys == prevKeys) break;

            prevKeys = keys;
        }

        return false;
    }

    static class Cell {
        int x;
        int y;
        public Cell(int xx, int yy) {
            x = xx;
            y = yy;
        }

        public int hashCode() {
            return new Integer(x).hashCode();
        }

        public boolean equals(Cell another) {
            return x == another.x && y == another.y;
        }
    }

    static int[][] dirs = new int[][] {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};

    static void pln(char[][] maze) {
        int m = maze.length, n = maze[0].length;
        for(int r=0; r<m; r++) {
            for(int c=0; c<n; ++c) System.out.print(maze[r][c]);
            System.out.println();
        }
        System.out.println("========");
    }
}
