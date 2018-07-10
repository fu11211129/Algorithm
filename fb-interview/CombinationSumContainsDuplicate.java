import java.util.*;

public class CombinationSumContainsDuplicate {
    static Map<Integer, Map<Integer,Integer>> map2 = new HashMap<>();

    static int helper2(int[] nums, int len, int target, int MaxLen) {
        int count = 0;
        if (  len > MaxLen  ) return 0;
        if ( map2.containsKey(target) && map2.get(target).containsKey(len)) {
            return map2.get(target).get(len);
        }
        if ( target == 0 && len >= 1)   count++;
        for (int num: nums) {
            count+= helper2(nums, len+1, target-num, MaxLen);
        }
        if ( ! map2.containsKey(target) ) map2.put(target, new HashMap<Integer,Integer>());
        Map<Integer,Integer> mem = map2.get(target);
        mem.put(len, count);
        return count;
    }

    static int combinationSum42(int[] nums, int target, int MaxLen) {
        if (nums == null || nums.length ==0 || MaxLen <= 0 ) return 0;
        return helper2(nums, 0,target, MaxLen);
    }

    public static void main(String[] args) {
        int[] A = new int[] {-1, 1, 2, 3};
        int target = 4;
        int maxLen = 4;
        int re = combinationSum42(A, target, maxLen);
        System.out.println(re);

        System.out.println(map2.get(target));
    }
}
