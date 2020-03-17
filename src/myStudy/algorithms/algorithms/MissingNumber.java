package myStudy.algorithms.algorithms;

public class MissingNumber {

	//Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, 
	//find the one that is missing from the array.
	public static int findMissingNumberMine(int[] ar) {
		int n = ar.length;
		int sum = n*(n+1)/2;
		for(int i=0; i<n; i++) sum -= ar[i];
		return sum;
	}
	
	public static int findMissingNumber(int[] ar) {
		//index에 맞춰 정렬 후 비교하는 방식. 별로다
		int n = ar.length;
		
		//swap
		for(int i=0; i<n; i++) {
			if(ar[i] != i && ar[i] <n && ar[i]!=ar[ar[i]]) {
				int tmp = ar[ar[i]];
				ar[ar[i]] = ar[i];
				ar[i] = ar[ar[i]];
			}
		}
		
		for(int i=0; i<n; i++) if(ar[i]!=i) return i;
		
		return -1;
	}
	public static void main(String[] args) {
		//4
		int[] ar = {0,1,2,3,5,6,7,8,9,10,11,12,13,14,15};
		System.out.println(findMissingNumber(ar));
	}
}
