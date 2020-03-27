package myStudy.algorithms.leetcode.medium;

/*
	There is a brick wall in front of you. The wall is rectangular and has several rows of bricks. The bricks have the same height but different width. 
	You want to draw a vertical line from the top to the bottom and cross the least bricks.
	The brick wall is represented by a list of rows. Each row is a list of integers representing the width of each brick in this row from left to right.
	If your line go through the edge of a brick, then the brick is not considered as crossed. 
	You need to find out how to draw the line to cross the least bricks and return the number of crossed bricks.
	You cannot draw a line just along one of the two vertical edges of the wall, in which case the line will obviously cross no bricks.
	
	Example:
	Input:
	[[1,2,2,1],
	 [3,1,2],
	 [1,3,2],
	 [2,4],
	 [3,1,2],
	 [1,3,1,1]]
	Output: 2
*/

import java.util.*;

public class BrickWall {
		
	public static int leastBricks(List<List<Integer>> wall	) {
		
		int sum	=0;
		for( Integer n : wall.get(0)) sum+=n;
		int[] blanks = new int[sum+1];
		
		//blank계산

		for( List<Integer> layer : wall) {
			sum=0;
			for(Integer n : layer) {
				sum+=n;
				blanks[sum]++;
			}
		}
		//양끝단 edge는 고려하지 않기때문
		blanks[blanks.length-1]	= 0;
		
		
		//최대blank(최소 brick) 계산
		int max = 0;
		for(int blank : blanks) max = Math.max(max, blank);
		
		return wall.size() - max;
	}
	
	//GithubVer. 생각하는 방식은 나랑 똑같은데 HashMap쓰길래 연습해보려고 구현.
	public static int leastBricks2(List<List<Integer>> wall) {
		
		Map<Integer, Integer> map = new HashMap<>();
		int width = 0, max =0;
		for( List<Integer> sub : wall ) {
			int p =0;
			for( int i=0; i<sub.size() -1; i++) {
				p += sub.get(i);
				Integer v= map.get(p);
				//멋들어진 코드!
				map.put(p, (v==null) ? 1 : v+1);
			}
		}
		for( Integer integer : map.values()) {
			max = Math.max(max, integer);
		}
		
		return wall.size() - max;
		
	}
	public static void main(String[] args) {
		List<List<Integer>> wall = new ArrayList<>();
		List<Integer> layer1 = new ArrayList<>();

		List<Integer> layer2 = new ArrayList<>();
		List<Integer> layer3 = new ArrayList<>();
		List<Integer> layer4 = new ArrayList<>();
		List<Integer> layer5 = new ArrayList<>();
		List<Integer> layer6 = new ArrayList<>();
		
		layer1.add(1);
		layer1.add(2);
		layer1.add(2);
		layer1.add(1);
		
		layer2.add(3);
		layer2.add(1);
		layer2.add(2);
		
		layer3.add(1);
		layer3.add(3);
		layer3.add(2);

		layer4.add(2);
		layer4.add(4);
		
		layer5.add(3);
		layer5.add(1);
		layer5.add(2);
		
		layer6.add(1);
		layer6.add(3);
		layer6.add(1);
		layer6.add(1);

		wall.add(layer1);
		wall.add(layer2);
		wall.add(layer3);
		wall.add(layer4);
		wall.add(layer5);
		wall.add(layer6);
		
		System.out.println(leastBricks(wall));
		System.out.println(leastBricks2(wall));
	}
}
