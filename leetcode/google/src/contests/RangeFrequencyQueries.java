package contests;

import java.util.*;

/**
 * Range Frequency Queries
 * Keys: Binary search: lower/upper bound
 */
public class RangeFrequencyQueries {
	private Map<Integer, List<Integer>> map = new HashMap<>();

	public RangeFrequencyQueries(int[] arr) {
		for (int i = 0; i < arr.length; ++i) {
			map.putIfAbsent(arr[i], new ArrayList<>()); // keep in mind
			map.get(arr[i]).add(i);
		}
	}

	public int query(int left, int right, int value) {
		List<Integer> list = map.getOrDefault(value, new ArrayList<>());
		return lowerBound(list, right+1) - lowerBound(list, left);
	}

	private int lowerBound(List<Integer> a, int value) {
		int l = 0, r = a.size(), m;
		while (l < r) {
			m = (l+r)/2;
			if (a.get(m) < value) l = m+1; else r = m;
		}
		return r;
	}

}
