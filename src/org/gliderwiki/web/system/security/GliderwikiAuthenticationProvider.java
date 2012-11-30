/**
 * @FileName  : GliderwikiAuthenticationProvider.java
 * @Project   : NightHawk
 * @Date      : 2012. 7. 30.
 * @작성자      : @author bluepoet

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.system.security;

import org.gliderwiki.framework.exception.AuthenticationNotException;
import org.gliderwiki.framework.exception.GliderwikiException;
import org.gliderwiki.framework.exception.PasswordMismatchException;
import org.gliderwiki.framework.exception.UserNotFoundException;
import org.gliderwiki.web.space.controller.SpaceController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.Assert;


/**
 * @author bluepoet
 *
 */
public class GliderwikiAuthenticationProvider implements AuthenticationProvider, InitializingBean {
	Logger logger = LoggerFactory.getLogger(GliderwikiAuthenticationProvider.class);

	private AuthenticationService authenticationService;
	private UserDetailsService userDetailsService;

	public void setAuthenticationService(AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}

	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(this.authenticationService, "authenticationService is mandatory");
        Assert.notNull(this.userDetailsService, "userDetailsService is mandatory");
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String userId = authentication.getPrincipal().toString();
		String password = authentication.getCredentials().toString();
		login(userId, password);

		UserDetails userDetails = userDetailsService.loadUserByUsername(userId);
		return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
	}

	@Override
	public boolean supports(Class<? extends Object> authentication) {
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	}

	private void login(String userId, String password) {
		logger.debug("id/password : {}{}", userId, password);

		try {
			authenticationService.login(userId, password);
		} catch (UserNotFoundException e) {
			logger.debug("에러메세지 : {}", e.getMessage());
			throw new UsernameNotFoundException(e.getMessage(), e);
		} catch (AuthenticationException e) {
			throw new BadCredentialsException(e.getMessage(), e);
		} catch (PasswordMismatchException e) {
			throw new BadCredentialsException(e.getMessage(), e);
		} catch(AuthenticationNotException e) {
			throw new BadCredentialsException(e.getMessage(), e);
		} catch (Throwable e) {
			throw new GliderwikiException("사용자정보 조회중 오류", e);
		}
	}
}
