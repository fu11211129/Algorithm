import java.util.*;

public class CountSubarraySum {

    public static void main(String[] args) {
        int[] A = new int[] {10, 2, -2, -20, 10};
        int target = -10;
        System.out.println(count(A, target));
        System.out.println(count(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 5));
    }

    static int countSubarraySumEqualToK(int[] A, int K) {
        Map<Integer, Integer> map = new HashMap<> ();
        map.put(0, 1);

        int s = 0;
        int re = 0;

        for(int i=0; i<A.length; ++i) {
            s += A[i];
            if(map.containsKey(s - K)) re += map.get(s - K);
            map.put(s, map.getOrDefault(s, 0) + 1);
        }

        return re;
    }

    static int countSubarraySumEqualToMutipleOfK(int[] A, int K) {
        int[] mod = new int[K];
        int s = 0;
        for(int i=0; i<A.length; ++i) {
            s += A[i];
            mod[(s % k + k) % k] += 1;
        }

        int re = 0;
        for(int i=0; i<k; ++i) re += mod[i] * (mod[i] - 1)/2;
        re += mod[0];
        return re;
    }
}
