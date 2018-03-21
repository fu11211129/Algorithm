import java.util.*;

public class EncodeStringWithShortestLength {

    public static void main(String[] args) {
        EncodeStringWithShortestLength encoder = new EncodeStringWithShortestLength();
        String s = "abbbabbbcabbbabbbc";
        String result = encoder.encode(s);
        System.out.println(result);

        // result =encoder.encode("2[abbbabbbc]");
        // System.out.println(result);
    }

    public String encode(String s) {
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

                int k = i;
                for(; k<j; ++k) {
                    // find the concatenatin of shortest combined length
                    if(f[i][k].length() + f[k+1][j].length() < f[i][j].length()) {
                        //slen = f[i][k].length() + f[k+1][j].length();
                        System.out.print(String.format("f[%d:%d] = %s  f[%d:%d] = %s\n", i, k, f[i][k], (k+1), j, f[k+1][j]));
                        f[i][j] = f[i][k] + f[k+1][j];
                    }
                }

                String f2 = sf + sf;
                int index = f2.indexOf(sf, 1);
                if(index < sf.length()) {
                    int len = index;
                    String repeatUnit = f[i][i+len-1];
                    int times = sf.length() / len;

                    String cand = Integer.toString(times) + "[" + repeatUnit + "]";
                    if(cand.length() < f[i][j].length()) f[i][j] = cand;
                }

                System.out.println(String.format("f[%d:%d] = %s\n", i, j, f[i][j]));

            }
        }

        return f[0][n-1];
    }
}
