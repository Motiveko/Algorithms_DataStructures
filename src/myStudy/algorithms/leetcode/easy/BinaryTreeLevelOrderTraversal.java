package myStudy.algorithms.leetcode.easy;

import java.util.*;

public class BinaryTreeLevelOrderTraversal {

	public static List<List<Integer>> levelOrder(int[] ar) {
		
		Queue<Integer> queue = new ArrayDeque<>();
		List<List<Integer>> ans = new ArrayList<>();
		
		final int LEVEL_TOKEN = -1;
		queue.offer(0);
		queue.offer(LEVEL_TOKEN);
		int lv= 0;
		
		while( !queue.isEmpty()) {
			int par = queue.poll();
			if(par == LEVEL_TOKEN) {
				lv++;
				continue;
			}
			if(ans.size() ==lv ) ans.add(new ArrayList<>());
			 ans.get(lv).add(ar[par]);
			
			int left = 2*par + 1;
			int right = left + 1; 
			if(left < ar.length) queue.offer(left);
			if(right < ar.length) queue.offer(right);
			//LEVEL끝단
			if((right +2 & right+1) ==0) queue.offer(LEVEL_TOKEN);
			
		}

		return ans;
	}
	
	public static void main(String[] args) {
		
		List<List<Integer>> list = new ArrayList<>();
		list.add(0, new ArrayList<>());
		list.get(0).add(3);
		
		int[] ar = {1,3,5,7,9,75,5,7,8,9,4,5,6,1,2,3,4,5,6,98};
		List<List<Integer>> ordering = levelOrder(ar);
		
		for(int i=0; i<ordering.size(); i++) {
			System.out.println("LEVEL : " + (i+1) + " : " + ordering.get(i).toString());
		}
	}
}
