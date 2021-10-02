public class AddTwoNumbers {
	public static class ListNode {
		int val;
		ListNode next;
		ListNode() {}
		ListNode(int val) { this.val = val; }
		ListNode(int val, ListNode next) { this.val = val; this.next = next; };
	}

	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode point = new ListNode();
		ListNode cur = point;
		int carry = 0, x;
		while (l1 != null && l2 != null) {
			x = l1.val + l2.val + carry;
			cur.next = new ListNode(x % 10);
			carry /= 10;
			l1 = l1.next;
			l2 = l2.next;
			cur = cur.next;
		}
		while (l1 != null) {
			x = l1.val + carry;
			cur.next = new ListNode(x % 10);
			cur = cur.next;
			l1 = l1.next;
			carry /= 10;
		}
		while (l2 != null) {
			x = l2.val + carry;
			cur.next = new ListNode(x % 10);
			cur = cur.next;
			l2 = l2.next;
			carry /= 10;
		}
		if (carry == 1) cur.next = new ListNode(1);
		return point.next;
	}

	public static void main(String[] args) {
		ListNode cur;
		ListNode l1 = new ListNode(1);
		cur = l1;

		ListNode l2 = new ListNode(2);
	}

}
