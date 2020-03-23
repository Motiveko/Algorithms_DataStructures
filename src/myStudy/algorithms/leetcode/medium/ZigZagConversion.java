package myStudy.algorithms.leetcode.medium;

import java.util.*;

public class ZigZagConversion {

	public static List<String>[] zigZagConversion(String str,int n){
		
		List<String>[] ans = new ArrayList[n];
		
		for(int i=0; i<n; i++) ans[i] = new ArrayList<>();
		
		int len = str.length();
		
		for(int i=0,k=0; i<len; i++) {
		
			if( (i / (n-1)) % 2 == 0) {
				
				ans[k++].add(String.valueOf(str.charAt(i)));
			} else {
				
				ans[k--].add(String.valueOf(str.charAt(i)));
			}
		}
		
		return ans;
	}
	
	//GitHubVersion, 공간복잡도 측면에서 좀 더 유리, 큰차이는 없다
	public static String convert(String s, int numRows) {
		
		if( numRows <=1 ) return s;
		int len = s.length();
		char[] chars = s.toCharArray();
		int cycle = 2*(numRows - 1);
		StringBuilder sb = new StringBuilder();
		
		//첫째줄(0번줄)
		for( int j=0; j < len; j+= cycle) {
			sb.append(chars[j]);
		}
		
		//1~numRow-2 번줄
		for(int i=1; i<numRows -1; i++) {
			int step = 2*i;
			// 신박하다!!!!!!!!!!! step = cycle - step을 할때마다
			// step = cycle -2i , step = 2i가 왔다갔다 반복된다!
			// 각 행에 값을 차례대로 집어넣는 과정
			for(int j=i; j<len; j+=step) {
				sb.append(chars[j]);
				step = cycle - step;
			}
		}
		//마지막줄(numRows-1 번줄)
		for(int j = numRows - 1; j<len; j+= cycle) {
			sb.append(chars[j]);
		}
		
		
		return sb.toString();
	}
	
	public static void main(String[] args) {
		
		List<String>[] ans = zigZagConversion("PAYPALISHIRING", 4);
		for(int i=0; i<ans.length; i++) 
			System.out.println(ans[i].toString());

		System.out.println(convert("PAYPALISHIRING",4));
	}
}
