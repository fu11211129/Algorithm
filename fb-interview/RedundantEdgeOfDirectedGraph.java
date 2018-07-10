public class RedundantEdgeOfDirectedGraph {

    public int[] findRedundantDirectedConnection(int[][] edges) {

        int N = edges.length; // number of vertex
        if(N <= 1) return new int[] {0, 0}; // corner case

        UnionFind uf = new UnionFind(N + 1);
        Set<Integer>[] g = new HashSet[N + 1]; // map
        for(int i = 1; i <= N; i += 1) g[i] = new HashSet<> ();
        Map<Integer, Integer> fa = new HashMap<> ();
        // stores two suspected redundant edges where two edges point to the same vertex.
        List<int[]> suspects = new ArrayList<> ();

        for(int i = 0; i < N; i += 1) {
            int u = edges[i][0], v = edges[i][1];
            g[u].add(v);
            if(fa.containsKey(v)) {
                int pu = fa.get(v);
                // first put the later edge as the 1st suspect.
                suspects.add(new int[] {u, v});
                suspects.add(new int[] {pu, v});
            }
            fa.put(v, u);
        }

        for(int[] suspect: suspects) {
            // remove this edge
            int u = suspect[0], v = suspect[1];
            g[u].remove(v);

            int[] ind = new int[N + 1]; // indegree of each vertex
            for(int i = 1; i <= N; i += 1) {
                if(g[i].size() > 0) {
                    for(int j: g[i]) {
                        ind[j] += 1;
                    }
                }
            }

            Queue<Integer> q = new LinkedList<> ();
            for(int i = 1; i <= N; i += 1) {
                if(ind[i] == 0) {
                    q.offer(i);
                }
            }

            if(q.size() == 1) { // for a tree, only the root has 0 indegree, other has only 1 indegree.
                Set<Integer> seen = new HashSet<> ();
                seen.add(q.peek());
                boolean tree = true;

                while(q.size() > 0) {
                    int ux = q.poll();
                    for(int vx: g[ux]) {
                        if(seen.contains(vx)) { // if a cycle detected.
                            tree = false;
                            break;
                        } else {
                            seen.add(vx);
                            q.offer(vx);
                        }
                    }
                }

                if(tree && seen.size() == N) return suspect;
            }

            g[u].add(v); // add this removed edge back to graph.
        }

        // in case "2 parent" edges detected or none of them are redundant edges
        // find the edge that forms the cycle
        for(int i = 0; i < N; i += 1) {
            int u = edges[i][0], v = edges[i][1];
            boolean b = uf.union(u, v);
            if(!b) return new int[] {u, v};
        }

        return new int[] {0, 0};
    }    

    private class UnionFind {
        int N;
        int[] rank;
        int[] fa;
        int clusters;

        public UnionFind(int n) {
            N = n;
            rank = new int[n];
            fa = new int[n];
            for(int i = 0; i < n; i += 1) fa[i] = i;
            clusters = n;
        }

        public boolean union(int x, int y) {
            int rx = find(x), ry = find(y);
            if(rx != ry) {
                if(rank[rx] == rank[ry]) {
                    fa[rx] = ry;
                    rank[ry] += 1;
                } else if(rank[rx] < rank[ry]) {
                    fa[rx] = ry;
                } else {
                    fa[ry] = rx;
                }
                clusters -= 1;
                return true;
            } else {
                return false;
            }
        }

        public int find(int x) {
            if(fa[x] != x) {
                fa[x] = find(fa[x]);
            }
            return fa[x];
        }
    }
}
