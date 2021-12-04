package oop;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

public class StreamOfCharacters {
	private class Node {
		Node[] next;
		boolean isWord;
		Node() { this.next = new Node[26]; this.isWord = false; }
	}

	private Deque<Character> dq;
	private Node root = new Node();

	public StreamOfCharacters(String[] words) {
		this.root = new Node();
		this.dq = new ArrayDeque<>();
		for (String word : words) {
			Node cur = root;
			for (int i=words.length-1; i>=0; --i) {
				int idx = word.charAt(i) - 'a';
				if (cur.next[idx] == null) cur.next[idx] = new Node();
				cur = cur.next[idx];
			}
			cur.isWord = true;
		}
	}

	public boolean query(char letter) {
		dq.add(letter);
		if (dq.size()>2000) dq.removeFirst();
		Iterator it = dq.descendingIterator();
		Node cur = root;
		while(it.hasNext()) {
			Character ch = (Character) it.next();
			int idx = ch - 'a';
			if (cur.next[idx] == null) return false;
			if (cur.next[idx].isWord) return true;
			cur = cur.next[idx];
		}
		return cur.isWord;
	}
	public boolean query2(char letter) {
		dq.addFirst(letter);
		if (dq.size()>2000) dq.removeFirst();
		Node cur = root;
		for (char ch : dq) {
			int idx = ch - 'a';
			if (cur.next[idx] == null) return false;
			if (cur.next[idx].isWord) return true;
			cur = cur.next[idx];
		}
		return cur.isWord;
	}

}
