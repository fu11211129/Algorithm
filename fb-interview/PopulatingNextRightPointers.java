public class PopulatingNextRightPointers {
    public void connect(TreeLinkNode root) {
        if(root == null) return;

        root.next = null;
        while(root != null) {
            TreeLinkNode first = root;
            TreeLinkNode p = first;
            while(p != null) {
                if(p.left != null) {
                    if(p.right != null) p.left.next = p.right;
                    else p.left.next = findNephew(p.next);
                }

                if(p.right != null) {
                    p.right.next = findNephew(p.next);
                }
                p = p.next;
            }
            if(first.left != null) root = first.left;
            else if(first.right != null) root = first.right;
            else {
                root = findNephew(root.next);
            }
        }
    }

    public TreeLinkNode findNephew(TreeLinkNode node) {
        if(node == null) return null;

        while(node != null) {
            if(node.left != null) return node.left;
            else if(node.right != null) return node.right;
            else node = node.next;
        }

        return null;
    }    
}
