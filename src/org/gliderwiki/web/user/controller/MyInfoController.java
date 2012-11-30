/**
 * @FileName  : UserProfileController.java
 * @Project   : NightHawk
 * @Date      : 2012. 4. 2.
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 : 내 정보 > 프로필 수정
 *                 1) 회원정보 조회
 *                 2) 회원정보 수정
 *                     2-1 : 첨부파일 임시저장
 *                 3) 비밀번호가 입력된 경우 Key 조회하여 다시 암호화 한 후 업데이트
 */
package org.gliderwiki.web.user.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.gliderwiki.framework.entity.service.EntityService;
import org.gliderwiki.framework.exception.GliderwikiException;
import org.gliderwiki.framework.util.DateUtil;
import org.gliderwiki.framework.util.FileUploader;
import org.gliderwiki.framework.util.StringUtil;
import org.gliderwiki.web.domain.JoinType;
import org.gliderwiki.web.domain.WeFile;
import org.gliderwiki.web.domain.WeProfile;
import org.gliderwiki.web.domain.WeSpaceJoin;
import org.gliderwiki.web.domain.WeUser;
import org.gliderwiki.web.login.service.LoginService;
import org.gliderwiki.web.system.SystemConst;
import org.gliderwiki.web.system.argumentresolver.LoginUser;
import org.gliderwiki.web.user.service.UserActionService;
import org.gliderwiki.web.user.service.UserAlarmService;
import org.gliderwiki.web.user.service.UserConnectionService;
import org.gliderwiki.web.user.service.UserFavorService;
import org.gliderwiki.web.user.service.UserProfileService;
import org.gliderwiki.web.vo.AddFriendVo;
import org.gliderwiki.web.vo.AlarmInfoVo;
import org.gliderwiki.web.vo.BaseObjectBean;
import org.gliderwiki.web.vo.MemberSessionVo;
import org.gliderwiki.web.vo.MetaInfoVo;
import org.gliderwiki.web.vo.ProfileVo;
import org.gliderwiki.web.vo.TempUploadVo;
import org.gliderwiki.web.vo.WikiFavoriteVo;
import org.gliderwiki.web.vo.WikiLogVo;
import org.gliderwiki.web.wiki.common.service.CommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


/**
 * @author yion
 *
 */
@Controller
public class MyInfoController {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Value("#{config['file.maxSize']}")
	String uploadMaxSize;


	@Autowired
	private LoginService loginService;

	@Autowired
	private UserProfileService userProfileService;

	@Autowired
	private UserActionService userActionService;

	@Autowired
	private UserFavorService userFavorService;

	@Autowired
	private UserConnectionService userConnectionService;

	@Autowired
	private UserAlarmService userAlarmService;

	@Autowired
	private EntityService entityService;

	@Autowired
	private CommonService commonService;

//	@Autowired
//	private UserProfileService userProfileService;

	@SuppressWarnings("static-access")
	@RequestMapping(value="/user/profile", method = RequestMethod.GET)
	public ModelAndView profile(@LoginUser MemberSessionVo loginUser, HttpServletRequest request, HttpServletResponse response, ModelAndView modelAndView) throws Throwable {
		logger.debug("### 사용자 프로필 ");
		logger.debug("###############################");
		logger.debug("로그인 ! : " + loginUser.toString());
		logger.debug("###############################");
		WeUser weUser = null;			// 사용자 기본정보
		WeProfile weProfile = null;		// 사용자 프로필 정보

		weUser = commonService.getUserInfo(loginUser.getWeUserIdx());				// 회원 기본 정보 조회
		weProfile = commonService.getUserProfileInfo(loginUser.getWeUserIdx());		// 회원 프로파일 정보 조회


		modelAndView.addObject("weProfile", weProfile);
		modelAndView.addObject("weUser", weUser);

		modelAndView.setViewName("user/profile/profile");
		return modelAndView;
	}

