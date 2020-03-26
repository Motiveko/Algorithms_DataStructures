package myStudy.algorithms.leetcode.medium;

/*
 	못풀었다. 꼭다시보기 이렇게 단순한논리로 풀 수 있었다니 ㅜ ㅡ ㅜ 
	Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
	For example, given n = 3, a solution set is:
*/
import java.util.*;
public class GenerateParentheses {

	public static List<String> generate(int n){
	
		List<String> ans = new ArrayList<>();
	
		helper(ans,"",0,n);
		return ans;
	}
	
	public static void helper(List<String> ans, String str, int rightNeed, int leftRest){
		
		if( rightNeed == 0 && leftRest ==0) ans.add(str);
		
		if( rightNeed > 0) helper(ans, str+")" , rightNeed-1, leftRest);
		if( leftRest > 0) helper(ans, str+"(", rightNeed+1, leftRest-1);
		
	}
	
	public static void main(String[] args) {
		
		int n = 3;
		List<String> ans = generate(n);
	
		System.out.println(ans.toString());
		
		List<String> list = Collections.singletonList("2");
		System.out.println(list.toString());
	}
}
