/**
 * @FileName  : SessionService.java
 * @Project   : NightHawk
 * @Date      : 2012. 8. 1.
 * @작성자      : @author bluepoet

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.system;

import org.gliderwiki.web.system.security.AuthenticationUtils;
import org.gliderwiki.web.system.security.GliderAuthorityType;
import org.gliderwiki.web.vo.MemberSessionVo;

/**
 * @author bluepoet
 *
 */
public class SessionService {
	/**
	 * 로그인 유무를 확인한다.
	 *
	 * @return
	 */
	public boolean isAuthenticated() {
		return AuthenticationUtils.isAuthenticated();
	}

	/**
	 * null을 리턴하지 않는다. 비로그인의 경우 비로그인 객체를 리턴한다.
	 *
	 * @return
	 */
	public MemberSessionVo getLoginUser() {
		return AuthenticationUtils.getGliderwikiUser();
	}

	/**
	 * 로그인 사용자가 특정 ROLE을 가지고 있는가?
	 *
	 * @param authorityType
	 * @return
	 */
	public boolean hasRole(GliderAuthorityType authorityType) {
		return AuthenticationUtils.hasRole(authorityType);
	}
}