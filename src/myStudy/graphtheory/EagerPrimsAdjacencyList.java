package myStudy.graphtheory;

/*
 * Minimum Spanning Tree(MST, 최소신장트리) 는 Undirected Weighted Graph에서 Vertex N개를
 * 최소한의 비용으로 N-1개의 Edge를 사용해서 잇는 것을 말한다
 * 
 * (Indexed) Min Priority Queue 를 이용, 이것의 최소값이 맨 먼저 빠져나온다는 것을 이용한다.
 * 
 * Undirected 그래프의 Edge를 양방향의 Directed Edge로 나눠서 생각한다고 했을 때, 
 * Starting Vertex를 제외한 모든 Vertex는 Incoming Edge와 V-E Pair를 이룬다. 
 * 
 *  Eager Version 은 IPQ를 이용, 값을 업데이트 할 때 O(1)의 complexity가 발생한다! 쬐금더빨르
 */
import java.util.*;

//겨우겨우 돌아가게 만들었다 .. Dheap에서 짜잘한 실수를 너무많이했다
//fiset코드는 한번 보
public class EagerPrimsAdjacencyList {
	
	public  static class Edge implements Comparable<Edge>{
		int from,to,cost;
		public Edge(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
		@Override
		public int compareTo(Edge other) {
			return cost - other.cost;
		}
	}
	
	private final int n;
	private boolean solved;
	private boolean mstExists;
	
	private boolean[] visited;
	
	private MinIndexedDHeap<Edge> pq;
	private List<List<Edge>> graph;
	
	private long minCostSum;
	private Edge[] mstEdges;
	
	public EagerPrimsAdjacencyList(List<List<Edge>> graph) {
		if(graph ==null ) throw new IllegalArgumentException("graph null!");
		
		n = graph.size();
		this.graph = graph;
	}
	
	public Edge[] getMst() {
		solve();
		return mstExists ? mstEdges : null;
	}
	
	public Long getMstCost() {
		solve();
		return mstExists ? minCostSum : null;
	}
	
	private void solve() {
		
		visited = new boolean[n];
		minCostSum = 0;
		mstEdges = new Edge[n-1];
		int edgeCount = 0;
		//가지 4개짜리 pq!
		pq = new MinIndexedDHeap(4, 5*n);
		
		//혹시몰라 스타트 노드 설정
		int start = 0;
		for(int i=0; i<n; i++) {
			if(!graph.get(i).isEmpty()) {
				start = i;
				break;
			}
		}
		
		List<Edge> edges = graph.get(start);
		for(Edge edge : edges) {
			pq.insert(edge.to, edge);
		}
		visited[start] = true;
		
		while(!pq.isEmpty() && edgeCount!=n-1) {
			
			Edge edge = pq.poll();
			if(visited[edge.to]) continue;
			
			visited[edge.to] = true;
			minCostSum+= edge.cost;
			mstEdges[edgeCount++] = edge;
			
			List<Edge> nextEdges = graph.get(edge.to);
			if(!nextEdges.isEmpty()) 
				for(Edge nextEdge : nextEdges) 
					if(!visited[nextEdge.to]) pq.insert(nextEdge.to, nextEdge);
			
		//	System.out.println(pq.isMinHeap());
		}
		
		mstExists = true;
		solved = true;
		
	}
	
	public static List<List<Edge>> createEmptyGraph(int n ){
		List<List<Edge>> graph = new ArrayList<>();
		for(int i=0; i<n ; i++) graph.add(i, new ArrayList<>());
		return graph;
	}
	
	public static void addUndirectedEdge(List<List<Edge>> graph, int from, int to, int cost) {
		graph.get(from).add(new Edge(from,to,cost));
		graph.get(to).add(new Edge(to,from,cost));
	}
	public static void main(String[] args) {
	    int n = 10;
	    List<List<Edge>> g = createEmptyGraph(n);

	    addUndirectedEdge(g, 0, 1, 5);
	    addUndirectedEdge(g, 1, 2, 4);
	    addUndirectedEdge(g, 2, 9, 2);
	    addUndirectedEdge(g, 0, 4, 1);
	    addUndirectedEdge(g, 0, 3, 4);
	    addUndirectedEdge(g, 1, 3, 2);
	    addUndirectedEdge(g, 2, 7, 4);
	    addUndirectedEdge(g, 2, 8, 1);
	    addUndirectedEdge(g, 9, 8, 0);
	    addUndirectedEdge(g, 4, 5, 1);
	    addUndirectedEdge(g, 5, 6, 7);
	    addUndirectedEdge(g, 6, 8, 4);
	    addUndirectedEdge(g, 4, 3, 2);
	    addUndirectedEdge(g, 5, 3, 5);
	    addUndirectedEdge(g, 3, 6, 11);
	    addUndirectedEdge(g, 6, 7, 1);
	    addUndirectedEdge(g, 3, 7, 2);
	    addUndirectedEdge(g, 7, 8, 6);

	    EagerPrimsAdjacencyList solver = new EagerPrimsAdjacencyList(g);
	    Long cost = solver.getMstCost();

	    if (cost == null) {
	      System.out.println("No MST does not exists");
	    } else {
	      System.out.println("MST cost: " + cost);
	      for (Edge e : solver.getMst()) {
	        System.out.println(String.format("from: %d, to: %d, cost: %d", e.from, e.to, e.cost));
	      }
	    }
	    // Output:
	    // MST cost: 14
	    // from: 0, to: 4, cost: 1
	    // from: 4, to: 5, cost: 1
	    // from: 4, to: 3, cost: 2
	    // from: 3, to: 1, cost: 2
	    // from: 3, to: 7, cost: 2
	    // from: 7, to: 6, cost: 1
	    // from: 6, to: 8, cost: 4
	    // from: 8, to: 9, cost: 0
	    // from: 8, to: 2, cost: 1
	}

	
}

class MinIndexedDHeap<T extends Comparable<T>>{
	
