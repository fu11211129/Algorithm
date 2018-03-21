public class FindCelebrity {
    public int findCelebrity(int n) {
        int l = 0, r = n-1;
        while(l < r) {
            if(knows(l, r)) l += 1; // because the celebrity doesn't know anyone
            else r -= 1; // if r is not known for l, then r is not the celebrity.
        }

        boolean b = check(l, n);

        return b? l: -1;
    }

    private boolean check(int c, int n) {

        for(int i=0; i<n; ++i) {
            if(i == c) continue;
            if(knows(c, i)) return false;
        }

        int times = 0;
        for(int i=0; i<n; ++i) {
            if(i == c) continue;
            if(knows(i, c)) times += 1;
        }
        return times == n-1;
    }    
}
