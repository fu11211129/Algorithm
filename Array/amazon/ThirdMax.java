public class ThirdMax {
    public int thirdMax(int[] nums) {
        Integer first = null;
        Integer second = null;
        Integer third = null;

        for(int i=0; i<nums.length; ++i) {
            Integer ii = nums[i];
            if(ii.equals(first) || ii.equals(second) || ii.equals(third)) continue; // avoid duplicates
            if(first == null || nums[i] > first) {
                third = second;
                second = first;
                first = nums[i];
            } else if(second == null || nums[i] > second) {
                third = second;
                second = nums[i];
            } else if(third == null || nums[i] > third) {
                third = nums[i];
            }

            System.out.println("first : " + first + "\t" + "second : " + second + "\t" + "third : " + third + "\n");
        }

        return third == null? first: third;
    }

    public static void main(String[] args) {
        int[] nums = new int[] {2, 2, 3, 1};
        ThirdMax tm = new ThirdMax();
        int thirdMx = tm.thirdMax(nums);

        System.out.println(nums);
    }
}
