package myStudy.algorithms.leetcode.medium;

/*
	Given an array nums of n integers and an integer target, find three integers in nums such that the sum is closest to target. 
	Return the sum of the three integers. 
	You may assume that each input would have exactly one solution.
*/

import java.util.*;

public class ThreeSumClosest {

	public static int threeSum(int[] arr, int target) {
		
		int n = arr.length;
		int delta = 0x7fffffff, res = 0;
		
		Arrays.sort(arr);
		
		for(int i=0; i<n-2; i++) {
			int left = i+1;
			int right = n-1;
			int sum = arr[i] + arr[left] + arr[right];
			
			while(left < right) {
				
				int curDelta = Math.abs(sum-target);
				if( curDelta == 0) return sum;
				else {
					if(curDelta < delta) {
						delta = curDelta;
						res = sum;	
					}
				}
				
				if(sum < target) right--;
				else left ++;	
			}
		}
		
		return res;
	}
	
	public static void main(String[] args) {
		int n =100;
		int[] arr = new int[n];
		for(int i=0; i<n; i++) arr[i] = ranInt(100,-100);
		
		System.out.println(threeSum(arr, 28));
		
	}
	
	public static int ranInt(int max, int min) {
		Random ran = new Random();
		return ran.nextInt(max-min) + min;
	}
}

