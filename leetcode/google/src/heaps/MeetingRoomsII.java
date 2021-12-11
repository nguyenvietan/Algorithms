import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MeetingRoomsII {

	/**
	 * Solution #1: sort and count using start and end pointers
	 * Time complexity: O(NlogN)
	 */
	public static int minMeetingRooms(int[][] intervals) {
		int cnt = 0, n = intervals.length, ep = 0, ans = 0;
		int[] start = new int[n], end = new int[n];
		for (int i = 0; i < n; ++i) {
			start[i] = intervals[i][0];
			end[i] = intervals[i][1];
		}
		Arrays.sort(start);
		Arrays.sort(end);
		for (int i = 0; i < n; ++i) {
			cnt++;
			while (ep < n && end[ep] <= start[i]) { ep++; cnt--; }
			ans = Math.max(ans, cnt);
		}
		return ans;
	}

	/**
	 * Solution 2: Priority Queue (Min Heap)
	 * Time complexity: O(NlogN)
	 */
	public static int minMeetingRooms2(int[][] intervals) {
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(); // stores the end times
		// sort the intervals based on starting times
		Arrays.sort(intervals, new Comparator<int[]>() {
			public int compare(final int[] a, final int[] b) { return a[0] - b[0]; }
		});

		pq.add(intervals[0][1]);
		for (int i = 1; i < intervals.length; ++i) {
			int newStart = intervals[i][0];
			int newEnd = intervals[i][1];
			int earliestEnd = pq.peek();
			if (newStart >= earliestEnd) pq.poll();
			pq.add(newEnd);
		}
		return pq.size();
	}

	public static void main(String[] args) {
//		int[][] intervals = {{0, 30}, {5, 10}, {15, 20}};
		int[][] intervals = {{7, 10}, {2, 4}};
		int ans = minMeetingRooms(intervals);
		int ans2 = minMeetingRooms2(intervals);
		System.out.println(ans);
		System.out.println(ans2);
	}

}
