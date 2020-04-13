package myStudy.algorithms.leetcode.medium;


import java.util.*;


//문제는 S의 SUBSTRING의 PALINDROME이 K개가 가능하냐가 아니라 그냥 S의 CHAR 하나하나를 조합해서 PALINDROME을 K개 만드느냐이다!
public class ConstructKPalindromeStrings {
	
	
	public static boolean canConstruct(String s, int k) {
		
        if (s.length() < k) {
            return false;
        }
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        int count = 0;
        for (char c : map.keySet()) {
            if (map.get(c) % 2 == 1) {
                count++;
            }
        }
        return count <= k;
	}
	
	public static void main(String[] args) {
		
		System.out.println(canConstruct("AAAABBB",1));
		
	}
}
