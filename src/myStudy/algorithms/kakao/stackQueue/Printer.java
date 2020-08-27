package myStudy.algorithms.kakao.stackQueue;

import java.util.LinkedList;

//카카오 코딩테스트 연습, 큐/스택 1번문제
//https://www.welcomekakao.com/learn/courses/30/lessons/42587?language=java

public class Printer {

	public static int solution(int[] priorities, int location) {
		
		
		LinkedList<Integer> queue = new LinkedList<>();
		for(int i=0; i<priorities.length ; i++) queue.offer(i);
		
		int[] importance = new int[10];
		for( int p : priorities) importance[p]++;
		
		int res= -1;
		int count=0;
		int tmp; // location
		int priority; // 의 priority
		while(res!=location) {	
			tmp = queue.pop();
			if(queue.isEmpty()) return ++count;
			
			priority = priorities[tmp];
			boolean isHighestPriority = true;
			
			for( int i = priorities[tmp]+1; i<=9; i++ ) {
				if(importance[i]!=0) {
					isHighestPriority= false;
					break;
				}	
			}
			if(isHighestPriority) {
				count++;
				importance[priority]--;
				System.out.println(priority);
				res = tmp;
			}else queue.offer(tmp);
		}
		
		
		return count;
	}
	
	public static void main(String[] args) {
		System.out.println(solution(new int[] {2, 1, 3, 2}, 2));
	}
}
