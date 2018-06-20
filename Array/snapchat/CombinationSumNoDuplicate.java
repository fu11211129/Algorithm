import jaav.util.*;

public class CombinationSumNoDuplicate {

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer> > re = new ArrayList<>();
        List<Integer> load = new ArrayList<>();
        Arrays.sort(candidates);
        int curr = 0;
        int index = -1;

        dfs(candidates, curr, target, index, re, load);
        return re;
    }

    public void dfs(int[] A, int curr, int target, int index, List<List<Integer>> re, List<Integer> load) {
        if(curr > target || index == A.length) return;
        if(curr == target) {
            List<Integer> sol = new ArrayList<> (load);
            re.add(sol);
            return;
        }

        for(int i=index+1; i<A.length; ++i) {
            if(i > index + 1 && A[i] == A[i - 1]) continue;
            load.add(A[i]);
            dfs(A, curr + A[i], target, i, re, load);
            load.remove(load.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] candidates = new int[] {10,1,2,7,6,1,5};
        int target = 8;
        CombinationSumNoDuplicate sol = new CombinationSumNoDuplicate();
        List<List<Integer>> re = sol.combinationSum2(candidates, target);
        System.out.println(re);
    }
}
