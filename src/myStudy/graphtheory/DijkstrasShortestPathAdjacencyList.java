package myStudy.graphtheory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;


//Dijkstras :: Priority Queue 를 이용하여  negative weight edge가 없는 graph에서
// SSSP를 bfs 방식으로 찾는 알고리듬!
public class DijkstrasShortestPathAdjacencyList {

	private final static double EPS = 1e-6;
	
	private static class Edge{
		
		int from, to;
		double cost;
		
		
		public Edge(int from, int to, double cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
	}
	
	
	private static class Node{
		int id;
		double value;
		
		public Node(int id, double value) {
			this.id = id;
			this.value = value;
		}
	}
	
	private int n;
	private Integer[] prev;
	private List<List<Edge>> graph;
	private double[] dist;
	
	private Comparator<Node> comparator =
		new Comparator<Node>() {
			@Override
			public int compare(Node node1, Node node2) {
				if (Math.abs(node1.value - node2.value) < EPS) return 0;
				return (node1.value - node2.value) > 0 ? +1 : -1;
			}

	};
	
	
	public DijkstrasShortestPathAdjacencyList(int n) {
		this.n = n;
		createEmptyGraph();
	}
	
	
	public DijkstrasShortestPathAdjacencyList(int n , Comparator<Node> comparator) {
		this(1);
		if(comparator == null) throw new IllegalArgumentException("comparatorn could not be null");
		this.comparator = comparator;
	}
	
	public void addEdge(int from, int to , double cost) {
		List<Edge> edges = graph.get(from);
		edges.add(new Edge(from,to,cost));
	}
	
	public List<List<Edge>> getGraph(){
		return graph;
	}
	
	public List<Integer> reconstructPath(int start, int end){
		if(start<0 || start>n || end<0 || end>n) throw new IllegalArgumentException("invalid start or end value");
		
		double dist = dijkstras(start, end);
		if (dist == Double.POSITIVE_INFINITY) return null;
		List<Integer> path =  new ArrayList<>();
		for(Integer at = end; at!=null ; at = prev[at]) path.add(at);
		Collections.reverse(path);
		return path;
	}
	
	public double dijkstras(int start, int end) {
		
		prev = new Integer[n];
		dist = new double[n];
		boolean[] visited = new boolean[n];
		Arrays.fill(dist, Double.POSITIVE_INFINITY);
		dist[start] = 0;
		PriorityQueue<Node> pq = new PriorityQueue<>(n*2,comparator);
		pq.offer(new Node(start,0));
		
		while(!pq.isEmpty()) {
		
			Node node = pq.poll();
			visited[node.id] = true;
			
			//어차피 구하는건 end까지고 , end까지의 최소거리 나왔는데 굳이 end의 edge를 또 돌려야하는가?
			if(node.id ==end) return dist[end];
			
			if(dist[node.id] < node.value) continue;
			
			List<Edge> edges = graph.get(node.id);
			if( edges != null) {
				for(Edge edge : edges) {
					if(visited[edge.to]) continue;
					
					double newDist = dist[edge.from] + edge.cost;
					if(newDist < dist[edge.to]) {
						dist[edge.to] = newDist;
						prev[edge.to] = edge.from;
					}
					pq.offer(new Node(edge.to, dist[edge.to]));
					}
			}
			//william fiset은 여기다가 return해줬는데..!
//			if(node.id ==end) return dist[end];
		}
		//end에 도달 못하면 infinite Value출력됨.
		return dist[end];
	}
	
	public void createEmptyGraph() {
		
		graph = new ArrayList<List<Edge>>(n);
		for(int i=0; i<n; i++) graph.add(i, new ArrayList<Edge>());
	}
	
// ==================TEST==================	
	  public static void main(String[] args) {

		  final int N =7;
		  DijkstrasShortestPathAdjacencyList ds = new DijkstrasShortestPathAdjacencyList(7);
		  
		  ds.addEdge(0, 1, 3);
		  ds.addEdge(0, 2, 2);
		  ds.addEdge(0, 5, 3);
		  ds.addEdge(1, 3, 6);
		  ds.addEdge(1, 2, 1);
		  ds.addEdge(2, 3, 1);
		  ds.addEdge(2, 4, 10);
		  ds.addEdge(3, 4, 5);
		  ds.addEdge(5, 4, 7);
		  
		  System.out.println(ds.dijkstras(1, 4));
		  System.out.println(ds.reconstructPath(1, 4).toString());

		  
	}
	
}
