package myStudy.algorithms.sorting;

import java.util.*;

public class InsertionSort {

	public static void insertionSort(int[] ar) {
		
		if(ar ==null) return;
		final int N = ar.length;
		
		for(int i=1; i<N; i++)
			for(int j=i; j>0 && ar[j] < ar[j - 1]; j--) swap(ar, j-1, j);
	}
	
	private static void swap(int[] ar, int i, int j) {
		
		int tmp= ar[i];
		ar[i] = ar[j];
		ar[j] = tmp;
	}
	
	public static void main(String[] args) {
		
		Random ran = new Random();
		final int N = 100000;
		int[] arr = new int[N];
		
		for(int i=0; i<N; i++) arr[i] = ran.nextInt(10000);

		long start =System.currentTimeMillis();
		insertionSort(arr);
		long runtime = System.currentTimeMillis()- start;

		System.out.println(Arrays.toString(arr));
		// About 3741ms When N = 100,000
		System.out.println("Running Time for Array Length " + N + " : " + runtime + "ms");

	}
	
}
