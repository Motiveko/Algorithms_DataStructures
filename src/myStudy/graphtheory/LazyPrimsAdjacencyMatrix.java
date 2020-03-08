package myStudy.graphtheory;

import java.util.*;

public class LazyPrimsAdjacencyMatrix {

	static class Edge implements Comparable<Edge>{
		int to, cost;
		
		public Edge( int to, int cost) {
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge other) {
			return this.cost - other.cost;
		}
	}
	
	public static Long prims(Integer[][] graph){
		
		int n = graph.length;
		long sum=0, visitedNodes = 1;
		boolean[] visited = new boolean[n];
		
		int m = n-1;
		
		
		PriorityQueue<Edge> pq= new PriorityQueue<>();
		
		//relax edges of start node : 0;
		for(int i=0; i<n; i++	) {
			if(graph[0][i] != null)
				pq.add(new Edge(i,graph[0][i]));
		}
		visited[0] = true;
		while(!pq.isEmpty()) {
			
			Edge at = pq.poll();
			if(!visited[at.to]) {
				visited[at.to] = true;
				sum += at.cost;
				for(int i=0; i<n; i++)
					if(graph[at.to][i]!=null)
						pq.add(new Edge(i, graph[at.to][i]));
			}
			
		}
		
		
		return sum;
	}
	
	public static void main(String[] args) {
		final int NUM_NODES = 10;
	    Integer[][] graph = new Integer[NUM_NODES][NUM_NODES];

	    // Make an undirected graph
	    graph[0][1] = graph[1][0] = 1;
	    graph[0][3] = graph[3][0] = 4;
	    graph[0][4] = graph[4][0] = 5;
	    graph[1][3] = graph[3][1] = 2;
	    graph[1][2] = graph[2][1] = 1;
	    graph[2][3] = graph[3][2] = 5;
	    graph[2][5] = graph[5][2] = 7;
	    graph[3][4] = graph[4][3] = 2;
	    graph[3][6] = graph[6][3] = 2;
	    graph[3][5] = graph[5][3] = 11;
	    graph[4][7] = graph[7][4] = 4;
	    graph[5][6] = graph[6][5] = 1;
	    graph[5][8] = graph[8][5] = 4;
	    graph[6][7] = graph[7][6] = 4;
	    graph[6][8] = graph[8][6] = 6;
	    graph[7][8] = graph[8][7] = 1;
	    graph[7][9] = graph[9][7] = 2;
	    graph[8][9] = graph[9][8] = 0;

	    Long mstCost = prims(graph);
	    System.out.println("MST cost: " + mstCost);
	}
}
