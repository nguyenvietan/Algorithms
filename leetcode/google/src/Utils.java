import java.util.Comparator;
import java.util.Scanner;

public class Utils {

	public static void printMatrix(int[][] matrix) {
		int n = matrix.length;
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) {
				System.out.printf("%4d ", matrix[i][j]);
			}
			System.out.println();
		}
		System.out.println("---");
	}

	public static class Pair<K, V> {
		private K key;
		private V val;
		public Pair(K key, V val) {
			this.key = key;
			this.val = val;
		}
		public K getKey() { return key; }
		public V getValue() { return val; }
	}

	public static void printArray(int[] a, int l, int r) {
		System.out.printf("%d", a[l]);
		for (int i = l+1; i <= r; ++i) System.out.printf(" %d", a[i]);
		System.out.println();
	}

	public static void printArray(int[] a) {
		printArray(a, 0, a.length-1);
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String s;
		while (!input.hasNextInt()) {
			s = input.nextLine();
			System.out.println("invalid integer. please enter again.");
		}
		int i = input.nextInt();
		System.out.println(i);
	}

}
