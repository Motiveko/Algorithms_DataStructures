package myStudy.algorithms.dp;

/*
https://github.com/awangdev/LintCode/blob/master/Java/Best%20Time%20to%20Buy%20and%20Sell%20Stock%20III.java

Say you have an array for which the ith element is the price of a given stock on day i.
Design an algorithm to find the maximum profit. You may complete at most two transactions.
Example
Given an example [4,4,6,1,1,4,2,5], return 6.
Note
You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
Tags Expand 
Enumeration Forward-Backward Traversal Array
 */
public class BestTimeToBuyStock {

	final static int INF = Integer.MAX_VALUE/2;
	public static int solve1(int[] price) {
		
		
		int min1 = INF;
		int max1 = 0;
		int min2 = INF;
		int max2 = 0;
		int n = price.length;
		
		int dp[] = new int[n];
		
		for( int i=0; i<n; i++) {
			
			
		}
		
		return 0;
	}
}
