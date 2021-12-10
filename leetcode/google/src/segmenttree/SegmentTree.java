package segmenttree;

public class SegmentTree {

	private int n;
	private int[] st, a;

	public SegmentTree(int[] a) {
		this.a = a;
		this.n = a.length;
		this.st = new int[4*n]; // ??
		build(1, 0, n-1);
	}

	public int rmq(int i, int j) {
		return rmq0(1, 0, n-1, i, j);
	}

	private void build(int p, int L, int R) {
		if (L == R) { st[p] = L; return; }
		int m = (L+R)/2;
		build(left(p), L, m);
		build(right(p), m+1, R);
		int p1 = st[left(p)], p2 = st[right(p)];
		st[p] = (a[p1] <= a[p2]) ? p1 : p2;
	}

	private int rmq0(int p, int L, int R, int i, int j) {
		if (i == L && j == R) return st[p];
		int m = (L+R)/2;
		if (m < i) return rmq0(right(p), m+1, R, i, j);
		if (m >= j) return rmq0(left(p), L, m, i, j);
		int p1 = rmq0(left(p), L, m, i, m);
		int p2 = rmq0(right(p), m+1, R, m+1, j);
		return a[p1] <= a[p2] ? p1 : p2;
	}

	private int left(int p) { return 2*p; }
	private int right(int p) { return 2*p+1; }

	public static void main(String[] args) {
		int[] a = new int[]{18, 17, 13, 19, 15, 11, 20};
		SegmentTree obj = new SegmentTree(a);
		System.out.println(obj.rmq(1, 3));
		System.out.println(obj.rmq(4, 6));
	}

}

