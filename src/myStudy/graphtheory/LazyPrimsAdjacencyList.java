package myStudy.graphtheory;

import java.util.*;


/*
 * Minimum Spanning Tree(MST, 최소신장트리) 는 Graph에서 Vertex N개를
 * 최소한의 비용으로 N-1개의 Edge를 사용해서 잇는 것을 말한다
 * 
 * (Indexed) Min Priority Queue 를 이용, 이것의 최소값이 맨 먼저 빠져나온다는 것을 이용한다.
 * Undirected Weighted Graph!
 * 
 * Undirected 그래프의 Edge를 양방향의 Directed Edge로 나눠서 생각한다고 했을 때, 
 * Starting Vertex를 제외한 모든 Vertex는 Incoming Edge와 V-E Pair를 이룬다. 
 *  
 * Lazy는 PQ를 이용, 값을 업데이트 해 주는데 O(logN) 의 complexity가 든다.
 */
public class LazyPrimsAdjacencyList {

	static class Edge implements Comparable<Edge>{
		int from, to, cost;

		public Edge(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
		@Override
		public int compareTo(Edge other) {
			return this.cost - other.cost;
		}
	}
	
	//Input
	private final int n;
	private final List<List<Edge>> graph;
	
	//Internal
	private boolean solved;
	private boolean mstExists;
	private boolean[] visited;
	private PriorityQueue<Edge> pq;
	
	//OutPut
	private long minCostSum;
	private Edge[] mstEdges;
	
	public LazyPrimsAdjacencyList(List<List<Edge>> graph) {
		if(graph==null || graph.isEmpty()) throw new IllegalArgumentException("Graph Cannot BeEmpty or Null");
		this.n = graph.size();
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
		
		if(solved) return;
		
		//expected number of Edges, V-1
		int m = n-1;
		minCostSum =0;
		mstEdges = new Edge[m];
		pq = new PriorityQueue<>();
		visited = new boolean[n];
		int edgeCount = 0;
		
		relaxEdge(0);
		
		while( !pq.isEmpty() && edgeCount!=m) {
			
			Edge edge = pq.poll();
			if(!visited[edge.to]) {
				mstEdges[edgeCount++] = edge;
				minCostSum += edge.cost;
				relaxEdge(edge.to);
			}
		}
		
		
		
		mstExists = (edgeCount == m);
		solved =true;
	}
	
	private void relaxEdge(int at) {
		
		visited[at] = true;
		
		List<Edge> edges = graph.get(at);
		if(edges!=null) 
			for(Edge edge : edges) 
				if(!visited[edge.to]) pq.offer(edge);
	}
	
	public static List<List<Edge>> createEmptyGraph(int n){
		List<List<Edge>> graph = new ArrayList<>();
		for(int i=0; i<n; i++) graph.add(i,new ArrayList<>());
		return graph;
	}
	
	public static void addDirectedEdge(List<List<Edge>> graph, int from, int to, int cost) {
		graph.get(from).add(new Edge(from,to,cost));
	}
	public static void addUndirectedEdge(List<List<Edge>> graph, int from, int to, int cost) {
		graph.get(to).add(new Edge(to,from,cost));
		graph.get(from).add(new Edge(from,to,cost));
	}
	
	 public static void main(String[] args) {
		    // example1();
		    // firstGraphFromSlides();
		    // squareGraphFromSlides();
		    lazyPrimsDemoFromSlides();
		  }

