package myStudy.algorithms.String;

/*
 * https://www.geeksforgeeks.org/rabin-karp-algorithm-for-pattern-searching/
 * 이 글은 해싱의 방식을 10진수로 예를 들며 설명해서 이해하기 쉽다.
 */

/*
 * 참고 :: https://blog.naver.com/dinky_apple/221850206355
 * Rolling Hash :: shttps://blog.naver.com/babobigi/220911965116 
 * Rabin-Karp는 어떤 문자열S에서 패턴 P(subString)을 찾기 위한 함수다.
 * Naive한 함수는 S를 P의 길이만큼 각각 잘라서 전부 검사하면 O(SP)가 나오는데
 * Rabin-Karp는 P를 어떤 int k로 Hashing하고, S도 전부  P길이만큼 잘라서 정수로 Hashing한 
 * 뒤 여기서 k를 찾아내는 방식으로 작동한다. 따라서 Complexity는 O(S)
 * 
 * 알고리즘의 효율의 핵심은 해싱을 얼마나 잘 하느냐에 달려있다.(최소의 해시충돌)
 */

//Fiset의 코드는 너무 어려워서 geeksforgeeks 코드를 참조함

import java.util.*;

public class RabinKarp {
	
	//d is the number of characters in the input alphabet
	public final static int d = 256;
	
	// q : prime number
	public static List<Integer> rabinKarp(String txt, String pat, int q){
		
		List<Integer> matches = new ArrayList<>();
		
		int M = pat.length();
		int N = txt.length();
		int i, j;
		int p = 0; // hash value for pattern
		int t = 0; // hash value for txt
		int h = 1; 
		
		// h-> pow(d,M-1)%q.. :: 매 회차마다 나머지 해주는 이유는 값이 2^31보다 커질 수 있기때문
		// h = xq +y ( y는 q로나눈 나머지) 로 해서 계산해보면 결국 마지막에 나눠주나 매번 나눠주나 똑같은 것을 알 수 있다.
		// mod q 에 대해서는 HashTable을 복습하자.
		for( i = 0 ; i< M-1; i++) h = (h*d)%q;
		
		//txt의 맨 처음 window에 대한 hash값, pattern의 해쉬값 구함 
		for( i =0; i<M; i++) {
			p = (d*p + pat.charAt(i))%q;
			t = (d*t + txt.charAt(i))%q;
		}
		
		for( i = 0; i<= N-M; i++) {
			
			//Hash값이 같은것을 찾으면 char을 하나씩 비교해본다.
			if(p==t) {
				for( j =0; j<M; j++) {
					if( txt.charAt(i+j) != pat.charAt(j))
						break;
				}
				if( j == M ) matches.add(i);
			}
			
			//txt의 next window에 대한 hash값을 current window의 hash값을 이용해 계산한다. O(1)
			if( i < N-M) {
				t = (d*(t - txt.charAt(i)*h) + txt.charAt(i+M))%q;
				if( t< 0) t = (t + q);
			}
		}
		
		return matches;
	}
	
	public static void main(String[] args) {
		List<Integer> matches = rabinKarp("P@TTerNabcdefP@TTerNP@TTerNabcdefabcdefabcdefabcdefP@TTerN", "P@TTerN", 17);
		System.out.println(matches.toString());

	}
	

}
