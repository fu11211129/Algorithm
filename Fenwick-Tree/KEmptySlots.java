import java.util.*;

public class KEmptySlots {
    static int kEmptySlots(int[] flowers, int k) {
        int n = flowers.length;
        if (n == 1 && k == 0) return 1;
        TreeSet<Integer> sort = new TreeSet<>();
        for (int i = 0; i < n; ++i) {
            sort.add(flowers[i]);
            Integer min = sort.lower(flowers[i]);
            Integer max = sort.higher(flowers[i]);
            int mi = min == null ? -1111111 : min;
            int ma = max == null ? -1111111 : max;
            if (valid(flowers[i], k, mi, ma)) return i + 1;
        }
        return -1;
    }

    static int kEmptySlotsIncludeBoudary(int[] flowers, int k) {
        int n = flowers.length;
        if (n == 1 && k == 0) return 1;

        TreeSet<Integer> sort = new TreeSet<>();
        sort.add(0);
        sort.add(n+1);

        for (int i = 0; i < n; ++i) {
            sort.add(flowers[i]);
            Integer min = sort.lower(flowers[i]);
            Integer max = sort.higher(flowers[i]);
            int mi = min == null ? -1111111 : min;
            int ma = max == null ? -1111111 : max;
            if (valid(flowers[i], k, mi, ma)) return i;
        }
        return -1;
    }

    static boolean valid(int x, int k, int min, int max) {
        if (max - x == k + 1) return true;
        if (x - min == k + 1) return true;
        return false;
    }

    public static void main(String[] args) {
        int[] flowers = new int[] {3,1,5,4,2};
        testCase(flowers);

        flowers = new int[] {3,2,4,5,1};
        testCase(flowers);

        flowers = new int[] {6,3,4,2,1,5};
        testCase(flowers);

        flowers = new int[] {2,1};
        testCase(flowers);
    }

    static void testCase(int[] flowers) {
        int n = flowers.length;
        boolean[] vis = new boolean[n];
        for(int i=0; i<n; ++i) {
            System.out.print("day " + (i+1) + " ");
            for(int j=1; j<=n; ++j) {
                if(j == flowers[i] || vis[j-1]) {
                    System.out.print('+');
                    if(!vis[j-1]) vis[j-1] = true;
                }
                else System.out.print('-');
            } System.out.println();
        }

        for(int k=0; k<=n; ++k) {
            System.out.print("k = " + k + ", d = " + KflowersCluster(flowers, k) + "   ");
        } System.out.println("\n=========================");
    }

    static int KflowersCluster(int[] flowers, int k) {
        int n = flowers.length;
        if(n == 0 || k == 0) return -1;
        if(n == 1 && k == 1) return 1;
        if(n == k) return n;
        TreeSet<Integer> sort = new TreeSet<>();
        sort.add(0);
        sort.add(n+1);

        for(int i=n-1; i>=0; --i) {
            sort.add(flowers[i]);
            Integer min = sort.lower(flowers[i]);
            Integer max = sort.higher(flowers[i]);
            int mi = min == null ? -1111111 : min;
            int ma = max == null ? -1111111 : max;
            if (valid(flowers[i], k, mi, ma)) return i;
        }

        return -1;
    }
}
