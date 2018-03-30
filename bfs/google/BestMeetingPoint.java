public class BestMeetingPoint {
    // suppose p[i] is the perfect point where sum of manhattan distance to all other points is the smallest.
    // suppose p[i] is sorted.
    // sum of manhattan distance (md) = (p[i] - p[0]) + (p[i] - p[1]) + .. + (p[i] - p[i-1]) + (p[i+1] - p[i]) + .. + (p[n-1] - p[i]).
    // sum of md = i * p[i] - sum(p[0:i-1]) - (n-i-1) * p[i] + sum(p[i+1:n-1])
    // since p[i] is sorted, i = n/2 or n/2-1
    // i = n/2, sum of md = p[i] + sum(p[i+2] - p[i-1], p[i+2] - p[i-2], ..., p[n-1] - p[0])

    public int minTotalDistance(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        List<Integer> I = new ArrayList<>(m);
        List<Integer> J = new ArrayList<>(n);

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == 1){
                    I.add(i);
                    J.add(j);
                }
            }
        }

        return getMin(I) + getMin(J);
    }

    private int getMin(List<Integer> list){
        int ret = 0;

        Collections.sort(list);

        int i = 0;
        int j = list.size() - 1;
        while(i < j){
            ret += list.get(j--) - list.get(i++);
        }

        return ret;
    }
}
