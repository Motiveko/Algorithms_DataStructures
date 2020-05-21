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
	
	public static int solve1( int[][] matrix) {
		
		int m = matrix.length;
		int n = matrix[0].length;
		
		int[][] dp = new int[m][n];
		Set<Integer> visited = new HashSet<>();
		int res = 0;
		
		for( int i=0; i<m; i++) {
			for( int j=0; j<n; j++) {
				int curr = j + i*n;
				if(!visited.contains(curr)) dfs(dp,i,j,visited,res , curr);
			}
		}
		
		return res;
	}

	
	private static void dfs( int[][] dp, int i, int j, Set<Integer> visited , int res, int curr) {
		
		visited.add(curr);
		
		
	}
	
	
	
	
	
	
}
