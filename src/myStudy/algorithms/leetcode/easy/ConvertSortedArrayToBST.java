package myStudy.algorithms.leetcode.easy;

import java.util.Arrays;
import java.util.Random;

public class ConvertSortedArrayToBST {

	//정렬된Array라는것이 문제의 힌트이다
	//binarysearch방식으로 구축해나가면 최소한의 높이로 구현할 수 있다
	//github은 아예 tree를 node클래스 만들어서 구현한다는 가정하에 하는거라 쉽다..
	public static Integer[] convert(int[] ar) {
		
		int n = ar.length;
		int size = Integer.highestOneBit(n)*2 - 1;
		Integer[] tree= new Integer[size];
		
		int mid =(n -1 ) /2;
		
		tree[0] = ar[mid];
		construct( tree, true,ar, 0, 0, mid-1);
		construct( tree, false,ar, 0, mid+1, n-1);
		
		return tree;
	}
	
	private static Integer[] construct(Integer[] tree, boolean isLeft,int[] ar,int parent, int start, int end) {
		
		//복잡한거 필요 없이 start>end일때만 고려해주면 된다 ㅜ
		if(start>end) return tree;
		int mid = (start + end) >> 1;
		
		int current = isLeft ? 2*parent + 1 : 2*parent +2; 
		tree[current] = ar[mid];		
		
		construct(tree, true, ar, current, start, mid-1);
		construct(tree, false, ar, current, mid+1, end);
		
		
		return tree;
	}
	
	public static void main(String[] args) {
		
		int n = 15;
		int[] arr = new int[n];
		Random ran = new Random();
		
		for(int i = 0; i<n; i++) arr[i] = ran.nextInt(1440);
		Arrays.sort(arr);
		System.out.println(Arrays.toString(arr) + "\n" + Arrays.toString(convert(arr)));
	}
}
