/*
 You have two very large binary trees: T1, with millions of nodes, 
 and T2, with hundreds of nodes. Create an algorithm to decide if T2 is a subtree of T1.
 */

package com.rahul.ranjan.treesNGraphs;

/*
 SOLUTION:
 Note that the problem here specifies that T1 has millions of
  nodes—this means that we should be careful of how much space we use. 
  Let’s say, for example, T1 has 10 million nodes—this means that the data 
  alone is about 40 mb. We could create a string representing the inorder 
  and preorder traversals. If T2’s preorder traversal is a substring of T1’s 
  preorder traversal, and T2’s inorder traversal is a substring of T1’s 
  inorder traversal, then T2 is a substring of T1. We can check this 
  using a suffix tree. However, we may hit memory limitations because 
  suffix trees are extremely memory intensive. If this become an issue,
   we can use an alternative approach. Alternative Approach: The treeMatch
    procedure visits each node in the small tree at most once and is called 
    no more than once per node of the large tree. Worst case runtime is at 
    most O(n * m), where n and m are the sizes of trees T1 and T2, respectively.
If k is the number of occurrences of T2’s root in T1, the worst case runtime can
 be characterized as O(n + k * m).
 
 
 */
public class IsBinaryTreeASubTreeOfAnother {

	boolean containsTree(TreeNode t1, TreeNode t2) {
		if (t2 == null)
			return true; // The empty tree is always a subtree
		else
			return subTree(t1, t2);
	}

	boolean subTree(TreeNode r1, TreeNode r2) {
		if (r1 == null)
			return false; // big tree empty & subtree still not found.
		if (r1.data == r2.data) {
			if (matchTree(r1, r2))
				return true;
		}
		return (subTree(r1.left, r2) || subTree(r1.right, r2));
	}

	boolean matchTree(TreeNode r1, TreeNode r2) {
		if (r2 == null && r1 == null)
			return true; // nothing left in the subtree
		if (r1 == null || r2 == null)
			return false; // big tree empty & subtree still not found
		if (r1.data != r2.data)
			return false; // data doesn’t match
		return (matchTree(r1.left, r2.left) && matchTree(r1.right, r2.right));
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
