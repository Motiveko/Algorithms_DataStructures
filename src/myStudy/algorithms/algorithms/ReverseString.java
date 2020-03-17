package myStudy.algorithms.algorithms;

public class ReverseString {

	public static String reverseString(String str) {
		
		int n= str.length();
		char[] ch = new char[n];
		
		for( int i=0; i<n; i++) ch[n-1-i] = str.charAt(i);
		
		
		return String.copyValueOf(ch);
	}
	
	public static void main(String[] args) {
		
		System.out.println(reverseString("버크셔헤서웨이"));
	}
	
}
