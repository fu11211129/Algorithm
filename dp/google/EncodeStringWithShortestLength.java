import java.util.*;

public class EncodeStringWithShortestLength {

    public static void main(String[] args) {
        // EncodeStringWithShortestLength encoder = new EncodeStringWithShortestLength();
        String s = "abbbabbbcabbbabbbc";
        String encodedStr = encode(s);
        System.out.println(encodedStr);

        Counter c = new Counter(0);
        String decodedStr = decode(encodedStr, c);
        System.out.println(decodedStr);
        System.out.println(decodedStr.equals(s)? "success": "failed");

    }

    public static String encode(String s) {
        int n = s.length();
        String[][] f = new String[n][n];
        for(int i=0; i<n; ++i) {
            Arrays.fill(f[i], "");
        }

        for(int i=0; i<n; ++i) f[i][i] = ""+ s.charAt(i);

        for(int level=2; level<=n; ++level) {
            for(int i=0; i<=n-level; ++i) {

                int j = i+level-1;
                String sf = s.substring(i,j+1);
                f[i][j] = sf;

                for(int k = i; k<j; ++k) {
                    // find the concatenatin of shortest combined length
                    if(f[i][k].length() + f[k+1][j].length() < f[i][j].length()) {
                        // System.out.print(String.format("f[%d:%d] = %s  f[%d:%d] = %s\n", i, k, f[i][k], (k+1), j, f[k+1][j]));
                        f[i][j] = f[i][k] + f[k+1][j];
                    }
                }

                String f2 = sf + sf;
                int index = f2.indexOf(sf, 1);
                if(index < sf.length()) {
                    int len = index;
                    // very important, not "String repeatUnit = s.substring(i, i+len)"
                    // because in previous steps (line 31-36), s.substring(i, i+len) has been encoded with shortest length
                    // so "repeatUnit = f[i][i+len-1]""
                    String repeatUnit = f[i][i+len-1];
                    int times = sf.length() / len;

                    String cand = "[" + repeatUnit + "]" + Integer.toString(times);
                    if(cand.length() < f[i][j].length()) f[i][j] = cand;
                }

                // System.out.println(String.format("f[%d:%d] = %s\n", i, j, f[i][j]));

            }
        }

        return f[0][n-1];
    }

    public static String decode(String s, Counter c) {
        StringBuilder sb = new StringBuilder();

        while(c.idx < s.length()) {
            if(s.charAt(c.idx) == '[') {
                c.idx += 1;
                String sub = decode(s, c);
                int repeatedTimes = getRepeatedTimes(s, c);
                if(repeatedTimes == 0) sb.append(sub);
                else {
                    for(int k=0; k<repeatedTimes; ++k) sb.append(sub);
                }

            } else if(s.charAt(c.idx) == ']') {
                c.idx += 1;
                return sb.toString();

            } else {
                sb.append(s.charAt(c.idx));
                c.idx += 1;
            }
        }

        return sb.toString();
    }

    public static int getRepeatedTimes(String s, Counter c) {
        int times = 0;
        while(c.idx < s.length() && Character.isDigit(s.charAt(c.idx))) {
            times = times * 10 + (s.charAt(c.idx) - '0');
            c.idx += 1;
        }
        return times;
    }

    static class Counter {
        int idx;

        public Counter(int v) {
            idx = v;
        }
    }
}
