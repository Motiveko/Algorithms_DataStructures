package myStudy.algorithms.sorting;

import java.util.*;
public class QuickSort {

	
	public static void quickSort(int[] ar) {
		if(ar==null) return;
		quickSort(ar,0,ar.length-1);
	}
	
	private static void quickSort(int[] ar, int lo, int hi) {
		
		if(lo < hi) {
			int splitPoint = partition(ar,lo,hi);
			quickSort(ar, lo, splitPoint);
			quickSort(ar, splitPoint+1, hi);
		}	
	}
	
	private static int partition(int[] ar, int lo , int hi) {
		
		int i = lo-1;
		int j = hi+1;
		int pivot = ar[lo];
		
		while(true) {
			do {
				i++;
			}while( ar[i] <pivot);
			do {
				j--;
			} while( ar[j] > pivot);
			
			if(j>i) swap(ar, i, j);
			else return j;
		}
	}
	
	private static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
	
	public static void main(String[] args) {
		test1();
		test2();
	}
	
	static void test1() {
		Random ran = new Random();
		final int N = 100000;
		
		int[] arr = new int[N];
		
		for(int i=0; i<N; i++) arr[i] = ran.nextInt(100000);
		
		long runtime = System.currentTimeMillis();
		quickSort(arr);
		runtime = System.currentTimeMillis() - runtime;
	
		System.out.println(Arrays.toString(arr));
		
		//About 14ms when N = 100,000, faster than Merge Sort(28ms)!!
		System.out.println("Running Time for Array Length " + N + " : " +  runtime + "ms");
	}
	static void test2() {
		int[] arr = {5,1,3,2,4,5,7,9};
		quickSort(arr);
		System.out.println(Arrays.toString(arr));
	}
}
