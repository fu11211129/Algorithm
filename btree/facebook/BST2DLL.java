public class BST2DLL {
    public Node head = null;
    public Node prev = null;

    public Node treeToDoublyList(Node root) {
        flatten(root);
        if(prev != null) {
            prev.right = head;
            head.left = prev;
        }
        return head;
    }

    public void flatten(Node root) {
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
}
