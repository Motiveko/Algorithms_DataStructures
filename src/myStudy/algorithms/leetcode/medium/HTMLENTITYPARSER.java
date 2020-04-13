package myStudy.algorithms.leetcode.medium;

import java.util.*;


//https://leetcode.com/problems/html-entity-parser/
//https://github.com/fishercoder1534/Leetcode/blob/master/src/main/java/com/fishercoder/solutions/_1410.java
public class HTMLENTITYPARSER {

	public static String entityParser(String text) {
		
		Map<String, String> map = new HashMap<>();
		map.put("quot", "\"");
		map.put("qpos", "'");
		map.put("amp", "&");
		map.put("gt", ">");
		map.put("lt", "<");
		map.put("frasl", "/");
		
		
		int n = text.length();
		
		String s = "";
		
		int start = -1;
		int end = -1;
		for( int i=0; i<n; i++) {
			
			char ch = text.charAt(i);
			
			
			if( ch == '&') {
				if(start !=-1) s +="&";
				start = i;
			}
			else if( ch == ';') end = i;
			if( start == -1) s += String.valueOf(ch);

			if( end != -1 ) {
				String parse = text.substring(start+1,end);
				if(map.containsKey(parse)) s += map.get(parse);
				else s += "&"+parse+";";
				
				start = end = -1;
			}
			
		}
		if( start !=-1) s += text.substring(start);
		return s;
	}
	
	
	
	public static void main(String[] args) {
		System.out.println(entityParser("&amp; is an HTML entity but &ambassador; is not."));
	}
}
