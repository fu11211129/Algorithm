// https://leetcode.com/problems/01-matrix/description/

import java.util.*;

public class Matrix01 {

    private int[][] dir = new int[][] {{-1,0}, {1,0}, {0,-1}, {0,1}, {-1,1}, {1, 1}, {1, -1}, {-1,-1}};

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

    public static void main(String[] args) {
        int[][] mat = new int[][] {
            {0, 0, 0, 1, 1},
            {0, 1, 1, 1, 0},
            {1, 1, 1, 1, 0},
            {1, 1, 1, 1, 1},
            {0, 1, 1, 1, 1}
        };

        int m = mat.length;
        int n = mat[0].length;
        int[][] cp = new int[m][n];
        for(int i=0; i<m; ++i) {
            for(int j=0; j<n; ++j) cp[i][j] = mat[i][j];
        }

        Matrix01 mt = new Matrix01();
        int[][] result = mt.updateMatrix(mat);

        for(int i=-1; i<m+1; ++i) {
            if(i == -1 || i == m) {
                for(int j=0; j<n+2; ++j) {
                    System.out.print("* ");
                }System.out.print("\t");

                for(int j=0; j<n+2; ++j) {
                    System.out.print("* ");
                }System.out.println();

            } else {
                System.out.print("* ");
                for(int j=0; j<n; ++j) {
                    System.out.print(cp[i][j] + " ");
                }System.out.print("*\t");

                System.out.print("* ");
                for(int j=0; j<n; ++j) {
                    System.out.print(result[i][j] + " ");
                }
                System.out.println("*");
            }
        }
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
