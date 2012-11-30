/**
* @FileName  : RequestManager.java
 * @Project   : NightHawk
 * @Date      : 2012. 7. 16.
 * @작성자      : @author bluepoet

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * HttpServletRequest 관련 처리를 담당한다.
 *
 * @author bluepoet
 *
 */
public class RequestManager {
	public static final String L7_X_FORWARDED_FOR_HEADER_NAME = "X-forwarded-for";

	public HttpServletRequest getHttpServletRequest() {
		ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpServletRequest request = sra.getRequest();

		return request;
	}

	public static Object getSessionAttribute(HttpServletRequest request, String attributeName) {
		HttpSession session = request.getSession(false);

		if (session == null) {
			return null;
		}

		return session.getAttribute(attributeName);
	}

	public static String getRemoteAddress(HttpServletRequest request) {
		String remoteAddr = request.getHeader(L7_X_FORWARDED_FOR_HEADER_NAME);
		if (StringUtils.isBlank(remoteAddr)) {
			remoteAddr = request.getRemoteAddr();
		} else {
			int indexOfComma = remoteAddr.indexOf(",");
			if (indexOfComma >= 0) {
				remoteAddr = remoteAddr.substring(0, indexOfComma);
			}
		}
		return remoteAddr;
	}
}