public class BalancedBinaryTree {
    private int depth(TreeNode o) {
        if(o == null) return 0;

        int lh = depth(o.left);
        int rh = depth(o.right);
        if(lh == -1 || rh == -1 || Math.abs(lh - rh) > 1) return -1;
        return Math.max(lh, rh) + 1;
    }

    public boolean isBalanced(TreeNode root) {
        if(root == null) return true;

        int hdif = depth(root);
        return hdif != -1;
    }    
}
