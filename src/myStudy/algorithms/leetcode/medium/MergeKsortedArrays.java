package myStudy.algorithms.leetcode.medium;
import java.util.*;
/*
 * PQ를 이용하는데, 한번에 넣어서 정렬하지 않고 array의 갯수만큼씩만 넣어서 빠르게 정렬하는것이 핵심
 * 다시보기
Given k sorted integer arrays, merge them into one sorted array.
Example
Given 3 sorted arrays:
[
  [1, 3, 5, 7],
  [2, 4, 6],
  [0, 8, 9, 10, 11]
]
return [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11].
Challenge
Do it in O(N log k).
N is the total number of integers.
k is the number of arrays.
Tags Expand 
Heap Priority Queue
*/
public class MergeKsortedArrays {

	
	public static int[] merge(int[][] arrays) {
		
		class Node{
			int value;
			int x, y;
			
			public Node(int value, int x, int y) {
				this.value = value;
				this.x = x;
				this.y = y;
			}
		}
		List<Integer> res = new ArrayList<>();
		
		PriorityQueue<Node> pq = new PriorityQueue<Node>(new Comparator<Node>() {

			@Override
			public int compare(Node o1, Node o2) {
				return o1.value - o2.value;
			}
		});
		
		//initialize
		for(int i=0; i<arrays.length ; i++	) 
			pq.offer(new Node(arrays[i][0],i,0));
		
		while(!pq.isEmpty()) {
			Node node = pq.poll();
			res.add(node.value);
			if( node.y + 1 <10) {
				pq.offer(new Node(arrays[node.x][node.y + 1], node.x, node.y+1));
			}
		}
		
		int[] arr = new int[res.size()];
		
		for( int i=0; i<res.size(); i++) arr[i] = res.get(i);
		res = null;
		
		return arr;
	}
	
	public static void main(String[] args) {
		
		int[][] arrays = new int[3][10];
		
		for( int i=0; i<3; i++) {
			for(int j=0 ; j<10; j++) 
				arrays[i][j] = i+3*j;
		}
		
		System.out.println(Arrays.toString(merge(arrays)));
	}
}
