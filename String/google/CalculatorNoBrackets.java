public class CalculatorNoBrackets {

    public int i;

    public int calc(String s) {
        i = 0;
        int n = s.length();
        int v = 0;
        int update = 0;
        char[] chars = s.toCharArray();

        while(i < n) {
            while(i < n && chars[i] == ' ') i += 1;
            if(chars[i] == '+') {
                i += 1;
                int x = 0;
                while(i < n && chars[i] >= '0' && chars[i] <= '9') x = x * 10 + (chars[i++] - '0');
                v += x;
                update = x;
            } else if(chars[i] == '-') {
                i += 1;
                int x = 0;
                while(i < n && chars[i] >= '0' && chars[i] <= '9') x = x * 10 + (chars[i++] - '0');
                v -= x;
                update = 0 - x;
            } else if(chars[i] == '*') {
                i += 1;
                int x = 0;
                while(i < n && chars[i] >= '0' && chars[i] <= '9') x = x * 10 + (chars[i++] - '0');
                v = v - update + update * x;
                update = update * x;
            } else {
                i += 1;
                int x = 0 ;
                while(i < n && chars[i] >= '0' && chars[i] <= '9') x = x * 10 + (chars[i++] - '0');
                v = v - update + update / x;
                update = update / x;
            }
        }
        return v;
    }
}
