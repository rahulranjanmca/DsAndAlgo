package zcom.rahul.ranjan.geeksForGeeks;

public class RightMostSetBitInBinaryRepresentation {
	public static void main(String[] args) {
     int x=100;
     System.out.println(x ^ ( x & (x-1)));// One Way
     System.out.println(x & (-x)); // Second Way
	}
}
