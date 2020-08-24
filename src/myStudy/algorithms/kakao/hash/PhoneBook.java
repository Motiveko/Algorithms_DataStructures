package myStudy.algorithms.kakao.hash;

import java.util.HashMap;

public class PhoneBook {

	public static class Solution{
	    public boolean solution(String[] phone_book) {

	    	
	        HashMap<String, Integer> map = new HashMap<>();
	        
	        for( String phone : phone_book) {
	             if( map.containsKey(phone)) return false;
	            map.put(phone, phone.length());
	        }
	        for( int i=1; i<=20; i++) {
	        	
	        	for( String phone : phone_book){
	        		if( phone.length()<=i) continue;
	        		if( map.containsKey(phone.substring(0, i))){
	        			return false;
	        		}
	        	}
	        }
	    	return true;
	    }		
	}
	
	static void main(String[] args) {
		System.out.println(new String("dadf").substring(0,4));
	
	}
}