		  private static void example1() {
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

		    LazyPrimsAdjacencyList solver = new LazyPrimsAdjacencyList(g);
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

		  private static void firstGraphFromSlides() {
		    int n = 7;
		    List<List<Edge>> g = createEmptyGraph(n);

		    addUndirectedEdge(g, 0, 1, 9);
		    addUndirectedEdge(g, 0, 2, 0);
		    addUndirectedEdge(g, 0, 3, 5);
		    addUndirectedEdge(g, 0, 5, 7);
		    addUndirectedEdge(g, 1, 3, -2);
		    addUndirectedEdge(g, 1, 4, 3);
		    addUndirectedEdge(g, 1, 6, 4);
		    addUndirectedEdge(g, 2, 5, 6);
		    addUndirectedEdge(g, 3, 5, 2);
		    addUndirectedEdge(g, 3, 6, 3);
		    addUndirectedEdge(g, 4, 6, 6);
		    addUndirectedEdge(g, 5, 6, 1);

		    LazyPrimsAdjacencyList solver = new LazyPrimsAdjacencyList(g);
		    Long cost = solver.getMstCost();

		    if (cost == null) {
		      System.out.println("No MST does not exists");
		    } else {
		      System.out.println("MST cost: " + cost);
		      for (Edge e : solver.getMst()) {
		        System.out.println(String.format("from: %d, to: %d, cost: %d", e.from, e.to, e.cost));
		      }
		    }
		  }

		  private static void squareGraphFromSlides() {
		    int n = 9;
		    List<List<Edge>> g = createEmptyGraph(n);

		    addUndirectedEdge(g, 0, 1, 6);
		    addUndirectedEdge(g, 0, 3, 3);
		    addUndirectedEdge(g, 1, 2, 4);
		    addUndirectedEdge(g, 1, 4, 2);
		    addUndirectedEdge(g, 2, 5, 12);
		    addUndirectedEdge(g, 3, 4, 1);
		    addUndirectedEdge(g, 3, 6, 8);
		    addUndirectedEdge(g, 4, 5, 7);
		    addUndirectedEdge(g, 4, 7, 9);
		    addUndirectedEdge(g, 5, 8, 10);
		    addUndirectedEdge(g, 6, 7, 11);
		    addUndirectedEdge(g, 7, 8, 5);

		    LazyPrimsAdjacencyList solver = new LazyPrimsAdjacencyList(g);
		    Long cost = solver.getMstCost();

		    if (cost == null) {
		      System.out.println("No MST does not exists");
		    } else {
		      System.out.println("MST cost: " + cost);
		      for (Edge e : solver.getMst()) {
		        System.out.println(String.format("from: %d, to: %d, cost: %d", e.from, e.to, e.cost));
		      }
		    }
		  }

		  private static void lazyPrimsDemoFromSlides() {
		    int n = 8;
		    List<List<Edge>> g = createEmptyGraph(n);

		    addDirectedEdge(g, 0, 1, 10);
		    addDirectedEdge(g, 0, 2, 1);
		    addDirectedEdge(g, 0, 3, 4);

		    addDirectedEdge(g, 2, 1, 3);
		    addDirectedEdge(g, 2, 5, 8);
		    addDirectedEdge(g, 2, 3, 2);
		    addDirectedEdge(g, 2, 0, 1);

		    addDirectedEdge(g, 3, 2, 2);
		    addDirectedEdge(g, 3, 5, 2);
		    addDirectedEdge(g, 3, 6, 7);
		    addDirectedEdge(g, 3, 0, 4);

		    addDirectedEdge(g, 5, 2, 8);
		    addDirectedEdge(g, 5, 4, 1);
		    addDirectedEdge(g, 5, 7, 9);
		    addDirectedEdge(g, 5, 6, 6);
		    addDirectedEdge(g, 5, 3, 2);

		    addDirectedEdge(g, 4, 1, 0);
		    addDirectedEdge(g, 4, 5, 1);
		    addDirectedEdge(g, 4, 7, 8);

		    addDirectedEdge(g, 1, 0, 10);
		    addDirectedEdge(g, 1, 2, 3);
		    addDirectedEdge(g, 1, 4, 0);

		    addDirectedEdge(g, 6, 3, 7);
		    addDirectedEdge(g, 6, 5, 6);
		    addDirectedEdge(g, 6, 7, 12);

		    addDirectedEdge(g, 7, 4, 8);
		    addDirectedEdge(g, 7, 5, 9);
		    addDirectedEdge(g, 7, 6, 12);

		    LazyPrimsAdjacencyList solver = new LazyPrimsAdjacencyList(g);
		    Long cost = solver.getMstCost();

		    if (cost == null) {
		      System.out.println("No MST does not exists");
		    } else {
		      System.out.println("MST cost: " + cost);
		      for (Edge e : solver.getMst()) {
		        System.out.println(String.format("from: %d, to: %d, cost: %d", e.from, e.to, e.cost));
		      }
		    }
		  }
}
