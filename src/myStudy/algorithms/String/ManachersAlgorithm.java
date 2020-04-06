package myStudy.algorithms.String;

import java.util.*;
//O(n)으로 어떤 문자열 S에서 가능한 모든 Palindrome Substring을 찾는 대단한 알고리듬~
//https://www.crocus.co.kr/1075?category=209527 :: 참고!
public class ManachersAlgorithm {

	public static int[] manachers(char[] str) {
		char[] arr = preProcess(str);
		//c는 센터, r은 palindrome의 끝지점의 index
		//p[i]는 i에서 palindrome의 반지
		int n = arr.length, c=0, r=0;
		
		int[] p = new int[n];
		
		for(int i=1; i<n-1; i++) {
			//invI는 어떤 palindrome의 cente c에대해서 i의 대칭인 점 
			int invI = 2*c - i;
			// c~r사이 i의 p[i]의 값은 대칭점인 invI의 p값인데 i+p[i]가 r을 넘지 않는값이다.
			// p[invI] + i가 r을 넘지 않으면 그값을 넣어준
			p[i] = r > i ? Math.min(r-i, p[invI]) : 0;
			
			while(arr[i+1+p[i]] == arr[i-1-p[i]]) p[i]++;
			
			if( i+p[i] > r) {
				c = i;
				r = i+p[i];
			}
		}
		return p;
	}
	
	private static char[] preProcess(char[] str) {
		char[] arr = new char[str.length * 2 + 3];
		arr[0] = '^';
		
		for(int i=0; i<str.length; i++) {
			arr[ 2*i+1] = '#';
			arr[ 2*i+2] = str[i];
		}
		arr[arr.length-2] = '#';
		arr[arr.length-1] = '$';
		
		return arr;
	}
	
	public static TreeSet<String> findPalindromeSubstrings(String str){
		char[] S = str.toCharArray();
		int N = S.length;
		int[] centers = manachers(S);
		TreeSet<String> palindromes = new TreeSet<>();
		
		for( int i=1; i<centers.length-1; i++) {
			
			int diameter = centers[i];
			
			if( diameter > 0 ) {
				
			
					// 짝수길이, c:#
					//aabaa -> aba -> b 이런식으로
				if( i % 2 == 1) {
					while( diameter > 1) {
						int start = i/2 - diameter/2;
						palindromes.add( new String(S, start, diameter));
						diameter -= 2;
						
					// 홀수길이, c:something char
					}
				} else {
					while( diameter >=1) {
						int start = i/2 - 1-diameter/2;
						palindromes.add( new String(S, start, diameter));
						diameter -= 2;
					}
				}
			}	
		

		}
		
		return palindromes;
	}
	public static void main(String[] args) {
	    String s = "abbaabbababbbaa";

	    System.out.println(findPalindromeSubstrings(s));
	}
}
