package myStudy.algorithms.dp;


/*
	https://github.com/awangdev/LintCode/blob/master/Java/Perfect%20Squares.java
	
	Given a positive integer n, find the least number of perfect square numbers 
	(for example, 1, 4, 9, 16, ...) which sum to n.
	For example, given n = 12, return 3 because 12 = 4 + 4 + 4; 
	given n = 13, return 2 because 13 = 4 + 9.
 */

import java.util.*;

public class PerfectSquare {

	final static int INF = Integer.MAX_VALUE/2;

	
	public static int solve1(int n) {
		
		// Coin Change 와 같은 방식으로 접근할 수 있을듯하다.
		
		
		// n is perfect square itself 
		if( Math.sqrt(n) == Math.floor(Math.sqrt(n))) return 1;
		
		List<Integer> pSquare = new ArrayList<>();
		
		// make perfect Square list
		for( int i=1; i<=Math.floor(Math.sqrt(n)); i++) {
			if( !pSquare.contains(i*i) ) pSquare.add(i*i);
		}
		
		int m = pSquare.size();
		
		int[][] dp = new int[n+1][m+1];
		
		for( int i=1; i<=n; i++) Arrays.fill(dp[i], INF);
		Arrays.fill(dp[0], 0);
		
		// 
		for( int j = 1; j<=m ; j++) {
			int cur = pSquare.get(j-1);
			
			// 1~n 까지 구성할 수 있는 경우의수
			for ( int i = 1 ; i <=n; i++) {
				
				dp[i][j] = dp[i][j-1];
				if( i-cur >= 0 && dp[i-cur][j]!=INF) dp[i][j] = Math.min(dp[i][j], dp[i-cur][j]+1);
			}
		}
//		for( int i=0; i<=n; i++) System.out.println(Arrays.toString(dp[i]));

		return (dp[n][m]==INF) ? -1 : dp[n][m];
	}
	
	
	// Space Complexity : O(n)
	public static int solve2(int n) {
		
		if( Math.sqrt(n) == Math.floor(Math.sqrt(n))) return 1;

		
		List<Integer> pSquare = new ArrayList<>();		
		for( int i=1; i<=Math.floor(Math.sqrt(n)); i++) {
			if( !pSquare.contains(i*i) ) pSquare.add(i*i);
		}
		
		int m = pSquare.size();

		int[] dp = new int[n+1];
		Arrays.fill(dp, INF);
		dp[0]= 0;
		
		for( int i=0; i<m; i++) {
			int cur = pSquare.get(i);
			
			for( int j=1; j<=n; j++) {
				
				if( j-cur >=0 && dp[j-cur]!=INF) dp[j] = Math.min(dp[j], dp[j-cur]+1);
			}
		}
		
//		System.out.println(Arrays.toString(dp));
		return dp[n] == INF ? -1 : dp[n];
		
	}
	
	
	
	
	public static void main(String[] args) {
		
		System.out.println(solve1(115));
		System.out.println(solve2(115));
		
	}
}
