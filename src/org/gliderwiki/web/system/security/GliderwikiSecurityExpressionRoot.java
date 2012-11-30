/**
 * @FileName  : GliderwikiSecurityExpressionRoot.java
 * @Project   : NightHawk
 * @Date      : 2012. 7. 30.
 * @작성자      : @author bluepoet

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.system.security;

import org.gliderwiki.web.system.SessionService;
import org.gliderwiki.web.vo.MemberSessionVo;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.WebSecurityExpressionRoot;


/**
 * @author bluepoet
 *
 */
public class GliderwikiSecurityExpressionRoot extends WebSecurityExpressionRoot {

	private SessionService sessionService;

	/**
	 * @param a
	 * @param fi
	 */
	public GliderwikiSecurityExpressionRoot(Authentication a, FilterInvocation fi, SessionService sessionService) {
		super(a, fi);
		this.sessionService = sessionService;
	}

	public boolean leastLevel(int userLevel) {
		MemberSessionVo memberSessionVo = sessionService.getLoginUser();

		if (memberSessionVo == null) {
			return false;
		}

		boolean allowed = memberSessionVo.getWeGrade() >= userLevel;

		return allowed;
	}

}
