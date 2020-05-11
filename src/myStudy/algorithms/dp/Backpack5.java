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

	public static void main(String[] args) {

		System.out.println(solve1(new int[] { 2, 3, 5, 7, 8 }, 10));
	}
}
