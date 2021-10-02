import java.util.HashMap;


public class FruitIntoBaskets {
	/**
	 * Trivial solution: using a HashMap
	 */
	public int totalFruitUsingHashMap(int[] fruits) {
		HashMap<Integer, Integer> m = new HashMap<Integer, Integer>();
		int j = 0, cnt = 0, res = 0, n = fruits.length;
		for (int i = 0; i < n; ++i) {
			if (i > 0) {
				int x = m.get(fruits[i-1]);
				if (x == 1) {
					m.remove(fruits[i-1]);
					cnt--;
				} else {
					m.put(fruits[i-1], x-1);
				}
			}
			while (j < n && cnt <= 2) {
				if (m.containsKey(fruits[j])) {
					int x = m.get(fruits[j]);
					m.put(fruits[j], x+1);
				} else {
					if (cnt == 2) break;
					m.put(fruits[j], 1);
					cnt++;
				}
				res = Math.max(res, j-i+1);
				j++;
			}
			System.out.println(m);
			System.out.println("\t" + res);
		}
		return res;
	}

	/**
	 * Optimal solution: using two pointers and apply the idea of the 'running sum'
	 */
	public int totalFruit(int[] fruits) {
		int prev2 = -1, prev = -1;
		int res = 0, cnt = 0, prevCnt = 0;
		for (int cur : fruits) {
			// update cnt, res
			if (cur != prev2 && cur != prev) cnt = prevCnt + 1;
			else cnt++;
			res = Math.max(res, cnt);
			// update prevCnt
			prevCnt = (cur == prev) ? prevCnt + 1 : 1;
			// update prev2, prev
			if (cur != prev2 && cur != prev) {
				prev2 = prev;
				prev = cur;
			} else if (cur == prev2) {
				prev2 = prev;
				prev = cur;
			}
		}
		return res;
	}

	public static void main(String[] args) {
	    int[] fruits = {3,3,3,1,2,1,1,2,3,3,4};
		FruitIntoBaskets object = new FruitIntoBaskets();
		System.out.println(object.totalFruit(fruits));
	}
}
