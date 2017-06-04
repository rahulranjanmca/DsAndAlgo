package com.rahul.ranjan.algo.bitmanipulation;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;

public class CountingSetBits {
	private static PrintWriter out = new PrintWriter(System.out);
	private static InputStreamReader in;
 
	private static long[] array = new long[1900];
	private static long[] sumArray = new long[1900];
 
	/*public static void main(String args[]) throws Exception {
		InputStream is = CountingSetBits.class.getResourceAsStream("in.txt");
		boolean testMode = is != null;
		in = new InputStreamReader(testMode ? is : System.in);
 
		long start = System.nanoTime();
		main();
        System.out.println(Arrays.toString(array));
        System.out.println(Arrays.toString(sumArray));
		if (testMode) {
			out.println();
			out.print(System.nanoTime() - start + " ns");
		}
 
		out.close();
	}*/
		 public static void main(String[] args)  throws Exception{
			main();
			out.close();
		}
	private static void main() throws Exception {
		if(in==null)
		{
			in = new InputStreamReader(System.in);
		}
		
 
		int T = in.readInt();
		long[] tests = new long[T];
		long max = 0L;
		for (int t = 0; t < T; t++) {
			tests[t] = in.readLong();
			if (tests[t] > max) {
				max = tests[t];
			}
		}
 
		int count = setUpArrays(max);
 
		for (int t = 0; t < T; t++) {
			long N = tests[t];
			if (N < 3) {
				out.println(0);
			} else {
				int index = findIndex(N, 0, count);
				out.println(sumArray[index]);
			}
		}
	}
 
	private static int setUpArrays(long max) {
		int count = 0;
		for (int i = 1; i < 63; i++) {
			long l = 1L << i;
			for (int j = 0; j < i; j++) {
				array[count] = l + (1L << j);
				sumArray[count] = (array[count] + (count > 0 ? sumArray[count - 1] : 0)) % 1000000007;
				count++;
				if (array[count - 1] > max) {
					return count;
				}
			}
		}
		return count;
	}
 
	private static int findIndex(long a, int start, int end) {
		if (start == end) {
			if (a < array[start] && start > 0) {
				return start - 1;
			}
			return start;
		}
		int mid = (start + end) / 2;
		if (a == array[mid]) {
			return mid;
		}
		if (a < array[mid]) {
			return findIndex(a, start, mid);
		}
		return findIndex(a, mid + 1, end);
	}
 
	private static class InputStreamReader {
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
