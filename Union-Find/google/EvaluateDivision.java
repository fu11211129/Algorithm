public class EvaluateDivision {
    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        int n = equations.length;
        Map<String, String> fa = new HashMap<> ();
        Map<String, Double> val = new HashMap<> ();
        for(int i=0; i<n; ++i) {
            String na = equations[i][0];
            String nb = equations[i][1];
            double k = values[i];

            if(!fa.containsKey(na)) {
                fa.put(na, na);
                val.put(na, 1.0);
            }

            if(!fa.containsKey(nb)) { // num doesn't have a father node
                fa.put(nb, nb);
                val.put(nb, 1.0);
            }

            String ra = find(na, fa, val);
            String rb = find(nb, fa, val);

            if(ra != rb) {
                // a / ra = x;
                // b / rb = y;
                // a / b = k;
                // ra / rb = (ra/a) *(a/b)*(b/rb) = 1/x * k * y
                fa.put(ra, rb);
                double x = val.get(na);
                double y = val.get(nb);
                val.put(ra, k * (1.0 / x) * y);
            }

        }

        double[] results = new double[queries.length];
        for(int i=0; i<queries.length; ++i) {
            String na = queries[i][0];
            String nb = queries[i][1];

            String ra = find(na, fa, val);
            String rb = find(nb, fa, val);

            if(ra == null || rb == null || !ra.equals(rb)) results[i] = -1.0;
            else {
                results[i] = val.get(na) / val.get(nb);
            }
        }

        return results;
    }

    public String find(String x, Map<String, String> fa, Map<String, Double> val) {
        if(fa.get(x) == null) return null;

        if(fa.get(x) != x) {
            String currFather = fa.get(x);
            String root = find(currFather, fa, val);
            fa.put(x, root);
            double currValue = val.get(x);
            val.put(x, currValue * val.get(currFather));
        }
        return fa.get(x);
    }
}
