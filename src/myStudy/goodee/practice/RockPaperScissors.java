package myStudy.goodee.practice;

import java.util.*;

//가위바위보 게임

public class RockPaperScissors {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		Random ran = new Random();
		
		while(true) {
			
			System.out.println("======가위바위보 게임을 시작합니다.======");
			System.out.println("=======무슨 패를 내겠습니까?=======");
			System.out.println("1. 가위   2. 바위   3. 보");
			
			int user;
			int sys;
			while(true) {
				try {
					user = sc.nextInt();
					sys = ran.nextInt(3) + 1;
				} catch(Exception e){
					System.out.println("1,2,3 중 한가지 숫자를 입력하세요.");
					continue;
				}
				if( user < 1 || user > 3) {
					System.out.println("1,2,3 중 한가지 숫자를 입력하세요.");
					continue;
				}
				break;
			}
			
			System.out.println("사용자 : " + (user==1 ? "가위" : user==2 ? "바위" : "보"));
			System.out.println("시스템 : " + (sys==1 ? "가위" : sys==2 ? "바위" : "보"));

			switch(user) {
				
			case 1:{
				if( sys == 1) System.out.println("비겼습니다!");
				else if ( sys == 2) System.out.println("졌습니다..");
				else System.out.println("이겼습니다!");
				break;
			}
			case 2:{
				if( sys == 1) System.out.println("이겼습니다!");
				else if( sys == 2) System.out.println("비겼습니다!");
				else System.out.println("겼습니다..");	
				break;
			} 
			default: {
				if(sys == 1) System.out.println("졌습니다..");
				else if(sys == 2) System.out.println("이겼습니다!");
				else System.out.println("비겼습니다!");
			}
			}
			
			System.out.println("더 하시겠습니까 ? ");
			System.out.println("더한다 : 아무키    종료 : 1");
			
			try {
				user = sc.nextInt();
			} catch(Exception e){
				continue;
			}
			if(user == 1) break;
			
		}
		System.out.println("종료하였습니다.");
	}
	
}
