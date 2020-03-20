package myStudy.algorithms.leetcode.easy;

import java.util.*;
public class PascalsTriangle {

	public static List<List<Integer>> pascalsTriangle(int lv){
		
		List<List<Integer>> pt = new ArrayList<>();
		
		//lv0은 안쓸거고 1~lv까지 쓸 것임.
		for(int i=0 ; i<=lv; i++) pt.add(new ArrayList<>());
		
		//initialize lv 1,2
		pt.get(1).add(1);
		pt.get(2).add(1); pt.get(2).add(1);
		
		for( int i=2; i<lv; i++) {
			pt.get(i+1).add(1);
			for(int j=0; j<pt.get(i).size()-1 ; j++) {
				int sum = pt.get(i).get(j) + pt.get(i).get(j+1);
				pt.get(i+1).add(sum);
			}
			pt.get(i+1).add(1);
		}
		
		return pt;
	}
	
	public static void main(String[] args) {
		
		List<List<Integer>> pt = pascalsTriangle(7);
		
		for(int i=1; i<pt.size(); i++) {
			System.out.print("LEVEL " + i + " : ");
			System.out.println(pt.get(i).toString());;
			System.out.println();
		}
	}
}
