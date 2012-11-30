/**
 * @FileName  : WikiContoller.java
 * @Project   : NightHawk
 * @Date      : 2012. 7. 4.
 * @작성자    : @author yion

 * @변경이력     :
 * @프로그램 설명   : 위키 저장, 수정, 조회를 담당하는 컨트롤러
 */
package org.gliderwiki.web.wiki.parser.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.gliderwiki.framework.entity.service.EntityService;
import org.gliderwiki.framework.exception.DBHandleException;
import org.gliderwiki.framework.exception.FilePermitMsgException;
import org.gliderwiki.framework.util.DateUtil;
import org.gliderwiki.framework.util.FileUploader;
import org.gliderwiki.framework.util.GliderFileUtils;
import org.gliderwiki.framework.util.StringUtil;
import org.gliderwiki.util.WikiProdectStatus;
import org.gliderwiki.web.common.DownLoadAction;
import org.gliderwiki.web.domain.WeFile;
import org.gliderwiki.web.domain.WePoint;
import org.gliderwiki.web.domain.WeProfile;
import org.gliderwiki.web.domain.WeSpace;
import org.gliderwiki.web.domain.WeTemplate;
import org.gliderwiki.web.domain.WeUser;
import org.gliderwiki.web.domain.WeWiki;
import org.gliderwiki.web.domain.WeWikiFile;
import org.gliderwiki.web.domain.WeWikiGraph;
import org.gliderwiki.web.domain.WeWikiLink;
import org.gliderwiki.web.domain.WeWikiNote;
import org.gliderwiki.web.domain.WeWikiSummary;
import org.gliderwiki.web.domain.WeWikiTag;
import org.gliderwiki.web.system.SystemConst;
import org.gliderwiki.web.system.argumentresolver.LoginUser;
import org.gliderwiki.web.user.service.UserActionService;
import org.gliderwiki.web.vo.JsonResponse;
import org.gliderwiki.web.vo.JsonResponse.ResponseStatus;
import org.gliderwiki.web.vo.MemberSessionVo;
import org.gliderwiki.web.vo.TempUploadVo;
import org.gliderwiki.web.vo.WikiLogVo;
import org.gliderwiki.web.wiki.common.service.CommonService;
import org.gliderwiki.web.wiki.parser.service.WikiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;


/**
 * @author yion
 *
 */
@Controller
@RequestMapping(value = "/wiki")
public class WikiController {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@SuppressWarnings("rawtypes")
	@Autowired
	private EntityService entityService;

	@Autowired
	private WikiService wikiService;

	@Autowired
	private CommonService commonService;

	@Autowired
	private UserActionService userActionService;

	@Value("#{config['file.maxSize']}")
	String uploadMaxSize;

	/**
	 * 위키 신규 페이지를 생성한다.
	 *
	 * @param request
	 * @param response
	 * @param modelAndView
	 * @return
	 * @throws Throwable
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/editor/{spaceIdx}", method = RequestMethod.GET)
	public String editor(@LoginUser MemberSessionVo loginUser, @PathVariable("spaceIdx") Integer spaceIdx,
			@ModelAttribute("wikiForm") WeWiki wikiForm, Model model) throws Throwable {
		logger.debug("#### spaceIdx : {}", spaceIdx);

		// 템플릿 정보 조회
		WeTemplate temp = new WeTemplate();
		temp.setWe_use_yn("Y");

		List<WeTemplate> template = wikiService.getWeTemplateIdx(temp);

		WeSpace searchSpace = new WeSpace();
		searchSpace.setWe_use_yn("Y");
		searchSpace.setWe_space_idx(spaceIdx);

		// 공간 정보 조회
		WeSpace weSpace = (WeSpace) entityService.getRowEntity(searchSpace);

		wikiForm.setWe_space_idx(spaceIdx);

		JSONArray jsonTemplate = null;
		if (template != null) {
			jsonTemplate = JSONArray.fromObject(template); // 리스트형태
			// jsonTemplate =
			// JSONObject.fromObject(JSONSerializer.toJSON(template)); // 오브젝트
			// 형태
			logger.debug("### jsonTemplate : " + jsonTemplate.toString());
		} else {
			jsonTemplate = null;
		}

		model.addAttribute("editMode", "new");
		model.addAttribute("weSpace", weSpace);
		model.addAttribute("template", jsonTemplate);

		return "/wiki/editor";
	}

	/**
	 * 내가 작성한 위키 목록을 조회 한다.
	 *
	 * @param loginUser
	 * @param model
	 * @return
	 * @throws Throwable
	 */
	@RequestMapping(value = "/mywiki", method = RequestMethod.GET)
	public String myWikiList(@LoginUser MemberSessionVo loginUser, Model model) throws Throwable {
		// 사용자 위키 활동 내역 리스트 조회
		List<WikiLogVo> wikiLogVoList = userActionService.getMyWikiLogAction(loginUser);

		List<Integer> wikiSpaceIdxList = new ArrayList<Integer>();
		// 조회해온 위키 로그 정보의 공간 인덱스를 중복 제거 한다.

		if (wikiLogVoList != null) {
			if (wikiLogVoList.size() > 0) {

				for (int index = 0; index < wikiLogVoList.size(); index++) {
					if (!wikiSpaceIdxList.contains(wikiLogVoList.get(index).getWeSpaceIdx())) {
						wikiSpaceIdxList.add(wikiLogVoList.get(index).getWeSpaceIdx());
					}
				}
			}
		}

		List<WikiLogVo> spaceInfoList = null;
		logger.debug("## wikiSpaceIdxList : " + wikiSpaceIdxList.size());
		logger.debug("## wikiSpaceIdxList : " + wikiSpaceIdxList.toString());
		// TODOLIST 중복 제거된 공간 인덱스로 공간정보를 가져온다.

		if (wikiSpaceIdxList != null) {
			if (wikiSpaceIdxList.size() > 0) {
				spaceInfoList = userActionService.getSpaceInfoByIdx(wikiSpaceIdxList);
				wikiSpaceIdxList = null;
			}
		}

		model.addAttribute("wikiLogVoList", wikiLogVoList);
		model.addAttribute("spaceInfoList", spaceInfoList);

		return "/wiki/mywiki";

	}

