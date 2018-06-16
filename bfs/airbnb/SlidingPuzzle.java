public class SlidingPuzzle {

    public int[][] dirs = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int slidingPuzzle(int[][] board) {

        // Queue for bfs
        Queue<String> Q = new LinkedList<> ();
        Set<String> used = new HashSet<> ();
        String start = "";
        for(int i=0; i<2; ++i) {
            for(int j=0; j<3; ++j) start = start + (board[i][j] + "");
        }

        Q.offer(start);
        int step = 0;
        boolean found = false;

        while(Q.size() > 0) {
            //level bfs
            int sz = Q.size();
            used.addAll(Q);

            for(int k=0; k<sz; ++k) {
                String curr = Q.poll();
                //System.out.print(curr + "  ");
                if(curr.equals("123450")) {
                    found = true;
                    break;
                }

                int index = curr.indexOf("0"); // index = r * 3 + c
                int r = index / 3;
                int c = index % 3;

                for(int i=0; i<dirs.length; ++i) {
                    int nr = r + dirs[i][0];
                    int nc = c + dirs[i][1];
                    if(nr >= 0 && nr < 2 && nc >= 0 && nc < 3) {
                        int nidx = nr * 3 + nc;
                        char[] chars = curr.toCharArray();
                        swap(chars, index, nidx);
                        String nstr = new String(chars);
                        if(!used.contains(nstr)) {
                            Q.offer(nstr);
                        }
                    }
                }
            }

            //System.out.println();

            if(found) break;
            step += 1;
        }

        return found? step: -1;
    }

    public void swap(char[] chars, int x, int y) {
        char c = chars[x];
        chars[x] = chars[y];
        chars[y] = c;
    }    
}
