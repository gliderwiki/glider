/**
 * @FileName  : GliderwikiHandlerExceptionView.java
 * @Project   : NightHawk
 * @Date      : 2012. 7. 5.
 * @작성자      : @author bluepoet

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.framework.exception;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.google.common.collect.Lists;

/**
 * @author bluepoet
 *
 */
public class GliderwikiHandlerExceptionView {

	private String prefix;

	private boolean defaultView;

	private String viewName;

	private List<String> uriPatterns = Lists.newArrayList();

	public boolean match(HttpServletRequest request) {
		boolean uriCheck = false;
		String uri = getRequestUriWithoutContextPath(request);

		if (uri != null & uriPatterns.size() != 0) {
			for (String pattern : uriPatterns) {
				if (uri.matches(pattern)) {
					uriCheck = true;
					break;
				}
			}
		}

		return uriCheck;
	}

	private static String getRequestUriWithoutContextPath(HttpServletRequest request) {
		if (StringUtils.isBlank(request.getContextPath())) {
			return request.getRequestURI();
		}

		return request.getRequestURI().substring(request.getContextPath().length());
	}

	/**
	 * @return the prefix
	 */
	public String getPrefix() {
		return prefix;
	}

	/**
	 * @param prefix
	 *            the prefix to set
	 */
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	/**
	 * @return the defaultView
	 */
	public boolean isDefaultView() {
		return defaultView;
	}

	/**
	 * @param defaultView
	 *            the defaultView to set
	 */
	public void setDefaultView(boolean defaultView) {
		this.defaultView = defaultView;
	}

	/**
	 * @return the viewName
	 */
	public String getViewName() {
		return viewName;
	}

	/**
	 * @param viewName
	 *            the viewName to set
	 */
	public void setViewName(String viewName) {
		this.viewName = viewName;
	}

	/**
	 * @return the uriPatterns
	 */
	public List<String> getUriPatterns() {
		return uriPatterns;
	}

	/**
	 * @param uriPatterns
	 *            the uriPatterns to set
	 */
	public void setUriPatterns(List<String> uriPatterns) {
		this.uriPatterns = uriPatterns;
	}
}