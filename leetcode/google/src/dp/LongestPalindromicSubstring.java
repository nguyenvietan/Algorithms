package dp;

/**
 * Solution #1: Expand around center
 */
/*
public class LongestPalindromicSubstring {

	public String longestPalindrome(String s) {
		int n = s.length();
		if (n == 1) return s;
		int maxLen = 1, left = 0, right = 0;

		// find odd-length palindrome substrings
		for (int i = 1; i < n; ++i) {
			int l = i-1, r = i+1;
			while (l >= 0 && r < n && s.charAt(l) == s.charAt(r)) {
				if (r-l+1 > maxLen) {
					maxLen = r-l+1;
					left = l;
					right = r;
				}
				l--; r++;
			}
		}
		// find even-length palindrome substrings
		for (int i = 0; i < n; ++i) {
			int l = i, r = i + 1;
			while (l >= 0 && r < n && s.charAt(l) == s.charAt(r)) {
				if (r-l+1 > maxLen) {
					maxLen = r-l+1;
					left = l;
					right = r;
				}
				l--; r++;
			}
		}
		return s.substring(left, right+1);
	}

	public static void main(String[] args) {
		String s = "aaaxxyxxaaa";
		String res = new LongestPalindromicSubstring().longestPalindrome(s);
		System.out.println(res);
	}

}
*/

/**
 * Solution #2: DP: no need for recursion!!!
 *              dp[i][j] = dp[i+1][j-1] && s[i] == s[j]
 *                 xxxxxxxx
 *                 ^    ^
 *                 i -> j
 */
class LongestPalindromicSubstring {
	public String longestPalindrome(String s) {
		if (s == null || s.length() == 1) return s;
		String ans = null;
		int n = s.length(), maxLen = 0;
		boolean[][] dp = new boolean[n][n]; // ??
		for (int r = 0; r < n; ++r) {
			for (int l = 0; l <= r; ++l) {
				boolean judge = s.charAt(l) == s.charAt(r);
				dp[l][r] = r-l > 2 ? dp[l+1][r-1] && judge : judge;
				if (dp[l][r] && r-l+1 > maxLen) {
					maxLen = r-l+1;
					ans = s.substring(l, r+1);
				}
			}
		}
		return ans;
	}
}
