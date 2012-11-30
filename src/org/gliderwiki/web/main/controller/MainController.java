/**
 * @FileName  : MainController.java
 * @Project   : NightHawk
 * @Date      : 2012. 8. 11.
 * @작성자      : @author bluepoet

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.main.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.lang.StringUtils;
import org.gliderwiki.framework.util.StringUtil;
import org.gliderwiki.web.domain.FavorityType;
import org.gliderwiki.web.domain.WeSpace;
import org.gliderwiki.web.main.service.MainService;
import org.gliderwiki.web.space.service.SpaceService;
import org.gliderwiki.web.system.argumentresolver.LoginUser;
import org.gliderwiki.web.vo.MemberSessionVo;
import org.gliderwiki.web.vo.WikiTagVo;
import org.gliderwiki.web.vo.WikiVo;
import org.gliderwiki.web.wiki.common.service.CommonService;
import org.gliderwiki.web.wiki.keyword.service.TagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


/**
 * @author bluepoet
 *
 */

@Controller
public class MainController {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public static final String wikiRecentType = "recent";
	public static final String wikiAllType = "all";

	@Resource(name = "spaceService")
	private SpaceService spaceService;

	@Resource(name = "mainService")
	private MainService mainService;

	@Resource(name = "tagService")
	private TagService tagService;

	@Resource(name = "commonService")
	private CommonService commonService;
	

	@Autowired
    private BasicDataSource defaultDataSource;
	
	/**
	 * 인덱스 - 최초 접근시 인스톨 여부 판단하여 인덱스로 리턴한다. 
	 * @param loginUser
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "", "/index" }, method = RequestMethod.GET)
	public String home(@LoginUser MemberSessionVo loginUser, HttpServletRequest request, ModelMap model) {
		String dbUrl = "";
		String driverClassName = "";

		dbUrl = defaultDataSource.getUrl();
		driverClassName = defaultDataSource.getDriverClassName();
		Cookie[] cookies = request.getCookies();

		logger.debug("##cookies : " + cookies);
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				logger.debug("cookie : " + cookies[i].getName());
				if (StringUtils.equals("SPRING_SECURITY_REMEMBER_ME_COOKIE", cookies[i].getName())) {
					return "redirect:/dashboard";
				}
			}
		}

		if(dbUrl.startsWith("jdbc:mysql:") && driverClassName.equals("com.mysql.jdbc.Driver")) {
			// MySQL 인 경우 인스톨 된 상태이므로 인덱스 연결 시도함
			// URL : jdbc:mysql://localhost:3306/wiki
			// CLASS NAME : com.mysql.jdbc.Driver

			logger.debug("### MYSQL Connection ###");

			List<Map<String, String>> bestWikiList = mainService.getHomeWikiList("best");
			List<Map<String, String>> recentWikiList = mainService.getHomeWikiList("recent");



			int allWikiCnt = mainService.getWikiCount();
			int allTagCnt = mainService.getTagCount();
			int myWikiCnt = mainService.getMyWikiCount(loginUser.getWeUserIdx());
			int myPeopleCnt = mainService.getMyPeopleCount(loginUser.getWeUserIdx());
			int toMePeopleCnt = mainService.getToMePeopleCount(loginUser.getWeUserIdx());

			model.addAttribute("bestWikiList", bestWikiList);
			model.addAttribute("recentWikiList", recentWikiList);
			// 개설된 wiki수와 총 태그갯수
			model.addAttribute("allWikiCnt", allWikiCnt);
			model.addAttribute("allTagCnt", allTagCnt);
			model.addAttribute("myWikiCnt", myWikiCnt);
			model.addAttribute("myPeopleCnt", myPeopleCnt);
			model.addAttribute("toMePeopleCnt", toMePeopleCnt);
			model.addAttribute("visitCountInfo", mainService.getVisitCount(request));
		} else {
			// HSQL인 경우 아직 인스톨을 안했으므로 DB Skip
			// URL : jdbc:hsqldb:mem:spring-config
			// CLASS NAME : org.hsqldb.jdbcDriver

			logger.debug("$$$ HSQL Connection $$$");
			model.addAttribute("bestWikiList", null);
			model.addAttribute("recentWikiList", null);
			model.addAttribute("allWikiCnt", 0);
			model.addAttribute("allTagCnt", 0);
			model.addAttribute("myWikiCnt", 0);
			model.addAttribute("myPeopleCnt", 0);
			model.addAttribute("toMePeopleCnt", 0);
		}


		return "/index";
	}
	

	/**
	 * 검색 
	 * @param request
	 * @param response
	 * @param modelAndView
	 * @return
	 * @throws Throwable
	 */
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView searchResult(HttpServletRequest request, HttpServletResponse response, ModelAndView modelAndView) throws Throwable {
		String temp = StringUtil.strNull(request.getParameter("qry"));
		//String wiki_text = new String( temp.getBytes("ISO-8859-1"),"UTF-8");
		String wiki_text = temp;
		logger.debug("#wiki_text :" + wiki_text);
		
		List<WikiVo> wikiList = null;
		List<WeSpace> weSpaceList = null;
		
		if(!"".equals(wiki_text)) {
			wikiList = commonService.getWikiSearchList(wiki_text);
		}
		
		int wikiSize = 0;
		if(wikiList != null) {
			wikiSize = wikiList.size();
		}
		
		weSpaceList = tagService.createSpaceListWithTag(wikiList);
		
		modelAndView.addObject("wikiList", wikiList);
		modelAndView.addObject("wikiSize", wikiSize);
		modelAndView.addObject("weSpaceList", weSpaceList);
		modelAndView.addObject("wiki_text", wiki_text);
		modelAndView.setViewName("/searchWiki");
		return modelAndView;
	}
	
	
	/**
	 * 대시 보드 
	 * @param loginUser
	 * @param model
	 * @return
	 * @throws Throwable
	 */
	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public String main(@LoginUser MemberSessionVo loginUser, ModelMap model) throws Throwable {

		// 최근에 생성된 공간 가져오기
		List<Map<String, String>> recentSpaceList = spaceService.getRecentSpaceList(loginUser.getWeUserIdx(), 0, 5);

		// 최근 한달간의 Tag Cloud 목록을 조회한다.
		List<WikiTagVo> tagVoList = tagService.getTagList();

		// 내 활동내역 가져오기 -- 사용안함 
		//List<Map<String, String>> myActionList = mainService.getMyActionList(loginUser.getWeUserIdx());
		
		// 내가 속한 공간 및 전체 오픈공간의 공지사항 가져오기 
		List<Map<String, String>> myNotiList = mainService.getMyNotiList(loginUser.getWeUserIdx(), 0, 5);
		

		// 최근위키와 전체위키 가져오기
		List<Map<String, String>> recentWikiList = mainService.getWikiList(loginUser.getWeUserIdx(), wikiRecentType);
		List<Map<String, String>> allWikiList = mainService.getWikiList(loginUser.getWeUserIdx(), wikiAllType);

		// 전체 공간과 위키갯수 가져오기
		int allSpaceCount = mainService.getAllSpaceCount();
		int allWikiCount = mainService.getAllWikiCount();

		// 최근 업데이트 내역 가져오기
		List<Map<String, String>> updatedList = mainService.getUpdatedList(0, 5);

		// 랭킹 가져오기
		List<Map<String, String>> favorList = mainService.getFavorList();
		List<Map<String, String>> agreeList = mainService.getAgreeList();
		List<Map<String, String>> userPointList = mainService.getUserPointList();

		//model.addAttribute("myActionList", myActionList); // 내 활동 내역 사용안함 
		
		model.addAttribute("menuAttr", "default");		// 기본 메뉴 출력 
		model.addAttribute("recentSpaceList", recentSpaceList);
		model.addAttribute("myNotiList", myNotiList);
		model.addAttribute("tagVoList", tagVoList);
		model.addAttribute("recentWikiList", recentWikiList);
		model.addAttribute("allWikiList", allWikiList);
		model.addAttribute("allSpaceCount", allSpaceCount);
		model.addAttribute("allWikiCount", allWikiCount);
		model.addAttribute("updatedList", updatedList);
		model.addAttribute("favorList", favorList);
		model.addAttribute("agreeList", agreeList);
		model.addAttribute("userPointList", userPointList);

		return "/main/dashboard";
	}

