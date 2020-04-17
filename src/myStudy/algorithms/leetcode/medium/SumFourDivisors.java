package myStudy.algorithms.leetcode.medium;


//https://leetcode.com/problems/four-divisors/
//https://github.com/fishercoder1534/Leetcode/blob/master/src/main/java/com/fishercoder/solutions/_1390.java

import java.util.*;

public class SumFourDivisors {

	
	
	public int solution ( int[] arr) {
		
		int sum = 0;
		int count=0;
		List<Integer> list;
		for(int i=0; i<arr.length ; i++) {
			
			list = new ArrayList<>();
			int val = arr[i];
			list.add(1);
			list.add(val);
			
			for( int j=2; j<val; j++) {
				if(list.contains(j)) break;
				if(val%j ==0) list.add(val);
				list.add(val/j);
			}
			
			if(list.size()==4) for(int divisor : list) sum+= val;
		}
		
		return sum;
	}
	
}
