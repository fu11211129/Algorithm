public class Typeahead {
    public Set<String> findTop100Words(String prefix) {

    }

    class TrieNode {
        TrieNode[] next;
        Map<String, Integer> word2freq;

        public TrieNode() {
            next = new int[26];
            word2freq = new HashMap<> ();
        }

        public List<String> findTopKWords(int k) {
            if(word2freq.size() <= k) {
                return new ArrayList<String>(word2freq.keySet());
            } else {
                WordFreq[] wf = new WordFreq[word2freq.size];
                int e = 0;
                for(String w: word2freq.keySet()) {
                    wf[e++] = new WordFreq(w, word2freq.get(s));
                }
                Arrays.sort(wf, new Comparator<WordFreq> () {
                    public int compare(WordFreq a, WordFreq b) {
                        if(a.freq == b.freq) return a < b;
                        return b.freq - a.freq;
                    }
                });

                List<String> results = new ArrayList<> ();
                for(int i=0; i<k; ++i) results.add(wf[i].word);
                return results;
            }
        }
    }

    class WordFreq {
        String word;
        int freq;

        public WordFreq(String w, int f) {
            word = w;
            freq = f;
        }
    }
}
