package myStudy.datastructure.segmenttree;

import java.util.*;
//Fiset Version
public class Node {

	static final int INF = Integer.MAX_VALUE;
	
	Node left, right;
	int minPos, maxPos, min=0, sum=0, lazy =0;
	
	public Node(int[] values) {
		if( values == null) throw new IllegalArgumentException("Null input to sgt.");
		buildTree(0,values.length);
	
		for( int i=0; i<values.length; i++) update(i,i+1, values[i]);
	}
	
	public Node( int sz) {
		buildTree(0,sz);
	}
	
	public Node( int l, int r) {
		buildTree(l,r);
	}
	
	
	
	private void buildTree(int l, int r) {
		if( l<0 || r<0 || r<l) throw new IllegalArgumentException("Illegar range :(" + l + ", "+ r +")");
		
		
		minPos = l;
		maxPos = r;
		
		//leaf node
		if( r==l-1) {
			left = right = null;
			//Add children
		} else {
			int mid = (l+r) / 2;
			left = new Node(l, mid);
			// ================mid+1 , r 이 아니고 ?================
			right = new Node(mid, r);
		}
		
	}
	
	
	
	public void update( int l, int r, int change) {
		
		//Do lazy update
		propagate();
		
		if( l<= minPos && maxPos<= r) {
			
			sum += change*(maxPos - minPos);
			min += change;
			
			if( left!=null ) left.lazy += change;
			if( right!=null ) right.lazy += change;
			
		} else if( r<= minPos || l >= maxPos ) {
			//do nothing, range not overlap
		} else {
			//partially overlaped range
			if( left!=null ) left.update(l, r, change);
			if( right!=null ) right.update(l, r, change);
			sum = ( left!=null ? left.sum : 0) + ( right!=null ? right.sum : 0);
			min = Math.min((left!=null ? left.min : INF	), (right!=null ? right.min : INF));
		}
		
	}
	
	// get the sum in the interval[l,r)
	public int sum( int l, int r) {
		
	    propagate();
	    
	    //update와 동일
	    if( l <= minPos && maxPos<= r)  return sum;
	    
	    else if ( r<= minPos || l>= maxPos) return 0;
	    
	    else return( left == null? 0 : left.sum(l,r)) + (right == null ? 0 : right.sum(l,r));
	 
	}
	
	public int min(int l, int r) {
		
		propagate();
		
		//update와 동일
		
		//range overlaped
		if( l<= minPos && maxPos <= r) return min;
		//out of range
		else if( r<= minPos || l>= maxPos) return INF;
		// range partially overlaped
		else return Math.min((left == null ? INF : left.min(l,r)),(right == null ? INF : right.min(l,r)));
		
	}
	
	
	private void propagate() {
		//업데이트 시 조건 맞는 노드만 업뎃되고 나머지는 lazy값을 받는데 이는 sum이나 min 함수 실행 시
		//결국 propagate()를 다 하므로 값에 max/min에 반영된다.
		//왜 이런 방식을 택한지는 의문쓰..
		if( lazy!=0) {
			
			sum += lazy*(maxPos-minPos);
			min += lazy;
			if(left != null) left.lazy = lazy;
			if(right != null ) right.lazy = lazy;
			
			lazy = 0;
		}
		
	}
}



