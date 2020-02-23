package myStudy.graphtheory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class DFSAdjacencyListRecursive {

	static class Edge{
		int to, from, cost;
		
		public Edge(int from, int to, int cost) {
			this.from =from;
			this.to = to;
			this.cost = cost;
		}
	}

	static long dfs(int at, boolean[] visited, Map<Integer, List<Edge>> graph) {
		
		if(visited[at] == true) return 0L;
		
		visited[at] = true;
		long count =1;
		
		List<Edge> edges =  graph.get(at);
		
		if(edges!=null) {
			for(Edge edge : edges) {
				count += dfs(edge.to, visited, graph);
			}
		}
		return count;
	}
	
	
	
	public static void main(String[] args) {
		
		int numNodes = 5;
		Map<Integer, List<Edge>> graph = new HashMap<>();
		addDirectedEdge(graph, 0, 1, 4);
		addDirectedEdge(graph, 0, 2, 4);
		addDirectedEdge(graph, 1, 2, -2);
		addDirectedEdge(graph, 1, 3, 6);
		addDirectedEdge(graph, 2, 3, 1);
		addDirectedEdge(graph, 2, 2, 10);

		boolean[] visited = new boolean[numNodes];
		long nodeCount = dfs(0, visited, graph);
		System.out.println(nodeCount);
		

		nodeCount = dfs(4, visited, graph);
		System.out.println(nodeCount);

		//메인 구현하여 테스트해보기
		
	}
	
	private static void addDirectedEdge(Map<Integer,List<Edge>> graph, int from, int to, int cost) {
		
		List<Edge> list = graph.get(from);
		if(list== null) {
			list = new ArrayList<>();
			graph.put(from, list);
		}
		list.add(new Edge(from,to,cost));
		
	}
}