	/**
	 * 프로파일 이미지 업로드 - 미리보기용
	 * @param fileVo
	 * @param request
	 * @param response
	 * @param modelAndView
	 * @return
	 * @throws Throwable
	 */
	@RequestMapping(value = "/user/profileUpload", method = RequestMethod.POST)
	public ModelAndView profileUpload(@LoginUser MemberSessionVo loginUser, @ModelAttribute("frmFile") TempUploadVo fileVo,
			HttpServletRequest request, HttpServletResponse response, ModelAndView modelAndView)  throws Throwable {
		logger.debug("##root path : " + request.getServletContext().getRealPath("/"));
		logger.debug("##request.getSession() : " + request.getSession().getServletContext().getRealPath("/resource/temp/"));
		String svcPath = request.getSession().getServletContext().getRealPath("/resource/temp/");


		//업로드 날짜 및 파일명 구성
		String today = DateUtil.getToday();

		//TODOLIST : 세션에서 가져온다. 일단 테스트용으로 사용자 순번 1을 통해 등록한다.
		String weUserIdx = loginUser.getWeUserIdx().toString();
		String weUserId = loginUser.getWeUserId();

		double maxSize = Double.parseDouble(uploadMaxSize);

		// 파일 객체, 사용자 아이디, 오늘날짜, 파일 업로드 사이즈
		TempUploadVo tempFile = FileUploader.uploadTempFile(fileVo.getFile(), svcPath, weUserIdx, today, maxSize);

		logger.debug("tempFile : " + tempFile.toString());


		Map<String, String> param = new HashMap<String, String>();
		if(tempFile.isUploaded()) {
			// DB에 인서트 (WE_FILE 에 임시 저장 후 최종 프로필 수정시 profile 테이블에 업데이트한다.
			WeFile weFile = new WeFile();

			weFile.setWe_file_real_name(tempFile.getFileRealName());
			weFile.setWe_file_save_name(tempFile.getFileSaveName());
			weFile.setWe_file_save_path(tempFile.getFilePath());
			weFile.setWe_file_size(tempFile.getFileSize()+"");
			weFile.setWe_file_type(tempFile.getFileType());
			weFile.setWe_thumb_yn(tempFile.getThumbYn());
			weFile.setWe_thumb_name(tempFile.getThumbName());
			weFile.setWe_thumb_path(tempFile.getThumbPath());
			weFile.setWe_user_idx(Integer.parseInt(weUserIdx));
			weFile.setWe_ins_date(DateUtil.getTodayTime());
			weFile.setWe_ins_user(weUserIdx);

			int result = commonService.insertWeFile(weFile);

			logger.debug("### result : " + result);

			if(result == 1) {

				WeFile retFile = (WeFile)entityService.getRowEntity(weFile);

				param.put("result", "1");
		    	param.put("msg", "성공");
		    	param.put("realFileName", retFile.getWe_file_real_name());
		    	param.put("saveFileName", retFile.getWe_file_save_name());
		    	param.put("thumbPath", retFile.getWe_thumb_path());
		    	param.put("thumbName", retFile.getWe_thumb_name());
		    	param.put("filePath", retFile.getWe_file_save_path());
		    	param.put("fileSize", retFile.getWe_file_size()+"");
		    	param.put("tmpsrc", svcPath);
		    	param.put("fileIndex", retFile.getWe_file_idx()+"");
			} else {
				param.put("result", "0");
		    	param.put("msg", "파일 저장에 실패 하였습니다. 다시 시도하세요.");
		    	param.put("realFileName", tempFile.getFileRealName());
			}

		} else {
			param.put("result", "-1");
	    	param.put("msg", "파일 업로드에 실패 하였습니다.");
	    	param.put("realFileName", tempFile.getFileRealName());
		}

		logger.debug("param : "  + param.toString());

		return new ModelAndView("json_").addObject("param", param);
	}


	/**
	 * 프로필 수정
	 * @param profileVo
	 * @param request
	 * @param response
	 * @param modelAndView
	 * @return
	 * @throws Throwable
	 */
	@RequestMapping(value = "/user/updateUserProfile", method = RequestMethod.POST)
	public ModelAndView updateUserProfile(@ModelAttribute("frmFile") ProfileVo profileVo,
			HttpServletRequest request, HttpServletResponse response, ModelAndView modelAndView)  throws Throwable {
		logger.debug("회원정보 request 값 : " + profileVo.toString());
		//TODOLIST 회원 세션 정보 획득
		
		String weUserName = StringUtil.strNull(request.getParameter("weUserName"));
		logger.debug("weUserName : " + weUserName);
		

		String userPassword = StringUtil.strNull(request.getParameter("userPassword"));
		String weUserPwd = StringUtil.strNull(request.getParameter("weUserPwd"));

		BaseObjectBean baseObj = new BaseObjectBean();

		// 패스워드가 있을 경우 비밀번호 수정 로직 구현
		if(userPassword.equals("") || weUserPwd.equals("") ) {
			// 비밀번호가 둘중 하나라도 입력이 안되어있으면 수정할 수 없다.
			profileVo.setUserPassword("");
			profileVo.setWeUserPwd("");

		} else {
			// userPassword : 현재 사용자 패스워드, weUserPwd 바꿀 패스워드
			profileVo.setUserPassword(userPassword);
			profileVo.setWeUserPwd(weUserPwd);
		}


		try {
			baseObj = userProfileService.updateUserProfile(profileVo, request);
		} catch (Exception e) {
			logger.info("회원정보 수정 Controller 에서 에러 발생 : " + e.getMessage());
			baseObj.setRtnResult(-1);
			baseObj.setRtnMsg("에러가 발생하였습니다. " + e.getMessage());
		}

		Map<String, String> param = new HashMap<String, String>();
		param.put("result", baseObj.getRtnResult()+"");
    	param.put("msg", baseObj.getRtnMsg());

		return new ModelAndView("json_").addObject("param", param);
	}

