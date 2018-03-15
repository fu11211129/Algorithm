public class KEmptySlots-BIT {

    public static void main(String[] args) {
        int[] flowers = new int[] {3, 1, 5, 4, 2};
        int result = solution(flowers);

        System.out.println(result);
    }

    static int solution(int[] flowers, int k) {
        int n = flowers.length;

        int[] arr = new int[n + 1];
        FenwickTree ft = new FenwickTree(n);

        for(int day=0; day<n; ++day) {
            arr[day] = 1;
            ft.insert(day, 1);

            if(flower[day] >= k+1 && ft.getSum(flower[day]) == ft.getSum(flower[day-k-1]) return day + 1;

            if(flower[day]+k+1 <=n &&ft.getSum(flower[day+k+1]) == ft.getSum(flower[day]) return day + 1;
        }

        return -1;
    }

    class FenwickTree {
        public int n;
        public int[] sum;

        public FenwickTree(int nn) {
            this.n = nn;
            this.sum = new int[nn + 1];
        }

        private int lowbit(int x) {
            return x & (-x);
        }

        public void insert(int pos, int val) {
            while(pos <= n) {
                sum[pos] += val;
                pos += lowbit(pos);
            }
        }

        // retrieve the sum[1:pos]
        public int getSum(int pos) {
            int result = 0;
            while(pos > 0) {
                result += sum[pos];
                pos -= lowbit(pos);
            }
            return result;
        }
    }
}
