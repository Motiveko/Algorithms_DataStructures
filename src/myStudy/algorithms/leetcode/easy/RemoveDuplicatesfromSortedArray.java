package myStudy.algorithms.leetcode.easy;

import java.util.Arrays;

/*	
	Given a sorted array nums, remove the duplicates in-place such that each element appear only once and return the new length.
	
	Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
*/
public class RemoveDuplicatesfromSortedArray {

	public static Integer[] removeDuplicates(Integer[] arr) {
		
		int flag = 0;
	
		for(int i=1; i<arr.length; i++) if(arr[i] != arr[flag]) arr[++flag] = arr[i];
		

		for(int i =flag+1; i<arr.length; i++) arr[i] = null;
		
		return arr;
	}
	
	public static void main(String[] args) {
		
		Integer[] arr = {0,0,1,1,1,2,2,3,3,4};
		System.out.println( Arrays.toString(removeDuplicates(arr)));
	}
	
}
