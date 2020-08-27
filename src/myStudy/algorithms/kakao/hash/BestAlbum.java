package myStudy.algorithms.kakao.hash;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

// 카카오 코딩테스트 연습, 해쉬 4번문제
// https://www.welcomekakao.com/learn/courses/30/lessons/42579
public class BestAlbum {
	static class Solution {
	    public static int[] solution(String[] genres, int[] plays) {
	        
	    	// 필요한거
	    	// 장르별 총 재생수 및 그에대한 순위
	    	// 장르별 1,2위의 ID
	    	HashMap<String, Integer> totalMap = new HashMap<>(); // 장르 - 총재생횟수
	    	HashMap<Integer, String> reverseTotalMap = new HashMap<>(); // 총재생횟수 - 장르

	    	// 각 재생횟수 바로 정렬때려서 eachMap에서 id를 가져온다.
	    	HashMap<String, ArrayList<Integer>> genreMap = new HashMap<>(); // 장르, (재생횟수1,2,3..)
	    	HashMap<Integer, ArrayList<Integer>> eachMap = new HashMap<>(); // 재생횟수 - id ( 중복따져야한다.)
	    	
	    	for( int i=0; i<genres.length; i++) {
	    		
	    		String genre = genres[i];
	    		int play = plays[i];
	    		
	    		totalMap.put(genre, totalMap.getOrDefault(genre, 0)+play);
	    		if(!genreMap.containsKey(genre)) genreMap.put(genre, new ArrayList<>());
	    		genreMap.get(genre).add(play);
	    		
	    		if(!eachMap.containsKey(play)) eachMap.put(play, new ArrayList<>());
	    		eachMap.get(play).add(i);
	    	}
	    	
	    	ArrayList<Integer> sortedTotalPlays = new ArrayList<>();
	    	
	    	totalMap.values().stream().forEach( total -> sortedTotalPlays.add(total));
	    	totalMap.keySet().stream().forEach( key -> reverseTotalMap.put(totalMap.get(key), key));
	    	
	    	Collections.sort(sortedTotalPlays, Collections.reverseOrder());	    	
	    	
	    	ArrayList<Integer> answer = new ArrayList<>();
	    	for( Integer totalPlay : sortedTotalPlays) {
	    		// 장르 순위별로 꺼내보잣
	    		String genre = reverseTotalMap.get(totalPlay); // 현재장르
	    		ArrayList<Integer> list = genreMap.get(genre); // 해당장르 각 재생횟수
	    		Collections.sort(list,Collections.reverseOrder()); // 해당장르 재생횟수 정렬

	    		// 장르 내에서 순서대로 꺼내보자
	    		for( int i=0; i<2; i++) {
	    			if( list.size() <= i) break;
	    			Integer playTime = list.get(i);
	    			
	    			// 해당 재생횟수를 가지는 ids, 오름차순 정렬 	

	    			ArrayList<Integer> IdsWithSamePlay = eachMap.get(playTime);
	    			// 	Collections.sort(IdsWithSamePlay);	// 이미 넣을때부터 오름차순으로 넣는다.
	    			for( Integer id : IdsWithSamePlay) {
	    				if(genres[id].equals(genre)) {
	    					// 이미 같은 재생횟수, 장르 를 가지는 아이디가 들어가있다!	
	    					if(answer.contains(id)) continue;
	    					answer.add(id);
	    					break;
	    				}
	    			}
	    		}
	    	}

	    	return answer.stream().mapToInt(Integer::intValue).toArray();
	    }
	}
	
	public static void main(String[] args) {
		for( int x : Solution.solution(new String[] {"classic", "pop"},
											new int[] {50,60})) {
			System.out.println(x + ",");
		};
	}	
	
}
