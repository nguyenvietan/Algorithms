import java.util.Stack;

public class ValidParentheses {

	public static boolean isValid(String s) {
		Stack<Character> stack = new Stack<Character>();
		for (int i = 0; i < s.length(); ++i) {
			char c = s.charAt(i);
			if (isOpening(c)) {
				stack.push(c);
			} else {
				if (stack.isEmpty() || isClosing(stack.peek())) return false;
				char o = stack.pop();
				if (!isPair(o, c)) return false;
			}
		}
		return stack.isEmpty();
	}

	public static boolean isOpening(char c) {
		return c == '{' || c == '[' || c == '(';
	}

	public static boolean isClosing(char c) {
		return c == '}' || c == ']' || c == ')';
	}

	public static boolean isPair(char o, char c) {
		return (o == '(' && c == ')') || (o == '[' && c == ']') || (o == '{' && c == '}');
	}

	public static void main(String[] args) {
		System.out.println(isValid("(){[]}"));
	}

}
