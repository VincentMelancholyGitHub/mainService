package com.cjx.utils;

public class DateUtil {

	/**
	 * 获得年份字符串
	 * 
	 * @param date 格式应该为  year-month-day
	 * @return
	 */
	public static String getYear(String date)
	{
		String[] dateArr = date.split("-");
		return dateArr[0];
	}
	
}
