public class MaxUnivalueSubtree {
    static int re = 0;

    static Wrapper maxUnivalSubtree(Node root) {
        if(root.left == null && root.right == null) {
            re = Math.max(re, 1);
            return new Wrapper(1, root.val);
        }

        else if(root.left != null && root.right != null) {
            Wrapper lw = maxUnivalSubtree(root.left);
            if(lw.size == -1) return new Wrapper(-1, -1);

            Wrapper rw = maxUnivalSubtree(root.right);
            if(rw.size == -1) return new Wrapper(-1, -1);

            if(lw.v != root.val || rw.v != root.val || lw.v != rw.v) return new Wrapper(-1, -1);

            int size = lw.size + 1 + rw.size;
            re = Math.max(re, size);
            return new Wrapper(size, root.val);
        }

        else if(root.left == null && root.right != null) {
            Wrapper rw = maxUnivalSubtree(root.right);
            if(rw.size == -1) return new Wrapper(-1, -1);

            if(rw.v != root.val) return new Wrapper(-1, -1);

            int size = rw.size + 1;
            re = Math.max(re, size);
            return new Wrapper(rw.size + 1, root.val);
        }

        else {
            Wrapper lw = maxUnivalSubtree(root.left);
            if(lw.size == -1) return new Wrapper(-1, -1);

            if(lw.v != root.val) return new Wrapper(-1, -1);

            int size = lw.size + 1;
            re = Math.max(re, size);
            return new Wrapper(lw.size + 1, root.val);
        }
    }

    public static void main(String[] args) {
        Node root = new Node(5);
        root.left = new Node(5);
        root.left.left = new Node(5);
        root.left.right = new Node(5);
        root.right = new Node(1);

        maxUnivalSubtree(root);
        System.out.println(re);
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

    static class Wrapper {
        int size;
        int v;

        public Wrapper(int sz, int x) {
            size = sz;
            v = x;
        }
    }
}
