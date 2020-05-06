package myStudy.goodee.practice;

import java.util.Scanner;

public class AquariumPlayer {

	
	public static Scanner sc = new Scanner(System.in);
	public static Aquarium aq = new Aquarium();
	
	
	public static void letsBuy() {
		System.out.println("무슨 물고기를 구매하시겠습니까?");
		System.out.println("1. 금붕어    2. 잉어    3. 구피");
		int input;
		while(true) {
			try {
				input = sc.nextInt();
				if(input > 3 || input < 0) {
					System.out.println("잘못입력하였습니다.");
					continue;
				}
				break;
			}catch(Exception e) {
				System.out.println("잘못입력하였습니다.");
				continue;
			}
		}
		switch(input) {
		case 1:{
			aq.buyFish(new Goldfish());
			break;
		}
		case 2:{
			aq.buyFish(new Carp());
			break;
		}
		case 3:{
			aq.buyFish(new Guppy());
			break;
		}
		}
	}
	
	
	public static void letsSell() {
		if(!aq.hasFish()) {
			System.out.println("물고기가 없습니다");
			return;
		}
		System.out.println("물고기를 판매합니다.");
		aq.showFish();
		System.out.println("몇 번 어항에 든 물고기를 팔겠습니까?");
		int input;
		while(true) {
			try {
				input = sc.nextInt();
				if(input > aq.size() || input < 1) {
					System.out.println("잘못입력하였습니다.");
					continue;
				}
				break;
			}catch(Exception e) {
				System.out.println("잘못입력하였습니다.");
				continue;
			}
		}
		
		aq.sellFish(input);
		
	}
	
	public static void main(String[] args) {
		
		
		System.out.println("수족관놀이를 시작합니다.");
		while(true) {
			System.out.println("무엇을 하시겠습니까?");
			System.out.println("1. 정보보기     2. 물고기 구매하기    3. 물고기 팔기    4. 물고기 밥주기    5. 하루 보내기    0. 종료하기");
			
			int input;
		
			while(true) {
				try {
					input = sc.nextInt();
					if(input > 5 || input < 0) {
						System.out.println("잘못입력하였습니다.");
						continue;
					}
					break;
					
				}catch(Exception e) {
					System.out.println("잘못입력하였습니다.");
					continue;
				}
			}
				
			switch(input) {
			
			case 1: {
				aq.showInfo();
				break;
			}
			case 2: {
				letsBuy();
				break;
			}
			case 3: {
				letsSell();
				break;
			}
			case 4: {
				aq.feed();
				break;
			}
			case 5: {
				aq.nextDay();
				break;
			}
			case 0: {
				System.out.println("놀이를 종료합니다.");
				break;
			}
			}
			
		}
	}
}
