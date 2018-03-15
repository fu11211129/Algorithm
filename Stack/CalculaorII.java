public class CalculatorII {
    
    public int cal(int x, int y, char op) {
        if(op == '+') {
            return x + y;
        } else {
            return x - y;
        } 
    }
    
    public boolean hasPrecedence(char op1, char op2) {
        if (op2 == '(' || op2 == ')')
            return false;
        if ((op1 == '*' || op1 == '/'))
            return false;
        else
            return true;
    }
    
    public int calculate(String s) {
        if(s == null || s.length() == 0) return 0;
        
        char[] cs = s.trim().toCharArray();
        int n = cs.length;
        
        int i = 0;
        LinkedList<Integer> numsStack = new LinkedList<Integer> ();
        LinkedList<Character> opsStack = new LinkedList<Character> ();
        
        while(i < n) {
            if(cs[i] >= '0' && cs[i] <= '9') {
                StringBuffer e = new StringBuffer();
                while(i < n && cs[i] >= '0' && cs[i] <= '9') {
                    e.append(cs[i++]);
                } 
                numsStack.addFirst(Integer.parseInt(e.toString()));
            } 
            
            else if(cs[i] == ' ') {
                ++i;
            }
            
            else if(cs[i] == '(') {
                opsStack.addFirst(cs[i++]);
            } 
            
            else if(cs[i] == ')') {
                while(opsStack.peekFirst() != '(') {
                    int n2 = numsStack.pollFirst();
                    int n1 = numsStack.pollFirst();
                    char op = opsStack.pollFirst();
                    numsStack.addFirst(cal(n1, n2, op));
                }
                opsStack.pollFirst();
                ++i;
            }
            
            /** when cs[i] is '+' or '-' or '*' or '/' */
            else {
                while(!opsStack.isEmpty() && hasPrecedence(cs[i], opsStack.peekFirst())) {
                    int n2 = numsStack.pollFirst();
                    int n1 = numsStack.pollFirst();
                    char op = opsStack.pollFirst();
                    numsStack.addFirst(cal(n1, n2, op));
                }
                opsStack.addFirst(cs[i++]);
            }
        }
        
        while(!opsStack.isEmpty()) {
            int n2 = numsStack.pollFirst();
            int n1 = numsStack.pollFirst();
            char op = opsStack.pollFirst();
            numsStack.addFirst(cal(n1, n2, op));
        }
        
        return numsStack.pollFirst();
    }
}