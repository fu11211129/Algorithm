public class WallsAndGates {

    public int[][] dirs = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public void wallsAndGates(int[][] rooms) {

        if(rooms.length == 0) return;

        int mx = (1 << 31) - 1;
        Queue<Integer> Q = new LinkedList<> ();
        int m = rooms.length;
        int n = rooms[0].length;

        for(int i = 0; i < m; i += 1) {
            for(int j = 0; j < n; j += 1) {
                if(rooms[i][j] == 0) {
                    Q.offer(i * n + j);
                }
            }
        }

        int level = 1;
        while(Q.size() > 0) {
            int sz = Q.size();
            for(int i=0; i<sz; ++i) {
                int p = Q.poll();
                int y = p % n;
                int x = p / n;

                for(int[] d: dirs) {
                    int nx = x + d[0], ny = y + d[1];
                    if(nx >= 0 && nx < m && ny >= 0 && ny < n && rooms[nx][ny] == mx) {
                        rooms[nx][ny] = level;
                        Q.offer(nx * n + ny);
                    }
                }
            }
            level += 1;
        }

    }    
}
