package dp;

public class MaximumProductSubarray {

	public int maxProduct(int[] nums) {
		int res = nums[0], maxVal = nums[0], minVal = nums[0];
		for (int i = 1; i < nums.length; ++i) {
			int x = nums[i];
			int newMaxVal = Math.max(Math.max(x, x*minVal), x*maxVal);
			int newMinVal = Math.min(Math.min(x, x*minVal), x*maxVal);
			maxVal = newMaxVal;
			minVal = newMinVal;
			res = Math.max(res, maxVal);
		}
		return res;
	}

	public static void main(String[] args) {
		int[] a = new int[]{-3};
		int res = new MaximumProductSubarray().maxProduct(a);
		System.out.println(res);
	}
}
