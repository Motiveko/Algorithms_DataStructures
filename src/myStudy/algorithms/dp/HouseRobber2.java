package myStudy.algorithms.dp;

/*
 	https://github.com/awangdev/LintCode/blob/master/Java/House%20Robber%20II.java
 	Note: This is an extension of House Robber.
	After robbing those houses on that street, the thief has found himself a new place for his thievery 
	so that he will not get too much attention. This time, all houses at this place are arranged in a circle. 
	That means the first house is the neighbor of the last one. 
	Meanwhile, the security system for these houses remain the same as for those in the previous street.
	Given a list of non-negative integers representing the amount of money of each house, 
	determine the maximum amount of money you can rob tonight without alerting the police.

 */
public class HouseRobber2 {

	// 이것도 내스스로 못풀었다. ㅜ ㅜ 경우의수를 걍 나눠서 하는 방식이었다..

	public static int solve1(int[] arr) {

		int n = arr.length;

		int[][] dp = new int[2][n + 1];

		// 무조건 arr[0]은 포함안한다
		dp[0][1] = 0;
		// 무조건 arr[0]을 포함한다.
		dp[1][1] = arr[0];

		for (int i = 2; i < n; i++) {
			// 두경우 다 돌린다..
			dp[0][i] = Math.max(dp[0][i - 1], dp[0][i - 2] + arr[i - 1]);
			dp[1][i] = Math.max(dp[1][i - 1], dp[1][i - 2] + arr[i - 1]);
		}

		dp[0][n] = Math.max(dp[0][n - 1], dp[0][n - 2] + arr[n - 1]);
		dp[1][n] = dp[1][n - 1];

		return Math.max(dp[0][n], dp[1][n]);
	}

	// Space Complexity : O(1) , rolling array
	public static int solve2(int[] arr) {

		int n = arr.length;
		int[][] dp = new int[2][2];

		dp[0][1] = 0;
		dp[1][1] = arr[0];

		for (int i = 2; i < n; i++) {
			dp[0][i % 2] = Math.max(dp[0][(i - 1) % 2], dp[0][i % 2] + arr[i - 1]);
			dp[1][i % 2] = Math.max(dp[1][(i - 1) % 2], dp[1][i % 2] + arr[i - 1]);
		}

		dp[0][n % 2] = Math.max(dp[0][(n - 1) % 2], dp[0][n % 2] + arr[n - 1]);
		dp[1][n % 2] = dp[1][0];

		return Math.max(dp[0][n % 2], dp[1][n % 2]);

	}

	//1차원 dp로, last is robbed or not 두가지를 행한다 , SpaceComplexity O(n)
	public static int solve3(int[] arr) {

		int n = arr.length;
		
		//last house is not robbed
		int[] dp1 = new int[n];
		//last house is robbed
		int[] dp2 = new int[n];
		
		//case1
		dp1[0] = arr[0];
		dp1[1] = Math.max(arr[0], arr[1]);
			
		//n번재는 안털렸기때문에 고려치않는다.
		for(int i=2; i<n-1; i++) {
			dp1[i] = Math.max(dp1[i-1], dp1[i-2] + arr[i]);
		}
		dp1[n-1] = dp1[n-2];
		
		//case2
		dp2[0] = 0;
		dp2[1] = arr[1];
		
		for(int i=2; i<n; i++) {
			dp2[i] = Math.max(dp2[i-1], dp2[i-2] + arr[i]);
		}
		
		
		return Math.max(dp1[n-1], dp2[n-1]);
	}

	public static void main(String[] args) {

		int[] arr1 = { 1, 2, 3, 4, 8, 6, 4, 15, 6 };
		int[] arr2 = { 1, 3, 4, 8, 7, 695, 1, 56, 8 };

		System.out.println(solve1(arr1) + " = " + solve2(arr1) + " = " + solve3(arr1));
		System.out.println(solve1(arr2) + " = " + solve2(arr2) + " = " + solve3(arr2));
	}
}
