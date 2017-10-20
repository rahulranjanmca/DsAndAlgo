package zcom.rahul.ranjan.groupon.two;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;

//http://www.geeksforgeeks.org/count-number-of-subsets-of-a-set-with-gcd-equal-to-a-given-number/

public class TestClass {
	private static PrintWriter out = new PrintWriter(System.out);
	private static InputStreamReader in;
	private static int MODULUS = 1000000000 + 7;
	
	// n is size of arr[] and m is sizeof gcd[]
	static int ccountSubsets(int arr[], int n)
	{
	    // Map to store frequency of array elements
	    Map<Integer, Integer> freq=new HashMap<>();
	 
	    // Map to store number of subsets with given gcd
	    Map<Integer, Integer> subsets=new HashMap<>();
	   
	 
	    // Initialize maximum element. Assumption: all array
	    // elements are positive.
	    int arrMax = 0; 
	 
	    // Find maximum element in array and fill frequency
	    // map.
	    for (int i=0; i<n; i++)
	    {
	        arrMax = Math.max(arrMax, arr[i]);
	        if(freq.get(arr[i])==null)
	        {
	        	freq.put(arr[i], 0);
	        }
	        freq.put(arr[i], freq.get(arr[i])+1);
	    }
	 
	    // Run a loop from max element to 1 to find subsets
	    // with all gcds
	    for (int i=arrMax; i>=1; i--)
	    {
	        int sub = 0;
	        int add = freq.get(i)==null?0: freq.get(i);
	 
	        // Run a loop for all multiples of i
	        for (int j = 2; j*i <= arrMax; j++)
	        {
	            // Sum the frequencies of every element which
	            // is a multiple of i
	            add += freq.get(j*i)==null?0:freq.get(j*i);
	 
	            // Excluding those subsets which have gcd > i but
	            // not i i.e. which have gcd as multiple of i in
	            // the subset for ex: {2,3,4} cnsidering i = 2 and
	            // subset we need to exclude are those havng gcd as 4
	            sub += subsets.get(j*i);
	        }
	         
	        // Number of subsets with GCD equal to 'i' is pow(2, add)
	        // - 1 - sub    
	        subsets.put(i, (1<<add) - 1 - sub);
	    }
	    int total=0;
	    for (Map.Entry<Integer, Integer> subset:subsets.entrySet()){
	    	if(!subset.getKey().equals(new Integer(1)))
	    	{
	    		total+=subset.getValue();
	    	}
	    }
	    return total;
	    
	}
	
	public static void main(String[] args) throws Exception {
		InputStream is = TestClass.class.getResourceAsStream("input.txt");
		if (is == null) {
			in = new InputStreamReader(System.in);
		} else {
			in = new InputStreamReader(is);
		}
		int N = in.readInt();
		

		int[] A = new int[N];

		for (int i = 0; i < N; i++) {
			A[i] = in.readInt();

		}
		out.println(ccountSubsets(A, A.length));
		
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
