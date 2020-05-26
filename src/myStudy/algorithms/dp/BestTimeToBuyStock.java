package myStudy.algorithms.dp;

import java.util.Arrays;

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

	// 못풀었다. LCS1 문제와 약간 비슷한 방식이 사용된다..
	// O(nm) : m은 최대 팔수 있는 횟수에 비례하는 각 시점에서 가능한 상태의 경우의 수
	public static int solve1(int[] prices) {

		int n = prices.length;
		// 최대 두번 구매 가능하므로, 각 시점에 가능한 상태는
		// 0: 한번도 안삼 , 1: 한번 삼, 2: 한번 사고 팔음, 3: 한번 사고 팔고 한번 더 삼 , 4: 두번 사고 팜
		// dp[i][j]는 i-1번째 날에 j번째 상태일 때 최대이익상태로, 1,3같은 경우에는 지금팔때 잠정이익을 말한다!!!!!!
		int[][] dp = new int[n][5];
	
		for( int i = 1; i < n; i++ ) {
			
			int dailyMargin = prices[i]-prices[i-1];
			
			for( int j=1; j<5; j++) {
				
				if( j%2 ==0) {
										// 오늘 주식을 팔 때	                          이전에 팔 때 중 최대값을 선택
					dp[i][j] = Math.max(dp[i-1][j-1] + dailyMargin, dp[i-1][j]);
				} else {
					
					//오늘까지의 잠정이익		
					// j==3 일때는 2일때까지의 이익 + 잠정이익
					//  										  //음수면 0을넣는다는 전략.
					dp[i][j] = Math.max(dp[i-1][j] + dailyMargin, dp[i-1][j-1]);	
				}
			}
		}
			
			
		return dp[n-1][4];
	}
	
	// Rolling Array, Space Complexity : O(m)
	public static int solve2( int[] prices) {
		
		int n = prices.length;
		int[][]  dp = new int[2][5];
		
		for( int i=1; i<n; i++) {
			
			int dailyMargin = prices[i] - prices[i-1];
//			System.out.print(dp[i%2][0]+", ");
			for( int j=1; j<5; j++) {
				if( j%2==0) {
					dp[i%2][j] = Math.max( dp[(i-1)%2][j], dp[(i-1)%2][j-1] + dailyMargin);
				} else {
					dp[i%2][j] = Math.max(dp[(i-1)%2][j] + dailyMargin, dp[(i-1)%2][j-1]);
				}
//				System.out.print(dp[i%2][j]+", ");
			}
//			System.out.println();
		}
		
		return dp[(n-1)%2][4];
	}

	public static void main(String[] args) {

		int[] stock1 = { 4, 4, 6, 1, 1, 4, 2, 5 };
		int[] stock2 = { 1, 4, 2, 3, 5, 7, 4, 9 };
		
		System.out.println(solve1(stock1));
		System.out.println(solve1(stock2));
		
		System.out.println(solve2(stock1));
		System.out.println(solve2(stock2));
	}
}
