package myStudy.algorithms.String;

/*
 * https://www.geeksforgeeks.org/finite-automata-algorithm-for-pattern-searching/
 * FA Algorithm : Pattern Search Algorithm
 * pattern을 토대로 만들어진 FA에서 state는 현재까지 pattern과 txt가 일치하는 문자의 길이를 뜻한다.
 * 만약 txt를 순서대로 읽어서 pattern이 바로 나오면 0->m 까지 바로 가는거고 중간에 다른 문자가 나오면 prefix와 일치여부를 따져 이전 state로 이동한다!
 * 기존의 KMP 에서 lsp 를 찾는것과 비슷한 부분이 있다
 * FAconstruct를 하기만 하면 순서대로 읽어나가면 되므로 O(n)
 * FA construct는 O(m^3 * 문자종류 갯수), O(m * 문자종류갯수) 두가지가 있다!
 */

import java.util.*;

public class FiniteAutomataAlgorithm {

	public static int NO_OF_CHARS = 256;
	
	public static List<Integer> search( char[] txt, char[] pat	) {
		
		List<Integer> matches = new ArrayList<>();
		
		int M = pat.length;
		int N = txt.length;
		
		int[][] TF = new int[M+1][NO_OF_CHARS];
		
		computeTF(pat, TF, M);
		
		for( int i=0, state = 0; i<N; i++) {
			state = TF[state][txt[i]];
			if(state == M) matches.add(i-M+1);
		}
		
		return matches;
	}
	//O(m^3 * 256)
	private static void computeTF(char[] pat, int[][] TF, int M) {
		
		int state, x;
		for( state = 0; state<=M; state++) {
			for( x = 0 ; x<NO_OF_CHARS; x++) {
				TF[state][x] = getNextState(pat,M,state,x);
			}
		}
	}
	private static int getNextState(char[] pat, int M, int state, int x) {
		
		if( state < M && x == pat[state]) return state+1;
		
		int ns, i;
		
		//처음 state는 x를 가르키고 있다고 볼 수 있다. ns-1에서 같은걸 찾으면  0~ns-1 까지 prefix가 suffix인지 찾아보고
		//하나라도 틀리면 다시 ns값을 찾아 떠난다.
		for(ns = state; ns>0 ; ns--) {
			if( pat[ns-1] == x) {
				
				for(i = 0; i<ns-1; i++) {
					if( pat[i] != pat[state - ns +1 +i]) break;
				}
				if( i==ns-1) return ns;
			}	
		}
		
		return 0;
	}

	public static void main(String[] args) {
        char[] txt = "AABAACAADAABAAABAA".toCharArray(); 
        char[] pat = "AABA".toCharArray(); 
        System.out.println(search(txt,pat).toString());
	}
	
}
