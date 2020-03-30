package myStudy.algorithms.String;

/*
 * https://www.geeksforgeeks.org/kmp-algorithm-for-pattern-searching/
 * Pattern search Algorithm with O(n)
 * pattern에서 lps[]( longest prefix suffix)를 뽑아내서 text를 i=[0,n)까지 검사 할 때
 * lps길이만큼은 빼고 검사할 수 있게 하는 알고리즘이다!
 * 
 * Z-algorithm 과 아주 약간의 유사한 느낌이 있는데, 어쨋던 이것도 어느날 이해가 잘 안되는거 같다면
 * p1p2p1p3 라는 pattern을 만들어서 생각해보면 쉽지 않을까 한다
 * lps만드는 helper도 마찬가지다 p1p2p1까지 일치했는데 p3시작할때 걸리면 그 앞의 p1은 살리는 알고리즘이라고 할 수 있다.
 */

import java.util.*;

public class KMP {

	public static List<Integer> kmp(String txt, String pat){
		List<Integer> matches =new ArrayList<>();
		
		if( txt == null || pat == null) return matches;
		
		int m = pat.length(), n = txt.length(), i=0, j=0;
		
		int[] lps = createLps(pat);
		
//		while( i<n) {
//			if( txt.charAt(i)==pat.charAt(j)) {
//				i++; 
//				j++;
//			} else {
//				if(j>0 && lps[j-1]!=0) {
//					j= lps[j-1];
//				} else {
//					i++; j=0;
//				}
//			}
//			if( j==m) {
//				matches.add(i-j);
//				j= lps[j-1];
//			}
//		}
		
		//fiset Ver, 비슷하나 약간 다르므로 구현해본다
		while( i<n) {
			
			if( txt.charAt(i)==pat.charAt(j)) {
				i++; j++;
				
			}
			if( j==m) {
				matches.add(i-j);
				j = lps[j-1];
			} else if( i<n && txt.charAt(i)!=pat.charAt(j)) {
				if( j!=0 ) j =lps[j-1];
				else i++;
			}
		}
		
		return matches;	
	}
	//myCode for lps,Naive, O(n^2) :: 갖다버려도 된다 
	private static int[] createLps(String pat) {
		int m = pat.length();
		int[] lps = new int[m];
		for(int i=1; i<m; i++) {
			String str = pat.substring(0, i+1);
			int j=0;
			for(int k=(i+1)/2; k<=i;k++) {
				if( str.charAt(j) == str.charAt(k)) j++;
				else j = 0;
			}
			lps[i] = j;
		}
		
		return lps;
	}
	
	//fisetCode for lps, O(n)
	//존나 신박하다.. lps는 kmp의 일부인데, kmp와 상당히 비슷한 느낌으로 구현된다. 무조건 복습습습
	private static int[] kmpHelper(String pat, int m) {
		int[] lps = new int[m];
		
		for( int i=1, len=0; i<m; i++) {
			
			if(pat.charAt(i)==pat.charAt(len)) {
				lps[i++] = ++len;
			}
			else {
				//이부분이 fiset ver kmp 자체 알고리즘과 비슷.
				if( len > 0) len = lps[len-1];
				else i++;
			}
			
		}
		
		
		return lps;
	}
	
	public static void main(String[] args) {
		List<Integer> matches = kmp("P@TTerNabcdefP@TTerNP@TTerNabcdefabcdefabcdefabcdefP@TTerN", "P@TTerN");
		System.out.println(matches.toString());
	}
}