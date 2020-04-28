package myStudy.goodee.practice;

import java.util.*;

public class PokerDetail {
	
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
	
	public static List<Integer[]> getGrade(List<List<Integer>> cards) {
		
		
		// grade Type / {구성정보,..}
		List<Integer[]> gradeType = new ArrayList<>();
		for( int i=0; i<=9; i++) gradeType.add(new Integer[] {0,0});
		
		int grade = 0;
		//flush -> stf/rtf  (색상정보는 array[0]에 입력)
		for( int i=0; i<cards.size(); i++) {
			List<Integer> color = cards.get(i);
			if(color.size()>=5) {
				Collections.sort(color);
				//gdType 에서 첫번째 칸에 카드타입 정보 입력
				if( isStragiht(color)==1) gradeType.get(9)[0]=i+1;
				
				else if (isStragiht(color)>1) {
					
					gradeType.get(8)[0] = i+1;
					//숫자정보
					gradeType.get(8)[1] = isStragiht(color);
				}
				else gradeType.get(4)[0] = i+1;
			}
			
		}

		Map<Integer, Integer> cardMap = new HashMap<>();
	
		
		for( List<Integer> color : cards)
			for( int card : color) {
				cardMap.put(card, cardMap.getOrDefault(card, 0) + 1);
			}
		
		Set<Integer> keySet = cardMap.keySet();
		Iterator<Integer> keyItr = keySet.iterator();
		//=====================스트레이트검사;=====================
		List<Integer> list = new ArrayList<>();
		for( List<Integer> color : cards) {
			for( int num : color) list.add(num);
		}
		Collections.sort(list);
		
		if(isStragiht(list) != 0 )gradeType.get(5)[0] = isStragiht(list);
		//===============================================================
	
		boolean isPair = false;
		boolean isTrp = false;
		
		while( keyItr.hasNext()) {
 			
			int card = keyItr.next();
			
			if( cardMap.get(card)==4) {
				if(gradeType.get(7)[0]==0) {
					//만약 4카드가 두개나오면 그중 큰 값을 넣겠다.
					if( card == 1 ) gradeType.get(7)[0] = 1;
					else gradeType.get(7)[0]=Math.max(card,gradeType.get(7)[0]);
				}
			}
			else if( cardMap.get(card) == 3) {
				//트리플
				if( card == 1 ) gradeType.get(3)[0] = 1;
				else if( gradeType.get(3)[0]!=1) gradeType.get(3)[0] = Math.max(card, gradeType.get(3)[0]);
				isTrp = true;
				
				if( isPair) {
					//풀하우스
					//trp중에 가장 grade 높은거
					gradeType.get(6)[0] = gradeType.get(3)[0];
					//원페어중 가장 grade 높은거
					gradeType.get(6)[1] = gradeType.get(1)[0];
				} 
	
			  } else if( cardMap.get(card) == 2) {
				  

				  if( isPair) {
					  //투페어
					  if( gradeType.get(2)[0] == 0 ) {
						  // first 투페어
						  gradeType.get(2)[0] = card;
						  gradeType.get(2)[1] = gradeType.get(1)[0];
						  
					  }else {
						  // already to pair exist
						  if(gradeType.get(2)[0] > gradeType.get(2)[1]) gradeType.get(2)[1] = card;
						  else gradeType.get(2)[0] = card;
						 
					  }

					  
				  } else if ( isTrp) {
					  //풀하우스
					  gradeType.get(6)[0] = gradeType.get(3)[0];
					  gradeType.get(6)[1] = Math.max(card, gradeType.get(1)[0]);
				  }
				//페어
				  if( card==1 ) gradeType.get(1)[0] = 1;
					else if( gradeType.get(1)[0]!=1) gradeType.get(1)[0] = Math.max(card, gradeType.get(1)[0]);
				  isPair = true;
			  }
			
			}
			
		

		return gradeType;
	}
	
	
	private static int isStragiht(List<Integer> cards) {
		//royal straight : 2, straight : 1, none: 0;
		
		int count=1;
		int prev = cards.get(0);
		int type=0;
		
		boolean isStraight = false;
		//일반스트레이트
		for( int i=1; i<cards.size(); i++) {
			if( cards.get(i) == prev + 1) count++;
			 else count = 1;
			if(count == 5) {
				isStraight = true;
				type = i;
			}
			prev = cards.get(i);
		}
		//10JQKA 스트레이트
		if( prev==13 && count>=4 && cards.get(0)==1) {
			return 1;
		}
		
		return isStraight ? type: 0;
	}

	public static int regame() {
		System.out.println("계속 진행하시겠습니까? YES : 1  NO : 2");
		return yesOrNo();
	}
	
