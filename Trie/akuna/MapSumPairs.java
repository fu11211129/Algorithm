public class MapSumPairs {
    private Trie trie;

    /** Initialize your data structure here. */
    public MapSumPairs() {
        trie = new Trie();
    }

    public void insert(String key, int val) {
        trie.insert(key, val);
    }

    public int sum(String prefix) {
        return trie.queryByPrefix(prefix);
    }

    class Trie {
        private TrieNode root;
        private Set<String> dict;

        public Trie() {
            root = new TrieNode();
            dict = new HashSet<> ();
        }

        public void insert(String word, int val) {
            TrieNode p = root;
            for(int i=0; i<word.length(); ++i) {
                char c = word.charAt(i);
                int off = c - 'a';
                if(p.next[off] == null) p.next[off] = new TrieNode();
                p = p.next[off];
                if(dict.contains(word)) p.update(val);
                else p.increase(val);
            }
            dict.add(word);
        }

        public int queryByPrefix(String pre) {
            TrieNode p = root;
            for(int i=0; i<pre.length(); ++i) {
                char c = pre.charAt(i);
                int off = c - 'a';
                if(p.next[off] == null) return 0;
                p = p.next[off];
            }
            return p.sum;
        }
    }

    class TrieNode {
        public TrieNode[] next;
        public int sum;

        public TrieNode(int s) {
            sum = s;
            next = new TrieNode[26];
            for(int i=0; i<26; ++i) next[i] = null;
        }

        public TrieNode() {
            sum = 0;
            next = new TrieNode[26];
            for(int i=0; i<26; ++i) next[i] = null;
        }

        public void increase(int v) {
            sum += v;
        }

        public void update(int v) {
            sum = v;
        }
    }
}
