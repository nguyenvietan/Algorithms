public class MergeTwoSortedLists {

	private static class ListNode {
		public int val;
		public ListNode next;
		public ListNode() {}
		public ListNode(int val) { this.val = val; }
		public ListNode(int val, ListNode next) { this.val = val; this.next = next; }
	}

	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		ListNode head = new ListNode();
		ListNode cur = head;
		while (l1 != null && l2 != null) {
			if (l1.val <= l2.val) {
				cur.next = l1;
				l1 = l1.next;
			} else {
				cur.next = l2;
				l2 = l2.next;
			}
			cur = cur.next;
		}
		cur.next = l1 != null ? l1 : l2;
		return head.next;
	}

}
