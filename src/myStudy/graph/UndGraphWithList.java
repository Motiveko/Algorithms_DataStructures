package myStudy.graph;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

// https://algorithms.tutorialhorizon.com/graph-implementation-adjacency-list-better-set-2/
public class UndGraphWithList {

	int vertex;
	LinkedList<Integer>[] list;
	
	public UndGraphWithList(int vertex) {
		
		this.vertex = vertex;
		list = new LinkedList[vertex];
		
		for(int i=0 ; i<vertex; i++) {		
			list[i] = new LinkedList<>();
		}
	}
	
	public void addEdge(int source, int destination) {
		
		if(source <0 || source > vertex || destination<0 || destination > vertex) throw new IndexOutOfBoundsException("node가 유효하지 않습니다");
		//중복된 값 방지
		if(!list[source].contains(destination)) {
			list[source].addLast(destination);
			list[destination].addLast(source);
		}
	}
	
	public void removeEdge(int source, int destination) {
		if(source <0 || source > vertex || destination<0 || destination > vertex) throw new IndexOutOfBoundsException("node가 유효하지 않습니다");
		
		if(!list[source].contains(destination)) return;
		
		//remove() 는 내부에 int형태가 들어가면 index로 인식하므로 object로 인식할 수 있게 만들어준다
		list[source].remove((Integer) destination);
		list[destination].remove((Integer) source);
		
	}
	
	public void showGraph() {
		
		for(int i = 0; i<vertex; i++) {
			if(!list[i].isEmpty()) {
				LinkedList<Integer> node = list[i];
				Iterator<Integer> itr = node.iterator();
				System.out.print("Vertex " + i + " is Connected to : ");
				while(itr.hasNext()) {
					System.out.print( "'" + itr.next()+"', ");
				}
				System.out.println();
			}
		}
	}
	
	
	public static void main(String[] args) {
		
		UndGraphWithList graph = new UndGraphWithList(100);
		
		
		graph.addEdge(5, 7);
		graph.addEdge(1, 4);
		graph.addEdge(10, 3);
		//graph.addEdge(7, 5);
		graph.removeEdge(10, 3);

		graph.showGraph();

	}
	

}
