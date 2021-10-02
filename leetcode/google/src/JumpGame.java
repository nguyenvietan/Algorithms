/**
 * Jump Game
 * keys: DP : O(n^2) -> Greedy O(n), only keeps the leftmost GOOD index
 */

public class JumpGame {

	public boolean canJump(int[] nums) {
		if (nums[0] == 0 && nums.length > 1) return false;
		int n = nums.length, lastIndex = n-1;
		for (int i = n-1; i >= 0; --i) {
			lastIndex = nums[i] >= (lastIndex - i) ? i : lastIndex;
		}
		return lastIndex == 0;
	}

	public static void main(String[] args) {
		int[] nums = {2, 3, 1, 1, 4};
		JumpGame jumpGame = new JumpGame();
		System.out.println(jumpGame.canJump(nums));
	}
}
