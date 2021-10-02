import java.util.Arrays;

public class LongestSubstringWithoutRepeatingCharacters {
	/**
	 * Time complexity: O(2n)
	 */
	public int lengthOfLongestSubstring(String s) {
		int res = 0, j = 0;
		int[] freq = new int[128];
		for (int i = 0; i < s.length(); ++i) {
			char c = s.charAt(i);
			freq[c]++;
			while (freq[c] > 1) {
				freq[s.charAt(j)]--;
				j++;
			}
			res = Math.max(res, i - j + 1);
		}
		return res;
	}

	/**
	 * Time complexity: O(n) ?
	 */
	public int betterLengthOfLongestSubstring(String s) {
		int res = 0, start = 0;
		int[] indices = new int[128];
		Arrays.fill(indices, -1);
		for (int i = 0; i < s.length(); ++i) {
			char c = s.charAt(i);
			if (indices[c] >= start) start = indices[c] + 1;
			indices[c] = i;
			res = Math.max(res, i - start + 1);
		}
		return res;
	}

	public static void main(String[] args) {
		LongestSubstringWithoutRepeatingCharacters obj
				= new LongestSubstringWithoutRepeatingCharacters();
		String s = "bbbxyzbccc";
		System.out.println(obj.lengthOfLongestSubstring(s));
		System.out.println(obj.betterLengthOfLongestSubstring(s));
	}

}
