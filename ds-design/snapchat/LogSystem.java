import java.util.*;

public class LogSystem {

    private TreeMap<String, List<Integer>> logs = null;
    private Map<String, Integer> gra2idx = null;
    private String[] earliestDate = "2000:01:01:00:00:00".split("\\:");
    private String[] latestDate = "2017:12:31:23:59:59".split("\\:");

    public LogSystem() {
        logs = new TreeMap<> ();
        gra2idx = new HashMap<> ();

        gra2idx.put("Year", 0);
        gra2idx.put("Month", 1);
        gra2idx.put("Day", 2);
        gra2idx.put("Hour", 3);
        gra2idx.put("Minute", 4);
        gra2idx.put("Second", 5);
    }

    public void put(int id, String timestamp) {
        logs.putIfAbsent(timestamp, new ArrayList<> ());
        logs.get(timestamp).add(id);
    }

    public List<Integer> retrieve(String s, String e, String gra) {
        List<Integer> result = new ArrayList<> ();

        String[] sp = s.split("\\:");
        String[] ep = e.split("\\:");

        // if(sp.length != 6 || sp.length != ep.length) throw new Exception("wrong format!");

        int le = sp.length;
        int index = gra2idx.get(gra);
        int i = 0;
        StringBuilder ts = new StringBuilder();
        StringBuilder te = new StringBuilder();
        for(; i<=index; ++i) {
            ts.append(sp[i]);
            if(i != 5) ts.append(":");

            te.append(ep[i]);
            if(i != 5) te.append(":");
        }

        for(; i<6; ++i) {
            ts.append(earliestDate[i]);
            if(i != 5) ts.append(":");

            te.append(latestDate[i]);
            if(i != 5) te.append(":");
        }

        String start = ts.toString();
        String end = te.toString();

        // System.out.println(start + " " + end);

        SortedMap<String, List<Integer> > subMap = logs.subMap(start, true, end, true);
        for(List<Integer> v: subMap.values()) {
            result.addAll(v);
        }

        return result;
    }

}

/**
 * Your LogSystem object will be instantiated and called as such:
 * LogSystem obj = new LogSystem();
 * obj.put(id,timestamp);
 * List<Integer> param_2 = obj.retrieve(s,e,gra);
 */
