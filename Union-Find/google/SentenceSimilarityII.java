public class SentenceSimilarityII {
    public static void main(String[] args) {
        UnionFind uf = new UnionFind();
    }

    public boolean areSentencesSimilarTwo(String[] words1, String[] words2, String[][] pairs) {

        // corner case
        if(words1.length != words2.length) return false;

        UnionFind uf = new UnionFind();
        Map<String, Node> strMap = new HashMap<> ();

        for(String[] pair: pairs) {
            Node na = null, nb = null;
            if(strMap.containsKey(pair[0])) na = strMap.get(pair[0]);
            else {
                na = new Node(pair[0]);
                strMap.put(pair[0], na);
            }

            if(strMap.containsKey(pair[1])) nb = strMap.get(pair[1]);
            else {
                nb = new Node(pair[1]);
                strMap.put(pair[1], nb);
            }

            uf.union(na, nb);
        }

        for(int i=0; i<words1.length; ++i) {
            if(words1[i].equals(words2[i])) continue;

            Node wa = strMap.get(words1[i]);
            Node wb = strMap.get(words2[i]);

            if(wa == null || wb == null) return false;

            if(uf.find(wa) != uf.find(wb)) return false;
        }

        return true;
    }

    static class UnionFind {
        public boolean union(Node x, Node y) {
            Node fx = find(x);
            Node fy = find(y);

            if(fx != fy) {
                if(fx.rank > fy.rank) fy.fa = fx;
                else if(fx.rank < fy.rank) fx.fa = fy;
                else {
                    fy.fa = fx;
                    fx.rank += 1;
                }
                return true;
            }
            return false;
        }

        public Node find(Node x) {
            if(x.fa != x) {
                x.fa = find(x.fa);
            }
            return x.fa;
        }
    }

    static class Node {
        String word;
        Node fa;
        int rank;

        public Node(String s) {
            word = s;
            fa = this;
            rank = 1;
        }
    }
}
