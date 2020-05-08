package myStudy.algorithms.dp;

import java.util.Arrays;

//O(N * S) , S=amount
public class CoinChange {
	public static int INF = Integer.MAX_VALUE / 2;
	
	public static int coinChange(int[] coins, int amount) {
		
		int N = coins.length;
		
		int[][] DP = new int[N+1][amount+1];
		
		Arrays.fill(DP[0], INF);
		DP[0][0] = 0;
		
		//per coin
		for(int i=1; i<=N; i++) {
			
			int coinValue = coins[i-1];

			//per value, set coin count
			for(int j=1; j<=amount; j++) {
				
				DP[i][j] = DP[i-1][j];
				
				if( j - coinValue >= 0 && DP[i][j-coinValue] + 1 < DP[i-1][j] ) {
					DP[i][j] = DP[i][j-coinValue] + 1;
				}
			}
		}		
		for(int i = 0; i<DP.length; i++) {
			System.out.println(Arrays.toString(DP[i]));
		}
		if(DP[N][amount] == INF) return -1;


		return DP[N][amount];
	}
	
	// Space Complexity : O(n*S)
	public static int coinChangeSpaceEfficient ( int[] coins, int amount) {
		
		int N = coins.length;
		int[] DP = new int[amount+1];
		Arrays.fill(DP, INF);
		DP[0] = 0;
		
		for(int coinValue : coins) {
			
			for(int j=1; j<=amount; j++) {
				if( j-coinValue >=0 && DP[j-coinValue] + 1 < DP[j])
					DP[j] = DP[j-coinValue] + 1;
			}
		}
		
		if(DP[amount] == INF) return -1;
		return DP[amount];
	}
	
	public static int coinChangeRecursive(int[] coins, int amount) {
		
		
		int[] DP = new int[amount + 1];

		return coinChangeRecursive(coins, amount, DP);
	}
	
	private static int coinChangeRecursive(int[] coins, int amount, int[] DP) {
		
	    // Base cases.
	    if (amount < 0) return -1;
	    if (amount == 0) return 0;
	    if (DP[amount] != 0) return DP[amount];
	    
	    int minValue = INF;
	    for(int coinValue : coins) {
	    	
	    	int newAmount = amount - coinValue;
	    	int value = coinChangeRecursive(coins, newAmount, DP);	
	    	if(value != -1 && value < minValue) minValue = value + 1;
	    }
	    
	    return (minValue != INF) ? DP[amount] = minValue : -1;
	}
	public static void main(String[] args) {
		int[] coins = {4,9,15};
		System.out.println(coinChange(coins,1112));
		System.out.println(coinChangeSpaceEfficient(coins,1112));
		System.out.println(coinChangeRecursive(coins,1112));
	}
}
