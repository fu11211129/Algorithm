import java.util.*;

public class LongestPathInNaryTree {
    public static Map<Node, Integer> height = new HashMap<> ();

	public static int getLongestPathNary(Node root) {
		if(root == null) return 0;

		int childNum = root.childs.size();
		int result = 0;
		for(int i=0; i<childNum; ++i) {
			int len = getLongestPathNary(root.childs.get(i));
			result = Math.max(result, len);
		}

		Integer max1 = null, max2 = null;
		for(int i=0; i<childNum; ++i) {
			Node cn = root.childs.get(i);
			int h = height.get(cn);
			if(max1 == null || h > max1) {
				max2 = max1;
				max1 = h;
			} else if(max2 == null || h > max2) {
				max2 = h;
			}
		}

		max1 = max1 == null? 0: max1;
		max2 = max2 == null? 0: max2;
		int curMax = max1 + max2 + 1;

		return Math.max(result, curMax);
	}

	public static int depth(Node root) {
		if(root == null) return 0;

		int childNum = root.childs.size();
		int[] h = new int[childNum];
		int maxH = 0;
		for(int i=0; i<childNum; ++i) {
			h[i] = depth(root.childs.get(i));
			maxH = Math.max(maxH, h[i]);
		}
		height.put(root, maxH+1);
		return maxH + 1;
	}

	public static void main(String[] args) {
		Node root = buildTree();
		depth(root);

		for(Node nd: height.keySet()) {
			System.out.println(nd.val + "\t" + height.get(nd));
		}

		int maxLen = getLongestPathNary(root);
		System.out.println("lnegth of longest path = " + maxLen);
	}

	public static Node buildTree() {
		Node n11 = new Node(1);
		Node n21 = n11.addChild(3);
		Node n22 = n11.addChild(5);

		Node n31 = n21.addChild(8);
		Node n32 = n21.addChild(9);
		Node n33 = n21.addChild(10);
		Node n34 = n22.addChild(11);
		Node n35 = n22.addChild(12);
		Node n36 = n22.addChild(13);

		Node n41 = n33.addChild(15);
		Node n42 = n33.addChild(16);
		Node n43 = n36.addChild(17);
		Node n44 = n36.addChild(18);
		Node n45 = n36.addChild(19);

		Node n51 = n41.addChild(4);
		Node n52 = n43.addChild(2);

		Node n61 = n52.addChild(6);
		Node n62 = n52.addChild(7);

		Node n71 = n62.addChild(14);
		return n11;
	}

	static class Node {
		public int val;
		public List<Node> childs;

		public Node(int v) {
			this.val = v;
			this.childs = new ArrayList<> ();
		}

		public Node addChild(int v) {
			Node child = new Node(v);
			this.childs.add(child);
			return child;
		}
	}
}
