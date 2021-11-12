package recursion;

import java.util.ArrayList;
import java.util.List;

class WordSearch2 {

	private class Node {
		boolean isWord;
		Node[] next;
		String word;
		Node() { this.isWord = false; next = new Node[26]; this.word = null; }
	}

	private int dr[] = new int[]{0, 0, 1, -1};
	private int dc[] = new int[]{1, -1, 0, 0};
	private int rows, cols;
	private boolean[][] visited;
	private Node root;

	public List<String> findWords(char[][] board, String[] words) {
		this.rows = board.length;
		this.cols = board[0].length;
		this.visited = new boolean[rows][cols];
		buildTrie(words);
		List<String> res = new ArrayList<>();
		for (int i = 0; i < rows; ++i) {
			for (int j = 0; j < cols; ++j) {
				int idx = board[i][j] - 'a';
				visited[i][j] = true; // don't forget!!
				dfs(board, i, j, root.next[idx], visited, res);
				visited[i][j] = false; // don't fortget!!
			}
		}
		return res;
	}

	private void dfs(char[][] board, int r, int c, Node cur, boolean[][] visited, List<String> res) {
		if (cur == null) return;
		if (cur.isWord) {
			res.add(cur.word);
			cur.isWord = false; // tricky!!! to void duplicate words
		}
		for (int k = 0; k < 4; ++k) {
			int rr = r + dr[k], cc = c + dc[k];
			// don't forge to check visited here
			if (0 <= rr && rr < rows && 0 <= cc && cc < cols && !visited[rr][cc]) {
				visited[rr][cc] = true;
				char ch = board[rr][cc];
				int idx = ch - 'a';
				dfs(board, rr, cc, cur.next[idx], visited, res);
				visited[rr][cc] = false;
			}
		}
		cur = null; // tricky!! trim the leaf nodes recursively
	}

	private void buildTrie(String[] words) {
		root = new Node();
		for (String word : words) {
			Node cur = root;
			for (int i = 0; i < word.length(); ++i) {
				int idx = word.charAt(i) - 'a';
				if (cur.next[idx] == null) cur.next[idx] = new Node();
				cur = cur.next[idx];
				if (i == word.length()-1) {
					cur.isWord = true;
					cur.word = word; // review??
				}
			}
		}
	}

}