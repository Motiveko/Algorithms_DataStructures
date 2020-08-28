package myStudy.algorithms.kakao.heap;

import java.util.*;

// 카카오 코딩테스트 연습, 힙 1번문
// https://programmers.co.kr/learn/courses/30/lessons/42626?language=java
public class Hotter {

	
	
    public int solution(int[] scoville, int K) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        for( int s : scoville) pq.add(s);
        int answer = 0;
        while(pq.size()>1 && pq.peek()<K) {
        	
        	int mixed = pq.poll() + pq.poll()*2;
        	pq.add(mixed);
        	answer ++;
        }
        if( pq.poll() < K ) return -1;
        
        return answer;
    }
	
	
	
}
