
/**
 * Solution #1: Quick select, O(n) time
 *      3 2 1 5 6 4     | k = 2
 *      5 6 *4 3 2 1
 *     *6 5
 *     Average time complexity: O(n)
 *     Proof: T = cn + cn/2 + cn/4 + ... + c
 *              = cn(1 + 1/2 + 1/4 + ... + 1/2^(logN)) < cn(1 + 1/2 + 1/4 + ...) = 2cn = O(n)
 */
/*
public class KthLargestElement {

	*/
/**
	 * (smaller) | (x x ... x) | (greater)
	 *           l             r
	 *           i->
	 *           i++ if(a[i] >= x)
	 */
/*
	public static int partition(int[] a, int l, int r) {
		int pivot = a[r]; // or select a random element
		int i = l;
		while (i <= r) {
			if (a[i] == pivot) i++;
			else if (a[i] < pivot) swap(a, l++, i++); // '<' for ascending order, '>' otherwise
			else swap(a, i, r--); // do not advance i here
		}
		return l;
	}

	public static void swap(int[] a, int i, int j) {
		int tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}

	public static int quickSelect(int[] a, int l, int r, int k) {
		int i = partition(a, l, r);
		Utils.printArray(a);
		if (i == k-1) return a[i];
		if (i > k-1) return quickSelect(a, l, i-1, k);
		return quickSelect(a, i+1, r, k);
	}

	public static int findKthLargest(int[] a, int k) {
		return quickSelect(a, 0, a.length-1, k);
	}

	public static void main(String[] args) {
		int[] a = { 3, 2, 1, 5, 6, 4 };
		int k = 4, n = a.length;
		Utils.printArray(a);
		System.out.println("---------");
		int ans = findKthLargest(a, n + 1 - k);
		System.out.println(ans);
	}

}
*/

import java.util.PriorityQueue;
/**
 * Solution #2: Heap, O(NlogK)
 * use a min heap to store k largest elements
 */
public class KthLargestElement {

	public static int findKthLargest(int[] a, int k) {
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(); // min heap
		for (int i = 0; i < a.length; ++i) {
			if (pq.isEmpty() || pq.size() < k || (pq.size() ==k && pq.peek() < a[i])) pq.add(a[i]);
			if (pq.size() == k + 1) pq.poll();
		}
		return pq.peek();
	}

	public static void main(String[] args) {
		int[] a = { 3, 2, 1, 5, 6, 4 };
		int k = 2;
		Utils.printArray(a);
		int ans = findKthLargest(a, k);
		System.out.println(ans);
	}
}