	/**
	 * 내 활동 내역
	 * @param request
	 * @param response
	 * @param modelAndView
	 * @return
	 * @throws Throwable
	 */
	@RequestMapping(value="/user/action", method = RequestMethod.GET)
	public ModelAndView userAction(@LoginUser MemberSessionVo loginUser, HttpServletRequest request, HttpServletResponse response, ModelAndView modelAndView) throws Throwable {
		// 사용자 위키 활동 내역 리스트 조회
		List<WikiLogVo> wikiLogVoList = userActionService.getMyWikiLogAction(loginUser);

		List<Integer> wikiSpaceIdxList = new ArrayList<Integer>();
		// 조회해온 위키 로그 정보의 공간 인덱스를 중복 제거 한다.

		if(wikiLogVoList != null) {
			if(wikiLogVoList.size() > 0) {

				for(int index=0; index < wikiLogVoList.size(); index++) {
					if(!wikiSpaceIdxList.contains(wikiLogVoList.get(index).getWeSpaceIdx())) {
						wikiSpaceIdxList.add(wikiLogVoList.get(index).getWeSpaceIdx());
					}
				}
			}
		}


		List<WikiLogVo> spaceInfoList = null;
		logger.debug("## wikiSpaceIdxList : " + wikiSpaceIdxList.size());
		logger.debug("## wikiSpaceIdxList : " + wikiSpaceIdxList.toString());
		//TODOLIST 중복 제거된 공간 인덱스로  공간정보를 가져온다.

		if(wikiSpaceIdxList != null) {
			if(wikiSpaceIdxList.size() > 0) {
				spaceInfoList = userActionService.getSpaceInfoByIdx(wikiSpaceIdxList);
				wikiSpaceIdxList = null;
			}
		}


		

		modelAndView.addObject("wikiLogVoList", wikiLogVoList);			// 작성 위키 목록
		modelAndView.addObject("spaceInfoList", spaceInfoList);			// 위키의 공간 목록

		modelAndView.setViewName("user/myinfo/userAction");
		return modelAndView;
	}

	/**
	 * 사용자 선택 알람 내역 조회
	 * @param request
	 * @param response
	 * @param modelAndView
	 * @return
	 * @throws Throwable
	 */
	@RequestMapping(value="/user/alarm", method = RequestMethod.GET)
	public ModelAndView userAlarm(@LoginUser MemberSessionVo loginUser, HttpServletRequest request, HttpServletResponse response, ModelAndView modelAndView) throws Throwable {
		// 메타 알람 정보 조회 (사용자가 선택한 내역까지 조회한다)
		List<MetaInfoVo> metaList = userAlarmService.getUserMetaInfoList(loginUser);

		// 사용자 액션에 따른 알람 리스트를 조회한다.
		List<AlarmInfoVo> alarmList = userAlarmService.getUserAlarmList(loginUser);

		//지금까지 보지 않았던 알림 모두 봤던걸로 처리한다.
		commonService.updateAllRead(loginUser.getWeUserIdx());

		modelAndView.addObject("metaList", metaList);
		modelAndView.addObject("alarmList", alarmList);
		modelAndView.addObject("alarmSize", alarmList.size());
		modelAndView.setViewName("user/myinfo/userAlarm");
		return modelAndView;
	}

	/**
	 * 즐겨찾기 내역
	 * @param request
	 * @param response
	 * @param modelAndView
	 * @return
	 * @throws Throwable
	 */
	@RequestMapping(value="/user/favorite", method = RequestMethod.GET)
	public ModelAndView userFavorite(@LoginUser MemberSessionVo loginUser, HttpServletRequest request, HttpServletResponse response, ModelAndView modelAndView) throws Throwable {
		// 즐겨찾기 공간
		List<WikiFavoriteVo> spaceList = userFavorService.getMyFavoriteSpaceList(loginUser.getWeUserIdx());

		// 즐겨찾기 위키
		List<WikiFavoriteVo> wikiList = userFavorService.getMyFavoriteWikiList(loginUser.getWeUserIdx());

		modelAndView.addObject("spaceList", spaceList);
		modelAndView.addObject("spaceSize", spaceList.size());
		modelAndView.addObject("wikiList", wikiList);
		modelAndView.addObject("wikiSize", wikiList.size());
		modelAndView.setViewName("user/myinfo/userFavorite");
		return modelAndView;
	}