	/**
	 * 위키 목록 조회 - 추후 공간 클릭시 위키 목록으로 이동 (사용안함)
	 *
	 * @param weWikiIdx
	 * @param model
	 * @return
	 * @throws Throwable
	 */
	@RequestMapping(value = "/list/{spaceIdx}", method = RequestMethod.GET)
	public String list(@PathVariable("spaceIdx") Integer spaceIdx, Model model) throws Throwable {

		// 공간에 속한 위키 목록 조회함.

		List<WeWiki> wikiList = wikiService.getWikiList(spaceIdx);

		model.addAttribute("wikiList", wikiList);
		model.addAttribute("spaceIdx", spaceIdx);

		return "/wiki/list";

	}

	/**
	 * 위키 조회
	 *
	 * @param wikiIdx
	 * @param model
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = { "/{weWikiIdx}", "/pdf/{weWikiIdx}" }, method = RequestMethod.GET)
	public String view(@LoginUser MemberSessionVo loginUser, @PathVariable("weWikiIdx") Integer weWikiIdx,
			ModelMap model, @RequestParam(value = "pdfView", required = false) String pdfView) throws Throwable {

		logger.debug("###loginUser : " + loginUser.toString());

		WeWiki weWiki = commonService.getWikiInfo(weWikiIdx);

		// 태그목록
		List<WeWikiTag> tagList = null;

		// 각주목록
		List<WeWikiNote> noteList = null;

		// 링크목록
		List<WeWikiLink> linkList = null;

		// 파일목록
		List<WeWikiFile> fileList = null;

		// 섬머리목록
		List<WeWikiSummary> summaryList = null;

		// 그래프
		WeWikiGraph wikiGraph = null;

		try {
			tagList = commonService.getWeWikiTagList(weWikiIdx, weWiki.getWe_wiki_revision());
			noteList = commonService.getWeWikiNoteList(weWikiIdx, weWiki.getWe_wiki_revision());
			linkList = commonService.getWeWikiLinkList(weWikiIdx, weWiki.getWe_wiki_revision());
			fileList = commonService.getWeWikiFileList(weWikiIdx, weWiki.getWe_wiki_revision());
			summaryList = commonService.getWeWikiSummaryList(weWikiIdx, weWiki.getWe_wiki_revision());
			wikiGraph = commonService.getWeWikiGraph(weWikiIdx, weWiki.getWe_wiki_revision());
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (loginUser.getWeUserIdx() != null) {
			logger.debug("#### loginUser.getWeUserIdx() : " + loginUser.getWeUserIdx());
		}

		// 글상태
		String protectStatus = WikiProdectStatus.prodectStatus(weWiki.getWe_wiki_protect());

		// 회원 닉네임검색
		WeUser weuser = new WeUser();
		weuser.setWe_user_idx(weWiki.getWe_ins_user());
		weuser = (WeUser) entityService.getRowEntity(weuser);

		// 회원 권한
		WeProfile weprofile = new WeProfile();
		weprofile.setWe_user_idx(weuser.getWe_user_idx());
		weprofile.setWe_away_yn(null);
		weprofile.setWe_tech_yn(null);
		weprofile.setWe_point(null);
		weprofile = (WeProfile) entityService.getRowEntity(weprofile);

		// 조회수 증가
		weWiki.setWe_wiki_view_cnt(weWiki.getWe_wiki_view_cnt() + 1);
		entityService.updateEntity(weWiki);
		
		String htmlContent = "";
		String htmlTagRemove = StringUtil.removeTags(weWiki.getWe_wiki_markup().replaceAll("\r\n", ""));
		if(htmlTagRemove.length() > 80) { 
			htmlContent = htmlTagRemove.substring(0,  80)+"...";	
		} else {
			htmlContent = htmlTagRemove;
		}
				

		// TODO 조회 사용자 insert 해야 함

		model.addAttribute("weWiki", weWiki);
		model.addAttribute("tagList", tagList);
		model.addAttribute("noteList", noteList);
		model.addAttribute("linkList", linkList);
		model.addAttribute("fileList", fileList);
		model.addAttribute("summaryList", summaryList);
		model.addAttribute("wikiGraph", wikiGraph);
		model.addAttribute("userNick", weuser.getWe_user_nick());
		model.addAttribute("weGrade", weprofile.getWe_grade());
		model.addAttribute("protectStatus", protectStatus);
		model.addAttribute("title", weWiki.getWe_wiki_title());
		model.addAttribute("htmlContent", htmlContent);

		if (StringUtils.equals("ok", pdfView)) {
			return "wiki/pdf/show";
		}

		return "/wiki/show";

	}

	/**
	 * 위키 신규 페이지를 생성한다.
	 *
	 * @param request
	 * @param response
	 * @param modelAndView
	 * @return
	 * @throws Throwable
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/new/{spaceIdx}", method = RequestMethod.GET)
	public String newForm(@LoginUser MemberSessionVo loginUser, @PathVariable("spaceIdx") Integer spaceIdx,
			@ModelAttribute("wikiForm") WeWiki wikiForm, Model model) throws Throwable {
		logger.debug("#### spaceIdx : {}", spaceIdx);

		// 템플릿 정보 조회
		WeTemplate temp = new WeTemplate();
		temp.setWe_use_yn("Y");

		List<WeTemplate> template = wikiService.getWeTemplateIdx(temp);

		WeSpace searchSpace = new WeSpace();
		searchSpace.setWe_use_yn("Y");
		searchSpace.setWe_space_idx(spaceIdx);

		// 공간 정보 조회
		WeSpace weSpace = (WeSpace) entityService.getRowEntity(searchSpace);

		wikiForm.setWe_space_idx(spaceIdx);

		JSONArray jsonTemplate = null;
		if (template != null) {
			jsonTemplate = JSONArray.fromObject(template); // 리스트형태
			// jsonTemplate =
			// JSONObject.fromObject(JSONSerializer.toJSON(template)); // 오브젝트
			// 형태
			logger.debug("### jsonTemplate : " + jsonTemplate.toString());
		} else {
			jsonTemplate = null;
		}

		model.addAttribute("menuAttr", "default"); // 기본 메뉴 출력
		model.addAttribute("editMode", "new");
		model.addAttribute("weSpace", weSpace);
		model.addAttribute("template", jsonTemplate);

		return "/wiki/form";
	}

	/**
	 * 수정 페이지 이동
	 *
	 * @param loginUser
	 * @param wikiIdx
	 * @param wikiForm
	 * @param model
	 * @return
	 * @throws Throwable
	 */
	@RequestMapping(value = "/edit/{wikiIdx}", method = RequestMethod.GET)
	public String editForm(@LoginUser MemberSessionVo loginUser, @PathVariable("wikiIdx") Integer wikiIdx,
			@ModelAttribute("wikiForm") WeWiki wikiForm, Model model) throws Throwable {
		// [TODO] 템플릿 정보 조회
		// [TODO] 공개 레벨 목록 조회
		// [TODO] 위키 마크업 정보 조회
		// [TODO] 공간정보 조회
		logger.debug("####wikiIdx : {}", wikiIdx);

		wikiForm.setWe_wiki_idx(wikiIdx);

		// 템플릿 정보 조회
		WeTemplate temp = new WeTemplate();
		temp.setWe_use_yn("Y");

		List<WeTemplate> template = wikiService.getWeTemplateIdx(temp);

		WeSpace searchSpace = new WeSpace();
		searchSpace.setWe_use_yn("Y");
		searchSpace.setWe_space_idx(wikiForm.getWe_space_idx());

		// 공간 정보 조회
		WeSpace weSpace = (WeSpace) entityService.getRowEntity(searchSpace);

		// 템플릿 정보 조회
		JSONArray jsonTemplate = null;
		if (template != null) {
			jsonTemplate = JSONArray.fromObject(template); // 리스트형태
			// jsonTemplate =
			// JSONObject.fromObject(JSONSerializer.toJSON(template)); // 오브젝트
			// 형태
			logger.debug("### jsonTemplate : " + jsonTemplate.toString());
		} else {
			jsonTemplate = null;
		}

		try {
			// TODO 수정 모드로 전환하고 Push 해야함
			wikiForm = wikiService.getWikiForEdit(wikiForm, loginUser);
		} catch (Throwable e) {
			e.printStackTrace();
		}

		// 파일이 있을 경우 파일 정보 조회
		List<WeWikiFile> fileList = commonService.getWeWikiFileList(wikiForm.getWe_wiki_idx(),
				wikiForm.getWe_wiki_revision());

		// 태그가 있을 경우 태그 정보 조회
		List<WeWikiTag> tagList = commonService.getWeWikiTagList(wikiForm.getWe_wiki_idx(),
				wikiForm.getWe_wiki_revision());

		String weTag = "";
		if (tagList.size() > 0) {
			for (int index = 0; index < tagList.size(); index++) {
				if (index < tagList.size() - 1) {
					weTag = weTag + tagList.get(index).getWe_tag() + ", ";
				} else {
					weTag = weTag + tagList.get(index).getWe_tag();
				}
			}

		}

		// 에러가 존재 할 시....
		if (model.containsAttribute("errorMessage")) {
			return "redirect:/wiki/" + wikiIdx;
		}

		model.addAttribute("weWiki", wikiForm);
		model.addAttribute("editMode", "edit");
		model.addAttribute("weTag", weTag);
		model.addAttribute("fileList", fileList);
		model.addAttribute("fileSize", fileList.size());
		model.addAttribute("weSpace", weSpace);
		model.addAttribute("template", jsonTemplate);

		return "/wiki/form";
	}

