package myStudy.graphtheory;

import java.util.*;

public class ArticulationPointsAdjacencyList {

	private int n , id, rootNodeOutcomingEdgeCount;
	private boolean solved;
	private int[] ids, low;
	private boolean[] visited, isArticulationPoint;
	private List<List<Integer>> graph;
	
	public ArticulationPointsAdjacencyList(List<List<Integer>> graph, int n) {
		if(graph ==null || n ==0 || graph.size()!=n	) throw new IllegalArgumentException("ho!");
		this.graph = graph;
		this.n = n;
	}
	
	public boolean[] findArticulationPoints() {
		
		if(solved) return isArticulationPoint;
		
		id = 0;
		low = new int[n];
		ids = new int[n];
		visited = new boolean[n];
		isArticulationPoint = new boolean[n];
		
		//for the dependent group of graph
		for(int i=0; i<n; i++) {
			if(!visited[i]) {
				rootNodeOutcomingEdgeCount=0;
				dfs(i,i,-1);
				isArticulationPoint[i] = (rootNodeOutcomingEdgeCount > 1);
			}
		}
		
		solved=true;
		return isArticulationPoint;
	}
	
	public void dfs(int root, int at, int parent) {
		
		if(parent == root) rootNodeOutcomingEdgeCount++;
		
		ids[at] = low[at] = id++;
		visited[at] = true;
		for( int to : graph.get(at)) {
			
			if(to==parent) continue;
			
			if(!visited[to]) {
				dfs(root, to, at);
				low[at] = Math.min(low[at], low[to]);
				
				//Operator < ::find ArticulationPoint via Bridges
				//Operator = ::root node of cycle, 
				//but the main root node i shold check the OutEdgeCount :: 첫 포문에서 dfs하고 검사!
				//메인 루트가 아니라 파생된 사이클의 루트노드는 이미 부모노드와 연결된 엣지가있고
				//사이클을 만드는 아웃노드와 사이클의 끝에서 들어오는 노드가 있으므로 체크할 필요가 없다 무적권 트루!
				if( ids[at] <= low[to]) isArticulationPoint[at] = true;
				
			}else {
				low[at] = Math.min(low[at], ids[to]);
			}
		}
	}
	
	private static List<List<Integer>> createGraph(int n) {
//		List<List<Integer>> graph = new ArrayList<>(n);
//		for(int i=0; i<n; i++) {
//			graph.add(new ArrayList<>());
//		}
//		
//		return graph;
	    List<List<Integer>> graph = new ArrayList<>(n);
	    for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
	    return graph;
	}
	
	private static void addEdge(List<List<Integer>> graph, int i, int j) {
		graph.get(i).add(j);
		graph.get(j).add(i);
	}
	
	public static void main(String[] args) {
		testEx1();
	}
	
	private static void testEx1() {
		  int n  =9;
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
		  ArticulationPointsAdjacencyList solver = new ArticulationPointsAdjacencyList(graph, n);
		  boolean[] istArticulationPoint = solver.findArticulationPoints();
		  for(int i =0 ; i<n ; i++) {
			  if(istArticulationPoint[i]) System.out.println(i + "is Articulation Point.");
		  }

	}
}
