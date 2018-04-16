import java.util.*;

public class SplitArrayWithEqualSum {
    public boolean splitArray(int[] nums) {
        int n = nums.length;

        if(n < 6) return false;
        int[] sum = new int[n];
        sum[0] = nums[0];
        for(int i=1; i<n; ++i) sum[i] = sum[i-1] + nums[i];

        for(int m=3; m<=n-4; ++m) {
            Set<Integer> set = new HashSet<> ();
            for(int l=1; l<=m-2; ++l) {
                int lblock = sum[l-1];
                int mblock = sum[m-1] - sum[l];
                if(lblock == mblock) set.add(mblock);
            }

            for(int r=n-2; r>=m-2; --r) {
                int mblock = sum[r-1] - sum[m];
                int rblock = sum[n-1] - sum[r];
                if(mblock == rblock && set.contains(mblock)) return true;
            }
        }

        return false;
    }
}
