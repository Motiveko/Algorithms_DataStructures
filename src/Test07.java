
public class Test07 {

	public static void main(String[] args) {
		
//		for(int i=0 ; i<=4 ; i++) {
//			for( int j = 0; j<=4 ; j++) {
//				System.out.println("i : " + i + ", j : " + j);
//			}
//		}
//		
		
//		for(int i=2; i<=9; i++) {
//			System.out.println("=====" + i + "단=====");
//			for( int j = 1; j<=9; j++) {
//				System.out.println(i + " * " + j + " = " + i*j);
//			}
//		}
		System.out.println("1.");
		for(int i=1; i<=5 ; i++) {
			for(int j=1; j<=i; j++) System.out.print("*");
			System.out.println();
		}
		
		System.out.println("2.");
		for(int i=5; i>=1; i--) {
			for(int j=i ; j>0; j--) System.out.print("*");
			System.out.println();
		}
		
		System.out.println("3.");
		int n=5;
		for(int i=1; i<=n; i++) {
			// blank
			for(int j=n-i;j>0;j--) System.out.print(" ");
			// star
			for(int j=1; j<=i; j++) System.out.print("*");
			// \n
			System.out.println();
		}
		System.out.println();
		System.out.println("4.");
		
		for( int i=1; i<=n; i++) {
			// blank
			for(int j =1; j<i; j++) System.out.print(" ");
			// star
			for(int j=i; j<=n; j++) System.out.print("*");
			System.out.println();
		}

		System.out.println("5.");
	
		for(int i=1; i<=n; i++) {
			for( int j= 1; j<=n-i;j++ ) System.out.print(" ");
			for( int j =1; j<=(2*i-1);j++) System.out.print("*");
			System.out.println();
		}
	}
}
