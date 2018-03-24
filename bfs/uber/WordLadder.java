public class WordLadder {

    public static void main(String[] args) {

    }

    public void bfs(String beginWord, String endWord, Set<String> wordList) {
        Queue<String> Q = new LinkedList<> ();
        Set<String> used = new HashSet<> ();
        Map<String, Set<String> > paths = new HashMap<> ();
        Q.offer(beginWord);

        boolean found = false;

        while(Q.isEmpty()) {

            used.addAll(Q);
            int size = Q.size();

            for(int k=0; k<size; ++k) {
                String word = Q.poll();
                char[] cw = word.toCharArray();

                for(int i=0; i<cw.length; ++i) {
                    for(char c='a', c<='z'; ++c) {
                        if(cw[i] != c) {
                            char bkup = cw[i];
                            cw[i] = c;
                            String newStr = new String(cw);
                            if(!used.contains(newStr) && wordList.contains(newStr)) {
                                if(newStr.equals(endWord)) found = true;
                                Q.offer(newStr);
                                paths.putIfAbsent(newStr, new HashSet<String> ());
                                paths.get(newStr).add(word);
                            }
                            cw[i] = bkup;
                        }
                    }
                }

            }

            if(found) break; //if endWord found, terminate the level bfs
        } // end of while

        if(found) {
            LinkedList<String> load = new LinkedList<String> ();
            load.addLast(endWord);
            dfs(endWord, beginWord, load, paths);
        }
    }
}
