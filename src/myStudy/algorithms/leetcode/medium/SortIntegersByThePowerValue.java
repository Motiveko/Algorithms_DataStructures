package myStudy.algorithms.leetcode.medium;


// https://leetcode.com/problems/sort-integers-by-the-power-value/
// https://github.com/fishercoder1534/Leetcode/blob/master/src/main/java/com/fishercoder/solutions/_1387.java
public class SortIntegersByThePowerValue {

	public static int getKth(int lo, int hi, int k) {
		
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
	
	public static void main(String[] args) {
		System.out.println(getKth(12,225,44));
	}
}
