import java.util.*;

public class DetectRectangleInBinaryMatrix {
    public static void main(String[] args) {
        int[][] mat = new int[][]{
            { 1, 0, 0, 1, 0 },
            { 0, 0, 0, 0, 1 },
            { 0, 0, 0, 1, 0 },
            { 0, 0, 1, 1, 1 }
        };

        boolean b = detect(mat);
        System.out.println(b);

    }

    static boolean detect(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        Set<String> used = new HashSet<> ();

        for(int i=0; i<m; ++i) {
            for(int j=0; j<n-1; ++j) {
                for(int k=j+1; k<n; ++k) {
                    if(mat[i][j] == 1 && mat[i][k] == 1) {
                        if(used.contains(j + "," + k)) {
                            System.out.println(j + "," + k);
                            return true;
                        }
                        used.add(j + "," + k);
                    }
                }
            }
        }

        return false;
    }
}
