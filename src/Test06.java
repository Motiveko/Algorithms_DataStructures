
public class Test06 {

	public static void main(String[] args) {
		
//		int a = 1;
//		
//		while( a< 10) {
//			System.out.println(a);
//			a++;
//		}
//		
//		do {
//			System.out.println(a);
//			a++;
//		}while(a<10);
//	
//		for(int i=1;i<10;i+=2) {
//			System.out.println(i);
//		}
//
//		for(int i=1; i<10 ;i++) {	
//			if(i %2 == 1) System.out.println(i);
//		}
//		
		
		
//		StringBuffer sb = new StringBuffer();
//		for(int i =1; i<=9 ; i++) {
//			int ans = 2*i;
//			//System.out.println(i*2);
//			
//			String str = String.format("2 * %d = %2d \n", i,ans);
//			sb.append(str);
//		}
//		System.out.println(sb.toString());

		
		System.out.println("=====================================================================");
		
		for(int i = 1; i<=9; i++) {
			System.out.println("2 * " + i + " = " + 2*i);
		}
		
		
		//SUM
		int sum = 0;
		for( int i = 1; i<=100; i++) {
			sum +=i; 
		}
		System.out.println(sum);

		
		//피보나치 수열
		int prev=0;
		int curr=1;
		int next;
		for(int i=1;i<=20;i++) {
			System.out.println("count " + i + " : " + curr);
			next=curr+prev;
			prev=curr;
			curr=next;
		}
		
	}
}
