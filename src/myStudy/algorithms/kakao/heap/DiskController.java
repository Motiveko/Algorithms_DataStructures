package myStudy.algorithms.kakao.heap;


import java.util.*;

// 카카오 코딩테스트 연습, 힙 2번문제
// https://programmers.co.kr/learn/courses/30/lessons/42627?language=java
public class DiskController {

	public static int solution(int[][] jobs) {
		/* jobs : [ {요청시점, 작업크기} , ... ]
		 *할 수 있는 작업들 중 빠르게 끝내는걸 먼저하는게 좋다.
		 *jobs[0][0], jobs[0][1]
		 * 먼저 jobs[0][0] (요청시점) 기준으로 배열 정렬
		 * 순서대로 읽어서 pq에 jobs[n][1] 을 offer해 준다.
		 * pq에 값이 있으면 peek를 하는데 요청 시간이 offer 시간 + peek값 이하인 애들도 찾는다.
		 */
		
		int n = jobs.length;
		
		ArrayComparator comparator = new ArrayComparator(); 
		Arrays.sort(jobs,comparator); // 2차원 배열 오름차순 정렬
		
		PriorityQueue<Integer> pq = new PriorityQueue<>(); // 현재 작업 스택
		int curTime = 0;
		int timeSum = 0;
		
		// Initialize first job
		int i= 0;
		pq.add(jobs[i][1]);
		curTime = jobs[i++][0];
		while( i<n && jobs[i][0] <= curTime) pq.add(jobs[i++][1]);
		
		while(!pq.isEmpty()) {
			int taskTime = pq.poll();
			curTime += taskTime;
			timeSum += taskTime*(pq.size()+1);
			
			// 작업 중 요청이 들어온게 있다면
			while( i<n  &&  jobs[i][0] <= curTime) {
				pq.add(jobs[i][1]);
				timeSum += curTime-jobs[i][0]; // 작업중 대기시간 ++	
				i++;
			}
			
			// 공백기가 존재한다면?
			if( pq.size()==0 && i<n) {
				curTime = jobs[i][0];
				pq.add(jobs[i++][1]);
				// 공백기 후 동시에 여러작업이 있다면	
				while( jobs[i][0]==curTime) {
					pq.add(jobs[i++][1]);
				}
			}
		}
		
		return timeSum/n;
	}
	
	static class ArrayComparator implements Comparator<int[]>{

		@Override
		public int compare(int[] o1, int[] o2) {
			if(o1[0] > o2[0]) return 1;
			else if(o1[0]==o2[0]) return 0;
			return -1;
		}
		
	}
	
	
	public static void main(String[] args) {
//		ArrayComparator comparator = new ArrayComparator(); 
//		int[][] input = new int[][] {{5,4},{4,3},{3,2},{6,1}};
//		Arrays.sort(input,comparator);

		System.out.println(solution(new int[][] {{1,9},{0,3},{2,6}}));

	}
}
