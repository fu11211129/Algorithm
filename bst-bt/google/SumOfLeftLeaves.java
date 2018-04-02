public class SumOfLeftLeaves {
    public int sumOfLeftLeaves(TreeNode root) {
        return sumOfLeftLeavesUtil(root);
    }

    public int sumOfLeftLeavesUtil(TreeNode root) {
        if(root == null) return 0;
        // if(root.left == null && root.right == null) return root.val;

        Queue<TreeNode> Q = new LinkedList<> ();
        Set<TreeNode> leftLeaves = new HashSet<> ();
        Q.offer(root);
        while(Q.isEmpty() == false) {
            TreeNode curr = Q.poll();
            boolean isLeaf = true;

            if(curr.left != null) {
                Q.offer(curr.left);
                leftLeaves.add(curr.left);
                isLeaf = false;
            }

            if(curr.right != null) {
                Q.offer(curr.right);
                isLeaf = false;
            }

            if(!isLeaf) leftLeaves.remove(curr);
        }

        int sum = 0;
        for(TreeNode e: leftLeaves) {
            sum += e.val;
        }

        return sum;
    }    
}
