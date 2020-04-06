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
	
	int st[];
	
	
	public SegmentTree( int[] arr, int n) {
		
		int x = (int) Math.ceil(Math.log(n) / Math.log(2));
		
		int max_size = 2 * (int) Math.pow(2, x) - 1;
		
		
	}
}
