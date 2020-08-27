package myStudy.algorithms.kakao.stackQueue;

import java.util.LinkedList;
import java.util.Queue;

// 카카오 코딩테스트 연습, 큐/스택 1번문제
// https://www.welcomekakao.com/learn/courses/30/lessons/42583?language=java
public class TruckCrossingBridge {
 
    public static int solution(int bridge_length, int weight, int[] truck_weights) {
        
    	Queue<Integer > bridge = new LinkedList<Integer>(); // 지금 다리위에 있는 트럭의 index	
    	int[] truckPosition = new int[truck_weights.length]; // 각 트럭들이 건너가야 할 남은 거리, 들어갈 때 초기화

    	
    	// 첫트럭 먼저 넣어주고 시작
    	int answer = 1;
    	int curWeight = truck_weights[0];
    	truckPosition[0] = bridge_length; 
    	bridge.add(0);
    	
    	for( int i=1; i<truck_weights.length; i++) {
    		int truck = truck_weights[i]; // 대기중인 맨 앞 트럭
	    	answer ++; // 시간이 흘렀슴니다.
    		
    		// 다 건넌애가 있니?
	    	for( int j = bridge.peek() ; j<i; j++) truckPosition[j]--; // 들어가계신분들 전부 한칸씩 앞으로
    		if(truckPosition[bridge.peek()]==0) {
    			curWeight -= truck_weights[bridge.peek()];
    			bridge.poll();
    		}
    		
    		// 다음녀석 고려해봅시다.	
    		if( curWeight + truck <= weight ) {
    			bridge.add(i);
    			curWeight += truck;
    			truckPosition[i] = bridge_length;	
    		} else i--; // 못들어갔으면 다시 대기
    	}
    	answer += bridge_length;
    	
        return answer;
    }
    
    public static void main(String[] args) {
		
    	System.out.println(solution(2, 10, new int[] {7,4,5,6,}));
	}
	
}
