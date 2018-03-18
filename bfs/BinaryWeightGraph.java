import java.util.*;

public class BinaryWeightGraph {

    static void shortestPath(int src, Map<Integer, List<Integer> > map, int n) {
        int[] dist = new int[n];
        int[] prev = new int[n];
        dist[src] = 0;
        prev[src] = src;

        Deque<Integer> dq = new ArrayDeque<> ();
        Set<Integer> visited = new HashSet<> ();
        dq.addLast(src);

        while(!dq.isEmpty()) {
            int size = dq.size();
            visited.addAll(dq);

            while(size > 0) {
                size -= 1;
                int x = dq.pollFirst();

                if(!map.containsKey(x)) continue;

                for(int nx: map.get(x)) {
                    if(visited.contains(nx)) continue;

                    prev[nx] = x;
                    dist[nx] = dist[x] + 1;
                    dq.addLast(nx);
                    //visited.add(nx);
                }
            }
        }

        int target = 4;
        System.out.println("shortest distance is: " + dist[target]);

        int v = target;
        System.out.print("shortest path is: ");
        printPath(v, prev, src);
        System.out.println();
    }

    static void printPath(int v, int[] prev, int src) {
        if(v == src) {
            System.out.print(v + " => ");
            return;
        }

        printPath(prev[v], prev, src);
        System.out.print(v + " => ");
    }

    public static void main(String[] args) {
        int n = 9;
        Map<Integer, List<Integer> > map = new HashMap<> ();
        map.put(0, new ArrayList<>(Arrays.asList(7)));
        map.put(1, new ArrayList<>(Arrays.asList(2, 7)));
        map.put(2, new ArrayList<>(Arrays.asList(1, 8)));
        map.put(3, new ArrayList<>(Arrays.asList(4, 5)));
        map.put(4, new ArrayList<>(Arrays.asList(3, 5)));
        map.put(5, new ArrayList<>(Arrays.asList(3, 4, 6)));
        map.put(6, new ArrayList<>(Arrays.asList(5, 7)));
        map.put(7, new ArrayList<>(Arrays.asList(0, 1, 6, 8)));
        map.put(8, new ArrayList<>(Arrays.asList(2, 7)));

        shortestPath(0, map, n);



        // 0 - 7 - 1
        //     |   |
        //     6    2
    }
}
