package myStudy.algorithms.leetcode.medium;


//https://leetcode.com/problems/count-number-of-teams/
//https://github.com/fishercoder1534/Leetcode/blob/master/src/main/java/com/fishercoder/solutions/_1395.java
public class CountNumbersOfTeam {
		
	public int solve(int[] rating) {
		
		
		int n = rating.length;
		int count = 0;
		
		
		for(int i=0 ; i<n-2; i++) {
			for( int j=i+1; j<n-1; j++) {
				for(int k=j+1; k<n; k++	) {
					if(rating[i] > rating[j] && rating[j] > rating[k]) count++;
					else if( rating[k] > rating[j] && rating[j] > rating[i]) count++;
				}
			}
		}
		
		return count;	
		
	}
	
	public static void main(String[] args) {
		
		CountNumbersOfTeam res = new CountNumbersOfTeam();
		
		
		
		res.solve(new int[]{2,5,3,4,1});
		
		
		System.out.println(res.solve(new int[]{2,5,3,4,1}));
		System.out.println(res.solve(new int[] {2,1,3}));
		System.out.println(res.solve(new int[] {1,2,3,4}));
	}
	
	
}
