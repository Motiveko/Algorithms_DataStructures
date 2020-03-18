package myStudy.algorithms.leetcode.easy;

import java.util.Arrays;

/*
Given an array nums and a value val, remove all instances of that value in-place and return the new length.

Do not allocate extra space for another array, you must do this by **modifying the input array in-place ** with O(1) extra memory.

The order of elements can be changed. It doesn't matter what you leave beyond the new length.
*/
public class RemoveElement {

	
	public static Integer[]	removeElement(Integer[] input, int value) {
		
		int flag = -1;
		
		for(int i=0 ; i<input.length; i++	) {
			
			if( input[i] == value) {
				if(flag == -1) flag = i;
			}else if(flag!=-1) input[flag++] = input[i];
		}
		
		//지운거만큼 null
		for(int i=flag ; i<input.length; i++) input[i] =null;
		
		
		return input;
	}
	
	public static void main(String[] args) {
		
		Integer[] arr = {0,1,2,3,4,8,0,7,9,0,1,3};
		System.out.println(Arrays.toString(removeElement(arr,0)));
	}
}
