public class BinaryTreeVerticalOrderTraversal {
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer> > results = new ArrayList<> ();
        if(root == null) return results;

        Queue<Node> que = new LinkedList<> ();
        Map<Integer, List<Integer> > off2vals = new HashMap<> ();
        int minOffset = 0, maxOffset = 0;


        que.offer(new Node(root, 0));
        while(que.size() > 0) {
            Node curr = que.poll();
            TreeNode no = curr.no;
            int offset = curr.offset;
            minOffset = Math.min(minOffset, offset);
            maxOffset = Math.max(maxOffset, offset);

            off2vals.putIfAbsent(offset, new ArrayList<> ());
            off2vals.get(offset).add(no.val);

            if(no.left != null) que.offer(new Node(no.left, offset - 1));

            if(no.right != null) que.offer(new Node(no.right, offset + 1));
        }

        // System.out.println(minOffset + " " + maxOffset);

        for(int i=minOffset; i<=maxOffset; ++i) {
            results.add(off2vals.get(i));
        }

        return results;
    }

    class Node {
        TreeNode no;
        int offset;

        public Node(TreeNode nd, int off) {
            no = nd;
            offset = off;
        }
    }    
}
