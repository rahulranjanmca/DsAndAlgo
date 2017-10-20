package com.rahul.ranjan.bitmanipulation;

public class FindElementThatAppearsOnce {
	
	int getUniqueElement(int[] arr)
	{
	    int ones = 0 ; //At any point of time, this variable holds XOR of all the elements which have appeared "only" once.
	    int twos = 0 ; //At any point of time, this variable holds XOR of all the elements which have appeared "only" twice.
	    int not_threes ;

	    for( int x : arr )
	    {
	        twos |= ones & x ; //add it to twos if it exists in ones
	        ones ^= x ; //if it exists in ones, remove it, otherwise, add it

	        // Next 3 lines of code just converts the common 1's between "ones" and "twos" to zero.

	        not_threes = ~(ones & twos) ;//if x is in ones and twos, dont add it to Threes.
	        ones &= not_threes ;//remove x from ones
	        twos &= not_threes ;//remove x from twos
	    } 
	    return ones;
	}

}
