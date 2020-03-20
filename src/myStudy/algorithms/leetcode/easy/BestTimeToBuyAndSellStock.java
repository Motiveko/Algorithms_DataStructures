package myStudy.algorithms.leetcode.easy;
// 다시보기
import java.util.Arrays;

/*
	Say you have an array for which the ith element is the price of a given stock on day i.
	If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock), design an algorithm to find the maximum profit.
	Note that you cannot sell a stock before you buy one.
*/

//O(n)으로 해결할 수 있었다니!
public class BestTimeToBuyAndSellStock {
	
	public static int findProfit(int[] ar) {
		
		int n = ar.length;
		int max=0; int minPrice = Integer.MAX_VALUE/2;
		
		for(int i=0; i<n; i++) {
			if(ar[i] < minPrice) minPrice = ar[i];
			
			max = Math.max(max, ar[i] - minPrice);
		}
		return max;
	}

	public static void main(String[] args) {
		
		int[] ar = {7,1,5,3,6,4};
		System.out.println(findProfit(ar));
	}
	
}
