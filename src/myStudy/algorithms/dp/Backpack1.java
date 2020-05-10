package myStudy.algorithms.dp;

import java.util.Arrays;

/*
	https://github.com/awangdev/LintCode/blob/master/Java/Backpack.java
	
	Given n items with size A[i], an integer m denotes the size of a backpack. 
	How full you can fill this backpack?
	Example
	If we have 4 items with size [2, 3, 5, 7], the backpack size is 11, we can select [2, 3, 5], 
	so that the max size we can fill this backpack is 10. 
	If the backpack size is 12. we can select [2, 3, 7] so that we can fulfill the backpack.
	You function should return the max size we can fill in the given backpack.
	Note
	You can not divide any item into small pieces.
	Challenge
	O(n x m) time and O(m) memory.
	O(n x m) memory is also acceptable if you do not know how to optimize memory.
	Tags Expand 
	LintCode Copyright Dynamic Programming Backpack
	
	CoinChange와 매우흡사
 */
public class Backpack1 {

	public static final int INF = Integer.MAX_VALUE / 2;
	
	public static int solve1(int[] items, int size) {
		
		int[][] DP = new int[size + 1][items.length];
		for( int[] layer : DP) Arrays.fill(layer, INF);
		Arrays.fill(DP[0], 0);
		
		for( int j = 0; j < items.length ; j++) {
			int value = items[j];
			for( int i = 1; i <= size ; i++) {
				if( i-value >=0 && DP[i-value][j]!=INF) DP[i][j] = DP[i-value][j]+1;
				if( j-1>=0 ) DP[i][j] = Math.min(DP[i][j], DP[i][j-1]);
			}
		}
		
		int res = DP[size][items.length-1];
		
		return res==INF ? -1 : res;
	}
	
	
	//space efficiency, O(m) where m = size;
	public static int solve2(int[] items, int size) {
		
		int[] DP = new int[size+1];
		Arrays.fill(DP, INF);
		
		DP[0] = 0;
		
		for( int i = 0; i < items.length; i++) {
			int value = items[i];
			for (int j=1; j <= size ; j++	) {
				if( j-value >=0) DP[j] = Math.min(DP[j], DP[j-value]+1);
			}
		}
		
		int res = DP[size];
		
		return res==INF ? -1 : res;
		
	}
	
	public static void main(String[] args) {
		int[] items1 = {4,9,15};
		int size1 = 1112;
		
		System.out.println(solve1(items1,size1) + " = " + solve2(items1,size1));
	}
}
