public class SmallestSubtreeWithAllTheDeepestNodes {
    public int maxd = 0;

    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        if(root == null) return null;

        Map<TreeNode, Integer> depth = new HashMap<> ();
        int d = 0;
        calcDepth(root, depth, d);

        return find(root, depth);
    }

    public TreeNode find(TreeNode root, Map<TreeNode, Integer> depth) {
        if(root == null || depth.get(root) == maxd) return root;

        TreeNode L = find(root.left, depth);
        TreeNode R = find(root.right, depth);

        if(L != null && R != null) return root;
        else if(L == null && R != null) return R;
        else if(L != null && R == null) return L;
        return null;
    }

    public void calcDepth(TreeNode root, Map<TreeNode, Integer> depth, int d) {
        maxd = Math.max(maxd, d);
        depth.put(root, d);

        if(root.left != null) calcDepth(root.left, depth, d + 1);
        if(root.right != null) calcDepth(root.right, depth, d + 1);
    }    
}
