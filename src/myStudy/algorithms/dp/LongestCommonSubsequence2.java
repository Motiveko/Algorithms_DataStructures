package myStudy.algorithms.dp;

import java.util.Arrays;

/*
	https://github.com/awangdev/LintCode/blob/master/Java/Longest%20Increasing%20Continuous%20subsequence%20II.java
	
	LintCode, only available in algorithm2.
	Give you an integer matrix (with row size n, column size m)，
	find the longest increasing continuous subsequence in this matrix. 
	(The definition of the longest increasing continuous subsequence here 
	can start at any row or column and go up/down/right/left any direction).
	Example
	Given a matrix:
	[
	  [1 ,2 ,3 ,4 ,5],
	  [16,17,24,23,6],
	  [15,18,25,22,7],
	  [14,19,20,21,8],
	  [13,12,11,10,9]
	]
	return 25
	Challenge
	O(nm) time and memory.
	Tags Expand 
	Dynamic Programming
 */

import java.util.*;
public class LongestCommonSubsequence2 {

	
	// dfs + recursive 방식!
	
	static Integer count = 0;
	static Integer res = 0;
	
	public static int solve1( int[][] matrix) {
		
		int m = matrix.length;
		int n = matrix[0].length;
		int[][] dp = new int[m][n];
		boolean[][] visited = new boolean[m][n];
		

		for( int i=0; i<m; i++) {
			for( int j=0; j<n; j++) {
				if(!visited[i][j]) dfs(dp, matrix, i, j, visited);
			}
		}
		System.out.println("count : " + count);
		for( int i=0; i<m; i++) System.out.println(Arrays.toString(dp[i]));
		return res+1;
	}

	
	private static void dfs( int[][] dp, int[][] matrix, int i, int j, boolean[][] visited ) {
		
		count++;

		visited[i][j] = true;
		if( i-1 >= 0 && matrix[i][j] < matrix[i-1][j]) {
			dp[i-1][j] = Math.max(dp[i-1][j], dp[i][j] + 1);
			res = Math.max(dp[i-1][j],res);
			dfs(dp,matrix,i-1,j,visited);
		}
		if( j-1 >= 0 && matrix[i][j] < matrix[i][ j-1]) {
			dp[i][j-1] = Math.max(dp[i][j-1], dp[i][j] + 1);
			res = Math.max(dp[i][j-1],res);
			dfs(dp,matrix,i,j-1,visited);
		}
		if( i+1 < matrix.length && matrix[i][j] < matrix[i+1][j]) {
			dp[i+1][j] = Math.max(dp[i+1][j], dp[i][j] + 1);
			res = Math.max(dp[i+1][j],res);
			dfs(dp,matrix,i+1,j,visited);
		}
		if( j+1 < matrix[0].length && matrix[i][j] < matrix[i][j+1]) {
			dp[i][j+1] = Math.max(dp[i][j+1], dp[i][j] + 1);
			res = Math.max(dp[i][j+1],res);
			dfs(dp,matrix,i,j+1,visited);
		}

	}
	
	public static void main(String[] args) {
				
		int[][] matrix = new int[5][5];
		
		matrix[0] = new int[] {1,2,3,4,5};
		matrix[1] = new int[] {16,17,24,23,6};
		matrix[2] = new int[] {15,18,25,22,7};
		matrix[3] = new int[] {14,19,20,21,8};
		matrix[4] = new int[] {13,12,11,10,9};
		
		System.out.println(solve1(matrix));

	}
	
	
	
	
}
