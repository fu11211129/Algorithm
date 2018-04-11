import java.util.*;

public class FindKthElementinBST {

    static Node head = null;
    static Node prev = null;

    public static void main(String[] args) {
        int max = (1 << 22);
        // System.out.println(max);
        Node root = build(1, max);
        int k = 10000;
        // long prev = System.currentTimeMillis();
        // findKth(root, new Counter(0), k);
        // long curr = System.currentTimeMillis();
        // System.out.println((curr-prev) + " ms.");
        //
        // prev = System.currentTimeMillis();
        // int re = findKthByFlattenBST(root, k);
        // curr = System.currentTimeMillis();
        // System.out.println(re + "\t" + (curr-prev) + " ms.");

        // List<Integer> results = new ArrayList<> ();
        // long prev = System.currentTimeMillis();
        // findKCloset(root, new Counter(0), k, results);
        // long curr = System.currentTimeMillis();
        // System.out.println((curr-prev) + " ms.");
        //
        // results.clear();
        // prev = System.currentTimeMillis();
        // findKClosetByFlattenBST(root, k, results);
        // curr = System.currentTimeMillis();
        // System.out.println(results.size() + "\t" + (curr-prev) + " ms.");

        List<Integer>[] bucket = new List[10];
    }

    // time cost: O(h + k)/ so if k is comparatively small with n, the time cost is closed to O(h)
    static void findKth(Node root, Counter co, int k) {
        if(root == null || co.cc >= k) return;

        findKth(root.left, co, k);
        co.cc += 1;

        if(co.cc == k) {
            System.out.print(root.val + "\t");
            return;
        }

        findKth(root.right, co, k);
    }

    static void findKCloset(Node root, Counter co, int k, List<Integer> results) {
        if(root == null || co.cc >= k) return;

        findKth(root.left, co, k);
        co.cc += 1;

        if(co.cc == k) {
            results.add(root.val);
            return;
        }

        findKth(root.right, co, k);
    }

    static void findKClosetByFlattenBST(Node root, int k, List<Integer> results) {
        flatten(root);

        Node p = head;
        int c = 0;
        while(p != null && c < k) {
            results.add(p.val);
            p = p.right;
            c += 1;
        }
    }

    static int findKthByFlattenBST(Node root, int k) {
        flatten(root);

        Node p = head;
        int c = 0;
        while(p != null && c < k-1) {
            p = p.right;
            c += 1;
        }
        return p.val;
    }

    static void flatten(Node root) {
        if(root == null) return;

        flatten(root.left);

        if(head == null) {
            head = root;
            prev = root;
        } else {
            prev.right = root;
            root.left = prev;
            prev = root;
        }

        flatten(root.right);
    }

    static int getSize(Node o) {
        if(o == null) return 0;
        return getSize(o.left) + getSize(o.right) + 1;
    }

    static Node build(int l, int r) {
        if(l > r) return null;

        int mid = (l + r) / 2;
        Node root = new Node(mid);
        root.left = build(l, mid-1);
        root.right = build(mid+1, r);

        return root;
    }

    static class Node {
        int val;
        Node left;
        Node right;

        public Node(int v) {
            val = v;
            left = right = null;
        }
    }

    static class Counter {
        int cc;

        public Counter(int c) {
            cc = c;
        }
    }
}
