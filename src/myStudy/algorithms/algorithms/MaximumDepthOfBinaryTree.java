package myStudy.algorithms.algorithms;

//EASY
public class MaximumDepthOfBinaryTree {

	public static int maxDepth(Integer[] tree) {
		
		if(tree[0]==null) return 0;
		
		return Math.max(maxDepth(tree,1), maxDepth(tree,2)) + 1;
	}
	
	private static int maxDepth(Integer[] tree, int k) {
		
		int n = tree.length;
		
		if(k>=n || tree[k] ==null) return 0;
		int left = 2*k +1;
		int right = left + 1;
		
		return Math.max(maxDepth(tree,left), maxDepth(tree,right)) + 1;
	}
	
	
	public static void main(String[] args) {
		
		Integer[] tree=  {3,9,20,null,null,15,7};
		System.out.println(maxDepth(tree));
	}
}
