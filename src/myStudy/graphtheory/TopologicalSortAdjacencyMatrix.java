package myStudy.graphtheory;

public class TopologicalSortAdjacencyMatrix {

									// Matrix 가 제너릭으로 선언되었으므로 초기값은 null;
	public static int[] topologicalSort(Double[][] matrix) {
		
		int n = matrix.length;
		boolean[] visited = new boolean[n];
		int[] order = new int[n];
		
		int i = n-1;
		
		for(int at = 0; at<n; at++)
			if(!visited[at]) i = visit(at, i, visited, order, matrix); 
		
		return order;
	}
	
	public static int visit( int at , int i , boolean[] visited, int[] order, Double[][] matrix	) {
		
		visited[at] = true;
		int n = matrix.length;
		for(int k=0;k<n;k++) {
			if(matrix[at][k]!=null && !visited[k])			
				i = visit( k,i,visited,order,matrix);
		}
		
		order[i--] = at;
		
		return i; 
	}
	
	public static Double[] dagShortestPath(Double[][] matrix, int start) {
		int n= matrix.length;
		int[] order = topologicalSort(matrix);
		Double[] dist = new Double[n];
		dist[start] = (double) 0;
		
		for(int i=0; i<n; i++) {
			int index= order[i];
			if(dist[index]!=null) {
				for(int k=0; k<n; k++)
					if(matrix[index][k]!=null) {
						
						double newDist = dist[index] + matrix[index][k];
						if(dist[k]==null) dist[k] = newDist;
						else dist[k] =  Math.min(newDist, dist[k]);
					}

			}
		}
			
		return dist;
	}

public static void main(String[] args) {
	 final int N = 7;
	    Double[][] adjMatrix = new Double[N][N];

	    adjMatrix[0][1] = 3.0;
	    adjMatrix[0][2] = 2.0;
	    adjMatrix[0][5] = 3.0;

	    adjMatrix[1][3] = 1.0;
	    adjMatrix[1][2] = 6.0;

	    adjMatrix[2][3] = 1.0;
	    adjMatrix[2][4] = 10.0;

	    adjMatrix[3][4] = 5.0;

	    adjMatrix[5][4] = 7.0;

	    int[] ordering = topologicalSort(adjMatrix);

	    // Prints: [6, 0, 5, 1, 2, 3, 4]
	    System.out.println(java.util.Arrays.toString(ordering));
	    
	    // Find the shortest path from 0 to all other nodes
	    Double[] dists = dagShortestPath(adjMatrix, 0);

	    // Find the distance from 0 to 4 which is 8.0
	    System.out.println(dists[4]);

	    // Finds the shortest path from 0 to 6
	    // prints Infinity (6 is not reachable!)
	    System.out.println(dists[6]);

	}	
}
