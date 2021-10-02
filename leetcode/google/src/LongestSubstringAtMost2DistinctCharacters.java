public class LongestSubstringAtMost2DistinctCharacters {
	/**
	 * Approach #1: two pointers + running sum
	 */
	public static int lengthOfLongestSubstringTwoDistinct(String s) {
		int res = 0, cnt = 0, prevCnt = 0;
		char prev = 0, prev2 = 0;
		for (int i = 0; i < s.length(); ++i) {
			char c = s.charAt(i);
			if (c != prev && c != prev2) {
				cnt = prevCnt + 1;
				prevCnt = 1;
				prev2 = prev;
				prev = c;
			} else if (c == prev) {
				cnt++;
				prevCnt++;
			} else {
				cnt++;
				prevCnt = 1;
				prev2 = prev;
				prev = c;
			}
			res = Math.max(res, cnt);
		}
		return res;
	}

	/**
	 * Solution #2: sliding window: L--------------R
	 *                                  L----------R
	 */
	public static int lengthOfLongestSubstringTwoDistinct2(String s) {
		int j = 0, i = 0, n = s.length(), res = 0, cnt = 0; // cnt: no. different chars in the sliding window
		char[] f = new char[128];
		while (j < n) {
			char c = s.charAt(j);
			// phase++: update f[], cnt
			if (f[c] == 0) cnt++;
			f[c]++;
			// phase--: move i -> right, update f[], cnt
			while (i < n && cnt > 2) {
				char x = s.charAt(i);
				f[x]--;
				if (f[x] == 0) cnt--;
				i++;
			}
			res = Math.max(res, j-i+1);
			j++;
		}
		return res;
	}

	public static void main(String[] args) {
		String s = "abcabcaaabbb";
		int res1 = lengthOfLongestSubstringTwoDistinct(s);
		System.out.println(res1);
		int res2 = lengthOfLongestSubstringTwoDistinct2(s);
		System.out.println(res2);
	}

}
