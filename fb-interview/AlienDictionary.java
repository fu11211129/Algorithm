import java.util.*;

public class AlienDictionary {

    static String findOrderWithSortedWordList(String[] words) {
        int n = words.length;
        if(n == 0) return "";
        else if(n == 1) {
            char[] c = words[0].toCharArray();
            Arrays.sort(c);
            return new String(c);
        }

        List<Integer>[] g = new ArrayList[26];
        for(int i = 0 ; i < 26; i += 1) g[i] = new ArrayList<> ();
        int[] ind = new int[26];
        Arrays.fill(ind, 0); // -1 denotes this node is not in the graph
        boolean[] used = new boolean[26];
        int size = 0;
        Queue<Integer> Q = new LinkedList<> ();
        String re = "";

        for(int i = 0; i < n - 1; i += 1) {
            String w1 = words[i];
            for(char c: w1.toCharArray()) used[c - 'a'] = true;
            String w2 = words[i + 1];
            for(char c: w2.toCharArray()) used[c - 'a'] = true;

            int idx = 0;
            while(idx < w1.length() && idx < w2.length() && w1.charAt(idx) == w2.charAt(idx)) idx += 1;
            if(idx == w1.length() || idx == w2.length()) continue;

            int from = w1.charAt(idx) - 'a';
            int to = w2.charAt(idx) - 'a';
            g[from].add(to);
        }

        for(int i = 0; i < 26; i += 1) {
            for(int ni: g[i]) {
                ind[ni] += 1;
            }
        }

        for(int i = 0; i < 26; i += 1) {
            if(used[i]) {
                size += 1;
                if(ind[i] == 0) {
                    Q.offer(i);
                    //System.out.print((char)(i + 'a') + " ");
                }
                //System.out.print((char)(i + 'a') + " ");
            }
        }

        while(Q.size() > 0) {
            int i = Q.poll();
            re = re + ((char)(i + 'a'));

            for(int ni: g[i]) {
                ind[ni] -= 1;
                if(ind[ni] == 0) Q.offer(ni);
            }
        }

        if(re.length() != size) return "";
        return re;
    }

    static void sortWordListWithAlienOrder(String[] words, int[] order) {
        qsort(words, 0, words.length - 1, order);

        for(String s: words) {
            System.out.println(s + " ");
        }
    }

    static void qsort(String[] words, int l, int r, int[] ord) {
        if(l <= r) {
            int idx = partition(words, l, r, ord);
            qsort(words, l, idx - 1, ord);
            qsort(words, idx + 1, r, ord);
        }
    }

    static int partition(String[] words, int l, int r, int[] ord) {
        int wall = l;
        String pivot = words[r];

        for(int i = l; i <= r - 1; i += 1) {
            if(compare(words[i], pivot, ord) < 0) {
                swap(words, i, wall);
                wall += 1;
            }
        }
        swap(words, wall, r);
        return wall;
    }

    static void swap(String[] words, int i, int j) {
        String temp = words[i];
        words[i] = words[j];
        words[j] = temp;
    }

    static int compare(String a, String b, int[] ord) {
        int i = 0;
        int m = a.length();
        int n = b.length();
        while(i < Math.min(m, n)) {
            if(a.charAt(i) == b.charAt(i)) i += 1;
            else break;
        }
        if(i == m) return -1;
        if(i == n) return 1;
        if(ord[a.charAt(i)] == -1 || ord[b.charAt(i)] == -1) {
            return a.charAt(i) - b.charAt(i);
        } else {
            return ord[a.charAt(i)] - ord[b.charAt(i)];
        }
    }

    public static void main(String[] args) {
        String[] words = new String[] {"rftt", "ett", "er", "abc", "wrf", "wrt"};
        int[] order = new int[256];
        Arrays.fill(order, -1);
        order['w'] = 0;
        order['e'] = 1;
        order['r'] = 2;
        order['t'] = 3;
        order['f'] = 4;
        //char[] order = new char[] {'w', 'e', 'r', 't', 'f'};
        sortWordListWithAlienOrder(words, order);
    }

    static class Node {
        String str;

        public Node(String s) {
            str = s;
        }
    }
}
