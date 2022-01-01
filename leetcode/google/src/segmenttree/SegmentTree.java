package segmenttree;

/*
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
*/

/*
	int[] arr: 0-indexed
	int[] st: 1-indexed
	left: 2*p
	right: 2*p+1
	update: top-down recursion
*/
/*
public class SegmentTree {
	private int[] st, arr;
	private int n;
	public SegmentTree(int[] arr) {
		this.arr = arr;
		this.n = arr.length;
		this.st = new int[4*n]; // keep in mind
		build(1, 0, n-1);
	}
	private void build(int p, int l, int r) {
		if (l == r) st[p] = l;
		else {
			int m = (l + r)/2;
			build(2*p, l, m);
			build(2*p+1, m+1, r);
			int left = st[2*p];
			int right = st[2*p+1];
			st[p] = (arr[left] <= arr[right]) ? left : right;
		}
	}
	// return the index of the minimum element of arr[] in range [i,j]
	public int rmq(int i, int j) {
		return rmq0(1, 0, n-1, i, j);
	}
	private int rmq0(int p, int l, int r, int i, int j) {
		if (l>j || r<i) return -1;
		if (i<=l && r<=j) return st[p];
		int m =(l+r)/2;
		int p1 = rmq0(2*p, l, m, i, j);
		int p2 = rmq0(2*p+1, m+1, r, i, j);
		if (p1 == -1) return p2;
		if (p2 == -1) return p1;
		return arr[p1] <= arr[p2] ? p1 : p2;
	}
	public void update(int i, int val) {
		update0(1, 0, n-1, i, val);
	}
	private void update0(int p, int l, int r, int i, int val) {
		if (l>i || r<i) return;
		if (l == r) arr[i] = val;
		else {
			int m = (l+r)/2;
			update0(2*p, l, m, i, val);
			update0(2*p+1, m+1, r, i, val);
			int p1 = st[2*p];
			int p2 = st[2*p+1];
			st[p] = arr[p1] <= arr[p2] ? p1 : p2;
		}
	}
	public static void main(String[] args) {
		int[] arr = new int[]{18, 17, 13, 19, 15, 11, 20};
		SegmentTree st = new SegmentTree(arr);
		System.out.println(st.rmq(0, 6));
		st.update(5, 99);
		System.out.println(st.rmq(0, 6));
		st.update(0, -1);
		System.out.println(st.rmq(0, 6));
		System.out.println(st.rmq(3, 5));
		st.update(4, -9);
		System.out.println(st.rmq(0, 6));

	}
}
*/

// Range Sum Query (RSQ)
// left: 2*p
// right: 2*p+1
public class SegmentTree {
	private int[] st, arr;
	private int n;
	public SegmentTree(int[] arr) {
		this.arr = arr;
		this.n = arr.length;
		this.st = new int[4*n]; // keep in mind
		build(1, 0, n-1);
	}
	private void build(int p, int l, int r) {
		if (l == r) st[p] = arr[l];
		else {
			int m = (l + r)/2;
			build(2*p, l, m);
			build(2*p+1, m+1, r);
			st[p] = st[2*p] + st[2*p+1];
		}
	}
	// return the sum of arr[i]+arr[i+1]+...+arr[j]
	public int rsq(int i, int j) {
		return rsq0(1, 0, n-1, i, j);
	}
	private int rsq0(int p, int l, int r, int i, int j) {
		if (l>j || r<i) return 0;
		if (i<=l && r<=j) return st[p];
		int m =(l+r)/2;
		int left = rsq0(2*p, l, m, i, j);
		int right = rsq0(2*p+1, m+1, r, i, j);
		return left + right;
	}
	public void update(int i, int val) {
		update0(1, 0, n-1, i, val);
	}
	private void update0(int p, int l, int r, int i, int val) {
		if (l>i || r<i) return;
		if (l == r) {
			arr[i] += val; // keep in mind: only plus the delta
			st[p] += val; // keep in mind: only plus the delta
		}
		else {
			int m = (l+r)/2;
			update0(2*p, l, m, i, val);
			update0(2*p+1, m+1, r, i, val);
			st[p] = st[2*p] + st[2*p+1];
		}
	}
	public static void main(String[] args) {
		int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7};
		SegmentTree st = new SegmentTree(arr);
		System.out.println(st.rsq(0, 6));
		st.update(5, 1);
		System.out.println(st.rsq(0, 6));
		st.update(0, -10);
		System.out.println(st.rsq(0, 6));
		st.update(5, 100);
		System.out.println(st.rsq(0, 6));
	}
}
