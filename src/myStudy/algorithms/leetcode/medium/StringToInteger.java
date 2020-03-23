package myStudy.algorithms.leetcode.medium;

import java.util.*;

public class StringToInteger {
	
	public static Integer atoi(String s) {
		
		int len = s.length();
		int start=-1;
		int sign = 1;
		Integer ans=0;
		for( int i=0; i<len; i++	) {
			int tmp = s.charAt(i) - '0';
			if(s.charAt(i)==' ') {
				if(start==-1) continue;
				else break;
			}
			else if(s.charAt(i)=='-'|| s.charAt(i)=='+') {
				if(start==-1) sign = s.charAt(i)=='-' ? -1 : 1;
				else break;
			}else if(tmp>0 && tmp<=9) {
				if(start ==-1) start = i;
				if( (sign ==1 && ans> Integer.MAX_VALUE/10 )||( sign==-1 && ans<Integer.MIN_VALUE/10)) 
					return sign==1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
				ans = ans * 10 + tmp;
			} else  {
				if(start == -1)return 0;
				else break;
			}
		}


		return ans*sign;
	}
	
	public static void main(String[] args) {
		//-2147483648 ~ 2147483647 
		 System.out.println(atoi("  1 35+121233"));
		 System.out.println();
	}
	
	
}
