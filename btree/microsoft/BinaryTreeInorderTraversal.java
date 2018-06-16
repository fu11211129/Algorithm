public class BinaryTreeInorderTraversal {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> results = new ArrayList<> ();
        Stack<TreeNode> st = new Stack<> ();

        TreeNode curr = root;
        while(curr != null || !st.empty()) {
            while(curr != null) {
                st.push(curr);
                curr = curr.left;
            }

            curr = st.pop();
            results.add(curr.val);
            curr = curr.right;
        }

        return results;
    }    
}
