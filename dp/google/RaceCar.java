public class RaceCar {
    public int racecar(int target) {
        Integer[] f = new Integer[target + 1];
        return DP(target, f);
    }

    public int DP(int t, Integer[] f) {
        if(t == 0) return 0;
        if(f[t] != null) return f[t];

        int k = 32 - Integer.numberOfLeadingZeros(t);
        if(t == (1 << k) - 1) return f[t] = k;

        f[t] = (1 << 31) - 1;

        f[t] = Math.min(f[t], k + 1 + DP((1 << k) - 1 - t, f));

        for(int b=0; b<k-1; ++b) {
            int pos = (1 << (k - 1)) - (1 << b);
            f[t] = Math.min(f[t], k + b + 1 + DP(t - pos, f));
        }

        return f[t];
    }    
}
