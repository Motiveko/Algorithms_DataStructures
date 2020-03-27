package myStudy.algorithms.leetcode.medium;

/*
	Given a collection of intervals, merge all overlapping intervals.
	
	Example 1:
	
	Input: [[1,3],[2,6],[8,10],[15,18]]
	Output: [[1,6],[8,10],[15,18]]
	Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
	Example 2:
	
	Input: [[1,4],[4,5]]
	Output: [[1,5]]
	Explanation: Intervals [1,4] and [4,5] are considerred overlapping.
	Tags: Array, Sort
*/

import java.util.*;

public class MergeIntervals {
	
	public static class Interval {
	     int start;
	     int end;
	     Interval() { start = 0; end = 0; }
	     Interval(int s, int e) { start = s; end = e; }
	 }
	
    public static List<Interval> merge(List<Interval> intervals) {
    	
    	if(intervals ==null || intervals.size()==1) return intervals;
    	
    	Collections.sort(intervals, new Comparator<Interval>() {
			@Override
			public int compare(Interval o1, Interval o2) {
				if( o1.start > o2.start) return 1;
				if( o1.start < o2.start) return -1;
				return 0;
			}

    	});
    	
    	List<Interval> list = new ArrayList<>();
    	list.add(intervals.get(0));
    	
    	for(int i=1; i<intervals.size(); i++) {
    		Interval first = list.get(list.size()-1);
    		Interval second = intervals.get(i);
    		if( first.end > second.start) {
    			Interval merged = new Interval(first.start, second.end);
    			list.remove(first);
    			list.add(merged);
    		} else {
    			list.add(second);
    		}
    	}
    	
    	return list;
    }
    
    public static void main(String[] args) {
		List<Interval> intervals = new ArrayList<>();
		intervals.add(new Interval(1, 3));
		intervals.add(new Interval(2, 6));
		intervals.add(new Interval(8, 10));
		intervals.add(new Interval(15, 18));
		intervals = merge(intervals);
		
		for(Interval interval : intervals) {
			System.out.println("[" + interval.start + ", " + interval.end + "]");
		}
	}
}
