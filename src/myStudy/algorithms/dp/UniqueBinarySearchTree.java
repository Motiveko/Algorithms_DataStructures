package myStudy.algorithms.dp;

/*
	https://github.com/awangdev/LintCode/blob/master/Java/Unique%20Binary%20Search%20Tree.java
	
	Given n, how many structurally unique BST's (binary search trees) that store values 1...n?
	Example
	Given n = 3, there are a total of 5 unique BST's.
	   1         3     3      2      1
	    \       /     /      / \      \
	     3     2     1      1   3      2
	    /     /       \                 \
	   2     1         2                 3
	Tags Expand 
	Catalan Number Dynamic Programming
	Thinking proces:
	Knowing what is Catalan number. 
	C(n+1) = SUM(C(i)*C(n-i))
	OR: C(n) = SUM(C(i)*C(n-i-1)).
	
	Catalan number는 이미 정리된 알고리즘이다.
	참고  :  https://blog.naver.com/pyw0564/221523147108
		-> 1. 괄호문제
			   : C0 = 1(기본)
			() : C1 = 1
			()() : C2 = 2
			()()() : C3==> 
						기본괄호 하나 안에 .. 2개 밖에 .. 0개
									   1개 		1개
									   0개		2개 -> C2*C1 + C1*C1 + C0*C2 = C3 = 5 
			... 이런식으로 전개된다
			
			2. 대각선 이동문제
				오른쪽으로 이동 : R, 위로이동  :U 라고하면
				맨앞은 R, 맨뒤는 U 가 되야하는 문제다. 층이 커질수록 R,U 한쌍씩 생기므로 괄호문제와 똑같이 생각할 수 있다.
				
			3. Unique Bst 문제
				Binary Search tree도 1,2,3 개까지는 1,2,5
				4부터는 왼3 오0 , 왼2 오1, 왼1 오2, 왼0 오3 ... 이런식으로 생각해서 풀면 위와 같은 방식으로 계산해 낼 수 있다.
	
*/


public class UniqueBinarySearchTree {

	public static int solve1(int n) {
		
		int[] cNum = new int[n+1];
		cNum[0]= 1;
		cNum[1]= 1;
		
		for( int i=2; i<n+1; i++) {
			for( int j=0; j<i; j++) {
				cNum[i] += cNum[i-j-1]*cNum[j];
			}
		}
		
		return cNum[n];
	}

	public static void main(String[] args) {
		System.out.println(solve1(10));
	}
}
