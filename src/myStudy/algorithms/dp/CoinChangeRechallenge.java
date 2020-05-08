package myStudy.algorithms.dp;

import java.util.Arrays;

public class CoinChangeRechallenge {
	static final Integer INF = Integer.MAX_VALUE;
	// Dp
	public static int solve1(int[] coins, int target) {

		Integer[][] DP = new Integer[target + 1][coins.length];
		

		
		Arrays.fill(DP[0], 0);
		for( int i=1; i<= target ; i++) Arrays.fill(DP[i], INF);
		
		for( int j=0; j<coins.length ; j++) {
			for( int i = 1; i<= target; i++) {
				if( i - coins[j] >= 0 && DP[i-coins[j]][j] != INF) DP[i][j] = DP[i-coins[j]][j] + 1;
				if( j-1 >=0) DP[i][j] = Math.min(DP[i][j],DP[i][j-1]);
				
			}
		}
		
		return DP[target][coins.length-1] == INF ? -1 :  DP[target][coins.length-1];
	}
	
	//Space efficient
	public static int solve2(int[] coins, int target) {
		
		int[] DP = new int[target + 1];
		
		Arrays.fill(DP, INF);
		DP[0] = 0;
		
		for( int i = 0; i < coins.length ; i++) {
			int coinValue = coins[i];
			for( int j = 1; j <= target; j++	) {
				DP[j] = ( j - coins[i] >= 0 && DP[j-coins[i]] != INF) ? Math.min( DP[j-coins[i]] + 1 ,DP[j] ) : DP[j];
			}
		}
		
		if(DP[target]==INF) return -1;
		
		return DP[target];
	}
	
	//Recursive 
	public static int solve3(int[] coins, int target) {
		
		int[] DP = new int[target + 1];
		Arrays.fill(DP, INF);
		DP[0] = 0;
		int value = coinChangeRecursive(coins,target,DP);
		
		return value==INF ? -1 : value;
	}
	
	private static int coinChangeRecursive(int[] coins, int target, int[] DP) {
		
		if( target < 0) return INF;
		if( DP[target] != INF ) return DP[target];
		
		int minCount = INF;
		for( int coin : coins) {
			
			int newTarget = target - coin;
			int value = coinChangeRecursive(coins, newTarget, DP);
			if( value != INF ) minCount = Math.min( minCount, value + 1);
		}
			
		return minCount;

	}

	public static void main(String[] args) {
		
		int[] coins1 = {1,2,5};
		int target1 = 11;
		
		int[] coins2 = {2};
		int target2 = 3;
		
		System.out.println(solve1(coins1,target1) + " = " + solve2(coins1,target1) + " = " + solve3(coins1,target1));
		System.out.println(solve1(coins2,target2) + " = " + solve2(coins2,target2) + " = " + solve3(coins2,target2));
	}
	
}
