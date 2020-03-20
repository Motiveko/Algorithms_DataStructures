package myStudy.algorithms.leetcode.easy;
//다시보기

/* Given a non-negative index k where k ≤ 33, return the kth index row of the Pascal's triangle.
 * Note that the row index starts from 0.
 * Could you optimize your algorithm to use only O(k) extra space?
 */

import java.util.*;
//나는 못풀었다 ㅜ ㅡ ㅜ
public class PascalsTriangle2 {
	
	public static List<Integer> pt ( int k){

		List<Integer> pt = new ArrayList<>();
		
		for(int i=0; i<=k; i++) {
			pt.add(1);
			for(int j=i-1; j>0; j--) {
				pt.set(j, pt.get(j)+ pt.get(j-1));
			}
		}
		
		
		return pt;
	}
	
	public static void main(String[] args) {
		int n=5;
		List<Integer> pt = pt(n);
		System.out.println("LEVLE " + n + " : " + pt.toString());
	}

}
