import java.util.*;

public class RateLimiter {

	private int qps;
	private Map<String, Queue<Integer> > map;

	public void setQPS(int qps) {
		this.qps = qps;
		this.map = new HashMap<> ();
	}

	public boolean allowThisRequest(Request req) {
		boolean allowed = false;
		String ip = req.ip;
		long ts = req.ts;

		if(!map.containsKey(ip)) {
			map.put(ip, new Linkedlist<> ());
			allowed = true;
		} else if(map.get(ip).size() < qps || ts - map.get(ip).peek().ts > 1000) {
			allowed = true;
		}

		if(allowed) {
			map.get(ip).offer(ts);
			if(map.get(ip).size() > qps){ 
				map.get(ip).poll();
			}
			return true;
		}

		return false;
	}
}