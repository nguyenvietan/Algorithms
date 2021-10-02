public class LicenseKeyFormatting {

	public String licenseKeyFormatting(String s, int k) {
		StringBuilder sb = new StringBuilder(2 * s.length());
		int cnt = k;
		boolean first = true;
		for (int i = s.length() - 1; i >= 0; --i) {
			char c = s.charAt(i);
			if (c == '-') continue;
			if (!first && cnt == k) sb.append('-') ;
			sb.append(Character.toUpperCase(c));
			cnt--;
			if (cnt == 0) { cnt = k; first = false;}
		}
		return sb.reverse().toString();
	}

	public static void main(String[] args) {
		LicenseKeyFormatting object = new LicenseKeyFormatting();
		String s = "--5F3Z-2e-9-w--";
		int k = 4;
		String res = object.licenseKeyFormatting(s, k);
		System.out.println(res);
	}

}
