package myStudy.algorithms.leetcode.easy;

import java.util.HashMap;
import java.util.Map;

/*Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 *You may assume that each input would have exactly one solution, and you may not use the same element twice.
 */
public class TwoSum {

	public static int[] twoSum(int[] ar, int target) {
		
		int n = ar.length;
	
		for(int i=0; i<n-1; i++) {
			for(int j=i+1; j<n; j++) {
				if( ar[i] + ar[j] == target)
					//몰랐던문법;
					return new int[]{i, j};
			}
		}
		return null;
	}
	
	//solution 2, 런타임은 큰차이는 없지만 또 다른방식의 생각이다! 볼만하다.
	public static int[] twoSum2(int[] ar, int target) {
		
		Map<Integer,Integer> map = new HashMap<>();
		int n = ar.length;
		for(int i=0; i<n ; i++) {
			
			Integer value = map.get(ar[i]);
			if(value!=null) return new int[] {value,i};
			map.put(target - ar[i], i);
		}
		
		return null;
	}
	public static void main(String[] args) {
		
		int[] ar = new int[100];
		
		for(int i =0; i<100; i++) ar[i] = i;
		//
		int[] ans = twoSum(ar, 102);
		System.out.println(ans[0] + ans[1]);
		
		int[] ans2 = twoSum2(ar, 105);
		System.out.println(ans2[0] + ans2[1]);
	}
}
