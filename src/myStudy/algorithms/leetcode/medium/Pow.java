package myStudy.algorithms.leetcode.medium;

/*
	mplement pow(x, n), which calculates x raised to the power n (xn).
	
	Example 1:
	
	Input: 2.00000, 10
	Output: 1024.00000
	Example 2:
	
	Input: 2.10000, 3
	Output: 9.26100
	Example 3:
	
	Input: 2.00000, -2
	Output: 0.25000
	Explanation: 2^-2 = 1/2^2 = 1/4 = 0.25
	Note:
	
	-100.0 < x < 100.0
	n is a 32-bit signed integer, within the range [−231, 231 − 1]
*/	
		
public class Pow {

	public static double pow (double x, int n	) {
		
		if( n<0) return helper(1/x,-n);

		else return helper(x,n);

	}
	
	private static double helper(double x , int n) {
		
		if(n==0) return 1;
		if(n==1) return x;
		
		double d = helper( x, n>>1);
		if(n%2==0) return d*d;
		
		return d*d*x;
		
	}
	
	public static void main(String[] args) {
		System.out.println(pow(2,1));
	}
}
