public class NestedIntegerParser {
    public NestedInteger deserialize(String s) {
        if(!s.startsWith("[")) return new NestedInteger(Integer.parseInt(s));

        char[] c = s.toCharArray();
        Stack<NestedInteger> stack = new Stack<> ();

        int i = 0;

        while(i < c.length - 1) {
            if(c[i] == '[') {
                NestedInteger ni = new NestedInteger();
                if(stack.empty()) {
                    stack.push(ni);
                }
                else {
                    stack.peek().add(ni);
                    stack.push(ni);
                }
                i += 1;
            } else if(c[i] == ']') {
                stack.pop();
                i += 1;
            } else if(c[i] == ',') {
                i += 1;
                continue;
            } else {
                int sign = 1;
                if(c[i] == '-') {
                    i += 1;
                    sign = -1;
                }

                int value = 0;
                while(i < c.length - 1 && Character.isDigit(c[i])) {
                    value = value * 10 + (c[i] - '0');
                    i += 1;
                }
                stack.peek().add(new NestedInteger(sign * value));
            }
        }

        return stack.pop();
    }    
}
