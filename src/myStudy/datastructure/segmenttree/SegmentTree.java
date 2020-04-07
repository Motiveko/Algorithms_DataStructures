package myStudy.datastructure.segmenttree;

/*
 * https://www.geeksforgeeks.org/segment-tree-set-1-sum-of-given-range/ 참고!
 * 
 * All levels of the constructed segment tree will be completely filled except the last level. 
 * Also, the tree will be a Full Binary Tree because we always divide segments in two halves at every level. 
 * Since the constructed tree is always a full binary tree with n leaves, there will be n-1 internal nodes. 
 * So the total number of nodes will be 2*n – 1. 
 * Note that this does not include dummy nodes. 
 * :: sgt는 full binary tree이고 n개의 값에 대해 생성하면 dummy를 제외하면 n-1개의 노드를 생성한다.
 * :: n이 2^n이라면, dummy node 없이 perfect binary tree로 sgt를 만들 수 있다 
 */

import java.util.*;
public class SegmentTree {
	
	//geeksforgeeks 방식
	
	private int st[];
	private int n;
	
	public SegmentTree( int[] arr) {
		n = arr.length;
								// log2n ?
		int x = (int) Math.ceil(Math.log(n) / Math.log(2));
		
		int max_size = 2 * (int) Math.pow(2, x) - 1;
		
		st = new int[max_size];
		
		constructSTUtil( arr, 0, n-1, 0);
		
	}
	
	//이런식으로 만드는게 훨씬 효율적일거같아서 만듬
	public int getSum(int start, int end) {
		if( start > end || start < 0 ||   end > n-1) throw new IllegalArgumentException("Invalid query range");

		return getSumUtil( 0, n-1, start, end, 0);
	}
	
	public void updateValue( int[] arr,  int i, int new_val) {
		
		int diff = new_val - arr[i];
		
		arr[i] = new_val;
		
		updateValueUtil(0,n-1,i,diff,0);
	}
	
	//recursivly get the sum of values in given range of arr
	// st : pointer to sgt, si : current index in sgt
	// ss, se : starting and ending indexes of the sgt represented by current node, st[si]
	// qs, qe : starting and ending indexes of query range
	private int getSumUtil( int ss, int se, int qs, int qe, int si) {
		
		//현재 st[si]가 가지는 값의 범위가 (qs,qe) 안에 있다
		if( qs<= ss && se<=qe) return st[si];
		
		//위와 반대로 node가 가리키는 범위가 query range와 전혀 겹치치 않는다
		else if (se<qs || ss>qe) return 0;
		
		//부분적으로 겹친다.
		int mid = (ss+se)/2;
		
		// left child + right child
		return getSumUtil( ss, mid,qs,qe,2*si+1) +
				getSumUtil( mid+1, se, qs, qe, 2*si + 2);
	}
	
	private void updateValueUtil( int ss, int se, int i, int diff, int si) {
		
		//outofrange
		if( i < ss || i > se) return;
		
		st[si] += diff;
		
		//not a leaf nod
		
		if(se != ss ) {
			int mid = (ss + se) / 2;
			//update left,right child
			updateValueUtil( ss, mid, i, diff, 2*si + 1);
			updateValueUtil( mid+1, se, i, diff, 2*si + 2);
		}
		
	}
	
	// recursivly construct sgt, si: current node in sgt
	private int constructSTUtil(int[] arr, int ss, int se, int si) {
		
		//ss-se : range in arr
		
		//reach leaf node
		if( ss == se) {
			st[si] = arr[ss];
			return arr[ss];
		}
		
		int mid = (ss + se)/ 2;
		
		// current node = left child node + right child node
		st[si] = constructSTUtil(arr, ss, mid, si*2 + 1) +
				constructSTUtil(arr, mid+1, se, si*2 + 2);
		
		return arr[si];
	}

	public static void main(String[] args) {
		
		
		
		int arr[] =  {1, 3, 5, 7, 9, 11}; 
		
		SegmentTree sgt = new SegmentTree(arr);
		
		System.out.println("sum of given range (1,3) is " + sgt.getSum(1, 3) );
		
		
		sgt.updateValue(arr, 1, 10);
		System.out.println("sum of given range (1,3) is updated : " + sgt.getSum(1, 3) );

		
		
		
		
		
		
	}
	

}
