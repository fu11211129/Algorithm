import java.util.*;

public class RemoveInvalidParentheses {

    public List<String> removeInvalidParentheses(String s) {

        char[] A = s.toCharArray();
        int len = A.length;

        // find how many ( and ) need to be removed.
        int rmL = 0, rmR = 0;
        for(int i = 0; i < len; i += 1) {
            if(A[i] == '(') rmL += 1;
            else if(A[i] == ')'){
                if(rmL > 0) rmL -= 1;
                else rmR += 1;
            }
        }

        List<String> re = new ArrayList<> ();
        Set<String> set = new HashSet<> ();
        String load = "";
        int index = 0;
        int open = 0;
        dfs(A, index, load, rmL, rmR, open, set);
        re.addAll(set);
        return re;
    }

    public void dfs(char[] A, int index, String load, int rmL, int rmR, int open, Set<String> set) {
        if(index == A.length) {
            if(rmL == 0 && rmR == 0 && open == 0) set.add(load);
            return;
        }

        if(A[index] == '(') {
            dfs(A, index + 1, load + "(", rmL, rmR, open + 1, set); // pick it up
            if(rmL > 0) dfs(A, index + 1, load, rmL - 1, rmR, open, set); // drop it
        } else if(A[index] == ')') {
            if(open > 0) dfs(A, index + 1, load + ")", rmL, rmR, open - 1, set);
            if(rmR > 0) dfs(A, index + 1, load, rmL, rmR - 1, open, set);
        } else {
            dfs(A, index + 1, load + A[index], rmL, rmR, open, set);
        }
    }

    static int minRemove(String s) {
        int left = 0, right = 0;
        for(int i=0; i<s.length(); ++i) {
            if(s.charAt(i) == '(') left += 1;
            else if(s.charAt(i) == ')') {
                if(left > 0) left -= 1;
                else right += 1;
            }
        }
        return left + right;
    }

    public static void main(String[] args) {
        String s = "()()))))))))))))))))))()";
        List<String> valid = removeInvalidParentheses(s);
        for(String vs: valid) {
            System.out.println(vs);
        }
    }
}
