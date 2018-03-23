public class PlusOneLinkedList {
    public static void main(String[] args) {
        PlusOneLinkedList pone = new PlusOneLinkedList();
        ListNode ls = pone.createList(new int[] {1, 2, 3});

        ListNode p = pone.plusOne(ls);
        printList(p);
    }

    public ListNode plusOne(ListNode head) {
        ListNode rev = reverse(head);

        ListNode p = rev;
        p.val += 1;
        while(p.val == 10) {
            p.val = 0;
            if(p.next != null) {
                p.next.val += 1;
                p = p.next;
            } else {
                p.next = new ListNode(1);
                break;
            }
        }

        return reverse(rev);
    }

    public ListNode reverse(ListNode head) {
        if(head == null || head.next == null) return head;

        ListNode prev = head, curr = head.next, last = head;
        while(curr != null) {
            ListNode nx = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nx;
        }

        last.next = null;
        return prev;
    }

    public ListNode createList(int[] arr) {
        ListNode head = new ListNode(-1);
        ListNode r = head;

        for(int x: arr) {
            r.next = new ListNode(x);
            r = r.next;
        }
        head = head.next;
        return head;
    }

    public static void printList(ListNode node) {
        while(node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        } System.out.println();
    }

    static class ListNode {
        int val;
        ListNode next;

        public ListNode(int v) {
            val = v;
            next = null;
        }
    }
}
