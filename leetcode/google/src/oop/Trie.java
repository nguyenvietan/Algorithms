package oop;

public class Trie {
	protected class Node {
		Node[] next;
		boolean isEnd;
		Node() {
			next = new Node[256];
		}
	}
	protected Node root;

	public Trie() {
		root = new Node();
	}
	public void insert(String word) {
		Node cur = root;
		for (int i = 0; i < word.length(); ++i) {
			int idx = (int)word.charAt(i);
			if (cur.next[idx] == null) {
				cur.next[idx] = new Node();
			}
			cur = cur.next[idx];
		}
		cur.isEnd = true;
	}
	public boolean search(String word) {
		Node cur = root;
		for (int i = 0; i < word.length(); ++i) {
			int idx = (int)word.charAt(i);
			if (cur.next[idx] == null) return false;
			cur = cur.next[idx];
		}
		return cur.isEnd;
	}
	public boolean startsWith(String prefix) {
		Node cur = root;
		for (int i = 0; i < prefix.length(); ++i) {
			int idx = (int)prefix.charAt(i);
			if (cur.next[idx] == null) return false;
			cur = cur.next[idx];
		}
		return true;
	}

	public static void main(String[] args) {
		Trie trie = new Trie();
		trie.insert("how are you");
		trie.insert("hello");
		trie.insert("hello world");
		trie.insert("essilor");
		System.out.println(trie.search("essilo"));
		System.out.println(trie.startsWith("essilo"));

	}

}
