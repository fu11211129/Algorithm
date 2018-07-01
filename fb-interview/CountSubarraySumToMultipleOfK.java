import java.util.*;

public class CountSubarraySumToMultipleOfK {
    static int count(int[] A, int k) {
        int[] mod = new int[k];
        mod[0] = 1;
        int s = 0;

        for(int i=0; i<A.length; ++i) {
            s = (s + A[i]) % k;
            if(s < 0) s = (s + k) % k;
            mod[s] += 1;
        }

        int re = 0;
        for(int i=0; i<k; ++i) {
            if(mod[i] >= 2) re = re + (mod[i] * (mod[i] - 1)) / 2;
        }
        return re;
    }

    static int countMultipleOfZero(int[] A) {
        Map<Integer, Integer> map = new HashMap<> ();
        map.put(0, 1);
        int s = 0;
        int re = 0;

        for(int i=0; i<A.length; ++i) {
            s = s + A[i];
            if(map.containsKey(s)) re += map.get(s);
            map.put(s, map.getOrDefault(s, 0) + 1);
        }

        return re;
    }

    static int helper(int[] A, int k) {
        int n = A.length;
        int[] sum = new int[n];
        sum[0] = 0;
        for(int i=1; i<n; ++i) {
            sum[i] = sum[i - 1] + A[i];
        }

        int re = 0;
        for(int i=0; i<n; ++i) {
            for(int j=i; j<n; ++j) {
                int cur = sum[j] - sum[i] + A[i];
                if(k == 0) {
                    if(cur == 0) re += 1;
                } else {
                    if(cur % k == 0) re += 1;
                }
            }
        }
        return re;
    }

    public static void main(String[] args) {
        int[] A = new int[] {-5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5};
        System.out.println(countMultipleOfZero(A));

        // int k = 5;
        // int re = count(A, k);
        // System.out.println(re);

        System.out.println(helper(A, 0));
    }
}
