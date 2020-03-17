package myStudy.algorithms.algorithms;

import java.util.Arrays;
import java.util.Random;

//Given a target number and an integer array A sorted in ascending order, 
//find the index i in A such that A[i] is closest to the given target.
public class ClosestNumberInSortedArray {
	
	public static int solve(int[] ar, int num) {
		
		int start = 0;
		int end = ar.length-1;
		int mid = (end + start) / 2;
		
		int index = -1;
		
		//binary search
		while(mid >start && mid<=end) {
			
			if(ar[mid] == num) return mid;
			else if( ar[mid] > num ) {
				if( mid-1 >=0 && ar[mid -1 ] <= num) 
					return (ar[mid] - num) >= (num - ar[mid-1]) ? mid-1 : mid;
				
				end = mid;
				mid = (start + end) / 2;
			} else {
				if( mid + 1 <=end && ar[mid + 1] >= num) 
					return (num - ar[mid]) >= (ar[mid + 1] - num) ? mid+1 : mid;
					
				start = mid;
				mid = (start + end) / 2;
			}
		}
		
		//가져왔다!
		return (num - ar[start]) < (ar[end] - num) ? start : end;
	}
	public static void main(String[] args) {
		int[] nums = {1, 3, 3, 4};
		System.out.println(solve(nums,2));
		int[] nums2 = {1, 4, 6};
		System.out.println(solve(nums2,5));
		int[] nums3 = {1, 2, 3};
		System.out.println(solve(nums3,2));
		
		Random ran = new Random();
		int n =600;
		int[]nums4 = new int[n];
		for(int i=0; i<600;i++) nums4[i] = ran.nextInt(600);
		Arrays.sort(nums4);
		System.out.println(Arrays.toString(nums4));
		System.out.println(solve(nums4,619));
		System.out.println(nums4[solve(nums4,619)]);
	}

}
