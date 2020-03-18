package myStudy.algorithms.leetcode.easy;

import java.util.function.DoubleFunction;

public class Sqrt {

	public static double sqrt(int n) {
		
		final double EPS = 0.000000001;

		double left = 0, right = n, mid = (left+right)/2;
		
		DoubleFunction<Double> pow = (x) -> x*x; 

		//Using Binary Search
		while(Math.abs(pow.apply(mid) -n) > EPS) {
			if(pow.apply(mid) == n) return (int)mid;
			if( pow.apply(mid) > n) {
				right = mid;
				mid = (left + right)/2;
			} else {
				left = mid;
				mid = (left + right)/2;
			}
		}

		return mid;
	}
	
	public static void main(String[] args) {
		
		System.out.println(sqrt(8));
		System.out.println(sqrt(10));
	}
}
