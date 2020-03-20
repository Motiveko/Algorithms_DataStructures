package myStudy.algorithms.leetcode.easy;

//DFS, Tree
public class PathSum {
	
	public static boolean hasPathSum(Integer[] tree, int sum) {
		return dfs(tree, 0, sum);
	}
	
	private static boolean dfs(Integer[] tree, int index, int sum) {
		
		// leaf, sum=!0 : false
		if( index >= tree.length || tree[index] == null ) return false;		
		
		sum -= tree[index];
		int left = 2*index + 1; 
		int right = left + 1;
		// leaf, sum=0 : true, !leaf, sum = 0 : false
		if( sum ==0) {
			System.out.println(index);
			return (2*index + 1 >= tree.length) || (tree[left] == null && tree[right] == null);
		}

		return dfs(tree, left, sum) || dfs(tree, right, sum);
	}
	
	public static void main(String[] args) {
//				       5
//				      / \
//				     4   8
//				    /   / \
//				   11  13  4
//				  /  \      \
//				 7    2      1
		Integer[] tree = { 5,4,8,11,null, 13,4,7,2,null,null,null,null,null,1};
		System.out.println(hasPathSum(tree, 17));
	}
}
