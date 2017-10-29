package zcom.rahul.ranjan.hackerEarth;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.TreeMap;

public class TurtlePath {
	private static PrintWriter out = new PrintWriter(System.out);
	private static MyInputStreamReader in;
	private static int MODULUS = 1000000000 + 7;
	public static int N;
	public static int M;
	static long dynamicProgramming[][] = new long[1005][1005];
	static int mark[][] = new int[1005][1005];
	static int[][] matrix = new int[1005][1005];
	static int q = 0;
	static Map<Integer, Pair> pairs = new HashMap<>();

	public static Map<Integer, Boolean> primeMap = new TreeMap<>();

	public static boolean isPrime(int n) {
		Boolean isPrimeFromMap = primeMap.get(n);
		if (isPrimeFromMap != null)
			return isPrimeFromMap;

		boolean isPrime = true;
		if (n < 2)
			isPrime = false;
		for (long factor = 2; factor * factor <= n; factor++) {
			if (n % factor == 0) {
				isPrime = false;
				break;
			}
		}
		primeMap.put(n, isPrime);
		return isPrime;
	}

	public static void main(String[] args) throws Exception {

		long startTime = System.currentTimeMillis();
		boolean testMode = true;
		InputStream is = TurtlePath.class.getResourceAsStream("input.txt");
		if (is == null) {
			testMode = false;
			in = new MyInputStreamReader(System.in);
		} else {
			in = new MyInputStreamReader(is);
		}
		N = in.readInt();
		M = in.readInt();
		dynamicProgramming = new long[N][M];
		mark = new int[N][M];
		matrix = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				int number = in.readInt();
				boolean isPrime = isPrime(number);
				matrix[i][j] = !isPrime ? 1 : 0;
			}
		}

		for (int i = 0; i < N; i++) {
			if (matrix[i][0] != 1) {
				dynamicProgramming[i][0] = 1;

			} else {
				break;
			}
		}
		for (int i = 0; i < M; i++) {
			if (matrix[0][i] != 1) {
				dynamicProgramming[0][i] = 1;

			} else {
				break;
			}
		}

		for (int i = 1; i < N; i++) {
			for (int j = 1; j < M; j++) {

				dynamicProgramming[i][j] = (dynamicProgramming[i - 1][j] + dynamicProgramming[i][j - 1]
						+ dynamicProgramming[i - 1][j - 1]) % MODULUS;
				if (matrix[i][j] == 1)
					dynamicProgramming[i][j] = 0;
			}
		}

		System.out.println(dynamicProgramming[N - 1][M - 1]);
		depthFirstSearch(0, 0, 0);
		if (q > 0) {
			for (Map.Entry<Integer, Pair> pairEntry : pairs.entrySet())
				System.out.println(pairEntry.getValue().x + 1 + " " + (pairEntry.getValue().y + 1));
		}

		if (testMode) {
			System.out.println("Time Taken:" + (System.currentTimeMillis() - startTime));
		}

		out.close();

	}

	public static void depthFirstSearch(int i, int j, int k) {
		if (i >= N || j >= M || mark[i][j] == 1 || q > 0 || matrix[i][j] == 1)
			return;
		mark[i][j] = 1;

		pairs.put(k, new Pair(i, j));
		if (i == N - 1 && j == M - 1) {
			q = k;
			return;
		}

		depthFirstSearch(i + 1, j + 1, k + 1);
		depthFirstSearch(i + 1, j, k + 1);
		depthFirstSearch(i, j + 1, k + 1);
	}

	public static class Pair {
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}

		int x;
		int y;
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
