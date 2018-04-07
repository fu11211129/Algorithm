public class RemoveNthNodeFromEndOfList {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head == null) return null;
        if(head.next == null) {
            if(n == 1) return null;
            else return head;
        }

        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode fast = dummy;
        ListNode slow = dummy;

        for(int i=0; i<n; ++i) {
            fast = fast.next;
        }

        if(fast == null) return null;

        ListNode prev = slow;
        while(fast != null) {
            fast = fast.next;
            prev = slow;
            slow = slow.next;
        }

        // System.out.println(prev.val + " " + slow.val);

        ListNode nx = slow.next;
        prev.next = nx;

        return dummy.next;
    }    
}
