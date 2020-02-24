	// Bellman Ford v-1번 relax하는 이유
	// negative cycle 을 검사하는 edge relaxation을 v-1번 수행하는 이유는
	// 그래프의 도착지 노드를 제외한 모든 노드들을 거쳐서 도착한다고 했을 때
	// 어떤 노드에 가는 최단경로를 검색할 때 relaxtion이 효율적으로 이뤄지지 못하고
	// 그럼으로 인해 매 relaxation에서 나머지 노드 중 하나의 값씩만 갱신이 이뤄질때
	// v-1번 해야 전체 갱신이 완료된다. 어디까지나 Worst case!
	// neagative cycle이 없다면 v번째부터는 모든 노드의 값들이 다 반영되었으므로 
	//갱신할게없다
	
package myStudy.graphtheory;

import java.util.Arrays;

public class BellmanFordAdjacencyList {

	public static class Edge{
		int from, to;
		double cost;
		
		public Edge(int from, int to, double cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
	}
	
	
	public static double[] bellmanFord(Edge[] edges , int V, int start) {
		
		double[] dist = new double[V];
		Arrays.fill(dist, Double.POSITIVE_INFINITY);
		dist[start] = 0;
		
		boolean relaxedAnEdge = true;
		
		//Edge Relaxation
		for(int v = 0; v < V - 1 && relaxedAnEdge; v++) {
			relaxedAnEdge = false;
			for(Edge edge : edges) {
				if(dist[edge.from] + edge.cost < dist[edge.to]) {
					dist[edge.to] = dist[edge.from] + edge.cost;
					relaxedAnEdge = true;
				}
			}
		}
		
		//Find Negative Cycle, if none, do not run this passage
		for(int v= 0; v<V-1 && relaxedAnEdge ; v++) {
			relaxedAnEdge = false;
			for(Edge edge : edges ) {
				if(dist[edge.from] + edge.cost < dist[edge.to]) {
					dist[edge.to] = Double.NEGATIVE_INFINITY;
					relaxedAnEdge = true;
				}
			}
		}
		return dist;
	}
	
	public static void main(String[] args) {

	    int E = 10, V = 9, start = 0;
	    Edge[] edges = new Edge[E];
	    edges[0] = new Edge(0, 1, 1);
	    edges[1] = new Edge(1, 2, 1);
	    edges[2] = new Edge(2, 4, 1);
	    edges[3] = new Edge(4, 3, -3);
	    edges[4] = new Edge(3, 2, 1);
	    edges[5] = new Edge(1, 5, 4);
	    edges[6] = new Edge(1, 6, 4);
	    edges[7] = new Edge(5, 6, 5);
	    edges[8] = new Edge(6, 7, 4);
	    edges[9] = new Edge(5, 7, 3);

	    double[] d = bellmanFord(edges, V, start);

	    for (int i = 0; i < V; i++)
	      System.out.printf("The cost to get from node %d to %d is %.2f\n", start, i, d[i]);

	    // Output:
	    // The cost to get from node 0 to 0 is 0.00
	    // The cost to get from node 0 to 1 is 1.00
	    // The cost to get from node 0 to 2 is -Infinity
	    // The cost to get from node 0 to 3 is -Infinity
	    // The cost to get from node 0 to 4 is -Infinity
	    // The cost to get from node 0 to 5 is 5.00
	    // The cost to get from node 0 to 6 is 5.00
	    // The cost to get from node 0 to 7 is 8.00
	    // The cost to get from node 0 to 8 is Infinity

	}
}
