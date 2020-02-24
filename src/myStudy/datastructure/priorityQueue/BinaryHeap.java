package myStudy.datastructure.priorityQueue;

import java.util.*;

public class BinaryHeap<T extends Comparable<T>> {

	private int size;
	private int capacity;
	
	private List<T> heap;
	
	public BinaryHeap() {
		this(1);
	}
	
	public BinaryHeap(int sz) {
		heap = new ArrayList(sz);
	}

	public BinaryHeap(T[] elems) {
		size = capacity = elems.length;
		
		heap = new ArrayList<T>(size);
		
		for(int i=0; i<capacity; i++) heap.add(elems[i]);
		
		//heapify
		for(int i = capacity/2 + 1; i>=0; i--) sink(i);
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size==0;
	}
	
	public void clear() {
		heap = Collections.emptyList();
//		for(int i =0; i<capacity;i++) heap.set(i, null);
	}
	
	public T peek() {
		if(isEmpty()) return null;
		return heap.get(0);
	}
	
	public T poll() {
		return removeAt(0);
	}
	
	public boolean contains(T e) {
	
		for( int i=0; i<size; i++)
			if( heap.get(i).equals(e)) return true;
		return false;
	}
	
	public void add(T e) {
		if(e==null) throw new IllegalArgumentException();

		if(size == capacity) {
			capacity++;
			heap.add(e);
		} else heap.set(size, e);
		size++;
		swim(size-1);
	}
	
	public boolean remove(T e) {
		if(!heap.contains(e)) return false;
		
		int index = heap.indexOf(e);
		return removeAt(index)!=null;
	}
	
	public T removeAt(int k) {
		if(k<0 || k>=size) throw new IllegalArgumentException("invalid index");
		
		T removed_Data = heap.get(k);
		swap(k,size-1);
		heap.set(size-1, null);
		
		T element = heap.get(k);
		
		sink(k);
		if(element.equals(heap.get(k))) swim(k);
		
		size--;
		return removed_Data;
	}
	
	private void sink(int k) {
		if(k<0 || k>=size) throw new IllegalArgumentException("invalid index");
		int left = 2*k +1;
		int right = left+1;
		int smaller = left;
		
		if( right<size && cmp(left,right)>0 ) smaller = right;
		if( smaller<size && cmp(k,smaller) >0 ) {
			swap(k,smaller);
			sink(smaller);
		}
	}
	private void swim(int k) {
		if(k<0 || k>=size) throw new IllegalArgumentException("invalid index");
		int parent = (k-1)/2 ;
		
		if( parent>=0 && cmp(k,parent) <0) {
			swap(k,parent);
			swim(parent);
		}
	}
	
	private int cmp(int k, int j) {
		if(k<0 || k>=size || j<0 || j>=size) throw new IllegalArgumentException("invalid index k : " + k + ", j : " + j);
		T t1 = heap.get(k);
		T t2 = heap.get(j);
		
		return t1.compareTo(t2);
	}
	private void swap(int k, int j) {
		if(k<0 || k>=size || j<0 || j>=size) throw new IllegalArgumentException("invalid index k : " + k + ", j : " + j);
		T t1 = heap.get(k);
		T t2 = heap.get(j);
		heap.set(k, t2);
		heap.set(j, t1);
	}
	
	public boolean isMinHeap() {
		return isMinHeap(0);
	}
	
	private boolean isMinHeap(int k) {
		
		if(k>=size) return true;
		
		int left = 2*k +1;
		int right = left +1;
		
		if( left<size && cmp(k,left)>0) return false;
		if( right<size && cmp(k,right)>0) return false;
		
		return isMinHeap(left) && isMinHeap(right);
	
	}

	public String toString() {
		StringBuffer sb= new StringBuffer();
		
		sb.append("[ ");
		for(int i=0;i<size;i++) {
			sb.append("'"+ heap.get(i).toString() + "', ");
			if(i/10 >=0 && i%10 ==9) {
				sb.append("] \n [ ");
			}
		}
		sb.append("]");
		return sb.toString();
	}
	
	public static void main(String[] args) {
		
		int N = 1000;
		Random ran = new Random();
		Integer[] arr  = new Integer[N];
		
		for(int i=0; i<N; i++) arr[i] = ran.nextInt(N);
		
		
		BinaryHeap bh = new BinaryHeap(arr);
		
		System.out.println(bh.toString());
		
		System.out.println(bh.isMinHeap());
		
	}
	
}
