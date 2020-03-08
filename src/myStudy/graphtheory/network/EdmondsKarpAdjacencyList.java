package myStudy.graphtheory.network;

import java.util.*;

/*
 * Edmonds - Karp Algorithms
 * Ford - Fulkerson과 다르게 BFS로 Augment Path를 찾는다!	
 * bfs를 이용함으로서 s->t의 매번 가장 짧은 경로를 찾아낸다
 * Augment Path가 짧아지면 확률적으로 Bottle Neck Value가 작아질 확률이 적어지므로, Runtime이 줄어든다!	
 * 그 외의 방식은 비슷하다.	
 * O(VE^2)
 */
public class EdmondsKarpAdjacencyList extends NetworkFlowSolverBase{

	public EdmondsKarpAdjacencyList(int n, int s, int t) {
		super(n, s, t);
	}

	@Override
	public void solve() {

		long flow = 0;
		do {
			markAllNodesAsUnvisited();
			flow = bfs();
			maxFlow += flow;
		}while(flow != 0);
		
	}

	private long bfs() {
		
		Deque<Integer> queue = new ArrayDeque<>();
		Edge[] prev = new Edge[n];
		
		
		queue.offer(s);
		visit(s);
			
		while(!queue.isEmpty()) {
			
			Integer node = (Integer) queue.poll();
			
			if(node == t) break;

			
			List<Edge> edges = graph[node];
			
			if(edges!=null)
				for(Edge edge : edges) {
					if(!visited(edge.to) && edge.remainingCapacity()>0) {
						queue.offer(edge.to);
						visit(edge.to);
						prev[edge.to] = edge;
					}
				}
		}
		//No Augment Path Left	
		if(prev[t] == null) return 0;
		long bottleNeck = INF;
		
//		int node = t;
	
		
//		List<Edge> augmentPath = new ArrayList<>();
//				
//		while(node != s) {
//			Edge edge = prev[node];
//			augmentPath.add(edge);
//			node = edge.from;
//			bottleNeck=Math.min(bottleNeck, edge.remainingCapacity());
//		}
//		
//		for(Edge edge : augmentPath) {
//			edge.augment(bottleNeck);
//		}
		
		for( Edge edge = prev[t]; edge !=null ; edge = prev[edge.from]) bottleNeck = Math.min(bottleNeck, edge.remainingCapacity());
		for( Edge edge = prev[t]; edge !=null ; edge = prev[edge.from] ) edge.augment(bottleNeck);

		return bottleNeck;
	}
	
	public static void main(String[] args) {
		testSmallFlowGraph();
	}
	
	private static void testSmallFlowGraph() {

	    int n = 6;
	    int s = n - 1;
	    int t = n - 2;

	    EdmondsKarpAdjacencyList solver;
	    solver = new EdmondsKarpAdjacencyList(n, s, t);

	    // Source edges
	    solver.addEdge(s, 0, 10);
	    solver.addEdge(s, 1, 10);

	    // Sink edges
	    solver.addEdge(2, t, 10);
	    solver.addEdge(3, t, 10);

	    // Middle edges
	    solver.addEdge(0, 1, 2);
	    solver.addEdge(0, 2, 4);
	    solver.addEdge(0, 3, 8);
	    solver.addEdge(1, 3, 9);
	    solver.addEdge(3, 2, 6);

	    System.out.println(solver.getMaxFlow()); // 19
		
	}

	
}
