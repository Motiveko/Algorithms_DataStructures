
public class Test09 {

	public static void main(String[] args) {
		Test09s ts = new Test09s("Hello!");
		System.out.println(ts);
		System.out.println(ts.s);

		ts.test();
		
		System.out.println(ts.plus(1, 2));
		System.out.println(ts.minus(1, 2));
		System.out.println(ts.time(1, 2));
		System.out.println(ts.divide(1, 2));

	}
}