	private int sz;
	private final int N;
	private final int D;
	
	private int[] child, parent;
	
	private final int[] pm;
	private final int[] im;
	
	public final Object[] values;
	
	public MinIndexedDHeap(int degree, int maxSize) {
		if(maxSize<=0) throw new IllegalArgumentException("maxSize can't be less than 0");
		
		D = Math.max(2, degree);
		N = Math.max(D, maxSize);
		
		im = new int[N];
		pm = new int[N];
		
		child = new int[N];
		parent = new int[N];
		
		values =  new Object[N];
		
		for(int i=0; i<N; i++) {
			child[i] = D*i + 1;
			parent[i] = (i-1)/D;
			pm[i] = im[i] =-1;
		}
	}
	
	public int size() {
		return sz;
	}
	public boolean isEmpty() {
		return sz==0;
	}
	public boolean contains(int key) {
		return pm[key]!=-1;
	}
	public int peekKeyIndex() {
		return im[0];
	}
	// 잡기능 많은데 구현하지 않겠음.	
	
	public T poll() {
		T val = (T) values[im[0]];
		removeAt(0);
		return val;
	}
	public void insert(int key, T value) {
		if(contains(key)) {
			if(value.compareTo((T) values[key]) < 0 ) update(key, value);
			return;
		}
		pm[key] = sz;
		im[sz] = key;
		values[key]=value;
		swim(sz++);
	}
	
	public T update(int key, T value) {
		if(!contains(key)) return null;
		T oldVal = (T) values[key];
		int i = pm[key];
		values[key] = value;
		swim(i);
		sink(i);
		return oldVal;
	}
	public void remove(T value) {
		int key=0;
		for(int i=0; i < N; i++) {
			if(value.equals(values[i])) {
				key = i;
				break;
			}
		}
		removeAt(im[key]);
	}
	
	public T removeAt(int i) {
		
		if(i <0 || i>=sz) throw new IllegalArgumentException("invalid position value for remove");
		T val = (T) values[im[i]];
		
		swap(i, sz-1);
		values[im[sz-1]] = null;
		pm[im[sz-1]] = -1;
		im[sz-1]= -1;
		
		sz--;
		if(sz==0) return val;
		
		sink(i);
		swim(i);
		return val;
	}
	
	
	private void swim(int i) {
		if(i<0 || i>=sz) throw new IllegalArgumentException("Heap position value is invalid");
		
		if(parent[i] >=0 && less(i,parent[i])) {
			swap(i,parent[i]);
			swim(parent[i]);
		}
	}

	private void sink(int i) {
		if(i<0 || i>=sz) throw new IllegalArgumentException("invalid position value for sink");
		int minChild = minChild(i);
		if(minChild == -1) return;
		
		if(minChild<sz && !less(i,minChild)) {
			swap(i,minChild);
			sink(minChild);
		}
	}
	
	private boolean less(int i, int j) {
//		T t1 = (T) values[im[i]];
//		T t2 = (T) values[im[j]];
//		return  t2.compareTo(t1) > 0;
	    return ((Comparable<? super T>) values[im[i]]).compareTo((T) values[im[j]]) < 0;
	}
	private void swap(int i, int j) {
		int tmp = im[i];
		pm[im[i]] = j;
		im[i] = im[j];
		pm[im[j]] = i;
		im[j] = tmp;
	}
	
	private int minChild(int i) {
		
		int from = child[i];
		if(from>=sz) return -1;
		int to = Math.min(from+D, sz-1);
		int minChild = from;
		for(int k=from ; k<=to; k++){
			if( less(k, minChild)) minChild = k;
		}
		return minChild;
	}
	
	public boolean isMinHeap() {
		return isMinHeap(0);
	}
	private boolean isMinHeap(int i) {
		
		int from = child[i];
		int to = Math.min(from+D, sz-1);
		
		for(int j = from; j<=to; j++) {
			if(!less(i,j)) return false;
			if(!isMinHeap(j)) return false;
		}
		return true;
	}
}

