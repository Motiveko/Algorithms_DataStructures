package myStudy.algorithms.sorting;

import java.util.*;

public class HeapSort {

	
	public static void heapSort(int[] ar) {
		if ( ar == null) return;
		int n = ar.length;
		
		for(int i=(n/2)-1 ; i>=0; i--) sink(ar,n,i);
		
		// maxheap 을 오름차순으로 정렬, 기가맥히다
		for( int i = n-1; i >=0 ; i--) {
			swap(ar, 0, i);
			sink(ar, i, 0);
		}
	}
	
	private static void sink(int[] ar , int n , int i) {
		
		while(true) {
			int left = 2*i +1;
			int right = left + 1;
			int largest = i;
			
			if( right < n && ar[right] > ar[largest]) largest = right;
			
			if(left < n && ar[left] > ar[largest]) largest = left;
			
			if(largest != i) {
				swap(ar, largest , i);
				i = largest;
			} else break;
		}
	}
	
	private static void swap(int[] ar, int i , int j) {
		int tmp = ar[i];
		ar[i] = ar[j];
		ar[j] = tmp;
	}
	
	public static void main(String[] args) {
		
		Random ran = new Random();
		final int N = 500000;
		int[] ar = new int[N];
		
		for(int i=0; i<N; i++) ar[i] = ran.nextInt(100000);
		
		long runtime = System.currentTimeMillis();
		heapSort(ar);
		runtime = System.currentTimeMillis() - runtime;
		
		System.out.println(Arrays.toString(ar));
		// About 14ms When N = 100,000, almost same as quick sort! qs = hs > ms
		// 70ms when N =500,000
		System.out.println("Running Time for Array Length " + N + " : " + runtime + "ms");
	}
}
