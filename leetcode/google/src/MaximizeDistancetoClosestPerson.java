public class MaximizeDistancetoClosestPerson {

	public static int maxDistToClosest(int[] seats) {
		int n = seats.length, be = 0, en = seats.length - 1, res = 0;
		while (be < n && seats[be] == 0) be++;
		while (en >= 0 && seats[en] == 0) en--;
		res = Math.max(res, be);
		res = Math.max(res, n - 1 - en);
		int nZero = 0;
		for (int i = be + 1; i < en; ++i) {
			nZero = seats[i] == 0 ? nZero + 1 : 0;
			res = Math.max(res, (nZero + 1)/2);
		}
		return res;
	}

	public static void main(String[] args) {
		int[] seats = {0, 0, 0, 0, 1, 0, 1};
		int res = maxDistToClosest(seats);
		System.out.println(res);
	}

}
