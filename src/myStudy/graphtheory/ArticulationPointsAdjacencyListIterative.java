package myStudy.graphtheory;

import java.util.*;

//Fiset은 구현을 안했으나  bridge iterative를 응용삼아 한번 해보았다
public class ArticulationPointsAdjacencyListIterative {
	
	private int n, id, rootNodeOutcomingCount;
	private int[] ids, low;
	private boolean solved;
	private boolean[] visited, isArticulationPoint;
	private List<List<Integer>> graph;
	
	private final static int CALLBACK_TOKEN = -2;
	
	public ArticulationPointsAdjacencyListIterative(List<List<Integer>> graph, int n) {
		if(graph==null || n == 0 || graph.size()!=n) throw new IllegalArgumentException("invalid input");
		this.graph = graph;
		this.n = n;
	}
	
	public boolean[] findArticulationPoints() {
		if(solved) return isArticulationPoint;
		
		id = 0;
		ids = new int[n];
		low = new int[n];
		visited = new boolean[n];
		isArticulationPoint = new boolean[n];
		
		Deque<Integer> stack = new ArrayDeque<>();
		Deque<Integer> parentStack = new ArrayDeque<>();
		

		
		for( int i = 0; i < n; i ++) {
			if(!visited[i]) {
					stack.push(i);
					parentStack.push(-1);
					rootNodeOutcomingCount = 0;
				
				
				while(!stack.isEmpty()) {
					
					int at = stack.pop();
					
					if(at == CALLBACK_TOKEN) {
						
						at = stack.pop();
						int to = stack.pop();
						
						low[at] = Math.min(low[at], low[to]);
						if(ids[at] <= low[to]) isArticulationPoint[at] = true;
						
						continue;
					}
					int parent = parentStack.pop();

					
					if(!visited[at]) {
						if( parent == i) rootNodeOutcomingCount++;
						ids[at] = low[at] = ++id;
						visited[at] = true;
						
						if(graph.get(at)!=null) {
							for(int to : graph.get(at)) {
								if(to==parent) continue;
								
								if(!visited[to]) {
									stack.push(to);
									stack.push(at);
									stack.push(CALLBACK_TOKEN);
									stack.push(to);
									parentStack.push(at);
								} else {
									low[at] = Math.min(low[at], ids[to]);
								}
							}
						}
					}
				}
			isArticulationPoint[i] = (rootNodeOutcomingCount > 1);
			}
		}
		
		solved = true;
		return isArticulationPoint;
	}
	
	private static List<List<Integer>> createGraph(int n) {

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
		  ArticulationPointsAdjacencyListIterative solver = new ArticulationPointsAdjacencyListIterative(graph, n);
		  boolean[] istArticulationPoint = solver.findArticulationPoints();
		  for(int i =0 ; i<n ; i++) {
			  if(istArticulationPoint[i]) System.out.println(i + "is Articulation Point.");
		  }

	}
}