package myStudy.datastructure.priorityQueue;

import java.util.Random;

public class MinDHeap<T extends Comparable<T>> {

	private T[] heap;
	private int d, n, sz;
	private int[] child, parent;
	
	
	@SuppressWarnings("unchecked")
	public MinDHeap(int degree, int maxNodes) {
		d = Math.max(degree, 2);
		n = Math.max(d, maxNodes);
		
		child = new int[n];
		parent = new int[n];
		
//		heap = (T[]) new Object[n]; -->> 이것은 오류난다 ㅜ ㅜ
		heap = (T[]) new Comparable[n]; 
		
		for(int i=0; i<n; i++) {
			child[i] = d*i + 1;
			parent[i] = (i-1)/d;
		}
	}
	
	
	public int size() {
		return sz;	
	}
	
	public boolean isEmpty() {
		return size()==0;
	}
	
	public T peek()	{
		if(isEmpty()) return null;
		return heap[0];
	}
	
	public T poll() {
		if(isEmpty()) return null;
		T root = heap[0];
		heap[0] = heap[--sz];
		heap[sz] = null;
		sink(0);
		return root;
	}
	
	public void add(T e) {
		
		//resize heap
		if( sz == heap.length) {
			T[] tmp_heap = heap.clone();
			heap = (T[]) new Object[2*n];
			for(int i = 0; i<n; i++) {
				heap[i] = tmp_heap[i];
				tmp_heap =null;
			}
			tmp_heap =null;
			n = 2*n;
		}
		
		heap[sz++] = e;
		swim(sz-1);
	}
	
	public boolean remove(T e) {
		
		int index = 0;
		for(int i=0; i<sz; i++) {
			if(heap[i].equals(e)) index = i;
		}
		
		return removeAt(index)!=null;
		
	}
	
	public T removeAt(int k) {
		
		T removed_Data = heap[k];
		
		swap(k,sz-1);
		T element = heap[k];
		sink(k);
		if( element.equals(heap[k])) swim(k);
		sz--;
		return removed_Data;
	}
	
	public void sink(int k) {
		
		int c = minChild(k); 
		if(c==-1) return;
		
		if(cmp(k,c)>0) {
			swap(k,c);
			sink(c);
		}
	}
	
	public void swim(int k) {
		if(k<=0) return;
		int p = parent[k];
		if(p>=0 && cmp(p,k)>0) {
			swap(p,k);
			swim(p);
		}
	}
	
	public int cmp(int k, int j) {
		
		T t1 = heap[k];
		T t2 = heap[j];
		
		return t1.compareTo(t2);
	}
	
	public void swap(int k, int j) {
		T tmp = heap[k];
		heap[k] = heap[j];
		heap[j] = tmp;
	}
	
	public int minChild(int k) {
		int from = child[k];
		if(from>=sz) return -1;
		
		int to = Math.min(sz, from+d);
		int min = from;
		for(int i=from+1; i<to; i++) {
			if(cmp(min,from) >0	) {
				min = from;
			}
		}
		return min;
	}
	
	public boolean isMinHeap() {
		return isMinHeap(0);
	}
	
	private boolean isMinHeap(int k) {
		
		if(k>= sz) return true;
		
		int c= child[k];
		for(int i=c ; i<c+d; i++) {
			if(c<sz && cmp(k,c)>0) return false;
		}
		
		for(int i=c; i<c+d; i++) if(!isMinHeap(c)) return false;
		
		return true;
	}
	
	public static void main(String[] args) {
		
		
		int N = 1000;
		int d = 4;
		Random ran = new Random();

		MinDHeap dHeap = new MinDHeap(d, N);
		for(int i= 0; i<N; i++	) {
			dHeap.add(ran.nextInt(N));
		}
		
		System.out.println(dHeap.isMinHeap());
		
		dHeap.removeAt(5);
		dHeap.removeAt(5);
		dHeap.removeAt(5);
		dHeap.removeAt(5);
		dHeap.removeAt(5);
		dHeap.removeAt(5);
		dHeap.removeAt(5);
		dHeap.removeAt(5);
		System.out.println(dHeap.isMinHeap());

		
	}
}
