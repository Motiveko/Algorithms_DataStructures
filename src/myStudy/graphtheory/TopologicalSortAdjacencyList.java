package myStudy.graphtheory;

import java.util.*;

public class TopologicalSortAdjacencyList {

	
	static class Edge{
		int to, from, cost;
		
		public Edge(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
	}
	
	private static int dfs(
			int [] order, boolean[] visited, Map<Integer, List<Edge>> graph, int i, int at) 
	{
		
		visited[at] = true;
		
		List<Edge> edges = graph.get(at);
		
		if(edges !=null) for( Edge edge : edges) 
			if(!visited[edge.to])
				i = dfs(order, visited, graph, i, edge.to);
		
		order[i] = at;
		return i-1;
	}
	
	
	public static int[] topologicalSort(Map<Integer, List<Edge>> graph, int n) {
		
		int[] order = new int[n];
		boolean[] visited = new boolean[n];
		int i = n-1;
		
		for( int at = 0; at<n; at++) {
			
			if(!visited[at]) i = dfs(order,visited,graph,i,at);
		}
		
		return order;
	}
	
	public static Integer[] dagShortestPath(Map<Integer, List<Edge>> graph, int start, int numNodes) {
		//MY CODE
//		Integer[] dist = new Integer[numNodes];
//		for(int i=0; i<numNodes; i++) dist[i]= 0;
//		
//		Stack<Edge> stack = new Stack<Edge>();
//		
//		if(start>=0 && start<numNodes) {
//			List<Edge> edges = graph.get(start);
//			if(edges != null) {
//				for(Edge edge : edges) stack.push(edge);
//			}
//		}
//		
//		while(!stack.isEmpty()) {
//			Edge edge = stack.pop();
//			
//			if(dist[edge.to] ==0) {
//				dist[edge.to] = dist[edge.from] + edge.cost;
//			}
//			else if( dist[edge.to] > dist[edge.from] + edge.cost) {
//				dist[edge.to] = dist[edge.from] + edge.cost;
//			}
//
//			List<Edge> edges = graph.get(edge.to);
//			if(edges!=null) {
//				for(Edge edge2 : edges) stack.push(edge2);
//			}
//		}
//		return dist;
		
		int[] topsort = topologicalSort(graph, numNodes);
		Integer[] dist = new Integer[numNodes];
		dist[start] = 0;
		
		for(int i=0; i<numNodes; i++) {
			int index = topsort[i];
			if(dist[index]!= null) {
				List<Edge> edges = graph.get(index);
				if(edges!=null) {
					for(Edge edge : edges) {
						
						int newDist = dist[edge.from] + edge.cost;
						if(dist[edge.to]==null) dist[edge.to] = newDist;
						else dist[edge.to] = Math.min(newDist, dist[edge.to]);
					}
				}
				
			}
		}
		
		return dist;
		
	}
	
	public static void main(String[] args) {
		
		final int N = 7;
		Map<Integer, List<Edge>> graph = new HashMap<>();
		
		for(int i=0; i<N ; i++) graph.put(i, new ArrayList<Edge>());
		
		graph.get(0).add(new Edge(0, 1, 3));
	    graph.get(0).add(new Edge(0, 2, 2));
	    graph.get(0).add(new Edge(0, 5, 3));
	    graph.get(1).add(new Edge(1, 3, 1));
	    graph.get(1).add(new Edge(1, 2, 6));
	    graph.get(2).add(new Edge(2, 3, 1));
	    graph.get(2).add(new Edge(2, 4, 10));
	    graph.get(3).add(new Edge(3, 4, 5));
	    graph.get(5).add(new Edge(5, 4, 7));

	    int[] ordering = topologicalSort(graph, N);
	    System.out.println(java.util.Arrays.toString(ordering));
	    
	    Integer[] dists = dagShortestPath(graph, 0, N);
	    
	    //[0, 3, 2, 3, 8, 3, 0] or [0, 3, 2, 3, 8, 3, null]
	    System.out.println(java.util.Arrays.toString(dists));
	}
}
