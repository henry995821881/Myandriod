package com.henry.util;

import android.annotation.SuppressLint;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressLint("SimpleDateFormat")
public class DateUtil {

	// Thu Apr 14 15:25:46 +0800 2016

	public static String getRelativeDate(String string) {
		String[] splits = null;
		String dataString = null;
		String result = null;

		if (string != null && !"".equals(string.trim())) {

			splits = string.split(" ");

			dataString = handler(splits);
		}

		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");

		try {

			result = handlerResult(new Date(), dateFormat.parse(dataString));

		} catch (ParseException e) {

			e.printStackTrace();
		}

		return result;
	}

	/**
	 * 
	 * 
	 * 
	 * 
	 * @param date
	 * @param parse
	 * @return
	 */
	private static String handlerResult(Date date, Date parse) {

		long minus = date.getTime() - parse.getTime();

		long days = minus / (1000 * 60 * 60 * 24);
		long hours = minus / (1000 * 60 * 60);
		long min = minus / (1000 * 60);
		//Log.i("henry",days+" "+hours+" "+min);

		if (days >= 1) {

			return days + "天前";

		}

		if (hours >= 1) {
			
			
			return hours + "小时前";
		}

		if (min >= 1) {

			return min + "分钟前";

		} else {

			return "1分钟";
		}

	}

	// "yyyy-MM-dd HH:mm:ss"
	// Thu Apr 14 15:25:46 +0800 2016
	private static String handler(String[] splits) {

		StringBuffer sb = new StringBuffer();

		sb.append(splits[5]).append("-");
		// sb.append mone
		setMonth(splits, sb);

		sb.append(splits[2]).append(" ");
		sb.append(splits[3]);

		return sb.toString();
	}

	/**
	 * 
	 * 
	 * 
	 * @param splits
	 * @param sb
	 */
	private static void setMonth(String[] splits, StringBuffer sb) {

		if ("Jan".equals(splits[1])) {

			sb.append("01").append("-");
		} else if ("Feb".equals(splits[1])) {
			sb.append("02").append("-");
		} else if ("Mar".equals(splits[1])) {
			sb.append("03").append("-");
		} else if ("Apr".equals(splits[1])) {
			sb.append("04").append("-");
		} else if ("May".equals(splits[1])) {
			sb.append("05").append("-");
		} else if ("Jun".equals(splits[1])) {
			sb.append("06").append("-");
		} else if ("Jul".equals(splits[1])) {
			sb.append("07").append("-");
		} else if ("Aug".equals(splits[1])) {
			sb.append("08").append("-");
		} else if ("Sep".equals(splits[1])) {
			sb.append("09").append("-");
		} else if ("Oct".equals(splits[1])) {
			sb.append("10").append("-");
		} else if ("Nov".equals(splits[1])) {
			sb.append("11").append("-");
		} else if ("Dec".equals(splits[1])) {
			sb.append("12").append("-");
		} else {
			sb.append("01").append("-");
		}

	}

	/*
	 * public class Yugi
	 * 
	 * 
	 * 月：January 简写:Jan.
	 * 
	 * 二 月：February 简写:Feb.
	 * 
	 * 三 月：March 简写:Mar.
	 * 
	 * 四 月：April 简写:Apr.
	 * 
	 * 五 月：May 简写:May.
	 * 
	 * 六 月：June 简写:Jun.
	 * 
	 * 七 月：July 简写:Jul.
	 * 
	 * 八 月：August 简写:Aug.
	 * 
	 * 九 月：September 简写:Sep.
	 * 
	 * 十 月：October 简写:Oct.
	 * 
	 * 十一月：November 简写:Nov.
	 * 
	 * 十二月：December 简写:Dec. { public static void main(String[] args) throws
	 * ParseException { String reg =
	 * "(\\d+)/([a-zA-Z]+)/(\\d+):(\\d+:\\d+:\\d+\\s+[+\\d]+)"; String[] months
	 * = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct",
	 * "Nov", "Dec" }; String d1 = "05/Aug/2015:14:54:37 +0800"; String m =
	 * d1.replaceAll(reg, "$2"); int index = -1; for(int i = 0; i <
	 * months.length; i++) { if(m.equals(months[i])) { index = i + 1; break; } }
	 * String d2 = d1.replaceAll(reg, "$3-"+index+"-$1 $4");
	 * System.out.println(d2); SimpleDateFormat sdf = new
	 * SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");
	 * System.out.println(sdf.parse(d2)); } }
	 */
}
