package myStudy.algorithms.sorting;

import java.util.*;
public class MergeSort {

	public static int[] mergeSort(int[] arr) {
		
		
		final int n = arr.length;
		if(n ==1) return arr;
		int[] left = mergeSort(Arrays.copyOfRange(arr, 0, n/2));
		int[] right = mergeSort(Arrays.copyOfRange(arr, n/2, n));
		
		return merge(left, right);
	}
	
	private static int[] merge(int[] ar1, int[] ar2) {
		
		int n1 = ar1.length;
		int n2 = ar2.length;
		int i1 = 0, i2 =0;
		
		int n= n1+n2;
		int[] ar = new int[n];
		
		for( int i=0; i<n; i++) {
			if(i1==n1) {
				ar[i] = ar2[i2++];
			} else if(i2 ==n2) {
				ar[i] = ar1[i1++];
			}else {
				if(ar1[i1] < ar2[i2]) {
					ar[i] = ar1[i1++];
				} else {
					ar[i] = ar2[i2++];
				}
			}
		}
		return ar;
	}
	
	public static void main(String[] args) {
		
		Random ran = new Random();
		final int N = 100000;
		int[] arr = new int[N];
		
		for(int i=0; i<N; i++) arr[i] = ran.nextInt(100000);

		long runtime = System.currentTimeMillis();
		arr = mergeSort(arr);
		runtime = System.currentTimeMillis() - runtime;
		System.out.println(Arrays.toString(arr));
		// About 29ms when N = 100,000....!!
		// 181ms when N = 1,000,000...!!!?
		System.out.println("Running time for Array Length " + N + " : " + runtime + "ms");

	}
}
