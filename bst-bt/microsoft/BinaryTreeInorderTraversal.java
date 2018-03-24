public class BinaryTreeInorderTraversal {
    public static void main(String[] args) {

    }

    public List<TreeNode> inorderTraversal(TreeNode root) {
        List<TreeNode> io = new ArrayList<> ();
        if(root == null) return io;

        TreeNode curr = root;
        while(curr != null && st.empty()) {
            while(curr != null) {
                st.push(curr);
                curr = curr.left;
            }
            curr = st.pop();
            io.add(curr);
            curr = curr.right;
        }
    }
}
