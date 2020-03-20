package myStudy.algorithms.leetcode.medium;
//약간다시보기

import java.util.*;

public class LongestSubstringWithoutRepeatingCharacters {

	public static String longestSubstring(String s) {
		
		int start = 0;
		int end;
		String subString = s.substring(0,1);
		String longestSubString ="";
		for(int i=2; i<=s.length(); i++) {
			end = i;

		
			if(subString.contains(s.subSequence(end-1, end))) start = s.indexOf(s.charAt(end-1), start) +1;
			subString = s.substring(start, end);
			
			longestSubString = longestSubString.length() < subString.length() ? subString : longestSubString;
			
		}
		
		return longestSubString;
	}

	/*
	 * GITHUB방식 . 좋은건지는 잘 모르겠지만 일단 알면 득될거같아서 풀어봄 출력은 길이만 hash에는 어떤 char의 위치가 적혀져있고
	 * prep은 substring의 시작점을 의미하는듯.
	 * 중복된 char이 나오면 hash에서 위치를 받아 prep에 넣고 새로운 substring의 range를 만들어 길이를 계산.
	 * 그 뒤 hash에는 현재 나온 중복된 char의 위치를 집어늫는다.
	 */	
	public static int lengthOfLongestSubstring(String s) {
		
		if(s==null || s.length() ==0) return 0;
		int preP=0, l=0;
		int max = 0;
		int[] hash = new int[128];
		
		for( int i=0; i<s.length(); i++) {
			
			char c = s.charAt(i);
			if( preP < hash[c]) preP = hash[c];
			
			int len = i - preP +1;
			hash[c] = i+1;
			max = Math.max(max, len);
		}
		
		return max;
	}
	
	public static void main(String[] args) {
		
		System.out.println(longestSubstring("abcabcdbefgh"));
		
		System.out.println(lengthOfLongestSubstring("abcabcdbefgh"));
	}
	
}
