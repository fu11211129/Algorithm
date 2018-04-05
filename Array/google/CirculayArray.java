public class CircularArray {
    public boolean func(int[] nums) {
    	if(nums == null || nums.length == 0) {
            // ask interviwer
        }
        
    	int n = nums.length;
    	int curr=0;
    	for(int i=0; i<n; ++i){
    		// go to another indice
    		curr = (nums[curr] + curr + n) % n;
            if(curr == 0 && i < n-1) return false;
        }
        return curr == 0;
    }

}
