public class FindKthElementinBST {

    public static void main(String[] args) {
        Node root = build(1, 7);
        int k = 3;
        int result = findKth(root, k);
        System.out.println(result);
    }

    // time cost: O(h + k)/ so if k is comparatively small with n, the time cost is closed to O(h)
    static void findKth(Node root, Counter co) {
        if(root == null || co.cc >= 2) return;

        findKth(root.left, co);
        co.cc += 1;

        if(co.cc == k) {
            System.out.println(root.val);
            return;
        }

        findKth(root.right, co);
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
}
