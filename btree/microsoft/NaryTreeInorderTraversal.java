public class NaryTreeInorderTraversal {

    public static void main(String[] args) {
        NaryTreeInorderTraversal sol = new NaryTreeInorderTraversal();
        sol.inorderTraversal();
    }

    public void inorderTraversal(NaryTreeNode root) {
        Stack<NaryTreeNode> st = new Stack<> ();
        NaryTreeNode curr = root;

        while(curr != null || !st.empty()) {
            while(curr != null) {
                st.push(curr);
                curr = curr.left;
            }
            curr = st.pop();
            list.add(curr.val);
            curr = curr.right;
        }
    }

    class NaryTreeNode {
        int val;
        List<NaryTreeNode> children;

        public NaryTreeNode(int v) {
            this.val = v;
            this.children = new ArrayList();
        }
    }
}
