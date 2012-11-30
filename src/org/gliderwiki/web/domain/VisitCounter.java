/**
 * @FileName  : VisitCounter.java
 * @Project   : NightHawk
 * @Date      : 2012. 10. 15.
 * @작성자      : @author bluepoet

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.domain;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.gliderwiki.framework.exception.GliderwikiException;

import com.google.common.collect.Maps;

/**
 * 방문날짜 관련한 클래스 별도로 생성해서 기능제공 제공하는 기능 : 오늘 방문한 날짜횟수, 총 방문횟수, 최종방문날짜, 최종방문횟수등
 *
 * @author bluepoet
 *
 */
public class VisitCounter {
	public static final String VISIT_CACHE_FILE_PATH = "/resource/data/visitCountInfo.dat";

	private String pastVisitDate;

	private int todayVisitCount = 0;

	private int totalVisitCount = 0;

	private int pastVisitCount = 0;

	private File visitCacheFile;

	private HttpServletRequest request;

	private DateFormat df;

	public VisitCounter(HttpServletRequest request) {
		this.request = request;
		this.df = new SimpleDateFormat("yyyy/MM/dd");
		this.visitCacheFile = new File(this.request.getSession().getServletContext().getRealPath(VISIT_CACHE_FILE_PATH));
	}

	public Map<String, Integer> getAllVisitCountInfo() {
		Map<String, Integer> result = Maps.newHashMap();

		File visitCacheFile = getVisitCacheFile();

		if (visitCacheFile != null) {
			checkVisitSessionFile();
		}

		createVisitResultData(result);

		this.request.getSession().setAttribute("VisitToalCount", "Exist");

		return result;
	}

	private void createVisitResultData(Map<String, Integer> visitData) {
		visitData.put("totalVisitCount", this.totalVisitCount);
		visitData.put("todayVisitCount", this.todayVisitCount);
	}

	private File getVisitCacheFile() {
		if (this.visitCacheFile.exists()) {
			getDataFromVisitCacheFile(visitCacheFile);

			return visitCacheFile;
		}

		return null;
	}

	private void getDataFromVisitCacheFile(File visitCacheFile) {
		BufferedReader reader = null;

		try {
			reader = new BufferedReader(new FileReader(visitCacheFile));
			String todayVisitCount = reader.readLine();

			this.todayVisitCount = Integer.parseInt(todayVisitCount);
			this.totalVisitCount = Integer.parseInt(reader.readLine());
			this.pastVisitDate = reader.readLine();
			this.pastVisitCount = Integer.parseInt(reader.readLine());
		} catch (IOException e) {
			throw new GliderwikiException("방문자 캐시파일 읽는 도중 오류발생!", e);
		} finally {
			IOUtils.closeQuietly(reader);
		}
	}

	private void checkVisitSessionFile() {
		if (isVisitSessionData()) {

			this.totalVisitCount = this.totalVisitCount + 1;

			if (isDifferentDate()) {
				makeVisitCacheFile(this.totalVisitCount, 1, dateFormat(new Date()), this.todayVisitCount);
				this.todayVisitCount = 1;
			} else {
				this.todayVisitCount = this.todayVisitCount + 1;
				makeVisitCacheFile(this.totalVisitCount, this.todayVisitCount, this.pastVisitDate, this.pastVisitCount);
			}
		}
	}

	private boolean isVisitSessionData() {
		String visitSessionData = (String) this.request.getSession().getAttribute("VisitToalCount");

		if (StringUtils.isEmpty(visitSessionData)) {
			return true;
		}

		if (!StringUtils.equals("Exist", visitSessionData)) {
			return true;
		}

		return false;
	}

	private boolean isDifferentDate() {
		Date todayDate;

		todayDate = dateParse(dateFormat(new Date()));
		Date pastVisitDate = dateParse(this.pastVisitDate);

		if (todayDate.after(pastVisitDate)) {
			return true;
		}

		return false;
	}

	private void makeVisitCacheFile(int totalVisitCount, int todayVisitCount, String pastVisitDate, int pastVistCount) {
		PrintWriter writer = null;

		try {
			writer = new PrintWriter(new FileOutputStream(this.visitCacheFile));
			writer.println(todayVisitCount);
			writer.println(totalVisitCount);
			writer.println(pastVisitDate);
			writer.println(pastVistCount);
			writer.close();
		} catch (IOException e) {
			throw new GliderwikiException("방문자 캐시파일 만드는 도중 오류 발생!", e);
		} finally {
			IOUtils.closeQuietly(writer);
		}
	}

	private synchronized String dateFormat(Date date) {
		return df.format(date);
	}

	private synchronized Date dateParse(String strDate) {
		try {
			return df.parse(strDate);
		} catch (ParseException e) {
			throw new GliderwikiException("String을 Date로 parsing하는 도중 오류 발생!", e);
		}
	}
}