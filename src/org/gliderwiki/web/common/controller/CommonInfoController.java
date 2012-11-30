/**
 * @FileName  : CommonInfoController.java
 * @Project   : NightHawk
 * @Date      : 2012. 6. 28. 
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 : 공통정보를 호출하기 위한 controller 이다.
 *                include 되는 메뉴(헤더, 푸더) 등에서 호출한다.  
 */
package org.gliderwiki.web.common.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.gliderwiki.framework.entity.service.EntityService;
import org.gliderwiki.framework.util.StringUtil;
import org.gliderwiki.util.RequestManager;
import org.gliderwiki.util.SessionUtil;
import org.gliderwiki.web.common.DownLoadAction;
import org.gliderwiki.web.common.service.CommonInfoService;
import org.gliderwiki.web.domain.WeMenu;
import org.gliderwiki.web.system.SystemConst;
import org.gliderwiki.web.vo.JsonResponse;
import org.gliderwiki.web.vo.MemberSessionVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


/**
 * @author yion
 *
 */
@Controller
public class CommonInfoController {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private EntityService entityService;
	
	@Autowired
	private CommonInfoService commonInfoService;

	@RequestMapping(value="/menu/{type}", method = RequestMethod.GET)
	public ModelAndView menuList(@PathVariable("type") String type, HttpServletRequest request, HttpServletResponse response, ModelAndView modelAndView) throws Throwable {		
		String weUserIdx = StringUtil.strNull(request.getParameter("weUserIdx"));
		logger.debug("### 메뉴 목록조회 type : " + type);	
		logger.debug("### 메뉴 목록조회 weUserIdx : " + weUserIdx);		

		// 깊이가 1인 기본 메뉴를 불러온다. 
		WeMenu menuEntity = new WeMenu();
		menuEntity.setWe_use_yn("Y");
		menuEntity.setWe_menu_depth(1);
		menuEntity.setWe_menu_type(type);
 
					
		List<WeMenu> menuList = entityService.getListEntityOrdered(menuEntity, "we_menu_order_idx");
 		
		logger.debug("menuList : " + menuList.toString());

		
		modelAndView.addObject("type" , type);
		
		String viewPath = "common/include/MainHeader";
		
		if(type.equals("M")) {	// 메인 메뉴 (공간생성하기, 공간정보, 위키관리, 내정보)
			modelAndView.addObject("mainList" , menuList);
			viewPath = "common/include/MainHeader";
		} 
		
//		if (type.equals("T") && !"".equals(weMenuGroup)) {		// 타이틀 메뉴 
//			logger.debug("##############titleList");
//			modelAndView.addObject("titleList" , menuList);
//			viewPath = "common/include/TitleHeader";
//		} 
		
//		if (type.equals("S")) {		// 시스템 메뉴 (글라이드 소개 등)
//			modelAndView.addObject("systemList" , menuList);
//			viewPath = "common/include/SysteHeader";
//		}
		
		modelAndView.setViewName(viewPath);
		return modelAndView;
	}
	
	
	
