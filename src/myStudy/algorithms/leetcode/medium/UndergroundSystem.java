package myStudy.algorithms.leetcode.medium;

import java.util.*;

//https://leetcode.com/problems/design-underground-system/
//https://github.com/fishercoder1534/Leetcode/blob/master/src/main/java/com/fishercoder/solutions/_1396.java#L53
public class UndergroundSystem {

	public static class StartStation{
		
		String stationName;
		int time;
		
		public StartStation(String stationName, int time) {
			this.stationName = stationName;
			this.time = time;
		}
	}
	

	// <id, <start,time>>
	Map<Integer, StartStation> idsInSubway;
	// startStation, <endStation, runtime>
	Map<String , Map< String, List<Integer>>> runningTime;
	
	public UndergroundSystem() {
		idsInSubway =  new HashMap<>();
		runningTime =  new HashMap<>();
	}
	
	
	public void checkIn( int id, String startStationName, int startTime) {
		
		if( idsInSubway.containsKey(id)) return;
		idsInSubway.putIfAbsent(id, new StartStation(startStationName,startTime));
	}
	
	public void checkOut( int id, String endStationName, int endTime) {
	
		if( !idsInSubway.containsKey(id)) return;
		StartStation startStation = idsInSubway.remove(id);
		
		
		if( !runningTime.containsKey(startStation.stationName)) runningTime.put(startStation.stationName, new HashMap<>());
		
		
		if( !runningTime.get(startStation.stationName).containsKey(endStationName) ) 
			runningTime.get(startStation.stationName).put(endStationName, new ArrayList<>());
		runningTime.get(startStation.stationName).get(endStationName).add(endTime - startStation.time);
		
	}
	
	public double getAverageTime( String startStation, String endStation) {
		
		if( !runningTime.containsKey(startStation)) return -1;
		
		if( !runningTime.get(startStation).containsKey(endStation) ) return -1;
		
		int sum =0;
		List<Integer> timeList = runningTime.get(startStation).get(endStation);
		
		for( int time : timeList) sum += time;
		
		return sum/timeList.size();

	}
	
	
	public static void main(String[] args) {
		
		UndergroundSystem under = new UndergroundSystem();
		
		under.checkIn(45, "Leyton", 3);
		under.checkIn(32, "Paradise", 8);		 
		under.checkIn(27, "Leyton", 10);
		under.checkOut(45, "Waterloo", 15);
		under.checkOut(27, "Waterloo", 20);
		under.checkOut(32, "Cambridge", 22);
		  
		System.out.println(under.getAverageTime("Paradise", "Cambridge"));
		
		
		System.out.println(under.getAverageTime("Leyton", "Waterloo"));
		under.checkIn(10, "Leyton", 24);
		System.out.println( under.getAverageTime("Leyton", "Waterloo"));
		under.checkOut(10, "Waterloo", 38);
		System.out.println(under.getAverageTime("Leyton", "Waterloo")); 
	}
}
