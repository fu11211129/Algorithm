public class CountUnivalueSubtrees {

    public int re = 0;

    public int countUnivalSubtrees(TreeNode root) {
        helper(root);
        return re;
    }

    public boolean helper(TreeNode r) {
        if(r == null) return true;
        else if(r.left == null && r.right == null) {
            re += 1;
            return true;
        }

        boolean lb = helper(r.left);
        boolean rb = helper(r.right);

        if(lb && rb) {
            if(r.left == null && r.right != null && r.right.val == r.val) {
                re += 1;
                return true;
            } else if(r.left != null && r.right == null && r.left.val == r.val) {
                re += 1;
                return true;
            } else if(r.left != null && r.right != null && r.left.val == r.right.val && r.left.val == r.val) {
                re += 1;
                return true;
            }
        }

        return false;
    }    
}
