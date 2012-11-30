/**
 * @FileName  : UserAuthenticationSuccessHandler.java
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

import org.gliderwiki.web.space.controller.SpaceController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;


/**
 * @author bluepoet
 *
 */
public class UserAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
	Logger logger = LoggerFactory.getLogger(UserAuthenticationSuccessHandler.class);

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
	        Authentication authentication) throws IOException, ServletException {

		logger.debug("loginSucess!!");
		super.onAuthenticationSuccess(request, response, authentication);
	}
}
