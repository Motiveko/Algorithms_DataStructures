/*
low-link-value : 어떤 노드를 기준으로 dfs를 해서 번호를 매겨 id를 부여했다고 가정하자
이 때 어떤 노드의 low-link-value는 그 노드와 cycle로 묶인 노드들이 가지는 인덱스중에 가장 낮은 id값이다	

Bridge
	for a directed edge e, bridge is when
	id(e.from) < lowLink(e.to) ( to가 큰 이유는 dfs했을때 to 방향으로 갈수록 id가 커질 것 이기 때문)

Articulation Point
	- case1 : e가 브릿지라면 e.from 이나 e.to 중에 하나 이상은 articulation point다
	- case2 : cycle
		if id(e.from) == low(e.to) , there is cycle
		
		the present of cycle implies the root node to a articulation point :: strongly connected componenet
		그러나 start노드가 outEdge가 0~1개만 있으면 Articulation point가 될 수 없다
				- > 이유는 undirected edge를 dfs를 이용해서 directed로 바꿔줄 때 articulation point에서는 아웃엣지가 같은 사이클로 두개가 날 수가 없다
				- > 따라서 outedge가 두개가 있다면 자동으로 다른 사이클로(다른 low link value를 가지는 노드로) 향하게 된다

Complexity : O(V+E)
*/
package myStudy.graphtheory;

import java.util.*;

//Recursive Finding
public class BridgesAdjacencyList {

	private int n, id;
	private int[] low, ids;
	private boolean solved;
	private boolean[] visited;
	private List<List<Integer>> graph;
	private List<Integer> bridges;
	
	
	public BridgesAdjacencyList(List<List<Integer>> graph, int n) {
		if(graph==null || n<=0 || graph.size()!=n) throw new IllegalArgumentException("variables injected to constructor is invalid");
		
		this.graph = graph;
		this.n = n;
	}
	
	public List<Integer> findBridges(){
		if(solved) return bridges;
		
		id = 0;
		low = new int[n];
		ids = new int[n];
		visited = new boolean[n];
		
		// {짝수 + 짝수+1} index pair로 bridge 표시
		bridges = new ArrayList<>();
		
		//independent한 node가 있을 수 있기 때문에 for문으로 돌려준다
						//
		for(int i=0; i<n; i++) if(!visited[i]) dfs(i,-1);
		solved = true;
		return bridges;
	}
				//dfs 에 브릿지를 변수로 넣어주지 않아도 된다!(전역변수로 이미 선언되어있다)
				// fiset의 실수..!
	public void dfs(int at, int parent) {
		visited[at] = true;
		int i = at;
		ids[at] = low[i] = ++i;
		
		for( Integer to : graph.get(at)) {
			//edge를 한방향으로 만들어준다
			if(to==parent) continue;
			
			if(!visited[to]) {
				dfs(to, at);
				low[at] = Math.min(low[at], low[to]);
				if(ids[at] < low[to]) {
					bridges.add(at);
					bridges.add(to);
				}
			}else {
				low[at] = Math.min(low[at], ids[to]);
			}
		}
	}
	
	public static void main(String[] args) {
		
	    int n = 9;
	    List<List<Integer>> graph = createGraph(n);

	    addEdge(graph, 0, 1);
	    addEdge(graph, 0, 2);
	    addEdge(graph, 1, 2);
	    addEdge(graph, 2, 3);
	    addEdge(graph, 3, 4);
	    addEdge(graph, 2, 5);
	    addEdge(graph, 5, 6);
	    addEdge(graph, 6, 7);
	    addEdge(graph, 7, 8);
	    addEdge(graph, 8, 5);

	    BridgesAdjacencyList solver = new BridgesAdjacencyList(graph, n);
	    List<Integer> bridges = solver.findBridges();

	    // Prints:
	    // Bridge between nodes: 3 and 4
	    // Bridge between nodes: 2 and 3
	    // Bridge between nodes: 2 and 5
	    for (int i = 0; i < bridges.size() / 2; i++) {
	      int node1 = bridges.get(2 * i);
	      int node2 = bridges.get(2 * i + 1);
	      System.out.printf("Bridge between nodes: %d and %d\n", node1, node2);
	    }
	  }

	  // Initialize graph with 'n' nodes.
	  public static List<List<Integer>> createGraph(int n) {
	    List<List<Integer>> graph = new ArrayList<>();
	    for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
	    return graph;
	  }

	  // Add undirected edge to graph.
	  public static void addEdge(List<List<Integer>> graph, int from, int to) {
	    graph.get(from).add(to);
	    graph.get(to).add(from);
	  }
}	
