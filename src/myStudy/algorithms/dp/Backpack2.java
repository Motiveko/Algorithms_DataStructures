package myStudy.algorithms.dp;

import java.util.Arrays;

/*
 	https://github.com/awangdev/LintCode/blob/master/Java/Backpack%20II.java
	Given n items with size Ai and value Vi, and a backpack with size m. 
	What's the maximum value can you put into the backpack?
	Example
	Given 4 items with size [2, 3, 5, 7] and value [1, 5, 2, 4], and a backpack with size 10. 
	The maximum value is 9.
	Note
	You cannot divide item into small pieces and the total size of items you choose should smaller or equal to m.
	Challenge
	O(n x m) memory is acceptable, can you do it in O(m) memory?
	Tags Expand 
	LintCode Copyright Dynamic Programming Backpack
*/
public class Backpack2 {

	
	
	public static int solve1 ( int[] volumes , int[] values, int size) {
		/*
		 * strategy : 먼저 각 item이 지니는 value/volume 비중을 정해 높은순서대로 집어넣는다. -> 비중순으로 정렬한다.
		 * 가장 비중 큰걸로 size를 한번에 채울 수 있으면 return , 안돼면 다음 비중을 가지걸로 넘어간다
		 * 다음 비중의 item을 돌릴때에는 갯수의 최소화가 아닌 value의 최대화가 목적이므로 이전 item의 DP값을 받을 수 있는거는 무조건 받아온다.
		 * DP가 value값이 된다.
		 */
		
		//test
		int[][] DP = new int[size + 1][volumes.length];
		for( int[] d : DP) Arrays.fill(d, -1);
		Arrays.fill(DP[0], 0);
		
		double[] weight = new double[volumes.length];
		for( int i= 0 ; i<weight.length; i++) 
			weight[i] = (double) values[i]/ (double) volumes[i];
		
		//오름차순 정렬알고리즘, 버블sort	
		for( int i = 0; i <= weight.length - 2; i++) {
			for( int j = weight.length - 2; j >= i ; j--) {
				if( weight[j] < weight[j+1]) {
					volumes = change(volumes,j);
					values = change(values,j);
					weight = change(weight,j);
				}
			}
		}
		
		for( int j=0; j< volumes.length; j++) {
			
			int volume = volumes[j];
			
			for( int i=1; i<=size ; i++) {
				if(i-volume >= 0 && DP[i-volume][j] >=0 ) DP[i][j] =  Math.max(DP[i][j], DP[i-volume][j] + values[j]);
				if( j-1 >=0 && DP[i][j-1] >0 ) DP[i][j] = Math.max(DP[i][j], DP[i][j-1]);
			}
			
			if(DP[size][j] != -1) return DP[size][j];
		}
		
		
		return -1;
	}
	//space efficiency, O(m)
	public static int solve2(int[] volumes, int[] values, int size) {
		
		int[] DP = new int[size+1];
		Arrays.fill(DP, -1);
		DP[0]=0;

		double[] weight = new double[volumes.length];
		for( int i= 0 ; i<weight.length; i++) 
			weight[i] = (double) values[i]/ (double) volumes[i];
		
		//오름차순 정렬알고리즘, 버블sort	
		for( int i = 0; i <= weight.length - 2; i++) {
			for( int j = weight.length - 2; j >= i ; j--) {
				if( weight[j] < weight[j+1]) {
					volumes = change(volumes,j);
					values = change(values,j);
					weight = change(weight,j);
				}
			}
		}
		
		for( int j=0; j < volumes.length ; j++) {
			int volume = volumes[j];
			for( int i = 1; i<=size; i++) {
				if( i-volume >=0 && DP[i-volume] != -1) DP[i] = Math.max(DP[i], DP[i-volume] + values[j]);
			}
			if( DP[size] != -1) return DP[size];
		}
		
		return -1;
	}
	
	private static int[] change(int[] arr, int i) {
		int tmp = arr[i];
		arr[i] = arr[i+1];
		arr[i+1] = tmp;
		return arr;
	}
	
	private static double[] change(double[] arr, int i) {
		double tmp = arr[i];
		arr[i] = arr[i+1];
		arr[i+1] = tmp;
		return arr;
	}
	
	public static void main(String[] args) {
		int[] volumes1 = {2,3,5,7};
		int[] values1 = {1,5,2,4};
		int size1 = 10;
		System.out.println(solve1(volumes1,values1,size1) + " = " + solve2(volumes1,values1,size1));
	}
}
