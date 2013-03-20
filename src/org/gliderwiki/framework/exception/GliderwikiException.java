/**
 * @FileName  : GliderwikiException.java
 * @Project   : NightHawk
 * @Date      : 2012. 7. 5.
 * @작성자      : @author bluepoet

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.framework.exception;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartException;

import com.google.common.collect.Maps;

/**
 * 프로젝트 전반적으로 쓰는 체크예외 Exception을 정의한다.
 * 모든 예외는 GliderwikiException로 변환되어 처리된다.
 * @author bluepoet
 *
 */
public class GliderwikiException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public static final LogLevel DEFAULT_LOGLEVEL = LogLevel.ERROR;

	public static final String DEFAULT_MESSAGE_CODE = "exception.default.message";
	public static final String DEFAULT_VIEW_NAME = "/error";

	private static final Map<Class<? extends Throwable>, LogLevel> EXCEPTION_LOGLEVEL_MAP = Maps.newHashMap();

	static {
		EXCEPTION_LOGLEVEL_MAP.put(MultipartException.class, LogLevel.WARN);
	}

	/**
	 * 오류 메시지 프라퍼티 ID
	 */
	private String messageCode = DEFAULT_MESSAGE_CODE;

	/**
	 * 오류 메시지의 인자들을 지정한다.
	 */
	private String[] messageArgs;

	/**
	 * 오류를 보여줄 View 이름을 지정한다.
	 */
	private String viewName = DEFAULT_VIEW_NAME;

	/**
	 * 오류 로그를 남길때의 로그 레벨을 지정한다.
	 */
	private LogLevel logLevel = DEFAULT_LOGLEVEL;

	public GliderwikiException() {
		this(null, null);
	}

	public GliderwikiException(String message) {
		this(message, null);
	}

	public GliderwikiException(Throwable cause) {
		this(cause.getMessage(), cause);
	}

	public GliderwikiException(String message, Throwable cause) {
		super(message, cause);

		distinguishCauseLogLevel(cause);
	}

	/**
	 * @param cause
	 */
	private void distinguishCauseLogLevel(Throwable cause) {
		if(cause == null) {
			return;
		}

		LogLevel causeLogLevel = EXCEPTION_LOGLEVEL_MAP.get(cause.getClass());
		if(causeLogLevel != null) {
			setLogLevel(causeLogLevel);
		}
	}

	/**
	 * @return the messageCode
	 */
	public String getMessageCode() {
		return messageCode;
	}

	/**
	 * @param messageCode the messageCode to set
	 */
	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
	}

	/**
	 * @return the messageArgs
	 */
	public String[] getMessageArgs() {
		return messageArgs;
	}

	/**
	 * @param messageArgs the messageArgs to set
	 */
	public void setMessageArgs(String... messageArgs) {
		this.messageArgs = messageArgs;
	}

	/**
	 * @return the viewName
	 */
	public String getViewName() {
		return viewName;
	}

	/**
	 * @param viewName the viewName to set
	 */
	public void setViewName(String viewName) {
		this.viewName = viewName;
	}

	/**
	 * @return the logLevel
	 */
	public LogLevel getLogLevel() {
		return logLevel;
	}

	/**
	 * @param logLevel the logLevel to set
	 */
	public void setLogLevel(LogLevel logLevel) {
		this.logLevel = logLevel;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the defaultLoglevel
	 */
	public static LogLevel getDefaultLoglevel() {
		return DEFAULT_LOGLEVEL;
	}

	/**
	 * @return the defaultMessageCode
	 */
	public static String getDefaultMessageCode() {
		return DEFAULT_MESSAGE_CODE;
	}

	/**
	 * @return the defaultViewName
	 */
	public static String getDefaultViewName() {
		return DEFAULT_VIEW_NAME;
	}

	/**
	 * response Http Status 코드를 Default로 Client Error(400)으로 지정
	 */
	public int getHttpStatusCode() {
		return HttpServletResponse.SC_BAD_REQUEST;
	}

	/**
	 * 예외에 대해 어떤 레벨로 로그를 남길지 결정한다.
	 */
	public static enum LogLevel {
		TRACE, DEBUG, INFO, WARN, ERROR;
	}
}
