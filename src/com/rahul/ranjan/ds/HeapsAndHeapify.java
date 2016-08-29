package com.rahul.ranjan.ds;

public class HeapsAndHeapify {

	public static void BuildMaxHeap( int[ ] arr )
	{
	    for( int i = (int)Math.floor( arr.length - 1 ); i >= 0; i-- )
	        MaxHeapify( arr, i );
	}
	public static void MaxHeapify( int[ ] arr, int i )
	{
	    int left = 2 * i + 1;
	    int right = 2 * i + 2;
	    int largest = i;

	    if( left < arr.length && arr[ left ] > arr[ largest ] )
	        largest = left;
	    if( right < arr.length && arr[ right ] > arr[ largest ] )
	        largest = right;
	    if( largest != i )
	    {
	        int temp = arr[ i ];
	        arr[ i ] = arr[ largest ];
	        arr[ largest ] = temp;
	        MaxHeapify( arr, largest );
	    }
	}
}
