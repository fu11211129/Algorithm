public class RedundantConnectionII {
    public int[] findRedundantDirectedConnection(int[][] edges) {
        Set<Integer> set = new HashSet<> ();
        Map<Integer, Integer> parent = new HashMap<> ();
        int[] redundant1 = null, redundant2 = null;
        for(int[] e: edges) {
            int from = e[0], to = e[1];
            set.add(from);
            set.add(to);

            // if two parents node is detected.
            if(parent.get(to) != null) {
                // mark these two edges as suspected redundant edges
                redundant1 = new int[] {parent.get(to), to};
                redundant2 = new int[] {from, to};

                // remove the later edge
                e[0] = e[1] = -1;
            }
            parent.put(to, from);
        }
        int N = set.size();
        UnionFind uf = new UnionFind(N);

        for(int[] e: edges) {
            if(e[0] == -1) continue; // skip the deleted edge.
            boolean b = uf.union(e[0] - 1, e[1] - 1);
            if(!b) {
                if(redundant1 != null) return redundant1;
                else return e;
            }
        }

        return redundant2;
    }

    class UnionFind {
        public int N;
        public int[] fa;
        public int[] rank;

        public UnionFind(int nn) {
            N = nn;
            fa = new int[N];
            rank = new int[N];
            for(int i=0; i<N; ++i) fa[i] = i;
            for(int i=0; i<N; ++i) rank[i] = 1;
        }

        public boolean union(int x, int y) {
            int fx = find(x);
            int fy = find(y);

            if(fx != fy) {
                if(rank[fx] > rank[fy]) fa[fy] = fx;
                else if(rank[fx] < rank[fy]) fa[fx] = fy;
                else {
                    fa[fy] = fx;
                    rank[fx] += 1;
                }
                return true;
            }
            return false;
        }

        public int find(int x) {
            if(x != fa[x]) {
                fa[x] = find(fa[x]);
            }
            return fa[x];
        }
    }    
}
