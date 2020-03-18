package myStudy.algorithms.leetcode.easy;

public class LongestCommonPrefix {

	public static String lcp(String[] input) {
		
		boolean isCommon ;
		for( int i =0; ; i++ ) {
			
			char cmp = input[0].charAt(i);
			isCommon = true;
			
			for(String str : input) {
				if(str.length() <= i) {
					isCommon = false;
					break;
				}
				if(str.charAt(i) != cmp) {
					isCommon = false;
					break;
				}	
			}
			//출력은 이렇게 하는게 최고다(gitHub Ver)
			if(!isCommon) return input[0].substring(0, i);
		}
	}
	
	public static void main(String[] args) {
		
		String[] input = {"flower","flow","flight"};
		System.out.println(lcp(input));
		
		String[] input2 = {"dog","racecar","car"};
		System.out.println(lcp(input2));
	}
	
}
