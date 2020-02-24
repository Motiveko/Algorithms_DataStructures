package myStudy.graphtheory;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

//Matrix는  List방식과 다르게 n*n 메트릭스 전체를 스캔하므로
//존재하지 않는 edge를 계산하게된다. 비효율적이다
public class BellmanFordAdjacencyMatrix {

	private int n,start;
	private double[][]matrix;
	private boolean solved;
	private Integer[] prev;
	private double[] dist;
	
	public BellmanFordAdjacencyMatrix(double[][] matrix, int start) {
		if(start<0 || start>=matrix.length) throw new IllegalArgumentException("invalid start value, start : " + start);
		this.start = start;
		n = matrix.length;
		this.matrix = new double[n][n];

		for(int i = 0; i < n ; i++) 
			this.matrix[i] = matrix[i].clone();
	}
	
	public double[] getShortestPath() {
		if(!solved)	solve();
		return dist;
	}
	
	
	public List<Integer> reconstructPath(int end) {
		if(end<0 || end>=n) throw new IllegalArgumentException("invalid end value");
		LinkedList<Integer> path = new LinkedList();
		
		if (Double.isInfinite(dist[end])) return path;
		
		for( Integer at = end; at!=null; at=prev[at]) {
			if(prev[at]!=null && prev[at] == -1) return null;
			path.addFirst(at);
		}
		
		return path;
	}
	
	
	private void solve() {
		
		prev = new Integer[n];
		dist = new double[n];
		Arrays.fill(dist, Double.POSITIVE_INFINITY);
		dist[start] = 0;
		
		boolean relaxedAnEdge = true;
		
		//Relaxing Edge
		for(int k=0; k<n-1 && relaxedAnEdge; k++) {
			relaxedAnEdge = false;
			for(int i=0; i<n; i++)
				for(int j=0; j<n; j++) {
					if( dist[i] + matrix[i][j] < dist[j]) {
						dist[j] = dist[i] + matrix[i][j];
						relaxedAnEdge = true;
						prev[j] = i;
					}
				}
		}
			
		// Find Negative Cycle
		for(int k=0; k<n-1 && relaxedAnEdge; k++) {
			relaxedAnEdge = false;
			for(int i=0; i<n; i++)
				for(int j=0; j<n; j++) {
					if( dist[i] + matrix[i][j] < dist[j]) {
						dist[j] = Double.NEGATIVE_INFINITY;
						relaxedAnEdge = true;
						prev[j] = -1;
					}
				}	
		}	
	}
	
	public static void main(String[] args) {
		
	    int n = 9;
	    double[][] graph = new double[n][n];
		
	    // Setup Completely disconnected Graph with distance Positive infinite,
	    // from a node to itself to be 0
	    for (int i = 0; i < n; i++) {
	        java.util.Arrays.fill(graph[i], Double.POSITIVE_INFINITY);
	        graph[i][i] = 0;
	     }
	    

	    graph[0][1] = 1;
	    graph[1][2] = 1;
	    graph[2][4] = 1;
	    graph[4][3] = -3;
	    graph[3][2] = 1;
	    graph[1][5] = 4;
	    graph[1][6] = 4;
	    graph[5][6] = 5;
	    graph[6][7] = 4;
	    graph[5][7] = 3;
	    
	    int start = 0;
	    BellmanFordAdjacencyMatrix solver =
	    		new BellmanFordAdjacencyMatrix(graph, start);
	    
	    double[] shortestPath = solver.getShortestPath();
	    
	    for(int i =0; i<n; i++) {
	    	System.out.printf("The distance from %d to %d is %2.1f \n",start,i,shortestPath[i]);
	    }
	    
	    
	    for(int i=0 ; i<n ; i++) {
		    List<Integer> path = solver.reconstructPath(i);
	    	System.out.printf("Path from %d to %d is : \n[",start,i);
	    	
	    	if(Double.isFinite(shortestPath[i])) {
//		    	String str = String.join(
//		    			"->",path.stream().map(x->Integer.toString(x)).collect(java.util.stream.Collectors.toList()));
		    	String str = String.join(
		    			"->", path.stream().map(Object::toString).collect(java.util.stream.Collectors.toList()));
		    	System.out.println(str + "]");
	    	} else {
	    		System.out.println("Infinite number of shortest paths. ]");
	    	}
	    		
	    }
	    
	}

}
