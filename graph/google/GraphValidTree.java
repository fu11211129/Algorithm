public class GraphValidTree {
    public boolean validTree(int n, int[][] edges) {
        int m = edges.length;
        UnionFind uf = new UnionFind(n);

        for(int[] edge: edges) {
            boolean b = uf.union(edge[0], edge[1]);
            if(!b) return false;
        }

        for(int i=0; i<n; ++i) {
            uf.fa[i] = uf.find(i);
        }

        Integer root = uf.fa[0];
        for(int i=1; i<n; ++i) {
            if(uf.fa[i] != root) return false;
        }

        return true;
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