	@RequestMapping(value = "/nodeTest", method = { RequestMethod.HEAD, RequestMethod.GET })
	public String nodeConnectionTest() {
		return "/main/nodeTest";
	}

	@RequestMapping(value = "/delFavorite", method = RequestMethod.POST)
	@ResponseBody
	public int delFavorite(@LoginUser MemberSessionVo loginUser,
			@RequestParam(value = "spaceIdx", required = false) Integer spaceIdx,
			@RequestParam(value = "wikiIdx", required = false) Integer wikiIdx,
			@RequestParam(value = "favoriteType") String type) throws Throwable {
		FavorityType favorType;
		int addIdx;

		if (StringUtils.equals(type, "SPACE")) {
			favorType = FavorityType.SPACE;
			addIdx = spaceIdx;
		} else {
			favorType = FavorityType.WIKI;
			addIdx = wikiIdx;
		}

		int result = commonService.delFavorite(loginUser.getWeUserIdx(), favorType, addIdx);

		return result;
	}
	
	/**
	 * 컨텐츠  더 보기 
	 * @param wikiType
	 * @param request
	 * @param response
	 * @param modelAndView
	 * @return
	 * @throws Throwable
	 */
	@RequestMapping(value="/moreitem/{type}", method = RequestMethod.GET)
	public ModelAndView moreItem(@PathVariable("type") String type, HttpServletRequest request, HttpServletResponse response, ModelAndView modelAndView) throws Throwable {
		Map<String, Object> param = new HashMap<String, Object>();
		
		Integer userIdx = Integer.parseInt(request.getParameter("userIdx"));
		Integer startRow = Integer.parseInt(request.getParameter("startRow"));
		Integer endRow = Integer.parseInt(request.getParameter("endRow"));
		List<Map<String, String>> recentList = null;
		if(type != null) {
			
			if(type.equals("recent") || type.equals("all")) {
				// 최근 위키 , 전체 위키 
				recentList = mainService.getMoreWikiList(userIdx, type, startRow, endRow);
			} else if(type.equals("space")) {
				// 최근 공간 
				recentList = spaceService.getRecentSpaceList(userIdx, startRow, endRow);
			} else if(type.equals("notice")) {
				// 공지사항 
				recentList = mainService.getMyNotiList(userIdx, startRow, endRow);
			} else if(type.equals("update")) {
				// 최근 업데이트
				recentList = mainService.getUpdatedList(startRow, endRow);
			} 
			
			if(recentList.size() == 0) {
				param.put("result", "0");
				param.put("recentList", null);
				
			} else {
				param.put("result", "1");
				param.put("recentList", recentList);
			}
		} else {
			param.put("result", "-1");
		}
				
		
		modelAndView.setViewName("/main/dashboard");
		return new ModelAndView("json_").addObject("param", param);
		
	}
	
}
