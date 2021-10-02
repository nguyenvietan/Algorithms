package oop;

import java.util.*;
import java.util.stream.Collectors;

class AutocompleteSystem2 {

	private final Map<String, Integer> sentences;
	private final StringBuilder buffer = new StringBuilder();
	private final TrieNode root = new TrieNode();
	private TrieNode current = root;

	public AutocompleteSystem2(String[] sentences, int[] times) {
		this.sentences = new HashMap<>();
		for (int i = 0; i < sentences.length; i++) {
			this.sentences.put(sentences[i], times[i]);
			insert(sentences[i]);
		}
	}

	public List<String> input(char c) {
		if (c == '#') {
			String sentence = buffer.toString();
			Integer times = sentences.getOrDefault(sentence, 0);
			if (times == 0) {
				insert(sentence);
			}
			sentences.put(sentence, ++times);
			buffer.setLength(0);
			current = root;
		} else {
			buffer.append(c);
			if (current != null) {
				current = current.get(c);
				if (current != null) {
					List<Sentence> matches = current.getWords().stream().map(sentence -> new Sentence(sentence, sentences.get(sentence))).collect(Collectors.toList());
					Collections.sort(matches);
					return matches.subList(0, Math.min(3, matches.size())).stream().map(sentence -> sentence.text).collect(Collectors.toList());
				}
			}
		}
		return Collections.EMPTY_LIST;
	}

	private void insert(String word) {
		TrieNode current = root;
		for (int i = 0; i < word.length(); i++) {
			TrieNode node = current.get(word.charAt(i));
			if (node != null) {
				current = node;
			} else {
				current = current.add(word.charAt(i));
			}
			current.sentences.add(word);
		}
	}

	private class TrieNode {
		private final TrieNode[] links = new TrieNode[27];
		private Set<String> sentences = new HashSet<>();

		private int index(char c) {
			return c == ' ' ? 0 : c - 'a' + 1;
		}

		private TrieNode get(char c) {
			return links[index(c)];
		}

		private TrieNode add(char c) {
			return links[index(c)] = new TrieNode();
		}

		private Set<String> getWords() {
			return sentences;
		}

	}

	private class Sentence implements Comparable<Sentence> {
		private final String text;
		private final int times;
		public Sentence (String text, int times) {
			this.text = text;
			this.times = times;
		}

		public int compareTo(Sentence other) {
			int rank = other.times - times;
			return rank == 0 ? text.compareTo(other.text) : rank;
		}

		@Override
		public boolean equals(Object other) {
			return ((Sentence) other).text.equals(text);
		}

		@Override
		public int hashCode() {
			return text.hashCode();
		}
	}
}
