package myStudy.graphtheory.network;

public class FordFulkersonDFSAdjacencyMatrix {

	static int visitedToken = 1;
	
	public static int fordFulkerson(int[][] caps, int source, int sink) {
		int n  = caps.length;
		int[] visited = new int[n];
//		boolean[] minCut = new boolean[n];
		
		for( int maxFlow = 0 ; ; ) {
			
		int flow = dfs(caps,visited,source,sink,Integer.MAX_VALUE);
		visitedToken++;
		
		maxFlow += flow;
		if(flow == 0) return maxFlow;
		
		}
	
	}

	private static int dfs(int[][] caps, int[] visited, int node, int sink,
			int flow) {
		
		if(node == sink) return flow;
		
		int[] cap = caps[node];
		visited[node] = visitedToken;
		
		for(int i=0; i<cap.length; i++) {
			if( visited[i]!=visitedToken && cap[i]>0) {
				int bottleNeck = dfs(caps, visited, i, sink, Math.min(flow, cap[i]));
				
				if(bottleNeck > 0) {
					caps[node][i] -= bottleNeck;
					caps[i][node] += bottleNeck;
					return bottleNeck;
				}
			}
		}
		
		return 0;
	}
	
	public static void main(String[] args) {
		int n = 6;
		int s = n-2;
		int t = n-1;
		
		int[][] caps = new int[n][n];
		
		caps[4][1] = 10;
		caps[4][0] = 10;
		caps[1][3] = 15;
		caps[3][0] = 6;
		caps[0][2] = 25;
		caps[3][5] = 10;
		caps[2][5] = 10;
		
		int maxFlow = fordFulkerson(caps,s,t);
		System.out.println(maxFlow);
		
	}
	
	
}
