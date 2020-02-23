package myStudy.graphtheory;

import java.util.*;

public class BFSAdjacencyListIterative {

	
	public static class Edge{
		int from, to , cost;
		
		public Edge(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
	}
	
	private int n;
	private Integer[] prev;
	private List<List<Edge>> graph;
	
	public BFSAdjacencyListIterative(List<List<Edge>> graph) {
		if(graph == null) throw new IllegalArgumentException("graph could not be null!");
		n = graph.size();
		this.graph = graph;
	}
	
	public List<Integer> reconstructPath(int start, int end){
		
		bfs(start);
		List<Integer> path = new ArrayList<>();
		
		for( Integer at = end; at!=null; at = prev[at]) path.add(at);
		Collections.reverse(path);
		
		if(path.get(0)==start) 
			return path;
		
		
		//if start - end are not connected, return empty path
		path.clear();
		return path;
	}
	
	private void bfs(int start) {
		
		prev = new Integer[n];
		
		Deque<Integer> queue = new ArrayDeque<Integer>();
		
		boolean[] visited = new boolean[n];
		
		queue.add(start);
		visited[start] = true;
		
		while(!queue.isEmpty()) {
			int node = queue.poll();
			List<Edge> edges = graph.get(node);
			
			if(edges!=null) {
				for(Edge edge : edges) {
					if(!visited[edge.to]) {
						visited[edge.to] = true;						
						prev[edge.to] = node;
						queue.offer(edge.to);
					}
				}
			}
		}			
	
	}
	
	
	
	public static List<List<Edge>> createEmptyGraph(int n){
		
		List<List<Edge>> graph = new ArrayList(n);
		
		for(int i=0; i<n; i++) graph.add(i, new ArrayList());
		
		return graph;
	}
	
	
	public static void addDirectedEdge(List<List<Edge>> graph, int from, int to, int cost) {		
		graph.get(from).add(new Edge(from, to, cost));
	}
	
	public static void addUndirectedEdge(List<List<Edge>> graph, int from, int to, int cost) {
		
		addDirectedEdge(graph, from, to, cost);
		addDirectedEdge(graph, to, from, cost);
	}
	
	public static void addUnweightedUndirectedEdge(List<List<Edge>> graph, int from, int to) {
		addUndirectedEdge(graph, from, to, 1);
	}
	
	
	public static void main(String[] args) {
		
		 final int n = 13;
		    List<List<Edge>> graph = createEmptyGraph(n);

		    addUnweightedUndirectedEdge(graph, 0, 7);
		    addUnweightedUndirectedEdge(graph, 0, 9);
		    addUnweightedUndirectedEdge(graph, 0, 11);
		    addUnweightedUndirectedEdge(graph, 7, 11);
		    addUnweightedUndirectedEdge(graph, 7, 6);
		    addUnweightedUndirectedEdge(graph, 7, 3);
		    addUnweightedUndirectedEdge(graph, 6, 5);
		    addUnweightedUndirectedEdge(graph, 3, 4);
		    addUnweightedUndirectedEdge(graph, 2, 3);
		    addUnweightedUndirectedEdge(graph, 2, 12);
		    addUnweightedUndirectedEdge(graph, 12, 8);
		    addUnweightedUndirectedEdge(graph, 8, 1);
		    addUnweightedUndirectedEdge(graph, 1, 10);
		    addUnweightedUndirectedEdge(graph, 10, 9);
		    addUnweightedUndirectedEdge(graph, 9, 8);

		    BFSAdjacencyListIterative solver;
		    solver = new BFSAdjacencyListIterative(graph);

		    int start = 10, end = 5;
		    List<Integer> path = solver.reconstructPath(10, 5);
		    System.out.printf("The shortest path from %d to %d is: [%s]\n", start, end, formatPath(path));
		    // Prints:
		    // The shortest path from 10 to 5 is: [10 -> 9 -> 0 -> 7 -> 6 -> 5]
		
		
		
	}

	 private static String formatPath( List<Integer> path) {
		
//		return String.join(
//				"->" , path.stream().map(Object::toString).collect(java.util.stream.Collectors.toList()));
		//같다.
		 return String.join(
				 "->", path.stream().map(x->Integer.toString(x)).collect(java.util.stream.Collectors.toList()));
	}
}
