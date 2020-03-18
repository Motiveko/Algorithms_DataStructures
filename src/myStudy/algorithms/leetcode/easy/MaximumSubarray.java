package myStudy.algorithms.leetcode.easy;

import java.util.*;

//Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.
public class MaximumSubarray {
	
	public static int maximumSubArray(int[] arr) {
		
		int n = arr.length;
		int sum;
		int maxSum =sum = arr[0];
		
		for(int i=1; i<n; i++) {
			
			if(sum + arr[i] < arr[i]) sum = arr[i];
			else sum = sum+arr[i];
			
			maxSum = Math.max(maxSum, sum);
		}
		
		
		return maxSum;
	}
	
	public static void main(String[] args) {
		
		int[] arr = {-2,1,-3,4,-1,2,1,-5,4};
		
		System.out.println(maximumSubArray(arr));
	}
}
