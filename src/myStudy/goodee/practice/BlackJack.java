package myStudy.goodee.practice;

import java.util.*;

public class BlackJack {

	public static Random ran = new Random();
	public static Scanner sc = new Scanner(System.in);
	public static int nextCard() {
		return ran.nextInt(13) + 1;
	}
	
	public static int sumCards(List<Integer> cards) {
		int sum = 0;
		boolean hasA=false;
		
		for( int card : cards) {
			if(card==1) hasA=true;
			sum += card;
		}
		if( hasA && sum<16) sum += 10;
		
		return sum;
	}
	
	public static int adjustDCards(List<Integer> dCards) {
		
		while(sumCards(dCards) <= 16) {
			dCards.add(nextCard());
		}
		
		return sumCards(dCards);
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
	
	public static void main(String[] args) {
		

		
		int seed = 100000;
		
		while(true) {
			
			List<Integer> mCards = new ArrayList<>();
			List<Integer> dCards = new ArrayList<>();
			int mSum = 0;
			int dSum = 0;
			
			System.out.println("블랙잭을 시작합니다.");
			System.out.println("현재 잔고 : " + seed);
			System.out.println("배팅 금액을 입력하세요.");
			
			int bet;
			int input;
			while(true) {
				try {
					bet = sc.nextInt();
				} catch( Exception e) {
					System.out.println("올바른 숫자를 입력하세요.");
					continue;
				}
				
				if(bet<=0 || bet>seed) {
					System.out.println("올바른 숫자를 입력하세요.");
					continue;
				}
				break;
			}
			
			
			System.out.println(bet + "원을 배팅하였습니다.");
			System.out.println("카드를 배분합니다.");
			
			mCards.add(nextCard());
			mCards.add(nextCard());
			dCards.add(nextCard());
			dCards.add(nextCard());
			
			mSum = sumCards(mCards);
			dSum = sumCards(dCards);
			
			if( mSum == 21) {
				
				if( dSum==21) {
					System.out.println("나의 보유 카드 : " + mCards.toString() + " 나의 카드 합 : " + mSum);
					System.out.println("딜러 보유 카드 : " + dCards.toString() + " 딜러 카드 합 : " + dSum);
					System.out.println("비겼습니다.");
				}
				else {
					System.out.println("블랙잭입니다!");
					seed += bet;
					System.out.println(bet + "원을 획득하였습니다.");
				}
				
				if(regame()==1) continue;
				else break;
			} else if(dSum==21) {
				System.out.println("딜러가 블랙잭입니다!!");
				seed -= bet;
				System.out.println(bet + "원을 잃었습니다.");
				
				if(regame()==1) continue;
				else break;
			}
			
			dSum = adjustDCards(dCards);
			System.out.println("딜러 오픈 카드 : " + dCards.get(0) + "딜러 카드 갯수 : " + dCards.size());
			while(true) {
				System.out.println("현재 보유 카드 : " + mCards.toString() + " 카드 합 : " + mSum); 
				System.out.println("카드를 더 받으시겠습니까? YES : 1  NO : 2");
				
				input = yesOrNo();
				
				if(input == 1) {
					mCards.add(nextCard());
					mSum = sumCards(mCards);
				} else break;
			}
			
			int d = Math.abs(21-dSum);
			int m = Math.abs(21-mSum);
			System.out.println("딜러 카드 : " + dCards.toString());
			System.out.println("딜러합 : " + dSum + " ==== 나의 합 : " + mSum);
			if( d==m) {
				System.out.println("비겼습니다!");
			} else if( d>m) {
				System.out.println("승리하였습니다!!");
				System.out.println(bet + "원을 획득하였습니다.");
				seed += bet;
				
			} else {
				System.out.println("패배하였습니다.");
				System.out.println(bet + "원을 잃었습니다.");
				seed -= bet;
				if(seed==0) {
					System.out.println("파산하였습니다.");
					break;
				}
			}
			if(regame()==2) break;
		}
		
		System.out.println("게임을 진행하여 총 " + ( seed -100000)+ " 원을 획득하였습니다. " );
	}
}
