package classes;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class MyDateFormatter {
	public static Date truncate(Date date) {
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
		try {
			return new Date(formatter.parse(date.toString()).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
}
