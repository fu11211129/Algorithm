public class WiggleSort {
    public void wiggleSort(int[] nums) {
        int n = nums.length;
        if(n <= 1) return;

        for(int i=0; i<n; ++i) {
            if((i & 1) == 1 && nums[i-1] > nums[i]) {
                swap(nums, i-1, i);
            } else if((i & 1) == 0 && i > 0 && nums[i-1] < nums[i]) {
                swap(nums, i-1, i);
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }    
}
