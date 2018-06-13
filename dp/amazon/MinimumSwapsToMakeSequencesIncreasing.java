public class MinimumSwapsToMakeSequencesIncreasing {
    public int minSwap(int[] A, int[] B) {
        int n = A.length;
        if(n == 1) return 0;

        int[] swap = new int[n];
        int[] noSwap = new int[n];

        swap[0] = 1;
        noSwap[0] = 0;

        for(int i=1; i<n; ++i) {
            swap[i] = (1 << 31) - 1;
            noSwap[i] = (1 << 31) - 1;

            if(A[i - 1] < A[i] && B[i - 1] < B[i]) {
                noSwap[i] = noSwap[i - 1];
                swap[i] = swap[i - 1] + 1;
            }

            if(A[i - 1] < B[i] && B[i - 1] < A[i]) {
                noSwap[i] = Math.min(noSwap[i], swap[i - 1]);
                swap[i] = Math.min(swap[i], noSwap[i - 1] + 1);
            }
        }

        return Math.min(noSwap[n - 1], swap[n - 1]);
    }    
}
