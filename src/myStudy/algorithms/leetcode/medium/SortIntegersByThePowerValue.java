package myStudy.algorithms.leetcode.medium;
import java.util.*;

// https://leetcode.com/problems/sort-integers-by-the-power-value/
// https://github.com/fishercoder1534/Leetcode/blob/master/src/main/java/com/fishercoder/solutions/_1387.java
public class SortIntegersByThePowerValue {
	//myVer
	public static int getKth2(int lo, int hi, int k) {
		
		int[] power = new int[hi-lo+1];
		int[] vals = new int[hi-lo+1];
		//initialize
		for(int i=0; i < vals.length; i++) vals[i] = lo+i;
		
		for( int i=0; i < vals.length; i++) {
			int val = vals[i];
			while( val !=1	) {
				if( val % 2 == 0) val /= 2;
				else val = val*3 +1;
				power[i]++;
			}
		}
		
		//sorting algorithm (power을 sort할 때 vals 는 보너스로 하도록) 
		//bubble sorting 이용
		for( int i=0; i<power.length-1; i++) {
			for( int j=power.length-1 ; j>i; j-- ) {
				if(power[j] < power[j-1]) {
					int tmp= power[j];
					power[j] = power[j-1];
					power[j-1] = tmp;
					tmp = vals[j];
					vals[j] = vals[j-1];
					vals[j-1] = tmp;
				}
			}
		}
		
		return vals[k-1];
	}
	
	
	//git hub ver, 내가 안쓰던 방식의 코드가 많이들어가있다!
	public static int getKth(int lo, int hi, int k) {
		
		//int[]도 클래스처럼 쓸수있다
		List<int[]> power = new ArrayList<>();
		
		for( int i=lo ; i<=hi; i++) {
			power.add(new int[]{getSteps(i),i});
		}
		
		//오지는 정렬방법
		Collections.sort(power, (a,b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);
		return power.get(k-1)[1];
	}
	
	private static int getSteps(int number) {
		int steps=0;
		
		while( number!=1) {
			if(number % 2 == 0) {
				number /= 2;
			} else {
				number = 3 * number + 1;
			}
			steps++;
		}
		return steps;
	}
	
	
	public static void main(String[] args) {
		System.out.println(getKth(12,225,44));
	}
}
