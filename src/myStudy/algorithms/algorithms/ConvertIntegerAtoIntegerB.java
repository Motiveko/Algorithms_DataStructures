package myStudy.algorithms.algorithms;

//EASY
//Determine the number of bits required to convert integer A to integer B 
public class ConvertIntegerAtoIntegerB {

	public static int solve(int a, int b) {
		
		int count = 0;
		//mycode!
//		for(int i=0; i<32; i++) if( ((a^b) >> i ) % 2 ==1) count ++; 
		for(int i=0; i<32; i++) count += ((a ^ b) >> i) & 1;
		return count;
	}
	
	
	public static void main(String[] args) {
		System.out.println(solve(15,1));
	}
	
}
