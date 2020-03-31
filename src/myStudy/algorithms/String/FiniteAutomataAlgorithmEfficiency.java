package myStudy.algorithms.String;

//Construct FA O(m+no_of_char) using lsp
//https://www.geeksforgeeks.org/pattern-searching-set-5-efficient-constructtion-of-finite-automata/

import java.util.*;

public class FiniteAutomataAlgorithmEfficiency {

	
	public final static int NO_OF_CHARS = 256;
	
	public static List<Integer> search(char[] txt, char[] pat){
		
		List<Integer> matches = new ArrayList<>();
		int N = txt.length;
		int M = pat.length;
		int[][] TF = constructFA(pat);
		
		for( int i=0, state = 0; i<N; i++) {
			state = TF[state][txt[i]];
			if( state == M) matches.add(i-M+1);
		}
		
		return matches;
	}
	private static int[][] constructFA(char[] pat ) {
		
		int m = pat.length;
		int[][] TF = new int[m+1][NO_OF_CHARS];
		int x, lps = 0;
		for ( x= 0; x<NO_OF_CHARS; x++) TF[0][x] = 0;
		TF[0][pat[0]] = 1;
		
		//O(m+no_of_chars)
		for( int i=1; i<=m; i++) {
			for ( x= 0; x<NO_OF_CHARS; x++) TF[i][x] = TF[lps][x];
			
			//TF[m]에서는 이미 다왔기때문에 다음 x만 고려한다
			if(i!=m) TF[i][pat[i]] = i+1;
			
			//핵심
			if( i < m) lps = TF[lps][pat[i]];
			
		}
		
		return TF;
	}
	
	public static void main(String[] args) {
	
		String txt = "gegegeksgeggegek";
		String pat = "gegek";
		System.out.println(
				search(
				txt.toCharArray(),pat.toCharArray()
				).toString());
		
	}
}
