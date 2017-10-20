package zcom.rahul.ranjan.samsung.one;

public class GCD {
	 
    public static long findGcd(long... numbers) {
         
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
 
    public static void main(String[] x) {
        System.out.println("The GCD of 15 18 42 108 : "+GCD.findGcd(new long[]{15, 18, 42,108}));
    }
}