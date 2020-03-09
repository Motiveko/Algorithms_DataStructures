/*
 
 - Directed Graph 에서 LowLinkValue를 구해서 Cycle을 찾는다	
 - Bridge / Articulation Point 때와는 다르게 어떤 Graph에서 시작점에 따라 LowLinkValue가달라질 수 있다
 - 이를 해결하기위한 알고리즘!
 	- 어떤 Current Node u에 v의 LowLinkValue를 넣기 위해서는 조건이있는데	
 		- u->v 인 Edge가 반드시 존재하면서
 		- v는 이미 Stack에 들어가 있어야한다( u보다 조상이어야한다)

 		- cycle을 찾고 low link value를 갱신하고 나면 그 cycle은 stack에서 제거, 다른 group을 dfs할 때 고려하지 않게된다.
 
 - O(V+E) , Linear
 - Bridge , Articulation Point때와 다르게 to가 visited일 때에도 low[at]과 low[to]를 비교한다.
 */

package myStudy.graphtheory;

import java.util.*;

public class TarjanSccSolverAdjacencyList {

	
	private int n; 
	private int numOfSCCs;
	private int[] rootOfCycle;
	private Stack<Integer> stack;
	private boolean[] visited;
	private int id;
	private int[] low, ids;
	private List<List<Integer>> graph;
	
	private boolean[] onStack;

	//Unweighted Directed Edge

	
	public TarjanSccSolverAdjacencyList(List<List<Integer>> graph, int n) {
		if(graph == null || n == 0 || graph.size()!=n) throw new IllegalArgumentException("ho!");
		
		this.graph = graph;
		this.n = n;
	}
	
	public int tarjan() {
		
		stack = new Stack<>();
		visited = new boolean[n];
		id = 0;
		low = new int[n];
		ids = new int[n];
		rootOfCycle = new int[n];
		onStack = new boolean[n];
		
		for(int i=0; i<n; i++) {
			
			if(!visited[i]) dfs(i);
		}
		
		
		return numOfSCCs;
	}
	
	private void dfs( int at) {
		stack.push(at);
		
		visited[at] = true;
		onStack[at] = true;
		
		ids[at] = low[at] = id++;
		
		for(int to : graph.get(at)) {
			if(!visited[to]) dfs(to);
			if(onStack[to]) low[at] = Math.min(low[at], low[to]);
		}
		
		if(low[at] == ids[at]) {
			for( int node = stack.pop();  ; node = stack.pop()) {
				onStack[node] = false;
				if(low[node]==ids[at]) {
					numOfSCCs++;
					rootOfCycle[node] = at;
				}
				if(node == at) break;
			}
		}
		
	}
	
	private static List<List<Integer>> createEmptyGraph(int n){
		List<List<Integer>> graph = new ArrayList<>();
		for(int i=0; i<n; i++) graph.add(new ArrayList<>());
		return graph;
	}
	
	private static void addEdge(List<List<Integer>> graph, int from , int to) {
		graph.get(from).add(to);
	}
	
	public static void main(String[] args) {
		testEx2();
	}
	
	private static void testEx1() {
		int n=8;
		List<List<Integer>> graph = createEmptyGraph(n);
		addEdge(graph,0,1);
		addEdge(graph,1,2);
		addEdge(graph,2,0);
		addEdge(graph,3,4);
		addEdge(graph,3,7);
		addEdge(graph,4,5);
		addEdge(graph,5,6);
		addEdge(graph,5,0);
		addEdge(graph,6,4);
		addEdge(graph,6,0);
		addEdge(graph,6,2);
		addEdge(graph,7,3);
		addEdge(graph,7,5);
		
		TarjanSccSolverAdjacencyList solver = new TarjanSccSolverAdjacencyList(graph, n);
		System.out.println(solver.tarjan());
		System.out.println(Arrays.toString(solver.rootOfCycle));
	}

	private static void testEx2() {
	    int n = 8;
	    List<List<Integer>> graph = createEmptyGraph(n);

	    addEdge(graph, 6, 0);
	    addEdge(graph, 6, 2);
	    addEdge(graph, 3, 4);
	    addEdge(graph, 6, 4);
	    addEdge(graph, 2, 0);
	    addEdge(graph, 0, 1);
	    addEdge(graph, 4, 5);
	    addEdge(graph, 5, 6);
	    addEdge(graph, 3, 7);
	    addEdge(graph, 7, 5);
	    addEdge(graph, 1, 2);
	    addEdge(graph, 7, 3);
	    addEdge(graph, 5, 0);

	    TarjanSccSolverAdjacencyList solver = new TarjanSccSolverAdjacencyList(graph, n);
	    solver.tarjan();
	    int[] sccs = solver.rootOfCycle;
	    Map<Integer, List<Integer>> multimap = new HashMap<>();
	    for (int i = 0; i < n; i++) {
	      if (!multimap.containsKey(sccs[i])) multimap.put(sccs[i], new ArrayList<>());
	      multimap.get(sccs[i]).add(i);
	    }

	    // Prints:
	    // Number of Strongly Connected Components: 3
	    // Nodes: [0, 1, 2] form a Strongly Connected Component.
	    // Nodes: [3, 7] form a Strongly Connected Component.
	    // Nodes: [4, 5, 6] form a Strongly Connected Component.
//	    System.out.printf("Number of Strongly Connected Components: %d\n", solver.sccCount());
	    for (List<Integer> scc : multimap.values()) {
	      System.out.println("Nodes: " + scc + " form a Strongly Connected Component.");
	    }
	    System.out.println(solver.numOfSCCs);
	}
}
