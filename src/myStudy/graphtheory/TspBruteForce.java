package myStudy.graphtheory;

/*
 * TSP problem 에서는 출발하는 순서는 중요하지 않으므로 출발지점을 정해놓고 시작한다( 어차피 cycle이기때문)
 * BruteForce 방식은 출발지점에서부터 모든 permutation을 구하고 각각의 tourCost를 계산해서 최소값을 계속해서 갱신해나간다
 * Permutation을 구현하는 방식은 여기서는 먼저 값들을 오름차순 정렬한 후
 * 내림차순이 될 때 까지 계속 스왑하는 방식으로 전개한다.
 */
public class TspBruteForce {

	
	public static int[] tsp(double[][] matrix) {
		
		int n= matrix.length;
		int[] permutation = new int[n];
		for(int i=0; i<n ; i++) permutation[i] = i;
		int[] bestTour = permutation.clone();
		double bestTourCost = Double.POSITIVE_INFINITY;
		
		do {
			
			double tourCost = computeTourCost(permutation,matrix);
			if(bestTourCost > tourCost) {
				bestTourCost = tourCost;
				bestTour = permutation.clone();
			}
			
		} while(nextPermutation(permutation));
		
		return bestTour;
	}
	
	public static double computeTourCost(int[] permutation, double[][] matrix) {
		
		double cost = 0;
		
		for(int i= 1; i<permutation.length; i++) {
			if( matrix[permutation[i-1]][permutation[i]] == Double.POSITIVE_INFINITY) return Double.POSITIVE_INFINITY;
			cost += matrix[permutation[i-1]][permutation[i]];
		}
		//최종 도착 지점에서 0으로 돌아오는
		double returnCost = matrix[permutation[permutation.length-1]][0];
		return cost + returnCost;
	}
	
	
	public static boolean nextPermutation(int[] sequence) {
		
		int first = getFirst(sequence);
		if(first == -1) return false;
		int toSwap = sequence.length-1;
		while(sequence[first] >= sequence[toSwap]) toSwap--;
		swap(sequence, first++, toSwap);
		
		toSwap = sequence.length - 1;
		while(first < toSwap) swap(sequence, first++, toSwap--);
		return true;
	}
	private static int getFirst(int[] sequence) {
		for(int i = sequence.length-2 ; i>=0; i--) {
			if(sequence[i] < sequence[i+1]) return i;
		}
		
		return -1;
	}
	
	public static void swap(int[] sequence, int i, int j) {
		
		int tmp = sequence[i];
		sequence[i] = sequence[j];
		sequence[j] = tmp;
	}
	
	
	public static void main(String[] args) {
		
		int n = 10;
		
		double[][] matrix = new double[n][n];
		for(double[] row : matrix) java.util.Arrays.fill(row, Double.POSITIVE_INFINITY);
		
		int edgeCost = 5;
		int[] optimalTour = {2, 7, 6, 1, 9, 8, 5, 3, 4, 0, 2};
		for(int i=1; i < optimalTour.length; i++) 
			matrix[optimalTour[i - 1]][optimalTour[i]] = edgeCost;
		
		int[] bestTour = tsp(matrix);
	    System.out.println(java.util.Arrays.toString(bestTour));

	    double tourCost = computeTourCost(bestTour, matrix);
	    System.out.println("Tour cost: " + tourCost);
		
		
		
	}
}
