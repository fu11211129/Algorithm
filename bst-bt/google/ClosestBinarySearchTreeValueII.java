public class ClosestBinarySearchTreeValueII {
    private TreeNode head = null;
    private TreeNode prev = null;

    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        flatten(root);

        TreeNode p = head;
        int c = 0;
        Deque<Integer> dq = new ArrayDeque<> ();

        while(p != null && c < k) {
            dq.addLast(p.val);
            p = p.right;
            c += 1;
        }

        if(p == null) return new ArrayList<Integer>(dq);

        while(p != null) {
            int curr = p.val;
            int first = dq.pollFirst();

            long lcurr = (long)curr;
            long lfirst = (long)first;
            if(Math.abs(curr - target) < Math.abs(first - target)) dq.addLast(curr);
            else dq.addFirst(first);

            p = p.right;
        }

        return new ArrayList<Integer> (dq);
    }

    private void flatten(TreeNode root) {
        if(root == null) return;

        flatten(root.left);

        if(head == null) {
            head = root;
            prev = root;
        } else {
            prev.right = root;
            root.left = prev;
            prev = root;
        }

        flatten(root.right);
    }    
}