	/**
	 * 내 인맥
	 * @param request
	 * @param response
	 * @param modelAndView
	 * @return
	 * @throws Throwable
	 */
	@RequestMapping(value="/user/connection", method = RequestMethod.GET)
	public ModelAndView userConnection(@LoginUser MemberSessionVo loginUser, HttpServletRequest request, HttpServletResponse response, ModelAndView modelAndView) throws Throwable {

		// 내가 추가한 관심 인맥 리스트
		List<AddFriendVo> myFriendList = userConnectionService.getMyConnection(loginUser);


		logger.debug("### myFriendList : " + myFriendList.toString());

		List<AddFriendVo> myConnectionList = userConnectionService.getConnectionToMe(loginUser);

		modelAndView.addObject("myFriendList", myFriendList);
		modelAndView.addObject("myConnectionList", myConnectionList);
		modelAndView.addObject("myFriendListSize", myFriendList.size());
		modelAndView.addObject("myConnectionListSize", myConnectionList.size());

		modelAndView.setViewName("user/myinfo/userConnection");
		return modelAndView;
	}


	@RequestMapping(value="/user/myspace", method = RequestMethod.GET)
	public ModelAndView userSpace(@LoginUser MemberSessionVo loginUser, HttpServletRequest request, HttpServletResponse response, ModelAndView modelAndView) throws Throwable {
		List<Integer> wikiSpaceIdxList = new ArrayList<Integer>();

		//TODOLIST
		// 가입 신청 내역
		WeSpaceJoin joinDomain = new WeSpaceJoin();
		joinDomain.setWe_user_idx(loginUser.getWeUserIdx());
		joinDomain.setWe_join_type(JoinType.REQUEST);

		// 가입 신청 내역을 조회한다.
		List<WeSpaceJoin> joinList = userActionService.getUserSpaceJoinList(joinDomain, 0);

		// 중복제거 한 후 공간 정보 조회
		if(joinList != null) {
			if(joinList.size() > 0) {
				wikiSpaceIdxList = new ArrayList<Integer>();

				for(int index=0; index < joinList.size(); index++) {
					if(!wikiSpaceIdxList.contains(joinList.get(index).getWe_space_idx())) {
						wikiSpaceIdxList.add(joinList.get(index).getWe_space_idx());
					}
				}
			}
		}
		

		List<WikiLogVo> joinReqSpaceList = null;

		//TODOLIST 중복 제거된 공간 인덱스로  공간정보를 가져온다.
		if(wikiSpaceIdxList != null) {
			if(wikiSpaceIdxList.size() > 0) {
				joinReqSpaceList = userActionService.getSpaceInfoByIdx(wikiSpaceIdxList);
				wikiSpaceIdxList = null;
			}

		}
		
		//TODOLIST
		// 초대받은 내역
		joinDomain.setWe_join_type(JoinType.INVITE);
		// 최근 한달간의 초대받은 내역 을 조회한다.
		List<WeSpaceJoin> inviteList = userActionService.getUserSpaceJoinList(joinDomain, 0);

		// 중복제거 한 후 공간 정보 조회
		if(inviteList != null) {
			if(inviteList.size() > 0) {
				wikiSpaceIdxList = new ArrayList<Integer>();

				for(int index=0; index < inviteList.size(); index++) {
					if(!wikiSpaceIdxList.contains(inviteList.get(index).getWe_space_idx())) {
						wikiSpaceIdxList.add(inviteList.get(index).getWe_space_idx());
					}
				}
			}
		}
		

		List<WikiLogVo> inviteSpaceList = null;

		//TODOLIST 중복 제거된 공간 인덱스로  공간정보를 가져온다.
		if(wikiSpaceIdxList != null) {
			if(wikiSpaceIdxList.size() > 0) {
				inviteSpaceList = userActionService.getSpaceInfoByIdx(wikiSpaceIdxList);
				wikiSpaceIdxList = null;
			}
		}
		
		
		logger.debug("inviteList.size() : " + inviteList.size());
		

		modelAndView.addObject("joinReqSpaceList",joinReqSpaceList);	// 가입신청한 공간 정보
		modelAndView.addObject("joinList",joinList);					// 가입신청 목록
		modelAndView.addObject("joinSize",joinList.size());				// 가입신청 목록

		modelAndView.addObject("inviteSpaceList",inviteSpaceList);		// 초대받은 공간 정보
		modelAndView.addObject("inviteList",inviteList);				// 초대 공간 목록
		modelAndView.addObject("inviteSize",inviteList.size());			// 초대 공간 목록

		modelAndView.setViewName("user/myinfo/mySpace");
		return modelAndView;
	}


