package myStudy.algorithms.leetcode.medium;


import java.util.*;

public class LetterCombinationsOfAPhoneNumber {

	
	public static TreeSet<String> combinations(String digits){
		
		TreeSet<String> set = new TreeSet<>();
		
		HashMap<Integer,String> map = new HashMap<>();
		map.put(2,"abc");
		map.put(3,"def");
		map.put(4,"ghi");
		map.put(5,"jkl");
		map.put(6,"mno");
		map.put(7,"pqrs");
		map.put(8,"tuv");
		map.put(9,"wxyz");
		
		int n = digits.length();
		
		for(int i=0; i<n; i++) {
			 
		}
		return set;
	}
}
