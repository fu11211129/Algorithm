public class RegularExpressionMatching {
    public boolean isMatch(String s, String p) {
        char[] cs = s.toCharArray();
        char[] cp = p.toCharArray();

        int ns = cs.length, np = cp.length;
        // if(ns == 0) return np == 0;
        // if(np == 0) return ns == 0;

        boolean[][] dp = new boolean[ns + 1][np + 1];
        dp[0][0] = true;
        /** i.e p = "a*b*c*d*" matches s = ""  */
        for(int i=2; i<=np; ++i) dp[0][i] |= dp[0][i-2] && cp[i-1] == '*';

        for(int i=1; i<=ns; ++i) {
            for(int j=1; j<=np; ++j) {
                if(cp[j-1] != '*') {
                    dp[i][j] |= dp[i-1][j-1] && (cs[i-1] == cp[j-1] || cp[j-1] == '.');
                } else {
                    if(cp[j-2] == cs[i-1] || cp[j-2] == '.') dp[i][j] = dp[i-1][j] || dp[i][j-2] || dp[i-1][j-2];
                    else dp[i][j] = dp[i][j-2];
                }
            }
        }

        return dp[ns][np];
    }    
}
