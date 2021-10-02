import java.util.ArrayList;
import java.util.List;

public class MissingRanges {

	public static String formatRange(int start, int end) {
		if (end < start) return "";
		StringBuilder sb = new StringBuilder();
		sb.append(start);
		if (end > start) sb.append("->").append(end);
		return sb.toString();
	}

	public static List<String> findMissingRanges(int[] nums, int lower, int upper) {
		int n = nums.length;
		List<String> res = new ArrayList<String>();
		if (n == 0) {
			res.add(formatRange(lower, upper));
			return res;
		}
		if (nums[0] > lower) res.add(formatRange(lower, nums[0]-1));
		for (int i = 1; i < n; ++i) {
			if (nums[i] - nums[i-1] < 2) continue;
			res.add(formatRange(nums[i-1]+1, nums[i]-1));
		}
		if (nums[n-1] < upper) res.add(formatRange(nums[n-1]+1, upper));
		return res;
	}

	public static void main(String[] args) {
		int[] nums = {0, 1, 3, 50, 75, 99};
		// int[] nums = {};
		List<String> res = findMissingRanges(nums, 0, 99);
		System.out.println(res);
	}
}
