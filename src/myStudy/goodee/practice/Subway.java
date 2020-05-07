package myStudy.goodee.practice;

import java.util.*;

public class Subway {
	
	public class Passenger{
		String destination;
		
		public Passenger(String destination) {
			this.destination = destination;
		}
	}

	List<String> station = new ArrayList<>();
	String curStation ;
	List<Passenger>[] subway = new ArrayList[4];	
	int cur=0;
	boolean isRight = true;
	
	public Subway() {
		for(int i=0; i<4; i++) subway[i] = new ArrayList<>();
		
		station.add("장승백이");
		station.add("연신내");
		station.add("강남");
		station.add("양재");
		station.add("평택");
		curStation = station.get(0);
	}
	
	public void nextStation() {
		curStation = isRight ? station.get(++cur) : station.get(--cur);
		if( cur==0) isRight = true;
		else if( cur==4 ) isRight = false;
		
		for( int i=0 ; i<subway.length; i++) {
			
			List<Passenger> newList = new ArrayList<>();
			
			for( int j= 0; j<subway[i].size(); j++) {
				Passenger passenger = subway[i].get(j);
				if( !curStation.equals(passenger.destination)) newList.add(passenger);
			}
		
			subway[i] = null;
			subway[i] = newList;
		}
		System.out.println("현재 정차중인 역 : " + curStation);
	}
	
	
	public boolean addPassenger(String destination) {
		for(int i=0; i<subway.length; i++) {
			if( subway[i].size()==4) {
				if( i<subway.length-1) continue;
				else {
					System.out.println("열차가 가득찼습니다");
					return false;
				}
			} else{
				subway[i].add( new Passenger(destination));
				return true;
			}
		}
		
		return false;
	}
	
	public void showInfo() {
		System.out.println("********showInfo********");
		System.out.println("현재 정차중인 역 : " + curStation);
		for( int i=0; i<subway.length; i++) {
			System.out.println("========="+(i+1) + "번 열차=========");
			System.out.println("승객 수 : " + subway[i].size());
			int count=1;
			for( Passenger passenger : subway[i]) {
				
				System.out.println("승객 "+ (count++) + " : " + passenger.destination);
			}
		}
		System.out.println("************************");
	}
	
	
	public static void main(String[] args) {
		
		
		Subway subway = new Subway();
		Scanner sc= new Scanner(System.in);
		
		while(true) {
			
			System.out.println("=====무엇을 하시겠습니까 ? =====");
			System.out.println(" 승객탑승(1) 다음역으로이동(2) 열차정보보기(3) 그만하기(4)");
			int input=0;
			try {
				input = Integer.parseInt(sc.nextLine());
			} catch( Exception e) {
				System.out.println("잘못 입력하였습니다. 다시 입력해주세요");
				continue;
			}
			
			if( input == 1) {
				while(true) {
					System.out.println("목적지가 어디입니까?");
					System.out.println(" 장승백이(1) 연신내(2) 강남(3) 양재(4) 평택(5)");
					int destination=0;
					try {
						destination = Integer.parseInt(sc.nextLine());
					} catch(Exception e) {
						System.out.println("다시 입력해주세요");
						continue;
					}
					
					if(destination >=1 && destination <= 5) {
						if(subway.addPassenger(subway.station.get(destination-1)))
							System.out.println("열차에 탑승하였습니다.");
						break;
					} else System.out.println("다시 입력해주세요");
				}
				
			}else if (input == 2) {
				subway.nextStation();
			} else if( input == 3) {
				subway.showInfo();
			} else if (input == 4) {
				break;
			} else {
				System.out.println("다시 입력해주세요");
			}
			
			
		}
		
		subway.addPassenger(subway.station.get(3));
		subway.addPassenger(subway.station.get(4));
		subway.addPassenger(subway.station.get(1));
		subway.addPassenger(subway.station.get(4));
		subway.addPassenger(subway.station.get(2));
		subway.addPassenger(subway.station.get(1));
		subway.addPassenger(subway.station.get(0));
		subway.addPassenger(subway.station.get(4));
		subway.addPassenger(subway.station.get(4));
		subway.addPassenger(subway.station.get(4));
		subway.addPassenger(subway.station.get(4));
		subway.addPassenger(subway.station.get(4));
		subway.addPassenger(subway.station.get(4));
		subway.addPassenger(subway.station.get(4));
		subway.addPassenger(subway.station.get(4));
		subway.addPassenger(subway.station.get(1));
		subway.addPassenger(subway.station.get(0));
		subway.addPassenger(subway.station.get(0));
		subway.showInfo();
//		subway.nextStation();
//		subway.nextStation();
//		subway.showInfo();
		


	}
}
