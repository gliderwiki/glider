/**
 * @FileName  : LoginController.java
 * @Project   : NightHawk
 * @Date      : 2012. 4. 2.
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 : 회원 정보 가입 및 로그인, 비밀번호 찾기, 아이디 찾기
 */
package org.gliderwiki.web.login.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.gliderwiki.framework.entity.service.EntityService;
import org.gliderwiki.framework.exception.UserNotFoundException;
import org.gliderwiki.framework.util.DateUtil;
import org.gliderwiki.framework.util.StringUtil;
import org.gliderwiki.util.SessionUtil;
import org.gliderwiki.web.domain.WeUser;
import org.gliderwiki.web.login.service.LoginService;
import org.gliderwiki.web.system.SessionService;
import org.gliderwiki.web.system.SystemConst;
import org.gliderwiki.web.vo.BaseObjectBean;
import org.gliderwiki.web.vo.JsonResponse;
import org.gliderwiki.web.vo.MemberSessionVo;
import org.gliderwiki.web.vo.JsonResponse.ResponseStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


/**
 * @author yion
 *
 */
@Controller
public class LoginController {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private LoginService loginService;

	@Resource(name = "sessionService")
	private SessionService sessionService;
	
	@SuppressWarnings("rawtypes")
	@Autowired
	private EntityService entityService;

		
	/**
	 * 현재 사용안함 - 추후 레이어로 변경 
	 * @param request
	 * @param response
	 * @param modelAndView
	 * @return
	 * @throws Throwable
	 */
	@RequestMapping(value="/join", method = RequestMethod.GET)
	public ModelAndView join(HttpServletRequest request, HttpServletResponse response, ModelAndView modelAndView) throws Throwable {
		logger.debug("### 회원가입 폼 ");
		modelAndView.setViewName("user/login/join");
		return modelAndView;
	}
	
	
	@RequestMapping(value="/checkUesrId", method = RequestMethod.POST)
	@ResponseBody
	public JsonResponse checkUesrId(@RequestParam("userId") String userId) {
		JsonResponse response = new JsonResponse();
		logger.debug("### 중복체크 ");
		logger.debug("### userId :  " + userId);
		
		WeUser domain = new WeUser();
		domain.setWe_user_id(userId);
		domain.setWe_user_auth_yn("Y");
		
		int checkDupId = 0;
		try {
			checkDupId = entityService.getCountEntity(domain);
		} catch (Throwable e) {
			e.printStackTrace();
		}		
		
		if(checkDupId == 0) {
			response.setStatus(ResponseStatus.SUCCESS);
			response.setResult(checkDupId);
		} else {
			response.setStatus(ResponseStatus.FAIL);
			response.setResult(checkDupId);
		}		
		logger.debug("#### checkDupId response : " + response.toString());
		
		return response;
	}
	
	
	@RequestMapping(value="/checkUesrNick", method = RequestMethod.POST)
	@ResponseBody
	public JsonResponse checkUesrNick(@RequestParam("userNickName") String userNickName) {
		JsonResponse response = new JsonResponse();
		logger.debug("### 중복체크 ");
		logger.debug("### userNickName :  " + userNickName);
		
		WeUser domain = new WeUser();
		domain.setWe_user_nick(userNickName);
		domain.setWe_user_auth_yn("Y");
		
		int checkDupNick = 0;
		try {
			checkDupNick = entityService.getCountEntity(domain);
		} catch (Throwable e) {
			e.printStackTrace();
		}		
		
		if(checkDupNick == 0) {
			response.setStatus(ResponseStatus.SUCCESS);
			response.setResult(checkDupNick);
		} else {
			response.setStatus(ResponseStatus.FAIL);
			response.setResult(checkDupNick);
		}		
		logger.debug("#### checkDupNick response : " + response.toString());
		
		return response;
	}
	
	

	@RequestMapping(value = "/authUser/{code}", method = RequestMethod.GET)
	public ModelAndView authUser(@PathVariable("code") String code, ModelAndView modelAndView) throws Exception {
		logger.debug("### 회원 인증 ");
		logger.info("### code : " + code);
		int result = 0;
		try {
			result = loginService.updateUserAuth(code);
		} catch (Throwable e) {
			throw new UserNotFoundException(e.getMessage());
		}
		modelAndView.addObject("result", result);
		modelAndView.setViewName("user/login/userAuth");
		return modelAndView;
	}


	@RequestMapping(value="/registUser", method = RequestMethod.POST)
	@ResponseBody
	public JsonResponse registUser(@ModelAttribute("joinForm") WeUser weUser, BindingResult result,
			HttpServletRequest request, HttpServletResponse response, ModelAndView modelAndView) throws Throwable {
		logger.debug("### 회원가입 넘어온값 : " + weUser.toString());
		JsonResponse res = new JsonResponse();

		//TODOList 아이디가 존재하는지 체크

		//Key 생성
		String passKey = StringUtil.stringBuffersChars(128);		// 128

		logger.debug("passKey {} " , passKey);

		String password = loginService.getEncryptPassword(passKey, weUser.getWe_user_pwd());

		logger.debug("password {} " , password);		// 암호화 된 값

		String temp = String.valueOf(System.nanoTime());
		
		String randomKey = temp.substring(temp.length() -8, temp.length()-4);
		
		// 회원 가입 저장
		weUser.setWe_user_key(passKey);					// 패스워드 키 세팅
		weUser.setWe_user_pwd(password);				// 비밀번호 
		weUser.setWe_user_join_date(new Date());		// 기본값 세팅
		weUser.setWe_user_auth(randomKey);				// 인증전송 값 

		int rtnResult = 0;
		try {
			rtnResult = loginService.sendMailAuth(weUser, request);
		} catch (Exception e) {
			// 익셉션일 경우 Log 적재 - 추후 어드민에서 재전송 가능하도록
			logger.error("메일 전송 에러", e.getCause());
			e.printStackTrace();
		}


		res.setResult(rtnResult);

		return res;
	}

	
	@RequestMapping(value="/findIdPassword", method = RequestMethod.POST)
	public ModelAndView findIdPassword(HttpServletRequest request, HttpServletResponse response, ModelAndView modelAndView) throws Throwable {
		
		String userId = request.getParameter("userId");
		String userNick = request.getParameter("weUserNick");
		
		Map<String, Object> param = new HashMap<String, Object>();

		BaseObjectBean resultBean = null;
		try {
			
			resultBean = loginService.findUserPassword(userId, userNick, request);

			param.put("result", "SUCCESS");
			param.put("status", SystemConst.CALL_SUCCESS);
			param.put("rtnCode", resultBean.getRtnResult());
			param.put("rtnMsg", resultBean.getRtnMsg());
			
				
		} catch (Exception e) {
			param.put("result", "FAIL");
			param.put("status", SystemConst.CALL_FAIL);
			param.put("rtnCode", resultBean.getRtnResult());
			param.put("rtnMsg", resultBean.getRtnMsg());
			
		}

		return new ModelAndView("json_").addObject("param", param);
	}
	
	/**
	 * 로그 아웃 
	 * @param request
	 * @param response
	 * @param modelAndView
	 * @return
	 * @throws Throwable
	 */
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response, ModelAndView modelAndView) throws Throwable {
		logger.debug("### 로그아웃  ");
		SessionUtil sessions = new SessionUtil(request);
		sessions.invalidate();

		modelAndView.setViewName("redirect:/index");
		return modelAndView;
	}
	
	
}
