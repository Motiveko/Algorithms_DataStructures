package myStudy.algorithms.leetcode.medium;

/*
	You are given two non-empty linked lists representing two non-negative integers.
	The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
	You may assume the two numbers do not contain any leading zero, except the number 0 itself.
	
	Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
	Output: 7 -> 0 -> 8
	Explanation: 342 + 465 = 807.
*/
//LinkedList, Math

import java.util.*;
public class AddTwoNumbers {

	
	public static LinkedList<Integer> addNumber(
			LinkedList<Integer> l1, LinkedList<Integer> l2){
		
		LinkedList<Integer> sum = new LinkedList<>();
		
		//poll해서 인풋데이터 변하는 일을 방지
		LinkedList<Integer> num1 = (LinkedList<Integer>) l1.clone();
		LinkedList<Integer> num2 = (LinkedList<Integer>) l2.clone();
		
		int len = Math.max(num1.size(), num2.size());
		int over=0;
		for(int i=0; i<len ; i++) {
			int val =0;
			if(!num1.isEmpty()) val += num1.poll();
			if(!num2.isEmpty()) val += num2.poll();
			val += over;
			over=0;
			if(val>=10) {
				val %= 10;
				over = 1;
			}
			
			sum.addLast(val);
		}
		if(over == 1) sum.addLast(over);

		num1 = null;
		num2 = null;
		return sum;
	}
	
	public static void main(String[] args) {
		LinkedList<Integer> num1 = new LinkedList<>();
		LinkedList<Integer> num2 = new LinkedList<>();
		
		num1.addLast(1);
		num1.addLast(0);
		num1.addLast(9);
		
		num2.addLast(2);
		num2.addLast(4);
		num2.addLast(6);
		
		LinkedList<Integer> sum = addNumber(num1, num2);
		System.out.println(901+642);
		System.out.println(sum.toString());
	}
}
