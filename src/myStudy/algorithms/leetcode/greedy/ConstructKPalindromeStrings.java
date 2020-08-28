package myStudy.algorithms.leetcode.greedy;

import java.util.*;
/*
 * https://leetcode.com/problems/construct-k-palindrome-strings/
 * 어떤 문자열과 숫자 k가 있을 때 문자열 전체를 사용해 k개의 palindrome(좌우대칭) 을 만들 수 있는지 여부
 */
import java.util.HashMap;

public class ConstructKPalindromeStrings {
	
	public static boolean canConstruct(String s, int k) {
		
		int n = s.length();
		if( n==k ) return true;
		HashMap<String,Integer> map = new HashMap<>();
		
		for( int i=0 ;i<n; i++) map.put(s.substring(i,i+1), map.getOrDefault(s.substring(i,i+1),0)+1);
		
		int[] arr = map.values().stream().mapToInt(Integer::intValue).toArray();
		Arrays.sort(arr); // 캐릭터 갯수 순서대로..
		
		
		return false;
	}

}