	/**
	 * 위키 페이지 저장
	 *
	 * @param wikiForm
	 * @param request
	 * @param response
	 * @param modelAndView
	 * @return
	 * @throws Throwable
	 */
	@ResponseBody
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public JsonResponse save(@LoginUser MemberSessionVo loginUser,
			@RequestParam(value = "weTag", required = false) String weTag, @ModelAttribute WeWiki wikiForm,
			HttpServletRequest request) throws Throwable {
		logger.debug("### 위키 저장  ");
		int isUpload = Integer.parseInt(StringUtil.strNullToSpace(request.getParameter("isUpload"), "0"));
		logger.debug("####### isUpload : " + isUpload);
		String[] weFileIdx = request.getParameterValues("weFileIdx");

		if (weFileIdx != null) {
			int size = weFileIdx.length;

			for (int i = 0; i < size; i++) {
				logger.debug("####### weFileIdx[" + i + "] : " + weFileIdx[i]);
			}

		}

		logger.debug("####### wikiForm :::{}", wikiForm.toString());
		logger.debug("####### weTag :::{}", weTag);

		JsonResponse res = new JsonResponse();

		JsonResponse.ResultStatusInfo result = new JsonResponse.ResultStatusInfo();

		// TODO TODOLIST 공간 접근 허용 권한 체크
		// 공간순번, 위키제목, 위키내용 Validation
		// 첨부파일 저장

		// 임시저장 안내 - 미리보기를 실행하기 위해 현재 작성된 위키를 임시 저장합니다.
		// 위키 스페이스 정보 조회

		WeSpace searchSpace = new WeSpace();
		searchSpace.setWe_use_yn("Y");
		searchSpace.setWe_space_idx(wikiForm.getWe_space_idx());

		WeSpace weSpace = (WeSpace) entityService.getRowEntity(searchSpace);

		// if(weSpace == null) {
		// 익셉션 처리
		// throw new PageMoveException("", "", 1);
		// }
		// 위키 html 일 경우 markUp 생성, markUp일 경우 HTML 생성

		// 공간순번, 위키제목, 위키내용 check

		// 신규 위키 저장을 위한 기본정보 세팅
		wikiForm.newBasicSetting(loginUser.getWeUserIdx(), "S", request.getRemoteAddr());

		// 위키 기본정보 저장
		// 위키링크 저장 - 있을 경우
		// 위키태그 저장 - 있을 경우
		// 위키각주 저장 - 있을 경우
		// 위키 컨텐츠요약 서머리 저장 - 있을 경우
		// 에러 발생시 위키작성로그 저장
		int insertWikiIdx = 0;

		try {
			if (weFileIdx == null) { // 업로드가 없음
				insertWikiIdx = wikiService.addWikiAllContents(wikiForm, weSpace, StringUtil.strNull(weTag));
			} else { // 파일 업로드
				insertWikiIdx = wikiService.addWikiAllContents(wikiForm, weSpace, StringUtil.strNull(weTag), weFileIdx,
						request);
			}

			// 파일이 있는 경우 파일 저장
			logger.debug("#insertResult : {}", insertWikiIdx);

			// 회원 포인트 추가 - 위키 등록
			commonService.updateUserPoint(loginUser.getWeUserIdx(), WePoint.REGIST_WIKI.point);

			/**
			 * TODO TODOLIST : 위키 저장시, 공간 관리자에게 새 글이 추가 되었음을 알람 1. 공간의 관리자가
			 * WE_USER_ALARM 테이블에 2(SPACE_NEW_POST) 번이 저장되어 있는 경우 WE_ALARM_INFO에
			 * 넣어준다. 등록자, 등록자 닉네임, 알람 지시타입, 타겟 사용자, 위키 정보, 공간 정보
			 */
			logger.debug("### 알람 추가 시작");
			int count = commonService.requestAlarmInfo(loginUser.getWeUserIdx(), loginUser.getWeUserNick(),
					SystemConst.SPACE_NEW_POST, weSpace.getWe_ins_user(), null, weSpace.getWe_space_idx());
			logger.debug("### 알람 추가 끝 : {} ", count);

			result.setMessage("위키가 저장되었습니다.");
			result.setRedirectUrl("/space/main/" + wikiForm.getWe_space_idx());
			res.setStatus(ResponseStatus.SUCCESS);

		} catch (DBHandleException e) {
			// TODOLIST Exception 발생시 에러로그 저장

			logger.debug("### DBHandleException : " + e.getCause());
			logger.debug("### DBHandleException : " + e.getMessage());
			logger.debug("### DBHandleException : " + e.getStatus());

			result.setErrorMsg(e.getMessage());
			result.setMessage("위키가 저장에 실패 하였습니다.");
			res.setStatus(ResponseStatus.FAIL);
		}

		res.setResponse(result);

		return res;
	}

