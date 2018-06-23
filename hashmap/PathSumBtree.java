public class PathSumBtree {

    public Map<Integer, Integer> map = new HashMap<> ();

    public int countPaths(TreeNode root, int s, int target) {
        if(root == null) return 0;
        int re = 0;

        s += root.val;
        if(map.containsKey(s - target)) re += map.get(s - target);
        map[s] += 1;
        re += countPaths(root.left, s, target) + countPaths(root.right, s, target);
        map[s] -= 1;
        
        return re;
    }
}
