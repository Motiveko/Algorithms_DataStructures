package myStudy.graphtheory.network;
/*
 * Directed Graph에서 각 Edge에 Flow Capacity가 존재할 때,

 * Source(s) -> Sink(t) 의 구간에서 maximum flow를 구한다.
 * Augmenting Path : s->t 로의 유효한 edge로 이뤄진 path
 * Bottle Neck : Augmenting Path 내에서 가장 낮은 capacity
 * Augmenting flow : 
 * 		-Augmenting Path에 Bottle Neck Value만큼 flow를 Update해준다. (Path는 dfs로 random하게 찾아진다)
 *  	- Foward Edge에 Flow를 + 해줬다면 Backward Edge에는 -해준다 , Foward가 6/10 이면 Backward는 -6/10
 *  	 ( Backward Edge는 원래 s->t Edge의 reversed direction 을 가지는 Edge, Residual edge )
 * 		- Capacity of edge e : e.capacity - e.flow
 * 		- Residual Edge를 포함해서 반복해서 Augmenting Path를 찾고 Flow를 업데이트 해서 최종적으로 모든 경로의 bottlencek value가 0이되면 알고리즘은 종료!	
 * 
 * Augmenting Flow 과정이 끝나고 최종적인 Maximum Flow는 계산했던 모든 bottleneck값을 더해주면 된다.
 * 	 - source에서 나가는 값을 그냥 계산해줘도 되지 않을까?	
 * 
 * Complexity : O(fE) , f : the maximum flow, E : the number of edges
 *  -> fE가 되는 안좋은 경우는 유투브 참조, Augmenting Path를 dfs로 랜덤하게 찾으므로 발생할 수도 있는 문제다.
 *  
 *  이외의 Maximum Flow를 찾는 알고리즘.. 
 *  Edmonds-Karp( O(E^2V) ) , Capacity scaling( O(E^2logU) ), Dinic's Algorithms , Push Relabel ..
 */

import java.util.*;


public class FordFulkersonDfsSolverAdjacencyList extends NetworkFlowSolverBase{

	public FordFulkersonDfsSolverAdjacencyList(int n, int s, int t) {
		super(n, s, t);
	}

	@Override
	public void solve() {
		
		for( long f = dfs(s, INF); f != 0; f = dfs(s, INF)) {
			markAllNodesAsUnvisited();
			maxFlow += f;
		}
		
		//mincut 있는데 의미하는바가 뭔지도 모르겠고 main에서 출력하는것도 없고 fiset의 original code에도 mincut은 존재하지 않는다..!	
		//고로 그냥 넘어간다.	
	}

	private long dfs( int node, long flow) {
		
		if(node == t) return flow;
		
		List<Edge> edges = graph[node];
		visit(node);
		
		for(Edge edge : edges) {
			
			long rcap = edge.remainingCapacity();
			if(rcap > 0 && !visited(edge.to)) { 
				long bottleNeck = dfs(edge.to, Math.min(flow, rcap));
			
				if(bottleNeck > 0) {
					edge.augment(bottleNeck);
					return bottleNeck;
				}
			}	
		}
		
		return 0;
	}
	public static void main(String[] args) {
		exampleFromSlides();
	}
	private static void exampleFromSlides() {
	    int n = 6;
	    int s = n - 2;
	    int t = n - 1;

	    FordFulkersonDfsSolverAdjacencyList solver;
	    solver = new FordFulkersonDfsSolverAdjacencyList(n, s, t);

	    solver.addEdge(s, 1, 10);
	    solver.addEdge(1, 3, 15);
	    solver.addEdge(3, 0, 6);
	    solver.addEdge(0, 2, 25);
	    solver.addEdge(2, t, 10);

	    solver.addEdge(s, 0, 10);
	    solver.addEdge(3, t, 10);

	    System.out.println(solver.getMaxFlow());

	    List<Edge>[] g = solver.getGraph();
	    for (List<Edge> edges : g) {
	      for (Edge e : edges) {
	        System.out.println(e.toString(s, t));
	        // System.out.println(e.residual.toString(s, t));
	      }
	    }
	}
}
