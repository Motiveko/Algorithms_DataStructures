package myStudy.algorithms.dp;

import java.util.Arrays;

/*
	https://github.com/awangdev/LintCode/blob/master/Java/Backpack%20V.java
	
	Given n items with size nums[i] which an integer array and all positive numbers. 
	An integer target denotes the size of a backpack. 
	Find the number of possible ways of filling the backpack.
	Each item may only be used once
 */
public class Backpack5 {
	
	//결국 스스로 해내지 못했다.. 중복허용을 피하는 방법.. 매우 대단하다..
	// Naive, Space efficiency O(n*m)
	public static int solve1(int[] nums, int target) {
		
		if(nums==null || target==0) throw new IllegalArgumentException("invalid input");
		int n = nums.length;
		
		int[][] DP = new int[n+1][target+1];
		DP[0][0] = 1;
		
		for( int i=1; i<=n; i++) {
			int val = nums[i-1];
			for( int j=0; j<=target; j++) {
				DP[i][j] = DP[i-1][j];
				if( j-val >=0 ) DP[i][j] += DP[i-1][j-val];	
			}
		}
		for( int[] d : DP) System.out.println(Arrays.toString(d));
		return DP[n][target];
	}
	
	//Rolling array, space efficiency O(2*m), m==target
	public static int solve2(int[] nums, int target) {
		
		int[][] DP = new int[2][target+1];
		
		DP[1][0]= 1;
		
		for( int i=0; i<nums.length; i++) {
			int val = nums[i];
			
			for( int j=0; j<= target ; j++) {
				DP[i%2][j] = DP[(i+1)%2 ][j];
				if( j - val >= 0 ) DP[i%2][j] += DP[(i+1)%2][j-val];
			}
		}
		
		return DP[(nums.length-1)%2][target];
	}
	
	// space efficiency O(m)
	public static int solve3(int[] nums, int target) {
		
		int[] DP = new int[target+1];
		
		DP[0] = 1;
		int maxVal = 0;
		for( int i=0; i<nums.length; i++) {
			int val = nums[i];
			maxVal += val;
			for( int j=0; j<=Math.min(maxVal, target); j++) {
				if( j-val >= 0) DP[j] += DP[j-val];
			}
		}
		
		return DP[target];		
	}

	public static void main(String[] args) {

		System.out.println(solve1(new int[] { 2, 3, 5, 7, 8 }, 115));
		System.out.println(solve2(new int[] { 2, 3, 5, 7, 8 }, 15));
		System.out.println(solve2(new int[] { 2, 3, 5, 7, 8 }, 15));
	}
}
