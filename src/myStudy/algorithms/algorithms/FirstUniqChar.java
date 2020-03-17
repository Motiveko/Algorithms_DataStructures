package myStudy.algorithms.algorithms;

import java.util.*;

//EASY
//Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.
public class FirstUniqChar {
	
	public static int solve1(String s) {
		if(s ==null || s.length() ==0) return -1;
		int n = s.length();
		Map<Character, Integer> map = new HashMap<>();
		
		for(int i=0; i<n; i++) {
			
			char val = s.charAt(i);
			if(map.containsKey(val)) map.put(val, 2);
			else map.put(val, 1);
		}
		
		for(int i=0; i<n; i++) 
			if(map.get(s.charAt(i)) == 1) return i;
		
		return -1;
	}
	
	//String의 기본 메소드를 이용한 방법, 간단하나 느리다고 한다.
	public static int solve2(String s) {
		if(s ==null || s.length() ==0) return -1;
		int n = s.length();
		
		for(int i=0; i<n; i++) 
			if( s.indexOf(s.charAt(i)) == s.lastIndexOf(s.charAt(i))) 
				return i;
		
		return -1;
	}
	
	public static void main(String[] args) {
		
		System.out.println(solve1("@abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxy"));
		System.out.println(solve2("@abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxy"));

	}
}
