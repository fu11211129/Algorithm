public class FindKthSumOf2SortedArrays {

    // time cost: log(T) * (m + n)
    static int findkth(int[] A, int[] B, int k) {
        int m = A.length;
        int n = B.length;

        int L = A[0] + B[0], R = A[m - 1] + B[n - 1];
        while(L < R) {
            int mid = (L + R) / 2;
            int cnt = 0;
            int i = 0, j = n - 1;

            while(i < m && j >= 0) {
                if(A[i] + B[j] <= mid) {
                    cnt += (j + 1);
                    i += 1;
                } else {
                    j -= 1;
                }
            }

            if(cnt < k) L = mid + 1;
            else R = mid;
        }
        return L;
    }

    public static void main(String[] args) {
        int[] A = new int[] {1, 1, 2};
        int[] B = new int[] {1, 2, 3};
        int k = 5;
        int re = findkth(A, B, k);
        System.out.println(re);
    }
}
