/**
 * @FileName  : GliderwikiAccessDeniedHandler.java
 * @Project   : NightHawk
 * @Date      : 2012. 10. 24.
 * @작성자      : @author bluepoet

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.system.security;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * @author bluepoet
 *
 */
public class GliderwikiAccessDeniedHandler implements AccessDeniedHandler {
	private String errorPage;

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {

		System.out.println("erorrUrl : " + request.getRequestURI());

		if (!response.isCommitted()) {
			// Put exception into request scope (perhaps of use to a view)
			request.setAttribute(WebAttributes.ACCESS_DENIED_403, accessDeniedException);

			// Set the 403 status code.
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);

			String errorUrl = request.getRequestURI();

			if (equalSpaceCreateFormUrl(errorUrl)) {
				response.addHeader("errorMessage", "auth.restrict");
			}

			dispatch(request, response);
		}
	}

	private boolean equalSpaceCreateFormUrl(String errorUrl) {
		if (StringUtils.equals("/space/createSpaceCheck", errorUrl)) {
			return true;
		}

		return false;
	}

	private void dispatch(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(errorPage);
		dispatcher.forward(request, response);
	}

	public void setErrorPage(String errorPage) {
		this.errorPage = errorPage;
	}
}
