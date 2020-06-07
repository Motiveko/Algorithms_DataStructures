package myStudy.algorithms.dp;

import java.util.Stack;

/*
	https://github.com/awangdev/LintCode/blob/master/Java/Maximal%20Rectangle.java
	
	Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.
	For example, given the following matrix:
	1 0 1 0 0
	1 0 1 1 1
	1 1 1 1 1
	1 0 0 1 0
	Return 6
 */
public class MaximalRectangle {

	public static int solve1(int[][] matrix) {

		// 어렵따 스스로 풀어내지 못했다.
		int m = matrix.length;
		int n = matrix[0].length;

		int[][] heightMap = new int[m][n];

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {

				if (matrix[i][j] == 1) {
					heightMap[i][j] = (i == 0) ? 1 : heightMap[i - 1][j] + 1;
				}
			}
		}
		int res = 0;

		for (int i = 0; i < m; i++) {
			res = Math.max(res, helper(heightMap[i]));
		}
		
		return res;
	}

	private static int helper(int[] height) {

		
		Stack<Integer> stack = new Stack<>();
		int m = height.length;
		int max = 0;
		
		
		// 여기가 핵심이다. 높이가 prev >= curr 되는 시점마다 prevHeight에 의한 넓이값을 계산해 준다. stack에 들어가 있는거는 curr 보다 낮은 높이값이고 
		// 그걸 flag라고 하면 flag~curr사이에는 flag와 curr보다 높은값이 존재하므로 currHeight에 flag~curr을 width로 해서 사각형을 그릴 수 있다.
		// stack이 empty가 된다는거는 높이의 저점을 가져오는것이다. 만약 중간에 0 이있었으면 걔네한테 마지막 순번이 돌아가고 없었으면 최저높이 * m을 하게된다.
		// 솔직히 다시봐도 잘 이해 안될거같다 왜냐면 지금도 잘 이해가 안되기때문이다.
		for( int i=0; i<=m; i++) {
			
			int currHeight = (i==m) ? -1 : height[i];
			while( !stack.isEmpty() && currHeight <= height[stack.peek()]) {
				int peekHeight = height[stack.pop()];
				int width = stack.isEmpty() ? i : i - stack.peek() -1;
				
				max = Math.max(max, peekHeight * width);
			}
			
			stack.push(i);
		}
		
		return max;
	}

	public static void main(String[] args) {
		int[][] matrix1 = new int[4][5];

		matrix1[0] = new int[] { 1, 0, 1, 0, 0 };
		matrix1[1] = new int[] { 1, 0, 1, 1, 1 };
		matrix1[2] = new int[] { 1, 1, 1, 1, 1 };
		matrix1[3] = new int[] { 1, 0, 0, 1, 0 };

		System.out.println(solve1(matrix1));
	}
}
