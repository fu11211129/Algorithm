public class BestTimeSellAndBuyStock {
    // Best Time to Buy and Sell Stock I
    public static int oneTransaction(int[] stockPrice) {
        return 0;
    }

    // Best Time to Buy and Sell Stock II
    public static int ktransactions(int[] stockPrice) {
        return 0;
    }

    // Best Time to Buy and Sell Stock III
    // max sum 2 subarrays
    public static int twoTransaction(int[] stockPrice) {
        return 0;
    }

    // Best Time to Buy and Sell Stock IV
    // max sum k subarrays
    public static int ktransactions(int[] stockPrice, int k) {
        int n = stockPrice.length;
        if(n <= 1) return 0;

        int[] dif = new int[n - 1];
        for(int i=0; i<n-1; ++i) dif[i] = stockPrice[i+1] - stockPrice[i];
        n -= 1;
        int[][] local = new int[n + 1][k + 1];
        int[][] global = new int[n + 1][k + 1];

        for(int i=1; i<=n; ++i) {
            for(int j=1; j<=k; ++j) {
                local[i][j] = Math.max(local[i-1][j] + dif[i-1], global[i-1][j-1] + dif[i-1]);
                global[i][j] = Math.max(global[i-1][j], local[i][j]);
            }
        }

        return global[n][k];
    }

    public static void main(String[] args) {
        int[] stockPrice = new int[] {7, 4, 10, 1, 3, 5, 6, 9, 2};
        int maxProfit = ktransactions(stockPrice, 3);
        System.out.println(maxProfit);
    }
}
