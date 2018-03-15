public class CalculatorI {
    public int calculate(String s) {
        if(s == null || s.length() == 0) return 0;
        
        char[] ch = s.toCharArray();
        int le = ch.length, i = 0;
        
        // skip all preceding empty space
        while(i < le && ch[i] == ' ') ++i;
        // if it reaches the end of string, return 0, meaning there is nothing inside the expression.
        if(i == le) return 0;
        
        int first = 0;
        while(i < le && Character.isDigit(ch[i])) first = first * 10 + (ch[i++] - '0');
        // if expression is filled with digits, just return it as the result.
        if(i == le) return first;
        
        // else let it to the first operand.
        // prev is used to track the last operand visited so far.
        int rs = first, prev = first;
        
        while(i < le) {
            
            while(i < le && ch[i] == ' ') ++i;
            if(i == le) break;
            
            int n2 = 0;
            if(ch[i] == '+') {
                while(i < le && !Character.isDigit(ch[i])) ++i;
                while(i < le && Character.isDigit(ch[i]))  n2 = n2 * 10 + (ch[i++] - '0');
                rs += n2;
                prev = n2;
            } else if(ch[i] == '-') {
                while(i < le && !Character.isDigit(ch[i])) ++i;
                while(i < le && Character.isDigit(ch[i]))  n2 = n2 * 10 + (ch[i++] - '0');
                rs -= n2;
                prev = -n2;
            } else if(ch[i] == '*') {
                while(i < le && !Character.isDigit(ch[i])) ++i;
                while(i < le && Character.isDigit(ch[i]))  n2 = n2 * 10 + (ch[i++] - '0');
                rs = rs - prev + prev * n2;
                prev = prev * n2;
            } else if(ch[i] == '/') {
                while(i < le && !Character.isDigit(ch[i])) ++i;
                while(i < le && Character.isDigit(ch[i]))  n2 = n2 * 10 + (ch[i++] - '0');
                rs = rs - prev + prev / n2;
                prev = prev / n2;
            }
            
            //System.out.println(rs);
        }
        
        return rs;
    }
}