	/**
	 * 위키 수정 저장
	 *
	 * @param loginUser
	 * @param weTag
	 * @param wikiForm
	 * @param request
	 * @return
	 * @throws Throwable
	 */
	@ResponseBody
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public JsonResponse edit(@LoginUser MemberSessionVo loginUser,
			@RequestParam(value = "weTag", required = false) String weTag, @ModelAttribute WeWiki wikiForm,
			HttpServletRequest request) throws Throwable {
		logger.debug("### 위키 수정 저장  ");

		int isUpload = Integer.parseInt(StringUtil.strNullToSpace(request.getParameter("isUpload"), "0"));
		String weEditText = StringUtil.strNullToSpace(request.getParameter("weEditText"), "");
		logger.debug("####### isUpload : " + isUpload);
		wikiForm.setWe_edit_text(weEditText);

		String[] weFileIdx = request.getParameterValues("weFileIdx");

		logger.debug("####### wikiForm :::{}", wikiForm.toString());
		logger.debug("####### weTag :::{}", weTag);

		JsonResponse res = new JsonResponse();

		JsonResponse.ResultStatusInfo result = new JsonResponse.ResultStatusInfo();

		// TODO TODOLIST 공간 접근 허용 권한 체크
		// 공간순번, 위키제목, 위키내용 Validation
		// 첨부파일 저장

		// 위키 스페이스 정보 조회

		WeSpace searchSpace = new WeSpace();
		searchSpace.setWe_use_yn("Y");
		searchSpace.setWe_space_idx(wikiForm.getWe_space_idx());

		WeSpace weSpace = (WeSpace) entityService.getRowEntity(searchSpace);

		// if(weSpace == null) {
		// 익셉션 처리
		// throw new PageMoveException("", "", 1);
		// }
		// 위키 html 일 경우 markUp 생성, markUp일 경우 HTML 생성

		// 공간순번, 위키제목, 위키내용 check

		int insertWikiIdx = 0;

		try {

			if (isUpload == 0) { // 업로드가 없음
				insertWikiIdx = wikiService.modifiedWikiAndSaveRevision(wikiForm, weSpace, StringUtil.strNull(weTag),
						loginUser, request);
			} else { // 파일 업로드
				insertWikiIdx = wikiService.modifiedWikiAndSaveRevision(wikiForm, weSpace, StringUtil.strNull(weTag),
						loginUser, request, weFileIdx);
			}

			// 파일이 있는 경우 파일 저장
			logger.debug("#insertResult : {}", insertWikiIdx);

			// 회원 포인트 추가 - 위키 수정
			commonService.updateUserPoint(loginUser.getWeUserIdx(), WePoint.UPDATE_WIKI.point);

			/**
			 * TODO TODOLIST : 위키 저장시, 공간 관리자에게 새 글이 추가 되었음을 알람 1. 공간의 관리자가
			 * WE_USER_ALARM 테이블에 2(SPACE_NEW_POST) 번이 저장되어 있는 경우 WE_ALARM_INFO에
			 * 넣어준다. 등록자, 등록자 닉네임, 알람 지시타입, 타겟 사용자, 위키 정보, 공간 정보
			 */
			logger.debug("### 알람 추가 시작");
			int count = commonService.requestAlarmInfo(loginUser.getWeUserIdx(), loginUser.getWeUserNick(),
					SystemConst.WIKI_EDIT, wikiForm.getWe_ins_user(), wikiForm.getWe_wiki_idx(),
					weSpace.getWe_space_idx());
			logger.debug("### 알람 추가 끝 : {} ", count);

			result.setMessage("위키 수정되었습니다.");
			result.setRedirectUrl("/space/main/" + wikiForm.getWe_space_idx());

			logger.debug("##### result : " + result.toString());
			res.setStatus(ResponseStatus.SUCCESS);

			logger.debug("##### res : " + res.toString());

		} catch (DBHandleException e) {
			// TODOLIST Exception 발생시 에러로그 저장

			logger.debug("### DBHandleException : " + e.getCause());
			logger.debug("### DBHandleException : " + e.getMessage());
			logger.debug("### DBHandleException : " + e.getStatus());

			result.setErrorMsg(e.getMessage());
			result.setMessage("위키가 수정에 실패 하였습니다.");
			res.setStatus(ResponseStatus.FAIL);
		}

		res.setResponse(result);

		return res;
	}