	@RequestMapping(value="/menu/sub/{type}", method = RequestMethod.GET)
	public ModelAndView menuSubList(@PathVariable("type") String type, HttpServletRequest request, HttpServletResponse response, ModelAndView modelAndView) throws Throwable {		
		Integer weUserIdx = Integer.parseInt(StringUtil.strNullToSpace(request.getParameter("weUserIdx"), "0"));
		String weMenuGroup = StringUtil.strNullToSpace(request.getParameter("weMenuGroup"), "");
		
		String url = StringUtil.getURL(request);

		if(weMenuGroup.equals("")) {
			if(url.startsWith("/space")) {		
				logger.debug("메뉴URL SPACE");
				weMenuGroup = "SPACE";
			} else if(url.startsWith("/wiki")) {
				logger.debug("메뉴URL WIKI");
				weMenuGroup = "WIKI";
			} else if(url.startsWith("/user")) {
				logger.debug("메뉴URL USER");
				weMenuGroup = "USER";
			}
		}
		
		
		logger.debug("### 서브메뉴 목록조회 type : " + type);	
		logger.debug("### 서브메뉴 목록조회 weUserIdx : " + weUserIdx);	
		logger.debug("### 서브메뉴 목록조회 weMenuGroup : " + weMenuGroup);	
		logger.debug("### url : " + url);
		
		
		WeMenu menuEntity = new WeMenu();
		menuEntity.setWe_use_yn("Y");
		menuEntity.setWe_menu_depth(1);
		menuEntity.setWe_menu_type(type);
		menuEntity.setWe_menu_group(weMenuGroup);		// SPACE 혹은 WIKI 일때만 출력 
		
		List<WeMenu> titleList = null;
		try {
			titleList = commonInfoService.getTitleMenuByAuth(menuEntity,  weUserIdx);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		logger.debug("titleList : " + titleList.toString());
		
		
		modelAndView.addObject("type" , type);
		
		
		String viewPath = "common/include/TitleHeader";
		modelAndView.addObject("weMenuGroup" , weMenuGroup);
		modelAndView.addObject("titleList" , titleList);	
		modelAndView.setViewName(viewPath);
		return modelAndView;
	}
	
	
	
	@RequestMapping(value="/menu/headerMenu", method = RequestMethod.GET)
	public ModelAndView getHeaderMenuListAjax(HttpServletRequest request, HttpServletResponse response, ModelAndView modelAndView) throws Throwable {
		logger.debug("### getHeaderMenuList ");
		Integer weMenuIdx = Integer.parseInt(StringUtil.strNull(request.getParameter("weMenuIdx")));
		String subMenuId = StringUtil.strNull(request.getParameter("menuId"));
		Integer weUserIdx = Integer.parseInt(StringUtil.strNullToSpace(request.getParameter("weUserIdx"), "0"));
		
		logger.debug("### weMenuIdx : " + weMenuIdx);
		logger.debug("### subMenuId : " + subMenuId);
		logger.debug("### weUserIdx : " + weUserIdx);
		
		
		Map<String, String> param = new HashMap<String, String>();
		
		
		List<WeMenu> menuList = null;
		try {
			menuList = commonInfoService.getHeaderMenuListAjax(weMenuIdx,  weUserIdx);
			
			JSONArray jsonArray  = JSONArray.fromObject(menuList);
			
			param.put("result", "SUCCESS");
			param.put("status", SystemConst.CALL_SUCCESS);
			param.put("subMenuId", subMenuId);
			param.put("menuList", jsonArray.toString());
			
		} catch (Exception e) {
			param.put("result", "FAIL");
			param.put("status", SystemConst.CALL_FAIL);
			param.put("subMenuId", subMenuId);
			param.put("menuList", null);
			e.printStackTrace();
		}
		
		logger.debug("### menuList : " + menuList.toString());
		logger.debug("### param : " + param.toString());
		
		return new ModelAndView("json_").addObject("param", param);
	}
	
	@RequestMapping("/common/download")
	public ModelAndView fileDownload(HttpServletRequest request, HttpServletResponse response, ModelAndView modelAndView)  throws Throwable {
		String realPath = request.getSession().getServletContext().getRealPath("/");
		
		logger.debug("#realPath : " + realPath);
		
		String fileName = StringUtil.strNull(request.getParameter("filaName"));
		
		logger.debug("*************** 파일 다운로드 합니다 *******************");
		
		//다운로드 기본 경로 
		String filePath = realPath+SystemConst.SYSTEM_FILE_DOWNLOAD_PATH;
		
		
		//다운로드 할 파일 정보 취득

		//절대 경로 구하기
		HttpSession session = request.getSession();
		String doc_root = filePath + fileName;
		
		String type = session.getServletContext().getMimeType(doc_root);
				
		DownLoadAction download = new DownLoadAction();
		
		// 인자값 (절대경로, 파일이름, response, type)
		download.getFileDownload(doc_root, fileName, response, type);	
        
		return null;
    	
	}
	
}
