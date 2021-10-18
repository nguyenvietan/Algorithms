package sortingandsearching;

import java.util.ArrayList;
import java.util.List;

/**
 * Divide the program into three parts:
 *          |----|  |----|  |------|  |---------|   |-------|
 *                     |----------------|
 *           while#1        while#2                 while#3
 *           (before)       (during)                (after)
 */
public class InsertInterval {

	public int[][] insert(int[][] intervals, int[] newInterval) {

		List<int[]> res = new ArrayList<>();
		int idx = 0, n = intervals.length;
		int newStart = newInterval[0], newEnd = newInterval[1];

		// 1. before
		while (idx < n && intervals[idx][1] < newStart) {
			res.add(intervals[idx++]);
		}
		// 2. inside
		while (idx < n && intervals[idx][0] <= newEnd) {
			newStart = Math.min(newStart, intervals[idx][0]);
			newEnd = Math.max(newEnd, intervals[idx][1]);
			idx++;
		}
		res.add(new int[]{newStart, newEnd});
		// 3. after
		while (idx < n) {
			res.add(intervals[idx++]);
		}
		return res.toArray(new int[res.size()][2]);
	}

}
