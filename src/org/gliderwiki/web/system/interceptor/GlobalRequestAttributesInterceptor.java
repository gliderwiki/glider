/**
 * @FileName  : GlobalRequestAttributesInterceptor.java
 * @Project   : NightHawk
 * @Date      : 2012. 7. 4.
 * @작성자      : @author bluepoet

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.system.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.gliderwiki.framework.exception.GliderwikiException;
import org.gliderwiki.framework.util.Base64Coder;
import org.gliderwiki.framework.util.SecretKeyPBECipher;
import org.gliderwiki.framework.util.SimpleAesStringCipherManager;
import org.gliderwiki.framework.util.StringUtil;
import org.gliderwiki.web.system.SessionService;
import org.gliderwiki.web.vo.MemberSessionVo;
import org.gliderwiki.web.wiki.common.service.CommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


/**
 * Request에 각종 기본 어트리뷰트를 추가해준다.
 *
 * @author bluepoet
 *
 */
public class GlobalRequestAttributesInterceptor extends HandlerInterceptorAdapter {
	Logger logger = LoggerFactory.getLogger(GlobalRequestAttributesInterceptor.class);

	private static final String IMG_TEMP_UPLOAD_ROOT_PATH_ATTR_NAME = "tempRootPath";
	private static final String IMG_REAL_UPLOAD_ROOT_PATH_ATTR_NAME = "realRootPath";
	private static final String LOGIN_USER_ATTR_NAME = "loginUser";
	private static final String LOGIN_USER_CHANNEL_ATTR_NAME = "loginUserChannel";
	private static final String LOGIN_USER_VIEWALARM_ATTR_NAME = "viewAlarm";

	private SessionService sessionService;
	private CommonService commonService;
	private SimpleAesStringCipherManager simpleAesStringCipherManager;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		setUploadImgPath(request);
		addLoginUser(request);

		return true;
	}

	/**
	 * 로그인한 유저의 정보를 세팅한다.
	 *
	 * @param request
	 * @throws Throwable
	 */
	private void addLoginUser(HttpServletRequest request) {
		MemberSessionVo loginUser = sessionService.getLoginUser();

		logger.debug("loginUser : {}", loginUser);
		request.setAttribute(LOGIN_USER_ATTR_NAME, loginUser);

		if (!loginUser.isGuest()) {
			logger.debug("alarmChannel : {}", simpleAesStringCipherManager.encrypt(String.format("A_%d", loginUser.getWeUserIdx())));
			request.setAttribute(LOGIN_USER_CHANNEL_ATTR_NAME, simpleAesStringCipherManager.encrypt(String.format("A_%d", loginUser.getWeUserIdx())));
			logger.debug("viewAlarm : {}", commonService.realNotiView(loginUser.getWeUserIdx()));
			request.setAttribute(LOGIN_USER_VIEWALARM_ATTR_NAME, commonService.realNotiView(loginUser.getWeUserIdx()));
		}
	}

	/**
	 * 첨부파일 이미지 임시루트와 실제루트 경로를 세팅
	 *
	 * @param request
	 */
	private void setUploadImgPath(HttpServletRequest request) {
		String tempImgRootPath = request.getSession().getServletContext().getRealPath("/resource/temp");
		String realImgRootPath = request.getSession().getServletContext().getRealPath("/resource/real");

		request.setAttribute(IMG_TEMP_UPLOAD_ROOT_PATH_ATTR_NAME, tempImgRootPath);
		request.setAttribute(IMG_REAL_UPLOAD_ROOT_PATH_ATTR_NAME, realImgRootPath);
	}

	public void setSessionService(SessionService sessionService) {
		this.sessionService = sessionService;
	}

	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}

	public void setNotiCipherManager(SimpleAesStringCipherManager simpleAesStringCipherManager) {
		this.simpleAesStringCipherManager = simpleAesStringCipherManager;
	}
}
