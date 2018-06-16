public class MaximumWidthOfBinaryTree {
    public int widthOfBinaryTree(TreeNode root) {
        Node R = new Node(root, 1);
        Queue<Node> Q = new LinkedList();
        Q.offer(R);
        int re = 0;

        while(Q.size() > 0) {
            int sz = Q.size();
            int mi = (1 << 31) - 1;
            int mx = -(1 << 31);
            for(int i=0; i<sz; ++i) {
                Node curr = Q.poll();
                mi = Math.min(curr.id, mi);
                mx = Math.max(curr.id, mx);

                if(curr.node.left != null) Q.offer(new Node(curr.node.left, curr.id * 2));
                if(curr.node.right != null) Q.offer(new Node(curr.node.right, curr.id * 2 + 1));
            }
            re = Math.max(mx - mi + 1, re);
        }

        return re;
    }

    class Node {
        TreeNode node;
        int id;

        public Node(TreeNode tnode, int i) {
            node = tnode;
            id = i;
        }
    }    
}
