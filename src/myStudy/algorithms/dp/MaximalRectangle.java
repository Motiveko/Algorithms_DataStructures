package myStudy.algorithms.dp;

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
	
	
		
		// 접근 전략
		// dp[][] = (가로,세로) 값을 저장, res =Math.max(res, 가로*세로) 로 매번 갱신
		// dp[i][j] == 1 일때, 위와 왼쪽 옆을 비교, 
		// 왼쪽옆이 존재할 때, 위의 가로*(세로+1) 과 왼쪽옆의 가로+1 * 세로 를 비교, 앞이 크면 i,j는 위의 가로,세로+1 그 외에(==포함) 왼족옆의 가로+1,세로 를 가져온다
		// dp형은 [][]에다가 비트연산자를 써도 될 것 같고,, 생각해봐야할 것 같다.
		
	
		
		return 0;
	}
	
	public static void main(String[] args) {
		int[][] matrix1 = new int[4][5];
		
		matrix1[0] = new int[]{1,0,1,0,0};
		matrix1[1] = new int[]{1,0,1,1,1};
		matrix1[2] = new int[]{1,1,1,1,1};
		matrix1[3] = new int[]{1,0,0,1,0};
		

		System.out.println(solve1(matrix1));
	}
}
