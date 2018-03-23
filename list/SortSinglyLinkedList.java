public class SortSinglyLinkedList {
    public static void main(String[] args) {
        SortSinglyLinkedList sorter = new SortSinglyLinkedList();
        ListNode list = sorter.createList(new int[] {4, 3, 2, 1, 5});

        ListNode sortedList = sorter.sortList(list);
        sorter.printList(sortedList);
    }

    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null) return head;

        // halve the list into two parts
        ListNode slow = head, fast = head, prev = null;
        while(fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        // very important, to cut down list into two halves.
        prev.next = null;

        ListNode leftSorted = sortList(head);
        ListNode rightSorted = sortList(slow);

        return merge(leftSorted, rightSorted);
    }

    public ListNode merge(ListNode la, ListNode lb) {
        // create a dummy head node
        ListNode head = new ListNode(-1);
        ListNode r = head;

        while(la != null && lb != null) {
            if(la.val <= lb.val) {
                r.next = la;
                la = la.next;
            } else {
                r.next = lb;
                lb = lb.next;
            }
            r = r.next;
        }

        while(la != null) {
            r.next = la;
            la = la.next;
            r = r.next;
        }

        while(lb != null) {
            r.next = lb;
            lb = lb.next;
            r = r.next;
        }

        head = head.next;
        return head;
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

    public void printList(ListNode node) {
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
