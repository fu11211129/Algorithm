public class LargestBSTSubtree {
    public int re = 1;

    public int largestBSTSubtree(TreeNode root) {
        if(root == null) return 0;
        if(root.left == null && root.right == null) return 1;

        find(root);
        return re;
    }

    public Wrapper find(TreeNode o) {
        // leaf node, size = 1, range = [o.val, o.val]
        if(o.left == null && o.right == null) {
            return new Wrapper(1, o.val, o.val);
        }

        // threee cases
        // case #1. both two children of o exist
        if(o.left != null && o.right != null) {
            Wrapper lw = find(o.left);
            Wrapper rw = find(o.right);

            // in case either of two subtrees isn't bst,
            // in case o.val does not satisfy the requirement of bst
            // current root can never form a bst
            if(lw.size == -1 || rw.size == -1 || !(o.val > lw.ub && o.val < rw.lb)) return new Wrapper(-1, -1, -1);

            // otherwise, it can
            int size = lw.size + 1 + rw.size;
            re = Math.max(size, re);
            return new Wrapper(size, lw.lb, rw.ub);
        }

        // case #2. only left child is not nil
        else if(o.left != null && o.right == null) {
            Wrapper lw = find(o.left);

            // if not valid bst induced by current root.
            if(lw.size == -1 || !(o.val > lw.ub)) return new Wrapper(-1, -1, -1);

            // otherwise, it can
            int size = lw.size + 1;
            re = Math.max(size, re);
            return new Wrapper(size, lw.lb, o.val);
        }

        // case #3, right child not nil
        else if(o.right != null && o.left == null) {
            Wrapper rw = find(o.right);

            // if not valid bst induced by current root.
            if(rw.size == -1 || !(o.val < rw.lb)) return new Wrapper(-1, -1, -1);

            // otherwise, it can
            int size = rw.size + 1;
            re = Math.max(size, re);
            return new Wrapper(size, o.val, rw.ub);
        }

        // if none of children exist
        return new Wrapper(0, -1, -1);
    }

    class Wrapper {
        int size;
        int lb;
        int ub;

        public Wrapper(int sz, int l, int r) {
            size = sz;
            lb = l;
            ub = r;
        }
    }    
}
