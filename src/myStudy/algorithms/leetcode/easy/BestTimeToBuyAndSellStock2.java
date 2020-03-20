package myStudy.algorithms.leetcode.easy;

/*
	Say you have an array for which the ith element is the price of a given stock on day i.
	Design an algorithm to find the maximum profit. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times).
	Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).
*/
public class BestTimeToBuyAndSellStock2 {

	
	public static int findProfit(int[] ar) {
		
		int sum = 0;
		int n = ar.length;
		
		for(int i=0; i<n-1; i++) 
			if(ar[i] < ar[i+1]) sum+= ar[i+1] - ar[i];

		return sum;
	}
	
	public static void main(String[] args) {
		
		int[] ar = {5,4,3,2,1,9,4,6,8,1};
		
		System.out.println(findProfit(ar));
	}
}
