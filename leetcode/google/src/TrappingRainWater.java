public class TrappingRainWater {

	/**
	 * Solution #1: two-pointer technique
	 * - Downside: quite hard to recall
	 */
	public static int trap0(int[] height) {
		int sum = 0, i = 0, j = height.length - 1, curIndex = 0;
		int curMin = Math.min(height[i], height[j]);
		int curMax = Math.max(height[i], height[j]);
		while (i < j) {
			curIndex = height[i] <= height[j] ? ++i : --j;
			if (curMin >= height[curIndex]) sum += curMin - height[curIndex];
			curMin = Math.max(curMin, Math.min(height[curIndex], curMax));
			curMax = Math.max(curMax, height[curIndex]);
		}
		return sum;
	}

	/**
	 * Solution #2: find the peak
	 * 1 1 1 1 8 1 1 1
	 * ________|_____
	 * ->          <-
	 */
	public static int trap1(int[] height) {
		int sum = 0;
		int peak = 0, peakIndex = 0;
		for (int i = 0; i < height.length; ++i) {
			if (height[i] > peak) {
				peak = height[i];
				peakIndex = i;
			}
		}
		// left side: --> |
		int curHeight = 0;
		for (int i = 0; i < peakIndex; ++i) {
			curHeight = Math.max(curHeight, height[i]);
			if (curHeight > height[i]) sum += curHeight - height[i];
		}
		// right side:    | <--
		curHeight = 0;
		for (int j = height.length - 1; j > peakIndex; --j) {
			curHeight = Math.max(curHeight, height[j]);
			if (curHeight > height[j]) sum += curHeight - height[j];
		}
		return sum;
	}

	/**
	 * Solution #3: DP
	 *                      __    __
	 *              ___--_--  -__-  --__
	 *    leftMax ->
	 *                                  <- rightMax
	 */
	public static int trap2(int[] height) {
		int sum = 0, n = height.length, curMax;
		int[] leftMax = new int[n];
		int[] rightMax = new int[n];
		curMax = 0;
		for (int i = 0; i < n; ++i) {
			leftMax[i] = height[i] > curMax ? height[i] : curMax;
			curMax = height[i] > curMax ? height[i] : curMax;
		}
		curMax = 0;
		for (int i = n-1; i >= 0; --i) {
			rightMax[i] = height[i] > curMax ? height[i] : curMax;
			curMax = height[i] > curMax ? height[i] : curMax;
		}
		for (int i = 0; i < n; ++i) {
			sum += Math.min(leftMax[i], rightMax[i]) - height[i];
		}
		return sum;
	}

	public static void main(String[] args) {
		int[] height = { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 };
		int sum = trap0(height);
		System.out.println(sum);
		sum = trap1(height);
		System.out.println(sum);
		sum = trap2(height);
		System.out.println(sum);
	}

}
