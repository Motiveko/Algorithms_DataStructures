package myStudy.algorithms.kakao.stackQueue;

import java.util.ArrayList;
import java.util.Stack;

// 카카오 코딩테스트 연습, 큐/스택 2번문제
// https://www.welcomekakao.com/learn/courses/30/lessons/42586?language=java

public class DevelopFunction {

	public static class Solution{
		
		public static int[] solution(int[] progresses, int[] speeds) {
			
			
			int n = speeds.length;
			ArrayList<Integer> list = new ArrayList<>(); // 정답이 들어갈 arrayList	
			Stack<Integer> stack = new Stack<>();
			for( int i = n-1; i>=0; i--) {
				
				// 최소 배포 시간 : (100-p[i])/s[i] 의 올림
				int requiredDay =(int) Math.ceil((double)(100-progresses[i])/speeds[i]);
				stack.push(requiredDay);
			}
			int maxDay = stack.pop();
			int count = 1; // 하루에 배포되는 서비스 카운트
			while(!stack.isEmpty()) {
				if( stack.peek() <= maxDay) {
					count++;
					stack.pop();
				} else {
					list.add(count);
					count = 1;
					maxDay = stack.pop();
				}
				
			}
			list.add(count);
			

			// List -> Array로! 모범답안에서 참조함
			
			return list.stream().mapToInt(Integer::intValue).toArray();
		}
	}
    
	// ****TEST****
    public static void main(String[] args) {    	
									// 7 , 3, 9
    	int[] progresses1 = new int[] {93, 30, 55};
    	int[] speeds1 = new int[]{1, 30, 5};
    								// 5, 10, 1, 1, 20, 1
    	int[] progresses2 = new int[]{95, 90, 99, 99, 80, 99};
    	int[] speeds2 = new int[] {1, 1, 1, 1, 1, 1};
    	
    	int[] ans1 = Solution.solution(progresses1,speeds1);
    	int[] ans2 = Solution.solution(progresses2,speeds2);
    	
    	System.out.print("{");
    	for( int a1 : ans1 ) {
    		System.out.print(a1 + ",");
    	}
    	System.out.print("}");
    	System.out.println("");
    	System.out.print("{");
    	for( int a2 : ans2 ) {
    		System.out.print(a2 + ",");
    	}
    	System.out.print("}");
	}
	
}
