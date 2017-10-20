package com.rahul.ranjan.bitmanipulation;

import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import com.rahul.ranjan.common.*;

public class CountingSetBits {
	private static PrintWriter out = new PrintWriter(System.out);
	private static MyInputStreamReader in;
 
	private static long[] array = new long[1900];
	private static long[] sumArray = new long[1900];
 
	public static void main(String args[]) throws Exception {
		InputStream is = CountingSetBits.class.getResourceAsStream(CountingSetBits.class.getSimpleName().toLowerCase()+"_input.txt");
		boolean testMode = is != null;
		in = new MyInputStreamReader(testMode ? is : System.in);
 
		long start = System.nanoTime();
		main(in);
        System.out.println(Arrays.toString(array));
        System.out.println(Arrays.toString(sumArray));
		if (testMode) {
			out.println();
			out.print(System.nanoTime() - start + " ns");
		}
 
		out.close();
	}
		
	private static void main(MyInputStreamReader inputStream) throws Exception {
		
		
 
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
 
	

}
