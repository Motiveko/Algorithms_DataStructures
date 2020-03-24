


package myStudy.algorithms.leetcode.medium;


import java.util.*;

public class LetterCombinationsOfAPhoneNumber {

	
	public static TreeSet<String> combinations(String digits){
		
		TreeSet<String> set = new TreeSet<>();
		
		HashMap<Integer,String> map = new HashMap<>();
		map.put(1, null);
		map.put(2,"abc");
		map.put(3,"def");
		map.put(4,"ghi");
		map.put(5,"jkl");
		map.put(6,"mno");
		map.put(7,"pqrs");
		map.put(8,"tuv");
		map.put(9,"wxyz");
		map.put(0, null);
		
		helper(map,set,digits,0);
		//recursive하게 풀어보는건 어떤가
		//digits 0,1,2,3,4,... length까지 파고들어서 set으로 맨 처음경우 3개 받고 거기에 다음에 3*3해주고 ..이런식!645
		
		return set;
	}
	
	private static void helper(HashMap<Integer, String> map, 
			TreeSet<String> set, String digits, int index) {
		
		if(index < digits.length() -1 )helper(map,set,digits,index+1);
		String n = map.get(Integer.parseInt(digits.substring(index,index+1)));
		if(n==null) return;
		if(set.isEmpty()) {
			
			for(int i=0; i<n.length(); i++) set.add(n.substring(i,i+1));
			
		}else {
			TreeSet<String> oldSet = (TreeSet<String>) set.clone();
			set.clear();
			for( int i=0; i<n.length(); i++) {
				Iterator<String> oldItr = oldSet.iterator();
				while(oldItr.hasNext()) {
					set.add(n.substring(i, i+1)+oldItr.next());
				}
			}
		}

	}
	
	public static void main(String[] args) {
		TreeSet<String> set = combinations("4123");
		Iterator<String> setItr = set.iterator();
		
		while(setItr.hasNext()) System.out.print(setItr.next() + ", ");
	}
}
