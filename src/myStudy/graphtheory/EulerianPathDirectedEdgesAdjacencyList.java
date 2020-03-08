package myStudy.graphtheory;
/*
 * Eulerian Path : 모든 Edge를 한번 지나가는 경로
 * 조건 : 
 * Start Vertex가 (out-in) = 1 이고  Out Vertex가 (in-out)=1
 *  ---> Eulerian Circuit은 모두다 (out-in)이 0일때 가능, 출발지점 상관 ㄴㄴ
 * 나머지 Vertex는 (out-in)이 1이어야 존재할 수 있다
 * Componenet가 1개 이상 존재하면 Eulerian Path 가 존재하지 않느다.
 * 
 * Non Directed Graph에서의 알고리즘이지만 문제를 해결 할 땐 Directed로 생각해서 한다.
 * 
 * Complexity : O(E)
 * 
 */
import java.util.*;

public class EulerianPathDirectedEdgesAdjacencyList {
	//William Fiset의 코드는 정말 넘나 멋있다.. 나의 발코드와는 급이 다르다..	
	
	private final int n;
	private int edgeCount;
	private int[] in, out;

	private LinkedList<Integer> path;
	private List<List<Integer>> graph;
	
	public EulerianPathDirectedEdgesAdjacencyList(List<List<Integer>> graph) {
		if(graph==null) throw new IllegalArgumentException();
		
		n = graph.size();
		this.graph = graph;
		path = new LinkedList<>();
	}
	
	public int[] getEulerianPath() {
		setUp();
		
		if(!graphHasEulerianPath()) return null;
		dfs(findStartNode());
		
		if(path.size() != edgeCount + 1 ) return null;
		
		int[] soln = new int[edgeCount + 1];
		for(int i=0; !path.isEmpty(); i++)soln[i] = path.removeFirst();
		
		return soln;
	}
	
	private void setUp() {
		in = new int[n];
		out = new int[n];
		
		edgeCount = 0;
		
		for(int from=0; from<n; from++) {
			for(int to : graph.get(from)) {
				out[from] ++;
				in[to] ++;
				edgeCount++;
			}
		}
	}
	
	private boolean graphHasEulerianPath() {
		if(edgeCount ==0) return false;
		int startNodes = 0, endNodes = 0;
		for(int i=0; i<n ; i++) {
			if(out[i] - in[i] > 1 || in[i] - out[i] > 1) return false;
			else if(out[i] - in[i] == 1) startNodes++;
			else if(in[i] - out[i] == 1 ) endNodes++;
		}
		return (startNodes==1 && endNodes==1) || (startNodes==0 && endNodes == 0);

	}
	
	private int findStartNode() {
		int start = 0;
		for(int i=0; i<n ; i++) {
			if(out[i] - in[i] == 1) return i;
			if(out[i] >0) start = i;
		}
		return start;
	}
	
	//기가막힌 Recursive 코드다....
	private void dfs(int at) {
		while(out[at]!=0) {
			int next = graph.get(at).get(--out[at]);
			dfs(next);
		}
		path.addFirst(at);
	}
	public static List<List<Integer>> initializeEmptyGraph(int n) {
	    List<List<Integer>> graph = new ArrayList<>(n);
	    for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
	    return graph;
	  }

	  public static void addDirectedEdge(List<List<Integer>> g, int from, int to) {
	    g.get(from).add(to);
	  }

	  /* Examples */

	  public static void main(String[] args) {
	    exampleFromSlides();
	    // smallExample();
	  }

	  private static void exampleFromSlides() {
	    int n = 7;
	    List<List<Integer>> graph = initializeEmptyGraph(n);

	    addDirectedEdge(graph, 1, 2);
	    addDirectedEdge(graph, 1, 3);
	    addDirectedEdge(graph, 2, 2);
	    addDirectedEdge(graph, 2, 4);
	    addDirectedEdge(graph, 2, 4);
	    addDirectedEdge(graph, 3, 1);
	    addDirectedEdge(graph, 3, 2);
	    addDirectedEdge(graph, 3, 5);
	    addDirectedEdge(graph, 4, 3);
	    addDirectedEdge(graph, 4, 6);
	    addDirectedEdge(graph, 5, 6);
	    addDirectedEdge(graph, 6, 3);

	    EulerianPathDirectedEdgesAdjacencyList solver;
	    solver = new EulerianPathDirectedEdgesAdjacencyList(graph);

	    // Outputs path: [1, 3, 5, 6, 3, 2, 4, 3, 1, 2, 2, 4, 6]
	    System.out.println(Arrays.toString(solver.getEulerianPath()));
	  }

	  private static void smallExample() {
	    int n = 5;
	    List<List<Integer>> graph = initializeEmptyGraph(n);

	    addDirectedEdge(graph, 0, 1);
	    addDirectedEdge(graph, 1, 2);
	    addDirectedEdge(graph, 1, 4);
	    addDirectedEdge(graph, 1, 3);
	    addDirectedEdge(graph, 2, 1);
	    addDirectedEdge(graph, 4, 1);

	    EulerianPathDirectedEdgesAdjacencyList solver;
	    solver = new EulerianPathDirectedEdgesAdjacencyList(graph);

	    // Outputs path: [0, 1, 2, 1, 4, 1, 3]
	    System.out.println(Arrays.toString(solver.getEulerianPath()));
	  }
}
