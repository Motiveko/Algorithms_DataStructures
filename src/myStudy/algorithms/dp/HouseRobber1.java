package myStudy.algorithms.dp;

import java.util.*;

/*
	https://github.com/awangdev/LintCode/blob/master/Java/198.%20House%20Robber.java 	
	You are a professional robber planning to rob houses along a street. 
	Each house has a certain amount of money stashed, 
	the only constraint stopping you from robbing each of them is that 
	adjacent houses have security system connected and it will automatically 
	contact the police if two adjacent houses were broken into on the same night.
	Given a list of non-negative integers representing the amount of money of each house, 
	determine the maximum amount of money you can rob tonight without alerting the police.
	Example
	Given [3, 8, 4], return 8.
	Challenge
	O(n) time and O(1) memory.
	Tags Expand 
	Dynamic Programming
*/

public class HouseRobber1 {
	
	// 스스로 풀어내지 못하였다..
	
	
	//solve1 : time : O(n) , memory : O(n)
	public static int solve1(int[] arr) {
		
		int n = arr.length;
		// dp[i] : arr의 앞에서 i개 요소들에 대해 rubber가 가질 수 있는 최대값
		int[] dp = new int[n+1];
		dp[0] = 0;
		dp[1] = arr[1];
		
		
		for( int i = 2; i<=n; i++) {
			dp[i] = Math.max( dp[i-1], dp[i-2] + arr[i-1]);
		}
		
		return dp[n];
	}
	   
	//solve2 : time : O(n), memory : O(1)
	public static int solve2(int[] arr) {
		
		int n = arr.length;
		
		int[] dp = new int[2];
		dp[0] = 0;
		dp[1] = arr[1];
		
		for(int i = 2; i<n+1; i++) {
			
			int val = Math.max(dp[1], dp[0] + arr[i-1]);
			
			dp[0] = dp[1];
			dp[1] = val;
		}
		
		return dp[1];
	}
	
	//solve3 : solve2를 약간 더 복잡하게 만든 버젼 , rolling array
	public static int solve3(int[] arr) {
		
		int n = arr.length;
		int[] dp = new int[2];
		
		dp[0] = 0;
		dp[1] = arr[1];
		
		for( int i=2; i<n+1; i++) {
			dp[i%2] = Math.max(dp[(i-1)%2], dp[i%2] + arr[i-1]);
		}
		
		return dp[n%2];
	}
	
	//test
	public static void main(String[] args) {
		
		int[] arr1 = new int[] {1,3,5,9,7,1,0,11,456,7,654,4,7,1,132,4};
		int[] arr2 = new int[] {7,9,8,4,3,1,52,85,8,7,4,1,68,7,4,52,56,8};
		int[] arr3 = new int[] {8,7,4,6,8,1,3,5,4,8,4,8,7,101,0,1,1,3,4,5,6,789,41};
		
		System.out.println(solve1(arr1) + " = " + solve3(arr1));
		System.out.println(solve1(arr2) + " = " + solve3(arr2));
		System.out.println(solve1(arr3) + " = " + solve3 (arr3));
	}
}
