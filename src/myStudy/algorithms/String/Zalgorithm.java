package myStudy.algorithms.String;

/*	
 * Pattern Search Algorithm :: Naive - Z-algorithm - KMP - Rabin-Karp Algorithm 순서대로 하면 됨.
 * Z도 개념은 쉬우나 코드가 이해하기 매우어려움
 * https://www.geeksforgeeks.org/z-algorithm-linear-time-pattern-searching-algorithm/?ref=rp
*/
  
	
import java.util.*;
public class Zalgorithm {

	public static List<Integer> search(String text, String pattern) {
		
		String concat = pattern + "$" + text;
		int l = concat.length();
		//Z는 어떤 index i부터의 substring이 concat의 prefix와 얼마나 일치하는지를 나타내준다
		//prefix는 $가 unique한 문자이므로 pattern이 최대길이가 되고 따라서 z값이 pattern의 length가 되는 지점이 search된 지점이 된다.
		int[] Z = new int[l];
		
		getZarr(concat,Z);
		List<Integer> res = new ArrayList<>();
		for(int i=0; i<l; i++)
			if(Z[i]==pattern.length()) res.add(i -(pattern.length()+1)); 
		return res;
	}
	public static void getZarr(String str, int[] Z) {
		
		int n= str.length();
		int L, R, k;
		
		L=R=0;
		
		for( int i=1; i<n; i++) {
			
			if( i > R) {
				
				L = R = i;
				while( n > R && str.charAt(R-L) == str.charAt(R)) R++;
				Z[i] = R - L;
				R--;
			} else {
				k = i-L;
				//필요없는것
//				if(Z[k] < R-i+1	) Z[i] = Z[k];
				
				//Pattern P내에 k번째 substring이 Z[k]만큼 Prefix와 일치하므로 ( concat의 i번째 문자) substring 이후(현재 R이후)
				//부터 다시 일치여부를 검사해야한다.
				if(Z[k]==R-i+1) {
					L= i;
					while(n>R && str.charAt(R-L) == str.charAt(R)) R++;
					Z[i] = R-L;
					R--;
				}

			}
		}
	}
	
	public static void main(String[] args) {
		
		List<Integer> res = search("gegegeksabgegek","gegek");
		System.out.println(res.toString());
	}
}
