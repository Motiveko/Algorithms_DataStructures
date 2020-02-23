
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Practice_Date {

	public static void main(String[] args) {
		
		Date date = new Date();
		Date date2 = Calendar.getInstance().getTime();
//		Timestamp ts = new Timestamp(date.getTime());
//		System.out.println(ts);

		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
		String strDate = dateFormat.format(date.getTime());
		
		System.out.println(strDate);
	}
}
