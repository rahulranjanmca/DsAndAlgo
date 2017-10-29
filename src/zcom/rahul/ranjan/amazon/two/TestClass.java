package zcom.rahul.ranjan.amazon.two;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.InputMismatchException;

public class TestClass {

	private static int min(int x, int y, int z) {
		if (x < y)
			return (x < z) ? x : z;
		else
			return (y < z) ? y : z;
	}

	static int minCost(int cost[][], int m, int n, int dp[][]) {

		int i, j;
		int tc[][] = new int[m + 1][3 + 1];

		tc[0][0] = cost[0][0];

		/* Initialize first column of total cost(tc) array */
		/*
		 * for (i = 1; i <= m; i++) tc[i][0] = tc[i-1][0] + cost[i][0];
		 */

		/* Initialize first row of tc array */
		for (j = 1; j < 3; j++)
			tc[0][j] = cost[0][j];

		/* Construct rest of the tc array */
		for (i = 1; i <= m; i++)
			for (j = 0; j < 3; j++)
				tc[i][j] = Math.min(tc[i - 1][Math.abs(j - 1 == -1 ? 2 : j - 1)],
						tc[i - 1][Math.abs(j + 1 == 3 ? 0 : j + 1)]) + cost[i][j];

		return tc[m][n];
	}

	static int minCost1(int cost[][], int m, int n, int dp[][]) {
		if (m < 0)
			return 0;
		else if (m == 0 && n == 0)
			return cost[m][n];
		else if (dp[m][n] != -1)
			return dp[m][n];
		else {
			dp[m][n] = Integer.MAX_VALUE;
			int price = 0;
			if (n == 0) {
				price = cost[m][n] + Math.min(minCost(cost, m - 1, 1, dp), minCost(cost, m - 1, 2, dp));
			} else if (n == 1) {
				price = cost[m][n] + Math.min(minCost(cost, m - 1, 0, dp), minCost(cost, m - 1, 2, dp));
			} else {
				price = cost[m][n] + Math.min(minCost(cost, m - 1, 1, dp), minCost(cost, m - 1, 0, dp));
			}
			if (price < dp[m][n]) {
				dp[m][n] = price;
			}
			return price;

		}

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
			int totalNumberOfShop = myInputStreamReader.readInt();
			int costArray[][] = new int[totalNumberOfShop][3];
			int dp1[][] = new int[totalNumberOfShop][3];
			int dp2[][] = new int[totalNumberOfShop][3];
			int dp3[][] = new int[totalNumberOfShop][3];
			for (int[] arr : dp1) {
				Arrays.fill(arr, -1);
			}
			for (int[] arr : dp2) {
				Arrays.fill(arr, -1);
			}
			for (int[] arr : dp3) {
				Arrays.fill(arr, -1);
			}

			for (int k = 0; k < totalNumberOfShop; k++) {
				costArray[k][0] = myInputStreamReader.readInt();
				costArray[k][1] = myInputStreamReader.readInt();
				costArray[k][2] = myInputStreamReader.readInt();
			}

			System.out.println(min(minCost(costArray, totalNumberOfShop - 1, 0, dp1),
					minCost(costArray, totalNumberOfShop - 1, 1, dp2),
					minCost(costArray, totalNumberOfShop - 1, 2, dp3)));

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