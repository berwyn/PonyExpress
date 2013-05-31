package org.codeweaver.ponyexpress.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Utility class to convert to/from ISO 8906 date strings
 * 
 * @author Jamison Greeley <berwyn.codeweaver@gmail.com>
 * @since 0.1.0
 */
public class ISO8906 {

	private static DateFormat	dateFormat	= new SimpleDateFormat(
													"yyyy-MM-dd'T'HH:mm:ssZ");

	public static String toString(Date date) {
		return dateFormat.format(date);
	}

	public static Date fromString(String iso8906) {
		try {
			return dateFormat.parse(iso8906);
		} catch (ParseException e) {
			return null;
		}
	}

}
