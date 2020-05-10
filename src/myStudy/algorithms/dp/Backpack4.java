package myStudy.algorithms.dp;

/*
	https://github.com/awangdev/LintCode/blob/master/Java/Backpack%20VI.java
	
	Given an integer array nums with all positive numbers and no duplicates, 
	find the number of possible combinations that add up to a positive integer target.
	Notice
	A number in the array can be used multiple times in the combination. 
	Different orders are counted as different combinations.
*/

public class Backpack4 {
	
	public static int solve1( int[] nums, int target) {
		//밑에서 한 거 따라하는거다. 나는 스스로 못풀었다..
		
		int[] DP = new int[target+1];
		int n = nums.length;
		DP[0] = 1;
		
		for( int i=1; i<=target ; i++) {
			for( int j=0; j<n; j++) {
				if( i- nums[j] >=0) {
					DP[i] += DP[i-nums[j]];
				}
			}
		}
		
		return DP[target];
	}
	
	public static int solve2(int[] nums, int target) {
		
		// Combination : {22233 22323 23223 23232 23322 22332 32223 32232 32322 33222}와 같이 순서를 따지는 조합이다.

		/*
		 * 돌아가는 원리
		 * 
		 * ex) nums ={ 2,3} 이고 target이 5일때
		 * 
		 * 2 + 3 은 target이 2일 때 num=2에서 1 한것을 num = 3에서 ;
		 * 3 + 2 target이 3일 때 num = 3에서 1 한것을 num =2 에서 각각 더하게 된다.
		 * 위에 써놓은것처럼 target이 12이면 
		 * 22233은 target = 2, 4, 6, 9, 12 에서 더해져 온 값이고.
		 * 22323은 target = 2, 4 ,7, 9, 12 에서 더해져 온 값이다.  				
		 * 똑똑하다..
		 * 
		 */
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int n = nums.length;
        int[] dp = new int[target + 1];
        dp[0] = 1; // fill 0 weight, always possible.
        
        for (int i = 1; i <= target; i++) { // calc for all weights
            for (int j = 0; j < n; j++) { // iterate over all nums
                if (i - nums[j] >= 0) {
                    dp[i] += dp[i - nums[j]];
                }
            }
        }
        
        return dp[target];
    }		
	
	
	public static void main(String[] args) {
		int[] nums1 = {2,3};
		int target1 = 16;
		
		System.out.println(solve1(nums1,target1));
		System.out.println(solve2(nums1,target1));
	}
}
