package myStudy.algorithms.dp;

/*
	https://github.com/awangdev/LintCode/blob/master/Java/Paint%20Fence.java
	There is a fence with n posts, each post can be painted with one of the k colors.
	You have to paint all the posts such that no more than two adjacent fence posts have the same color.
	Return the total number of ways you can paint the fence.
	Note:
	n and k are non-negative integers.
	Tags: Dynamic Programming
	Similar Problems: (E) House Robber, (M) House Robber II, (M) Paint House, (H) Paint House II
 */

public class PaintFence {

	
	
	
	public static int solve1( int n, int k) {
		
		// 걍 간단한 문제다 수학문제나 다름없다..
		
		if( n == 0 || k == 0 ) return 0;
		if( n == 1 ) return k;
		if( n==2 ) return k*(k-1);
		
		int[] dp = new int[n];
		dp[0] = k;
		dp[1] = k*(k-1);
		for( int i=2; i<n; i++) dp[i] = dp[i-1] *(k-2);
			
		return dp[n-1];
	}
	
	
	// Rolling Array, Space Complexity : O(1) 
	public static int solve2( int n, int k) {
		
		if( n == 0 || k == 0 ) return 0;
		if( n == 1 ) return k;
		if( n==2 ) return k*(k-1);		
		
		int[] dp = new int[2];
		dp[0] = k;
		dp[1] = k*(k-1);
		for( int i=2; i<n; i++) dp[i%2] = dp[(i-1)%2]*(k-2);
		
		return dp[(n-1)%2];
	}
	
	
	
	public static void main(String[] args) {
		
		System.out.println( solve1( 4,10));
		System.out.println( solve2( 4,10));
		
	}
	
	
}
