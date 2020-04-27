package myStudy.goodee.practice;

import java.util.*;

public class Pocker {
	
	public static List<Integer> cardList = new ArrayList<>();
	public static Random ran = new Random();
	public static Scanner sc = new Scanner(System.in);
	
	public static void setCardList() {
		cardList.clear();
		for( int i=0 ;i<52; i++) cardList.add(i);
	}
	
	public static List<List<Integer>> getCards() {
	
		List<List<Integer>> cards = new ArrayList<>();
		for(int i=0 ; i<4; i++) cards.add(new ArrayList<>());
		
		for( int i=0; i<8; i++) {
			int card = cardList.get(ran.nextInt(cardList.size()));
			
			cards.get(card/13).add(card%13 + 1);
			cardList.remove(cardList.indexOf(card));
		}
		
		return cards;
	}
	
	public static double getGrade(List<List<Integer>> cards) {
		
		int grade = 0;
		//flush -> stf/rtf
		for( List<Integer> color : cards) {
			if(color.size()>=5) {
				if( isStragiht(color)==2) return grade = 9;
				else if (isStragiht(color)==1) return grade = 8;
				else grade = 5;
			}
		}

		List<Integer> numbers = new ArrayList<>();
		for( List<Integer> color : cards) {
			for(int card : color) numbers.add(card);
		}
		Collections.sort(numbers);
		//Straight
		if(isStragiht(numbers)!=0) grade = 4;
		
		boolean isPair=false;
		boolean isTrp=false;
		int prev = numbers.get(0);
		int count=1;
		
		int[] type = new int[2];
		
		
		for( int i=1; i<=numbers.size() ; i++) {
			
			if( i<numbers.size() && prev == numbers.get(i)) {
				count++;
			} else {
				//Four Card
				if( count == 4) return grade=7;
				else if( count == 3) {
					//Full House
					if( isPair==true) return grade = 6;
					
					//Minimum Triple
					grade = Math.max(grade, 3);
					
					isTrp = true;
				} else if( count== 2) {
					// Full House
					if( isTrp == true) return grade = 6;
					
					// Minimum Two Pair
					else if (isPair == true) grade = Math.max(grade, 2);
					
					// Minimum One Pair
					else grade = Math.max(grade, 1);
					isPair = true;
				}
				
				count = 1;
				if( i<numbers.size()) prev = numbers.get(i);
			}
		}
		
		return grade;
	}
	
	
	private static int isStragiht(List<Integer> cards) {
		//royal straight : 2, straight : 1, none: 0;
		
		int count=1;
		int prev = cards.get(0);
		boolean isStraight = false;
		//일반스트레이트
		for( int i=1; i<cards.size(); i++) {
			if( cards.get(i) == prev + 1) count++;
			 else count = 1;
			if(count == 5) isStraight = true;
			prev = cards.get(i);
		}
		//10JQKA 스트레이트
		if( prev==13 && count==4 && cards.get(0)==1) return 2; 
		return isStraight ? 1 : 0;
	}

	public static int regame() {
		System.out.println("계속 진행하시겠습니까? YES : 1  NO : 2");
		return yesOrNo();
	}
	
	public static int yesOrNo() {
		int input;
		while(true) {
			try {
				input = sc.nextInt();
			} catch(Exception e) {
				System.out.println(" 올바른 숫자를 입력하세요.");
				continue;
			}
			if( input!=1 && input!=2) {
				System.out.println(" 올바른 숫자를 입력하세요.");
				continue;						
			}
			break;
		}
		
		return input;
	}	
	
	public static void showCard(List<List<Integer>> cards) {
		
		for( int i=0; i<cards.size(); i++	) {
			switch(i){
			case 0:
				System.out.print("클로버 : ");
			case 1:
				System.out.print("하트 : ");
			case 2:
				System.out.print("다이아 : ");
			case 3:
				System.out.print("스페이드 :");
			}
			System.out.println(cards.get(i).toString());
		}
	}
	
	public static void main(String[] args) {
		
		
		
	
			
			System.out.println("포커 게임을 시작합니다.");
			setCardList();
			
			List<List<Integer>> dCards = getCards();
			List<List<Integer>> mCards = getCards();
			
			double dGrade = getGrade(dCards);
			double mGrade = getGrade(mCards);
			
			System.out.println("========딜러의 카드========");
			showCard(dCards);
			System.out.println("=======================");
			
			System.out.println("========유저의 카드========");
			showCard(mCards);
			System.out.println("=======================");

			System.out.println("나의 등급 : " + mGrade + "   상대의 등급 : " + dGrade);
			if( mGrade > dGrade) System.out.println("승리하였습니다! ");
			else if( mGrade == dGrade) System.out.println("비겼습니다!");
			else  System.out.println("졌습니다!");

		
		
//			List<List<Integer>> exp = new ArrayList<>();
//			for( int i=0; i<4; i++) exp.add(new ArrayList<>());
//			exp.get(0).add(5);
//			exp.get(0).add(13);
//			exp.get(1).add(11);
//			exp.get(1).add(9);
//			exp.get(2).add(2);
//			exp.get(2).add(8);
//			exp.get(3).add(9);
//			exp.get(3).add(13);
//			System.out.println(getGrade(exp));
	}
}
