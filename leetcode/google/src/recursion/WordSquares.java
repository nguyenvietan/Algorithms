package recursion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Solution #1: make a prefix hashmap, dfs+backtracking
 * Note:    List<List<String> res = new ArrayList<>();
 *          res.add(new ArrayList<>(can)); // allocate a new array list, deep copy
 */
public class WordSquares {

	private Map<String, List<String>> map;
	private List<List<String>> res;

	public List<List<String>> wordSquares(String[] words) {
		int n = words[0].length();
		map = new HashMap<>();
		res = new ArrayList<List<String>>(); // ??
		buildPrefixHashMap(words);
		// dfs + backtrack
		for (String w : words) {
			List<String> can = new ArrayList<>();
			can.add(w);
			backtrack(n, 1, can, words);
		}
		return res;
	}

	private void backtrack(int n, int i, List<String> can, String[] words) {
		if (i == n) {
			res.add(new ArrayList<>(can)); // allocate a new array list, deep copy
			return;
		}
		StringBuilder prefix = new StringBuilder();
		for (String s : can) prefix.append(s.charAt(i));
		for (String w : getWordsWithPrefix(prefix.toString())) {
			can.add(w);
			backtrack(n, i+1, can, words);
			can.remove(can.size()-1);
		}
	}

	private void buildPrefixHashMap(String[] words) {
		for (String w : words) {
			for (int i = 1; i < words[0].length(); ++i) {
				String prefix = w.substring(0, i);
				List<String> list = map.getOrDefault(prefix, new ArrayList<>());
				list.add(w);
				map.put(prefix, list);
			}
		}
	}

	private List<String> getWordsWithPrefix(String prefix) {
		return map.getOrDefault(prefix, new ArrayList<>());
	}

	public static void main(String[] args) {
		// verify: new ArrayList<>(src) - deep or shallow copy? -> deep copy! because of creating a new object
		List<Integer> l1 = new ArrayList<>();
		l1.add(1);
		l1.add(2);
		List<Integer> l2 = new ArrayList<>(l1);
		l2.remove(0);
		l2.add(10);

		for (int x : l1) {
			System.out.printf("%d ", x);
		}
		System.out.println();
		for (int x : l2) {
			System.out.printf("%d ", x);
		}
	}
}

/**
 * Solution #2: Trie: prefix lookup, dfs+backtracking
 */