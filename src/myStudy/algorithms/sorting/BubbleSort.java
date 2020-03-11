package myStudy.algorithms.sorting;

import java.util.*;

public class BubbleSort {

	//Fiset
	public static void bubbleSort(int[] ar) {
		if(ar==null) return;
		final int N = ar.length;
		boolean sorted;
		
		do {
			sorted = true;
			for(int i=1; i<N; i++) {
				if(ar[i]<ar[i-1]) {
					swap(ar,i,i-1);
					sorted = false;
				}
			}
		}while(!sorted);
		
	}
	//Mine
	private static void swap(int[] ar, int i , int j) {
		int tmp = ar[i];
		ar[i] = ar[j];
		ar[j] = tmp;
	}
	
	public static void main(String[] args) {
		Random RANDOM = new Random();
		int N = 100000;
		
		int[] arr = new int[N];
		for(int i=0; i<N; i++) arr[i] = RANDOM.nextInt(10000);
		
		long runtime = System.currentTimeMillis();
		bubbleSort(arr);
		runtime = System.currentTimeMillis() - runtime;
		System.out.println(Arrays.toString(arr));
		//about 15681ms when N = 100,000
		System.out.println("Running Time for Array Length " + N + " : " + runtime + "ms");
	}
	


}
