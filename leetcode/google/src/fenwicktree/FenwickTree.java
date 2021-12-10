package fenwicktree;

public class FenwickTree {
	private static int[] ft;
	private int n;
	public FenwickTree(int n) {
		this.n = n;
		this.ft = new int[n];
	}
	public int rsq(int b) {
		int sum = 0;
		while (b > 0) {
			sum += ft[b];
			b -= b&(-b);
		}
		return sum;
	}
	public int rsq(int a, int b) {
		// return a == 1 ? rsq(b) : rsq(b) - rsq(a-1);
		return rsq(b) - rsq(a-1);
	}
	public void adjust(int k, int v) {
		while (k < n) {
			ft[k] += v;
			k += lsOne(k);
		}
	}
	private int lsOne(int b) { return b&(-b); }
	private void printArr() {
		for (int x : ft) System.out.printf("%d ", x);
		System.out.println();
	}
	public static void main(String[] args) {
		int[] a = new int[]{2,4,5,5,6,6,6,7,7,8,9};
		int n = 10; // 0 <= a[i] <= 10
		FenwickTree fenwickTree = new FenwickTree(n+1);
		for (int i=0; i<a.length; ++i) { fenwickTree.adjust(a[i], 1);}
		fenwickTree.printArr();
		System.out.printf("rsq(1, 1) = %d\n", fenwickTree.rsq(1, 1));
		System.out.printf("rsq(1, 2) = %d\n", fenwickTree.rsq(1, 2));
		System.out.printf("rsq(1, 6) = %d\n", fenwickTree.rsq(1, 6));
		System.out.printf("rsq(1, 10) = %d\n", fenwickTree.rsq(1, 10));
		System.out.printf("rsq(3, 6) = %d\n", fenwickTree.rsq(3, 6));
		fenwickTree.adjust(5, 2);
		System.out.printf("rsq(1, 10) = %d\n", fenwickTree.rsq(1, 10));
	}
}
