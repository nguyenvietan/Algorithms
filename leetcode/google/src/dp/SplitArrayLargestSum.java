package dp;

/*
public class SplitArrayLargestSum {
	private int maxSumMinimized;

	private void dfs(int[] nums, int i, int cntSubarrayRemain, int[] sums) {
		// base cases
		if (i >= nums.length) {
			if (cntSubarrayRemain == 1) {
				int maxLocal = Integer.MIN_VALUE;
				for (int s : sums) maxLocal = Math.max(maxLocal, s);
				maxSumMinimized = Math.min(maxSumMinimized, maxLocal);
			}
			return;
		} else {
			if (cntSubarrayRemain == 0) return;
		}

		// recursion
		// pick nums[i] for the current subarray
		sums[cntSubarrayRemain-1] += nums[i];
		dfs(nums, i+1, cntSubarrayRemain, sums);
		sums[cntSubarrayRemain-1] -= nums[i]; // backtrack

		// move nums[i] to the next subarray
		if (cntSubarrayRemain > 1) {
			sums[cntSubarrayRemain-2] += nums[i];
			dfs(nums, i+1, cntSubarrayRemain-1, sums);
			sums[cntSubarrayRemain-2] -= nums[i]; // backtrack
		}
	}

	public int splitArray(int[] nums, int m) {
		maxSumMinimized = Integer.MAX_VALUE;
		int[] sums = new int[m];
		dfs(nums, 0, m, sums);
		return maxSumMinimized;
	}

	public void printArray(int[] a) {
		for (int x : a) System.out.printf("%d ", x);
		System.out.println();
	}

	public static void main(String[] args) {
		int[] nums = new int[]{1,2,3,4,5};
		int m = 2;
		int res = new SplitArrayLargestSum().splitArray(nums, m);
		System.out.println(res);
	}

}
*/

/**
 * Solution #3: Binary Search + Simulation (greedy)
 *      hi  ---------------------------
 *   -> mid             ----
 *      lo  ---------------------------
 */
/*
class SplitArrayLargestSum {

	public int splitArray(int[] nums, int m) {
		// find low = max(nums[i]), high = sum(nums[i])
		int lo = Integer.MIN_VALUE, hi = 0, mid = 0;
		for (int x : nums) {
			hi += x;
			lo = Math.max(lo, x);
		}
		// binary search + simulation (greedy)
		int ans = hi;
		while (lo <= hi) {
			mid = (lo + hi)/2;
			// simulation (greedy)
			int sum = 0, cnt = 1;
			for (int x : nums) {
				if (sum + x <= mid) sum += x;
				else {
					sum = x;
					cnt++;
				}
			}
			// System.out.printf("lo = %d, hi = %d: cnt = %d\n", lo, hi, cnt);
			if (cnt <= m) {
				ans = Math.min(ans, mid);
				hi = mid - 1;
			} else lo = mid + 1;
		}
		return ans;
	}

	public static void main(String[] args) {
		int[] nums = new int[]{1, 2, 3, 4, 1};
		int m = 2;
		int res = new SplitArrayLargestSum().splitArray(nums, m);
		System.out.println(res);
	}

}
*/

/**
 * Solution #3: bottom-up DP
 *          x x x x x x x x | x x x
 *        0 1          <- k       i
 *
 *          dp[i][j] = min(Max(dp[k][j-1], s[i]-s[k]))
 *                      k
 */
class SplitArrayLargestSum {
	public int splitArray(int[] nums, int m) {
		int n = nums.length;
		int[] s = new int[n+1];
		for (int i = 0; i < n; ++i) {
			s[i+1] = s[i] + nums[i];
		}
		int[][] dp = new int[n+1][m+1];
		for (int i = 0; i <= n; ++i) {
			for (int j = 0; j <= m; ++j) {
				dp[i][j] = Integer.MAX_VALUE;
			}
		}
		dp[0][0] = 0; // don't forget to initialize, otherwise all values would remain infinite
		// TODO: review carefully this technique: bottom-up DP
		for (int i = 1; i <= n; ++i) {
			for (int j = 1; j <= m; ++j) {
				for (int k = 0; k < i; ++k) {
					dp[i][j] = Math.min(dp[i][j], Math.max(dp[k][j-1], s[i]-s[k]));
				}
			}
		}
		return dp[n][m];
	}

	public static void main(String[] args) {
		int[] nums = new int[]{1, 2, 3, 4, 1};
		int m = 2;
		int res = new SplitArrayLargestSum().splitArray(nums, m);
		System.out.println(res);
	}

}