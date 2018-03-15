import java.util.*;

public class RandomKey {

	public static Random rand = new Random();

	public static String getRandomKey(Map<String, Integer> map, int rid) {
		System.out.println(rid);
		System.out.println(map);

		int pos = 0, id = 0;
		Iterator it = map.entrySet().iterator();
		String result = "";

		while(pos <= rid && it.hasNext()) {
			Map.Entry KV = (Map.Entry) it.next();
			result = (String) KV.getKey();
			int weight = (int) KV.getValue();
			pos += weight;
		}

		return result;
	}

	public static void main(String[] args) {
		Map<String, Integer> map = new HashMap<> ();
		map.put("apple", 10);
		map.put("banana", 50);
		map.put("peach", 30);

		int range = 0;
		for(int v: map.values()) range += v;

		int rid = rand.nextInt(range);

		String result = getRandomKey2(map, rid);
		System.out.println(result);
	}

	static class Node {
		String key;
		int val;

		public Node(String k, int v) {
			this.key = k;
			this.val = v;
		}
	}


	public static String getRandomKey2(Map<String, Integer> map, int rid) {
		System.out.println(rid);
		System.out.println(map);
		
		Node[] nr = new Node[map.size()];
		int e = 0;
		for(String k: map.keySet()) {
			nr[e++] = new Node(k, map.get(k));
		}
		int[] sum = new int[map.size()];
		sum[0] = nr[0].val;
		for(int i=1; i<sum.length; ++i) {
			sum[i] = sum[i-1] + nr[i].val;
		}

		int l = 0, r = sum.length-1;
		while(l < r) {
			int mid = (l+r)/2;
			if(sum[mid]-sum[l]+nr[l].val < rid) l = mid + 1;
			else if(sum[mid]-sum[l]+nr[l].val == rid) return nr[mid].key;
			else r=mid;
		}
		return nr[l].key;
	}
}