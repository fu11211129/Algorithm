public class BSTIterator {

    public Stack<TreeNode> st;

    // push the left-most branch
    public BSTIterator(TreeNode o) {
        TreeNode p = o;
        while(p != null) {
            st.push(p);
            p = p.left;
        }
    }

    public boolean hasNext() {
        return !st.empty();
    }

    public TreeNode next() {
        if(hasNext()) {
            TreeNode p = st.pop();
            if(p.right != null) {
                q = p.right;
                while(q != null) {
                    st.push(q.left);
                    q = q.left;
                }
            }
            return re;
        }
        return null;
    }
}
