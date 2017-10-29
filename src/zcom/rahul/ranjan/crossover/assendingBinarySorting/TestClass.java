package zcom.rahul.ranjan.crossover.assendingBinarySorting;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class TestClass { /**
     * Complete the function below.
     * DONOT MODIFY anything outside this function!
     */
    static int[] rearrange(int[] elements) 
	{
    	Integer [] returnArray= new Integer[elements.length];
    	int [] returnArray2= new int[elements.length];
    	for(int i=0;i<elements.length;i++)
    	{
    		returnArray[i]=elements[i];
    	}
		Comparator<Integer> compartor = new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				if(Integer.bitCount(o2)==Integer.bitCount(o1))
				{
					return o1.compareTo(o2);
				}
				return new Integer(Integer.bitCount(o1)).compareTo(Integer.bitCount(o2)) ;
			}
		};
		
		Arrays.sort(returnArray,compartor);
		for(int i=0;i<elements.length;i++)
    	{
			returnArray2[i]=returnArray[i];
    	}
		return returnArray2;

	}

    /**
     * DO NOT MODIFY THIS METHOD!
     */
    public static void main(String[] args) throws IOException
    {
        Scanner in = new Scanner(System.in);
        int[] res;
        
        int _elements_size = 0;
        _elements_size = Integer.parseInt(in.nextLine().trim());
        int[] _elements = new int[_elements_size];
        int _elements_item;
        for(int _elements_i = 0; _elements_i < _elements_size; _elements_i++) {
            _elements_item = Integer.parseInt(in.nextLine().trim());
            _elements[_elements_i] = _elements_item;
        }
        
        res = rearrange(_elements);
        for(int res_i=0; res_i < res.length; res_i++) {
            System.out.println(String.valueOf(res[res_i]));
        }        
    }
    }