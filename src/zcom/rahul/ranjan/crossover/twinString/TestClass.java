package zcom.rahul.ranjan.crossover.twinString;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class TestClass {
	

    /**
     * Complete the function below.
     * DO NOT MODIFY anything outside this method. 
     */
    static String[] twins(String[] a, String[] b) 
    {
     String[] array= new String[Math.max(a.length,b.length )];
     for(int i=0;i<array.length;i++)
     {
    	if(i>a.length-1|| i>b.length-1)
    	{
    		array[i]="No";
    	}
    	else if(a[i].length()!=b[i].length())
    	{
    		array[i]="No";
    	}
    	else {
    		String aOddString="";
    		String aEvenString="";
    		String bOddString="";
    		String bEvenString="";
    		for(int k=0;k<a[i].length();k++)
    		{
    			if(k%2==0)
    			{
    				aEvenString=aEvenString+a[i].charAt(k);
    				bEvenString=bEvenString+b[i].charAt(k);
    			}
    			else {
    				aOddString=aOddString+a[i].charAt(k);
    				bOddString=bOddString+b[i].charAt(k);
    			}
    		}
    		 char[] aOddStringChar = aOddString.toCharArray();
    	        Arrays.sort(aOddStringChar);
    	        String aOddStringSorted = new String(aOddStringChar);
    	        char[] aEventringChar = aEvenString.toCharArray();
    	        Arrays.sort(aEventringChar);
    	        String aEvenStringSorted = new String(aEventringChar);
    	        char[] bOddStringChar = bOddString.toCharArray();
    	        Arrays.sort(bOddStringChar);
    	        String bOddStringSorted = new String(bOddStringChar);
    	        char[] bEvenStringChar = bEvenString.toCharArray();
    	        Arrays.sort(bEvenStringChar);
    	        String bEvenStringSorted = new String(bEvenStringChar);
    	        if(aEvenStringSorted.equalsIgnoreCase(bEvenStringSorted)&& bOddStringSorted.equalsIgnoreCase(aOddStringSorted))
    	        {
    	        	array[i]="Yes";
    	        }
    	        else {
    	        	array[i]="No";
    	        }
    	
    	}
     }
     return array;
   
    }

    /**
     * DO NOT MODIFY THIS METHOD!
     */
    public static void main(String[] args) throws IOException
    {
        Scanner in = new Scanner(System.in);
        String[] res;
        
        int _a_size = 0;
        _a_size = Integer.parseInt(in.nextLine().trim());
        String[] _a = new String[_a_size];
        String _a_item;
        for(int _a_i = 0; _a_i < _a_size; _a_i++) {
            try {
                _a_item = in.nextLine();
            } catch (Exception e) {
                _a_item = null;
            }
            _a[_a_i] = _a_item;
        }
        
        
        int _b_size = 0;
        _b_size = Integer.parseInt(in.nextLine().trim());
        String[] _b = new String[_b_size];
        String _b_item;
        for(int _b_i = 0; _b_i < _b_size; _b_i++) {
            try {
                _b_item = in.nextLine();
            } catch (Exception e) {
                _b_item = null;
            }
            _b[_b_i] = _b_item;
        }
        
        res = twins(_a, _b);
        for(int res_i=0; res_i < res.length; res_i++) {
            System.out.println(String.valueOf(res[res_i]));
        }
    }
}