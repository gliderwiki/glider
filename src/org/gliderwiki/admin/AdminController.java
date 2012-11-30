/**
 * @FileName  : AdminController.java
 * @Project   : NightHawk
 * @Date      : 2012. 8. 29. 
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.admin;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.gliderwiki.admin.service.AdminGroupService;
import org.gliderwiki.admin.service.AdminSpaceService;
import org.gliderwiki.admin.service.AdminUserService;
import org.gliderwiki.admin.service.AdminWikiService;
import org.gliderwiki.framework.entity.service.EntityService;
import org.gliderwiki.framework.util.DateUtil;
import org.gliderwiki.util.SessionUtil;
import org.gliderwiki.web.domain.WeCategory;
import org.gliderwiki.web.domain.WeGroupInfo;
import org.gliderwiki.web.domain.WeMenu;
import org.gliderwiki.web.domain.WeProfile;
import org.gliderwiki.web.domain.WeSpace;
import org.gliderwiki.web.domain.WeUser;
import org.gliderwiki.web.domain.WeWiki;
import org.gliderwiki.web.domain.WeWikiTag;
import org.gliderwiki.web.main.service.MainService;
import org.gliderwiki.web.system.SystemConst;
import org.gliderwiki.web.vo.MailSendUserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.IOException;



/**
 * @author yion
 *
 */
@Controller
public class AdminController {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private EntityService entityService;
	
	@Autowired
	private AdminUserService adminUserService;
	
	@Autowired
	private AdminSpaceService adminSpaceService;
	
	@Autowired
	private AdminWikiService adminWikiService;
	
	@Resource(name = "mainService")
	private MainService mainService;
	
	
	/**
	 * 어드민 로그인 
	 * @param request
	 * @param response
	 * @param modelAndView
	 * @return
	 * @throws Throwable
	 */
	@RequestMapping(value="/admin/wikiadminlogin", method = RequestMethod.GET)
	public ModelAndView adminLogin(HttpServletRequest request, HttpServletResponse response, ModelAndView modelAndView) throws Throwable {
		logger.debug("### adminLogin ");
		modelAndView.setViewName("admin/adminLogin");
		return modelAndView; 
	}
	
	/**
	 * 메인 화면 
	 * @param request
	 * @param response
	 * @param modelAndView
	 * @return
	 * @throws Throwable
	 */
	@RequestMapping(value="/admin/index", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response, ModelAndView modelAndView) throws Throwable {
		logger.debug("### index ");
			
		
		
		WeProfile domain = new WeProfile(null, null);
		domain.setWe_away_yn("N");
				
		// 총 회원수 
		int intTotalUser = 0;
		intTotalUser = entityService.getCountEntity(domain);
		
		
		WeSpace space = new WeSpace();
		space.setWe_use_yn("Y");
		
		int intTotalSpace = 0;
		intTotalSpace = entityService.getCountEntity(space);
		
		
		WeWiki wiki = new WeWiki();
		wiki.setWe_use_yn("Y");
		
		int intTotalWiki = 0;
		intTotalWiki = entityService.getCountEntity(wiki);
		
		
		WeWikiTag tag = new WeWikiTag();
		tag.setWe_use_yn("Y"); 
		
		int intTotalTag = 0;
		
		intTotalTag = entityService.getCountEntity(tag);
		
		
		WeGroupInfo group = new WeGroupInfo();
		group.setWe_use_yn("Y");
		
		int intTotalGroup = 0;
		
		intTotalGroup = entityService.getCountEntity(group);
		
		
		// 최근 1개월(-30일) 의 공간 목록을 조회한다.  
		Date month = DateUtil.getOffsetDateToDate(DateUtil.getToday(), SystemConst.SEACH_MONTH);
		
		List<WeSpace> spaceList = adminSpaceService.getSpaceListMonth(space, month);
		
		List<WeWiki> wikiList = adminWikiService.getWikiListMonth(month);

		int spaceSize = 0;
		int wikiSize = 0;
		if(spaceList != null) {
			spaceSize = spaceList.size();
		}
		if(wikiList != null) {
			wikiSize = wikiList.size();
		}
		
		
		modelAndView.addObject("spaceList" , spaceList);
		modelAndView.addObject("wikiList" , wikiList);
		modelAndView.addObject("spaceSize" , spaceSize);
		modelAndView.addObject("wikiSize" , wikiSize);
		modelAndView.addObject("visitCountInfo", mainService.getVisitCount(request));
		
		modelAndView.addObject("intTotalUser", intTotalUser);
		modelAndView.addObject("intTotalSpace", intTotalSpace);
		modelAndView.addObject("intTotalTag", intTotalTag);
		modelAndView.addObject("intTotalGroup", intTotalGroup);
		modelAndView.addObject("intTotalWiki", intTotalWiki);
		modelAndView.addObject("menu", "1");
		modelAndView.setViewName("admin/adminIndex");
		return modelAndView; 
	}
	
		
	
}

