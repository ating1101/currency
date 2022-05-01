package com.crm.currency.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

/**
 * 跟日期相關的一些通用 method
 * 
 * @author
 *
 */
public class DateUtil {

	// private static ILogger logger =
	// com.fet.generic.logger.LoggerFactory.getLogger(DateUtil.class);

	/**
	 * 預設日期格式 - 日期部分 yyyy/MM/dd
	 */
	public static final String FORMAT_Date = "yyyy/MM/dd";

	/**
	 * 預設日期格式 - 日期部分 yyyy-MM-dd
	 */
	public static final String FORMAT_Date_ = "yyyy-MM-dd";
	/**
	 * 預設日期格式 - 時間部分
	 */
	public static final String FORMAT_Time = "HH:mm:ss";
	/**
	 * 預設日期格式 - 時間部分 只有時、分
	 */
	public static final String FORMAT_TIME_HHMM = "HH:mm";
	/**
	 * 預設日期格式 - 日期+時間
	 */
	public static final String FORMAT_DateTime = "yyyy/MM/dd HH:mm:ss";

	/**
	 * 預設日期格式 - 日期+時間
	 */
	public static final String FORMAT_DateTime_1 = "yyyy-MM-dd HH:mm:ss";
	/**
	 * 預設日期格式 - 日期+時間 沒有分號
	 */
	public static final String FORMAT_DATETIME_2 = "yyyyMMddHHmmss";
	/**
	 * 預設日期格式 - 日期+時間+毫秒第1位 yyyy-MM-dd HH:mm:ss.S
	 * 
	 */
	public static final String FORMAT_DATETIME_3 = "yyyy-MM-dd HH:mm:ss.S";
	/**
	 * 預設日期格式 - 日期+時間
	 */
	public static final String FORMAT_DateTime_4 = "yyyyMMdd HH:mm:ss";
	/**
	 * 預設日期格式 - 日期+時間 24小時制
	 */
	public static final String FORMAT_DATETIME_24MI = "YYYYMMDDHH24MI";

	/**
	 * 預設日期格式 - 日期+時間部分 只有時、分
	 */
	public static final String FORMAT_DateTime_HHMM = "yyyy/MM/dd HH:mm";

	/**
	 * 預設日期格式 - 日期+月部
	 */
	public final static String FORMAT_DATE_YYYYMM = "yyyyMM";

	/**
	 * 預設日期格式 - 西元年月日 yyyyMMdd
	 */
	public final static String FORMAT_DATE_YYYYMMDD = "yyyyMMdd";

	/**
	 * 預設日期格式 - 西元年月日時分 yyyyMMddHHmm
	 */
	public final static String FORMAT_DATE_YYYYMMDDHHMM = "yyyyMMddHHmm";

	/**
	 * 預設日期格式 - 西元年月日 d-MMM-yyyy HH:mm:ss
	 */
	public final static String FORMAT_TIME_dMMMyyy = "d-MMM-yyyy HH:mm:ss";

	/**
	 * 預設日期格式 - 西元年月日 d-MMM-yyyy
	 */
	public final static String FORMAT_DATE_dMMMyyy = "d-MMM-yyyy";

	/**
	 * 預設日期格式 - 西元年月日 d-MMM-yyyy
	 */
	public final static String FORMAT_DATE_dMMXyyy = "d-MM月-yyyy";

	/**
	 * 預設日期格式 - 西元年月日 d-MMM-yyyy HH:mm:ss
	 */
	public final static String FORMAT_TIME_YYYYMMDDHHMMSS = "yyyy-MM-dd/HH:mm:ss";

	/**
	 * 預設日期格式 - yyyyMMddHHmmssSS
	 */
	public final static String FORMAT_TIME_YYYYMMDDHHMMSSSS = "yyyyMMddHHmmssSS";

	/**
	 * PDP LOG指定的日期格式
	 */
	public static final String PDP_TIME_FORMAT = "yyyy/MM/dd hh:mm:ss";

	/**
	 * crmPaymentApi invoiceDueDate所需日期格式
	 */
	public static final String CRM_TIME_FORMAT = "yyyy-MM-dd'T'hh:mm:ssZ";

	/**
	 * 日期格式 到毫秒 - yyyyMMddHHmmssSSS
	 */
	public final static String FORMAT_TIME_YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";
	/**
	 * crm回傳格式 (Ex:2017-04-26T00:00:00+08:00)
	 */
	public static final String FORMAT_TIME_CRM = "yyyy-MM-dd'T'hh:mm:ssXXX";
	/**
	 * crmCustOrg回傳格式 (Ex:2017-04-26T00:00:00)
	 */
	public static final String FORMAT_TIME_CRM_CUST_ORG = "yyyy-MM-dd'T'HH:mm:ss";

	public static final String FORMAT_DB_DATE = "yyyy-MM-dd.HH.mm";

	/**
	 * CRMCEMBizService API 所需日期格式
	 */
	public static final String FORMAT_CEM = "yyyy/MM/dd'T'HH:mm:ss";
	public static final String FORMAT_CEM_EVENT_DATE = "MM/dd HH:mm";

	/**
	 * 預設日期格式 - yyyyMMdd
	 */
	public final static String FORMAT_DATE_yyMMdd = "yyMMdd";

	/**
	 * 日期格式 到毫秒 - yyyy-MM-dd'T'HH:mm:ss.SSS
	 */
	public final static String FORMAT_TIME_YYYYMMDDTHHMMSSSSS = "yyyy-MM-dd'T'HH:mm:ss.SSS";

