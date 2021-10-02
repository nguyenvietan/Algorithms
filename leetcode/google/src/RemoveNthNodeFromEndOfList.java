class RemoveNthNodeFromEndOfList {

	private static class ListNode {
		public int val;
		public ListNode next;
		public ListNode() {}
		public ListNode(int val) { this.val = val; }
		public ListNode(int val, ListNode next) { this.val = val; this.next = next; }
	}

	/**
	 *      head = [1 2 3 4 5], n = 2
	 *   	head = [1 2 3], n = 3
	 */

	public static ListNode removeNthFromEnd(ListNode head, int n) {
		ListNode p1 = null, p2 = head;
		int i = 0;
		while (p2 != null) {
			p2 = p2.next;
			i++;
			if (p1 != null) p1 = p1.next;
			if (i == n+1) p1 = head;
		}
		if (i < n + 1) return head.next;
		p1.next = p1.next.next;
		return head;
	}
}