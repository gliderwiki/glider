package org.gliderwiki.framework.util;


import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

public class DateUtil {
	public static Map monthName;

	/**
	 * 지정된 일자에서 특정 필드에 대한 값을 더하거나, 뺀 일자를 반환한다.
	 * 
	 * @return 변경된 일자
	 * @throws Exception
	 */
	public static Date addDate(Date pDate, int pField, int pAmount)
			throws Exception {

		if (pDate == null)
			pDate = new Date();

		Calendar ret = Calendar.getInstance();
		ret.setTime(pDate);
		ret.add(pField, pAmount);
		return ret.getTime();
	}

	/**
	 * 현재일자를 반환한다.(형식 yyyyMMdd)
	 * 
	 * @return 현재일자
	 * @throws Exception
	 */
	public static String getToday() throws Exception {
		return getDate(new Date(), "yyyyMMdd");
	}

	/**
	 * 현재일시간을 반환한다.(형식 yyyy-MM-dd HH:mm:ss)
	 * @return
	 * @throws Exception
	 */
	public static String getTodayTimeStamp() throws Exception{
		return getDate(new Date(), "yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * 현재시간을 반환한다.(형식 yyyy.MM.dd hh:mm:ss)
	 * 
	 * @return 현재 시간
	 * @throws Exception
	 */
	public static String getTodayTime() {
		return getDate(new Date(), "yyyy.MM.dd HH:mm:ss");
	}
	
	/**
	 * 현재 시간을 반환한다.(형식 yyyyMMddHHmmss);
	 * @return
	 * @throws Exception
	 */
	public static String getCurrentTime() throws Exception {
		Calendar dateTime = Calendar.getInstance();

		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String str = formatter.format(dateTime.getTime());

		return str;
	}

	/**
	 * 현재시간을 반환한다.(형식 yyyy/MM/dd hh:mm:ss)
	 * 
	 * @return 현재 시간
	 * @throws Exception
	 */
	public static String getTodayTimeForDB() throws Exception {
		return getDate(new Date(), "yyyy/MM/dd HH:mm:ss");
	}

	/**
	 * 현재일자를 지정한 형식으로 반환한다.
	 * 
	 * @return 현재일자
	 * @throws Exception
	 */
	public static String getToday(String pFormat) throws Exception {
		return getDate(new Date(), pFormat);
	}

	/**
	 * Date 타입의 날짜를 지정한 형식의 문자형 날짜로 반환한다.
	 * 
	 * @param pDate
	 *            Date 객체
	 * @param pFormat
	 *            SimpleDateFormat에 정의된 날짜형식
	 * @return 변경된 날짜
	 */
	public static String getDate(Date pDate, String pFormat) {

		if (pDate == null)
			return "";

		StringBuffer ret = new StringBuffer();
		new SimpleDateFormat(pFormat).format(pDate, ret, new FieldPosition(0));
		return ret.toString();
	}

	/**
	 * String 타입의 날짜를 지정한 형식의 Date 타입의 날짜로 반환한다.
	 * 
	 * @param strDate
	 *            String 타입의 날짜
	 * @param pFormat
	 *            SimpleDateFormat에 정의된 날짜형식
	 * @return 변경된 날짜
	 */
	public static Date getDate(String strDate, String pFormat) throws Exception {
		if (strDate == null)
			return null;

		return new SimpleDateFormat(pFormat).parse(strDate,
				new ParsePosition(0));
	}

	/**
	 * 지정된 날짜에서 원하는 일수 만큼 이동된 날짜를 반환한다.(디폴트형식 yyyyMMdd)
	 * 
	 * @param strDate
	 *            지정된 일자(String)
	 * @param offset
	 *            이동할 일수( -2147483648 ~ 2147483647 )
	 * @return 변경된 날짜
	 */
	public static String getOffsetDate(String strDate, int offset)
			throws Exception {
		SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
		Date date = fmt.parse(strDate); 
		return getOffsetDate(date, offset, "yyyyMMdd");
	}

	/**
	 * 지정된 날짜에서 원하는 일수 만큼 이동된 날짜를 반환한다.(형식 지정)
	 * 
	 * @param strDate
	 *            지정된 일자(String)
	 * @param offset
	 *            이동할 일수( -2147483648 ~ 2147483647 )
	 * @param pFormat
	 *            날짜형식
	 * @return 변경된 날짜
	 */
	public static String getOffsetDate(String strDate, int offset,
			String pFormat) throws Exception {
		SimpleDateFormat fmt = new SimpleDateFormat(pFormat);
		Date date = fmt.parse(strDate);
		return getOffsetDate(date, offset, pFormat);
	}

	/**
	 * 현재 날짜에서 원하는 일수 만큼 이동된 날짜를 반환한다.
	 * 
	 * @param offset
	 *            이동할 일수( -2147483648 ~ 2147483647 )
	 * @return 변경된 날짜
	 */
	public static String getOffsetDate(int offset) throws Exception {
		Date date = new Date();
		return getOffsetDate(date, offset, "yyyyMMdd");
	}

	/**
	 * 현재 날짜에서 원하는 일수 만큼 이동된 날짜를 반환한다.(형식 지정)
	 * 
	 * @param offset
	 *            이동할 일수( -2147483648 ~ 2147483647 )
	 * @param pFormat
	 *            날짜형식
	 * @return 변경된 날짜
	 */
	public static String getOffsetDate(int offset, String pFormat)
			throws Exception {
		Date date = new Date();
		return getOffsetDate(date, offset, pFormat);
	}

	/**
	 * 지정된 날짜에서 원하는 일수 만큼 이동된 날짜를 반환한다.(형식 지정)
	 * 
	 * @param pDate
	 *            Date 객체
	 * @param offset
	 *            이동할 일수( -2147483648 ~ 2147483647 )
	 * @param pFormat
	 *            날짜형식
	 * @return 변경된 날짜
	 */
	public static String getOffsetDate(Date pDate, int offset, String pFormat)
			throws Exception {
		SimpleDateFormat fmt = new SimpleDateFormat(pFormat);
		Calendar c = Calendar.getInstance();
		String ret = "";

		try {
			c.setTime(pDate);
			c.add(Calendar.DAY_OF_MONTH, offset);
			ret = fmt.format(c.getTime());
		} catch (Exception e) {
		}
		return ret;
	}

	/**
	 * 지정된 날짜에서 원하는 달수 만큼 이동된 날짜를 반환한다.(형식 지정)
	 * 
	 * @param pDate
	 *            Date 객체
	 * @param offset
	 *            이동할 달수
	 * @param pFormat
	 *            날짜형식
	 * @return 변경된 날짜
	 */
	public static String getOffsetMonth(Date pDate, int offset, String pFormat)
			throws Exception {
		SimpleDateFormat fmt = new SimpleDateFormat(pFormat);
		Calendar c = Calendar.getInstance();
		String ret = "";

		try {
			c.setTime(pDate);
			c.add(Calendar.MONTH, offset);
			ret = fmt.format(c.getTime());
		} catch (Exception e) {
		}
		return ret;
	}

	/**
	 * 지정된 날짜에서 원하는 달수 만큼 이동된 날짜를 반환한다.(형식 지정)
	 * 
	 * @param strDate
	 *            지정된 일자(String)
	 * @param offset
	 *            이동할 달수
	 * @param pFormat
	 *            날짜형식
	 * @return 변경된 날짜
	 */
	public static String getOffsetMonth(String strDate, int offset,
			String pFormat) throws Exception {
		SimpleDateFormat fmt = new SimpleDateFormat(pFormat);
		Date date = fmt.parse(strDate);
		return getOffsetMonth(date, offset, pFormat);
	}

	/**
	 * 지정된 날짜에서 원하는 달수 만큼 이동된 날짜를 반환한다.(형식 지정)
	 * 
	 * @param offset
	 *            이동할 달수
	 * @param pFormat
	 *            날짜형식
	 * @return 변경된 날짜
	 */
	public static String getOffsetMonth(int offset, String pFormat)
			throws Exception {
		Date date = new Date();
		return getOffsetMonth(date, offset, pFormat);
	}

	/**
	 * 지정된 날짜에서 원하는 일수 만큼 이동된 날짜를 반환한다.
	 * 
	 * @param pDate
	 *            Date 객체
	 * @param offset
	 *            이동할 일수( -2147483648 ~ 2147483647 )
	 * @return 변경된 날짜
	 */
	public static Date getOffsetDateToDate(Date pDate, int offset)
			throws Exception {
		Calendar c = Calendar.getInstance();
		Date ret = null;

		try {
			c.setTime(pDate);
			c.add(6, offset);
			ret = c.getTime();
		} catch (Exception e) {
		}
		return ret;
	}

	/**
	 * 지정된 날짜에서 원하는 일수 만큼 이동된 날짜를 반환한다.
	 * 
	 * @param strDate
	 *            Date 객체
	 * @param offset
	 *            이동할 일수( -2147483648 ~ 2147483647 )
	 * @return 변경된 날짜
	 */
	public static Date getOffsetDateToDate(String strDate, int offset)
			throws Exception {

		SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
		Date date = fmt.parse(strDate);

		return getOffsetDateToDate(date, offset);
	}

	/**
	 * 입력된 날짜형식을 지정된 형식으로 변경한다.
	 * 
	 * @param strDate
	 *            지정된 일자(String)
	 * @param pBeforeFormat
	 *            현재 날짜형식
	 * @param pAfterFormat
	 *            변경될 날짜형식
	 * @return 변경된 날짜
	 */
	public static String getChangedDateFormat(String strDate,
			String pBeforeFormat, String pAfterFormat) throws Exception {

		if (strDate == null || "".equals(strDate))
			return "";

		Date date = null;

		try {
			SimpleDateFormat fmt = new SimpleDateFormat(pBeforeFormat);
			date = fmt.parse(strDate);

		} catch (Exception e) {
			return "<font color=red>날짜 형식 에러 : " + e.toString() + "</font>";
		}

		return getDate(date, pAfterFormat);
	}

	/**
	 * 입력날짜가 현재일 보다 크면 true, 반대이면 false를 반환한다.
	 * 
	 * @param strDate
	 *            입력 날짜(문자형)
	 * @return 변경된 날짜
	 */
	public static boolean getCompareDate(String strDate) throws Exception {

		SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
		Date date = fmt.parse(strDate);

		return getCompareDate(date, null);
	}

	/**
	 * 입력된 두개의 날짜를 비교해서 첫번째 입력날짜가 두번째 입력날짜보다 크면 true, 반대이면 false를 반환한다.
	 * 
	 * @param strDate1
	 *            입력 날짜(문자형)
	 * @param strDate2
	 *            입력 날짜(문자형)
	 * @param pFormat
	 *            날짜형식
	 * @return 변경된 날짜
	 */
	public static boolean getCompareDate(String strDate1, String strDate2,
			String pFormat) throws Exception {

		SimpleDateFormat fmt = new SimpleDateFormat(pFormat);
		Date date1 = fmt.parse(strDate1);
		Date date2 = fmt.parse(strDate2);

		return getCompareDate(date1, date2);
	}

	/**
	 * 입력된 두개의 날짜를 비교해서 첫번째 입력날짜가 두번째 입력날짜보다 크면 true, 반대이면 false를 반환한다.
	 * 
	 * @param strDate1
	 *            입력 날짜(문자형)
	 * @param strDate2
	 *            입력 날짜(문자형)
	 * @return 변경된 날짜
	 */
	public static boolean getCompareDate(String strDate1, String strDate2)
			throws Exception {

		SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
		Date date1 = fmt.parse(strDate1);
		Date date2 = fmt.parse(strDate2);

		return getCompareDate(date1, date2);
	}

	/**
	 * 입력날짜가 현재일 보다 크면 true, 반대이면 false를 반환한다.
	 * 
	 * @param pDate
	 *            Date 객체
	 * @return 변경된 날짜
	 */
	public static boolean getCompareDate(Date pDate) throws Exception {

		return getCompareDate(pDate, null);
	}

	/**
	 * 입력된 두개의 날짜를 비교해서 첫번째 입력날짜가 두번째 입력날짜보다 작으면 true, 반대이면 false를 반환한다.
	 * 
	 * @param pDate1
	 *            Date 객체
	 * @param pDate2
	 *            Date 객체
	 * @return 변경된 날짜
	 */
	public static boolean getCompareDate(Date pDate1, Date pDate2)
			throws Exception {

		Calendar date1 = Calendar.getInstance();
		Calendar date2 = Calendar.getInstance();
		boolean ret = false;

		if (pDate1 == null)
			pDate1 = new Date();
		if (pDate2 == null)
			pDate2 = new Date();

		try {
			date1.setTime(pDate1);
			date2.setTime(pDate2);
			ret = date1.before(date2);
		} catch (Exception e) {
		}
		return ret;
	}

	/**
	 * 현재일자와 지정된 일자의 차이 일수를 반환한다.(형식 : yyyyMMdd)
	 * 
	 * @param strDate
	 *            지정된 일자
	 * @return 차이 일수
	 * @throws Exception
	 */
	public static int getOffsetDays(String strDate) throws Exception {

		SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
		Date param_date = fmt.parse(strDate);

		return getOffsetDays(param_date, new Date());
	}

	/**
	 * 현재일자와 지정된 일자의 차이 일수를 반환한다.(형식 : yyyyMMdd)
	 * 
	 * @param strDate
	 *            지정된 일자
	 * @return 차이 일수
	 * @throws Exception
	 */
	public static int getDefaultOffsetDays(String strDate) throws Exception {

		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date param_date = fmt.parse(strDate);

		return getOffsetDays(param_date, new Date());
	}

	/**
	 * 지정된 두 일자의 차이 일수를 반환한다.
	 * 
	 * @param pDate1
	 *            지정된 일자1
	 * @param pDate2
	 *            지정된 일자2
	 * @return 차이 일수
	 * @throws Exception
	 */
	public static int getOffsetDays(Date pDate1, Date pDate2) throws Exception {

		return (int) ((pDate1.getTime() - pDate2.getTime()) / (1000 * 60 * 60 * 24));
	}

	/**
	 * 지정된 두 일자의 차이 일수를 반환한다.
	 * 
	 * @param strDate
	 *            지정된 일자
	 * @param strDate2
	 *            지정된 일자
	 * @param pFormat
	 *            지정된 날짜 형식
	 * @return 차이 일수
	 * @throws Exception
	 */
	public static int getOffsetDays(String strDate, String strDate2,
			String pFormat) throws Exception {

		SimpleDateFormat fmt = new SimpleDateFormat(pFormat);
		SimpleDateFormat fmt2 = new SimpleDateFormat(pFormat);

		Date param_date1 = fmt.parse(strDate);
		Date param_date2 = fmt2.parse(strDate2);

		return getOffsetDays(param_date1, param_date2);
	}

	/**
	 * 지정된 두 일자의 차이 일수를 반환한다.(날짜 형식 : yyyyMMdd)
	 * 
	 * @param strDate
	 *            지정된 일자
	 * @param strDate2
	 *            지정된 일자
	 * @return 차이 일수
	 * @throws Exception
	 */
	public static int getOffsetDays(String strDate, String strDate2)
			throws Exception {

		return getOffsetDays(strDate, strDate2, "yyyyMMdd");
	}

	/**
	 * 2011.08.21 
	 * 
	 * add sangmin park
	 *  
	 * 지정된 두 일자의 차이 일수를 반환한다.(날짜 형식 : yyyyMMdd)
	 * 
	 * @param strDate
	 *            지정된 일자
	 * @param strDate2
	 *            지정된 일자
	 * @return 차이 일수
	 * @throws Exception
	 */
	public static int getOffsetDays(int strDate, int strDate2)
			throws Exception {

		return getOffsetDays(Integer.toString(strDate), Integer.toString(strDate2), "yyyyMMdd");
	}
	
	/**
	 * 해당 요일을 반환한다.(lang_type이 null 이면 영문 요일을 반환한다.)
	 * 
	 * @param cal
	 *            Calendar 객체
	 * @param lang_type
	 *            Locale 객체
	 * @return 요일
	 * @throws Exception
	 */
	public static String getDayOfWeek(Calendar cal, Locale lang_type)
			throws Exception {

		String ret_kor = "";
		String ret_eng = "";

		switch (cal.get(Calendar.DAY_OF_WEEK)) {

		case 1:
			ret_kor = "일";
			ret_eng = "SUN";
			break;
		case 2:
			ret_kor = "월";
			ret_eng = "MON";
			break;
		case 3:
			ret_kor = "화";
			ret_eng = "TUE";
			break;
		case 4:
			ret_kor = "수";
			ret_eng = "WED";
			break;
		case 5:
			ret_kor = "목";
			ret_eng = "THU";
			break;
		case 6:
			ret_kor = "금";
			ret_eng = "FRI";
			break;
		case 7:
			ret_kor = "토";
			ret_eng = "SAT";
			break;
		default:
			break;
		}

		if (lang_type == Locale.KOREA)
			return ret_kor;
		else
			return ret_eng;
	}

	/**
	 * 해당 요일을 반환한다.(한글요일)
	 * 
	 * @param cal
	 *            Calendar 객체
	 * @return 요일
	 * @throws Exception
	 */
	public static String getDayOfWeek(Calendar cal) throws Exception {

		return getDayOfWeek(cal, Locale.KOREA);

	}

	/**
	 * 이번주중에 주어진 요일의 날짜를 반환.
	 * 
	 * @param dayOfWeek
	 * @param format
	 * @return String
	 * @throws Exception
	 */
	public static String getDateOfThisWeek(int dayOfWeek, String format)
			throws Exception {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());

		int diff = dayOfWeek - cal.get(Calendar.DAY_OF_WEEK);

		return getOffsetDate(diff, format);
	}

	/**
	 * 지난주중에 주어진 요일의 날짜를 반환.
	 * 
	 * @param dayOfWeek
	 * @param format
	 * @return String
	 * @throws Exception
	 */
	public static String getDateOfPreWeek(int dayOfWeek, String format)
			throws Exception {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());

		int diff = dayOfWeek - cal.get(Calendar.DAY_OF_WEEK) - 7;

		return getOffsetDate(diff, format);
	}

	/**
	 * 다음주중에 주어진 요일의 날짜를 반환.
	 * 
	 * @param dayOfWeek
	 * @param format
	 * @return String
	 * @throws Exception
	 */
	public static String getDateOfNextWeek(int dayOfWeek, String format)
			throws Exception {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());

		int diff = dayOfWeek - cal.get(Calendar.DAY_OF_WEEK) + 7;

		return getOffsetDate(diff, format);
	}

	/**
	 * 이번주중에 주어진 요일을 기준으로 전후로 주어진 요일의 날짜를 반환. String sDate =
	 * DateUtil.getDateOfWeekByDay(Calendar.TUESDAY, "yyyy-MM-dd");
	 * 
	 * @param dayOfWeek
	 * @param format
	 * @return String
	 * @throws Exception
	 */
	public static String getDateOfWeekByDay(int dayOfWeek, String format)
			throws Exception {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());

		int diff = dayOfWeek - cal.get(Calendar.DAY_OF_WEEK);

		if (diff > 0) {
			diff = diff - 7;
		}

		return getOffsetDate(diff, format);
	}
	
}