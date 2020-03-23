package myStudy.algorithms.leetcode.medium;

/*
	Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai).
	n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0).
	Find two lines, which together with x-axis forms a container, such that the container contains the most water.
	Note: You may not slant the container and n is at least 2.
*/

import java.util.*;

public class ContainerWithMostWater {

	public static int maxVolume(int[] height) {

		int n = height.length;
		int l= 0, r = height.length -1;
		int max=0, h=0;
		
		while( l < r) {
			
			h = Math.min(height[l], height[r]);
			max = Math.max(max, (r-l) * h);
			
			while( height[l] <= h && l <= height.length-1) l++;
			while( height[r] <= h && r >= 0 ) r--;
		}
		return max;
	}
	
	
	public static void main(String[] args) {
		int[] height = { 100,4,5,9,7,2,1,9000,1737,6,8,9,7,99};
		
		System.out.println(maxVolume(height));
	}
}
