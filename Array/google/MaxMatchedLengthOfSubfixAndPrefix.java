public class MaxMatchedLengthOfSubfixAndPrefix {
    public static int getMaxMatchedLengthOfSubfixAndPrefix(int[] a, int[] b) {
        int alen = a.length;
        int blen = b.length;
        int[] next = new int[blen];

        next[0] = next[1] = 0;
        for(int i=2; i<blen; ++i) {
            int j = next[i-1];
            while(j != 0 && b[i-1] != b[j]) j = next[j];
            if(b[i-1] == b[j]) next[i] = j + 1;
            else next[i] = 0;
        }

        int i=0, j=0, maxMatchLen = 0;
        for(; i<alen; ++i) {
            while(j != 0 && a[i] != b[j]) j = next[j];
            if(a[i] == b[j]) j += 1;
            maxMatchLen = Math.max(j, maxMatchLen);
        }

        return maxMatchLen;
    }

    public static void main(String[] args) {
        int[] a = new int[] {1, 2, 3, 4, 2, 3, 4, 5};
        int[] b = new int[] {2, 3, 4, 5, 7};
        int result = getMaxMatchedLengthOfSubfixAndPrefix(a, b);
        System.out.println(result);
    }
}
