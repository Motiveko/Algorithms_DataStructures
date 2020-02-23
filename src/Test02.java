import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.SimpleFormatter;

public class Test02 {
	
	public static void main(String[] args) {
	
		int a = 3;
		
		float f= (float) 0.1;
		long l = 233333413333L;
		
		boolean b = true;
		
		char c = 'A';
		
		String s = "abc";
		
		System.out.println(a);
		System.out.println(f);
		System.out.println(b);
		System.out.println(c);
		System.out.println(s);
		
//		Date date = Calendar.getInstance().getTime();
//		String s1 = date.toString();
//		DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm-ss");
//		s1 = dateFormat.format(date);
//		System.out.println(s1);
	}
	
}
