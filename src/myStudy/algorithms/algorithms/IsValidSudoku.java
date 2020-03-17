package myStudy.algorithms.algorithms;

import java.util.*;

//9*9 matrix가 스도쿠 조건을 만족하는가?
public class IsValidSudoku {

	
	public static boolean isValidSudoku(char[][] matrix) {

		//죠땐다..
		for(int i=0; i<9; i++) {
			
			Set<Character> rowSet = new HashSet<>(); 
			Set<Character> columnSet = new HashSet<>();
			Set<Character> squareSet = new HashSet<>();
			
			for(int j=0; j<9; j++) {
			
				if(!helper( matrix[i][j], rowSet)) return false;
				if(!helper(matrix[j][i], columnSet)) return false;
				
				// 머리를 잘 쓰면 for문이 정사각형으로도 돌아가게 만들 수 있다! 짱구를 굴리자
				int r =  j/3 + 3*(i/3);
				int c = j%3 + 3*(i%3);
				
				if(!helper(matrix[r][c], squareSet)) return false;
				
			}
			
		}
		
		return true;
	}
	
	public static boolean helper( char value, Set<Character> set) {
		
		if(set.contains(value) && value!='.') return false;
		set.add(value);
		return true;
	}
	
	
	
	
	public static void main(String[] args) {
		char[][] matrix = new char[9][9];
		for(int i=0; i<9; i++) Arrays.fill(matrix[i], '.');
		matrix[0][0] =1;
		matrix[2][2] =2;
		matrix[1][5] =4;
		matrix[1][8] =3;
		matrix[2][7] =1;
		int i = 1 << matrix[1][5];
		System.out.println(i);
		System.out.println(isValidSudoku(matrix));
	}
}
