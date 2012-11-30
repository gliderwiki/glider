/**
 * @FileName  : AuthenticationService.java
 * @Project   : NightHawk
 * @Date      : 2012. 7. 30.
 * @작성자      : @author bluepoet

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.system.security;

import org.gliderwiki.framework.exception.PasswordMismatchException;
import org.gliderwiki.framework.exception.UserNotFoundException;
import org.gliderwiki.web.vo.MemberSessionVo;

/**
 * @author bluepoet
 *
 */
public interface AuthenticationService {
	void login(String userId, String password) throws Throwable, UserNotFoundException, PasswordMismatchException;
}
