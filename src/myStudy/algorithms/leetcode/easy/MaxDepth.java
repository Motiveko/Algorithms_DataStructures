package myStudy.algorithms.leetcode.easy;

import java.util.Stack;

//DFS
public class MaxDepth {

	public static int maxDepth(Integer[] ar) {

		return  1+ Math.max(dfs(ar,1,0), dfs(ar,2,0));
		
	}
	private static int dfs(Integer[] ar, int k, int lv) {
		if( k >= ar.length) return lv;
		if(ar[k] == null) return lv;
		
		return 1 + Math.max( dfs(ar, 2*k + 1, lv) , dfs(ar, 2*k + 2, lv));
	}

	public static void main(String[] args) {
	
		Integer[] ar = {3,9,20,null,null,15,7};
		System.out.println(maxDepth(ar));
		
	}
}
