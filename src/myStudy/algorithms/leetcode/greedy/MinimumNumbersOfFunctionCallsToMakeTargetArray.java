package myStudy.algorithms.leetcode.greedy;


/*
 *  https://leetcode.com/problems/minimum-numbers-of-function-calls-to-make-target-array/
 * 	0으로 채워진 arr에서 nums를 만드는데 필요한 연산횟수.
 * 	연산은 fun(arr, op, idx) 일 때 op==0 -> arr[idx]++, op==1일 때 arr모든값 *2를한다.
 */

public class MinimumNumbersOfFunctionCallsToMakeTargetArray {

	
	
	public static int solution(int[] nums) {
	
		int count1=0; // +1 연산
		int count2=0; // 전체 *2연산
		
		int tmp2;
		for( int n : nums) {
			tmp2 = 0;
			while(n!=0) {
				if( n%2 !=0) {
					n--;
					count1++;
				} else {
					n /=2;
					tmp2++;
				}
			}
			count2 = Math.max(count2, tmp2);
		}
		
	
		return count1+count2;
	}
}
