package myStudy.algorithms.leetcode.easy;

import java.util.Arrays;
import java.util.Random;

/*
	Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.
	
	You may assume no duplicates in the array.
*/
public class SearchInsertionPoint {

	public static int insertionPoint(int[] arr, double value) {

		int start = 0;
		int end = arr.length-1;
		int mid = (start + end) /2;
		
		while(mid>0 && mid<arr.length-2) {
			
			if( arr[mid] > value) {
				if( arr[mid-1] < value ) return mid;
				
				end = mid;
				mid = (start + end) /2;
			} else {
				if(arr[mid+1] > value) return mid+1;
		
				start = mid;
				mid = (start + end) / 2;
			}
			
		}
		
		//값이 arr최대값보다 크다!
		if(mid == arr.length - 2) return (arr[end] < value) ? end+1 : end;
		//mid ==0이다!
		return mid;
	}

	//GitHubCode :: 훨씬 깔끔하다!
	public static int insertionPoint2(int[] arr, double target) {
		
		int left = 0; int right = arr.length-1; int mid = (left + right) >> 1;
		
		// <= 인 이유는 반환값 left인데 arr최대보다 큰값이 들어올 때 length +1의 값이 들어가야하므로!
		while(left<=right) {
			
			if( arr[mid] > target) {
				right = mid-1;
				mid = (left + right) >> 1;
			} else {
				left = mid+1;
				mid = (left + right) >> 1;
			}
		}
		
		return left; 
	}
	public static void main(String[] args) {
		int n= 10;
		int[] arr = new int[n];
		Random ran = new Random();
		
		for(int i=0; i<n; i++) arr[i] = ran.nextInt(100);
		Arrays.sort(arr);
		System.out.println(Arrays.toString(arr));
		int point = insertionPoint2(arr, 101);
		System.out.println( " point : " + point);
		if(point<n)System.out.println( "arrVal : " + arr[point]);
		
	}
	
}
