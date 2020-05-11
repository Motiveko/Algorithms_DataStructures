package myStudy.algorithms.dp;

/*
	https://github.com/awangdev/LintCode/blob/master/Java/Unique%20Binary%20Search%20Tree.java
	
	Given n, how many structurally unique BST's (binary search trees) that store values 1...n?
	Example
	Given n = 3, there are a total of 5 unique BST's.
	   1         3     3      2      1
	    \       /     /      / \      \
	     3     2     1      1   3      2
	    /     /       \                 \
	   2     1         2                 3
	Tags Expand 
	Catalan Number Dynamic Programming
	Thinking proces:
	Knowing what is Catalan number. 
	C(n+1) = SUM(C(i)*C(n-i))
	OR: C(n) = SUM(C(i)*C(n-i-1)).
	
*/


public class UniqueBinarySearchTree {

	public static int solve1(int n) {
		
		return 0;
		  
	}
}
