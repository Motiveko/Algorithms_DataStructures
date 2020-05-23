package myStudy.algorithms.dp;

import java.util.Arrays;

/*
	https://github.com/awangdev/LintCode/blob/master/Java/Flip%20Game%20II.java
	
	
	You are playing the following Flip Game with your friend: 
	Given a string that contains only these two characters: + and -, 
	you and your friend take turns to flip two consecutive "++" into "--". 
	The game ends when a person can no longer make a move and 
	therefore the other person will be the winner.
	Write a function to determine if the starting player can guarantee a win.
	For example, given s = "++++", return true. 
	The starting player can guarantee a win by flipping the middle "++" to become "+--+".
	Follow up:
	Derive your algorithm's runtime complexity.
	Tags: Backtracking
	Similar Problems: (E) Nim Game, (E) Flip Game
 */

public class FlipGame2 {
	
	
	// 스스로 풀기 실패..
	// 너무쩌는코드다.. dfs의 심오함을 느낄 수 있다
	// 경우에 따라 time Complexity가 들쭉날쭉 한데 클 때 너무크다. 
	static int count = 0;
	public static boolean solve1( String str) {
		
		// str >= 4 라고 가정
		
		int n = str.length();
		for( int i=0; i<n; i++) if(str.charAt(i)!='+') throw new IllegalArgumentException("input must be formed only '+' ");
		
		boolean[] sign = new boolean[n];
		Arrays.fill(sign, true);

		return dfs(sign, 2);
		
	}
	
	private static boolean dfs( boolean[] sign, int k) {
		
		int n= sign.length;
		
		for( int i=0; i<n-1; i++) {
			
			if( sign[i] && sign[i+1]) {
				count++;
				setValue(sign, i, false);
				
				if(!dfs(sign,k+1)) {
					// 검사 후에는 다시 원상복구해줘야 이전 dfs함수에서 계속 처리해나갈 수 있다.
					setValue(sign, i, true);
					if( k%2 ==0 ) System.out.println(i);
					return true;
				}
				setValue(sign, i, true);
			}
			
		}
		
		return false;
	}
	
	private static void setValue( boolean[] sign, int i, boolean value) {
		sign[i] = sign[i+1] = value;
	}
	
	public static void main(String[] args) {
		String str = "";
		int num = 6;
		for(int i=0; i<num; i++) str += "+";
		System.out.println(solve1(str));
		System.out.println("count : " + count);
	}

}
