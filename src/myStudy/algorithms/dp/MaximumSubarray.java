package myStudy.algorithms.dp;

public class MaximumSubarray {

	//O(n^2)
	public static int maximumSubarrayValueNaive(int[] ar) {
		
		int n = ar.length;
		
		int max = Integer.MIN_VALUE/2;
		//길이
		for(int i=0; i<n; i++) {

			//시작점
			for(int k=0; k+i<n; k++) {
				int sum=0;	
				//합
				for(int j=k; j <=k+i ; j++) sum += ar[j];
			
				max = Math.max(max, sum);
			}
		}
		return max;
	}
	
	//O(n)...!!
	public static int maximumSubarrayValue(int[] ar) {
		int n = ar.length;
		
		int maxVal, sum;
		maxVal = sum = ar[0];
		
		for(int i=1; i<n; i++) {
			
			// if sum이 +라면 : sum += ar[i], sum이 -라면 sum = ar[i]
			if( ar[i] < sum + ar[i]) sum += ar[i];
			else sum = ar[i];
			maxVal = Math.max(sum, maxVal);
		}
		
		return maxVal;
	}
	
	public static void main(String[] args) {
		// ans : -5, -1, 41
		 System.out.println(maximumSubarrayValue(new int[] {-5}));
		 System.out.println(maximumSubarrayValue(new int[] {-5, -4, -10, -3, -1, -12, -6}));
		 System.out.println(maximumSubarrayValue(new int[] {1, 2, 1, -7, 2, -1, 40, -89}));		
	}
}
