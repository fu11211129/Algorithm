public class RedundantConnection {
    public int[] findRedundantConnection(int[][] edges) {
        int N = 0;
        Set<Integer> set = new HashSet<> ();
        for(int[] edge: edges) {
            int from = edge[0];
            int to = edge[1];
            set.add(from);
            set.add(to);
        }
        N = set.size(); // number of vertex in graph.

        UnionFind uf = new UnionFind(N);

        for(int i=0; i<=edges.length-1; ++i) {
            int from = edges[i][0];
            int to = edges[i][1];
            boolean b = uf.union(from - 1, to - 1);
            if(!b) return new int[] {from, to};
        }

        return new int[] {};
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
