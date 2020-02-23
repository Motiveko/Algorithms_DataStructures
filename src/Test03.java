import java.util.Scanner;
import java.util.StringTokenizer;

public class Test03 {

	public static void main(String[] args) {
		
		int a = 4;
		int b = 2;
		
		System.out.println(a+b);
		System.out.println(a-b);
		System.out.println(a*b);
		System.out.println(a/b);
		System.out.println(a%b);
		
		a=a+1;
		a+=1;
		a++;
		System.out.println(a);

		System.out.println(++a);
		System.out.println(a++);

		System.out.println(--a);
		System.out.println(a--);
		
		System.out.println(a > b);
		System.out.println(a < b);
		System.out.println(a >= b);
		System.out.println(a <= b);
		System.out.println(a == b);
		System.out.println(a != b);

		int c = 10;
		int d = 20;
		String e = "30";
		
		System.out.println(c + d + e);
		System.out.println(e + d + c);
		System.out.println(e + (d+c));

		//Tokenizer
//		Scanner scan = new Scanner(System.in);
//		String str = scan.next();
//		StringTokenizer st = new StringTokenizer("rhehdrl smswk");
//
//		while(st.hasMoreTokens()) {
//			System.out.println(st.nextToken());
//		}

		//concat
		String str = "고동기는";
		str = str.concat(" 천재입니다.");
		 System.out.println(str);
		
		
	}
}
