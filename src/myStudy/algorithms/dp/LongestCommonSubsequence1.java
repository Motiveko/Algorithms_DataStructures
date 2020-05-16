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

	// 그냥 내가 Naive하게 풀어본 방식, DP를 활용치 못했다, O(mn)쯤..
	public static int solve1(String s1, String s2) {

		int n1 = s1.length();
		int n2 = s2.length();

		int[] arr = new int[n1];
		Arrays.fill(arr, -1);

		for (int i = 0; i < n1; i++) {
			char c1 = s1.charAt(i);
			for (int j = 0; j < n2; j++) {
				if (c1 == s2.charAt(j)) {
					arr[i] = j;
					break;
				}
			}
		}

		int tmp = -1;
		int count = 0;

		for (int i = 0; i < n1; i++) {

			if (arr[i] != -1) {
				tmp = arr[i];
				count = 1;
				for (int j = i + 1; j < n1; j++) {
					if (arr[j] == -1)
						continue;
					else {
						if (tmp < arr[j])
							count++;
						tmp = arr[j];
					}
				}
				break;
			}
		}

		return count;
	}

	// gitHub 버젼,O(nm)인것은 같으나 이쪽이 dynamic programming방식을 활용한 풀이라고 할 수 있다.
	// dp는 현재 경우의 수를 이전 경우의 수 들에서 가져올 수 있어야한다. 가져온 뒤 현재케이스만 따진다.
	public static int solve2(String s1, String s2) {

		// DP[i][j] i
		int n1 = s1.length();
		int n2 = s2.length();
		// 0 1 2 3 4 5 6
		// D A A B C D
		// D A E A C B
		// 0 1 2 3 4 5 6

		// dp[i][j] - > lcs of s1(0,i-1) , s2(0,j-1)
		int[][] dp = new int[n1 + 1][n2 + 1];

		for (int i = 1; i <= n1; i++) {
			for (int j = 1; j <= n2; j++) {
				dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
				if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
					dp[i][j] = Math.max(dp[i - 1][j - 1] + 1, dp[i][j]);
				}
			}
		}

		return dp[n1][n2];
	}

	// Space efficiency!
	public static int solve3(String s1, String s2) {

		int n1 = s1.length();
		int n2 = s2.length();

		int[][] dp = new int[2][n2 + 1];

		for (int i = 1; i <= n1; i++) {
			for (int j = 1; j <= n2; j++) {
				dp[i % 2][j] = Math.max(dp[i % 2][j - 1], dp[(i - 1) % 2][j]);
				if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
					dp[i % 2][j] = Math.max(dp[(i - 1) % 2][j - 1] + 1, dp[i % 2][j]);
				}
			}
		}
		return dp[n1 % 2][n2];
	}

	// Print lcs..
	public static int solve4(String s1, String s2) {

		int n1 = s1.length();
		int n2 = s2.length();

		int[][] dp = new int[2][n2 + 1];
		int[][] pi = new int[n1+1][n2+1];
		
		// prtIndex[1] 부터스캔

		for (int i = 1; i <= n1; i++) {
			for (int j = 1; j <= n2; j++) {
				dp[i % 2][j] = Math.max(dp[i % 2][j - 1], dp[(i - 1) % 2][j]);
				pi[i][j] = (dp[i % 2][j - 1] > dp[(i - 1) % 2][j] ) ? 2 : 1;
				if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
					if (dp[(i - 1) % 2][j - 1] + 1 > dp[i % 2][j]) {
						dp[i % 2][j] = dp[(i - 1) % 2][j - 1] + 1;
						pi[i][j] = 3;
					}
				}
			}
		}
		
		
		int p = n1, q = n2;
		
		while( p>0 && q>0) {
			if( pi[p][q] == 1) {
				p--;
			} else if( pi[p][q] == 2) {
				q--;
			} else {
//				pi[p][q] == 3
				System.out.print(String.valueOf(s2.charAt(q-1)));
				p--;
				q--;
			}
		}
		System.out.println();
		return dp[n1 % 2][n2];
	}

	// Print LCS git hub 정답지
	public static int solve5(String A, String B) {
		if (A == null || B == null || A.length() == 0 || B.length() == 0) {
			return 0;
		}
		int m = A.length();
		int n = B.length();
		int[][] dp = new int[m + 1][n + 1];
		int[][] pi = new int[m + 1][n + 1]; // stores case1, case2, or case3

		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				if (i == 0 || j == 0) {
					dp[i][j] = 0;
					continue;
				}
				dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				pi[i][j] = dp[i - 1][j] > dp[i][j - 1] ? 1 : 2;


				if (A.charAt(i - 1) == B.charAt(j - 1)) {
					dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
					if (dp[i][j] == dp[i - 1][j - 1] + 1) {
						pi[i][j] = 3;
					}
				}
			}
		}

		// Prepare for printing
		char[] res = new char[dp[m][n]];
		int i = m;
		int j = n;
		int w = dp[m][n] - 1;
		while (i > 0 && j > 0) {
			if (pi[i][j] == 1) {
				i--;
			} else if (pi[i][j] == 2) {
				j--;
			} else {// 3
				res[w] = A.charAt(i - 1);
				i--;
				j--;
				w--;
			}
		}

		for (int k = 0; k < dp[m][n]; k++) {
			System.out.print(res[k]);
		}
		System.out.println();

		return dp[m][n];
	}

	public static void main(String[] args) {
		// a b c d e f g h i j k l a s d f a a m n o p
		// e a d c b h a a a g t i o u z l
		// 1 2 3 2 2 3 4 5 6 3 0 4 7 0 0 5
		
		// a b c d e f g h i j k l a s d f a a m n o p
		// e a d c b h a a a g t i o u z l
		
		//a b    2, 3, 2
		//e a d	 2, 1, 2
		
		//       2, 1, 3
		
		// e, a, d, c, b, h, a, a, a, g, t, i, o, u, z, l
		// 1, 2, 3, 2, 2, 3, 4, 5, 6, 3, 0, 4, 7, 0, 0, 5

		// E ABCD
		// ABCD E
		System.out.println(solve1("abcdefghijklasdfaamnop", "eadcbhaaagtiouzl"));

		System.out.println(solve2("abcdefghijklasdfaamnop", "eadcbhaaagtiouzl"));
		System.out.println(solve3("abcdefghijklasdfaamnop", "eadcbhaaagtiouzl"));
		System.out.println(solve5("abcdefghijklasdfaamnop", "eadcbhaaagtiouzl"));
		System.out.println(solve4("abcdefghijklasdfaamnop", "eadcbhaaagtiouzl"));

	}
}
