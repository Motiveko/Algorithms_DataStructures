package myStudy.algorithms.leetcode.easy;

//Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
public class ImplementstrStr {

	public static int strStr( String haystack, String needle) {
		
		int len = needle.length();
		
		for(int i=0; i<haystack.length() - len ; i++) {
			if(haystack.substring(i, i +len).equals(needle)) return i;
		}
		
		return -1;
	}
	
	public static void main(String[] args) {
		
		String haystack = "hello", needle = "ll";
		System.out.println(strStr(haystack,needle));
	}
}
