package sortingandsearching;


class MergeSort {

	public void mergeSort(int[] a, int l, int r) {
		if (l == r) return;
		int m = (l+r)/2;
		mergeSort(a, l, m);
		mergeSort(a, m+1, r);
		merge(a, l, m, r); // O(n) space, a[l,m] and a[m+1,r] are sorted now, so merge them here. it's not recursion
	}

	private void merge(int[] a, int l, int m, int r) {
		if (l == r) return;
		int[] tmp = new int[r-l+1];
		int i = l, j = m + 1, idx = 0;
		while (i <= m && j <= r) {
			if (a[i] < a[j]) tmp[idx++] = a[i++];
			else tmp[idx++] = a[j++];
		}
		while (i <= m) { tmp[idx++] = a[i++]; }
		while (j <= r) { tmp[idx++] = a[j++]; }
		for (int x = 0; x <= r-l; ++x) a[x+l] = tmp[x]; // be careful here, a[l,r] and tmp[0,r-l] have different indices
	}

	public static void main(String[] args) {
		int[] a = new int[]{ 7, 2, 5, 4, 1, 6, 1, 0, 0, -7 };
		new MergeSort().mergeSort(a, 0, a.length-1);
		for (int i = 0; i < a.length; ++i) {
			System.out.printf("%d ", a[i]);
		}
		System.out.println();
	}

}




