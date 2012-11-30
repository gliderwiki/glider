/**
 * @FileName  : LoginUserWebArgumentResolver.java
 * @Project   : NightHawk
 * @Date      : 2012. 7. 7.
 * @작성자      : @author bluepoet

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.system.argumentresolver;

import java.lang.annotation.Annotation;

import javax.servlet.http.HttpServletRequest;

import org.gliderwiki.framework.exception.GliderwikiException;
import org.gliderwiki.web.system.SessionService;
import org.gliderwiki.web.vo.MemberSessionVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.context.request.NativeWebRequest;


/**
 * {@link LoginUser} 어노테이션이 있는 컨트롤러 메소드에 로그인 사용자 객체를 주입해준다.
 *
 * @author bluepoet
 *
 */
public class LoginUserWebArgumentResolver implements WebArgumentResolver {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public Object resolveArgument(MethodParameter param, NativeWebRequest req) throws Exception {
		Annotation[] paramAnns = param.getParameterAnnotations();

		for (Annotation paramAnn : paramAnns) {
			if (LoginUser.class.isInstance(paramAnn) && paramAnn.annotationType().equals(LoginUser.class)) {
				HttpServletRequest httpServletRequest = (HttpServletRequest) req.getNativeRequest();
				MemberSessionVo loginUser = (MemberSessionVo) httpServletRequest.getAttribute("loginUser");
				logger.debug("LoginUserWebArgumentResolver : {}", loginUser);

				if (loginUser == null) {
					loginUser = MemberSessionVo.GUEST_USER.getGuestUser();
				}

				return loginUser;
			}
		}

		return UNRESOLVED;
	}
}