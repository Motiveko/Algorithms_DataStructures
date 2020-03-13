package myStudy.algorithms.serch;
/*
 * 한글명 : 보간탐색
 * binary search와 비슷한 방식인데 , binary search가 mid를 (low+high)/2로 하는데 반해
 * 조금 특이한 방식으로 mid를 정한다. -> 값과 인덱스 값이 비슷하게 흘러간다는 가정 하에 비례식을 만들어서 index를 찾는다
 * 나도 수식을 도출해 낼 수 있으니 잘 기억이 안나면 머리를 잠깐만 굴리면 된다.
 * 찾고자 하는 값을 알고 있을때만 사용 가능.
 * 응용버전의 binary search처럼 fucntion의 해를 찾는 식의 응용은 힘들 것 같고 일반적인 배열에서 특정값 찾는데 쓰이는듯
 */
public class InterpolationSearch {

	
	//nums : ordered list, val : value which we are looking for	 
	public static int interpolationSearch(int[] nums, int val) {
		int lo = 0, mid = 0, hi = nums.length-1;
		
		while( nums[lo] <= val && nums[hi] >= val) {
			
			mid = lo + ((val - nums[lo]) * ( hi - lo)) / (nums[hi] - nums[lo]);
			if( nums[mid] < val)  lo= mid+1;
			else if( nums[mid] > val) hi = mid -1;
			else return mid;
		}
		//hi로는 값이 떨어지질 않는다. 보통은 mid, 최악의 경우에도 hi=mid가 되어서 mid값에서 걸린다.
		if(nums[lo] == val) return lo;
		
		return-1;
	}
	
	public static void main(String[] args) {
	    int[] values = {10,33};
	    
	    System.out.println(interpolationSearch(values, 33));
	}
}
