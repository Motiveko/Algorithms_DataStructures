package myStudy.algorithms.dp;

/*
https://github.com/awangdev/LintCode/blob/master/Java/Frog%20Jump.java#L50

A frog is crossing a river. The river is divided into x units and at each unit 
there may or may not exist a stone. The frog can jump on a stone, but it must not jump into the water.
Given a list of stones' positions (in units) in sorted ascending order, 
determine if the frog is able to cross the river by landing on the last stone. 
Initially, the frog is on the first stone and assume the first jump must be 1 unit.
If the frog's last jump was k units, then its next jump must be either k - 1, k, or k + 1 units. 
Note that the frog can only jump in the forward direction.
Note:
The number of stones is ≥ 2 and is < 1,100.
Each stone's position will be a non-negative integer < 231.
The first stone's position is always 0.
Example 1:
[0,1,3,5,6,8,12,17]
There are a total of 8 stones.
The first stone at the 0th unit, second stone at the 1st unit,
third stone at the 3rd unit, and so on...
The last stone at the 17th unit.
Return true. The frog can jump to the last stone by jumping 
1 unit to the 2nd stone, then 2 units to the 3rd stone, then 
2 units to the 4th stone, then 3 units to the 6th stone, 
4 units to the 7th stone, and 5 units to the 8th stone.
Example 2:
[0,1,2,3,4,8,9,11]
Return false. There is no way to jump to the last stone as 
the gap between the 5th and 6th stone is too large.
*/

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class FrogJump {

	private static final int Integer = 0;
	static int count = 0;
	// 나의 방법, dp가 아닌 dfs로 풀었다
	public static boolean solve1(int[] stones) {

//		 전략 : dfs 활용.
//		 현재 지점(pos = stones[i] , i를 계속 넣어준다.)최근점프 거리(d) 를 가지고 다음에 나올 수 있는 경우는  -> pos+d-1 , pos+d , pos+d+1
//		 그 중 유효한 값이 있다면 거기에 dfs, 만약에 최종지점에 도달하면 true
//		 false는? 더 갈 곳이 없을 경우

//		 int[] 를 List<Integer> 로 바꾸는방법..! 대단하다.. 나는 이것을 살짝 응용해서 아래 코드를 만들었다!
//		 https://stackoverflow.com/questions/2607289/converting-array-to-list-in-java
//		 int[] ints = new int[] {1,2,3,4,5};
//		 List<Integer> list11 =Arrays.stream(ints).boxed().collect(Collectors.toList()); 

//		.boxed() 는 IntStream의 method인데  stream 각각의 값을 Integer로 boxed 한다. 한마디루 Integer화 시킨다고 보면 된다.
		List<Object> stoneList = Arrays.asList(Arrays.stream(stones).boxed().toArray());
		
		return dfs(stoneList, 0, 1);
	}

	private static boolean dfs(List<Object> stoneList, int cur, int dist) {
		count ++;
		if (dist == 0)
			return false;

		int n = stoneList.size();
		int prev = (int) stoneList.get(cur);

		if (!stoneList.contains(prev + dist))
			return false;

		int next = stoneList.indexOf(prev + dist);
		if (next == n - 1)
			return true;

		return dfs(stoneList, next, dist - 1) || dfs(stoneList, next, dist) || dfs(stoneList, next, dist + 1);

	}
	
	public static boolean solve2(int[] stones) {
	
		// How to solve it with dp..!
		// DP 로 풀려면 어떤 상태와 그 상태에 대한 저장값이 있어야 하는데 여기서는 상태는 stone, 저장값은 그 stone에 도달할 때 점프한 점프거리값이다!
		
				// Key: stone, value: 그 stone에 도달할 때 점프한 거리
		HashMap< Integer, Set<Integer>> dp = new HashMap<>();
		
		
		for( int stone : stones) dp.put(stone, new HashSet<>());
		
		//맨 처음 1번째 돌에는 1만큼으로 점프한다
		dp.get(1).add(1);
	
		for( int stone : stones) {
			for( int step : dp.get(stone)) {
				move(dp, stone, step);
				move(dp, stone, step-1);
				move(dp, stone, step+1);
			}
		}
		
		int destination = stones[stones.length - 1];
		
		Set<Integer> keySet = dp.keySet();	
		keySet.forEach(val -> {
			System.out.print("stone " + val + " : " );
			dp.get(val).forEach( e -> {
				System.out.print(e + ", ");
			});
			System.out.println();
		});
		
		return !dp.get(destination).isEmpty();
	}
	
	private static void move( HashMap<Integer, Set<Integer>> dp, int curr, int step) {
		if( step > 0 && dp.containsKey(curr + step)) dp.get(curr+step).add(step);
	}

	public static void main(String[] args) {

		System.out.println(solve1(new int[] { 0, 1, 3, 5, 6, 8, 12, 17 }));
		System.out.println(solve1(new int[] { 0, 1, 2, 3, 4, 8, 9, 11 }));

		System.out.println(solve2(new int[] { 0, 1, 3, 5, 6, 8, 12, 17 }));
		System.out.println(solve2(new int[] { 0, 1, 2, 3, 4, 8, 9, 11 }));
		
		
	}

}
