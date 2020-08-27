package myStudy.algorithms.kakao.stackQueue;

//카카오 코딩테스트 연습, 큐/스택 1번문제
// https://www.welcomekakao.com/learn/courses/30/lessons/42584?language=java
import java.util.Stack;

public class StockPrice {

	public static class Solution{
		
		public static int[] solution(int[] prices) {
			
			int n= prices.length;
			int[] answer = new int[n];
			
			answer[n-1] = 0;
			
			Stack<Integer> stack =  new Stack<>(); // times
			
			stack.push(n-1);
			
			for( int i = n-2; i>=0 ; i--) {
				while( !stack.empty() && prices[stack.peek()] >= prices[i]) {
					stack.pop();
				}
				if(stack.empty()) {
					answer[i] = n-i-1;
					stack.push(i);
				} else {
					answer[i] = stack.peek()-i;
					stack.push(i);
				}
			}
			return answer;
		}
		 // 프로그래머스 다른사람답안
		public static int[] solution2(int[] prices) {
	        Stack<Integer> beginIdxs = new Stack<>();
	        int i=0;
	        int[] terms = new int[prices.length];

	        beginIdxs.push(i);
	        for (i=1; i<prices.length; i++) {
	            while (!beginIdxs.empty() && prices[i] < prices[beginIdxs.peek()]) {
	                int beginIdx = beginIdxs.pop();
	                terms[beginIdx] = i - beginIdx;
	            }
	            beginIdxs.push(i);
	        }
	        while (!beginIdxs.empty()) {
	            int beginIdx = beginIdxs.pop();
	            terms[beginIdx] = i - beginIdx - 1;
	        }

	        return terms;
		}
		public static void main(String[] args) {
			int[] input = {1,2,3,4,5};
			int[] output = Solution.solution2(input);
			System.out.print("{");
			for( int i=0; i<output.length;i++) {
				System.out.print(output[i]+",");
				
			}
			System.out.print("}");
			
		}
	}
}
