package fenwicktree;

/**
 * Fenwick Tree 2D
 */
public class RangeSumQuery2DMutable {
	// handle 0-indexed matrix
	public RangeSumQuery2DMutable(int[][] matrix) {
		build(matrix);
	}

	public void update(int row, int col, int val) {
		int diff=val-matrix[row][col];
		matrix[row][col]=val;
		adjust(row+1, col+1, diff);
	}

	public int sumRegion(int row1, int col1, int row2, int col2) {
		return rsq(row1+1, col1+1, row2+1, col2+1);
	}

	//-- build Fenwick Tree, 1-indexed
	private int[][] ft, matrix;
	private int rows, cols;

	// O(MNlogMlogN)
	private void build(int[][] matrix) {
		this.matrix=matrix; // don't forget this key word
		this.rows=matrix.length;
		this.cols=matrix[0].length;
		ft=new int[rows+1][cols+1];
		for (int i=1; i<rows+1; ++i) {
			for (int j=1; j<cols+1; ++j) {
				adjust(i, j, matrix[i-1][j-1]);
			}
		}
		// printMat(ft);
	}

	// O(logMlogN)
	private void adjust(int i, int j, int diff) {
		for (int r=i; r<rows+1; r+=lsOne(r)) {
			for (int c=j; c<cols+1; c+=lsOne(c)) {
				ft[r][c]+=diff;
			}
		}
	}

	// O(logMlogN)
	private int rsq(int i, int j) {
		int sum=0;
		for (int r=i; r>0; r-=lsOne(r)) {
			for (int c=j; c>0; c-=lsOne(c)) {
				sum+=ft[r][c];
			}
		}
		return sum;
	}

	// O(logMlogN)
	private int rsq(int i1, int j1, int i2, int j2) {
		return rsq(i2, j2)-rsq(i2,j1-1)-rsq(i1-1, j2)+rsq(i1-1, j1-1);
	}

	private int lsOne(int b) { return b&(-b); }

	//-- debug
	private void printMat(int[][] mat) {
		for (int i=0; i<mat.length; ++i) {
			for (int j=0; j<mat[0].length; ++j) {
				System.out.printf("%d ", mat[i][j]);
			}
			System.out.printf("\n");
		}
		System.out.printf("------");
	}

}
