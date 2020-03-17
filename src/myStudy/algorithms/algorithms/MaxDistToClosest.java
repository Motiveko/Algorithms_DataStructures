package myStudy.algorithms.algorithms;

import java.util.Random;
//EASY
/*
In a row of seats, 1 represents a person sitting in that seat, 
and 0 represents that the seat is empty. 
There is at least one empty seat, and at least one person sitting.
Alex wants to sit in the seat such that the distance between him 
and the closest person to him is maximized. 
Return that maximum distance to closest person.
Example 1:
Input: [1,0,0,0,1,0,1]
Output: 2
Explanation: 
If Alex sits in the second open seat (seats[2]), then the closest person has distance 2.
If Alex sits in any other open seat, the closest person has distance 1.
Thus, the maximum distance to the closest person is 2.
Example 2:
Input: [1,0,0,0]
Output: 3
Explanation: 
If Alex sits in the last seat, the closest person is 3 seats away.
This is the maximum distance possible, so the answer is 3.
Note:
1 <= seats.length <= 20000
seats contains only 0s or 1s, at least one 0, and at least one 1.
 */
public class MaxDistToClosest {

	
	public static int maxDistToClosest(int[] seats) {
		
		int n = seats.length;
		int flag = 0;
		
		int maxDist=0;
		int curDist=0;
		for(int i=0; i<n; i++) {
			
			if(seats[i] ==1) {
				//시작이 0이면서 첫 flag
				if(seats[0]==0 && flag == 0) {
					maxDist = i+1;
					flag = i;
				}
				curDist = (i-flag)/2;
				maxDist = Math.max(maxDist, curDist);
				flag = i;
			}
			
			//마지막 값이 0일 경우 길이계산
			if( i==n-1 && seats[i]==0) {
				curDist = i-flag;
				maxDist = Math.max(maxDist, curDist);
			}
		}
		
		return maxDist;
	}
	
	public static void main(String[] args) {
		int n = 1000;
		int[] seats = new int[n];
		Random ran = new Random();
		for(int i=0; i<n; i++) seats[i] = ran.nextInt(2); 
		
		System.out.println(maxDistToClosest(seats));
	}
}
