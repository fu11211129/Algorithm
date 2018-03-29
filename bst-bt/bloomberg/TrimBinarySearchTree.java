public class TrimBinarySearchTree {
    public TreeNode trimBST(TreeNode root, int L, int R) {
        if(root == null) return null;

        if(root.val < L) {
            // all left-subtree nodes are smaller than L too
            root.right = trimBST(root.right, L, R);
            return root.right;

        } else if(root.val > R) {
            // all right-subtree nodes are greater than R too
            root.left = trimBST(root.left, L, R);
            return root.left;

        } else {
            // L <= root.val <= R

            // trim left-subtree
            root.left = trimBST(root.left, L, R);

            root.right = trimBST(root.right, L, R);
            return root;
        }

        // return root;
    }    
}
