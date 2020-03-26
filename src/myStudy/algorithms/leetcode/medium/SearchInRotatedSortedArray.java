package myStudy.algorithms.leetcode.medium;

/*
	Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
	(i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
	You are given a target value to search. If found in the array return its index, otherwise return -1.
	You may assume no duplicate exists in the array.
	Your algorithm's runtime complexity must be in the order of O(log n).
*/

//다시보기, 깃헙 방식이 훨씬 더 잘정리되있다. 내껀 너무  복잡하게 되있다. 나도못알아본다.
import java.util.*;

public class SearchInRotatedSortedArray {

	
	public static int search(int[] ar , int target) {
		return helper(ar, 0, ar.length-1, target);
	}
	
	private static int helper(int[] ar, int start, int end, int target) {
		
		int mid = (end + start) /2;
		if(start==end) {
			if(ar[start] != target) return -1;
			return start;
		}
		
		if( ar[mid] == target) return mid;
		else if( ar[mid] > target) {
			if( ar[mid] < ar[start]) return helper(ar,start,mid-1,target);
			else {
				if(ar[start] <= target) return helper(ar,start,mid-1,target);
				else return helper(ar,mid+1,end,target);
			}
		} else {
			if( ar[mid] > ar[end]) return helper(ar,mid+1,end,target);
			else {
				if(ar[end] >= target) return helper(ar,mid+1,end,target);
				else return helper(ar,start,mid-1,target);
			}
		}
	}
	

	//GitHubSolution
	public static int search2(int[] nums, int target) {
		int l= 0, r= nums.length-1, mid;
		while( l<=r) {
			
			mid = (l+r)>>1;
			if(nums[mid] == target) return mid;
			else if( nums[mid] >= nums[l]) {
				//if는 중간에 피벗이 없는 경우, else는 피벗이 있는경우(절벽생기는부분)
				if( nums[l] <= target && target < nums[mid]) r = mid - 1;
				else l = mid+1;
			} else {
				//if는 중간에 피벗이 없는 경우, else는 피벗이 있는경우(절벽생기는부분)
				if(nums[mid] < target && target <= nums[r]) l = mid + 1;
				else r = mid -1;
			}
			
		
		}
		return -1;
	}
	
	
	public static void main(String[] args) {
		
		int[] ar = generateRotatedSortedArray(7, 1);
		System.out.println(Arrays.toString(ar));
		System.out.println(search2(ar,7));
	}
	
	public static int[] generateRotatedSortedArray(int max, int start) {
		
		int[] ar = new int[max];
		
		for( int i=start, k=0; k<max; k++,i++) {
			if(i == max) i = 0;
			ar[i] = k;
		}
		
		return ar;
	}

	
}
