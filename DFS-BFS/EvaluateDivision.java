import java.util.*;

public class EvaluateDivision {

    public static void main(String[] args) {
        String[][] equations = new String[][] {{"a", "b"}, {"b", "c"}};
        double[] values = new double[] {2.0, 3.0};
        String[][] queries = new String[][] {{"a", "c"}, {"b", "a"}, {"a", "e"}, {"a", "a"}, {"x", "x"}};
        //ff
        EvaluateDivision ed = new EvaluateDivision();
        double[] results = ed.calcEquation(equations, values, queries);
        System.out.println(Arrays.toString(results));
    }

    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        int n = equations.length;
        Map<String, Map<String, Double> > mp = new HashMap<> ();
        // build graph
        for(int i=0; i<n; ++i) {
            String ne = equations[i][0];
            String de = equations[i][1];
            if(!mp.containsKey(ne)) mp.put(ne, new HashMap<String, Double> ());
            if(!mp.containsKey(de)) mp.put(de, new HashMap<String, Double> ());

            mp.get(ne).put(de, values[i]);
            mp.get(de).put(ne, 1/values[i]);
        }

        for(String k: mp.keySet()) {
            System.out.print(k + " ");
            Map<String, Double> adjs = mp.get(k);
            for(String ka: adjs.keySet()) {
                System.out.print("(" + ka + ", " + adjs.get(ka) + ") ");
            } System.out.println();
        }

        Set<String> used = new HashSet<> ();
        int nq = queries.length;
        double[] results = new double[nq];
        for(int i=0; i<nq; ++i) {
            used.clear();
            String ne = queries[i][0];
            String de = queries[i][1];
            double rs = query(ne, de, used, mp, 1.0);
            if(rs == 0.0) results[i] = -1.0;
            else results[i] = rs;
        }

        return results;
    }

    public double query(String cur, String end, Set<String> used, Map<String, Map<String, Double> > mp, double value) {
        if(used.contains(cur) || !mp.containsKey(cur)) return 0.0;

        if(cur.equals(end)) return value;

        used.add(cur);
        Map<String, Double> adjs = mp.get(cur);
        double rs = 0.0;
        for(String de: adjs.keySet()) {
            rs = query(de, end, used, mp, value * adjs.get(de));
            if(rs != 0.0) break;
        }
        used.remove(cur);
        return rs;
    }

}
