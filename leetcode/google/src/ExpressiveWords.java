public class ExpressiveWords {

	public static int expressiveWords(String s, String[] words) {
		int res = 0, cnt = 0, cntT = 0;
		char[] chars = new char[105], charsT = new char[105];
		int[] freq = new int[105], freqT = new int[105];

		// step 1: calculate chars[], freq[] on s
		cnt = calFreq(chars, freq, s);

		// step 2: iterate over words[] and validate
		for (String w : words) {
			cntT = calFreq(charsT, freqT, w);
			if (isValid(chars, freq, cnt, charsT, freqT, cntT)) res++;
		}
		return res;
	}

	public static int calFreq(char[] chars, int[] freq, String s) {
		char prev = '-';
		int j = -1;
		for (int i = 0; i < s.length(); ++i) {
			char c = s.charAt(i);
			if (c != prev) {
				++j;
				chars[j] = c;
				freq[j] = 1;
				prev = c;
			} else {
				freq[j]++;
			}
		}
		return j+1;
	}

	public static boolean isValid(char[] chars, int[] freq, int cnt, char[] charsT, int[] freqT, int cntT) {
		if (cnt != cntT) return false;
		for (int i = 0; i < cnt; ++i) {
			if (chars[i] != charsT[i]) return false;
			if (freqT[i] > freq[i]) return false;
			if (freqT[i] < freq[i] && freq[i] < 3) return false;
		}
		return true;
	}

	public static void main(String[] args) {
		String s = "dddiiiinnssssssoooo";
		String[] words = {"dinnssoo","ddinso","ddiinnso","ddiinnssoo","ddiinso","dinsoo","ddiinsso","dinssoo","dinso"};
		int res = expressiveWords(s, words);
		System.out.println(res);
	}

}