	@RequestMapping(value = "/user/awayUser", method = RequestMethod.POST)
	public ModelAndView updateAwayUser(@LoginUser MemberSessionVo loginUser,
			HttpServletRequest request, HttpServletResponse response, ModelAndView modelAndView)  throws Throwable {
		String awayPassword = StringUtil.strNull(request.getParameter("awayPassword"));
		String weUserIdx = StringUtil.strNull(request.getParameter("weUserIdx"));

		int result = 0;

		Map<String, String> param = new HashMap<String, String>();
		if(loginUser.getWeUserIdx() == Integer.parseInt(weUserIdx)) {

			try {
				WeUser weUser = commonService.getUserInfo(Integer.parseInt(weUserIdx));

				String passKey = weUser.getWe_user_key();		// 현재 로그인 사용자의 패스워드 키
				String password = loginService.getEncryptPassword(passKey, awayPassword);

				logger.debug("현재 가져온 PASSWORD : " + password);
				logger.debug("DB의 getWe_user_pwd() : " + weUser.getWe_user_pwd());

				// 사용자가 입력한 현재 비밀번호와 DB에서 가져온 값과 같은가 판단한다.
				if(password.equals(weUser.getWe_user_pwd())) {
					//비밀번호 일치
					//회원 정보 초기화 업데이트
					result = userProfileService.updateAwayUser(loginUser.getWeUserIdx());
					logger.debug("#### result : "  +result);

					if(result > 0) {
						param.put("result", result+"");
				    	param.put("msg", "회원 탈퇴 처리 되었습니다. 곧 자동으로 로그아웃 됩니다. 이용해주셔서 감사합니다.");
					}
				} else {
					result = -1;
					param.put("result", result+"");
			    	param.put("msg", "회원 비밀번호가 일치 하지 않습니다.");
				}

				logger.debug("### result : " + result);
			} catch (Exception e) {
				result = -3;
				param.put("result", result+"");
		    	param.put("msg", "에러가  발생하였습니다.");
			}


		} else {
			result = -2;
			throw new GliderwikiException("사용자 정보가 일치 하지 않습니다.");
		}

		logger.debug("param : "  + param.toString());

		return new ModelAndView("json_").addObject("param", param);
	}


	@RequestMapping(value="/user/addfriend", method = RequestMethod.GET)
	public ModelAndView addFiend(@LoginUser MemberSessionVo loginUser,
			HttpServletRequest request, HttpServletResponse response, ModelAndView modelAndView) throws Throwable {


		//TODO 중복된 사용자는 제외하고 저장한다.
		String arrayCheckId = request.getParameter("arrayCheckId");
		Integer weUserIdx = loginUser.getWeUserIdx();


		Map<String, Object> param = new HashMap<String, Object>();

		int result = 0;
		try {

			result = userConnectionService.addWeFriends(arrayCheckId, weUserIdx);

			if(result > 0) {
				param.put("result", "SUCCESS");
				param.put("status", SystemConst.CALL_SUCCESS);
				param.put("rtnResult", result);
				param.put("rtnMsg", "저장되었습니다.");

			} else if(result == -2) {
				//TODOLIST 중복 처리 메시지 변경해야 함 
				param.put("result", "ALREADY");
				param.put("status", SystemConst.CALL_FAIL);
				param.put("rtnResult", -1);
				param.put("rtnMsg", "저장이 되지 않았습니다. 다시 시도 하세요");
			}else {
				param.put("result", "FAIL");
				param.put("status", SystemConst.CALL_FAIL);
				param.put("rtnResult", -1);
				param.put("rtnMsg", "저장이 되지 않았습니다. 다시 시도 하세요");
			}
		} catch (Exception e) {
			param.put("result", "FAIL");
			param.put("status", SystemConst.CALL_FAIL);
			param.put("rtnResult", -2);
			param.put("rtnMsg", "에러가 발생하였습니다.");
		}

		return new ModelAndView("json_").addObject("param", param);
	}

}