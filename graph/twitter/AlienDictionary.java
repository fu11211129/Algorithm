public class Alien Dictionary {
    public String alienOrder(String[] words) {
        int n = words.length;
        StringBuilder sb = new StringBuilder();
        Set<Character> set = new HashSet<> ();
        Map<Character, Set<Character> > map = new HashMap<> ();

        for(String w: words) {
            char[] cw = w.toCharArray();
            for(char c: cw) set.add(c);
            // set.addAll(Arrays.asList(w.toCharArray()));
        }

        for(int i=0; i<n-1; ++i) {
            char[] pair = alienOrderUtil(words[i], words[i+1]);
            if(pair != null) {
                map.putIfAbsent(pair[0], new HashSet<> ());
                map.get(pair[0]).add(pair[1]);
            }
        }

        String topoSorted = topoSort(map, set);
        System.out.println(topoSorted);
        if(topoSorted.length() != set.size()) return "";
        else return topoSorted;
    }

    public String topoSort(Map<Character, Set<Character> > map, Set<Character> set) {
        int[] d = new int[26];
        Queue<Integer> Q = new LinkedList<> ();
        StringBuilder sb = new StringBuilder();

        for(Set<Character> descs: map.values()) {
            for(Character desc: descs) {
                d[desc - 'a'] += 1;
            }
        }

        for(int i=0; i<26; ++i) {
            if(d[i] == 0 && set.contains((char) (i+'a')) ) Q.offer(i);
        }

        while(!Q.isEmpty()) {
            int x = Q.poll();
            char c = (char)(x + 'a');
            sb.append(c);

            Set<Character> adjs = map.get(c);
            if(adjs == null) continue;

            for(char cx: adjs) {
                d[cx - 'a'] -= 1;
                if(d[cx - 'a'] == 0) Q.offer(cx - 'a');
            }
        }

        return sb.toString();
    }

    public char[] alienOrderUtil(String w1, String w2) {
        char[] pair = null;
        int i=0;
        for(; i<w1.length() && i<w2.length(); ++i) {
            char c1 = w1.charAt(i);
            char c2 = w2.charAt(i);
            if(c1 != c2) {
                pair = new char[] {c1, c2};
                break;
            }
        }
        return pair;
    }    
}
