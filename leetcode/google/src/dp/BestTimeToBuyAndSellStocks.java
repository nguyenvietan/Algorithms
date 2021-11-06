package dp;

public class BestTimeToBuyAndSellStocks {
	public int maxProfit(int[] prices) {
		int res = 0, bottom = prices[0];
		for (int i = 1; i < prices.length; ++i) {
			res = Math.max(prices[i]-bottom, res);
			bottom = Math.min(bottom, prices[i]);
		}
		return res;
	}
}
