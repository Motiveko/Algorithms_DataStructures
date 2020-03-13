package myStudy.algorithms.serch;

import java.util.function.DoubleFunction;
/*
 * binary search는 값이 증가하거나 감소하는 한가지 경향만 가진 함수에서 해를 찾는 그래프이다!
 * sqrt값이나 구의 부피에 따른 r값 찾는것들이 37정도의 연산으로 상당히 낮은 오차범위에서 찾아진다..!
 * lo-hi value를 잘 설정하는것이 관건
 * Function , Double Function Interface 활용법은 구글참조..!
 */
public class BinarySearch {

	private static final double EPS = 0.00000001;
	
	private static double binarySearch(
			double lo, double hi, double target, DoubleFunction<Double> fuction) {
		
		if(lo>= hi) throw new IllegalArgumentException("hi should be greater than lo");
		int count=0;
		double mid;
		do {
			mid = (lo + hi) / 2;

			double value = fuction.apply(mid);
			
			if(value > target) hi = mid;
			else lo = mid;
			
			count++;
			
		} while( hi-lo > EPS);
		
		System.out.println("function count : " + count);
		return mid;
	}
	
	public static void main(String[] args) {
		
		double lo = 0.0;
		double hi = 875.0;
		double target = 875.0;
		
		DoubleFunction<Double> fuction = (x) -> (x*x);
		
		double ans = binarySearch(lo, hi, target, fuction);
		
		System.out.printf("sqrt(%.2f) = %.5f, x^2 = %.5f\n",target,ans,(ans*ans));
		
		
		double radiusLowerBound = 0;
		double radiusUpperBound = 1000;
		double volume = 100.0;
		
		DoubleFunction<Double> sphereVolumeFunction = (r) -> ((4.0/3.0) * Math.PI * r*r*r);
		
		double radius = binarySearch(radiusLowerBound, radiusUpperBound, volume, sphereVolumeFunction);
		System.out.printf("Sphere radius = %.5fm \n", radius);
	}
	
}
