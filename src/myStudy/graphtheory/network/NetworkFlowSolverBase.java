package myStudy.graphtheory.network;

import java.util.*;

public abstract class NetworkFlowSolverBase {

	//To avoid overflow, set infinity to a value less than Long.MAX_VALUE;
	protected static final long INF = Long.MAX_VALUE / 2;
	
	public static class Edge{
		public int from, to;
		public Edge residual;
		public long flow, cost;
		public final long capacity, originalCost;
		
		public Edge(int from, int to, long capacity) {
			this(from, to, capacity, 0);
		}
		
		public Edge(int from, int to, long capacity, long cost) {
			this.from = from;
			this.to = to;
			this.capacity = capacity;
			this.originalCost = this.cost = cost;
		}
		
		public boolean isResidual() {
			return capacity == 0;
		}
		
		public long remainingCapacity() {
			return capacity - flow;
		}
		
		public void augment(long bottleNeck) {
			this.flow += bottleNeck;
			residual.flow -= bottleNeck;
		}
		
		public String toString(int s, int t) {
			String u = (from == s) ? "s" : ((from==t) ? "t" : String.valueOf(from));
			String v = (to == s) ? "t" : ((to == t) ? "t" : String.valueOf(to));
			return String.format(
					"Edge %s -> %s | flow = %d | capacity = %d | is residual : %s",
					u,v,flow,capacity,isResidual());
		}
	}
	// n = number of nodes, s= source, t = sink;
	protected final int n, s, t;
	
	protected long maxFlow;
	protected long minCost;
	
	protected boolean[] minCut;
	protected List<Edge>[] graph;
	
	//boolean 말고 이런 방식도 있다.
	//쓰다가 전부 unvisited로 만들고 싶으면 visitedToken 값을 올려버리면 된다고 한다.
	private int visitedToken = 1;
	private int[] visited;
	
	private boolean solved;
	
	public NetworkFlowSolverBase(int n, int s, int t) {
		this.n = n;
		this.s = s;
		this.t = t;
		initializeGraph();
		minCut = new boolean[n];
		visited = new int[n];
	}
	
	public void initializeGraph() {
		graph = new List[n];
		for(int i=0; i<n; i++) graph[i] = new ArrayList<>();
	}
	
	public void addEdge(int from, int to, long capacity) {
		if(capacity<0) throw new IllegalArgumentException("capacity should be bigger than 0");
		Edge e1 = new Edge(from, to, capacity);
		Edge e2 = new Edge(to, from, 0);
		e1.residual = e2;
		e2.residual = e1;
		graph[from].add(e1);
		graph[to].add(e2);
	}
	
  public void addEdge(int from, int to, long capacity, long cost) {
	    Edge e1 = new Edge(from, to, capacity, cost);
	    Edge e2 = new Edge(to, from, 0, -cost);
	    e1.residual = e2;
	    e2.residual = e1;
	    graph[from].add(e1);
	    graph[to].add(e2);
  }
	
	public void visit(int i) {
		visited[i] = visitedToken;
	}
	
	public boolean visited(int i) {
		return visited[i] == visitedToken;
	}
	
	public void markAllNodesAsUnvisited() {
		visitedToken++;
	}
	
	public List<Edge>[] getGraph(){
		execute();
		return graph;
	}
	
	public long getMaxFlow() {
		execute();
		return maxFlow;
	}
	
	public long getMinCost() {
		execute();
		return minCost;
	}
	
	public boolean[] getMinCut() {
	    execute();
	    return minCut;
	}

	private void execute() {
		if(solved) return;
		solved = true;
		solve();
	}
	
	public abstract void solve();
	
	
}