	/**
	 * 위키의 부모글이 있는 새 글을 작성한다.
	 *
	 * @param spaceIdx
	 * @param parentIdx
	 * @param request
	 * @param response
	 * @param modelAndView
	 * @return
	 * @throws Throwable
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/new/{spaceIdx}/{parentIdx}", method = RequestMethod.GET)
	public String replyForm(@LoginUser MemberSessionVo loginUser, @PathVariable("spaceIdx") Integer spaceIdx,
			@PathVariable("parentIdx") Integer parentIdx, Model model) throws Throwable {

		if (loginUser.getWeUserId().equals("GUEST") || loginUser.getWeUserIdx() == null) {
			// 에러 처리
		}
		// 템플릿 정보 조회
		WeTemplate temp = new WeTemplate();
		temp.setWe_use_yn("Y");

		List<WeTemplate> template = wikiService.getWeTemplateIdx(temp);

		WeSpace searchSpace = new WeSpace();
		searchSpace.setWe_use_yn("Y");
		searchSpace.setWe_space_idx(spaceIdx);

		// 공간 정보 조회
		WeSpace weSpace = (WeSpace) entityService.getRowEntity(searchSpace);

		JSONArray jsonTemplate = null;
		if (template != null) {
			jsonTemplate = JSONArray.fromObject(template); // 리스트형태
			// jsonTemplate =
			// JSONObject.fromObject(JSONSerializer.toJSON(template)); // 오브젝트
			// 형태
			logger.debug("### jsonTemplate : " + jsonTemplate.toString());
		} else {
			jsonTemplate = null;
		}

		// wikiForm 을 세팅 한다.
		WeWiki wikiForm = new WeWiki();
		wikiForm = commonService.getWikiInfo(parentIdx);
		wikiForm.setWe_wiki_text(null);
		wikiForm.setWe_wiki_title(null);
		wikiForm.setWe_wiki_markup(null);

		logger.debug("#### wikiForm " + wikiForm.toString());

		model.addAttribute("weWiki", wikiForm);
		model.addAttribute("editMode", "reply");
		model.addAttribute("weSpace", weSpace);
		model.addAttribute("template", jsonTemplate);
		model.addAttribute("wikiForm", wikiForm);

		return "/wiki/form";
	}

	/**
	 * 부모 위키에 하위 위키를 저장한다.
	 *
	 * @param loginUser
	 * @param weTag
	 * @param wikiForm
	 * @param request
	 * @return
	 * @throws Throwable
	 */
	@ResponseBody
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/sub/{parentIdx}", method = RequestMethod.POST)
	public JsonResponse saveSubWiki(@LoginUser MemberSessionVo loginUser,
			@RequestParam(value = "weTag", required = false) String weTag, @ModelAttribute WeWiki wikiForm,
			HttpServletRequest request) throws Throwable {

		logger.debug("### 부모글이 있는 위키 저장  :  {}", wikiForm.getWe_wiki_parent_idx());
		logger.debug("### weWiki : {}", wikiForm.toString());

		logger.debug("### 위키 저장  ");
		int isUpload = Integer.parseInt(StringUtil.strNullToSpace(request.getParameter("isUpload"), "0"));
		logger.debug("####### isUpload : " + isUpload);
		String[] weFileIdx = request.getParameterValues("weFileIdx");

		if (weFileIdx != null) {
			int size = weFileIdx.length;

			for (int i = 0; i < size; i++) {
				logger.debug("####### weFileIdx[" + i + "] : " + weFileIdx[i]);
			}

		}

		logger.debug("####### wikiForm :::{}", wikiForm.toString());
		logger.debug("####### weTag :::{}", weTag);

		JsonResponse res = new JsonResponse();

		JsonResponse.ResultStatusInfo result = new JsonResponse.ResultStatusInfo();

		WeSpace searchSpace = new WeSpace();
		searchSpace.setWe_use_yn("Y");
		searchSpace.setWe_space_idx(wikiForm.getWe_space_idx());

		WeSpace weSpace = (WeSpace) entityService.getRowEntity(searchSpace);

		// if(weSpace == null) {
		// 익셉션 처리
		// throw new PageMoveException("", "", 1);
		// }
		// 위키 html 일 경우 markUp 생성, markUp일 경우 HTML 생성

		// wikiForm.newBasicSetting(loginUser.getWeUserIdx(), "S",
		// request.getRemoteAddr());

		// 답변 컬럼을 제외한 나머지 컬럼을 초기화 한다.
		wikiForm.setWe_use_yn("Y");
		wikiForm.setWe_ins_user(loginUser.getWeUserIdx());
		wikiForm.setWe_wiki_revision(1);
		wikiForm.setWe_wiki_status("S"); // 저장상태
		wikiForm.setWe_user_ip(request.getRemoteAddr());
		wikiForm.setWe_wiki_agree(0);
		wikiForm.setWe_wiki_view_cnt(0);
		wikiForm.setWe_wiki_protect("0");
		wikiForm.setWe_edit_yn("Y"); // 수정 가능 상태
		wikiForm.setWe_ins_date(new Date());

		int insertWikiIdx = 0;

		try {

			if (weFileIdx == null) { // 업로드가 없음 (메소드 오버로딩으로 추가 작업이 있을 경우 미리 검증하도록 한다)
				insertWikiIdx = wikiService.addSubWikiAllContents(wikiForm, weSpace, StringUtil.strNull(weTag));
			} else { // 파일 업로드
				insertWikiIdx = wikiService.addSubWikiAllContents(wikiForm, weSpace, StringUtil.strNull(weTag),
						weFileIdx, request);
			}

			// 저장 후 결과값
			logger.debug("#insertWikiIdx : " + insertWikiIdx);

			// 회원 포인트 추가 - 위키 등록
			commonService.updateUserPoint(loginUser.getWeUserIdx(), WePoint.REGIST_WIKI.point);

			/**
			 * TODO TODOLIST : 위키 저장시, 공간 관리자에게 새 글이 추가 되었음을 알람 1. 공간의 관리자가
			 * WE_USER_ALARM 테이블에 2(SPACE_NEW_POST) 번이 저장되어 있는 경우 WE_ALARM_INFO에
			 * 넣어준다. 등록자, 등록자 닉네임, 알람 지시타입, 타겟 사용자, 위키 정보, 공간 정보
			 */
			// logger.debug("### 알람 추가 시작");
			// int count =
			// commonService.requestAlarmInfo(loginUser.getWeUserIdx(),
			// loginUser.getWeUserNick(),
			// SystemConst.SPACE_NEW_POST, weSpace.getWe_admin_idx(), null,
			// weSpace.getWe_space_idx());
			// logger.debug("### 알람 추가 끝 : {} ", count);

			result.setMessage("위키 저장에 성공 하였습니다. 페이지 이동 하겠습니다.");
			result.setRedirectUrl("/wiki/" + (insertWikiIdx));
			res.setStatus(ResponseStatus.SUCCESS);

		} catch (DBHandleException e) {
			// TODOLIST Exception 발생시 에러로그 저장

			logger.debug("### DBHandleException : " + e.getCause());
			logger.debug("### DBHandleException : " + e.getMessage());
			logger.debug("### DBHandleException : " + e.getStatus());

			result.setErrorMsg(e.getMessage());
			result.setMessage("위키가 저장에 실패 하였습니다.");
			res.setStatus(ResponseStatus.FAIL);
		}

		res.setResponse(result);

		return res;

	}

