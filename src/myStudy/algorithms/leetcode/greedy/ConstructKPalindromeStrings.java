package myStudy.algorithms.leetcode.greedy;

import java.util.*;
/*
 * https://leetcode.com/problems/construct-k-palindrome-strings/
 * 어떤 문자열과 숫자 k가 있을 때 문자열 전체를 사용해 k개의 palindrome(좌우대칭) 을 만들 수 있는지 여부
 */
import java.util.HashMap;

// https://leetcode.com/problems/construct-k-palindrome-strings/submissions/
// input s에 대해서 k개의 palindrome을 만들수 있는지를 출력하는 문제
public class ConstructKPalindromeStrings {
	
	// 나는 풀지 못하였습니다.. 
	// 이 문제의 핵심은 같은문자가 짝수개와 홀수개 일 때 각각 가지는 특성을 파악하는것이다. 
	// 머리를 더 쓰자!
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
		System.out.println(canConstruct("bb",0));
	}
}
