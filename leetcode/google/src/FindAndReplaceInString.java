import java.util.HashMap;

public class FindAndReplaceInString {

	public static String findReplaceString(String s, int[] indices, String[] sources, String[] targets) {
		int k = indices.length, n = s.length();
		HashMap<Integer, Pair> map = new HashMap<Integer, Pair>();
		for (int i = 0; i < k; ++i) {
			if (s.substring(indices[i], indices[i] + sources[i].length()).equals(sources[i])) {
				map.put(indices[i], new Pair(sources[i].length(), targets[i]));
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; ++i) {
			char c = s.charAt(i);
			if (map.containsKey(i)) {
				sb.append(map.get(i).replacer);
				i += map.get(i).span-1;
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	private static class Pair {
		private int span;
		private String replacer;
		Pair(int l, String s) { span = l; replacer = s; }
	}

	public static void main(String[] args) {
		String s = "helloworldhowareyou";
		int[] indices = {0, 5, 13};
		String[] sources = {"hello", "world", "arere"};
		String[] targets = {"hi", "there", "were"};
		String res = findReplaceString(s, indices, sources, targets);
		System.out.println(res);
	}
}
