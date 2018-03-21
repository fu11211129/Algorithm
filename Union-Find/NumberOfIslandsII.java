public class NumberOfIslandsII {
    private int[] fa = null;
    private int[] rank = null;
    private int[][] dirs = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public List<Integer> numIslands2(int m, int n, int[][] positions) {

        List<Integer> results = new ArrayList<> ();
        int islands = 0;
        Set<Integer> marked = new HashSet<>();
        UnionFind uf = new UnionFind(m * n);

        for(int[] p: positions) {
            islands += 1;
            int x = p[0];
            int y = p[1];
            int id = x*n + y;
            marked.add(id);

            for(int i=0; i<dirs.length; ++i) {
                int nx = x + dirs[i][0];
                int ny = y + dirs[i][1];

                if(nx < 0 || nx >= m || ny < 0 || ny >= n || !marked.contains(nx*n + ny)) continue;
                int nid = nx*n + ny;
                boolean b = uf.union(id, nid);
                if(b) islands -= 1;
            }
            results.add(islands);
        }

        return results;
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
