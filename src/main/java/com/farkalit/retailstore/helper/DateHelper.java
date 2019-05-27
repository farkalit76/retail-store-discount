/**
 * 
 */
package com.farkalit.retailstore.helper;

import java.util.Calendar;
import java.util.Date;

/**
 * @File name: DateHelper.java This class .....
 *
 * @author name: Farkalit Usman (S785410)
 * @Created on: 26 May 2019
 */
public class DateHelper {

	private DateHelper() {
	}

	/**
	 * 
	 * @param year
	 * @param month
	 * @param date
	 * @return
	 */
	public static Date getDate(int year, int month, int date) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.DAY_OF_MONTH, date);
		return cal.getTime();
	}
}
