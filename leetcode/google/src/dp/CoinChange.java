package dp;

/**
 * Solution #1: DP-bottom-up
 *      init dp[i] = infinite, dp[0] = 0
 */
public class CoinChange {

	private final int LIMIT = 10005;

	public int coinChange(int[] coins, int amount) {
		int[] dp = new int[amount+1];
		for (int i = 0; i <= amount; ++i) dp[i] = LIMIT;
		dp[0] = 0; // never ever forget!!!
		for (int a = 0; a <= amount; ++a) {
			for (int c : coins) {
				if (a >= c) dp[a] = Math.min(dp[a], 1 + dp[a-c]);
			}
		}
		return dp[amount] < LIMIT ? dp[amount] : -1;
	}
}
