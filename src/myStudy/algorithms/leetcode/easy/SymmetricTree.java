package myStudy.algorithms.leetcode.easy;

import java.util.*;

/*
Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).

For example, this binary tree [1,2,2,3,4,4,3] is symmetric:

    1
   / \
  2   2
 / \ / \
3  4 4  3
But the following [1,2,2,null,3,null,3] is not:

    1
   / \
  2   2
   \   \
   3    3
Note:

Bonus points if you could solve it both recursively and iteratively.

Tags: Tree, Depth-first Search, Breadth-first Search
*/

public class SymmetricTree {

	
	public static boolean isSymmetric(Integer[] ar) {
	
		Stack<Integer> stackLeft = new Stack<>();
		Stack<Integer> stackRight = new Stack<>();
		stackLeft.add(1);
		stackRight.add(2);
		int n = ar.length;
		
		//길이가 2의 제곱근이 아니면 대칭이 될 수 없다(빈칸은 null로 들어오기때문)
		if((n+1 & n) !=0) return false;
		while(!stackLeft.isEmpty() || !stackRight.isEmpty()) {
			Integer indexL = stackLeft.pop();
			Integer indexR = stackRight.pop();		
			Integer valueL = ar[indexL];
			Integer valueR = ar[indexR];
			
			if(valueL==null || valueR==null) {
				if(valueL!=null || valueR!=null) return false;
			} else if(!valueL.equals(valueR)) return false;
			
			//위의 2의제곱근 판별로 일단 각 층이 값이 null이라도 가득 차있기 때문
			if(2*indexR + 2<= n-1) {
			stackRight.add(2*indexR+1);
			stackRight.add(2*indexR+2);
			stackLeft.add(2*indexL +2);
			stackLeft.add(2*indexL+1);
			}

		}
		
		return stackLeft.isEmpty() && stackRight.isEmpty();
	}
	
	public static void main(String[] args) {
		
		Integer[] ar = {0,1,1,null,1,1,null,3,5,7,8,8,7,5,3};
		System.out.println(ar.length);
		System.out.println(isSymmetric(ar));
	}

	
}
