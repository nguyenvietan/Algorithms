package contests;

/**
 * Sum of k mirror numbers
 * keys: generate 10-based mirror numbers
 */
public class SumOfKMirrorNumbers {
	public long kMirror(int k, int n) {
		long sum = 0;
		// directly generate mirror 10-based numbers, check for mirror k-based numbers
		for (int len = 1; ; ++len) {
			int x = (int)Math.pow(10, (len-1)/2);
			int y = (int)Math.pow(10, (len+1)/2);
			for (int i = x; i < y; ++i) {
				long b = i;
				int j = (len%2 == 1) ? i/10 : i;
				while (j > 0) {
					b = b*10 + j%10;
					j /= 10;
				}
				String kBasedNum = Long.toString(b, k);
				if (isMirror(kBasedNum.toCharArray())) {
					sum += b;
					if (--n == 0) return sum;
				}
			}
		}
	}

	private boolean isMirror(char[] num) {
		int l = 0, r = num.length-1;
		while (l < r) {
			if (num[l] != num[r]) return false;
			l++; r--;
		}
		return true;
	}
}
