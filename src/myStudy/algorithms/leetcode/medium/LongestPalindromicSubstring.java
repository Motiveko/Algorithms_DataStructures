package myStudy.algorithms.leetcode.medium;

//Palindrome :: 앞에서부터 읽어도, 뒤에서부터 읽어도 똑같은 문자열
public class LongestPalindromicSubstring {

	
	public static String longestPalindrome(String s) {
		
		int n = s.length();
		String subString;
		int start, end;
		boolean isPalindrome;
		//i는 길이
		for(int i=n; i>1; i--) {
			for(int j=0; j+i<=n ; j++) {
				isPalindrome = true;
				subString = s.substring(j,j+i);
//				System.out.println(j + ", " + i);
				start = 0; end = subString.length() -1;
				
				while( start < end) {
					if( subString.charAt(start++) != subString.charAt(end--)) {
						isPalindrome = false;
						break;
					}
				}
				if(isPalindrome) return subString;
			}
		}
		
		
		return s.substring(0,1);
	}
	
	public static void main(String[] args) {
		
		System.out.println(longestPalindrome("adfadaabaafadf"));
	}
}
