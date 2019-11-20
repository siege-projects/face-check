package com.study.apphcpzj.base.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * @author ztt : 2018-05-28
 */
public class DateTimeUtils {

	public final static String FORMAT_DATE = "yyyy-MM-dd";

	/**
	 * 获得月初
	 * @return 2018-05-28
	 */
	public static String getMonthStart() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT_DATE, Locale.getDefault());
		return dateFormat.format(calendar.getTime());
	}

	/**
	 * 获得月末
	 *
	 * @return 2018-05-28
	 */
	public static String getMonthEnd() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT_DATE, Locale.getDefault());
		return dateFormat.format(calendar.getTime());
	}

	/**
	 * 获得年初
	 *
	 * @return 2018-05-28
	 */
	public static String getYearStart() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getMinimum(Calendar.DAY_OF_MONTH));
		calendar.set(Calendar.MONTH, calendar.getMinimum(Calendar.MONTH));
		SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT_DATE, Locale.getDefault());
		return dateFormat.format(calendar.getTime());
	}

	public static String getTodayStart() {
		SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT_DATE, Locale.getDefault());
		Calendar calendar = Calendar.getInstance();
		Calendar calendar2 = Calendar.getInstance();
		calendar2.clear();
		calendar2.set(Calendar.YEAR,calendar.get(Calendar.YEAR));
		calendar2.set(Calendar.MONTH,calendar.get(Calendar.MONTH));
		calendar2.set(Calendar.DAY_OF_MONTH,calendar.get(Calendar.DAY_OF_MONTH));
		return dateFormat.format(calendar.getTime());
	}

	public static long getTodayStartTime() {
		SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT_DATE, Locale.getDefault());
		Calendar calendar = Calendar.getInstance();
		Calendar calendar2 = Calendar.getInstance();
		calendar2.clear();
		calendar2.set(Calendar.YEAR,calendar.get(Calendar.YEAR));
		calendar2.set(Calendar.MONTH,calendar.get(Calendar.MONTH));
		calendar2.set(Calendar.DAY_OF_MONTH,calendar.get(Calendar.DAY_OF_MONTH));
		return calendar2.getTime().getTime();
	}

}