	/**
	 * 위키 파일 업로드
	 *
	 * @param loginUser
	 * @param fileVo
	 * @param request
	 * @param response
	 * @param modelAndView
	 * @return
	 * @throws Throwable
	 */
	@RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
	public ModelAndView fileUpload(@LoginUser MemberSessionVo loginUser,
			@ModelAttribute("wikiForm") TempUploadVo fileVo, HttpServletRequest request, HttpServletResponse response,
			ModelAndView modelAndView) throws Throwable {
		String svcPath = (String) request.getAttribute("tempRootPath");

		// 업로드 날짜 및 파일명 구성
		String today = DateUtil.getToday();

		// TODOLIST : 세션에서 가져온다. 일단 테스트용으로 사용자 순번 1을 통해 등록한다.
		String weUserIdx = Integer.toString(loginUser.getWeUserIdx());

		double maxSize = Double.parseDouble(uploadMaxSize);
		TempUploadVo tempFile = null;
		Map<String, String> param = new HashMap<String, String>();
		// 파일 객체, 사용자 아이디, 오늘날짜, 파일 업로드 사이즈
		try {
			tempFile = FileUploader.uploadTempFile(fileVo.getFile(), svcPath, weUserIdx, today, maxSize);
		} catch (FilePermitMsgException e) {
			logger.debug("###익셉션 메시지 : " + e.getCustomMsg());
			logger.debug("###익셉션 메시지 : " + e.toString());
			param.put("result", "-1");
			param.put("msg", e.getCustomMsg());

			return new ModelAndView("json_").addObject("param", param);
		} catch (MaxUploadSizeExceededException e1) {
			logger.debug("###익셉션 메시지 : " + e1.getMessage());
			logger.debug("###익셉션 메시지 : " + e1.getLocalizedMessage());
			param.put("result", "-1");
			param.put("msg", e1.getMessage());

			return new ModelAndView("json_").addObject("param", param);
		} catch (Exception ee) {
			logger.debug("**************************예상치 않은 Exception 발생!!!!");
			ee.printStackTrace();
		}

		logger.debug("tempFile : " + tempFile.toString());

		if (tempFile.isUploaded()) {// 업로드가 완료되면
			WeFile weFile = new WeFile();

			weFile.setWe_file_real_name(tempFile.getFileRealName());
			weFile.setWe_file_save_name(tempFile.getFileSaveName());
			weFile.setWe_file_save_path(tempFile.getFilePath());
			weFile.setWe_file_size(tempFile.getFileSize() + "");
			weFile.setWe_file_type(tempFile.getFileType());
			weFile.setWe_thumb_yn(tempFile.getThumbYn());
			weFile.setWe_thumb_name(tempFile.getThumbName());
			weFile.setWe_thumb_path(tempFile.getThumbPath());
			weFile.setWe_user_idx(Integer.parseInt(weUserIdx));
			weFile.setWe_ins_date(DateUtil.getTodayTime());
			weFile.setWe_ins_user(weUserIdx);

			int result = commonService.insertWeFile(weFile);

			logger.debug("### result : " + result);

			if (result == 1) {

				WeFile retFile = (WeFile) entityService.getRowEntity(weFile);

				param.put("result", "1");
				param.put("msg", "성공");
				param.put("realFileName", retFile.getWe_file_real_name());
				param.put("saveFileName", retFile.getWe_file_save_name());
				param.put("thumbPath", retFile.getWe_thumb_path());
				param.put("thumbName", retFile.getWe_thumb_name());
				param.put("filePath", retFile.getWe_file_save_path());
				param.put("fileSize", retFile.getWe_file_size() + "");
				param.put("tmpsrc", svcPath);
				param.put("fileIndex", retFile.getWe_file_idx() + "");
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

		logger.debug("param : " + param.toString());

		return new ModelAndView("json_").addObject("param", param);
	}

	@RequestMapping(value = "/imageUpload", method = RequestMethod.POST)
	public ModelAndView imageUpload(@LoginUser MemberSessionVo loginUser,
			@ModelAttribute("wikiForm") TempUploadVo fileVo, HttpServletRequest request, HttpServletResponse response,
			ModelAndView modelAndView) throws Throwable {
		String svcPath = (String) request.getAttribute("realRootPath");
		String width = StringUtil.strNullToSpace(request.getParameter("width"), "0");
		String height = StringUtil.strNullToSpace(request.getParameter("height"), "0");

		// 업로드 날짜 및 파일명 구성
		String today = DateUtil.getToday();

		// TODOLIST : 세션에서 가져온다. 일단 테스트용으로 사용자 순번 1을 통해 등록한다.
		String weUserIdx = Integer.toString(loginUser.getWeUserIdx());

		double maxSize = Double.parseDouble(uploadMaxSize);
		TempUploadVo tempFile = null;
		Map<String, String> param = new HashMap<String, String>();
		// 파일 객체, 사용자 아이디, 오늘날짜, 파일 업로드 사이즈
		try {

			// 이미지 너비, 높이가 들어올 경우 섬네일 사이즈로 추가
			// if(!height.equals("0") && !height.equals("0")) {
			// tempFile = FileUploader.uploadThumbFile(fileVo.getFile(),
			// svcPath, weUserIdx, today, maxSize, width, height);
			// } else {
			// // 아니면 기본 사이즈
			// tempFile = FileUploader.uploadTempFile(fileVo.getFile(), svcPath,
			// weUserIdx, today, maxSize);
			// }

			tempFile = FileUploader.uploadTempFile(fileVo.getFile(), svcPath, weUserIdx, today, maxSize);

		} catch (FilePermitMsgException e) {
			logger.debug("###익셉션 메시지 : " + e.getCustomMsg());
			logger.debug("###익셉션 메시지 : " + e.toString());
			param.put("result", "-1");
			param.put("msg", e.getCustomMsg());

			return new ModelAndView("json_").addObject("param", param);
		} catch (Exception ee) {
			logger.debug("**************************예상치 않은 Exception 발생!!!!");
			ee.printStackTrace();
		}

		logger.debug("tempFile : " + tempFile.toString());

		if (tempFile.isUploaded()) {// 업로드가 완료되면
			WeFile weFile = new WeFile();

			weFile.setWe_file_real_name(tempFile.getFileRealName());
			weFile.setWe_file_save_name(tempFile.getFileSaveName());
			weFile.setWe_file_save_path(tempFile.getFilePath());
			weFile.setWe_file_size(tempFile.getFileSize() + "");
			weFile.setWe_file_type(tempFile.getFileType());
			weFile.setWe_thumb_yn(tempFile.getThumbYn());
			weFile.setWe_thumb_name(tempFile.getThumbName());
			weFile.setWe_thumb_path(tempFile.getThumbPath());
			weFile.setWe_user_idx(Integer.parseInt(weUserIdx));
			weFile.setWe_ins_date(DateUtil.getTodayTime());
			weFile.setWe_ins_user(weUserIdx);

			int result = commonService.insertWeFile(weFile);

			logger.debug("### result : " + result);

			if (result == 1) {

				WeFile retFile = (WeFile) entityService.getRowEntity(weFile);

				param.put("result", "1");
				param.put("msg", "성공");
				param.put("realFileName", retFile.getWe_file_real_name());
				param.put("saveFileName", retFile.getWe_file_save_name());
				param.put("thumbPath", retFile.getWe_thumb_path());
				param.put("thumbName", retFile.getWe_thumb_name());
				param.put("filePath", retFile.getWe_file_save_path());
				param.put("fileSize", retFile.getWe_file_size() + "");
				param.put("tmpsrc", svcPath);
				param.put("fileIndex", retFile.getWe_file_idx() + "");
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

		logger.debug("param : " + param.toString());

		return new ModelAndView("json_").addObject("param", param);
	}

	/**
	 * 위키 파일 삭제
	 *
	 * @param request
	 * @param response
	 * @param modelAndView
	 * @return
	 * @throws Throwable
	 */
	@RequestMapping(value = "/removeFile", method = RequestMethod.GET)
	public ModelAndView removeFile(HttpServletRequest request, HttpServletResponse response, ModelAndView modelAndView)
			throws Throwable {
		String chkType = StringUtil.strNullToSpace(request.getParameter("chkType"), "");
		Integer weFileIdx = Integer.parseInt(StringUtil.strNullToSpace(request.getParameter("weFileIdx"), "0"));
		String targetId = StringUtil.strNullToSpace(request.getParameter("targetId"), "");
		String formId = StringUtil.strNullToSpace(request.getParameter("formId"), "");

		logger.debug("### chkType : " + chkType);
		logger.debug("### weFileIdx : " + weFileIdx);
		logger.debug("### targetId : " + targetId);
		logger.debug("### formId : " + formId);

		Map<String, Object> param = new HashMap<String, Object>();

		try {

			if (chkType.equals("1")) { // 위키 파일 테이블 삭제
				wikiService.delWeWikiFile(weFileIdx);
			} else {
				commonService.delWeFile(weFileIdx);
			}

			param.put("result", "SUCCESS");
			param.put("status", SystemConst.CALL_SUCCESS);
			param.put("chkType", chkType);
			param.put("weFileIdx", weFileIdx);
			param.put("targetId", targetId);
			param.put("formId", formId);

		} catch (Exception e) {
			param.put("result", "FAIL");
			param.put("status", SystemConst.CALL_FAIL);
			param.put("chkType", chkType);
			param.put("weFileIdx", weFileIdx);
			param.put("targetId", targetId);
			param.put("formId", formId);
		}

		return new ModelAndView("json_").addObject("param", param);
	}

	@RequestMapping(value = "/export/pdf/{wikiIdx}", method = RequestMethod.GET)
	public void getPdfFileExport(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("wikiIdx") String wikiIdx) {
		GliderFileUtils fileUtils = new GliderFileUtils();
		fileUtils.htmlToPdfExport(request, response, wikiIdx);
	}

	/**
	 * 위키 파일 다운로드
	 *
	 * @param request
	 * @param response
	 * @param modelAndView
	 * @return
	 * @throws Throwable
	 */
	@RequestMapping("/download")
	public ModelAndView fileDownload(HttpServletRequest request, HttpServletResponse response, ModelAndView modelAndView)
			throws Throwable {
		String realPath = request.getSession().getServletContext().getRealPath("/");

		logger.debug("#realPath : " + realPath);

		String we_file_idx = request.getParameter("we_file_idx");

		if (we_file_idx == null) {
			throw new FilePermitMsgException("File is not exsist!!");
		}

		logger.debug("*************** 파일 다운로드 합니다 *******************");

		WeWikiFile fileInfo = commonService.getWikiFileInfo(Integer.parseInt(we_file_idx));

		// 다운로드 기본 경로
		String filePath = realPath + SystemConst.WIKI_FILE_DOWNLOAD_PATH;

		// 다운로드 할 파일 정보 취득

		// 절대 경로 구하기
		HttpSession session = request.getSession();
		String doc_root = filePath + fileInfo.getWe_file_save_path() + fileInfo.getWe_file_save_name();

		String type = session.getServletContext().getMimeType(doc_root);

		DownLoadAction download = new DownLoadAction();

		// 인자값 (절대경로, 파일이름, response, type)
		download.getFileDownload(doc_root, fileInfo.getWe_file_real_name(), response, type);

		// 파일 조회수 업데이트
		fileInfo.setWe_file_down(fileInfo.getWe_file_down() + 1);
		entityService.updateEntity(fileInfo);

		return null;

	}

	@RequestMapping(value = "/addFavorite", method = RequestMethod.POST)
	@ResponseBody
	public int addFavorite(@LoginUser MemberSessionVo loginUser, @RequestParam(value = "spaceIdx") int spaceIdx,
			@RequestParam(value = "wikiIdx") int wikiIdx) throws Throwable {
		int result = wikiService.addFavorite(loginUser.getWeUserIdx(), spaceIdx, wikiIdx);

		return result;
	}
}