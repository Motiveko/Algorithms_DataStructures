package myStudy.algorithms.leetcode.easy;

//DFS, Tree
public class PathSum {

	public static boolean hasPathSum(int[] tree, int sum) {
		return dfs(tree, 0, sum);
	}
	
	private static boolean dfs(int[] tree, int index, int sum) {
		
		if(index >= tree.length) return false;
		
		sum -= tree[index];
		int left = 2*index + 1; 
		int right = left + 1;
		if(left< tree.length) boolean has = dfs(tree,left,sum);
		if(right< tree.length) dfs(tree,right,sum);
		
		//구현해야함
		
		return false;
	}
}
