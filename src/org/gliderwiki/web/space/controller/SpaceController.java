/**
 * @FileName  : SpaceController.java
 * @Project   : NightHawk
 * @Date      : 2012. 7. 3.
 * @작성자      : @author bluepoet

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.space.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.gliderwiki.web.domain.AuthorityType;
import org.gliderwiki.web.domain.FavorityType;
import org.gliderwiki.web.domain.ImageInfo;
import org.gliderwiki.web.domain.WeGroupInfo;
import org.gliderwiki.web.domain.WeSpace;
import org.gliderwiki.web.domain.WeSpaceUser;
import org.gliderwiki.web.domain.WeWiki;
import org.gliderwiki.web.domain.WebConstant;
import org.gliderwiki.web.space.service.BoardService;
import org.gliderwiki.web.space.service.SpaceService;
import org.gliderwiki.web.system.argumentresolver.LoginUser;
import org.gliderwiki.web.vo.GroupUserVo;
import org.gliderwiki.web.vo.MemberSessionVo;
import org.gliderwiki.web.vo.SpaceInfoVo;
import org.gliderwiki.web.wiki.common.service.CommonService;
import org.gliderwiki.web.wiki.parser.service.WikiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;


/**
 * @author bluepoet
 *
 */
@Controller
@RequestMapping(value = "/space")
public class SpaceController {

	Logger logger = LoggerFactory.getLogger(SpaceController.class);

	@Resource(name = "spaceService")
	private SpaceService spaceService;

	@Resource(name = "commonService")
	private CommonService commonService;

	@Resource(name = "wikiRegistService")
	private WikiService wikiService;

	@Resource(name = "boardService")
	private BoardService boardService;


	@RequestMapping(value = "", method = RequestMethod.GET)
	public String list(@LoginUser MemberSessionVo loginUser, ModelMap model) throws Throwable {
		List<Map<String, String>> list = spaceService.getAllSpaceList(loginUser.getWeUserIdx());
		logger.debug("생성된 리스트 객체 사이즈 : {}", list.size());

		model.addAttribute("list", list);
		model.addAttribute("listType", "all");

		return "/space/list";
	}

	@RequestMapping(value = "/main/{spaceIdx}", method = RequestMethod.GET)
	public String main(@PathVariable("spaceIdx") int spaceIdx, @LoginUser MemberSessionVo loginUser, ModelMap model)
			throws Throwable {
		SpaceInfoVo spaceInfoVo = spaceService.getSpaceInfo(spaceIdx);
		List<WeWiki> wikiList = wikiService.getWikiList(spaceInfoVo.getSpaceIdx());

		model.addAttribute("spaceInfo", spaceInfoVo);
		model.addAttribute("wikiList", wikiList);
		model.addAttribute("boardList", boardService.getRecentList(spaceIdx));
		model.addAttribute("updatedList", spaceService.getUpdatedList(spaceIdx, 0, 5));
		model.addAttribute("fileList", spaceService.getRecentFileList(spaceIdx));

		return "/space/main";
	}

	@RequestMapping(value = "/entryList", method = RequestMethod.GET)
	public String entryList(@LoginUser MemberSessionVo loginUser, ModelMap model) throws Throwable {
		// 내가 가입한 공간 가져오기
		List<Map<String, String>> myEntryList = spaceService.getMyEntryList(loginUser.getWeUserIdx());

		model.addAttribute("list", myEntryList);
		model.addAttribute("listType", "entry");

		return "/space/list";
	}

	@RequestMapping(value = "/openList", method = RequestMethod.GET)
	public String openList(@LoginUser MemberSessionVo loginUser, ModelMap model) throws Throwable {
		// 내가 개설한 공간 가져오기
		List<Map<String, String>> mySpaceList = spaceService.getMySpaceList(loginUser.getWeUserIdx());

		model.addAttribute("list", mySpaceList);
		model.addAttribute("listType", "open");

		return "/space/list";
	}

