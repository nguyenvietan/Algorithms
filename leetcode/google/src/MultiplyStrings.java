public class MultiplyStrings {

	public String multiplyString(String num1, String num2) {
		int n = num1.length(), m = num2.length();
		int[] vals = new int[m+n];
		for (int i = n-1; i >= 0; --i) {
			for (int j = m-1; j >= 0; --j) {
				vals[i+j+1] += (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
				vals[i+j] += vals[i+j+1] / 10;
				vals[i+j+1] %= 10;
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n+m; ++i) {
			if (sb.length() == 0 && vals[i] == 0) continue;
			sb.append(vals[i]);
		}
		return sb.length() == 0 ? "0" : sb.toString();
	}

	public static void main(String[] args) {
		String num1 = "123", num2 = "456";
		MultiplyStrings obj = new MultiplyStrings();
		System.out.println(obj.multiplyString(num1, num2));
	}

}
