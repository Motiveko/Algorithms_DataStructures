package myStudy.algorithms.dp;


/*
	https://github.com/awangdev/LintCode/blob/master/Java/Triangles.java
		
	Given a triangle, find the minimum path sum from top to bottom. 
	Each step you may move to adjacent numbers on the row below.
	For example, given the following triangle
	[
	     [2],
	    [3,4],
	   [6,5,7],
	  [4,1,8,3]
	]
	The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
	Note:
	Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.
	Thinking process:
	1. Bottom-up
		- Start from the bottom row, get all values of this row. Note: in triangle, height = cols at each row. So row X has X numbers.
		- Start from (n - 1)th row and run up: calculate min from lower level + current node value.
		- Depending what is wanted, here we use a 2D int arraya and return the min sum.
*/

import java.util.*;

public class Triangles {

	final static int INF = Integer.MAX_VALUE/2;
	public static int solve1( List<List<Integer>> triangle) {
		// top to bottom, my way
		int n = triangle.size();
		
		int[][] memo = new int[n][n];
		for( int[] m : memo) Arrays.fill(m,INF);
		
		memo[0][0] = triangle.get(0).get(0);
		
		for( int i=1; i<n; i++) {
			List<Integer> layer = triangle.get(i);
			for(int j=0; j<layer.size(); j++) {
				//맨왼쪽노드	
				if( j==0 ) memo[i][j] = memo[i-1][j];
				else memo[i][j] = Math.min(memo[i-1][j-1], memo[i-1][j]);
				
				memo[i][j] += layer.get(j);
			}
		}
		
		int minRes = INF;
		for( int bottom : memo[n-1]) minRes = Math.min(minRes, bottom);
//		for( int[] m : memo) System.out.println(Arrays.toString(m));
		
		return minRes;
	}
	
	
	// Space Complexity : O(n) + 1(tmp)
	public static int solve2(List<List<Integer>> triangle) {
		
		if(triangle.size() == 0 ) throw new IllegalArgumentException("Invalid Triangle");
		
		int n = triangle.size();
		int[] dp = new int[n];
		
		Arrays.fill(dp, INF);
		dp[0] = triangle.get(0).get(0);
		
		System.out.println(Arrays.toString(dp));
		for(int i=1; i<n; i++) {
			List<Integer> layer = triangle.get(i);
			int tmp = dp[0];
			for( int j=0; j<=i; j++) {
				
				if( j== 0 ) dp[0] += layer.get(0);
				else {
					dp[j] = Math.min(tmp + layer.get(j), dp[j] + layer.get(j));
					tmp = dp[j]; 
				}
			}
			System.out.println(Arrays.toString(dp));
		}
		int res = INF;
		for( int value : dp ) res = Math.min(res, value);
		
		return res;
	}
	
	//dfs 방식, 뭐그리 효율적이지는 않으나 연습을위해 구현해본다.
	public static int solve3(List<List<Integer>> triangle) {
		
//		pathSum[i][j] = Math.min(dfs(i+1,j), dfs(i+1, j+1))
		int n = triangle.size();
		int[][] pathSum = new int[n][n];
		
		
		return dfs(0,0,triangle,pathSum); 
		
	}
	
	private static int dfs( int i , int j, List<List<Integer>> triangle ,int[][] pathSum){
		
		List<Integer> layer = triangle.get(i);
		if( i==0) pathSum[i][j] = layer.get(j);
		if( j==0) pathSum[i][j] = pathSum[i-1][j];
		
		
		return pathSum[i][j];
	}
	
	public static void main(String[] args) {
		
		List<List<Integer>> triangle = new ArrayList<>();
		List<Integer> layer0 = new ArrayList<>();
		List<Integer> layer1 = new ArrayList<>();
		List<Integer> layer2 = new ArrayList<>();
		List<Integer> layer3 = new ArrayList<>();
		layer0.add(2);
		layer1.add(3);
		layer1.add(4);
		layer2.add(6);
		layer2.add(5);
		layer2.add(7);
		layer3.add(4);
		layer3.add(1);
		layer3.add(8);
		layer3.add(3);
		triangle.add(layer0);
		triangle.add(layer1);
		triangle.add(layer2);
		triangle.add(layer3);
		
		System.out.println(solve1(triangle));
		System.out.println(solve2(triangle));
	}
}
