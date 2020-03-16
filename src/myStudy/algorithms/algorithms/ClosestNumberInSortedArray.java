package myStudy.algorithms.algorithms;

//Given a target number and an integer array A sorted in ascending order, 
//find the index i in A such that A[i] is closest to the given target.
public class ClosestNumberInSortedArray {
	
	public static int solve(int[] ar, int num) {
		
		int start = 0;
		int end = ar.length-1;
		int mid = (end + start) / 2;
		
		int index = -1;
		
		//binary search
		while(true) {
			if(ar[mid] > num) {
				if(ar[mid-1] < num) {
					index = mid;
					break;
				}
				end = mid;
				mid = (start + end)/2;
				
			} else if( ar[mid] < num) {
				if(ar[mid+1] >num) {
					index = mid;
					break;
				}
				
			} else return index;
		}
		
		if( ar[index] > num) {
			return (ar[index] - num) < (num - ar[index-1]) ? index : index -1;
		} else 
			return (num-ar[index]) < (ar[index+1] - num) ? index : index+1; 

	}
	//오답이 나온다 index 가 0이거나 n-1일때 조건 수정해야한다!
	public static void main(String[] args) {
		int[] nums = {1, 3, 3, 4};
		System.out.println(solve(nums,2));
		int[] nums2 = {1, 4, 6};
		System.out.println(solve(nums2,5));
		int[] nums3 = {1, 2, 3};
		System.out.println(solve(nums3,2));
	}

}
