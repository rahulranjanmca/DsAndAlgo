package com.rahul.ranjan.samsung.one;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.TreeMap;

public class TestClass {
	
	public static Map<Integer,Long> fib= new TreeMap<Integer,Long>();
	static long  fib2(int n)
	{
	    if(n < 2)
	        return n;
	     long a = 0,b = 1,ans=0;
	   
	    int i = 1;
	    while(i < n)
	    {
	        ans = (a+b) ;
	        a = b;
	        b = ans;
	        i++;
	    }
	    return ans;
	}
	
	static void  fib2(Map<Integer,Long> fib)
	{
		long previousKey=0;
		
		long previousKey2=1;
		
		long lastIndex=1;

		
		
		for(Map.Entry<Integer, Long> maps:fib.entrySet())
		{
			long ans=0;
			long n=maps.getKey();
			
				 if(n < 2)
				 {
					 maps.setValue(n);
					 previousKey=n;
					 continue;
				 }
			     
			   
			    int i = 1;
			    while(i < n-lastIndex+1)
			    {
			        ans = (previousKey+previousKey2)%MODULUS ;
			        previousKey = previousKey2;
			        previousKey2 = ans;
			        i++;
			    }
			   /*  previousKey2=previousKey;
				 previousValue2=previousVal;
			    previousKey=n;
			   ;*/
			   lastIndex=n;
			   maps.setValue(ans);
		}
	   
	}
	
	


	private static PrintWriter out = new PrintWriter(System.out);
	private static InputStreamReader in;
	private static int MODULUS=1000000000+7;
	 static Map<Long,Integer> f= new HashMap<>(); 
	 static void fib(long n)
	    {
	        /* Declare an array to store Fibonacci numbers. */
	 
	    long i;
	      
	    /* 0th and 1st number of the series are 0 and 1*/
	    f.put(0l, 0) ;
	    f.put(1l, 1);
	     
	    for (i = 2; i <= n; i++)
	    {
	        f.put(i,(f.get(i-1)+f.get(i-2)));
	    }
	      
	    }
	 
	 public static long gcd(long a, long b)
	 {
	     if (a == 0)
	         return b;
	     return gcd(b%a, a);
	 }
	 
	   public static long findGcd(long[] numbers) {
		   
	        //Find the smallest integer in the number list
	         
	        long smallest = numbers[0];
	 
	        for (int i = 1; i < numbers.length; i++) {
	            if (numbers[i] < smallest) {
	                smallest = numbers[i];
	            }
	        }
	 
	        //Find the GCD
	        while (smallest > 1) {
	             
	            int counter = 0;
	            int modTot = 0;
	             
	            while (counter < numbers.length) {
	 
	                modTot += numbers[counter] % smallest;
	                counter++;
	 
	            }
	 
	            if (modTot == 0) {
	                //Return the gcd if any
	                return smallest;
	            }
	 
	            //System.out.print(" "+ smallest);
	            smallest--;
	 
	        }
	        //return -1 if there is no gcd
	        return 1;
	    }
	 
	 public static long findGCD(long arr[])
	 {
	     long result = arr[0];
	     for (int i=1; i<arr.length; i++)
	         result = gcd(arr[i], result);
	  
	     return result;
	 }
	 
	 static long gcdNew(long x, long y)
	 {
	     long r;

	     if (x <= 0 || y <= 0)
	         return(0);

	     while ((r = x % y) != 0)
	     {
	         x = y;
	         y = r;
	     }
	     return(y);
	 }
	 
	 
	 public static long findGCD2(long numbers[]) {
		 
	        //Find the smallest integer in the number list
	         
	        long smallest = numbers[0];
	 
	        for (int i = 1; i < numbers.length; i++) {
	            if (numbers[i] < smallest) {
	                smallest = numbers[i];
	            }
	        }
	 
	        //Find the GCD
	        while (smallest > 1) {
	             
	            int counter = 0;
	            int modTot = 0;
	             
	            while (counter < numbers.length) {
	 
	                modTot += numbers[counter] % smallest;
	                counter++;
	 
	            }
	 
	            if (modTot == 0) {
	                //Return the gcd if any
	                return smallest;
	            }
	 
	            //System.out.print(" "+ smallest);
	            smallest--;
	 
	        }
	        //return -1 if there is no gcd
	        return 1;
	    }
	 

	public static void main(String[] args) throws Exception {
		InputStream is = TestClass.class.getResourceAsStream("input1.txt");
		if(is==null)
		{
		in = new InputStreamReader(System.in);
		}
		else{
			in= new InputStreamReader(is);
		}
		int N = in.readInt();
		int Q = in.readInt();
		/*int [] result=new int[Q];*/
		int[] A = new int[N];
		/*long[] L = new long[Q];
		long[] R = new long[Q];*/
		long max=0;
		for (int i = 0; i < N; i++) {
			A[i] = in.readInt();
			fib.put(A[i], null);
			
		}

		fib2(fib);
		for (int i = 0; i < Q; i++) {
			int l=in.readInt();
			int r=in.readInt();
			long arr[]= new long[r-l+1];
			
			for(int j=l-1, k=0;j<=r-1;j++,k++)
			{
				arr[k]=fib.get(A[j]);
			}
			long gcd=arr[0];
			for(int k=1;k<arr.length;k++)
			{
				gcd=gcdNew(gcd, arr[k]);
			}
			
			out.println(gcd%MODULUS);
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
