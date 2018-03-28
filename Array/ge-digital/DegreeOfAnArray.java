public class DegreeOfAnArray {
    public int findShortestSubArray(int[] nums) {
        Map<Integer, Integer> counts = new HashMap<> ();
        Set<Integer> set = new HashSet<> ();
        int l=0,r=0;
        int n = nums.length;
        int degree = 0;

        // get degree of whole array
        for(; r<n; ++r) {
            if(!counts.containsKey(nums[r])) counts.put(nums[r], 0);
            counts.put(nums[r], counts.get(nums[r]) + 1);
            degree = Math.max(degree, counts.get(nums[r]));
        }

        // System.out.println(degree);

        r=0;
        counts.clear();
        int d = 0;
        int minl = n;

        for(;r<n; ++r) {
            if(!counts.containsKey(nums[r])) counts.put(nums[r], 0);
            int f = counts.get(nums[r]);
            counts.put(nums[r], f + 1);

            if(f + 1 > d) {
                d = f + 1;
                set.clear();
                set.add(nums[r]);
            } else if(f + 1 == d) {
                set.add(nums[r]);
            }

            if(d == degree) {
                while(l <= r && (!set.contains(nums[l]) || set.size() > 1)) {
                    if(!set.contains(nums[l])) counts.put(nums[l], counts.get(nums[l]) - 1);
                    else if(set.size() > 1) set.remove(nums[l]);
                    l += 1;
                }
                if(r-l+1 < minl) minl = r-l+1;
            }

            // System.out.println(d);
        }

        return minl;
    }    
}
