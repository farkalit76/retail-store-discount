/**
 * 
 */
package com.farkalit.retailstore.helper;

import java.util.Date;

/**
 * @File name: DateHelper.java
 * This class .....
 *
 * @author name: Farkalit Usman (S785410)
 * @Created on: 26 May 2019
 */
public class DateHelper {

	/**
	 * 
	 * @param year
	 * @param month
	 * @param date
	 * @return
	 */
	public static Date getDate( int year, int month, int date)
	{
		return new Date(year-1900, month-1, date);
	}
}
