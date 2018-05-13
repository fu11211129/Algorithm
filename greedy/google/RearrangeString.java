import java.util.*;

public class RearrangeString {

    public static void main(String[] args) {
        RearrangeString sol = new RearrangeString();
        String re = sol.rearrangeString("aa", 2);
        System.out.println(re);
    }

    public String rearrangeString(String s, int k) {
        if(s.length() <= 1) return s;
        if(k <= 1) return s;

        char[] chars = s.toCharArray();
        int[] counts = new int[26];
        Integer[] index = new Integer[26];
        int distincts = 0;
        int maxFreq = 0;
        PriorityQueue<Element> pq = new PriorityQueue<> ((a, b) -> (a.freq == b.freq? a.c - b.c: b.freq - a.freq));

        for(char c: chars) counts[c - 'a'] += 1;
        for(char c='z'; c>='a'; --c) {
            if(counts[c - 'a'] > 0) {
                distincts += 1;
                maxFreq = Math.max(maxFreq, counts[c - 'a']);
                pq.offer(new Element(c, counts[c - 'a']));
            }
        }

        // if(maxFreq > (chars.length + 1) / 2) return "";

        StringBuilder re = new StringBuilder();

        while(!pq.isEmpty()) {
            Element first = pq.poll();
            Element second = null;
            re.append(first.c);
            if(index[first.c - 'a'] != null) {
                int prev = index[first.c - 'a'];
                int curr = re.length() - 1;
                System.out.println(curr - prev);
                if(curr - prev < k) return "";
            }
            index[first.c - 'a'] = re.length() - 1;
            first.freq -= 1;

            if(!pq.isEmpty()) {
                second = pq.poll();
                re.append(second.c);
                if(index[second.c - 'a'] != null) {
                    int prev = index[second.c - 'a'];
                    int curr = re.length() - 1;
                    System.out.println(curr - prev);
                    if(curr - prev < k) return "";
                }
                index[second.c - 'a'] = re.length() - 1;
                second.freq -= 1;
            }

            if(first.freq > 0) pq.offer(first);
            if(second != null && second.freq > 0) pq.offer(second);
        }


        return re.toString();
    }

    class Element {
        char c;
        int freq;

        public Element(char cc, int f) {
            c = cc;
            freq = f;
        }
    }
}
