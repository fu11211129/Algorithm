public class RandomSubsetOfSizeK {
    public int get(int[] A, int k) {
        int[] re = new int[k];
        for(int i = 0; i < k; i += 1) re[i] = A[i];

        for(int i = k; i < n; i += 1) {
            int j = rand.nextInt(i + 1); // select A[0 -> i]
            if(j < k) {
                // the probability of j < k is: k / i(currentlen)
                re[j] = A[i];
            }
        }
    }
}
