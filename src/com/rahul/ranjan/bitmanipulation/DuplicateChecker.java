package com.rahul.ranjan.bitmanipulation;

public class DuplicateChecker {
	public static boolean isUniqueChars(String str) {

		int checker = 0;
		for (int i = 0; i < str.length(); i++) {
			int val = str.charAt(i) - 'a';
			System.out.println("Checker:"+Integer.toBinaryString(checker));
			System.out.println("val:"+Integer.toBinaryString(checker));
			System.out.println("1 << val:"+(Integer.toBinaryString(1 << val)));
			
			if ((checker & (1 << val)) > 0) {
				return false;
			}
			
			checker |= (1 << val);
			System.out.println("Cheker 2:"+Integer.toBinaryString(checker));
		}
		return true;
	}

	public static void main(String[] args) {
		isUniqueChars("azcdefgh");

	}
}