	private static int yesOrNo() {
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
				break;
			case 1:
				System.out.print("하트 : ");
				break;
			case 2:
				System.out.print("다이아 : ");
				break;
			case 3:
				System.out.print("스페이드 :");
				break;
			}
			System.out.println(cards.get(i).toString());
		}
	}
	
	public static void showGrade(List<Integer[]> gradeList) {
		int grade=0;
		for( int i=gradeList.size()-1; i>=0; i--) {
			if(gradeList.get(i)[0] !=0 || gradeList.get(i)[1]!=0) {
				grade = i;
				break;
			}
		}
		
		switch(grade) {
		
		case 9:{
			int color = gradeList.get(9)[0];
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			if( color == 4) System.out.println("스페이드 로얄 스트레이트 플러쉬!");
			else if( color == 3) System.out.println("다이아 로얄 스트레이트 플러쉬!");
			else if( color == 2) System.out.println("하트 로얄 스트레이트 플러쉬!");
			else System.out.println("클로버 로얄 스트레이트 플러쉬!");
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

			break;
		}
		case 8:{
			int color = gradeList.get(8)[0];
			int lastNum = gradeList.get(8)[1];
			
			List<Integer> list = new ArrayList<>();
			for( int i=lastNum ; i>=lastNum-5 ; i--) list.add(i);
			
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			System.out.println(list.toString());
					
			if( color == 4) System.out.print("스페이드  스트레이트 플러쉬!");
			else if( color == 3) System.out.print("다이아  스트레이트 플러쉬!");
			else if( color == 2) System.out.print("하트  스트레이트 플러쉬!");
			else System.out.print("클로버 스트레이트 플러쉬!");			
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

			break;
		}
		case 7:{
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			int num = gradeList.get(7)[0];
			System.out.println("[ "+num+" ]" + " 포카드!");
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

			break;

		}
		case 6:{
			int trpNum = gradeList.get(6)[0];
			int pairNum = gradeList.get(6)[1];
			System.out.println("[ " + trpNum + ", " + pairNum +" ]" + " 풀 하우스!");
			break;
		}
		case 5:{
			int lastNum = gradeList.get(5)[0];
		
			if (lastNum == 1) System.out.println("[ 10,11,12,13,1 ]");
			else {
				List<Integer> list = new ArrayList<>();
				for( int i=lastNum ; i>lastNum-5 ; i--) list.add(i);
				System.out.println(list.toString());
			}
			System.out.println(" 스트레이트!");
			break;
		}
		case 4:{
			int color = gradeList.get(4)[0];
			if( color == 4) System.out.println("스페이드 플러쉬!");
			else if( color == 3) System.out.println("다이아  플러쉬!");
			else if( color == 2) System.out.println("하트 플러쉬!");
			else System.out.println("클로버 플러쉬!");			
			
			break;
		}
		case 3:{
			int num = gradeList.get(3)[0];
			System.out.println("[ "+num+" ]" + "트리플!");
			
			break;
			
		}
		case 2:{
			int maxNum = Math.max(gradeList.get(2)[0],gradeList.get(2)[1]);
			int minNum = Math.min(gradeList.get(2)[0],gradeList.get(2)[1]);
			
			System.out.println("[ " + maxNum + ", " + minNum + " ]" + "투페어!");
			
			break;
		}
		case 1:{
			int num = gradeList.get(1)[0];
			System.out.println("[ " + num + " ]" + "원페어!");
			
			break;
		}
		default: System.out.println("조합이 없습니다...");
		
		}
	}
	public static void main(String[] args) {
		
		
		
		while(true) {
			
			System.out.println("포커 게임을 시작합니다.");
			setCardList();
			
			List<List<Integer>> dCards = getCards();
			List<List<Integer>> mCards = getCards();
			
			List<Integer[]> dGradeList = getGrade(dCards);
			List<Integer[]> mGradeList = getGrade(mCards);
			int dGrade = 0;
			int mGrade = 0;
			System.out.println("========딜러의 카드========");
			showCard(dCards);
			System.out.println("=======================");
			
			System.out.println("========유저의 카드========");
			showCard(mCards);
			System.out.println("=======================");

			for( int i=dGradeList.size()-1; i>=0; i--) {
				if(dGradeList.get(i)[0] !=0 || dGradeList.get(i)[1]!=0) 
					dGrade = Math.max(i, dGrade);
				
				if(mGradeList.get(i)[0] !=0 || mGradeList.get(i)[1]!=0)
					mGrade = Math.max(i, mGrade);
			}
			
			System.out.println("상대의 등급 : " );
			showGrade(dGradeList);
			System.out.println("나의 등급 : ");
			showGrade(mGradeList);
			
			if( mGrade > dGrade) System.out.println("승리하였습니다! ");
			else if( mGrade == dGrade) {
				if( mGradeList.get(mGrade)[0] > dGradeList.get(dGrade)[0])  System.out.println("승리하였습니다! ");
				else if ( mGradeList.get(mGrade)[0] < dGradeList.get(dGrade)[0])  System.out.println("패배하였습니다! ");
				if(mGradeList.get(mGrade)[0] == dGradeList.get(dGrade)[0]) System.out.println("비겼습니다!");
				//==에서 또 [1]비교하기
				
			}
			else  System.out.println("졌습니다!");
			

			if(regame()==2) break;;
		
		}
		System.out.println("종료하였습니다.");
	}
}
