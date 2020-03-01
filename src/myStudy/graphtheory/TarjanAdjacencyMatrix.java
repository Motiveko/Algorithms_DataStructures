package myStudy.graphtheory;

import java.util.*;

public class TarjanAdjacencyMatrix {

	private int n, id; 
	private int sccCount;
	private int[] ids, low;
	private boolean[] onStack;
	private boolean solved;
	private Deque<Integer> stack;
	private int[][] matrix;
	
	
	private final static int UNVISITED = -1;
	
	
	public TarjanAdjacencyMatrix(int[][] matrix) {
		if (matrix==null) throw new IllegalArgumentException("matrix can't be null!");
		
		n = matrix.length;
		this.matrix = matrix;
	}
	
	public int sccCount() {
		if(!solved) solve();
		return sccCount;
	}
	
	public int[] getSccs() {
		if(!solved) solve();
		return low;
	}
	
	public void solve() {
		
		ids = new int[n];
		low = new int[n];
		onStack = new boolean[n];
		Arrays.fill(ids, UNVISITED);
		stack = new ArrayDeque<>();
		
		for(int i=0; i<n; i++) {
			if(ids[i]==UNVISITED) dfs(i);
		}
		
		solved = true;
	}
	
	private void dfs(int at) {
		
		stack.push(at);
		onStack[at] = true;
		ids[at] = low[at] = id++;
		
		for(int to=0; to<n; to++) {
			//not an Edge
			if(matrix[at][to]==0) continue;
			
			if(ids[to]==UNVISITED) dfs(to);
			if(onStack[to]==true) low[at] = Math.min(low[at], low[to]);
			
		}
		
		if(ids[at]==low[at]) {
			while(!stack.isEmpty()) {
				int node= stack.pop();
				onStack[node] = false;
				if(at==node) break;
			}
			sccCount++;
		}
	}
	
	public static void main(String[] args) {
		

	    final int NUM_NODES = 10;

	    int[][] adjMatrix = new int[NUM_NODES][NUM_NODES];

	    // SCC 1 with nodes 0,1,2
	    adjMatrix[0][1] = 1;
	    adjMatrix[1][2] = 1;
	    adjMatrix[2][0] = 1;

	    // SCC 2 with nodes 3,4,5,6
	    adjMatrix[5][4] = 1;
	    adjMatrix[5][6] = 1;
	    adjMatrix[3][5] = 1;
	    adjMatrix[4][3] = 1;
	    adjMatrix[4][5] = 1;
	    adjMatrix[6][4] = 1;

	    // SCC 3 with nodes 7,8
	    adjMatrix[7][8] = 1;
	    adjMatrix[8][7] = 1;

	    // SCC 4 is node 9 all alone by itself
	    // Add a few more edges to make things interesting
	    adjMatrix[1][5] = 1;
	    adjMatrix[1][7] = 1;
	    adjMatrix[2][7] = 1;
	    adjMatrix[6][8] = 1;
	    adjMatrix[9][8] = 1;
	    adjMatrix[9][4] = 1;
	    
	    TarjanAdjacencyMatrix solver = new TarjanAdjacencyMatrix(adjMatrix);
	    int sccCount = solver.sccCount();
	    int[] low = solver.getSccs();
	    Map<Integer, List<Integer>> map = new HashMap<>();
	    
	    for(int i=0; i<NUM_NODES; i++) {
	    	if(!map.containsKey(low[i])) map.put(low[i], new ArrayList<>());
	    	map.get(low[i]).add(i);
	    }
	    int[] index=new int[10];
	    for(int i=0; i<10; i++) index[i] = i;
	    
	    System.out.println(Arrays.toString(index));
	    System.out.println(Arrays.toString(solver.ids));
	    System.out.println(Arrays.toString(low));
	    
	    System.out.println("Strong Connected Component Count : " + sccCount	);
	    for(List scc : map.values())
	    	System.out.println("Node " + scc.toString() + " is Strongly Connected");
	    
	}
	
}
