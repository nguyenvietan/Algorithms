import java.util.Arrays;
import java.util.HashSet;
import java.util.TreeSet;

/**
 * Solution #1: Brute-force -> not efficient
 */
/*
public class NextClosestTime {

	public static int toMins(char[] time) {
		if (time.length != 4) {
			System.err.println("Error: time must have length = 4.");
			return -1;
		}
		int c3 = time[3] - '0', c2 = time[2] - '0', c1 = time[1] - '0', c0 = time[0] - '0';
		int hours = 10 * c0 + c1, mins = 10 * c2 + c3;
		if (hours > 23 || mins > 59) return -1;
		return mins + 60 * hours;
	}

	public static int toMins(String time) {
		String[] s = time.split(":");
		return Integer.parseInt(s[0]) * 60 + Integer.parseInt(s[1]);
	}

	public static String nextClosestTime(String time) {
		HashSet<Character> s = new HashSet<Character>();
		char[] t = new char[4], res = new char[5];
		char[] timeChars = time.toCharArray();
		for (char c : timeChars) {
			if (c != ':') s.add(c);
		}
		if (s.size() == 1) return time;
		int minDist = Integer.MAX_VALUE;
		int timeInMins = toMins(time);
		for (char c3 : s) {
			for (char c2 : s) {
				for (char c1 : s) {
					for (char c0 : s) {
						t[3] = c3; t[2] = c2; t[1] = c1; t[0] = c0;
						int curMins = toMins(t);
						if (curMins < 0 || curMins == timeInMins) continue;
						int dist = curMins > timeInMins ? (curMins-timeInMins) : (24*60-timeInMins+curMins);
						if (dist < minDist) {
							res[0] = c0; res[1] = c1; res[2] = ':'; res[3] = c2; res[4] = c3;
							minDist = dist;
						}
					}
				}
			}
		}
		return String.valueOf(res);
	}

	public static void main(String[] args) {
		String time = "22:23";
		String res = nextClosestTime(time);
		System.out.println(res);
	}

}
*/

/**
 * Solution #2:
 * 22:23 -> 22:32
 * 22:32 -> __:_3
 * 22:29 -> 22:22
 * 22:39 -> 23:22
 * 22:59 -> 22:22
 * 12:59 -> 21:11
 * 23:55 -> 25:22 (invalid) => condition for upperbound
 *       -> 22:22 (ok)
 * 13:55 -> __:11
 * 13:11 ->
 */
/*
public class NextClosestTime {

	public static String nextClosestTime(String time) {
		// convert to array, then sort
		char[] res = time.toCharArray();
		char[] digits = {res[0], res[1], res[3], res[4]};
		Arrays.sort(digits);
		// select res[4], res[3], res[2] = ':', res[1], res[0]
		res[4] = getUpperOrLowest(res[4], digits, '9');
		if (res[4] > time.charAt(4)) return String.valueOf(res);

		res[3] = getUpperOrLowest(res[3], digits, '5');
		if (res[3] > time.charAt(3)) return String.valueOf(res);

		// this is quite a tricky point
		res[1] = res[0] == '2' ? getUpperOrLowest(res[1], digits, '3')
								: getUpperOrLowest(res[1], digits, '9');
		if (res[1] > time.charAt(1)) return String.valueOf(res);

		res[0] = getUpperOrLowest(res[0], digits, '2');
		return String.valueOf(res);
	}

	public static char getUpperOrLowest(char c, char[] digits, char upperLimit) {
		for (char x : digits) {
			if (x != ':' && x > c && x <= upperLimit) return x; // return the upper bound of c
		}
		return digits[0]; // otherwise, return the smallest value, assuming digits[] is already sorted
	}

	public static void main(String[] args) {
		String time = "06:49";
		String res = nextClosestTime(time);
		System.out.println(res);
	}

}
*/

/**
 * Solution #3: similar to Solution #2, but employ TreeSet
 */
public class NextClosestTime {

	public static String nextClosestTime(String time) {
		char[] res = time.toCharArray();
		Character[] digits = new Character[]{res[0], res[1], res[3], res[4]};
		TreeSet<Character> set = new TreeSet<Character>(Arrays.asList(digits));

		res[4] = getNext(set, res[4], '9');
		if (res[4] > time.charAt(4)) return String.valueOf(res);

		res[3] = getNext(set, res[3], '5');
		if (res[3] > time.charAt(3)) return String.valueOf(res);

		res[1] = getNext(set, res[1], res[0] == '2' ? '3' : '9');
		if (res[1] > time.charAt(1)) return String.valueOf(res);

		res[3] = getNext(set, res[3], '2');
		return String.valueOf(res);
	}

	public static char getNext(TreeSet<Character> set, char c, char upperLimit) {
		Character n = set.higher(c);
		return n == null || n > upperLimit ? set.first() : n;
	}

	public static void main(String[] args) {
		String time = "06:49";
		String res = nextClosestTime(time);
		System.out.println(res);
	}

}




















