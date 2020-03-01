package myStudy.algorithms.other;

import java.util.Arrays;

public class Permutations {

//	//Recursive Approach
//	public static void generatePermutations(Object[] sequence) {
//		
//		if(sequence == null) return;
//		boolean[] used = new boolean[sequence.length];
//		int[] picked = new int[sequence.length];
//		permutations(0, used, picked, sequence);
//	}
//	
//	private static void permutations(int at, boolean[] used, int[] picked , Object[] sequence) {
//		final int N = sequence.length;
//		
//		if(at == N) {
//			System.out.print("[ ");
//			for(int i=0; i<N; i++) {
//				System.out.print( sequence[picked[i]] );
//			}
//			System.out.println(" ] ");
//			
//			return;
//		}
//		
//		for(int i =0 ; i<N ; i++) {
//			if(!used[i]) {
//				used[i] = true;
////				picked[i] = at;
//				picked[at] = i;
//				permutations(at+1,used,picked,sequence);
//				used[i] = false;
//			}
//		}
//	}
//	
//
//	/* Iterative Approach
//	 * 오름차순 -> 내림차순 하는 형식의 알고리즘.
//	 * 2개씩 짤라서 작->크 를 크-> 작으로 바꾸고
//	 * 3개째 인덱스를 바꾸면 그 뒤를 다시 오름차순으로 만들어서 내림차순으로 정렬해가는 방식
//	 * 이해가 안되면 두개, 세개, 네개의 예제를 만들어서 실행해보자. 
//	 * 
//	 * 
//	 * first 인덱스를 고르는 기준은 first 위로는 전부 내림차순이 되는 인덱스다.
//	 * 따라서 first와 어떤 값이든 toSwap인덱스가 swap하면 first위로 다시 오름차순으로 바꿔줘야한다.	
//	*/
//	
//	static <T extends Comparable<T>> boolean nextPermutation(T[] sequence) {
//		int first = getFirst(sequence);
//		if(first==-1) return false;
//		int toSwap = sequence.length -1;
//		
//		while( sequence[first].compareTo(sequence[toSwap]) >=0) toSwap--;
//		swap(sequence, first, toSwap);
//		first++;
//		//first위로 다 다시 오름차순으로 정
//		toSwap = sequence.length-1;
//		while(first<toSwap) swap(sequence,first++,toSwap--);
//		return true; 
//	}
//	
//	static <T extends Comparable<T>> int getFirst(T[] sequence) {
//		for( int i = sequence.length-2; i>=0; i--) {
//			if(sequence[i].compareTo(sequence[i+1]) < 0) return i;
//		}
//		
//		return -1;
//	}
//	
//	static  <T extends Comparable<T>>  void swap(T[] sequence , int i, int j) {
//		T tmp = sequence[i];
//		sequence[i] = sequence[j];
//		sequence[j] = tmp;
//	}
//	
//	
	public static void main(String[] args) {
		Integer[] nums = {0,1,2,3};
		generatePermutations(nums);
		
		
		String[] alpha = {"A", "B", "C", "D"};
		
		do {
			System.out.println(java.util.Arrays.deepToString(alpha));
		} while(nextPermutation(alpha));
		
	}
	// PERMUTATION 복습!
		public static void generatePermutations(Object[] sequence) {
			int n = sequence.length;
			boolean[] used = new boolean[n];
			int[] picked = new int[n];
			generatePermutations(0, used, picked, sequence);
		}
		
		public static void generatePermutations(int at, boolean[] used, int[] picked, Object[] sequence) {
			int n= sequence.length;
			if(at == n) {
				System.out.print("[");
				for(int i=0; i<n; i++) System.out.print(sequence[picked[i]] + " ");
				System.out.println("]");
			}
			
			for(int i=0; i<n ; i++) {
				if(!used[i]) {
					used[i] = true;
					picked[at] = i;
					generatePermutations(at+1, used, picked, sequence);
					used[i] = false;
				}
			}
		}
		
		public static < T extends Comparable<T>> boolean nextPermutation(T[] sequence){
			//sequence가 오름차순 정렬일 때만 제대로 작동한다.
			int first = getFirst(sequence);
			if(first==-1) return false;
			int toSwap = sequence.length - 1;
			while(sequence[first].compareTo(sequence[toSwap]) >=0) toSwap--;
			swap(sequence,first++,toSwap);
			// first 뒤로 다시 오름차순정
			toSwap = sequence.length - 1;
			while(first < toSwap) swap(sequence,first++,toSwap--);
			return true;
		}
		
		public static < T extends Comparable<T>> int getFirst(T[] sequence) {
			for(int i=sequence.length-2; i>=0; i--)
				if(sequence[i].compareTo(sequence[i+1]) < 0 ) return i;
			
			//sequence가 내림차순으로 정렬되었을 경우
			return -1;
		}
		public static <T extends Comparable<T>> void swap(T[] sequence, int i , int j) {
			T tmp = sequence[i];
			sequence[i] = sequence[j];
			sequence[j] = tmp;
		}
}
