package sortingandsearching;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class CountOfSmallerNumbersAfterSelf {

	private int offset = 10000;
	private int[] record = new int[20005];

	public List<Integer> countSmaller(int[] nums) {
		int[] a = Arrays.copyOf(nums, nums.length);
		mergeSort(a, 0, a.length-1);
		List<Integer> res = new ArrayList<>();
		for (int i = 0; i < nums.length; ++i) {
			res.add(record[nums[i]+offset]);
		}
		printArray(a);
		return res;
	}

	private void mergeSort(int[] a, int l, int r) {
		if (l == r) return;
		int m = (l+r)/2;
		mergeSort(a, l, m);
		mergeSort(a, m+1, r);
		merge(a, l, m, r);
	}

	private void merge(int[] a, int l, int m, int r) {
		int[] tmp = new int[r-l+1];
		int i = l, j = m+1, idx = 0;
		while (i <= m && j <= r) {
			if (a[i] <= a[j]) {
				record[a[i]+offset] += j-(m+1);
				tmp[idx++] = a[i++];
			} else {
				tmp[idx++] = a[j++];
			}
		}
		while (i <= m) {
			record[a[i]+offset] += j-(m+1);
			tmp[idx++] = a[i++];
		}
		while (j <= r) { tmp[idx++] = a[j++]; }
		for (int x = 0; x <= r-l; ++x) { a[x+l] = tmp[x]; }
	}

	public static void printArray(int[] a) {
		for (int i = 0; i < a.length; ++i) {
			System.out.printf("%d ", a[i]);
		}
		System.out.println();
	}
	public static void printArray(int[] a, int l, int r) {
		for (int i = l; i <= r; ++i) {
			System.out.printf("%d ", a[i]);
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int[] a = new int[]{ 5, 3, 3, 2, 3};
//		int[] a = new int[]{26,78,27,100,33,67,90,23,66,5,38,7,35,23,52,22,83,51,98,69,81,32,78,28,94,13,2,97,3,76,99,51,9,21,84,66,65,36,100,41};
		printArray(a);
		List<Integer> res = new CountOfSmallerNumbersAfterSelf().countSmaller(a);
		for (int x : res) {
			System.out.printf("%d ", x);
		}
		System.out.println();
	}
}



//package sortingandsearching;
//
//import java.util.Arrays;
//import java.util.List;
//
//public class CountOfSmallerNumbersAfterSelf {
//
//	private static int offset = 10000;
//	private static int[] res, record;
//
//	public List<Integer> countSmaller(int[] nums) {
//
//	}
//
//	public static void mergeSort(int[] a, int l, int r) {
//		if (l == r) return;
//		int m = (l+r)/2;
//		mergeSort(a, l, m);
//		mergeSort(a, m+1, r);
//		merge(a, l, m, r); // O(n) space, a[l,m] and a[m+1,r] are sorted now, so merge them here. it's not recursion
//	}
//
//	private static void merge(int[] a, int l, int m, int r) {
//		if (l == r) return;
//		int[] tmp = new int[r-l+1];
//		int i = l, j = m + 1, idx = 0;
//		while (i <= m && j <= r) {
//			if (a[i] <= a[j]) {
//				record[a[i]+offset] += j-(m+1);
//				tmp[idx++] = a[i++];
//			} else {
//				tmp[idx++] = a[j++];
//			}
//		}
//		while (i <= m) {
//			record[a[i]+offset] += j-(m+1);
//			tmp[idx++] = a[i++];
//		}
//		while (j <= r) { tmp[idx++] = a[j++]; }
//		for (int x = 0; x <= r-l; ++x) a[x+l] = tmp[x]; // be careful here, a[l,r] and tmp[0,r-l] have different indices
//	}
//
//	public static void printArray(int[] a) {
//		for (int i = 0; i < a.length; ++i) {
//			System.out.printf("%d ", a[i]);
//		}
//		System.out.println();
//	}
//
//	public static void main(String[] args) {
//
//		int[] a = new int[]{7, 2, 5, 4, 1, 6};
//		int[] b = Arrays.copyOf(a, a.length);
//		res = new int[a.length];
//		record = new int[20005];
//
//		printArray(a);
//		mergeSort(a, 0, a.length-1);
//		// printArray(a);
//
//		for (int i = 0; i < b.length; ++i) {
//			res[i] = record[b[i]+offset];
//		}
//		printArray(res);
//	}
//
//}
