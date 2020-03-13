package myStudy.algorithms.serch;

import java.util.function.DoubleFunction;

/*
 * binary Search와 다르게 ternary search는 2차함수 같은 증가-감소 / 감소-증가 형태의함수에서(unimodal function)
 * 꼭지점을 찾는 기술이다. 둘 중 한가지씩만 구현이 가능하다!
 * 만약 [lo,hi] 안에 꼭지점이 없으면 어느 한쪽으로 수렴하는방향으로 실행된다.
 */
public class TernarySearch {

	static final double EPS = 0.000000001;
	
	
	public static double ternarySearch(double lo, double hi, DoubleFunction<Double> function) {
		
		Double best = null;
		int count=0;
		while(true) {
			double mid1 = (2*lo + hi) / 3, mid2 = (lo + 2*hi) / 3;
			double res1 = function.apply(mid1), res2 = function.apply(mid2);
			//U자형태 그래프 일때는 res가 큰값을 기준으로 lo/hi 재설정하고 반대는 작은값 기준으로 lo/hi 재설정
			if(res1 > res2) lo = mid1;
			else hi = mid2;
			//best를 mid1 이던 mid2던 상관없다.
			if( best != null && Math.abs(best - mid2) < EPS) break;
			
			best = mid2;
			count++;
		}
		System.out.println(count);
		return best;
	}
	
	public static void main(String[] args) {
		
		DoubleFunction<Double> function = (x) -> (x*x + 3*x + 5);
		
		double root = ternarySearch(-100, 100, function);
		
		//count = 60;
		System.out.printf("%.4f\n",root);
	}
	
	
}
