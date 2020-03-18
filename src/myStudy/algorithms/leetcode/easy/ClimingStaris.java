package myStudy.algorithms.leetcode.easy;

/*
	You are climbing a stair case. It takes n steps to reach to the top.
	
	Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
	
	Note: Given n will be a positive integer.
*/
public class ClimingStaris {

	//Using DP..
	public static int climbStairs(int n) {
		int count = 0;
		return climbStairs(n,1,count) + climbStairs(n,2,count);
	}
	
	private static int climbStairs(int n ,int at, int count) {
		
		if( at > n) return 0;
		if(at == n) return 1;
		
		return climbStairs(n,at+1,count) + climbStairs(n,at+2,count);
	}
	
	//Git Hub Code , 사실은 문제는 피보나치수열과 같은것이었다 .어떤원리인지는 파악이 되질 않는다..
    public static int climbStairs1(int n) {
        int a = 1, b = 1;
        while (--n > 0) {
            b += a;
            a = b - a;
        }
        return b;
    }
	
	public static void main(String[] args) {
		
//		System.out.println(climbStairs(20));
		
		System.out.println(climbStairs1(40));
	}
	
}
