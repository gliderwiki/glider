/**
 * @FileName  : UserIdPasswordAuthenticationService.java
 * @Project   : NightHawk
 * @Date      : 2012. 7. 30.
 * @작성자      : @author bluepoet

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.system.security;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.gliderwiki.framework.exception.AuthenticationNotException;
import org.gliderwiki.framework.exception.PasswordMismatchException;
import org.gliderwiki.framework.exception.UserNotFoundException;
import org.gliderwiki.framework.util.StringUtil;
import org.gliderwiki.web.domain.WeUser;
import org.gliderwiki.web.login.service.LoginService;
import org.gliderwiki.web.vo.MemberSessionVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Service;


/**
 * @author bluepoet
 *
 */
@Service(value = "authenticationService")
public class UserIdPasswordAuthenticationService implements AuthenticationService {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Resource(name = "loginService")
	LoginService loginService;

	@Autowired
	MessageSourceAccessor messages;

	@Override
	public void login(String userId, String password) throws Throwable, UserNotFoundException, AuthenticationNotException, 	PasswordMismatchException {
		logger.debug("#####################################");
		logger.debug("여긴 언제 들어오는거냐~~~");
		logger.debug("#####################################");
		
		if (StringUtils.isEmpty(userId)) {
			throw new UserNotFoundException(messages.getMessage("account.authenticationFailure"));
		}

		userId = userId.toLowerCase();

		WeUser weUser = new WeUser(userId, password);
		MemberSessionVo result = loginService.getRowWeUserById(userId);

		if (result == null) {
			throw new UserNotFoundException(messages.getMessage("account.authenticationFailure"));
		}

		String passKey = result.getWeUserKey();
		String pwd = loginService.getEncryptPassword(passKey, weUser.getWe_user_pwd());

		if (StringUtil.strNull(result.getWeUserAuthYn()).equals("Y")) {
			if (!result.getWeUserPwd().equals(pwd)) {
				throw new PasswordMismatchException(messages.getMessage("account.passwordmismatch"));
			}
		} else {
			throw new AuthenticationNotException(messages.getMessage("account.notauthentication"));
		}
	}
}