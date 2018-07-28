public class NextGreaterPermutation {
    public void nextPermutation(int[] nums) {
        int n = nums.length;
        if(n <= 1) return;

        int i = n - 2;
        for(; i >= 0 && nums[i] >= nums[i + 1]; i -= 1);

        if(i == -1) {
            reverse(nums, i + 1);
        } else {
            int j = n - 1;
            for(; i < j && nums[i] >= nums[j]; j -= 1);
            swap(nums, i, j);
            reverse(nums, i + 1);
        }
    }

    public void reverse(int[] A, int idx) {
        int l = idx, r = A.length - 1;
        for(; l < r; l += 1, r -= 1) swap(A, l, r);
    }

    public void swap(int[] A, int i, int j) {
        int ai = A[i];
        A[i] = A[j];
        A[j] = ai;
    }    
}
