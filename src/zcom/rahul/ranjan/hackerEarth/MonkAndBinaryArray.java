package zcom.rahul.ranjan.hackerEarth;

import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;

public class MonkAndBinaryArray {
	public static void main(String[] args) throws Exception {

		MyInputStreamReader myInputStreamReader;
		InputStream is = SmartTravelAgent.class.getResourceAsStream(MonkAndBinaryArray.class.getSimpleName() + ".txt");
		if (is == null) {
			myInputStreamReader = new MyInputStreamReader(System.in);
		} else {
			myInputStreamReader = new MyInputStreamReader(is);
		}
		int N = myInputStreamReader.readInt();

		int binaryArray[] = new int[N+1];
		int result = 0;
		int pre[] = new int[N+1];
		int tot = 0;
		for (int i = 1; i <= N; i++) {
			binaryArray[i] = myInputStreamReader.readInt();
			pre[i] = pre[i - 1] + binaryArray[i];
		}

		for (int i = 1; i <= N; ++i) {
			for (int j = i; j <= N; ++j) {
				int vl = pre[j] - pre[i - 1];
				if (vl % 2 == 1)
					tot++;
			}
		}
		for (int i = 1; i <= N; ++i) {
			int cnt1 = 0, cnt0 = 0, sum = 0, ans = 0, val = 0;
			for (int j = i; j <= N; ++j) {
				if (j == i)
					val += binaryArray[j] ^ 1;
				else
					val += binaryArray[j];
				if (val % 2 == 0)
					cnt0++;
				else
					cnt1++;
			}
			for (int j = i - 1; j >= 1; --j) {
				sum += binaryArray[j];
				if (sum % 2 == 0) {
					ans += cnt1;
				} else {
					ans += cnt0;
				}
			}
			ans += cnt1;
			int cnt11 = 0, cnt00 = 0, sum1 = 0, ans1 = 0, val1 = 0;
			for (int j = i; j <= N; ++j) {
				val1 += binaryArray[j];
				if (val1 % 2 == 0)
					cnt00++;
				else
					cnt11++;
			}
			for (int j = i - 1; j >= 1; --j) {
				sum1 += binaryArray[j];
				if (sum1 % 2 == 0) {
					ans1 += cnt11;
				} else {
					ans1 += cnt00;
				}
			}
			ans1 += cnt11;
			result = Math.max(result, tot + ans - ans1);
		}
		System.out.println(result);

	}
	public static void main1(String[] args) throws Exception{


		MyInputStreamReader myInputStreamReader;
		InputStream is = SmartTravelAgent.class.getResourceAsStream(MonkAndBinaryArray.class.getSimpleName() + ".txt");
		if (is == null) {
			myInputStreamReader = new MyInputStreamReader(System.in);
		} else {
			myInputStreamReader = new MyInputStreamReader(is);
		}
		int N = myInputStreamReader.readInt();

		int binaryArray[] = new int[N+1];
		int result = 0;
		int pre[] = new int[N+1];
		int tot = 0;
		for (int i = 1; i <= N; i++) {
			binaryArray[i] = myInputStreamReader.readInt();
			pre[i] = pre[i - 1] + binaryArray[i];
		}

		for (int i = 1; i <= N; ++i) {
			for (int j = i; j <= N; ++j) {
				int vl = pre[j] - pre[i - 1];
				if (vl % 2 == 1)
					tot++;
			}
		}
		for (int i = 1; i <= N; ++i) {
			int cnt1 = 0, cnt0 = 0, sum = 0, ans = 0, val = 0;
			for (int j = i; j <= N; ++j) {
				if (j == i)
					val += binaryArray[j] ^ 1;
				else
					val += binaryArray[j];
				if (val % 2 == 0)
					cnt0++;
				else
					cnt1++;
			}
			for (int j = i - 1; j >= 1; --j) {
				sum += binaryArray[j];
				if (sum % 2 == 0) {
					ans += cnt1;
				} else {
					ans += cnt0;
				}
			}
			ans += cnt1;
			int cnt11 = 0, cnt00 = 0, sum1 = 0, ans1 = 0, val1 = 0;
			for (int j = i; j <= N; ++j) {
				val1 += binaryArray[j];
				if (val1 % 2 == 0)
					cnt00++;
				else
					cnt11++;
			}
			for (int j = i - 1; j >= 1; --j) {
				sum1 += binaryArray[j];
				if (sum1 % 2 == 0) {
					ans1 += cnt11;
				} else {
					ans1 += cnt00;
				}
			}
			ans1 += cnt11;
			result = Math.max(result, tot + ans - ans1);
		}
		System.out.println(result);

	
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
