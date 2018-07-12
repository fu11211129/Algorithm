public class BuildBTFromStringOfParenthesesAndIntegers {
    public TreeNode str2tree(String s) {
        if(s.equals("")) return null;

        int idx = 0;
        int n = s.length();
        char[] c = s.toCharArray();
        Stack<TreeNode> st = new Stack<> ();

        for(; idx < n; ) {
            if(c[idx] == '-' || (c[idx] >= '0' && c[idx] <= '9')) {
                int sign = 1;
                if(c[idx] == '-') {
                    sign = -1;
                    idx += 1;
                }
                int start = idx;
                int v = 0;
                for(; start < n && c[start] >= '0' && c[start] <= '9'; v = v * 10 + (c[start] - '0'), start += 1);
                v *= sign;

                TreeNode node = new TreeNode(v);
                st.push(node);
                idx = start;

            } else if(c[idx] == '(') {
                idx += 1;
                int sign = 1;
                if(c[idx] == '-') {
                    sign = -1;
                    idx += 1;
                }
                int start = idx;
                int v = 0;
                for(; start < n && c[start] >= '0' && c[start] <= '9'; v = v * 10 + (c[start] - '0'), start += 1);
                v *= sign;

                TreeNode node = new TreeNode(v);
                if(st.empty() == false) {
                    if(st.peek().left == null) st.peek().left = node;
                    else if(st.peek().right == null) st.peek().right = node;
                }
                st.push(node);
                idx = start;

            } else if(c[idx] == ')') {
                idx += 1;
                st.pop();
            }
        }
        return st.pop();
    }    
}
