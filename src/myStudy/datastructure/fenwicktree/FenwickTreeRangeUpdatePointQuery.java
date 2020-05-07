package myStudy.datastructure.fenwicktree;

import java.util.Arrays;

public class FenwickTreeRangeUpdatePointQuery {

	final int N;
	
	private long[] tree;
	
	public FenwickTreeRangeUpdatePointQuery(int sz) {
		tree = new long[N=sz+1];
	}
	
	//construct fenwicktree
	//values[0] = 0 이라고 가정한다!
	public FenwickTreeRangeUpdatePointQuery(long[] values) {
		
		if(values == null) throw new IllegalArgumentException("Invalid input");
		
		N = values.length;
		tree = new long[N];
		
		tree = values.clone();
		
		for( int i=1; i<N; i++) {
			if( i + lsb(i) >= N) continue;
			tree[i + lsb(i)] += tree[i + lsb(i)] + tree[i];
		}
	}
	
	/*
	 * -i는 i의 보수로서 가장 오른족 비트는 1로 동일하고 그 오른쪽은 모두 0으로 i와 같다
	 * 가장 오른쪽 비트의 왼쪽부분은 전부다 i의 비트의 보수로서 서로 더했을 때 0이 나오게 한다!! 보수에대해 알아야만 이해할 수 있다!
	 */
	private static int lsb(int i) {
		//Integer.lowestOneBit(i);
		return i & -i;
	}
	
	// prefix Sum for [1,i]
	private long prefixSum(int i) {
		
		long sum = 0L;
		
		while( i >= 1) {
			sum += tree[i];
			
			i -= lsb(i);
//			i &= ~lsb(i);
		}
		return sum;
	}
	
	
	// sum of interval [left,right], O(log(n))
	public long sum( int left, int right) {
		if( right < left) throw new IllegalArgumentException("left should not be bigger than right");
		return prefixSum(right) - prefixSum(left-1);
	}
	
	//arr[i]의 값
	public long get(int i) {
		return prefixSum(i) - prefixSum(i-1);
	}
	
	
	// index i에 v를 더해준다. O(log(n))
	public void add(int i, long v) {
		if( i > N) throw new IndexOutOfBoundsException();
		while( i < N) {
			tree[i] += v;
			i += lsb(i);
		}
		
	}
	
	public void set( int i, long v) {
		if( i > N) throw new IndexOutOfBoundsException();
		add(i, v-get(i));
		
	}
	
	public String toString() {
		return Arrays.toString(tree);
	}
	
}
