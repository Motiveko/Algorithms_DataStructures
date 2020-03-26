package myStudy.algorithms.leetcode.medium;

import java.lang.reflect.Array;

/*
	Given an array of strings, group anagrams together.
	
	Example:
	
	Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
	Output:
	[
	  ["ate","eat","tea"],
	  ["nat","tan"],
	  ["bat"]
	]
*/

import java.util.*;

public class GroupAnagrams {

	public static List<List<String>> findAnagrams(String[] strs){
		
		List<List<String>> anagrams = new ArrayList<>();
		
		HashMap<String , List<String>> map = new HashMap<>();
		
		for( int i=0; i<strs.length; i++) {
			
			char[] str = new char[strs.length];
			
			for( int j=0; j<strs[i].length() ; j++) {
				str[j] = strs[i].charAt(j);
			}
			Arrays.sort(str);
			String offset = String.valueOf(str);
			if(map.get(offset) == null) map.put(offset, new ArrayList<String>());
			map.get(offset).add(strs[i]);
		}
		
		map.values().stream().forEach( list -> anagrams.add(list));
		 
		return anagrams;
	}
	
	public static void main(String[] args) {
		
		String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
		
		List<List<String>> anagrams = findAnagrams(strs);
		
		for( List<String> anagram : anagrams) System.out.println(anagram.toString());
	}
}
