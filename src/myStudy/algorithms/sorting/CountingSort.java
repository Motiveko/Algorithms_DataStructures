package myStudy.algorithms.sorting;

import java.util.*;

/*
 * 어떤 값의 범위를 알 때 그 범위만큼의 크기를 가지는 배열을 만들고
 * input arr의 값들을 차례대로 세어 카운팅해서 새로 만든 배열에 넣는다
 * 그리고 카운팅한 수를 다시 세어서 오름차순으로 쭉 정렬~! 공간 복잡도는 O(k)!
 * Linear한 방식이므로 매우매우매우매우 빠르다..! 
 *  O(n + k) , k는 value range!
 */
public class CountingSort {

	public static void countingSort( int[] ar , int minVal, int maxVal) {
		
		//minVal이 음수일수도 있다!
		int sz = maxVal-minVal + 1;
		int[] B = new int[sz];
		
		for(int i=0; i<ar.length; i++)B[ar[i] - minVal]++;
		
		for(int i=0,k=0; i<sz; i++) while(B[i]-- > 0) ar[k++] = i + minVal;
	}
	
	public static void main(String[] args) {
		
		Random ran = new Random();
		final int N = 100000;
		int[] arr= new int[N];
		
		for(int i=0; i<N; i++) arr[i] = ran.nextInt(100000);
		
		long runtime = System.currentTimeMillis();
		countingSort(arr,0,100000);
		runtime = System.currentTimeMillis() - runtime;
		
		System.out.println(Arrays.toString(arr));
		
		// About 6ms when N = 100,000..! Faster than heap,quick sort!
		System.out.println("Running Time for Array Length " + N + " : " + runtime + "ms");
	}
	
}
