package myStudy.graph;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

//Directed
public class GraphWithMatrix {
	
	int vertex;
	int[][] matrix;
	
	public GraphWithMatrix(int vertex) {
		this.vertex = vertex;
		matrix = new int[vertex][vertex];
	}
	
	public void addEdge(int start, int end) {
		if(start<0 || start>=vertex || end<0 || end>=vertex) throw new IndexOutOfBoundsException("invalid node value");
		if(start == end) return;
		if(matrix[start][end]!=0) return;
		matrix[start][end] = 1;
	}
	
	public void removeEdge(int start, int end) {
		if(start<0 || start>=vertex || end<0 || end>=vertex) throw new IndexOutOfBoundsException("invalid node value");
		if(start == end) return;
		matrix[start][end] = 0;
	}
	
	
	public void reverseGraph() {
		LinkedList<Integer>[] tmp = new LinkedList[vertex];
		//find and remove edges and store the reversed node index 
		for(int i = 0 ; i<vertex; i++) {
			for(int j=0; j<vertex; j++) {
				if(matrix[i][j]==1) {
					matrix[i][j]=0;
					LinkedList<Integer> list = tmp[j];
					if(list==null) list = new LinkedList<Integer>();
					list.add(i);
					tmp[j] = list;
				}
			}
		}
		// load the index, connect it
		for(int i = 0; i<vertex; i++) {
			if(tmp[i]!=null) {
				LinkedList<Integer> list= tmp[i];
				while(list.peek()!=null) {
					matrix[i][list.poll()] = 1;
				}
			}
		}
		
	}
	public void showGraph() {
		
		for(int i=0; i<vertex;i++) {
			boolean empty = true;
			for(int j=0; j<vertex; j++) {
				if(matrix[i][j]!=0) empty = false;
			}
			if(!empty){
				System.out.print("Vertex " + i + " is Directing to : ");
				for(int j=0; j<vertex; j++) {
					if(matrix[i][j]!=0) System.out.print("'" + j + "', ");
				}
				System.out.println();
			}

		}
	}
	
	public static void main(String[] args) {
		
		GraphWithMatrix graph = new GraphWithMatrix(100);
		
		graph.addEdge(1, 7);
		graph.addEdge(55, 2);
		graph.addEdge(1, 3);
		graph.addEdge(44, 99);
		graph.addEdge(1, 3);
		graph.addEdge(55, 1);
		graph.addEdge(9, 3);
		
		graph.showGraph();
		System.out.println("======reverse the graph======");
		graph.reverseGraph();
		graph.showGraph();

	}
	
}
