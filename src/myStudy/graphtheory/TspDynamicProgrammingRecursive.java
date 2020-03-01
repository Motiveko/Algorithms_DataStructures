package myStudy.graphtheory;

import java.util.*;
/* MY CODE BY YOUTUBE SUDO CODE
 * 
public class TspDynamicProgrammingRecursive {
	
	private final int N;
	private final int START_NODE;
	private final int FINISHED_STATE;
	
	private double[][] distance;
	private double minTourCost = Double.POSITIVE_INFINITY;
	
	private ArrayList<Integer> tour;
	private boolean ranSolver = false;
	
	public TspDynamicProgrammingRecursive(double[][] distance) {
		this(0,distance);
	}
	public TspDynamicProgrammingRecursive(int startNode, double[][] distance) {
		this.distance = distance;
		N = distance.length;
		START_NODE = startNode;
		
		if( N<= 2 ) throw new IllegalStateException("TSP on 0, 1 or 2 nodes dosen't make sense.");
		if(N != distance[0].length)
			throw new IllegalArgumentException("Matrix must be squere(N x N");
		if(N > 32)
			throw new IllegalArgumentException(" 슈퍼컴퓨터를 가져오세요! 	");
		//N-1개만큼 1인 상태;
		FINISHED_STATE = (1 << N ) -1;
	}
	public List<Integer> getTour(){
		
		if(!ranSolver) solve();
		return tour;
	}
	
	public double getTourCost() {
	   if (!ranSolver) solve();
	   return minTourCost;
	}
	
	//여기서 부터 직접 해보고 코드리뷰 해보
	private void solve() {
		
		double memo[][] = setUp();
		tsp(memo);
		minTourCost = findMinCost(distance, memo, START_NODE);
		findOptimalTour( distance, memo, START_NODE);
		
		ranSolver = true;
	}
	
	private double[][] setUp() {
	
		double[][] memo = new double[N][(int) Math.pow(2, N)];
		
		//씨부엉~~이거안해서계속 초기값 반영안됬쥬~!
		int rep = 1<<START_NODE;
		
		for(int i=0; i<N; i++) {
			if(i == START_NODE) continue;
			int newrep =  rep | (1 << i);
			memo[i][newrep] = distance[START_NODE][i];
		}
		return memo;
	}
	
	private void tsp(double[][] memo) {
		
		for(int r= 3 ; r<=N; r++) {
			int[] subsets = combination(r,N);
			for( int subset : subsets) {

				if(subset ==0) break;
				
				for(int next = 0; next<N; next++) {
					if(next == START_NODE || notIn(next,subset)) continue;
					
					int state = subset ^ (1<<next);
					double minDist = Double.POSITIVE_INFINITY;
					for(int e =0; e<N; e++){
						if( e==START_NODE || e==next || notIn(e,state)) continue;
						
						double newDist = memo[e][state] + distance[e][next];
						if( minDist > newDist) {
							memo[next][subset] = newDist;
							minDist = newDist;
						}
					}
				}
			}
		}
	}
	
	private boolean notIn(int i, int subset) {
		return ((1<<i) & subset) ==0;
	}
	
	private double findMinCost(double[][] distance, double[][]memo, int START_NODE) {
		
		//i == final destination
		int finalNode = 0;
		for(int i=0; i<N; i++) {
			if(i == START_NODE) continue;	
			double tourCost = memo[i][FINISHED_STATE] + distance[i][START_NODE];
			if(minTourCost > tourCost) {
				minTourCost = tourCost; finalNode = i;
			}
		}
//		System.out.println(finalNode);
		return minTourCost;
	}
	
	
	private void findOptimalTour( double[][] distance, double[][] memo, int START_NODE){
		
		tour = new ArrayList<Integer>();
		int lastIndex = START_NODE;
		tour.add(START_NODE);
		
		int state = FINISHED_STATE;
		for(int i=N-1; i>=1; i--) {
			
			int index = -1;
			for(int j=0; j<N; j++) {
				if(j==START_NODE || notIn(j,state)) continue;
				if(index == -1) index = j;
				double prevDist = memo[index][state] + distance[index][lastIndex];
				double newDist = memo[j][state] + distance[j][lastIndex];
				
				if(prevDist > newDist) index = j;
			}
			//단계별 distance 출
//			System.out.println( Math.abs(i-N) +  " :" + distance[index][lastIndex]);
			tour.add(index);
			state = state ^ (1 <<index);
			lastIndex = index;
		}
		tour.add(START_NODE);

	}
	
	
	private int[] combination(int r, int N) {
		int subsets[] = new int[ 1<<N];
	
		combination(0,0,r,N,subsets);
		return subsets;
	}
	
	private void combination(int set, int at, int r,int N,  int[] subsets) {
		
		if(r==0) {
			int i=0;
			while(subsets[i]!=0) i++;
			subsets[i] = set;
			return;
		};//subsets [] = set
			
		
		for(int i=at; i < N ; i++) {
			
			//i번째 1켜기
			set = set | (1 << i) ;
			
			combination(set, i+1, r-1, N,  subsets);
			
			//combination 방출이 끝났으므로 i번째 1 다시 끄기	
			set = set & ~(1 << i);
		}
	}
	
	//Combination 작동 여부 테스트!
	public void testCombination() {
		int r=3;
		int[] subsets = combination(4,10);
		int i = 0;
		for(int subset : subsets) {
			if(subset!=0)System.out.println(i++ + " : " + subset);
		}
	}
	
	
	
	public static void main(String[] args) {
		
		int n = 6;
		double[][] distanceMatrix = new double[n][n];
	    for (double[] row : distanceMatrix) java.util.Arrays.fill(row, 10000);
	    distanceMatrix[1][4] = distanceMatrix[4][1] = 2;
	    distanceMatrix[4][2] = distanceMatrix[2][4] = 4;
	    distanceMatrix[2][3] = distanceMatrix[3][2] = 6;
	    distanceMatrix[3][0] = distanceMatrix[0][3] = 8;
	    distanceMatrix[0][5] = distanceMatrix[5][0] = 10;
	    distanceMatrix[5][1] = distanceMatrix[1][5] = 12;
	    
	    TspDynamicProgrammingRecursive solver = new TspDynamicProgrammingRecursive(distanceMatrix);
	    



	    // Print: 42.0
	    System.out.println("Tour cost: " + solver.getTourCost());
	    // Prints: [0, 3, 2, 4, 1, 5, 0]
	    System.out.println("Tour: " + solver.getTour());

	    //	    solver.testCombination();
	    
	}
}

*/


