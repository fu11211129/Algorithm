public class MinimumFactorization {
    public int smallestFactorization(int a) {
        if(a < 10) return a;

        List<Integer> divs = new ArrayList<> ();
        for(int div=9; div>=2; --div) {
            while(a >= div && a % div == 0) {
                divs.add(div);
                a /= div;
            }
        }

        if(a != 1) return 0;

        long result = 0;
        for(int i=divs.size()-1; i>=0; --i) {
            result = result * 10 + divs.get(i);
            if(result > Integer.MAX_VALUE) return 0;
        }

        return (int)result;
    }
}
