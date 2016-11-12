/**
 * 
 */
package com.wiseboys.mmhackathon.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * All util functions
 * 
 * @author Irudaya Raj Nov 11, 2016 2016
 */
public class Utils {

	public static String encodeToUtf8(String rawString) {
		try {
			return URLEncoder.encode(rawString, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return rawString;
	}

	/**
	 * Get date object from string
	 * @param stringDate
	 * @param sourceDateFormat
	 * @return Date object
	 */
	public static Date getDateFromString(String stringDate, String sourceDateFormat) {
		SimpleDateFormat formatter = new SimpleDateFormat(sourceDateFormat);
		try {
			Date date = formatter.parse(stringDate);
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Get current date
	 * @param sourceDateFormat
	 * @return return current date in dd/MM/yyyy hh:mm:ss a format
	 */
	public static Date getCurrentDateWithFormat(String sourceDateFormat) {
		String currentDate = new SimpleDateFormat(sourceDateFormat).format(new Date());
		return getDateFromString(currentDate, sourceDateFormat);
	}
	/**
	 * Get hour difference between two dates
	 * @param date1
	 * @param date2
	 * @return hours
	 */
	public static int getHourDifferenceBetweenDates(Date date1, Date date2) {
		final int MILLI_TO_HOUR = 1000 * 60 * 60;
	    return (int) (date1.getTime() - date2.getTime()) / MILLI_TO_HOUR;
	}
	
}
