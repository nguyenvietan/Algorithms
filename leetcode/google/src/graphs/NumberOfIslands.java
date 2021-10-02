package graphs;

import java.util.LinkedList;
import java.util.Queue;

public class NumberOfIslands {
    /**
     * Solution #1: DFS
     */
    private void dfs(char[][] grid, int r, int c) {
        // base cases
        int rows = grid.length, cols = grid[0].length;
        if (r < 0 || r >= rows || c < 0 || c >= cols || grid[r][c] == '0') {
            return;
        }
        grid[r][c] = '0';
        // recursion
        dfs(grid, r - 1, c);
        dfs(grid, r + 1, c);
        dfs(grid, r, c - 1);
        dfs(grid, r, c + 1);
    }

    /**
     * Solution #2: BFS
     */
    private void bfs(char[][] grid, int r, int c) {
        int rows = grid.length, cols = grid[0].length;
        Queue<Pair<Integer, Integer>> q = new LinkedList<>();
        q.add(new Pair<>(r, c));
        grid[r][c] = '0';
        while (!q.isEmpty()) {
            Pair<Integer, Integer> p = q.poll();
            int rr = p.first, cc = p.second;
            if (rr-1 >= 0 && grid[rr-1][cc] == '1') { q.add(new Pair<>(rr-1, cc)); grid[rr-1][cc] = '0'; }
            if (rr+1 < rows && grid[rr+1][cc] == '1') { q.add(new Pair<>(rr+1, cc)); grid[rr+1][cc] = '0'; }
            if (cc-1 >= 0 && grid[rr][cc-1] == '1') { q.add(new Pair<>(rr, cc-1)); grid[rr][cc-1] = '0'; }
            if (cc+1 < cols && grid[rr][cc+1] == '1') { q.add(new Pair<>(rr, cc+1)); grid[rr][cc+1] = '0'; }
        }
    }

    /**
     * TODO: Solution #3: UNION FIND (Disjoint Set)
     */
    private void unionFind(char[][] grid, int r, int c) {
        // int rows = grid.length, cols = grid[0].length;
    }

    public int numIslands ( char[][] grid) {
        int res = 0, rows = grid.length, cols = grid[0].length;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; ++c) {
                if (grid[r][c] == '1') {
                    res++;
                    // dfs(grid, r, c);
                    bfs(grid, r, c);
                }
            }
        }
        return res;
    }

    private class Pair<K, V> {
        private K first;
        private V second;
        public Pair(K first, V second) {
            this.first = first;
            this.second = second;
        }
    }

    public static void main (String[] args) {
        char[][] grid = new char[][]{
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '0', '1'},
                {'1', '1', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };
        NumberOfIslands numberOfIslands = new NumberOfIslands();
        int res = numberOfIslands.numIslands(grid);
        System.out.println(res);
    }

}
