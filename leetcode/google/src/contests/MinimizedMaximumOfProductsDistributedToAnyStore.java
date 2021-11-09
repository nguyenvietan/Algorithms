package contests;

public class MinimizedMaximumOfProductsDistributedToAnyStore {

	public int minimizedMaximum(int n, int[] quantities) {
		int lo = Integer.MAX_VALUE, hi = Integer.MIN_VALUE, mid = 0;
		int ans = Integer.MAX_VALUE, m = quantities.length;
		int sum = 0;
		for (int x : quantities) {
			sum += x;
			if (x > hi) hi = x;
		}
		lo = sum/n > 0 ? sum/n : 1;
		System.out.printf("start: lo = %d, hi = %d\n", lo, hi);
		while (lo <= hi) {
			mid = (hi+lo)/2;
			int nStores = 0;
			for (int x : quantities) {
				nStores += (x-1)/mid + 1; // concise!!!
			}
			if (nStores <= n) {
				ans = Math.min(ans, mid);
				System.out.printf("+++[%d, %d, %d]\n", lo, mid, hi);
				hi = mid - 1;
			} else {
				lo = mid + 1;
				System.out.printf("---[%d, %d, %d]\n", lo, mid, hi);
			}
		}
		return ans;
	}
	public static void main(String[] args) {
		int[] quantities = {1, 1, 2, 5};
		int n = 7;
		int ans = new MinimizedMaximumOfProductsDistributedToAnyStore().minimizedMaximum(n, quantities);
		System.out.println(ans);
	}
}
