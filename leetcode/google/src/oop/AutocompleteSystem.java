package oop;

import java.util.*;
import java.util.stream.Collectors;

public class AutocompleteSystem {

	private Trie trie;
	private HashMap<String, Integer> sentencesToTimes;
	private SentenceComparator sentenceComparator;
	private StringBuilder buffer;

	public AutocompleteSystem(String[] sentences, int[] times) {
		trie = new Trie();
		sentencesToTimes = new HashMap<>();
		sentenceComparator = new SentenceComparator();
		buffer = new StringBuilder();
		for (int i = 0; i < sentences.length; ++i) {
			sentencesToTimes.put(sentences[i], times[i]);
			trie.insert(sentences[i]);
		}
	}
	public List<String> input(char c) {
		List<String> res = null;
		if (c == '#') {
			String word = buffer.toString();
			// put buffer word into map, time++
			int times = sentencesToTimes.getOrDefault(word, 0);
			// insert buffer word into trie if it not exists
			if (times == 0) trie.insert(word);
			sentencesToTimes.put(word, ++times);
			// TODO: clear the buffer
			buffer.setLength(0);
			// buffer.delete(0, buffer.length());
		} else {
			buffer.append(c);
			res = trie.searchAllSentencesWithPrefix(buffer.toString());
		}
		if (res == null) return new ArrayList<>();
		Collections.sort(res, sentenceComparator);
		return res.size() > 3 ? res.subList(0, 3) : res;
	}

	private class TrieNode {
		private final TrieNode[] next;
		private final HashSet<String> sentences; // tricky!! TODO: any improvement? - keep the top 3 sentences at each node
		public TrieNode() {
			next = new TrieNode[256];
			sentences = new HashSet<>();
		}
	}
	private class Trie {
		private TrieNode root;
		public Trie() {
			root = new TrieNode();
		}
		public void insert(String word) {
			TrieNode cur = root;
			for (int i = 0; i < word.length(); ++i) {
				int idx = word.charAt(i);
				if (cur.next[idx] == null) cur.next[idx] = new TrieNode();
				cur = cur.next[idx];
				cur.sentences.add(word);
			}
		}
		public List<String> searchAllSentencesWithPrefix(String prefix) {
			List<String> res = null;
			TrieNode cur = root;
			for (int i = 0; i < prefix.length(); ++i) {
				int idx = prefix.charAt(i);
				if (cur.next[idx] == null) return null;
				cur = cur.next[idx];
			}
			// TODO: convert set to array list
			res = new ArrayList<>(cur.sentences);
			return res;
		}
	}

	private class SentenceComparator implements Comparator<String> {
		public int compare(String a, String b) {
			int aFreq = sentencesToTimes.get(a);
			int bFreq = sentencesToTimes.get(b);
			if (aFreq != bFreq) return -aFreq + bFreq;
			return a.compareTo(b);
		}
	}

	public static void main(String[] args) {
		String[] sentences = new String[]{ "i love you", "island", "iroman", "i love leetcode" };
		int[] times = new int[]{ 5, 3, 2, 2 };
		AutocompleteSystem autoSys = new AutocompleteSystem(sentences, times);
		List<String> list = null;
		list = autoSys.input('i');
		printList(list);
		list = autoSys.input(' ');
		printList(list);
		list = autoSys.input('a');
		printList(list);
		list = autoSys.input('#');
		printList(list);

		list = autoSys.input('i');
		printList(list);
		list = autoSys.input(' ');
		printList(list);
		list = autoSys.input('a');
		printList(list);
		list = autoSys.input('#');
		printList(list);
	}

	private static void printList(List<String> list) {
		if (list == null) {
			System.out.println("null");
			return;
		}
		for (String s : list) {
			System.out.println(s);
		}
		System.out.println("---");
	}
}