//WILLIAM FISET SOURCE CODE
//BETTER THAN MINE
public class TspDynamicProgrammingRecursive {
	
	private final int N;
	private final int START_NODE;
	private final int FINISHED_STATE;
	
	private double[][] distance;
	private double minTourCost = Double.POSITIVE_INFINITY;
	
	private List<Integer> tour ;
	private boolean ranSolver;
	
	public TspDynamicProgrammingRecursive(double[][] distance) {
		this(0,distance);
	}
	
	public TspDynamicProgrammingRecursive(int start, double[][] distance){
		
		
		N = distance.length;
		START_NODE = start;
		FINISHED_STATE = (1 << N) - 1;
		this.distance = distance;
		tour = new ArrayList();
		if(N <= 2) throw new IllegalStateException(" node less than 2 is not make sense!");
		if(start <0 || start > N) 
			throw new IllegalArgumentException("Starting is invalid");
		if(N>32)
			throw new IllegalArgumentException("Give me the SuperComputer");
	}
	
	public List<Integer> getTour(){
		if(!ranSolver) solve();
		return tour;
	}
	public double getTourCost() {
		if(!ranSolver) solve();
		return minTourCost;
	}
	
	
	public void solve() {
	
		int state = 1 << START_NODE;
		Double[][] memo = new Double[N][1<<N];
		Integer[][] prev = new Integer[N][1<<N];
		minTourCost = tsp(START_NODE, state, memo, prev);
		
		int index = START_NODE;
		while(true) {
			tour.add(index);
			Integer nextIndex = prev[index][state];
			if(nextIndex==null) break;
			int nextState = state | (1<<nextIndex);
			state = nextState;
			index = nextIndex;
		}
		tour.add(START_NODE);
		ranSolver = true;
	}
	
	
	public double tsp(int i, int state, Double[][] memo, Integer[][] prev) {
		
		if(state == FINISHED_STATE) return distance[i][START_NODE];
		
	    // Return cached answer if already computed.
		// 무슨의미지? 어차피 recursive 하게 반복될 때 중복되는 연산은 없는데?
	    if (memo[i][state] != null) return memo[i][state];
	    
		double minCost = Double.POSITIVE_INFINITY;
		int index= -1;

		
		for(int next=0; next<N; next++) {
			if( (state & (1<<next)) != 0) continue;
			int newState = state | (1<<next) ;
			double newCost = distance[i][next] + tsp(next, newState, memo, prev);
			if( minCost > newCost) {
				minCost = newCost;
				index = next;
			}
		}
		prev[i][state] = index;
		//memo[두번째노][Start State]인데, minCost는 최종 tourCost이다
		//의미가 맞지도 않는거 같기도 할 뿐더러 이 알고리즘에서 사실 memo가없어도 잘만 돌아간다.
		return memo[i][state] = minCost;
	}
	
	public static void main(String[] args) {

	    // Create adjacency matrix
	    int n = 6;
	    double[][] distanceMatrix = new double[n][n];
	    for (double[] row : distanceMatrix) java.util.Arrays.fill(row, 10000);
	    distanceMatrix[1][4] = distanceMatrix[4][1] = 2;
	    distanceMatrix[4][2] = distanceMatrix[2][4] = 4;
	    distanceMatrix[2][3] = distanceMatrix[3][2] = 6;
	    distanceMatrix[3][0] = distanceMatrix[0][3] = 8;
	    distanceMatrix[0][5] = distanceMatrix[5][0] = 10;
	    distanceMatrix[5][1] = distanceMatrix[1][5] = 12;

	    // Run the solver
	    TspDynamicProgrammingRecursive solver = new TspDynamicProgrammingRecursive(distanceMatrix);

	    // Prints: [0, 3, 2, 4, 1, 5, 0]
	    System.out.println("Tour: " + solver.getTour());

	    // Print: 42.0
	    System.out.println("Tour cost: " + solver.getTourCost());
	  }
	
	
}