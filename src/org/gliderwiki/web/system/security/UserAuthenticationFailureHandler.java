/**
 * @FileName  : UserAuthenticationFailureHandler.java
 * @Project   : NightHawk
 * @Date      : 2012. 7. 30.
 * @작성자      : @author bluepoet

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.system.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AbstractAuthenticationTargetUrlRequestHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

/**
 * @author bluepoet
 *
 */
public class UserAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
	Logger logger = LoggerFactory.getLogger(UserAuthenticationFailureHandler.class);
	public static final String DEFAULT_FAIL_TARGET_PARAMETER = "spring-security-fail-redirect";

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		logger.debug("login Failed!!");

		RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
		String entryUrl = request.getParameter(DEFAULT_FAIL_TARGET_PARAMETER);
		if (entryUrl == null) {
			entryUrl = request.getHeader("Referer");
		}
		String forwardUrl = request
				.getParameter(AbstractAuthenticationTargetUrlRequestHandler.DEFAULT_TARGET_PARAMETER);

		logger.debug("login Error Message: {}", exception.getMessage(), exception);
		saveException(request, exception);

		redirectStrategy.sendRedirect(request, response, createForwardUrl(entryUrl, forwardUrl));
	}

	private String createForwardUrl(String entryUrl, String forwardUrl) {
		if (StringUtils.isBlank(forwardUrl)) {
			return entryUrl;
		}

		return entryUrl + "&forwardUrl=" + forwardUrl;
	}
}
