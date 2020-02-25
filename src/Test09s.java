
public class Test09s {
	String s = "Hi";
	/*
	public Test09s() {
		System.out.println("기본생성자 호출");
	}
	*/
	public Test09s(String a) {
		System.out.println("추가생성자 호출");
		s = a;
	}
	
	public void test() {
		System.out.println("첫 메소드 생성!");
	}
	
	public int plus(int a , int b) {
		return a+b;
	}
	
	public int minus(int a, int b) {
		return a-b;
	}
	public int time(int a, int b) {
		return a*b;
	}
	public double divide(int a, int b) {
		if(b==0) throw new IllegalArgumentException("can't divide with 0");
		return (double) a/b ;
	}
	
}
