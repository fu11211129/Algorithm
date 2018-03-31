import java.util.*;

public class LongestConsecutiveSequenceInNaryTree {

    public static void main(String[] args) {
        Node root = buildTree();
        Map<Node, Integer> decr = new HashMap<> ();
        Map<Node, Integer> incr = new HashMap<> ();
        getDecrDepthPerNode(root, decr);
        getIncrDepthPerNode(root, incr);

        // for(Node nd: decr.keySet()) {
		// 	System.out.println(nd.val + "\t" + (decr.get(nd) + incr.get(nd) - 1));
		// }

		int maxLen = findLongestConsecutiveSequence(root, incr, decr);
		System.out.println("lnegth of longest path = " + maxLen);
    }

    static int findLongestConsecutiveSequence(Node root, Map<Node, Integer> incr, Map<Node, Integer> decr) {
        if(root == null) return 0;

        int childNum = root.childs.size();
		int result = 0;
		for(int i=0; i<childNum; ++i) {
			int len = findLongestConsecutiveSequence(root.childs.get(i), incr, decr);
			result = Math.max(result, len);
		}

        // find one child with longest increasing subsequence
		Integer max = null;
		for(int i=0; i<childNum; ++i) {
			Node cn = root.childs.get(i);
            // if(cn.val + 1 != root.val) continue;

			int h = incr.get(cn);
			if(max == null || h > max) {
				max = h;
			}
		}
        max = max == null? 0: max;
        int incrH = max + 1;


        // find one child with longest decreasing subsequence
        max = null;
		for(int i=0; i<childNum; ++i) {
			Node cn = root.childs.get(i);
            // if(cn.val - 1 != root.val) continue;

			int h = decr.get(cn);
			if(max == null || h > max) {
				max = h;
			}
		}
        max = max == null? 0: max;
        int decrH = max + 1;

        return Math.max(result, incrH + decrH - 1);
    }

    static int getDecrDepthPerNode(Node root, Map<Node, Integer> decr) {
        if(root == null) return 0;

        int childNum = root.childs.size();
		int[] h = new int[childNum];
		int decrH = 0;
		for(int i=0; i<childNum; ++i) {
            Node child = root.childs.get(i);
			h[i] = getDecrDepthPerNode(child, decr);
			if(child.val - 1 == root.val) decrH = Math.max(decrH, h[i]);
		}

		decr.put(root, decrH + 1);
		return decrH + 1;
    }

    static int getIncrDepthPerNode(Node root, Map<Node, Integer> incr) {
        if(root == null) return 0;

        int childNum = root.childs.size();
		int[] h = new int[childNum];
		int incrH = 0;
		for(int i=0; i<childNum; ++i) {
            Node child = root.childs.get(i);
			h[i] = getIncrDepthPerNode(child, incr);
			if(child.val + 1 == root.val) incrH = Math.max(incrH, h[i]);
		}

		incr.put(root, incrH + 1);
		return incrH + 1;
    }

    public static Node buildTree() {
		Node n11 = new Node(1);
		Node n21 = n11.addChild(0);
		Node n22 = n11.addChild(2);

		Node n31 = n21.addChild(-1);
		Node n32 = n21.addChild(9);
		Node n33 = n21.addChild(-1);
		Node n34 = n22.addChild(11);
		Node n35 = n22.addChild(12);
		Node n36 = n22.addChild(3);

		Node n41 = n33.addChild(-2);
		Node n42 = n33.addChild(16);
		Node n43 = n36.addChild(4);
		Node n44 = n36.addChild(18);
		Node n45 = n36.addChild(4);

		Node n51 = n41.addChild(-3);
		Node n52 = n43.addChild(5);

		Node n61 = n52.addChild(6);
		Node n62 = n52.addChild(6);

		Node n71 = n62.addChild(7);
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
