package myStudy.algorithms.kakao.stackQueue;

import java.util.*;

//카카오 코딩테스트 연습, 큐/스택 1번문제
//https://www.welcomekakao.com/learn/courses/30/lessons/42587?language=java

public class Printer {

	private static final Comparator<? super Integer> IntComparator = new IntComparator();

	public static int solution(int[] priorities, int location) {
		
		int n = priorities.length; 
		LinkedList<Integer> queue = new LinkedList<>(); // stores location
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(IntComparator); // priorities, 높은게 맨앞에
		
		for( int i=0; i<n; i++) {
			queue.add(i);
			pq.add(priorities[i]);
		}
		
		int res=-1;
		while(res!=location) {
			// 현재 priority보다 높은 priority가 있는 경우check
			int loc = queue.poll();
			if( priorities[loc] >= pq.peek()) {
				res = loc;
				pq.poll();
			} else queue.add(loc);	
		}
		
		return n-queue.size();
	}
	
	static class IntComparator implements Comparator<Integer>{

		@Override
		public int compare(Integer o1, Integer o2) {
			if(o1>o2) return -1;
			else if(o1==o2) return 0;
			return 1;
		}
		
	}
	
	// 프로그래머스 모범답안
	// 굳이 현재 위치를 찾기 위해 location들을 큐에 넣어 그걸 가지고 참조를 하는게 아니라 
	// 그냥 priorities를 큐에 넣고 내가 찾는 위치에 l이라는 포인터만 넣은형태.
	public static int solution2(int[] priorities, int location) {
		
        int answer = 0;
        int l = location; //찾고싶은놈의 que상의 위치

        Queue<Integer> que = new LinkedList<Integer>();
        for(int i : priorities){
            que.add(i);
        }

        Arrays.sort(priorities);
        int size = priorities.length-1;



        while(!que.isEmpty()){
            int i = que.poll();
            if(i == priorities[size - answer]){
                answer++;
                l--; // 큐가 계속 움직이므로 --해준다.	
                if(l <0)	// 만약에 l:0에서 빠져나가 -1이된거면 찾는애가 출력된거다.	
                    break;
            }else{
                que.add(i);
                l--;
                if(l<0) // 우리가 찾는애는 이번엔 못나가고 뒤로 밀렸다.
                    l=que.size()-1;
            }
        }

        return answer;
	}
	
	
	public static int solution3(int[] priorities, int location) {
		
		
		LinkedList<Integer> queue = new LinkedList<>();
		int loc = location;
		int answer = 0;
		int n = priorities.length;
		
		for( int priority: priorities) queue.add(priority);
		Arrays.sort(priorities);
		
		while(!queue.isEmpty()) {
			int p = queue.poll();
			if( p == priorities[n-answer-1]) {
				
				answer++;
				if( loc-- ==0) break;
				
			} else {
				queue.offer(p);
				if(loc-- ==0) loc = queue.size()-1;
			}
		}
		
		return answer;
		
	}
	
	public static void main(String[] args) {
		System.out.println(solution3(new int[] {2, 1, 3, 2}, 1));
	}
}
