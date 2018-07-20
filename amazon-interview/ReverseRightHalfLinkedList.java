public class ReverseRightHalfLinkedList {

    static Node reverse(Node head) {
        if(head == null || head.next == null) return head;

        Node dummy = new Node(-1);
        dummy.next = head;
        Node slow = dummy, fast = dummy;
        Node prev = null;
        int nodeNum = 0;

        for(; fast.next != null; ) {
            prev = slow;
            slow = slow.next;
            fast = fast.next;
            nodeNum += 1;

            if(fast.next != null) {
                nodeNum += 1;
                fast = fast.next;
            }
        }

        if(nodeNum % 2 == 0) {
            Node firstTail = slow;
            Node p = slow.next;
            Node q = p.next;
            Node secondTail = p;
            int cnt = 0;
            int rev = nodeNum / 2 - 1;
            for(; cnt < rev; cnt += 1) {
                Node r = q.next;
                q.next = p;
                p = q;
                q = r;
            }
            Node secondHead = p;
            firstTail.next = secondHead;
            secondTail.next = null;
        }

        else {
            Node firstTail = prev;
            Node p = slow;
            Node q = p.next;
            Node secondTail = p;
            int cnt = 0;
            int rev = nodeNum / 2;
            for(; cnt < rev; cnt += 1) {
                Node r = q.next;
                q.next = p;
                p = q;
                q = r;
            }
            Node secondHead = p;
            firstTail.next = secondHead;
            secondTail.next = null;
        }
        pln(head);
        return head;
    }

    static void pln(Node head) {
        Node h = head;
        for(; h != null; h = h.next) System.out.print(h.val + " ");
        System.out.println();
    }

    public static void main(String[] args) {
        Node head = new Node(2);
        head.next = new Node(1);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = new Node(6);
        head.next.next.next.next.next.next = new Node(7);
        head.next.next.next.next.next.next.next = new Node(8);

        // Node head = new Node(5);
        // head.next = new Node(7);
        // head.next.next = new Node(8);
        // head.next.next.next = new Node(6);
        // head.next.next.next.next = new Node(3);
        // head.next.next.next.next.next = new Node(4);
        // head.next.next.next.next.next.next = new Node(2);
        reverse(head);
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
