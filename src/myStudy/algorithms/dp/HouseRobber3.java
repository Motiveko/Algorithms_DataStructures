package myStudy.algorithms.dp;

/*
	https://github.com/awangdev/LintCode/blob/master/Java/House%20Robber%20III.java
	The thief has found himself a new place for his thievery again. 
	There is only one entrance to this area, called the "root." 
	Besides the root, each house has one and only one parent house. 
	After a tour, the smart thief realized that "all houses in this place forms a binary tree". 
	It will automatically contact the police if two directly-linked houses were broken into on the same night.
	Determine the maximum amount of money the thief can rob tonight without alerting the police.
	Example 1:
	     3
	    / \
	   2   3
	    \   \ 
	     3   1
	Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
	Example 2:
	     3
	    / \
	   4   5
	  / \   \ 
	 1   3   1
	Maximum amount of money the thief can rob = 4 + 5 = 9.
	Subscribe to see which companies asked this question

*/

public class HouseRobber3 {
	
	// 신박함..그 자체.. 꼭 복습하쟈
	// Recursive Programming과 Dynamic Programming 의 합작품..
	public static int solve1(Integer[] tree) {
		
		int[] dp = dfs1(tree,0);
		return Math.max(dp[0], dp[1]);	
	}
	
	// dp[0] : root 미포함 -- dp[1] : root 포함
	private static int[] dfs1(Integer[] tree, Integer root) {
		
		int[] dp = new int[2];
		if(root == null || tree[root] == null) return dp;
		
		int n = tree.length;
		
		Integer leftChild = (2*root + 1) >= n ? null : 2*root+1;
		Integer rightChild = (2*root + 2) >= n ? null : 2*root+2;
		
		int[] leftDp = dfs1(tree,leftChild);
		int[] rightDp = dfs1(tree,rightChild);
		
		dp[0] = Math.max(leftDp[0], leftDp[1]) + Math.max(rightDp[0], rightDp[1]);
		dp[1] = tree[root] + leftDp[0] + rightDp[0];
		
		return dp;
	}
	
	// 위 방법과 알고리즘 원리는 동일. 구현방식에 살짝의 차이가있다.
	
	public static int solve2(Integer[] tree) {
		
		return Math.max(dfs2(tree,0,true),dfs2(tree,0,false));
	}
	
	private static int dfs2(Integer[] tree, Integer root, boolean isVisited) {
		
		int n = tree.length;
		if( root >= n || tree[root] == null) return 0;
		
		int leftChild = 2*root + 1;
		int rightChild = 2*root + 2;
		
		
		if(isVisited) {
			// 해당노드를 방문한다 -> 자식노드 방문금지
			return tree[root] + dfs2(tree,leftChild,false) + dfs2(tree,rightChild,false);
		} else {
			// 해당노드를 방문하지 않는다 -> 자식노드 방문 or 안방문 둘다가능
			return Math.max(dfs2(tree,leftChild,true), dfs2(tree, leftChild, false)) + 
					Math.max(dfs2(tree,rightChild,true) , dfs2(tree, rightChild, false));
		}
	}

	
	
	
	
	
	public static void main(String[] args) {
		Integer[] tree1 = {3,2,3,null,3,null,1};
		Integer[] tree2 = {3,4,5,1,3,null,1};
	
		System.out.println(solve1(tree1) + " == " + solve2(tree1));
		System.out.println(solve1(tree2) + " == " + solve2(tree2));
	}
	

}
