package com.rahul.ranjan.treesNGraphs;

import java.util.ArrayList;

public class PrintAllPathWhoseSumMatchSomeValueInBinaryTree {

	void findSum(TreeNode head, int sum, ArrayList<Integer> buffer, int level) {
		if (head == null)
			return;
		int tmp = sum;
		buffer.add(head.data);
		for (int i = level; i > -1; i--) {
			tmp -= buffer.get(i);
			if (tmp == 0)
				print(buffer, i, level);
		}
		ArrayList<Integer> c1 = (ArrayList<Integer>) buffer.clone();
		ArrayList<Integer> c2 = (ArrayList<Integer>) buffer.clone();
		findSum(head.left, sum, c1, level + 1);
		findSum(head.right, sum, c2, level + 1);
	}

	void print(ArrayList<Integer> buffer, int level, int i2) {
		for (int i = level; i <= i2; i++) {
			System.out.print(buffer.get(i) + "");
		}
		System.out.println();
	}

	public static class TreeNode {
		public TreeNode(int data) {
			this.data = data;
		}

		int data;
		public TreeNode left;
		public TreeNode right;
	}

}
