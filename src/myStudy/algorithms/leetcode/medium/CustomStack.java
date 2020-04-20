package myStudy.algorithms.leetcode.medium;

import java.util.*;

// https://leetcode.com/problems/design-a-stack-with-increment-operation/
// https://github.com/fishercoder1534/Leetcode/blob/master/src/main/java/com/fishercoder/solutions/_1381.java
public class CustomStack {

	int maxSize;
	List<Integer> stack;
	
    public CustomStack(int maxSize) {
    	this.maxSize = maxSize;
    	stack = new ArrayList<>();
    }
    
    public void push(int x) {
        if( stack.size() == maxSize	) return;
        
        stack.add(x);
    }
    
    public int pop() {
        if(stack.size()==0) return -1;
    	return stack.remove(stack.size()-1);
    }
    
    public void increment(int k, int val) {
        for( int i=0; i<k && i<stack.size() ; i++) {
        	int oldVal = stack.remove(i);
        	stack.add(i,oldVal + val);
        }
    }
    
}
