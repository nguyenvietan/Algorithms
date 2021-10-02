import java.util.HashMap;

public class CopyListWithRandomPointer {

	private static class Node {
		int val;
		Node next;
		Node random;
		public Node(int val) {
			this.val = val;
			this.next = null;
			this.random = null;
		}
	}

	/**
	 * Solution #1: Iteration, O(N) space
	 *        1  ->  2  ->  3  ->  4  ->  5
	 *  x -> 1' ->  2' ->  3' ->  4' ->  5'
	 *  HashMap: 1 : 1'
	 *          2 : 2'
	 *           ...
	 *          5 : 5'
	 */
	public Node copyRandomList(Node head) {
		// create a new list and a hash map
		Node p1 = head, p2 = new Node(0), newHead = null;
		HashMap<Node, Node> map = new HashMap<Node, Node>();
		while (p1 != null) {
			p2.next = new Node(p1.val);
			if (newHead == null) newHead = p2.next;
			map.put(p1, p2.next);
			p1 = p1.next;
			p2 = p2.next;
		}
		// link random pointers
		p1 = head;
		p2 = newHead;
		while (p2 != null) {
			if (map.containsKey(p1.random)) p2.random = map.get(p1.random);
			p2 = p2.next;
			p1 = p1.next;
		}
		return newHead;
	}

	/**
	 * Solution #2: Iteration, O(1) space
	 *  1  ->  2  ->  3  ->  4  ->  5
	 *  1->1'->2->2'->3->3'->4->4'->5->5'
	 */
	public Node copyRandomList2(Node head) {
		if (head == null) return null;
		// insert new nodes
		// 1->1'->2->2'->3->3'->4->4'->5->5'
		Node p1 = head, p2 = null;
		while (p1 != null) {
			p2 = p1.next;
			p1.next = new Node(p1.val);
			p1.next.next = p2;
			p1 = p2;
		}
		// link random pointers
		p1 = head;
		while (p1 != null) {
			p2 = p1.next;
			if (p1.random != null) p2.random = p1.random.next;
			if (p2 != null) p1 = p2.next;
		}
		// separate old and new lists
		Node newHead = head.next;
		p1 = head;
		p2 = p1.next;
		while (p2 != null) {
			p1.next = p2.next;
			p1 = p2;
			p2 = p2.next;
		}
		return newHead;
	}

}
