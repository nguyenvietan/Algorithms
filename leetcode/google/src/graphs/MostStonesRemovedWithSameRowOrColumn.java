package graphs;

/**
 * Solution #1: Naive Union Find, unite points by pair
 * Time complexity: O(N^2logN). note: union(i, j) takes O(logN) with path compression
 */
/*
public class MostStonesRemovedWithSameRowOrColumn {
	private int[] parent;
	private int[] rank;
	private int numSets;
	private void union(int i, int j) {
		int pi = find(i), pj = find(j);
		if (pi == pj) return;
		if (rank[pi] < rank[pj]) {
			parent[pi] = pj;
		} else if (rank[pi] == rank[pj]) {
			parent[pi] = pj;
			rank[pj]++;
		} else {
			parent[pj] = pi;
		}
		numSets--;
	}
	private int find(int i) {
		if (i != parent[i]) return parent[i] = find(parent[i]); // compress path -> leading to O(logN) for union(i, j)
		return parent[i];
	}
	public int removeStones(int[][] stones) {
		int n = stones.length;
		numSets = n;
		parent = new int[n];
		rank = new int[n];
		for (int i = 0; i < n; ++i) {
			parent[i] = i;
			rank[i] = 0;
		}
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) {
				if (stones[i][0] == stones[j][0] || stones[i][1] == stones[j][1])
					union(i, j);
			}
		}
		return n - numSets;
	}
	public static void main(String[] args) {
		int[][] stones = new int[][]{ {0,0}, {0,1}, {1,0}, {1,2}, {2,1}, {2,2} };
		int res = new MostStonesRemovedWithSameRowOrColumn().removeStones(stones);
		System.out.println(res);
	}
}
*/

import java.util.HashMap;

/**
 * Solution #2: union find on x, y axes
 *                   ------------
 *                  --------|---|
 *                  |---|--------
 *                  |---|---|---|
 *                  ------------
 */
public class MostStonesRemovedWithSameRowOrColumn {
	private HashMap<Integer, Integer> parent;
	private HashMap<Integer, Integer> rank;
	private int numSets;
	private void union(int i, int j) {
		int pi = parent.get(i), pj = parent.get(j);
		if (pi == pj) return;
		if (rank.get(pi) < rank.get(pj)) {
			parent.put(pi, pj);
		} else if (rank.get(pi) == rank.get(pj)) {
			parent.put(pi, pj);
			rank.put(pj, rank.get(pj)+1);
		} else {
			parent.put(pj, pi);
		}
		numSets--;
	}
	// find with path compression
	private int find(int i) {
		if (i == parent.get(i)) return i;
		int t = find(parent.get(i));
		parent.put(i, t);
		return t;
	}
	public int removeStones(int[][] stones) {
		// init parent, rank, sumSets
		parent = new HashMap<>();
		rank = new HashMap<>();
		for (int i = 0; i < stones.length; ++i) {
			int x = stones[i][0], y = stones[i][1] + 20000;
			parent.put(x, x);
			parent.put(y, y);
			rank.put(x, 0);
			rank.put(y, 0);
		}
		numSets = parent.size();
		System.out.println("numSets: " + numSets);
		// iterate over stones, union x, y
		for (int i = 0; i < stones.length; ++i) {
			int x = stones[i][0], y = stones[i][1] + 20000;
			union(x, y);
		}
		System.out.println("numSets: " + numSets);
		return stones.length - numSets;
	}
	public static void main(String[] args) {
		int[][] stones = new int[][]{ {0,0}, {0,2}, {1,1}, {2,0}, {2,2} };
		int res = new MostStonesRemovedWithSameRowOrColumn().removeStones(stones);
		System.out.println(res);
	}

}
