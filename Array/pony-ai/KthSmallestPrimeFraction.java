public class KthSmallestPrimeFraction {
    public int[] kthSmallestPrimeFraction(int[] A, int K) {
        int n = A.length;
        double lo = 0.0;
        double hi = 1.0;
        int[] result = new int[2];

        while(hi - lo > 1e-9) {
            double guess = lo + (hi - lo) / 2.0;
            int[] re = countNoLarger(guess, A);
            int counts = re[0];
            //System.out.println(guess + "\t" + counts + "\t" + re[1] + "/" + re[2]);
            if(counts < K) {
                lo = guess;
            }
            else {
                hi = guess;
                result[0] = re[1];
                result[1] = re[2];
            }
        }
        //System.out.println(lo);
        return result;
    }

    public int[] countNoLarger(double x, int[] primes) {
        // Returns {count, numerator, denominator}
        int numer = 0, denom = 1, count = 0, i = 0;
        for (int j = 1; j < primes.length; ++j) {
            // For each j, find the largest i so that primes[i] / primes[j] < x
            // It has to be at least as big as the previous i, so reuse it ("two pointer")
            while (i < j && primes[i] < primes[j] * x) ++i;

            // There are i+1 fractions: (primes[0], primes[j]),
            // (primes[1], primes[j]), ..., (primes[i], primes[j])
            count += i;
            if (i > 0 && numer * primes[j] < denom * primes[i - 1]) {
                numer = primes[i - 1];
                denom = primes[j];
            }
        }
        return new int[]{count, numer, denom};
    }    
}
