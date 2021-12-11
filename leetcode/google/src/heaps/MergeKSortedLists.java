import java.util.Comparator;
//import javafx.util.Pair;
import java.util.PriorityQueue;

public class MergeKSortedLists {

	private static class ListNode {
		int val;
		ListNode next;
		ListNode() {};
		ListNode(int val) { this.val = val; }
		ListNode(int val, ListNode next) { this.val = val; this.next = next; }
	}

	/**
	 * Solution #1: round-robin. time: O(kN), space: O(1)
	 */
	public ListNode mergeKLists1(ListNode[] lists) {
		if (lists.length == 0) return null;
		ListNode point = new ListNode();
		ListNode cur = point;
		int minIdx = 0, curVal;
		boolean exist;
		while (true) {
			exist = false;
			curVal = Integer.MAX_VALUE;
			for (int i = 0; i < lists.length; ++i) {
				if (lists[i] != null) {
					exist = true;
					if (lists[i].val <= curVal) {
						minIdx = i;
						curVal = lists[i].val;
					}
				}
			}
			if (!exist) break;
			cur.next = new ListNode(lists[minIdx].val);
			cur = cur.next;
			lists[minIdx] = lists[minIdx].next;
		}
		return point.next;
	}

	/**
	 * Solution #2: Priority Queue. Time: O(NlogK), Space: O(K)
	 */
	public ListNode mergeKLists2(ListNode[] lists) {
		if (lists.length == 0) return null;
		ListNode point = new ListNode();
		ListNode cur = point;
		PriorityQueue<Utils.Pair<Integer, ListNode>> pq = new PriorityQueue<Utils.Pair<Integer, ListNode>>((a, b) -> a.getValue().val-b.getValue().val);
		// PriorityQueue<Utils.Pair<Integer, ListNode>> pq = new PriorityQueue<Utils.Pair<Integer, ListNode>>(new PairComparator());

		// insert all the heads of lists to pq
		for (int i = 0; i < lists.length; ++i) {
			if (lists[i] != null) {
				pq.add(new Utils.Pair(Integer.valueOf(i), lists[i]));
				lists[i] = lists[i].next;
			}
		}
		// poll out pq in each loop, advance the corresponding pointer to the next position
		while (!pq.isEmpty()) {
			Utils.Pair<Integer, ListNode> p = pq.poll();
			int i = p.getKey();
			ListNode node = p.getValue();
			cur.next = new ListNode(node.val);
			cur = cur.next;
			if (lists[i] != null) {
				pq.add(new Utils.Pair(Integer.valueOf(i), lists[i]));
				lists[i] = lists[i].next;
			}
		}
		return point.next;
	}

	private class PairComparator implements Comparator<Utils.Pair<Integer, ListNode>> {
		public int compare(Utils.Pair<Integer, ListNode> p1, Utils.Pair<Integer, ListNode> p2) {
			if (p1.getValue().val < p2.getValue().val) return -1;
			if (p1.getValue().val == p2.getValue().val) return 0;
			return 1;
		}
	}

	/** [the best solution]
	 * Solution #3: Divide and Conquer. Time: O(nlog(k)). Space: O(1).
	 */
	public static ListNode mergeKLists3(ListNode[] lists) {
		if (lists.length == 0) return null;
		return merge(lists, 0, lists.length-1);
	}

	private static ListNode merge(ListNode[] lists, int l, int r) {
		if (l == r) return lists[l];
		if (l + 1 == r) return merge2Lists(lists[l], lists[r]);
		int m = (l + r) / 2;
		ListNode p1 = merge(lists, l, m);
		ListNode p2 = merge(lists, m + 1, r);
		return merge2Lists(p1, p2);
	}

	private static ListNode merge2Lists(ListNode p1, ListNode p2) {
		ListNode head = new ListNode();
		ListNode cur = head, p1Next, p2Next;
		while (p1 != null && p2 != null) {
			p1Next = p1.next;
			p2Next = p2.next;
			if (p1.val <= p2.val) {
				cur.next = p1;
				p1 = p1Next;
			} else {
				cur.next = p2;
				p2 = p2Next;
			}
			cur = cur.next;
		}
		cur.next = p1 != null ? p1 : p2;
		return head.next;
	}

	private static void printList(ListNode head) {
		ListNode cur = head;
		while (cur != null) {
			System.out.printf(cur.val + " ");
			cur = cur.next;
		}
		System.out.println();
	}
	public static void main(String[] args) {
		ListNode head0 = new ListNode(0);
		ListNode head1 = new ListNode(1);
		ListNode head2 = new ListNode(2);
		ListNode p0 = head0, p1 = head1, p2 = head2;
		for (int i = 3; i < 20; i += 3) {
			p0.next = new ListNode(i);
			p0 = p0.next;
		}
		for (int i = 4; i < 20; i += 3) {
			p1.next = new ListNode(i);
			p1 = p1.next;
		}
		for (int i = 5; i < 20; i += 3) {
			p2.next = new ListNode(i);
			p2 = p2.next;
		}
		ListNode[] lists = { head0, head1, head2 };
		ListNode head = mergeKLists3(lists);
		printList(head);
	}

}
