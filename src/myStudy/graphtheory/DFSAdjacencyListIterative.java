package myStudy.graphtheory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class DFSAdjacencyListIterative {

	static class Edge{
		int to, from, cost;
		
		public Edge(int from, int to, int cost) {
			this.from =from;
			this.to = to;
			this.cost = cost;
		}
	}

	static int dfs(Map<Integer, List<Edge>> graph ,int start, int n) {
		
		int count =0;
		boolean[] visited = new boolean[n];
		Stack<Integer> stack = new Stack();
	
		stack.push(start);
		
		while(!stack.isEmpty()) {
			int node = (int) stack.pop();
			
			//단순 트리구조면 pop한거는 무조건 visited가 아니지만 
			//Graph에서는 부모-자식 관계가 아니므로
			// cyclic Graph에서는 pop한게 다시들어오는 경우도 잇다
			if(!visited[node]) {
				count++;
				visited[node] = true;
				
				List<Edge> edges = graph.get(node);
				
				if(edges!=null) {
					for( Edge edge : edges) {
						if(!visited[edge.to]) stack.push(edge.to);
					}
				}
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

		
		long nodeCount = dfs(graph, 0 ,5);
		System.out.println(nodeCount);
		
		nodeCount = dfs(graph, 4, 5);
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
