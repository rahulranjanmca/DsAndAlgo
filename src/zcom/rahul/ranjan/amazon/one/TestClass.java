package zcom.rahul.ranjan.amazon.one;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;

public class TestClass {

	public static long computeCost(int i, int j, ArrayList<Integer> B, long[][] cost, int[][] index, int x, int y) {
		// Base case - return 0 when there is no cuts between a range (i.e cuts are
		// sequential)
		if (i > j)
			return 0l;
		if (cost[i][j] != -1)
			return cost[i][j];

		cost[i][j] = Long.MAX_VALUE / 2;
		// we go through each of the values between i and j to find minimum
		for (int k = i; k <= j; k++) {
			long firstCut = ((long) ((B.get(k) - B.get(i - 1)) * x + (B.get(j + 1) - B.get(k)) * y));
			long new_cost = firstCut + computeCost(i, k - 1, B, cost, index, x, y)
					+ computeCost(k + 1, j, B, cost, index, x, y);
			// System.out.println("size of cutting rod from +"+B.get(k)+":"+new_cost);
			if (new_cost < cost[i][j]) {
				cost[i][j] = new_cost;
				// storing the index which gives minimum cost
				index[i][j] = k;
			}
		}

		return cost[i][j];
	}

	public static long rodCut(ArrayList<Integer> B, int x, int y) {
		int size = B.size();
		// we define two 2D arrays (for memoization) one for the cost and one for the
		// index of arrayList B
		// so we can backtrack without any computation. you can also backtrack without
		// this.
		long[][] cost = new long[size][size];
		int[][] index = new int[size][size];
		// initialize empty arrays with -1
		for (long[] arr : cost)
			Arrays.fill(arr, -1);
		// resulting sequence

		// compute final costs
		long l = computeCost(1, size - 2, B, cost, index, x, y);
		return l;

		// backtrack to get sequence
	}

	public static void main1(String[] args) {
		ArrayList<Integer> arrayList = new ArrayList<>();
		arrayList.add(1);
		arrayList.add(4);
		arrayList.add(6);
		arrayList.add(8);
		arrayList.add(15);
		rodCut(arrayList, 0, 0);
		System.out.println(rodCut(arrayList, 0, 0));
	}

	public static void backtracking(int i, int j, int[][] index, ArrayList<Integer> result, ArrayList<Integer> B) {
		if (i >= j - 1)
			return;
		int lowest = index[i][j];
		result.add(B.get(lowest));
		backtracking(i, lowest, index, result, B);
		backtracking(lowest, j, index, result, B);
	}

	public static void main(String[] args) throws Exception {

		MyInputStreamReader myInputStreamReader;
		InputStream is = TestClass.class.getResourceAsStream(TestClass.class.getSimpleName() + ".txt");
		if (is == null) {
			myInputStreamReader = new MyInputStreamReader(System.in);
		} else {
			myInputStreamReader = new MyInputStreamReader(is);
		}
		int noOfTestCase = myInputStreamReader.readInt();

		for (int i = 0; i < noOfTestCase; i++) {
			int xMultiplier = myInputStreamReader.readInt();
			int yMultiplier = myInputStreamReader.readInt();
			int noOfMarkingPoints = myInputStreamReader.readInt();
			int[] markingPoints = new int[noOfMarkingPoints];
			ArrayList<Integer> list = new ArrayList<>();
			for (int j = 0; j < noOfMarkingPoints; j++) {
				markingPoints[j] = myInputStreamReader.readInt();
				list.add(markingPoints[j]);
			}
			System.out.println(rodCut(list, xMultiplier, yMultiplier));

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