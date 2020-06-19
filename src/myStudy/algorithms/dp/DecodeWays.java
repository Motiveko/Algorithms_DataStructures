package myStudy.algorithms.dp;

import java.util.Arrays;

/*
https://github.com/awangdev/LintCode/blob/master/Java/91.%20Decode%20Ways.java

A message containing letters from A-Z is being encoded to numbers using the following mapping:
'A' -> 1
'B' -> 2
...
'Z' -> 26
Given an encoded message containing digits, determine the total number of ways to decode it.
For example,
Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).
The number of ways decoding "12" is 2.
*/

public class DecodeWays {
	
	public static int solve1( String s) {
				
		//5123 abc lc aw   11111 aaaa 
		// 111 aaa ka
		int n = s.length();
		
		int[] dp = new int[n];
		Arrays.fill(dp, 1);
		

		
		return dp[n-1];
	}
	
	public static void main(String[] args) {
		
		System.out.println(solve1("123"));
	}
}
