package sortingandsearching;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MergeIntervals {
	public int[][] merge(int[][] intervals) {

		List<int[]> res = new ArrayList<>();

		Arrays.sort(intervals, new NewComparator());
		// Arrays.sort(intervals, (a, b) -> (a[0] - b[0]));

		int start = intervals[0][0], end = intervals[0][1];
		for (int[] a : intervals) {
			int newStart = a[0], newEnd = a[1];
			if (newStart <= end) end = Math.max(end, newEnd);
			else {
				res.add(new int[]{start, end});
				start = newStart;
				end = newEnd;
			}
		}

		res.add(new int[]{start, end});
		return res.toArray(new int[res.size()][]);

	}

	private class NewComparator implements Comparator<int[]> {
		public int compare(final int[] a, final int[] b) {
			return a[0] - b[0];
		}
	}
}
