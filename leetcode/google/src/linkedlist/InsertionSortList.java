package linkedlist;

/**
 * [REVIEW] Insertion Sort List: sort the singly linked list in the ascending order.
 * Key: dummy node:
 *      original list: 5 -> 3 -> 1 -> 2
 *      dummy -> 5 -> 3 -> 1 -> 2
 *
 */
public class InsertionSortList {
	private class ListNode {
		int val;
		ListNode next;
		ListNode() {}
	}
	public ListNode insertionSortList(ListNode head) {
		ListNode dummy = new ListNode();
		ListNode cur = head, prev, curNext;
		while (cur != null) {
			curNext = cur.next;
			prev = dummy;
			while (prev.next != null && prev.next.val < cur.val)
				prev = prev.next;
			cur.next = prev.next;
			prev.next = cur;
			cur = curNext;
		}
		return dummy.next;
	}

}
