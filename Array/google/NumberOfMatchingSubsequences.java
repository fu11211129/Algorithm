public class NumberOfMatchingSubsequences {
    private boolean isSubseq(String w, String S) {
        if(w.length() == 0) return true;

        int i = 0, j = 0;
        for(; i<S.length(); ++i) {
            if(S.charAt(i) == w.charAt(j)) j += 1;
            if(j == w.length()) break;
        }
        return j == w.length();
    }

    public int numMatchingSubseq(String S, String[] words) {
        int results = 0;
        Set<String> subseq = new HashSet<> ();
        Set<String> notSubseq = new HashSet<> ();

        for(String w: words) {
            if(subseq.contains(w)) results += 1;
            else if(notSubseq.contains(w)) continue;
            else {
                if(isSubseq(w, S)) {
                    results += 1;
                    subseq.add(w);
                } else {
                    notSubseq.add(w);
                }
            }
        }
        return results;
    }    
}