	@RequestMapping(value = "/{spaceIdx}", method = RequestMethod.GET)
	public String show(@LoginUser MemberSessionVo loginUser, @PathVariable("spaceIdx") int spaceIdx, ModelMap model)
			throws Throwable {
		SpaceInfoVo spaceInfoVo = spaceService.getSpaceInfo(spaceIdx);

		model.addAttribute("we_space_idx", spaceIdx);
		model.addAttribute("spaceInfo", spaceInfoVo);
		model.addAttribute("adminName", commonService.getUserInfo(spaceInfoVo.getAdminUserIdx()).getWe_user_nick());

		return "/space/show";
	}

	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public String createForm(@LoginUser MemberSessionVo loginUser, ModelMap model) throws Throwable {
		model.addAttribute("WeSpace", new WeSpace());
		model.addAttribute("adminName", loginUser.getWeUserNick());

		return "/space/form";
	}

	@RequestMapping(value = "/{spaceIdx}/form", method = RequestMethod.GET)
	public String updateForm(@LoginUser MemberSessionVo loginUser, @PathVariable("spaceIdx") int spaceIdx,
			ModelMap model) throws Throwable {
		WeSpace weSpace = spaceService.getWeSpaceInfo(spaceIdx);

		Map<String, String> policyData = spaceService.getAuthorityData(weSpace);

		weSpace.setWe_view_data(policyData.get("viewData"));
		weSpace.setWe_view_name(policyData.get("viewName"));
		weSpace.setWe_edit_data(policyData.get("editData"));
		weSpace.setWe_edit_name(policyData.get("editName"));

		String spaceImage = spaceService.getSpaceImageInfo(weSpace.getWe_space_idx());

		// 메뉴 정보에서 spaceInfo 객체를 쓰기위해 추가한다.
		SpaceInfoVo spaceInfoVo = spaceService.getSpaceInfo(spaceIdx);

		model.addAttribute("spaceInfo", spaceInfoVo);
		model.addAttribute("WeSpace", weSpace);
		model.addAttribute("spaceImage", spaceImage);
		model.addAttribute("adminName", commonService.getUserInfo(weSpace.getWe_ins_user()).getWe_user_nick());

		return "/space/form";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(@LoginUser MemberSessionVo loginUser, HttpServletRequest request,
			@ModelAttribute WeSpace weSpace) throws Throwable {
		logger.debug("저장할 Space 객체 : {}", weSpace);

		String tempImgPath = (String) request.getAttribute("tempRootPath");
		String realImgPath = (String) request.getAttribute("realRootPath");
		ImageInfo imageInfo = new ImageInfo(tempImgPath, realImgPath);
		logger.debug("임시업로드루트 : {}, 실제업로드루트 : {}", tempImgPath, realImgPath);

		spaceService.create(weSpace, loginUser.getWeUserIdx(), imageInfo);

		return "redirect:/space";
	}

	@RequestMapping(value = "/create", method = RequestMethod.PUT)
	public String update(@LoginUser MemberSessionVo loginUser, HttpServletRequest request,
			@ModelAttribute WeSpace weSpace) throws Throwable {
		logger.debug("저장할 Space 객체 : {}", weSpace);

		String tempImgPath = (String) request.getAttribute("tempRootPath");
		String realImgPath = (String) request.getAttribute("realRootPath");
		ImageInfo imageInfo = new ImageInfo(tempImgPath, realImgPath);
		logger.debug("임시업로드루트 : {}, 실제업로드루트 : {}", tempImgPath, realImgPath);

		spaceService.update(weSpace, loginUser.getWeUserIdx(), imageInfo);

		return "redirect:/space";
	}

	@RequestMapping(value = "/logo/preUpload", method = RequestMethod.POST)
	public ModelAndView logoImagePreUpload(@LoginUser MemberSessionVo loginUser, HttpServletRequest request)
			throws Exception {
		String tempRootPath = (String) request.getAttribute("tempRootPath");

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile file = multipartRequest.getFile("uploadFile");

		Map<String, String> result = spaceService.tempFileUpload(file, tempRootPath,
				Integer.toString(loginUser.getWeUserIdx()));

		logger.debug("param : {}", result.toString());

		return new ModelAndView("json_").addObject("param", result);
	}

	@RequestMapping(value = "/group/search", method = RequestMethod.GET)
	@ResponseBody
	public List<WeGroupInfo> groupSearch() throws Throwable {

		List<WeGroupInfo> allGroupList = spaceService.getAllGroupList();
		logger.debug("모든 그룹리스트 가져오기 : {}", allGroupList);

		return allGroupList;
	}

	@RequestMapping(value = "/user/search", method = RequestMethod.GET)
	@ResponseBody
	public List<GroupUserVo> userSearch(@RequestParam(value = "groupIdx") int groupIdx) throws Throwable {
		logger.debug("search GroupIdx : {}", groupIdx);
		List<GroupUserVo> groupUserList = spaceService.getGroupUserList(groupIdx);

		return groupUserList;
	}

	@RequestMapping(value = "/user/delete", method = RequestMethod.POST)
	@ResponseBody
	public int userDelete(@LoginUser MemberSessionVo loginUser, @RequestParam(value = "spaceIdx") int spaceIdx,
			@RequestParam(value = "userIdx") int userIdx, @RequestParam(value = "authorityType") String type)
			throws Throwable {
		int result = spaceService.userDeleted(spaceIdx, userIdx, type);

		return result;
	}

	@RequestMapping(value = "/group/delete", method = RequestMethod.POST)
	@ResponseBody
	public int groupDelete(@LoginUser MemberSessionVo loginUser, @RequestParam(value = "spaceIdx") int spaceIdx,
			@RequestParam(value = "groupIdx") int groupIdx, @RequestParam(value = "authorityType") String type)
			throws Throwable {
		logger.debug("spaceIdx, groupIdx : {}{}", spaceIdx, groupIdx);
		int result = spaceService.groupDeleted(spaceIdx, groupIdx, type);

		return result;
	}

	@RequestMapping(value = "/addFavorite", method = RequestMethod.POST)
	@ResponseBody
	public int addFavorite(@LoginUser MemberSessionVo loginUser, @RequestParam(value = "favoriteType") FavorityType type,
			@RequestParam(value = "spaceIdx") int spaceIdx,@RequestParam(value = "wikiIdx", required=false) int wikiIdx) throws Throwable {
		int result = commonService.addFavorite(loginUser.getWeUserIdx(), type, spaceIdx, wikiIdx);

		return result;
	}

	@RequestMapping(value = "/joinRequest", method = RequestMethod.POST)
	@ResponseBody
	public int joinRequest(@LoginUser MemberSessionVo loginUser, @RequestParam(value = "spaceIdx") int spaceIdx)
			throws Throwable {
		int result = spaceService.joinRequest(loginUser.getWeUserIdx(), spaceIdx);

		return result;
	}

	@RequestMapping(value = "/inviteRequest", method = RequestMethod.POST)
	@ResponseBody
	public WebConstant inviteRequest(@LoginUser MemberSessionVo loginUser,
			@RequestParam(value = "arrUserIdx") String arrUserIdx, @RequestParam(value = "spaceIdx") int spaceIdx)
			throws Throwable {
		WebConstant result = spaceService.inviteRequest(loginUser.getWeUserIdx(), spaceIdx, arrUserIdx);

		return result;
	}

	@RequestMapping(value = "/nameDuplicateCheck", method = RequestMethod.POST)
	@ResponseBody
	public WebConstant nameDuplicateCheck(@LoginUser MemberSessionVo loginUser,
			@RequestParam(value = "spaceName") String spaceName) throws Throwable {
		Object result = spaceService.nameDuplicateCheck(spaceName);

		if (result != null) {
			return WebConstant.DUPLICATE;
		}

		return WebConstant.SUCCESS;
	}

	@RequestMapping(value = "/group/{spaceIdx}/selectList", method = RequestMethod.GET)
	public String groupSelectedList(@RequestParam(value = "authorityType") String type,
			@PathVariable("spaceIdx") int spaceIdx, ModelMap model) {
		List<Map<String, Object>> myGroupSelectedList = spaceService.groupSelectedList(spaceIdx);

		model.addAttribute("list", myGroupSelectedList);
		model.addAttribute("authorityType", type);

		return "/space/popup/groupList";
	}

	@RequestMapping(value = "/user/{spaceIdx}/selectList", method = RequestMethod.GET)
	public String userSelectedList(@RequestParam(value = "authorityType") String type,
			@PathVariable("spaceIdx") int spaceIdx, ModelMap model) {
		List<Map<String, Object>> myUserSelectedList = spaceService.userSelectedList(spaceIdx);

		model.addAttribute("list", myUserSelectedList);
		model.addAttribute("authorityType", type);

		return "/space/popup/userList";
	}

	@RequestMapping(value = "/group/list", method = RequestMethod.GET)
	public String groupList(@RequestParam(value = "authorityType") String type, ModelMap model) {
		List<Map<String, Object>> myGroupSelectedList = spaceService.groupList();

		model.addAttribute("list", myGroupSelectedList);
		model.addAttribute("authorityType", type);

		return "/space/popup/groupList";
	}

	@RequestMapping(value = "/user/list", method = RequestMethod.GET)
	public String userList(@RequestParam(value = "authorityType") String type, ModelMap model) {
		List<Map<String, Object>> myUserSelectedList = spaceService.userList();

		model.addAttribute("list", myUserSelectedList);
		model.addAttribute("authorityType", type);

		return "/space/popup/userList";
	}

	@RequestMapping(value = "/joinManage/{spaceIdx}", method = RequestMethod.GET)
	public String joinManage(@LoginUser MemberSessionVo loginUser, @PathVariable("spaceIdx") int spaceIdx,
			ModelMap model) {

		model.addAttribute("entryList", spaceService.getMyEntryRequestedList(spaceIdx));
		model.addAttribute("inviteList", spaceService.getMyInviteList(spaceIdx));

		return "/space/joinManage";
	}

	@RequestMapping(value = "/entryReject", method = RequestMethod.POST)
	@ResponseBody
	public WebConstant entryReject(@RequestParam(value = "spaceJoinIdx") int spaceJoinIdx) {

		int result = spaceService.entryReject(spaceJoinIdx);

		if (result > 0) {
			return WebConstant.SUCCESS;
		}

		return WebConstant.FAIL;
	}

	@RequestMapping(value = "/entryApprove", method = RequestMethod.POST)
	@ResponseBody
	public WebConstant entryApprove(@LoginUser MemberSessionVo loginUser,
			@RequestParam(value = "spaceIdx") Integer spaceIdx, @RequestParam(value = "userIdx") Integer userIdx,
			@RequestParam(value = "spaceJoinIdx") Integer spaceJoinIdx) {
		WeSpaceUser weSpaceUser = new WeSpaceUser(spaceIdx, userIdx, false, false, true);
		int result = spaceService.entryApprove(spaceJoinIdx, weSpaceUser);

		if (result > 0) {
			return WebConstant.SUCCESS;
		}

		return WebConstant.FAIL;
	}

	@RequestMapping(value = "/checkSearchSpaceInfo", method = RequestMethod.POST)
	@ResponseBody
	public WebConstant nameDuplicateCheck(@LoginUser MemberSessionVo loginUser,
			@RequestParam(value = "authorityType") AuthorityType type, @RequestParam(value = "spaceIdx") int spaceIdx) {
		WebConstant result = spaceService.checkSearchSpaceInfo(type, spaceIdx, loginUser.getWeUserIdx(), "view");

		return result;
	}

	@RequestMapping(value = "/createSpaceCheck", method = RequestMethod.GET)
	@ResponseBody
	public WebConstant authorityCheckFromCreateSpace(@LoginUser MemberSessionVo loginUser) {
		return WebConstant.SUCCESS;
	}
}