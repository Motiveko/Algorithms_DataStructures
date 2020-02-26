package myStudy.datastructure.priorityQueue;

import java.util.Random;

public class MinIndexedDHeap< T extends Comparable<T>> {

	private int sz;
	private final int N;
	private final int D;
	
	private final int[] child, parent;
	
	//pm[key] = position in heap
	public final int[] pm;
	
	//im[positin in heap] = key   -> im[pm[key]] = key // pm[im[pos]] = pos
	public final int[] im;
	
	
	//values[key] = value :: key-value pair 고대로 저장한다
	public final Object[] values;
	
	
	
	public MinIndexedDHeap(int degree, int maxSize) {
		
		this.D = Math.max(degree, 2);
		this.N = Math.max(D+1, maxSize);
		
		sz = 0;
		values = new Object[N];
		pm = new int[N];
		im = new int[N];
		child = new int[N];
		parent = new int[N];
		
		for(int i=0; i<N ; i++) {
			child[i] = D * i + 1;
			parent[i] = (i-1) / D;
			pm[i] = im[i] = -1;
		}
	}
	
	
	public int size() {
		return sz;
	}
	
	public boolean isEmpty() {
		return sz==0;
	}
	
	public boolean contains(int k) {
		//throw exception
		
		return pm[k]!=-1;
	}
	
	//최소 value의 key 
	public int peekMinKeyIndex() {
		//throw exception
		
		return im[0];
	}
	
	public T peekMinValue() {
		//throw exception
		return (T) values[im[0]];
	}
	
	public int pollMinKeyIndex() {
		int key = peekMinKeyIndex();
		delete(key);
		return key;
	}
	
	public void insert(int key, T value) {
		if(contains(key)) throw new IllegalArgumentException("index already exists, received : "  + key);
		
		//Impelentation 
		pm[key] = sz;
		im[sz] = key;
		values[key] = value;
		swim(sz++);
	}
	
	public T valueOf(int key) {
		//keyExistsOrThrow(key)
		return (T) values[key];
	}
	public T delete(int key) {
		//throw exception
		
		final int i = pm[key];
		swap(i,--sz);
		sink(i);
		swim(i);
		
		T value = (T) values[key];
		values[key] = null;
		pm[key] = -1;
		im[sz] = -1;
		
		return value;
	}
	
	public T update(int key, T value) {
		//keyExistsAndValueNotNullOrThrow(key, value)
		final int i = pm[key];
		T oldVal = (T) values[key];
		
		values[key] = value;
		sink(i);
		swim(i);
		return oldVal;
	}
	
	public void decrease(int key, T value) {
		//key에 value값을 value로 줄임..? 업데이트랑 겹치는 기능
		
		System.out.println("decreas 안해");
	}
	public void increase(int key, T value) {
		//decrease와 반대
		System.out.println("increas 안해");
	}
	
	private void sink(int i) {
		if(minChild(i)==-1 ) return;
		
		if(less(i,minChild(i))<0) {
			swap(i,minChild(i));
			sink(minChild(i));
		}
	}
	
	private void swim(int i) {
		while(less(i,parent[i]) <0) {
			swap(i,parent[i]);
			i= parent[i];
		}
	}
	
	private void swap(int i, int j) {
		pm[im[i]] = j;
		pm[im[j]] = i;
		
		int tmp = im[i];
		im[i] = im[j];
		im[j] = tmp;
	}
	
	private int minChild(int i) {
		
		int from = child[i];
		int to = Math.min(sz, from+D);
		//no child
		if(to<from) return -1;
		int minchild = from;
		for(int c = from; c<to; c++) {
			if(less(c,minchild)<0) {
				minchild = c;
			}
		}
		
		return minchild;
	}
	
	private int less(int i, int j) {
		
		T t1 = (T) values[im[i]];
		T t2 = (T) values[im[j]];
		
		return t1.compareTo(t2);
	}
	
	public boolean isMinHeap() {
		return isMinHeap(0);
	}
	
	private boolean isMinHeap(int i) {
		
		//세련되지 못한 나의 단순코드
//		if(i >=sz) return true;
//		
//		for(int j=i; j<sz; j++) {
			
//			if(minChild(j)!=-1 && less(j,minChild(j))>0) return false;
//		}
//		
//		return true;
		
		//세련된 엔지니어의  Recursive코드..
		int from = child[i];
		int to = Math.min(sz, from+D);
		
		for(int j=from;j<from;j++) {
			if(less(i,j)>0) return false;
			if(!isMinHeap(j)) return false;
		}
		
		return true;
	}
	
	public static void main(String[] args) {
		// Check Class가 따로 구현되어 있으나 졸라복잡하므로 소소하게 자체검수
		int n = 30;
		int d =2;
		Integer[]pairs = new Integer[n];
		Random ran = new Random();
		
		for(int i=0; i <n; i ++) {
			pairs[i] = ran.nextInt(20);
		}
		MinIndexedDHeap pq = new MinIndexedDHeap(d, n);
		
		System.out.println(pq.size());
		System.out.println(pq.isEmpty());
		
		for(int i=0; i<n; i++) pq.insert(i, pairs[i]);
		
		System.out.println(pq.isMinHeap());
		System.out.println(pq.size());
		System.out.println(pq.isEmpty());
		
	}
	
}
