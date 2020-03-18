package myStudy.algorithms.leetcode.easy;

import java.util.Stack;

/*
		Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
		
		An input string is valid if:
		
		Open brackets must be closed by the same type of brackets.
		Open brackets must be closed in the correct order.
		Note that an empty string is also considered valid.
	*/
public class ValidParentheses {

	// () {} []
	public static boolean isValidParentheses(String input) {
		
		Stack<Character> stack = new Stack<>();
			for(int i=0; i<input.length(); i++) {
			char next = input.charAt(i);
			if( next == '(' || next =='{' || next=='[') stack.push(reverse(next));
			else {
				if( next != stack.pop()) return false;
			}
		}
		return true;
	}
	
	private static Character reverse(char input) {
		if(input == '(') return ')';
		if(input == '{') return '}';
		if(input == '[') return ']';
		
		return null;
	}
	
	public static void main(String[] args) {
		System.out.println(isValidParentheses("()[]{}"));
		System.out.println(isValidParentheses("({[]})"));
		System.out.println(isValidParentheses("([)]"));
	}
}
