package zcom.rahul.ranjan.groupon.one;
/*package com.rahul.ranjan.groupon.one;

public class TestClass {

	public static void main(String[] args) {
		int N,K;
		int DP[][]=new int[N][K];;
		int set[N];

		//go through all elements in the set by index
		for (int i =0;i<N;i++)
		{
		   //count the one element subset consisting only of set[i]
		   DP[i][set[i]] = 1;

		   if (i == 0) continue;

		   //case 1. build and count all subsets that don't contain element set[i]
		   for(int k=0;k<K;k++)
		       DP[i][k] += DP[i-1][k];

		    //case 2. build and count subsets that contain element set[i]
		   for(int k=0;k<K;k++)
		   {
		       if (k + set[i] >= K){ break;} //then break inner loop                     
		       DP[i][k+set[i]] += DP[i-1][k];
		   }

		//result is the number of all subsets - number of subsets with sum < K
		//the -1 is for the empty subset
		}
		return 2^N - sum(DP[N-1][1..K-1]) - 1;
	}

}
*/