package myStudy.graphtheory.network;

/*
 * Flow를 Largest Capacity를 가지는 Edge로 먼저 흘려보내주는거 (방식은 Ford Fulkerson DFS와 같다)
 * 기존 Ford Fulkerson의 Dfs방식은 Path의 capacity가 1이상이면 랜덤하게 고르므로 최악의 경우
 * Complexity가 O(fE) , f: flow 가 될 수 있었다
 * 이를 방지하기 위해 capacity가 큰 edge들을 먼저 고려하는 것!
 * U : the value of the largest edge capacity in the initial flow graph.	 
 * 델타 : largest power of 2 less than or equal to U.
 * 
 * largest capacity scaling에서는 Runtime을 빠르게 하기 위해
 * edge를 선택 할 때 델타 이상의 capacity를 가지는 edge를 선택한다.
 * 
 * 델타 이상값을 찾을수 없게 되면 델타를 1/2 한 후 다시 검색한다.
 *
 * O(E^2logU) or O(EVlogU)
 */
import java.util.*;
public class CapacityScalingSolverAdjacencyList extends NetworkFlowSolverBase{

	public CapacityScalingSolverAdjacencyList(int n, int s, int t) {
		super(n, s, t);
	}

	@Override
	public void solve() {
		/*
		 * Fiset은 delta를 찾는 방식을 add Edge할 때 마다 최대값을 update하는 방식으로 했다! 나머지 알고리즘은 비슷!	
		 */
		//find U value
		long U = findU(s,0);
		 
		//Set Delta Value;
		int c=1;
		//U보다 작은 2의 제곱근 찾는 간단한 코드!	
		double d = Long.highestOneBit(U);
//		double d = Math.pow(2, c);
//		while(d < U) {
//			d = Math.pow(2, ++c);
//		}
//		d = d/2;
		System.out.println("U value : " + U + ", D value : " + d);
		for(long f = dfs(s,INF,d) ; d >= 1 ; f = dfs(s,INF,d) ) {
			System.out.println("f : " + f + ", delta : " + d);
			if(f ==0) d=d/2;
			
			System.out.println(d);
			markAllNodesAsUnvisited();
			maxFlow += f;
		}
		
	}
	
	private long dfs(int node, long flow, double d) {
		
		if(node == t) return flow;
		
		List<Edge> edges = graph[node];
		visit(node);
		
		if(edges != null) {
			for(Edge edge : edges) {
				long rcap = edge.remainingCapacity();
				long bottleNeck = 0;
				if( rcap >= (long)d && !visited(edge.to)) {
					bottleNeck = dfs(edge.to, Math.min(flow, rcap), d);
				}
				if(bottleNeck > 0) {
					edge.augment(bottleNeck);
					return bottleNeck;
				}
			}
		}
		
		return 0;
	}

	private long findU( int node, long U) {
		
		if(node == t) return U;
		
		List<Edge> edges = graph[node];
		long maxCap = U;
		visit(node);
		if(edges != null) {
			for(Edge edge : edges) {
				if( edge.capacity > 0 ) {
					maxCap = findU(edge.to, Math.max(maxCap, edge.capacity));
				}
			}
		}
		markAllNodesAsUnvisited();
		return maxCap;
	}
	
	  public static void main(String[] args) {
		    testSmallFlowGraph();
		    testExampleFromMySlides();
		  }

		  // Testing graph from:
		  // http://crypto.cs.mcgill.ca/~crepeau/COMP251/KeyNoteSlides/07demo-maxflowCS-C.pdf
	  private static void testSmallFlowGraph() {
		    int n = 6;
		    int s = n - 1;
		    int t = n - 2;

		    CapacityScalingSolverAdjacencyList solver;
		    solver = new CapacityScalingSolverAdjacencyList(n, s, t);

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

	  private static void testExampleFromMySlides() {
		    int n = 6;
		    int s = n - 1;
		    int t = n - 2;

		    CapacityScalingSolverAdjacencyList solver;
		    solver = new CapacityScalingSolverAdjacencyList(n, s, t);

		    // Source edges
		    solver.addEdge(s, 0, 6);
		    solver.addEdge(s, 1, 14);

		    // Sink edges
		    solver.addEdge(2, t, 11);
		    solver.addEdge(3, t, 12);

		    // Middle edges
		    solver.addEdge(0, 1, 1);
		    solver.addEdge(2, 3, 1);
		    solver.addEdge(0, 2, 5);
		    solver.addEdge(1, 2, 7);
		    solver.addEdge(1, 3, 10);

		    System.out.println(solver.getMaxFlow()); // 20
		  }
	
}
