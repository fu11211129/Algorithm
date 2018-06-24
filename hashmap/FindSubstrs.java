public class FindSubstrs {
    static void findSubstrs(char[] s, char[] pat) {
        int[] need = new int[26];
        int[] found = new int[26];
        char[] pat = pattern.toCharArray();
        for(char c: pat) need[c - 'a'] += 1;

        int n = s.length();
        List<Integer> re = new ArrayList<> ();
        int len = 0;
        int sum = 0;
        int l = 0, r = 0;

        for(; r<n; ++r) {
            char c = s[r];
            int v = c - 'a';

            sum = (sum + v) % pat.length;
            mod[sum] += 1;
            found[s[r] - 'a'] += 1;

            if(found[s[r] - 'a'] <= need[s[r] - 'a']) len += 1;

            if(len == pat.length) {
                while(l <= r && (need[s[l] - 'a'] == 0 || found[s[l] - 'a'] > need[s[l] - 'a'])) {
                    if(found[s[l] - 'a'] > need[s[l] - 'a']) found[s[l] - 'a'] -= 1;
                    l += 1;
                }

                if(r-l+1 == pat.length) {
                    re.add(l);
                }
            }
        } // end for loop

        int module = 0;
        for(char c: pat) module = (module + (c - 'a')) % (pat.length);

        for(int i=0; i<n; ++i) {

        }
    }
}
