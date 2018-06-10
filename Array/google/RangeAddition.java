public class RangeAddition {

    public static void main(String[] args) {
        RangeAddition sol = new RangeAddition();
        int length = 5;
        int[][] updates = new int[][] {
            {1, 3, 2},
            {2, 4, 3},
            {0, 2, -2}
        };
        int[] re = getModifiedArray(length, updates);
    }

    public int[] getModifiedArray(int length, int[][] updates) {

        int[] re = new int[length];
        for(int[] update: updates) {
            int from = update[0];
            int to = update[1];
            int inc = update[2];

            re[from] += inc;
            if(to + 1 < length) re[to + 1] -= inc;
        }

        int v = 0;
        for(int i=0; i<length; ++i) {
            v += re[i];
            re[i] = v;
        }

        return re;
    }
}
