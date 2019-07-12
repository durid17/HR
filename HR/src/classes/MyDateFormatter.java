package classes;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

/**
 * The Class MyDateFormatter.
 */
public class MyDateFormatter {
	
	/** The Constant SINGLE_DAY. */
	public static final int SINGLE_DAY = 86400000;
	
	/**
	 * Truncate.
	 *
	 * @param date the date
	 * @return the date
	 */
	public static Date truncate(Date date) {
		if(date == null) return null;
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
		try {
			return new Date(formatter.parse(date.toString()).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * Days between.
	 *
	 * @param from the from
	 * @param to the to
	 * @return the long
	 */
	public static long daysBetween (Date from, Date to) {
		long diff = to.getTime() - from.getTime();
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}
	
	/**
	 * Years between.
	 *
	 * @param from the from
	 * @param to the to
	 * @return the int
	 */
	@SuppressWarnings("deprecation")
	public static int yearsBetween (Date from, Date to) {
		return to.getYear() - from.getYear();
	}
}
