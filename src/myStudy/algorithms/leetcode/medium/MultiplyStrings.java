package myStudy.algorithms.leetcode.medium;

/*
	Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2, also represented as a string.
	
	Example 1:
	
	Input: num1 = "2", num2 = "3"
	Output: "6"
	Example 2:
	
	Input: num1 = "123", num2 = "456"
	Output: "56088"
	Note:
	
	The length of both num1 and num2 is < 110.
	Both num1 and num2 contain only digits 0-9.
	Both num1 and num2 do not contain any leading zero, except the number 0 itself.
	You must not use any built-in BigInteger library or convert the inputs to integer directly.
*/

import java.util.*;

public class MultiplyStrings {

	public static int multiply(String str1, String str2) {
		
		int num1=0, num2=0;
		
		for(int i=0; i<str1.length() ; i++) num1 = 10*num1 + (str1.charAt(i)-'0');

		
		for(int i=0; i<str2.length() ; i++) num2 = 10*num2 + (str2.charAt(i)-'0');
		
		
		System.out.println(num1 + ";"+ num2);
		return num1 * num2;
		
	}
	
	//int안쓰고 하는거
	
	public static String multiply2(String str1, String str2) {
		
		if(str1.equals("0") || str2.equals("0")) return "0";	
		char[] ans = new char[5];
		
		char[] ch1 = new char[3];
		char[] ch2 = new char[3];
		Arrays.fill(ans, '0');
		Arrays.fill(ch1, '0');
		Arrays.fill(ch2, '0');
		
		for(int i=0; i<str1.length(); i++) ch1[i] = str1.charAt(str1.length()-1-i);
		for(int i=0; i<str2.length(); i++) ch2[i] = str2.charAt(str2.length()-1-i);

		int tmp;
		
		for(int i=0; i<3; i++) {
			
			for(int j=0; j<3; j++) {
				if(ch2[i]=='0') break;
				int radix = i+j;
				int val = (ch1[j] - '0')*(ch2[i] -'0');
				if(val >=10) {
					tmp = val/10;
					val %= 10;
					ans[radix+1] += tmp ;
				}
				ans[radix] += val;
			}
		}
		
		//변환과정,, 귀찮다
		String str="";
		boolean bo=false;
		for(int i=ans.length-1; i>=0;i--) {
			if(ans[i] != '0') bo=true;
			if(bo) str+=ans[i];
		}
		return str;
	}
	
	public static void main(String[] args) {
		System.out.println(multiply2("11","30"));
	}
}