	/**
	 * 日期格式 到毫秒 - yyyy-MM-dd HH:mm:ss.SSS
	 */
	public final static String FORMAT_TIME_YYYYMMDDTHHMMSSSSS_2 = "yyyy-MM-dd HH:mm:ss.SSS";
	/**
	 * 日期格式 到毫秒 - yyyy-MM-dd'T'HH:mm:ss.SSS
	 */
	public final static String FORMAT_TIME_YYYYMMDD_HHMMSSSSS = "yyyy-MM-dd_HH-mm-ss.SSS";

	/**
	 * 轉換為中文格式 - 年月日時分
	 */
	public final static String FORMAT_TIME_CHT_STRING = "yyyy年MM月dd日 HH:mm";

	/**
	 * 轉換為中文格式 - 年月日
	 */
	public final static String FORMAT_TIME_CHT_DATE_STRING = "yyyy年MM月dd日";

	/**
	 * 轉換為中文格式 - 年/月
	 */
	public final static String FORMAT_YYYYMM = "yyyy/MM";

	/**
	 * 轉換為中文格式 - 年-月
	 */
	public final static String FORMAT_YYYY_MM = "yyyy-MM";

	/**
	 * HHmmss 時分秒
	 */
	public final static String FORMAT_TIME_HHMMSS = "HHmmss";

	private DateUtil() {

	}

	/**
	 * 將日期物件轉成字串 (將套用預設格式)
	 * 
	 * @param date : 日期物件
	 * @return
	 */
	public static String format(Date date) {
		return format(date, FORMAT_DateTime);
	}

	/**
	 * 將日期物件轉成字串 (將套用預設格式)
	 * 
	 * @param date : 日期物件
	 * @return
	 */
	public static String formatDate(Date date) {
		return format(date, FORMAT_Date);
	}

	/**
	 * 將日期物件轉成字串
	 * 
	 * @param date   : 日期物件
	 * @param format : 格式 (傳入 null 的話, 將套用預設格式)
	 * @return
	 */
	public static String format(Date date, String format) {

		String result = null;

		try {
			if (date != null) {
				SimpleDateFormat simple = null;
				if (format != null) {
					simple = new SimpleDateFormat(format);
				} else {
					simple = new SimpleDateFormat(FORMAT_DateTime);
				}
				result = simple.format(date);
			}
		} catch (Exception e) {
			LogUtil.error(DateUtil.class, "", e.getMessage(), e);
		}

		return result;
	}

	/**
	 * 把日期格式的字串轉成日期物件 (將套用預設格式來做轉換)
	 * 
	 * @param date : 日期字串
	 * @return
	 */
	public static Date parse(String date) {
		return parse(date, FORMAT_DateTime);
	}

	/**
	 * 把日期格式的字串轉成日期物件
	 * 
	 * @param date   : 日期字串
	 * @param format : 日期字串的格式 (傳入 null 的話, 將套用預設格式來做轉換)
	 * @return
	 */
	public static Date parse(String date, String format) {

		Date result = null;

		try {
			if (date != null && !date.trim().equalsIgnoreCase("")) {
				SimpleDateFormat simple = null;
				if (format != null) {
					simple = new SimpleDateFormat(format);
				} else {
					simple = new SimpleDateFormat(FORMAT_DateTime);
				}
				result = simple.parse(date);
			}
		} catch (Exception e) {
			LogUtil.error(DateUtil.class, "", e.getMessage(), e);
		}

		return result;
	}

	/**
	 * 輸入年、月、日，轉換出輸入的日期物件
	 * 
	 * @param year
	 * @param month
	 * @param date
	 * 
	 * @return Date 指定的日期
	 */
	public static Date parse(int year, int month, int date) {

		Date result = null;

		try {
			Calendar calender = Calendar.getInstance();
			calender.set(year, month, date);

			result = calender.getTime();
		} catch (Exception e) {
			LogUtil.error(DateUtil.class, "", e.getMessage(), e);
		}

		return result;
	}

	/**
	 * 把日期格式的字串轉成Calendar物件
	 * 
	 * @param date : 日期字串
	 * @return
	 */
	public static Calendar parseCalendar(String date, String format) throws Exception {
		Calendar cal = Calendar.getInstance();
		cal.setTime(parse(date, format));
		return cal;
	}

	/**
	 * 將 Date 物件轉成 Calendar 物件
	 */
	public static Calendar toCalendar(Date date) {
		Calendar cal = null;

		if (date != null) {
			cal = Calendar.getInstance();
			cal.setTime(date);
		}

		return cal;
	}

	/**
	 * 取得 Date 年
	 */
	public static int getYear(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.YEAR);
	}

	/**
	 * 取得 Date 月
	 */
	public static int getMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.MONTH) + 1;
	}

	/**
	 * 取得 Date 日
	 */
	public static int getDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * String轉換日期顯示格式
	 * 
	 * @param DateStr   : 日期
	 * @param oldFormat : 原日期格式 (傳入 null 的話, 將套用預設格式)
	 * @param newFormat : 新日期格式 (傳入 null 的話, 將套用預設格式)
	 * @return
	 */
	public static String changeDateFormat(String DateStr, String oldFormat, String newFormat) {
		String result = null;
		Date date = parse(DateStr, oldFormat);
		result = format(date, newFormat);
		return result;
	}

	public static Date TimestampToDate(Timestamp timestamp) {
		Date result = null;
		if (timestamp != null) {
			result = new Date(timestamp.getTime());
		}
		return result;
	}

	/**
	 * @param 天數轉成毫秒
	 * @return
	 */
	public static long dayToSecond(String day) {
		long daySeconds = 0;
		if (!StringUtils.isEmpty(day)) {
			daySeconds = Integer.valueOf(day) * 24 * 60 * (long) 60;
		}

		return daySeconds;
	}
}
