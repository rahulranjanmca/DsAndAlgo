package zcom.rahul.ranjan.samsung.one;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;

public class TestClass {
	private static int[] st;

	public static int[] constructSegmentTree(int[] arr) {
		int height = (int) Math.ceil(Math.log(arr.length) / Math.log(2));
		int size = 2 * (int) Math.pow(2, height) - 1;
		st = new int[size];
		constructST(arr, 0, arr.length - 1, 0);
		return st;
	}

	public static int constructST(int[] arr, int ss, int se, int si) {
		if (ss == se) {
			st[si] = arr[ss];
			return st[si];
		}
		int mid = ss + (se - ss) / 2;
		st[si] = gcd(constructST(arr, ss, mid, si * 2 + 1), constructST(arr, mid + 1, se, si * 2 + 2));
		return st[si];
	}

	// Function to find gcd of 2 numbers.
	private static int gcd(int a, int b) {
		if (a < b) {
			// If b greater than a swap a and b
			int temp = b;
			b = a;
			a = temp;
		}

		if (b == 0)
			return a;
		return gcd(b, a % b);
	}

	// Finding The gcd of given Range
	public static int findRangeGcd(int ss, int se, int[] arr) {
		int n = arr.length;

		if (ss < 0 || se > n - 1 || ss > se)
			throw new IllegalArgumentException("Invalid arguments");

		return findGcd(0, n - 1, ss, se, 0);
	}

	public static int findGcd(int ss, int se, int qs, int qe, int si) {
		
		if (ss > qe || se < qs)
			return 0;

		if (qs <= ss && qe >= se)
			return st[si];

		int mid = ss + (se - ss) / 2;

		return gcd(findGcd(ss, mid, qs, qe, si * 2 + 1), findGcd(mid + 1, se, qs, qe, si * 2 + 2));
	}

	private static PrintWriter out = new PrintWriter(System.out);
	private static InputStreamReader in;
	private static int MODULUS = 1000000000 + 7;
	static Map<Long, Integer> f = new HashMap<>();

	public static long gcd(long a, long b) {
		if (a == 0)
			return b;
		return gcd(b % a, a);
	}

	static void multiply(long f[][], long m[][]) {
		long x = (f[0][0] * m[0][0] + f[0][1] * m[1][0]) % MODULUS;
		long y = (f[0][0] * m[0][1] + f[0][1] * m[1][1]) % MODULUS;
		long z = (f[1][0] * m[0][0] + f[1][1] * m[1][0]) % MODULUS;
		long w = (f[1][0] * m[0][1] + f[1][1] * m[1][1]) % MODULUS;

		f[0][0] = x;
		f[0][1] = y;
		f[1][0] = z;
		f[1][1] = w;
	}

	static void power(long f[][], long n) {
		if (n == 0 || n == 1)
			return;
		long m[][] = { { 1, 1 }, { 1, 0 } };
		power(f, n / 2);
		multiply(f, f);
		if (n % 2 != 0)
			multiply(f, m);
	}

	static long  fib(long n) {
		long f[][] = { { 1, 1 }, { 1, 0 } };
		if (n == 0)
			return 0;
		power(f, n - 1);
		return (f[0][0] % MODULUS);
	}

	public static void main(String[] args) throws Exception {
		InputStream is = TestClass.class.getResourceAsStream("input.txt");
		if (is == null) {
			in = new InputStreamReader(System.in);
		} else {
			in = new InputStreamReader(is);
		}
		int N = in.readInt();
		int Q = in.readInt();

		int[] A = new int[N];

		for (int i = 0; i < N; i++) {
			A[i] = in.readInt();

		}
		constructSegmentTree(A);

		for (int i = 0; i < Q; i++) {
			int l = in.readInt();
			int r = in.readInt();
			int result=findRangeGcd(l - 1, r - 1, A);
			System.out.println(fib(7));
		}
		out.flush();
		out.close();

	}

	public static class InputStreamReader {
		private byte[] buf = new byte[1 << 8];
		private int curChar;
		private int charsRead;
		private InputStream stream;

		public InputStreamReader(InputStream stream) {
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
