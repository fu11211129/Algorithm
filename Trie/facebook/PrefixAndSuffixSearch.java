public class PrefixAndSuffixSearch {
    static Node root = null;
    static int re = -1;
    static Map<String, Integer> s2w = null;

    public WordFilter(String[] words) {
        root = new Node();
        s2w = new HashMap<> ();

        for(int i=0; i<words.length; ++i) {
            add(words[i]);
            s2w.put(words[i], i);
        }
    }

    public int f(String prefix, String suffix) {
        Node no = findPrefix(prefix);
        if(no == null) return -1;

        re = -1;
        dfs(no, s2w, suffix);
        return re;
    }

    static void dfs(Node p, Map<String, Integer> s2w, String suf) {
        if(p.word != null) {
            String s = p.word;
            String su = s.substring(s.length() - suf.length());
            if(su.equals(suf) && s2w.get(s) > re) re = s2w.get(s);
            return;
        }

        // have not yet reach the end, continue dfs
        for(int i=0; i<26; ++i) {
            if(p.next[i] != null) dfs(p.next[i], s2w, suf);
        }
    }

    static void add(String w) {
        Node p = root;
        int len = w.length();

        for(int i=0; i<len; ++i) {
            char c = w.charAt(i);
            int id = c - 'a';
            if(p.next[id] == null) p.next[id] = new Node();
            p = p.next[id];
        } p.word = w;
    }

    static Node findPrefix(String w) {
        Node p = root;
        int len = w.length();

        for(int i=0; i<len; ++i) {
            char c = w.charAt(i);
            int id = c - 'a';
            if(p.next[id] == null) return null;
            else p = p.next[id];
        }
        return p;
    }

    static class Node {
        Node[] next = null;
        String word;
        public Node() {
            next = new Node[26];
            word = null;
        }
    }    
}
