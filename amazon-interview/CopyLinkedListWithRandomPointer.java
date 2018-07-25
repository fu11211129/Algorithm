public class CopyLinkedListWithRandomPointer {
    public RandomListNode copyRandomList(RandomListNode head) {
        HashMap<RandomListNode, RandomListNode> hm = new HashMap<RandomListNode, RandomListNode> ();
		RandomListNode p = head;
		while(p != null) {
			RandomListNode no = new RandomListNode(p.label);
			hm.put(p, no);
			p = p.next;
		}

		RandomListNode r = head;
		while(r != null) {
			RandomListNode no = hm.get(r);
			no.next = hm.get(r.next);
			no.random = hm.get(r.random);
			r = r.next;
		}

		return hm.get(head);
    }    
}
