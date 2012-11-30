/**
 * @FileName  : GliderwikiLogoutHandler.java
 * @Project   : NightHawk
 * @Date      : 2012. 8. 1.
 * @작성자      : @author bluepoet

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.system.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

/**
 * @author bluepoet
 *
 */
public class GliderwikiLogoutHandler extends SimpleUrlLogoutSuccessHandler {
	Logger logger = LoggerFactory.getLogger(GliderwikiLogoutHandler.class);

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		clearSession(request);

		super.onLogoutSuccess(request, response, authentication);
	}

	private void clearSession(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
	}
}