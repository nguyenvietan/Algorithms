import java.util.*;

public class ThreeSum {

	public List<List<Integer>> threeSum(int[] nums) {
		Arrays.sort(nums);
		List<List<Integer>> res = new ArrayList<>();
		for (int i = 0; i < nums.length; ++i) {
			if (i > 0 && nums[i-1] == nums[i]) continue;
			var seen = new HashSet<Integer>();
			for (int j = i+1; j < nums.length; ++j) {
				int x = - nums[i] - nums[j];
				if (seen.contains(x)) {
					res.add(Arrays.asList(nums[i], nums[j], x));
					while (j+1 < nums.length && nums[j] == nums[j+1]) ++j;
				} else seen.add(nums[j]);
			}
		}
		return res;
	}

	public static void main(String[] args) {
		ThreeSum obj = new ThreeSum();
		int[] nums = {-1,0,1,2,-1,-4};
		System.out.println(obj.threeSum(nums));
	}

}
