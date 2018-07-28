public class MoveZeros {

    static Node moveZeros(Node head) {
        if(head == null || head.next == null) return head;

        
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(0);
        head.next.next = new Node(2);
        head.next.next.next = new Node(0);
        head.next.next.next.next = new Node(3);
        head.next.next.next.next.next = new Node(0);
    }

    static class Node {
        int val;
        Node next;

        public Node(int v) {
            val = v;
            next = null;
        }
    }
}
