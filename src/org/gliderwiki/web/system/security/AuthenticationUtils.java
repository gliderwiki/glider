/**
 * @FileName  : AuthenticationUtils.java
 * @Project   : NightHawk
 * @Date      : 2012. 8. 1.
 * @작성자      : @author bluepoet

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.system.security;

import java.util.Collection;

import org.gliderwiki.web.system.interceptor.GlobalRequestAttributesInterceptor;
import org.gliderwiki.web.vo.MemberSessionVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.context.SecurityContextHolder;


/**
 * @author bluepoet
 *
 */
public class AuthenticationUtils {
	static Logger logger = LoggerFactory.getLogger(AuthenticationUtils.class);

	public static Authentication getAuthentication() {
		if (SecurityContextHolder.getContext() == null) {
			logger.debug("null 1번");
			return null;
		}

		return SecurityContextHolder.getContext().getAuthentication();
	}

	public static Collection<GrantedAuthority> getAuthorities() {
		return getAuthentication().getAuthorities();
	}

	public static boolean hasRole(GliderAuthorityType authorityType) {
		return getAuthorities().contains(new GrantedAuthorityImpl(authorityType.name()));
	}

	public static MemberSessionVo getGliderwikiUser() {
		return getUser(getAuthentication());
	}

	public static MemberSessionVo getUser(Authentication authentication) {
		if (authentication == null) {
			return MemberSessionVo.GUEST_USER.getGuestUser();
		}

		Object principal = authentication.getPrincipal();
		if (principal == null) {
			logger.debug("null 3번");
			throw new NullPointerException("Principal should not null!");
		}

		if (principal instanceof GliderwikiUserDetails) {
			GliderwikiUserDetails userDetails = (GliderwikiUserDetails) principal;
			MemberSessionVo account = userDetails.getMemberSessionVo();
			return account;
		} else {
			return MemberSessionVo.GUEST_USER.getGuestUser();
		}
	}

	public static String getCurrentUserId() {
		if (getGliderwikiUser() == null) {
			MemberSessionVo account = MemberSessionVo.GUEST_USER.getGuestUser();
			return account.getWeUserId();
		}

		return getGliderwikiUser().getWeUserId();
	}

	public static boolean isAuthenticated() {
		if (getAuthentication() == null) {
			return false;
		}

		AuthenticationTrustResolver trustResolver = new AuthenticationTrustResolverImpl();
		if (getAuthentication().isAuthenticated()) {
			return !trustResolver.isAnonymous(getAuthentication());
		}
		return false;
	}
}