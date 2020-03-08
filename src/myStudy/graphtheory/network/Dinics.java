package myStudy.graphtheory.network;

/*
 * Algorithm for Max Flow Problem in Unweighted Bipartite Matching Graph
 * 
 * Level Graph를 이용하여 s->t 의 Augmenting Path를 찾는다!	
 * 
 * Step 1 : Find the Level Graph Using BFS
 * Step 2 : Find the augmenting path(DFS) , min capacity >0, if can't find anymore, go step 1 ( blocking flow phase : t까지 도달 못하는 경로, dead end)
 * Step 3 : Augment! go Step 2
 * 
 * 알고리즘을 돌리면 dead end 를 계속해서 만나는 경우가 생기는데 이는 매우 비효율적이므로 DFS 단계에서 dead end를 제거해준다.
 * 
 * O(V^2E)
 */

import java.util.*;

public class Dinics extends NetworkFlowSolverBase {

	private int[] level;
	public Dinics(int n, int s, int t) {
		super(n, s, t);
		level = new int[n];	
	}
	
	@Override
	public void solve() {
	
		int[] next = new int[n];
		
		while(bfs()) {
			//next는 무슨의미일까?!
			Arrays.fill(next, 0);
			for( long f = dfs(s,next,INF) ; f!=0; f = dfs(s,next,INF)) {
				maxFlow += f;
			}
			markAllNodesAsUnvisited();
			//mincut 생략
		}
	}
	
	private boolean bfs() {
		Arrays.fill(level, -1);
		level[s] = 0;
		
		Queue<Integer> queue = new ArrayDeque<>();
		queue.offer(s);
		
		while(!queue.isEmpty()) {
			int node = queue.poll();
			
			List<Edge> edges = graph[node];
			if( edges != null) {
				for(Edge edge : edges) {
					long rcap = edge.remainingCapacity();
					if( rcap > 0 && level[edge.to] ==-1) {
						level[edge.to] = level[node] + 1;
						queue.offer(edge.to);
					}
					
				}
			}
			
		}
		
		return level[t] != -1;
	}

	private long dfs(int at, int[] next, long flow) {
		
		if(at == t) return flow;
		
		int edgeCount = graph[at].size();
		
		//이 for문이 Dinic's Algorithm의 핵심이다!
		//만약 어떤 노드 at의 next[at] index의 edge가 bottle Neck을 못 찾았으면 next ++이 되고 
		//해당 인덱스는 다음 dfs때 at노드를 방문 해도 참조하지 않게 된다!1
		//이해가 안 될 수도 있으나 꽤나 복잡한 상황을 만나서 똑같은 level그래프에서 어떤 노드를 여러번 방문하는 일이 발생할때를 생각하면 어떻게 작동하는지 이해할 수 있다!
		//알고리즘 캡쳐본의 dinic캡쳐에서 하단부의 dead end는 해당 노드로 들어가는 엣지가 두개이므로 두번 스캔할 일을막아준다!
		for( ; next[at]<edgeCount ; next[at]++) {
			
			Edge edge = graph[at].get(next[at]);
			long rcap = edge.remainingCapacity();
			if(rcap > 0 && level[edge.to] == level[at] + 1) {
				long bottleNeck = dfs(edge.to, next, Math.min(flow, rcap));
				
				if(bottleNeck > 0) {
					edge.augment(bottleNeck);
					return bottleNeck;
				}
			}
		}
		
		return 0;
	}
	
	  public static void main(String[] args) {
		    testSmallFlowGraph();
		    // testGraphFromSlides();
		  }

		  // Testing graph from:
		  // http://crypto.cs.mcgill.ca/~crepeau/COMP251/KeyNoteSlides/07demo-maxflowCS-C.pdf
		  private static void testSmallFlowGraph() {
		    int n = 6;
		    int s = n - 1;
		    int t = n - 2;

		    Dinics solver;
		    solver = new Dinics(n, s, t);

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

		  private static void testGraphFromSlides() {
		    int n = 11;
		    int s = n - 1;
		    int t = n - 2;

		    NetworkFlowSolverBase solver;
		    solver = new Dinics(n, s, t);

		    // Source edges
		    solver.addEdge(s, 0, 5);
		    solver.addEdge(s, 1, 10);
		    solver.addEdge(s, 2, 15);

		    // Middle edges
		    solver.addEdge(0, 3, 10);
		    solver.addEdge(1, 0, 15);
		    solver.addEdge(1, 4, 20);
		    solver.addEdge(2, 5, 25);
		    solver.addEdge(3, 4, 25);
		    solver.addEdge(3, 6, 10);
		    solver.addEdge(3, 7, 20);
		    solver.addEdge(4, 2, 5);
		    solver.addEdge(4, 7, 30);
		    solver.addEdge(5, 7, 20);
		    solver.addEdge(5, 8, 10);
		    solver.addEdge(7, 8, 15);

		    // Sink edges
		    solver.addEdge(6, t, 5);
		    solver.addEdge(7, t, 15);
		    solver.addEdge(8, t, 10);

		    System.out.printf("Maximum flow %d\n", solver.getMaxFlow()); // 30
		  }
}
