// leetcode 542. 01 Matrix
// Given a matrix consists of 0 and 1, find the distance of the nearest 0 for each cell.
// The distance between two adjacent cells is 1.
import java.util.*;

public class BinaryMatrix {
    private int[][] dir = new int[][] {{-1,0}, {1,0}, {0,-1}, {0,1}};

    private boolean check(int nx, int ny, int m, int n) {
        if(nx < 0 || nx >= m || ny < 0 || ny >= n) return false;
        return true;
    }

    public int[][] updateMatrix(int[][] matrix) {
        if(matrix == null || matrix.length == 0) return matrix;

        int m = matrix.length, n = matrix[0].length;
        boolean[][] vis = new boolean[m][n];
        LinkedList<node> Q = new LinkedList<> ();

        for(int i=0; i<m; ++i) {
            for(int j=0; j<n; ++j) {
                if(matrix[i][j] == 0) {
                    vis[i][j] = true;
                    Q.addLast(new node(i, j));
                }
            }
        }

        int distance = 0;

        while(!Q.isEmpty()) {
            int tot = Q.size();
            ++distance;

            while(tot > 0) {
                node nd = Q.pollFirst();
                for(int i=0; i<dir.length; ++i) {
                    int nx = nd.x + dir[i][0];
                    int ny = nd.y + dir[i][1];

                    if(check(nx, ny, m, n) && !vis[nx][ny] && matrix[nx][ny] != 0) {
                        matrix[nx][ny] = distance;
                        Q.addLast(new node(nx, ny));
                        vis[nx][ny] = true;
                    }
                }
                --tot;
            }

        }

        return matrix;
    }

    class node {
        public int x;
        public int y;
        public node(int xx, int yy) {
            this.x = xx;
            this.y = yy;
        }
    }
}
