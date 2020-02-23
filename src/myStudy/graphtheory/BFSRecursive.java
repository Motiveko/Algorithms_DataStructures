package myStudy.graphtheory;

import java.util.*;
public class BFSRecursive {

	public static int DEPTH_TOKEN = -1;

	public static int bfs(List<List<Integer>> graph, int start, int n) {
		boolean[] visited = new boolean[n];
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(start);
		queue.offer(DEPTH_TOKEN);
		return bfs(visited, queue, graph);
		
	}
	
	
	private static int bfs(boolean[] visited, Queue<Integer> queue, List<List<Integer>> graph) {
		
		Integer node = queue.poll();
		
		if(node == DEPTH_TOKEN) {
			queue.offer(DEPTH_TOKEN);
			return 1;
		}
		
		if(visited[node]) return 0;
		
		visited[node] = true;
		
		int depth = 0;
		
		List<Integer> edges = graph.get(node);
		
		
		// if(!visited[edge]) 를 안해주면 마지막단계에서 DEPTH_TOKEN 하나만 남아서 while문이 정지되어야하는데 
		// DT뒤에도 visited 노드들이 몇개 더 생겨서 break안하고 한번 더 return 1을 받을 수 있게된다. 따라서 엣지들 넣을때
		// visited검사를 꼭 해야한다 반 듯 이!
		if(edges!=null) for( Integer edge : edges) if(!visited[edge]) queue.offer(edge);
	
		while(true) {
			
			if( queue.size()==1 && queue.peek()==DEPTH_TOKEN) break;
			
			depth += bfs(visited,queue,graph);
		}
		
		return depth;
	}

	
	public static void main(String[] args) {
		
		int n = 14;
		List<List<Integer>> graph = new ArrayList<List<Integer>>();
		for( int i=0; i < n; i++) graph.add(new ArrayList<>());
		
	    addUndirectedEdge(graph, 0, 1);
	    addUndirectedEdge(graph, 0, 2);
	    addUndirectedEdge(graph, 0, 3);
	    addUndirectedEdge(graph, 2, 9);
	    addUndirectedEdge(graph, 8, 2);
	    addUndirectedEdge(graph, 3, 4);
	    addUndirectedEdge(graph, 10, 11);
	    addUndirectedEdge(graph, 12, 13);
	    addUndirectedEdge(graph, 3, 5);
	    addUndirectedEdge(graph, 5, 7);
	    addUndirectedEdge(graph, 5, 6);
	    addUndirectedEdge(graph, 0, 10);
	    addUndirectedEdge(graph, 11, 12);

		System.out.println("DFS depth : " + bfs(graph, 12, n));
		
	}
	
	private static void addUndirectedEdge(List<List<Integer>> graph, int x, int y) {
		graph.get(x).add(y);
		graph.get(y).add(x);
	}
}
