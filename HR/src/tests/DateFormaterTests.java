package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.junit.jupiter.api.Test;

import classes.MyDateFormatter;

// TODO: Auto-generated Javadoc
/**
 * The Class DateFormaterTests.
 */
class DateFormaterTests {

	/**
	 * Test years between of two dates.
	 */
	@Test
	void test() {
		Date date = null;
		assertEquals(MyDateFormatter.truncate(date), null);
		DateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
		
		Date d = new Date(2000, 2, 3);
		Date d1 = convertUtilToSql(new java.util.Date(2000, 2, 3, 13, 50));
		assertEquals(d , MyDateFormatter.truncate(d1));
		
		d1 = new Date(2000, 2, 3);
		Date d2 = new Date(2001, 2, 3);
		assertEquals(1, MyDateFormatter.yearsBetween(d1, d2));
		assertEquals(365, MyDateFormatter.daysBetween(d1, d2));		

		
	}

	/**
	 * Convert util to sql.
	 *
	 * @param uDate the u date
	 * @return the java.sql. date
	 */
	private static java.sql.Date convertUtilToSql(java.util.Date uDate) {
		java.sql.Date sDate = new java.sql.Date(uDate.getTime());
		return sDate;
	}
}
