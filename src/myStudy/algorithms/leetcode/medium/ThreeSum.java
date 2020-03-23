package myStudy.algorithms.leetcode.medium;

/*
	문제 원래 이름 : 3Sum 
	Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0?
	Find all unique triplets in the array which gives the sum of zero.
	Note:
	The solution set must not contain duplicate triplets.
*/

import java.util.*;

public class ThreeSum {

	public static List<List<Integer>> threeSum(int[] arr){
		//3개숫자 합이 0이어야한다!
		
		int n = arr.length;
		List<List<Integer>> ans = new ArrayList<>();
		Arrays.sort(arr);
		int max = arr[n-1];
		
		for(int i=0; i<n; ) {
			
			//i는 기준값이고 그 위의 값들로만 계산할거기때문에 arr[i]>0이면 그만한다!
			if(arr[i] > 0) break;
			if(arr[i] + 2*max <0) {
				while(arr[i]==arr[++i] && i<n-2);
				continue;
			}
			
			int left = i+1;	
			int right = n-1;
			
			//어떤 기준 i에 대해 그 위의 값 left, right를 찾아 넣어준다
			while(left < right) {
				int sum = arr[i] + arr[left] + arr[right];
				if(sum==0) {
					//못쓰던 기능이다. Arrays.asList!
					ans.add(Arrays.asList(arr[i],arr[left],arr[right]));
					while( arr[left] == arr[++left] && left<right) ;
					while( arr[right] == arr[--right] && right>left) ;
				} else if( sum < 0) {
					left++;
				} else right--;
			}
			
			//i값 다음으로
			while(arr[i]==arr[++i] && i < n-3) ;
		}
		return ans;
	}
	
	public static void main(String[] args) {
		int n = 120;
		int[] arr = new int[n];
		for(int i=0; i<n; i++) arr[i] = ranInt(1000,-1000);
		List<List<Integer>> ans = threeSum(arr);
		int count =1;
		for(List<Integer> set : ans) {
			System.out.println(count + " : " + set.toString());
			count++;
		}
	}

	public static int ranInt( int max, int min) {
		Random ran = new Random();
		return ran.nextInt(max-min) + min;
	}
}
