package zcom.rahul.ranjan.samsung.two;

import java.util.HashMap;
import java.util.Map;

public class Test3 {
	  static int sum(String s) {
		     Map<String,Integer> map= new HashMap<>();
		        map.put("one",1);
		        map.put("two",2);
		        map.put("three",3);
		        map.put("four", 4);
		        map.put("five",5);
		        map.put("six",6);
		        map.put("seven",7);
		        map.put("eight",8);
		        map.put("nine",9);
		        map.put("ten",10);
		        String[] numbers= s.split(" ");
		        int totalSum=0;
		        for(int i=0;i<numbers.length;i++)
		        {
		            totalSum+=map.get(numbers[i].toLowerCase());
		        }
		           return totalSum; 

		    }

}
