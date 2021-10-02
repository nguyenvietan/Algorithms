public class BackspaceStringCompare {

	public static boolean backspaceCompare(String s, String t) {
		int i = s.length() - 1, j = t.length() - 1;
		while (i >= 0 && j >= 0) {
			i = nextChar(s, i);
			j = nextChar(t, j);
			System.out.printf("i = %d, j = %d\n", i, j);
			if (i < 0 || j < 0) break;
			if (s.charAt(i) != t.charAt(j)) return false;
			i--;
			j--;
		}
		return nextChar(s, i) < 0 && nextChar(t, j) < 0;
	}

	public static int nextChar(String s, int i) {
		if (i < 0) return i;
		int cnt = s.charAt(i) == '#' ? 1 : 0;
		if (cnt == 0) return i;
		while (cnt > 0) {
			i--;
			if (i < 0) break;
			if (s.charAt(i) == '#') cnt++; else cnt--;
			if (i > 0 && s.charAt(i-1) == '#') { cnt++; i--; }
		}
		return i-1;
	}

	public static void main(String[] args) {
		String s = "isfcow#";
		String t = "isfcog#w#";
		boolean ans = backspaceCompare(s, t);
		System.out.println(ans);
	}
}
