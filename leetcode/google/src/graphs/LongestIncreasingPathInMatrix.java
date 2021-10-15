package graphs;

public class LongestIncreasingPathInMatrix {
	private static final int[][] directions = new int[][]{ {-1, 0}, {1, 0}, {0, -1}, {0, 1} } ;
	private int rows, cols;
	private int res;
	private int[][] dp;

	private int dfs(int[][] m, int r, int c) {
		if (dp[r][c] > 0) return dp[r][c];
		int len = 0;
		for (int[] d : directions) {
			int rr = r + d[0], cc = c + d[1];
			if (0 <= rr && rr < rows && 0 <= cc && cc < cols && m[rr][cc] > m[r][c])
				len = Math.max(len, dfs(m, rr, cc));
		}
		return dp[r][c] = len + 1;
	}

	public int longestIncreasingPath(int[][] matrix) {
		rows = matrix.length;
		cols = matrix[0].length;
		res = 0;
		dp = new int[rows][cols];
		for (int i = 0; i < rows; ++i) {
			for (int j = 0; j < cols; ++j) {
				res = Math.max(res, dfs(matrix, i, j));
			}
		}
		return res;
	}

	public static void main(String[] args) {
		int[][] matrix = new int[][]{ {9, 9, 4}, {6, 6, 8}, {2, 1, 1} };
		LongestIncreasingPathInMatrix obj = new LongestIncreasingPathInMatrix();
		int res = obj.longestIncreasingPath(matrix);
		System.out.println(res);
	}
}
