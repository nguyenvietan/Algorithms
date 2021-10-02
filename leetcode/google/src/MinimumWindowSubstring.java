/**
 * Minimum Window Substring
 * keys: sliding window, two-pointer technique
 *      L-------------R
 *        ->  L-------R
 */
public class MinimumWindowSubstring {

	public static String minWindow(String s, String t) {
		int iStart = -1, iEnd = -1, i = 0, j = 0, cnt = 0;
		int n = s.length(), m = t.length();
		int[] f = new int[128], f2 = new int[128];
		for (int k = 0; k < m; ++k) f[t.charAt(k)]++;
		while (j < n) {
			// phase: ++
			char c = s.charAt(j);
			f2[c]++;
			if (f2[c] <= f[c]) cnt++;
			// phase: --
			while (i < n && cnt == m) {
				if (iStart == -1 || (j - i < iEnd - iStart)) {
					iStart = i;
					iEnd = j;
				}
				char x = s.charAt(i);
				f2[x]--;
				if (f2[x] < f[x]) cnt--;
				i++;
			}
			j++; // don't forget to increase j
		}
		return iStart == -1 ? "" : s.substring(iStart, iEnd+1);
	}

	public static void main(String[] args) {
		String s = "aaxbxxxddbcccb";
		String t = "aabb";
		String res = minWindow(s, t);
		System.out.println(res);
	}

}
