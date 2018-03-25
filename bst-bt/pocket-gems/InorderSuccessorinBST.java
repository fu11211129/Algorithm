public class InorderSuccessorinBST {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode suc = null;
        while(root != null) {
            if(p.val > root.val) {
                root = root.right;
            } else if(p.val < root.val) {
                suc = root;
                root = root.left;
            } else {
                root = root.right;
            }

        }

        return suc;
    }    
}
