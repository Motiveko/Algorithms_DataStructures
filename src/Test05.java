
public class Test05 {


	public static void main(String[] args) {
		
		int a=2;
		
		switch(a) {
			case 1 : 
				System.out.println("1입니다.");
				break;
			case 2 :
				System.out.println("2입니다");
				break;
			default : 
				System.out.println("1도 2도 아닙니다.");
		}
		
		
		int score = 66;
		char grade;
		String grade2;
		switch(score/10) {
		case 10:
			grade = 'A';
			break;
		case  9: 
			grade = 'A';
			break;
		case 8 :
			grade = 'B';
			break;
		case 7 :
			grade = 'C';
			break;
		case 6 : 
			grade = 'D';
			break;
		default :
			grade = 'F';
		}
		
		System.out.println(grade);
		
		switch(score/5) {
		
		case 20:
			grade2 = "A+";
			break;
		case 19:
			grade2 = "A+";
			break;
		case 18:
			grade2 = "A";
			break;
		case 17:
			grade2 = "B+";
			break;
		case 16:
			grade2 = "B";
			break;
		case 15:
			grade2 = "C+";
			break;
		case 14:
			grade2 = "C";
			break;
		case 13:
			grade2 = "D+";
			break;
		case 12:
			grade2 = "D";
			break;
		default : grade2="F";
			
		}
		
		System.out.println(grade2);
		
	}
}
