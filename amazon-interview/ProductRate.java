import java.util.*;

public class ProductRate {
    static Map<Integer, Double> helper(List<Result> rates, int k) {
        Map<Integer, PriorityQueue<Double> > map = new HashMap<> ();
        Map<Integer, Double> re = new HashMap<> ();

        for(Result res: rates) {
            int id = res.id;
            double rt = res.rate;
            map.putIfAbsent(id, new PriorityQueue<Double> ());
            map.get(id).offer(rt);
            if(map.get(id).size() > k) {
                map.get(id).poll();
            }
        }

        for(int id: map.keySet()) {
            PriorityQueue<Double> scores = map.get(id);
            int sz = scores.size();
            double tot = 0.0;
            for(double score: scores) tot += score;

            re.put(id, tot / (sz * 1.0));
        }

        return re;
    }

    public static void main(String[] args) {
        List<Result> rates = new ArrayList<> ();
        rates.add(new Result(1, 10.0));
    }

    static class Result {
        int id;
        double rate;

        public Result(int uid, double r) {
            id = uid;
            rate = r;
        }
    }
}
