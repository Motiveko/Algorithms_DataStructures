package myStudy.goodee.practice;


/* 기본어항 4개 
 * 물고기는 해당일자가 지나야 판매 가능
 * 먹이 줄경우 해당일자가 1일씩 줄어듬
 * 완전 성장후 3일 경과시 사망
 * 먹이는 하루에 한번사용가능
 * 초기자금 300원
 * 
 * 어항추가 1만원, 먹이 : 50원
 * 
 * 물고기 구매  판매    성장
 * 금붕어 100 150  3
 * 잉어    500 1000 7
 * 구피    200 350  5
 */

import java.util.*;

abstract class Fish{
	
	protected String fishType;
	protected int bPrice, sPrice;
	protected int date;
	protected boolean isFeedable, isNull;
	
	
	
	public Fish(String fishType, int bPrice, int sPrice, int date,boolean isNull) {
		this.fishType = fishType;
		this.bPrice = bPrice;
		this.sPrice = sPrice;
		this.date = date;
		this.isNull = isNull;
		isFeedable = true;
	}
	protected boolean feed() {
		if(isFeedable && date>0) {			
			date--;
			isFeedable = false;
			return true;
		} 
		return false;
	}
	
	protected void nextDay() {
		--date;
		if( date <= 0) isFeedable = false;
		else isFeedable = true;
	}
}

//금붕어
class Goldfish extends Fish{
	public Goldfish() {
		super("금붕어",100, 150, 3,false);
	}
}

//잉어
class Carp extends Fish{
	public Carp() {
		super("잉어",500, 1000, 7,false);
	}
}

class Guppy extends Fish{
	public Guppy() {
		super("구피",200, 350, 5,false);
	}
}

class NullFish extends Fish{

	public NullFish() {
		super("NullFIsh",0,0,0,true);
		// TODO Auto-generated constructor stub
	}
	
	
}

class Aquarium {
	
	private int capacity;
	private int money;
	private List<Fish> aquarium;
	
	public Aquarium() {
		capacity = 4;
		money = 200;
		aquarium = new ArrayList<>();
		
	}
	
	public int size() {
		return aquarium.size();
	}
	
	public boolean hasFish() {
		for( Fish fish : aquarium) if(!fish.isNull) return true;
		return false;
	}
	
	public void buyFish(Fish fish) {
		if(money < fish.bPrice) System.out.println("돈이 모자릅니다.");
		else if(aquarium.size() >= capacity) System.out.println("어항이 가득 차있습니다.");
		else {
			if( aquarium.size() > 0 ) {
				//nullFish 있으면 거기다가 추가
				for(Fish existedFish : aquarium) 
					if(existedFish.isNull) {
						existedFish = fish;
						money -= fish.bPrice;
						System.out.println(fish.fishType + "1마리 구매 완료.");
						return;
					}
			}
			aquarium.add(fish);
			capacity--;
			money -= fish.bPrice;
			System.out.println(fish.fishType + "1마리 구매 완료.");
		}
	}
	
	public boolean sellFish(int index) {

		//인덱스는 1번부터이므로 -1
		if(index-1 >= aquarium.size()) {
			System.out.println("입력값이 잘못되었습니다.");
			return false;
		}
		
		Fish fish = aquarium.get(index-1);
		
		//빈어항
		if( fish.isNull ) {
			System.out.println("빈 어항입니다.");
			return false;
		}
		//아직 성체가 되지 못함
		if( fish.date > 0 ) {
			System.out.println(fish.fishType + "를 팔려면 최소 " + fish.date + "일 더 양식해야 합니다.");
			return false;
		}
		
		money += fish.sPrice;
		aquarium.set(index-1, new NullFish());
		capacity ++;

		System.out.println(fish.fishType + "를 팔아 " + fish.sPrice + "원을 획득하였습니다.");
		return true;
	}
	
	public void feed() {
		
		if( money < 200) {
			System.out.println("먹이줄 돈이 없습니다.");
			return;
		}
		boolean isFeeded = false;
		for( Fish fish : aquarium) {
			if(!fish.isNull && fish.feed()) isFeeded = true;
		}
		if(isFeeded) money -= 200;
		else System.out.println("오늘은 이미 먹이를 줬거나 어항에 물고기가 없습니다.");
	}


	public void extendAquarium() {
		if(money >=10000) {
			money -= 10000;
			capacity++;
		} else System.out.println("돈이 모자랍니다.");
	}
	
	public void nextDay() {
	
		for( int i=0; i<aquarium.size() ; i++) {
			Fish fish = aquarium.get(i);
			if(!fish.isNull) {
				fish.nextDay();
				if( fish.date <= -3) {
					System.out.println(fish.fishType + " 물고기 1마리 완전성장 후 3일 경과로 사망.");
					aquarium.set(i, new NullFish());
					capacity++;		
				}				
			}
		}

	}

	public void showInfo() {
		System.out.println("현재 잔고 : " + money + "   현재 어항 여유공간 : " + capacity);
		showFish();
	}
	
	public void showFish() {
		System.out.println("========물고기 현황=========");
		for( int i=0; i<aquarium.size(); i++) {
			Fish fish = aquarium.get(i);
			System.out.print((i+1) + "번 어항 : ");
			if(fish.isNull) {
				System.out.println("빈 어항");
			} else {
				System.out.printf("어종 : %s    D-day : %d \n", fish.fishType,-fish.date);
			}
			System.out.println("========================");
		}		
	}
	

}

