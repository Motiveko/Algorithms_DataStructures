
import java.io.IOException;
import java.util.Scanner;

public class Test01 {

	public static void main(String[] args) throws IOException {
		
		System.out.println("Hello World");
		
		Scanner stdIn = new Scanner(System.in);
		StringBuffer[] sb = new StringBuffer[2];
		int[] num = new int[2];
		for(int i = 0; i<2; i++) {
			sb[i] = new StringBuffer(stdIn.next());
			sb[i].reverse();
			num[i]=Integer.parseInt(sb[i].toString());
		}
		
		int bigger = (num[0]>num[1]) ? num[0] : num[1];
		System.out.println(bigger);
		
	}
}
