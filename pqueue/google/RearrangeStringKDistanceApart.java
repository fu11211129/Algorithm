import java.util.*;

public class RearrangeStringKDistanceApart {

    public static void main(String[] args) {
        String s = "aabbcc";
        k = 3;
        RearrangeStringKDistanceApart sol = new RearrangeStringKDistanceApart();

        String re = sol.rearrangeString(s, k);
        System.out.println(re);
    }

    public String rearrangeString(String s, int k) {
        if(k == 0) return s;

        Map<Character, Integer> freq = new HashMap<>();
        for(char c : s.toCharArray()) freq.put(c, freq.getOrDefault(c, 0) + 1);
        Queue<Item> q = new PriorityQueue<>(new Comp());
        for(char c : freq.keySet()) q.offer(new Item(c, freq.get(c)));

        StringBuilder sb = new StringBuilder();
        int indx = 0;

        while(!q.isEmpty()){
            int n = k;
            Queue<Item> saved = new LinkedList<>();
            while(n > 0 && !q.isEmpty()){
                Item cur = q.poll();
                if(cur.lastIndex == -1) cur.lastIndex = indx;
                else if (indx - cur.lastIndex < n) return "";
                else cur.lastIndex = indx;
                indx++;
                cur.count--;
                sb.append(cur.key);
                --n;
                saved.offer(cur);
            }

            while(!saved.isEmpty()){
                Item cur = saved.poll();
                if(cur.count > 0) q.offer(cur);
            }

        }
        return sb.toString();
    }

    private class Item{
        public char key;
        public int count;
        public int lastIndex;
        public Item(char key, int count){this.key = key; this.count = count; this.lastIndex = -1;}
    }

    private class Comp implements Comparator<Item>{
        @Override
        public int compare(Item a, Item b){
            if(a.count != b.count) return b.count - a.count;
            return a.key - b.key;
        }
    }
}
