/**
 * Rotate image
 *
 * Solution 1: + (*) rotate the group of 4 cells
 *                      (i, j)      ->  (j, n-1-i)
 *                          |                 |
 *                      (n-1-j, i)  <-  (n-i-i, n-1-j)
 *              + divide the matrix into 4 quarters, apply the rotation (*) to the quarter I
 *
 * Solution 2: rotate = transpose + reverse
 *          transpose: reverse the matrix around the main diagonal
 *          reverse: left -> right
 */

public class RotateImage {
	// Solution 1: rotate the group of 4 cells
	public static void rotate(int[][] matrix) {
		int n = matrix.length;
		for (int i = 0; i < (n+1)/2; ++i) {
			for (int j = 0; j < n/2; ++j) {
				// rotate: (i, j) -> (j, n-1-i) -> (n-1-i, n-1-j) -> (n-1-j, i) -> (i, j)
				int tmp = matrix[i][j];
				matrix[i][j] = matrix[n-1-j][i];
				matrix[n-1-j][i] = matrix[n-1-i][n-1-j];
				matrix[n-1-i][n-1-j] = matrix[j][n-1-i];
				matrix[j][n-1-i] = tmp;
			}
		}
	}

	// Solution 2: transpose + reverse
	public static void rotate2(int[][] matrix) {
		transpose(matrix);
		reverse(matrix);
	}

	public static void transpose(int[][] matrix) {
		int n = matrix.length;
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < i; ++j) {
				int tmp = matrix[i][j];
				matrix[i][j] = matrix[j][i];
				matrix[j][i] = tmp;
			}
		}
	}

	public static void reverse(int matrix[][]) {
		int n = matrix.length;
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n/2; ++j) {
				int tmp = matrix[i][j];
				matrix[i][j] = matrix[i][n-1-j];
				matrix[i][n-1-j] = tmp;
			}
		}
	}

	public static void main(String[] args) {
		// int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
		int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
		Utils.printMatrix(matrix);
		rotate2(matrix);
		Utils.printMatrix(matrix);
	}

}
