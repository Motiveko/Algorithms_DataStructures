package myStudy.algorithms.sorting;

import java.util.Arrays;
import java.util.Random;
public class SelectionSort {

	public static void selectionSort(int[] array) {
		
		if(array == null) return ;
		final int n = array.length;
		for(int i=0; i<n-1; i++) {
			int swapIndex = i;
			for(int j=i+1; j<n; j++) {
				if(array[swapIndex] > array[j]) swapIndex = j;
			}
			swap(array,i,swapIndex);
		}
		
	}
	
	private static void swap(int[] ar, int i, int j) {
		int tmp = ar[i];
		ar[i] = ar[j];
		ar[j] = tmp;
	}
	
	public static void main(String[] args) {
		Random ran = new Random();
		final int N = 100000;
		int[] arr = new int[N];
		for(int i=0; i<N; i++) arr[i] = ran.nextInt(10000);
		
		long runtime = System.currentTimeMillis();
		selectionSort(arr);
		runtime = System.currentTimeMillis() - runtime;
		System.out.println(Arrays.toString(arr));

		// About 3011ms When N = 100,000
		System.out.println("Running Time for Array Length " + N + " : " + runtime + "ms");
	}
}
