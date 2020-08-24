package myStudy.algorithms.kakao.hash;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

public class Spy {
    public int solution(String[][] clothes) {

        HashMap<String, Integer> map = new HashMap<>();
        
        for(String[] cloth : clothes){
            map.put(cloth[1],map.getOrDefault(cloth[1], 0)+1);
        }
        Iterator<String> itr = map.keySet().iterator();
        int answer = 1;
        while(itr.hasNext()) {
        	answer *= (map.get(itr.next())+1);
        }
        return answer-1;
    }
   
}

    