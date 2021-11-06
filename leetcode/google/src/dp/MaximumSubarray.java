package dp;

/** Solution #1: peak - bottom
 * Time: O(n), space O(1)
 */
/*
public class MaximumSubarray {
	public int maxSumarray(int[] nums) {
		int bottom = 0, s = 0, maxSum = Integer.MIN_VALUE;
		for (int x : nums) {
			s += x;
			maxSum = Math.max(maxSum, s-bottom);
			bottom = Math.min(bottom, s);
		}
		return maxSum;
	}
	public static void main(String[] args) {
		int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
		int maxSum = new MaximumSuarray().maxSumarray(nums);
		System.out.println(maxSum);
	}
}
*/

/**
 * Solution #2: Divide and conquer
 * Time: O(NlogN), Space: O(logN)
 */

public class MaximumSubarray {
	public int maxSumArray(int[] nums) {
		return find0(nums, 0, nums.length-1);
	}

	private int find0(int[] a, int l, int r) {
		if (l >= r) return a[l];
		int m = (l+r)/2;

		int s = 0, i = m-1;
		int left = 0;
		while (i >= l) {
			s += a[i--];
			left = Math.max(left, s);
		}

		s = 0; i = m+1;
		int right = 0;
		while (i <= r) {
			s += a[i++];
			right = Math.max(right, s);
		}

		int maxLeft = find0(a, l, m-1);
		int maxRight = find0(a, m+1, r);


		int maxMid = a[m] + left + right;
		int res = maxMid;
		res = Math.max(res, maxLeft);
		res = Math.max(res, maxRight);
		return res;
	}

	public static void main(String[] args) {
//		int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
		int[] nums = {5,4,-1,7,8};
		int maxSum = new MaximumSubarray().maxSumArray(nums);
		System.out.println(maxSum);
	}

}