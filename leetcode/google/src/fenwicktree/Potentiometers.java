// UVA - 12086: https://vjudge.net/problem/UVA-12086
package fenwicktree;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Potentiometers { // change class name to Main for Uva submission

	private static class FenwickTree {
		private int[] ft;
		private int n;
		public FenwickTree(int[] a) {
			this.n = a.length;
			this.ft = new int[n];
			for (int i=1; i<n; ++i) adjust(i, a[i]);
		}
		public int rsq(int b) {
			int sum = 0; // change to sum = a[0] if the input array is 0-indexed
			while (b > 0) { sum += ft[b]; b -= lsOne(b); }
			return sum;
		}
		public int rsq(int a, int b) {
			return rsq(b) - rsq(a-1);
		}
		public void adjust(int k, int v) {
			while (k < n) { ft[k] += v; k += lsOne(k); }
		}
		private int lsOne(int b) { return b&(-b); }
	}
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		// BufferedReader in = new BufferedReader(new FileReader("/home/annv/git/algo/leetcode/google/src/fenwicktree/input.txt"));
		int n, t = 0;
		while ((n = Integer.parseInt(in.readLine())) != 0) {
			StringBuilder sb = new StringBuilder();
			if (t > 0) sb.append("\n");
			sb.append("Case ").append(++t).append(":\n");
			int[] a = new int[n+1];
			for (int i=1; i<=n; ++i) a[i] = Integer.parseInt(in.readLine());
			FenwickTree ft = new FenwickTree(a);
			String line;
			while (true) {
				line = in.readLine();
				if (line.equals("END")) {
					System.out.print(sb.toString());
					break;
				}
				String[] s = line.split("\\s+"); // keep in mind, greedily removing white spaces
				int u = Integer.parseInt(s[1]);
				int v = Integer.parseInt(s[2]);
				if (s[0].equals("M")) {
					sb.append(ft.rsq(u, v)).append("\n");
				} else if (s[0].equals("S")) {
					int d = v - a[u];
					a[u] = v;
					ft.adjust(u, d);
				}
			}
		}
	}

}

/*
3
100
100
100
M 1 1
M 1 3
S 2 200
M 1 2
S 3 0
M 2 3
END
10
1
2
3
4
5
6
7
8
9
10
M 1 10
END
0
 */
