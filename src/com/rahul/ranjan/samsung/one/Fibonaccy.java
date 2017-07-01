package com.rahul.ranjan.samsung.one;

public class Fibonaccy {
	
	
	static long  fib1(int n)
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

	 static int fib(int n )
	    {
	        /* Declare an array to store Fibonacci numbers. */
	    int f[] = new int[n];
	    int i;
	      
	    /* 0th and 1st number of the series are 0 and 1*/
	    f[0] = 0;
	    f[1] = 1;
	     
	    for (i = 2; i <= n; i++)
	    {
	       /* Add the previous 2 numbers in the series
	         and store it */
	        f[i] = f[i-1] + f[i-2];
	    }
	      
	    return f[n];
	    }
	      
	    public static void main (String args[])
	    {
	        int n = 1000000000;
	        System.out.println(fib1(n));
	    }

}
