import java.util.*;

public class CombinationSum {

	public static List<List<Integer> > result = new ArrayList<> ();

	public static List<List<Integer> > combinationSum(int[] nums, int target) {
		Arrays.sort(nums);

		List<Integer> candidate = new ArrayList();
		dfsNoDuplicates(nums, -1, 0, target, candidate);
		return result;
	}

	public static void dfs(int[] nums, int u, int sum, int target, List<Integer> candidate) {
		if(sum > target) {
			return;
		}

		if(sum == target) {
			result.add(new ArrayList<> (candidate));
			return;
		}

		for(int v=u; v<nums.length; ++v) {
			candidate.add(nums[v]);
			dfs(nums, v, sum+nums[v], target, candidate);
			candidate.remove(candidate.size()-1);
		}
	}

	public static void dfsNoDuplicates(int[] nums, int u, int sum, int target, List<Integer> candidate) {
		if(sum == target) {
			result.add(new ArrayList<> (candidate));
			return;
		}

		if(sum > target) {
			return;
		}

		for(int v=u+1; v<nums.length; ++v) {
			if(v>u+1 && nums[v] == nums[v-1]) continue;
			candidate.add(nums[v]);
			dfsNoDuplicates(nums, v, sum+nums[v], target, candidate);
			candidate.remove(candidate.size()-1);
		}		
	}

	public static void main(String[] args) {
		int[] nums = new int[] {10, 1, 2, 7, 6, 1, 5};
		List<List<Integer> > result = combinationSum(nums, 8);
		//System.out.println(result);

		for(List<Integer> ls: result) {
			System.out.println(ls);
		}
	}

	 public static List<List<Integer>> combinationSum2(int[] cand, int target) {
	    Arrays.sort(cand);
	    List<List<Integer>> res = new ArrayList<List<Integer>>();
	    List<Integer> path = new ArrayList<Integer>();
	    dfs_com(cand, 0, target, path, res);
	    return res;
	}

	public static void dfs_com(int[] cand, int cur, int target, List<Integer> path, List<List<Integer>> res) {
	    if (target == 0) {
	        res.add(new ArrayList(path));
	        return ;
	    }
	    if (target < 0) return;
	    for (int i = cur; i < cand.length; i++){
	        if (i > cur && cand[i] == cand[i-1]) continue;
	        path.add(path.size(), cand[i]);
	        dfs_com(cand, i+1, target - cand[i], path, res);
	        path.remove(path.size()-1);
	    }
	}
	
}