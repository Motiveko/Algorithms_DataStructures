package myStudy.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

// https://www.baeldung.com/java-graphs
// 구현은 뻔한데 모르던 다양한 매서드를 사용한다
public class Graph {

	class Vertex {
		String label;
		
		public Vertex(String label) {
			this.label = label;
		}

		@Override
		public int hashCode() {
			return label.hashCode();
		}

		// equals와 hash코드를 구현해야 쓸 수 있는데 맞게 하지 못한듯..
		@Override
		public boolean equals(Object obj) {
			if(!obj.getClass().isInstance(this)) return false;
			if(this.toString().equals(obj.toString())) return true;
			
			return false;
		}

	}
	
	
	private Map<Vertex, List<Vertex>> adjVerticies;
	
	public Graph() {
		adjVerticies = new HashMap<>();
	}

	public void addVertex(String label) {
		//contains(vertex) 로 검사하지 않아도 된다.
		adjVerticies.putIfAbsent(new Vertex(label), new ArrayList<>());
	}
	
	public void removeVertex(String label) {
		Vertex v = new Vertex(label);
		// Map의 key값 vertex 외에도 다른 vertex에서 edge로 연결되어있는것도 다 지워줘야한다, forEach로 List전체 훑어서 지운다
		adjVerticies.values().stream().forEach( e -> e.remove(v));
		//
		adjVerticies.remove(v);
		
	}
	
	public void addEdge(String label1, String label2) {
		//Undirected Graph 
		Vertex v1 = new Vertex(label1);
		Vertex v2 = new Vertex(label2);
		
		if(adjVerticies.get(v1) ==null) {
			System.out.println(label1 + "이(가) 들어간 vertex를 찾을수가 없습니다.");
			return;
		}
		
		adjVerticies.get(v1).add(v2);
		adjVerticies.get(v2).add(v1);
	}
	
	public void removeEdge(String label1, String label2	) {
		
		Vertex v1 = new Vertex(label1);
		Vertex v2 = new Vertex(label2);
		
		adjVerticies.get(v1).remove(v2);
		adjVerticies.get(v2).remove(v1);
	}
	
	
	
	
	public Map<Vertex, List<Vertex>> getAdjVerticies() {
		return adjVerticies;
	}
	public void setAdjVerticies(Map<Vertex, List<Vertex>> adjVerticies) {
		this.adjVerticies = adjVerticies;
	}
	
	public void showGraph() {
		Set<Vertex> keys = adjVerticies.keySet();
		Iterator<Vertex> itr = keys.iterator();
		
		while(itr.hasNext()) {
			Vertex node = itr.next();
			System.out.print(node.label + "은 ");
			
			ArrayList<Vertex> edges = (ArrayList<Vertex>) adjVerticies.get(node);
			edges.stream().forEach(e -> System.out.print(e.label + ", "));
			System.out.print("과 연결되어 있습니다.");
			System.out.println();
		}
		
		
	}
	public static void main(String[] args) {
		
		Graph graph = new Graph();
		
		graph.addVertex("서울");
		graph.addVertex("부산");
		graph.addVertex("대구");
		graph.addVertex("광주");
		graph.addVertex("대전");
		
		graph.addEdge("서울", "부산");
		graph.addEdge("부산", "대구");
		graph.addEdge("대구", "서울");
		graph.addEdge("광주", "대전");
		graph.addEdge("서울", "대전");
		
		graph.showGraph();

	}
	

}
