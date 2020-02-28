package myStudy.graphtheory;

import java.util.*;

public class BridgesAdjacencyListIterative {

	private int n, id;
	private int[] ids, low;
	private boolean solved;
	private boolean[] visited;
	private List<List<Integer>> graph;
	private List<Integer> bridges;
	
	private static int CALLBACK_TOKEN = -2;
	
	public BridgesAdjacencyListIterative(List<List<Integer>> graph, int n) {
		
		if(graph==null || n<=0 || graph.size()!=n) throw new IllegalArgumentException("fuck U");
		this.graph = graph;
		this.n = n;
	}
	
	public List<Integer> findBridges() {
		
		ids = new int[n];
		low = new int[n];
		visited = new boolean[n];
		bridges = new ArrayList<>();
		
		id = 0;
		

		
		// for independent group
		for(int i=0; i<n; i++) {
			
			Deque<Integer> stack =  new ArrayDeque<>();
			Deque<Integer> parentStack = new ArrayDeque<>();
			
			stack.push(i);
			parentStack.push(-1);
			
			
			while(!stack.isEmpty()) {
				
				int at = stack.pop();
				
				if(at==CALLBACK_TOKEN) {
					
					at = stack.pop();
					int to = stack.pop(); 
					low[at] = Math.min(low[at], low[to]);
					if( ids[at] < low[to]) {
						bridges.add(at);
						bridges.add(to);
					}
					continue;
				}
				
				int parent = parentStack.pop();
				
				if(!visited[at]) {
					//initialize id, low value
					ids[at] = low[at] = ++id;
					visited[at] = true;
					for(int to : graph.get(at)) {
						if(to==parent) continue;
						
						if(!visited[to]) {
							stack.push(to);
							stack.push(at);
							stack.push(CALLBACK_TOKEN);
							stack.push(to);
							parentStack.push(at);
						} else {
							low[at] = Math.min(low[at] , ids[to]);
						}
					}	
				}	
			}
		}
		solved= true;
		return bridges;
	}
	
	public static void main(String[] args) {

	    int n = 10;
	    List<List<Integer>> graph = createGraph(n);

	    addEdge(graph, 0, 1);
	    addEdge(graph, 0, 2);
	    addEdge(graph, 1, 2);
	    addEdge(graph, 1, 3);
	    addEdge(graph, 2, 3);
	    addEdge(graph, 1, 4);
	    addEdge(graph, 2, 7);
	    addEdge(graph, 4, 6);
	    addEdge(graph, 4, 5);
	    addEdge(graph, 5, 6);
	    addEdge(graph, 7, 8);
	    addEdge(graph, 7, 9);

	    BridgesAdjacencyListIterative solver = new BridgesAdjacencyListIterative(graph, n);
	    List<Integer> bridges = solver.findBridges();
	    for (int i = 0; i < bridges.size() / 2; i++) {
	      int node1 = bridges.get(2 * i);
	      int node2 = bridges.get(2 * i + 1);
	      System.out.printf("BRIDGE between nodes: %d and %d\n", node1, node2);
	    }
	  }

	  // Initialize graph with 'n' nodes.
	  public static List<List<Integer>> createGraph(int n) {
	    List<List<Integer>> graph = new ArrayList<>();
	    for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
	    return graph;
	  }

	  // Add undirected edge to graph.
	  public static void addEdge(List<List<Integer>> graph, int from, int to) {
	    graph.get(from).add(to);
	    graph.get(to).add(from);
	  }
}
