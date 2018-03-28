public class TwoSumInBST {

    private TreeNode head = null;
    private TreeNode prev = null;

    public boolean findTarget(TreeNode root, int k) {
        tree2list(root);
        TreeNode tail = null;
        if(prev != null) tail = prev;

        if(head == null) return false;

        while(head != null && tail != null && head != tail) {
            if(head.val + tail.val == k) return true;
            else if(head.val + tail.val > k) tail = tail.left;
            else head = head.right;
        }

        return false;
    }

    private void tree2list(TreeNode root) {
        if(root == null) return;

        tree2list(root.left);
        if(head == null) {
            head = root;
            prev = root;
        }
        else {
            prev.right = root;
            root.left = prev;
            prev = root;
        }
        tree2list(root.right);
    }    
}
