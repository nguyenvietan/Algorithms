package graphs;

import java.util.Stack;

/**
 * Solution #1: stack
 */
/*
public class DecodeString {

	public String decodeString(String s) {
		Stack<Character> stack =  new Stack<>();
		for (int i = 0; i < s.length(); ++i) {
			char c = s.charAt(i);
			if (c != ']') {
				stack.push(c);
			} else {
				// pop all chars, ended by [
				StringBuilder sbChars = new StringBuilder();
				while (!stack.isEmpty()) {
					char x = stack.pop();
					if (x != '[') sbChars.append(x);
					else break;
				}
				// pop all digits
				StringBuilder sbDigits = new StringBuilder();
				while (!stack.isEmpty() && isDigit(stack.peek())) {
					sbDigits.append(stack.pop());
				}
				// replicate strings
				StringBuilder sb = new StringBuilder();
				int n = Integer.parseInt(sbDigits.reverse().toString());
				String seqChars = sbChars.reverse().toString();
				while (n-- > 0) {
					for (int j = 0; j < seqChars.length(); ++j) {
						sb.append(seqChars.charAt(j));
					}
				}
				// push the concatenated string to stack, char-by-char
				String sbString = sb.toString();
				for (int t = 0; t < sbString.length(); ++t) {
					stack.push(sbString.charAt(t));
				}
			}
		}
		// pop out all chars in the stack, reverse string
		StringBuilder sbResult = new StringBuilder();
		while (!stack.isEmpty()) sbResult.append(stack.pop());
		// return
		return sbResult.reverse().toString();
	}

	private boolean isDigit(char c) {
		return '0' <= c && c <= '9';
	}

	public static void main(String[] args) {
		DecodeString decoder = new DecodeString();
		String s = "3[a2[c]]";
//		String s = "abc";
//		String s = "abc2[p2[x]]";
//		String s = "2[abc]3[xxx]";
//		String s = "1[aaa2[b3[c]]]";
		System.out.println(s);
		String res = decoder.decodeString(s);
		System.out.println(res);
	}

}
*/

/**
 * Solution  #2: recursion
 */
public class DecodeString {

	public String decodeString(String s) {
		StringBuilder res = new StringBuilder();
		int i = 0, len = s.length();
		while (i < len) {
			char c = s.charAt(i);
			if (!isDigit(c)) {
				res.append(c);
			} else {
				// parse number
				int n = c - '0';
				i++;
				while (i < len && isDigit(s.charAt(i))) {
					n = 10 * n + s.charAt(i) - '0';
					i++;
				}
				// now s.charAt(i) == '['
				// find the index j: s.charAt(j) == ']', the corresponding closing bracket
				int cnt = 0, j = i;
				while (j < len) {
					if (s.charAt(j) == '[') cnt++;
					if (s.charAt(j) == ']') cnt--;
					if (cnt == 0) break;
					j++;
				}
				String nested = s.substring(i+1, j);
				// System.out.printf("nested = %s, i+1 = %d, j = %d\n", nested, i+1, j);
				String ss = decodeString(nested); // nested string to decode
				// replicate strings
				while (n-- > 0) res.append(ss);
				i = j;
			}
			++i;
		}
		return res.toString();
	}
	private boolean isDigit(char c) {
		return '0' <= c && c <= '9';
	}

	public static void main(String[] args) {
		DecodeString decoder = new DecodeString();
		String s = "3[a2[c]]";
		System.out.println("Input: " + s);
		String res = decoder.decodeString(s);
		System.out.println(res);
	}

}
