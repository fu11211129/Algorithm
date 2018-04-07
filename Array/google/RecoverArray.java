public class RecoverArray {
    public static void main(String[] args) {
        int[] nums = new int[] {5, 3, 1, 2, 4}; // 5 3 2 1 4
        int n = nums.length;
        List<int[]> permutaions = new ArrayList<> ();
        getPermutations(nums, 0, n-1, permutaions);
        boolean[] used = new boolean[n + 1];

        int[] encoded = new int[] {0, 1, 2, 2, 1};
        int[] decoded = new int[n];
        for(int i=n-1; i>=0; --i) {
            int rank = 1 + encoded[i];
            decoded[i] = find(used, n, rank);
        }

        for(int i=0; i<n; ++i) {
            System.out.print(decoded[i] + " ");
        }
    }

    static int find(boolean[] used, int n, int rank) {
        int count = 0;
        for(int i=n; i>=1; --i) {
            if(used[i] == false) {
                count += 1;
                if(count == rank) {
                    used[i] = true;
                    return i;
                }
            }
        }
        return -1;
    }

    static void permute(int[] nums, int l, int r, List<int[]> permutaions) {
        if(l == r) {
            int[] line = new int[nums.length];
            for(int i=0; i<line.length; ++i) line[i] = nums[i];
            permutaions.add(line);
            return;
        }

        for(int k=l; k<=r; ++k) {
            swap(nums, l, k);
            permute(nums, l+1, r, permutaions);
            swap(nums, l, k);
        }
    }
}
