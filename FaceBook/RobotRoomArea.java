import java.util.*;

public class RobotRoomArea {

    static int[][] dirs = new int[][] {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

    static void pln(String s) {
        System.out.println(s);
    }

    static void bfs(int x, int y, int[][] grid) {
        int m = grid.length, n = grid[0].length;
        Queue<Node> Q = new LinkedList<> ();
        Q.offer(new Node(x, y));

        Set<Node> visited = new HashSet<> ();

        int areas = 0;

        while(!Q.isEmpty()) {
            Node no = Q.poll();
            areas += 1;

            for(int i=0; i<dirs.length; ++i) {
                int nx = no.x + dirs[i][0];
                int ny = no.y + dirs[i][1];
                Node nd = new Node(nx, ny);

                if(valid(nx, ny, gr
                id) && !visited.contains(nd)) {
                    visited.add(nd);
                    Q.offer(nd);
                }
            }
        }

        pln("Area = " + areas);
    }

    static int dfs(Node curr, int[][] grid, Set<Node> visited) {
        int m = grid.length, n = grid[0].length;

        if(!valid(curr.x, curr.y, grid) || visited.contains(curr)) return 0;

        //visited.add(curr);
        int areas = 1;

        Node next = nextPosition(curr);
        area += dfs(curr, grid, visted);

        for(int i=0; i<dirs.length; ++i) {
            Node next = new Node(curr.x + dirs[i][0], curr.y + dirs[i][1]);
            areas += dfs(next, grid, visited);
        }
        return areas;
    }

    static boolean valid(int x, int y, int[][] grid) {
        int m = grid.length, n = grid[0].length;
        if(x < 0 || x >= m || y < 0 || y >= n || grid[x][y] == 1) return false;
        return true;
    }

    static Node nextPosition(Node curr) {
        for(int i=0; i<4; ++i) {
            boolean canMove = move(curr, i);
            if(canMove) return new Node(curr.x + dirs[i][0], curr.y + dirs[i][1]);
        }
        return null;
    }

    // static boolean move(Node curr, int di) {
    //     int nx = curr.x
    //     if(curr.x + dirs[di][0] < 0 || curr.x + dirs[di][0] >= m || curr.y < 0 || curr.y + dirs[di][1] >= n || grid[x][y] == 1) return false;
    //     return true;
    // }


    public static void main(String[] args) {
        int[][] grid = new int[][] {
            {1, 1, 0, 0, 0},
            {0, 1, 0, 0, 1},
            {1, 0, 0, 1, 1},
            {0, 0, 0, 0, 0},
            {1, 0, 1, 0, 1}
        };

        // bfs(0, 2, grid);
        Node curr = new Node(0, 2);
        Set<Node> visited = new HashSet<> ();
        curr.times += 1;
        int areas = dfs(curr, grid, visited);
        pln(areas + "");
    }

    static class Node {
        int x;
        int y;
        int times;

        public Node(int xx, int yy) {
            x = xx;
            y = yy;
            times = 0;
        }

        public int hashCode() {
            return new Integer(x).hashCode();
        }

        @Override
        public boolean equals(Object o) {
            Node no = (Node) o;
            return no.x == this.x && no.y == this.y;
        }
    }
}
