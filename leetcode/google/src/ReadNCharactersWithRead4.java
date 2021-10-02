/**
 * The read4 API is defined in the parent class Reader4.
 *     int read4(char[] buf4); 
 */

/**
 * TODO: review
 */

//public class ReadNCharactersWithRead4 extends Reader4 {
//	/**
//	 * @param buf Destination buffer
//	 * @param n   Number of characters to read
//	 * @return    The number of actual characters read
//	 */
//
//	char[] buf4 = new char[4];
//	int i, nRead4;
//
//	public int read(char[] buf, int n) {
//		int cur = 0;
//		while (cur < n) {
//			// check if there some chars remaining in buf4
//			if (i == 0) nRead4 = read4(buf4);
//			if (nRead4 == 0) break;
//			while (cur < n && i < nRead4) buf[cur++] = buf4[i++];
//			if (i >= nRead4) i = 0;
//		}
//		return cur;
//	}
//}
