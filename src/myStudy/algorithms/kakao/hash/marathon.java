package myStudy.algorithms.kakao.hash;

import java.util.HashMap;
import java.util.Map;

// https://www.welcomekakao.com/learn/courses/30/lessons/42576?language=java
public class marathon {

	
	static class Solution {
	    public static String solution(String[] participant, String[] completion) {
	    	
	        
	        String answer="";
	        Map<String, Integer> map = new HashMap<String, Integer>();
	        
	        for( String com : completion){
	            Integer x = map.get(com);
	            if( x==null){
	                map.put(com,1);
	            } else {
	                map.put(com,x+1);
	            }
	        }
	        for(String par : participant){
	            Integer x = map.get(par);
	            if( x==null){
	                return answer=par;
	            } else if(x==0) {
	                return answer=par;
	            } else{
	                map.put(par,x-1);
	            }
	        }
	        return answer;
	    }
	}
	// Test
	public static void main(String[] args) {
		
		System.out.println(
				Solution.solution(new String[] {"marina", "josipa", "nikola", "vinko", "filipa"},
							new String[] {"josipa", "nikola", "vinko", "filipa"}));
	}
}
