public class NumberOfWaysCanReachTargetIn2DPlane {
    int steps(int[] source, int[] dest) {
        int delta = dest[0] - source[0];
        int y = dest[1];

        // dest: [3, 2];
        // source: [0, 0];
        // delta = 3;
        // y: [-3: 3]

        if(dest[1] > delta) return -1;

        int[] f = new int[2*delta + 1];
        f[y] = f[y-1] + f[y+1];

        f[y +delta] = f[delta+y-1] + f[delta+1+y] ;

        f[2] = f[1] + f[4]

    }
}
