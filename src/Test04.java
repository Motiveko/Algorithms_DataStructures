import java.util.Scanner;

public class Test04 {

	
	public static void main(String[] args) {
		
//		int a = 8;
//		if(a>10) {
//			System.out.println("a가 10보다 크다.");
//			
//		}else if(a>5) {
//			System.out.println("a가 5보다 크다.");
//		} else {
//			System.out.println("a는 5보다 작다");
//		}
//		

		
		int point = 75;
		String grade;
		
		if(point>90) {
			grade = "A";
		} else if(point>80) {
			grade = "B";
		} else if(point>70) {
			grade = "C";
		} else if(point>60) {
			grade = "D";
		} else {
			grade = "F";
		}
		
		System.out.println("당신의 학점은 : " + grade + " 입니다.");
	}
	
}
