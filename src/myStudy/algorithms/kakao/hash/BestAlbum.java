package myStudy.algorithms.kakao.hash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

public class BestAlbum {
	static class Solution {
	    public static int[] solution(String[] genres, int[] plays) {
	        
	    	HashMap<String, Integer> totalMap = new HashMap<>(); // 장르 - 토탈실행
	    	HashMap<String, ArrayList<Integer>>  genreMap = new HashMap<>(); // 장르, 장르별 id	
	    	for(int i=0; i<genres.length; i++) {
	    		
	    		Integer totalPlay = totalMap.get(genres[i]);
	    		if(totalPlay==null) {
	    			totalMap.put(genres[i],plays[i]);
	    			genreMap.put(genres[i], new ArrayList<>(Arrays.asList(i)));
	    			
	    		} else {
	    			totalMap.put(genres[i], totalPlay + plays[i]);
	    			genreMap.get(genres[i]).add(i);
	    		}
	    		
	    	}
	    	// (total , 순위)		
	    	String[] sortedGenres = new String[totalMap.size()];
	    	ArrayList<Integer> genreRank = new ArrayList<>();
	    	totalMap.values().stream().forEach( a -> genreRank.add(a));
	    	genreRank.sort(null);;
	    	for( int i=0; i<totalMap.size(); i++) {
	    		Iterator<String> itr = totalMap.keySet().iterator();
	    		while(itr.hasNext()) {
	    			String genre = itr.next();
	    			Integer genrePlay = totalMap.get(genre);
	    			sortedGenres[genreRank.indexOf(genrePlay)] = genre; 
	    		}
	    	}
	    	
	    	ArrayList<Integer> answer = new ArrayList<>();
	    	for( String genre : sortedGenres) {
	    		
	    		ArrayList<Integer> list = genreMap.get(genre);
	    		
	    		Integer first = list.get(0);
	    		if( list.size()==1) {
	    			answer.add(first);
	    			continue;
	    		}
	    		Integer second = list.get(1);
	    		if( plays[first] < plays[second]) {
	    			int tmp = second;
	    			second = first;
	    			first = tmp;
	    		}
	    		for( int i=2;i<list.size();i++ ) {

	    			if( plays[i] > first) {
	    				second = first;
	    				first = list.get(i);
	    			} else if( plays[i] > plays[second]) second=list.get(i);
	    		}
	    		answer.add(second);
	    		answer.add(first);	    		
	    	}
	    	
	    	
	    	int[] a = new int[answer.size()];
	    	for( int i =0; i<a.length;i++) {
	    		a[i] = answer.get(a.length-i-1);
	    	}
	    	
	    	return a;
	    }

	}
	public static void main(String[] args) {
		
		for( int x : Solution.solution(new String[] {"classic", "pop", "classic", "classic", "pop"},
				new int[] {50,60,15,80,250})) {
			System.out.println(x + ",");
		};
	}	
	
}
