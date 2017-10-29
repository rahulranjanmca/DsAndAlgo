package zcom.rahul.ranjan.geeksForGeeks;

import java.math.BigInteger;

public class CountSetBitInAnIntegerByBrianKernighansAlgorithm {

	int countSetBits(int x)
	{
	    int count = 0;
	    while (x>0)
	    {
	        x &= (x-1);
	        count++;
	    }
	    return count;
	}
	
	public static void main(String[] args) {
		for(int i=900000000;i<1000000000;i++)
		{
			BigInteger bigInteger= BigInteger.valueOf(2);
			bigInteger=bigInteger.pow(i).subtract(BigInteger.ONE);
			boolean bool=bigInteger.isProbablePrime(10000);
		   if(bool)
		   {
			   System.out.println(bool);
			   System.out.println(i);
			   break;
		   }
		   else {
			   System.out.println(bool);
		   }
		}
	}
}
