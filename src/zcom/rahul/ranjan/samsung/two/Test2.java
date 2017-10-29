package zcom.rahul.ranjan.samsung.two;

public class Test2 {
	 static void findLargestPrime(int n) {
		  
	     int z=(int)Math.pow(10,n)-1;
	     int x=(int)Math.pow(10,n-1)-1;
	     for(int i=z;i>=x;i--)
	     {
	         if(isPrime(i))
	         {
	            System.out.println(i);
	             break;
	         }
	     }
	        
	    }

	 private static boolean isPrime(int num) {
	        if (num < 2) return false;
	        if (num == 2) return true;
	        if (num % 2 == 0) return false;
	        for (int i = 3; i * i <= num; i += 2)
	            if (num % i == 0) return false;
	        return true;
	}
public static void main(String[] args) {
	findLargestPrime(1);
}
}
