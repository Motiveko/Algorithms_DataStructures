import java.awt.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Exam01 {

	public static void main(String[] args) {
		
		int a=7;
		int b=5;
		int c=3;
		int d;
/*		
			if(a>b) {
				if(c>a) {
					d=a; a=b;b=d;
				} else if(c>b) {
					d=a; a=b; b=c; c=d;
				} else {
					d=a; a=c;c=d;
				}
			} else {
				if(c>b) {
					return;
				} else if(c>a) {
					d=b; b=c; c=d;
				} else {
					d=a; a=c; c=b; b=d;
				}
			}
*/
		if(a>b) {
			d=a; a=b; b=d;
		}
		if(a>c) {
			d=a; a=c; c=d;
		}
		if(b>c) {
			d=b; b=c;c=d;
		}
		
		
		System.out.println(a); //3
		System.out.println(b); //5
		System.out.println(c); //7
				
	}

}
