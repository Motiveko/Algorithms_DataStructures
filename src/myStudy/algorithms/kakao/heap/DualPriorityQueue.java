package myStudy.algorithms.kakao.heap;

import java.util.*;

// 카카오 코딩테스트 연습, 힙 3번문제
// https://programmers.co.kr/learn/courses/30/lessons/42628?language=java
public class DualPriorityQueue {

	
	public static int[] solution(String[] operations) {
		
		
		PriorityQueue<Integer> minHeap = new PriorityQueue<>();
		PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				if(o1>o2) return -1;
				return 1;
			}
		});
		
		for( String str : operations) {
			if(str.substring(0, 1).equals("I")) {
				int val = Integer.parseInt(str.substring(2));
				minHeap.add(val);
				maxHeap.add(val);
			} else if( str.equals("D 1") && maxHeap.size()>0) {
				// 최대값 삭제
				int val = maxHeap.poll();
				minHeap.remove(val);
				
			} else {
				// 최소값 삭제
				if(maxHeap.size()>0) {
					int val = minHeap.poll();
					maxHeap.remove(val);
				}
			}
			
		}
		int[] ans;
		if( minHeap.size()==0) ans = new int[] {0,0};
		else ans = new int[]{maxHeap.poll(),minHeap.poll()};		
		
		
		return ans;

	}
	
	public static void main(String[] args) {
		int ans[] = solution(new String[] {"I -45", "I 653", "D 1", "I -642", "I 45", "I 97", "D 1", "D -1", "I 333"});
		System.out.println("{"+ans[0]+","+ans[1]+"}");
		
	}
	
}
