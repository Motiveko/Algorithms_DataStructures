package myStudy.algorithms.dp;

import java.util.Arrays;

/*
	https://github.com/awangdev/LintCode/blob/master/Java/Longest%20Common%20Subsequence.java
	
	Given two strings, find the longest comment subsequence (LCS).
	Your code should return the length of LCS.
	Example
	For "ABCD" and "EDCA", the LCS is "A" (or D or C), return 1
	For "ABCD" and "EACB", the LCS is "AC", return 2
	Clarification
	What's the definition of Longest Common Subsequence?
	    * The longest common subsequence (LCS) problem is to find the longest subsequence common to all sequences in a set of sequences (often just two). (Note that a subsequence is different from a substring, for the terms of the former need not be consecutive terms of the original sequence.) It is a classic computer science problem, the basis of file comparison programs such as diff, and has applications in bioinformatics.
	    * https://en.wikipedia.org/wiki/Longest_common_subsequence_problem
	Tags Expand 
	LintCode Copyright Longest Common Subsequence Dynamic Programming
	
	Subsequence는 Substring보다 포괄적이다. ABCD 에서 AC나 AD도 Subsequence임
	

*/

public class LongestCommonSubsequence1 {

	// 그냥 내가 Naive하게 풀어본 방식, DP를 활용치 못했다, 중복된 문자 있는경우도 고려치못함
	public static int solve1(String s1, String s2) {
		
		int n1 = s1.length();
		int n2 = s2.length();
		
		int[] arr = new int[n1];
		Arrays.fill(arr, -1);
		
		for( int i = 0; i<n1; i++) {
			char c1 = s1.charAt(i);
			for( int j=0; j<n2; j++) {
				if( c1==s2.charAt(j)) {
					arr[i] = j;
					break;
				}
			}
		}
		
		int tmp=-1;
		int count = 0;
		
		for( int i=0; i<n1; i++) {
			
			if( arr[i]!=-1) {
				tmp = arr[i];
				count = 1;
				for( int j=i+1 ; j<n1; j++) {
					if( arr[j]==-1) continue;
					else {
						if(tmp<arr[j]) count++;
						tmp = arr[j];
					}
				}
				break;
			}
		}
		
		return count;
	}

	public static int solve2(String s1, String s2) {
		
	
		
		return 0;
	}
	
	public static void main(String[] args) {
		
		System.out.println(solve1("ABCDE","EADCB"));
	}
}
