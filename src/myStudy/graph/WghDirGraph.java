package myStudy.graph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


//Weighted Directed Graph
public class WghDirGraph {

	class Edge{
		int from, to, cost;
		
		public Edge(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
	}
	
	
	
	Map<Integer,List<Edge>> graph =  new HashMap<>();
	
	public WghDirGraph() {
	
	}
	
	public void addEdge(int from, int to , int cost) {
		
		
	}
	
}
