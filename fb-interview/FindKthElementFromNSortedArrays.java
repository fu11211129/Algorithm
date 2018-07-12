import java.util.*;

public class FindKthElementFromNSortedArrays {

    public static void main(String[] args) {
        int n = 5;
        List<Integer>[] A = new ArrayList[n];
        for(int i = 0; i < n; i += 1) {
            A[i] = new ArrayList<>();
        }
        A[0].addAll(Arrays.asList(1, 2, 3, 4, 5));
        A[1].addAll(Arrays.asList(5, 7, 8, 9, 10));
        A[2].addAll(Arrays.asList(11, 12, 13, 14, 15));
        A[3].addAll(Arrays.asList(16, 17, 18, 19, 20));
        A[4].addAll(Arrays.asList(21, 22, 23, 24, 25));

        int re = find(A, 6);
        System.out.println(re);

        // int[] A = new int[] {1, 2, 3, 4, 5};
        // int[] B = new int[] {5, 5, 8, 9, 10};
        // int re = find(A, 0, B, 0, 6);
        // System.out.println(re);
    }

    static int find(List<Integer>[] A, int k) {
        int minv = A[0].get(0);
        for(int i = 1; i < A.length; i += 1) minv = Math.min(minv, A[i].get(0));

        int maxv = A[0].get(A[0].size() - 1);
        for(int i = 1; i < A.length; i += 1) maxv = Math.max(maxv, A[i].get(A[i].size() - 1));

        int L = minv, R = maxv;
        for(; L < R; ) {
            int mid = (L + R) / 2;
            int cnt = 0; // count # <= "mid" guess

            for(int i = 0; i < A.length; i += 1) {
                // count (#) <= mid for each list
                cnt += bsearch(A[i], mid);
            }

            if(cnt < k) {
                L = mid + 1;
            } else {
                R = mid;
            }
        }

        return L;
    }

    // @ list is sorted
    static int bsearch(List<Integer> list, int v) {
        if(list.size() == 0) return 0;
        if(v < list.get(0)) return 0;
        else if(v >= list.get(list.size() - 1)) return list.size();

        int l = 0, r = list.size() - 1;
        int re = -1;
        while(l <= r) {
            int mid = (l + r) / 2;
            if(list.get(mid) <= v) {
                re = mid;
                l = mid + 1;
            } else if(list.get(mid) > v) {
                r = mid - 1;
            }
        }
        return re + 1;
    }

    static int find(int[] A, int i, int[] B, int j, int k) {
        if(i >= A.length) return B[j + k - 1];
        if(j >= B.length) return A[i + k - 1];

        if(k == 1) return Math.min(A[i], B[j]);

        int mina = (i + k / 2 - 1) < A.length? A[i + k / 2 - 1]: (1 << 31) - 1;
        int minb = (j + k / 2 - 1) < B.length? B[j + k / 2 - 1]: (1 << 31) - 1;

        if(mina < minb) return find(A, i + k / 2, B, j, k - k / 2);
        else return find(A, i, B, j + k / 2, k - k / 2);
    }
}
