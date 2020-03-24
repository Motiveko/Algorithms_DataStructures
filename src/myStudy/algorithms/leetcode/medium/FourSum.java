package myStudy.algorithms.leetcode.medium;

import java.util.*;
/*
 * 어렶닷..
 * 3sum의 응용이다. 4개부터는 1개씩 유효한 left를 찾고 
 * 2sum이 될 때 까지 계속 필요한 값을 recursive로 넘겨주는 방식
 * List에서  
 */

public class FourSum {

	public static List<List<Integer>> findFourSum(int[] nums, int target){
		
		Arrays.sort(nums);
		int len = nums.length;
		if( len < 4) return Collections.EMPTY_LIST;
		int max = nums[len - 1];
		if( 4*max < target) return Collections.EMPTY_LIST;
		return kSum(nums, 0, 4, target);
	}
	
	private static List<List<Integer>> kSum(int[] nums, int start, int k, int target){
		List<List<Integer>> res = new ArrayList<>();
		
		if( k== 2) {
			
			int left = start, right = nums.length - 1;
			while(left < right) {
				int sum = nums[left] + nums[right]; 
				if( sum == target) {
					
					List<Integer> twoSum = new LinkedList<>();
					twoSum.add(nums[left]);
					twoSum.add(nums[right]);
					res.add(twoSum);
					while( nums[left]==nums[++left] && left < right) ;
					while( nums[right]==nums[--right] && left < right) ;
				}else if( sum < target) left++;
				else right--;
			}
		} else {
			int i = start, end = nums.length - (k-1), max = nums[nums.length-1];
			while(i < end) {
				
				if( nums[i]*k > target) return res;
				if( nums[i] + (k-1)*max < target) {
					while( nums[i]==nums[++i] && i < end) ;
					continue;
				}
				List<List<Integer>> tmp = kSum(nums, i+1, k-1, target - nums[i]);
				
				for( List<Integer> t : tmp) {
					t.add(0,nums[i]);
				}
				
				res.addAll(tmp);
				while(nums[i]==nums[++i] && i < end);
			}
		}
		
		return res;
	}
	
	public static void main(String[] args) {
		
		int[] nums = {1, 0, -1, 0, -2, 2 };
		List<List<Integer>> res = findFourSum(nums,0);
		for(List<Integer> r : res) System.out.println(r.toString());
	}

}
