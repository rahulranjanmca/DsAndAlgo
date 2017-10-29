package zcom.rahul.ranjan.hackerEarth;

import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;
import java.util.TreeMap;

public class EscapeTheNight {
	private static MyInputStreamReader in;
	private static int MODULUS = 1000000000 + 7;
	static TreeMap<Long, Long> sum = new TreeMap<>();

	static void printTwoSetBitNums() {

		long x = 1;

		long currentMax = 0l;
		long currentTotal = 0;
		outer: while (true) {
			long y = 0;
			while (y < x) {
				long currentNumber = ((1 << x) + (1 << y));
				if (currentNumber <= currentMax) {
					currentTotal = (currentTotal + currentNumber) % MODULUS;
				}
				if (currentNumber > currentMax) {
					if (currentMax != 0) {
						sum.put(currentMax, currentTotal);
					}
					currentTotal = (currentTotal + currentNumber) % MODULUS;
					Long max = sum.higherKey(currentMax);
					if (max == null)
						break outer;
					else {
						currentMax = max;
					}
				}
				y++;
			}
			x++;
		}

	}

	static void printTwoSetBitNums(int n) {
		// Initialize higher of two sets bits
		int x = 1;

		// Keep reducing n for every number
		// with two set bits
		while (n > 0) {
			// Consider all lower set bits for
			// current higher set bit
			int y = 0;
			while (y < x) {
				// Print current number
				System.out.print(((1 << x) + (1 << y)) + " ");

				// If we have found n numbers
				n--;
				if (n == 0)
					return;

				// Consider next lower bit for current
				// higher bit.
				y++;
			}

			// Increment higher set bit
			x++;
		}
	}

	public static void main(String[] args) throws Exception {

		InputStream is = EscapeTheNight.class.getResourceAsStream(EscapeTheNight.class.getSimpleName() + ".txt");
		if (is == null) {
			in = new MyInputStreamReader(System.in);
		} else {
			in = new MyInputStreamReader(is);
		}
		int T = in.readInt();
		long array[] = new long[T];
		for (int i = 0; i < T; i++) {
			long value = in.readLong();
			sum.put(value, 0l);
			array[i] = value;
		}
		printTwoSetBitNums();
		for (int i = 0; i < T; i++) {
			System.out.println(sum.get(array[i]));
		}
		

	}

	public static class MyInputStreamReader {
		private byte[] buf = new byte[1 << 8];
		private int curChar;
		private int charsRead;
		private InputStream stream;

		public MyInputStreamReader(InputStream stream) {
			this.stream = stream;
		}

		public int readInt() throws IOException {
			int c = read();
			while (skip(c))
				c = read();
			int sgn = 1;
			if (c == '-') {
				sgn = -1;
				c = read();
			}
			int res = 0;
			do {
				if (c < '0' || c > '9')
					throw new InputMismatchException();
				res *= 10;
				res += c - '0';
				c = read();
			} while (!skip(c));
			return res * sgn;
		}

		public long readLong() throws IOException {
			int c = read();
			while (skip(c))
				c = read();
			int sign = 1;
			if (c == '-') {
				sign = -1;
				c = read();
			}
			long res = 0;
			do {
				if (c < '0' || c > '9')
					throw new InputMismatchException();
				res *= 10;
				res += c - '0';
				c = read();
			} while (!skip(c));
			return res * sign;
		}

		private int read() throws IOException {
			if (charsRead == -1)
				throw new InputMismatchException();
			if (curChar >= charsRead) {
				curChar = 0;
				charsRead = stream.read(buf);
				if (charsRead <= 0)
					return -1;
			}
			return buf[curChar++];
		}

		private static boolean skip(int c) {
			return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
		}
	}
}
