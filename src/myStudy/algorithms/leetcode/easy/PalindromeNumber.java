package myStudy.algorithms.leetcode.easy;

//Determine whether an integer is a palindrome. An integer is a palindrome when it reads the same backward as forward.
public class PalindromeNumber {

	public static boolean isPalindrome(int num) {
		
		if( num < 0 ) return false;
		String str = String.valueOf(num);
		int n = str.length();
		
		for(int i = 0; i<n;i++) {
			int cmp = str.charAt(i)-48;
			if(cmp != num%10) return false;
			num /= 10;
		}
		return true;
		
	}
	//github에서는 그냥 리버스 시킨 후 input과 대조하는 방식으로 풀었다
	//num<0 -> false 는 동일
	
	public static void main(String[] args) {
		System.out.println(isPalindrome(1234321));
	}
}
