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
		id = 0;
		
		Stack<Integer> stack = new Stack<>();
		
		for(int i=0; i<n ; i++) {
			//for independent group
			if(!visited[i]) continue;
			stack.push(i);
			
			while(!stack.isEmpty()) {
				
				Integer at = stack.pop();
				visited[at] = true;
				ids[at] = low[at] = at;
				 
				for(Integer to : graph.get(at)) {
					if(to==at) continue;
					stack.push(to);
				}
				
				
			}
			
			
		}
		
		solved= true;
		return bridges;
	}
	
}
