package zcom.rahul.ranjan.tw.two;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.InputMismatchException;

public class TestClass {
	static int minDiff(int arr[], int k) {
		int result = Integer.MAX_VALUE;
		for (int i = 0; i <= arr.length - k; i++)
			result = Math.min(result, arr[i + k - 1] * k - arr[i] * k);
		return result;
	}

	
	public static void main(String[] args) throws Exception {
		MyInputStreamReader myInputStreamReader;
		InputStream is = TestClass.class.getResourceAsStream(TestClass.class.getSimpleName() + ".txt");
		if (is == null) {
			myInputStreamReader = new MyInputStreamReader(System.in);
		} else {
			myInputStreamReader = new MyInputStreamReader(is);
		}
		int noOfTestCases = myInputStreamReader.readInt();

		for (int i = 0; i < noOfTestCases; i++) {
			int toNumberOfArray = myInputStreamReader.readInt();
			int difference = myInputStreamReader.readInt();
			int array[] = new int[toNumberOfArray];
			for (int k = 0; k < toNumberOfArray; k++) {
				array[k] = myInputStreamReader.readInt();
			}
			Arrays.sort(array);
			for (int k = toNumberOfArray; k > 0; k--) {
				int minDif = minDiff(array, k);
				if (minDif <= difference) {
					System.out.println(k + " " + minDif);
					break;
				}

			}

		}

	}

}

class MyInputStreamReader {
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