import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class KClosestPointsToOrigin {

	/**
	 * Solution #1: Priority Queue (Min Heap), time O(NlogK), space O(N)
	 */
	public static int[][] kClosest(int[][] points, int k) {
		PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new DistanceComparator());
		for (int[] p : points) {
			if (pq.isEmpty() || pq.size() < k) {
				pq.add(p);
				System.out.printf("++added (%d, %d)\n", p[0], p[1]);
			}
			else {
				int[] u = pq.peek();
				System.out.printf("peek: (%d, %d)\n", u[0], u[1]);
				boolean isCloser = p[0]*p[0] + p[1]*p[1] - u[0]*u[0] - u[1]*u[1] < 0;
				System.out.println(isCloser);
				if (isCloser) {
					pq.poll();
					pq.add(p);
					System.out.printf("added (%d, %d)\n", p[0], p[1]);
				}
			}
		}
		int[][] res = new int[k][2];
		int i = 0;
		while (!pq.isEmpty()) {
			res[i++] = pq.poll();
		}
		return res;
	}
	private static class DistanceComparator implements Comparator<int[]> {
		public int compare(final int[] a, final int[] b) {
			return b[0] * b[0] + b[1] * b[1] - a[0] * a[0] - a[1] * a[1] ;
		}
	}

	/**
	 * Solution #2: Quick Select, time O(N), Space O(1)
	 */
/*
	public static int[][] KCloest2(int[][] points, int k) {

	}
*/

	public static void main(String[] args) {
//		int[][] points = { {1, 3}, {-2, 2} };
		int[][] points = { {3, 3}, {5, -1}, {-2, 4} };

		int k = 2;
		int[][] ans = kClosest(points, k);
		for (int[] p : ans) {
			System.out.printf("%d %d\n", p[0], p[1]);
		}
	}

}
