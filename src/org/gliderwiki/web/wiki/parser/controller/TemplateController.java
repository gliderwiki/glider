/**
 * @FileName  : TemplateController.java
 * @Project   : NightHawk
 * @Date      : 2012. 10. 26. 
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.wiki.parser.controller;

import java.io.FileOutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.gliderwiki.framework.entity.service.EntityService;
import org.gliderwiki.framework.exception.DBHandleException;
import org.gliderwiki.framework.util.StringUtil;
import org.gliderwiki.util.GliderTagParser;
import org.gliderwiki.web.domain.WePoint;
import org.gliderwiki.web.domain.WeSpace;
import org.gliderwiki.web.domain.WeTemplate;
import org.gliderwiki.web.domain.WeUser;
import org.gliderwiki.web.domain.WeWiki;
import org.gliderwiki.web.system.SystemConst;
import org.gliderwiki.web.system.argumentresolver.LoginUser;
import org.gliderwiki.web.vo.JsonResponse;
import org.gliderwiki.web.vo.MemberSessionVo;
import org.gliderwiki.web.vo.JsonResponse.ResponseStatus;
import org.gliderwiki.web.wiki.common.service.CommonService;
import org.gliderwiki.web.wiki.parser.service.WikiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
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
public class TemplateController {

	
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private WikiService wikiService;

	
	@SuppressWarnings("rawtypes")
	@Autowired
	private EntityService entityService;

	
	@Autowired
	private CommonService commonService;
	
	
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
	@RequestMapping(value = "/template/new", method = RequestMethod.GET)
	public String newForm(@LoginUser MemberSessionVo loginUser, @ModelAttribute("wikiForm") WeWiki wikiForm, Model model) throws Throwable {
		

		// 템플릿 정보 조회
		WeTemplate temp = new WeTemplate();
		temp.setWe_use_yn("Y");

		List<WeTemplate> template = wikiService.getWeTemplateList(temp);


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

		model.addAttribute("editMode", "template");
		model.addAttribute("template", jsonTemplate);

		return "/wiki/template/tempform";
	}
	
	
	/**
	 * 템플릿을 저장한다. 
	 * @param loginUser
	 * @param weTag
	 * @param wikiForm
	 * @param request
	 * @return
	 * @throws Throwable
	 */
	@ResponseBody
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/template/new/save", method = RequestMethod.POST)
	public JsonResponse save(@LoginUser MemberSessionVo loginUser, @ModelAttribute WeWiki wikiForm, HttpServletRequest request) throws Throwable {
		JsonResponse res = new JsonResponse();
		JsonResponse.ResultStatusInfo result = new JsonResponse.ResultStatusInfo();
		
		
		GliderTagParser gtp = new GliderTagParser();

		logger.debug("####### getWe_wiki_text() : {}", wikiForm.getWe_wiki_text());

		Map<String, Object> resultMap = null;
		try {
			resultMap = gtp.parserMap(wikiForm.getWe_wiki_text());
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
		WeTemplate domain = new WeTemplate();
		
		domain.setWe_ins_date(new Date());
		domain.setWe_ins_user(loginUser.getWeUserIdx());
		domain.setWe_template_name(wikiForm.getWe_wiki_title());
		domain.setWe_template_type("MARKUP");
		domain.setWe_template_text(wikiForm.getWe_wiki_text());
		domain.setWe_template_markup(resultMap.get("htmltag").toString().replaceAll("\r\n", "\r\n<br class=\"br\"/>\r\n"));
		domain.setWe_use_yn("Y");
		
		logger.debug("#####################################");
		logger.debug("htmltag : {}", resultMap.get("htmltag").toString());
		logger.debug("#####################################");
		
		try {
			entityService.insertEntity(domain);
			
			result.setMessage("템플릿이 저장되었습니다.");
			result.setRedirectUrl("/template/list");
			res.setStatus(ResponseStatus.SUCCESS);

		} catch (DBHandleException e) {
			// TODOLIST Exception 발생시 에러로그 저장

			logger.debug("### DBHandleException : " + e.getCause());
			logger.debug("### DBHandleException : " + e.getMessage());
			logger.debug("### DBHandleException : " + e.getStatus());

			result.setErrorMsg(e.getMessage());
			result.setMessage("에러가 발생 하였습니다.");
			res.setStatus(ResponseStatus.FAIL);
		}

		res.setResponse(result);

		return res;
	}
	
	
	
	/**
	 * 템플릿 조회
	 * @param loginUser
	 * @param weTemplateIdx
	 * @param model
	 * @return
	 * @throws Throwable
	 */
	@RequestMapping(value = "/template/view/{weTemplateIdx}", method = RequestMethod.GET)
	public String view(@LoginUser MemberSessionVo loginUser, @PathVariable("weTemplateIdx") Integer weTemplateIdx, ModelMap model) throws Throwable {

		logger.debug("###loginUser : " + loginUser.toString());

		WeTemplate template = commonService.getTemplateByIdx(weTemplateIdx);

		

		//회원 닉네임검색
		WeUser weuser = new WeUser();
		weuser.setWe_user_idx(template.getWe_ins_user());
		weuser = (WeUser) entityService.getRowEntity(weuser);

		
		model.addAttribute("userNick", weuser.getWe_user_nick());
		model.addAttribute("template", template );

		return "/wiki/template/view";

	}
	

	/**
	 * 템플릿 리스트 조회 
	 * @param request
	 * @param response
	 * @param modelAndView
	 * @return
	 * @throws Throwable
	 */
	@RequestMapping(value = "/template/list", method = RequestMethod.GET)
	public ModelAndView templateList(HttpServletRequest request, HttpServletResponse response, ModelAndView modelAndView) throws Throwable {
		// 템플릿 정보 조회
		WeTemplate temp = new WeTemplate();
		temp.setWe_use_yn("Y");
		
		List<WeTemplate> tempList = (List<WeTemplate>) wikiService.getWeTemplateList(temp);
			
				
		modelAndView.addObject("tempList", tempList); 
		modelAndView.addObject("templateSize", tempList.size());
		modelAndView.setViewName("/wiki/template/list");
		
		return modelAndView;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/template/delete/{weTemplateIdx}", method = RequestMethod.GET)
	@ResponseBody
	public JsonResponse deleteTemplate(@LoginUser MemberSessionVo loginUser, @PathVariable("weTemplateIdx") Integer weTemplateIdx, Model model) throws Throwable {
		
		// 템플릿 정보 조회
		WeTemplate temp = new WeTemplate();
		temp.setWe_use_yn("Y");
		temp.setWe_template_idx(weTemplateIdx);
		
		WeTemplate domain = (WeTemplate) entityService.getRowEntity(temp);
		domain.setWe_use_yn("N");

		JsonResponse res = new JsonResponse();
		
		
		try {
			entityService.updateEntity(domain);
			res.setResult(1);
		} catch (Exception e) {
			res.setResult(-1);
			e.printStackTrace();
		}
		
		return res;
	}
	
	/**
	 * 위키 페이지 작성시 템플릿을 불러온다. 
	 * @param weTemplateIdx
	 * @param request
	 * @param response
	 * @param modelAndView
	 * @return
	 * @throws Throwable
	 */
	@RequestMapping(value = "/template/call/{weTemplateIdx}", method = RequestMethod.GET)
	@ResponseBody
	public JsonResponse callTemplate(@PathVariable("weTemplateIdx") Integer weTemplateIdx 
			                       , HttpServletRequest request, HttpServletResponse response, ModelAndView modelAndView) throws Throwable {
		JsonResponse res = new JsonResponse();
		
		WeTemplate domain = new WeTemplate();
		domain.setWe_template_idx(weTemplateIdx);
		domain.setWe_use_yn("Y");
		
		WeTemplate rtnObj = (WeTemplate) entityService.getRowEntity(domain);

		try {
					    
		    res.setResult(rtnObj.getWe_template_text());
			res.setStatus(ResponseStatus.SUCCESS);
		} catch (Exception e) {
			res.setResult("");
			res.setStatus(ResponseStatus.FAIL);
			e.printStackTrace();
		}
		
		logger.debug("#### res : " + res.getResult().toString());
		
			
		return res;
		
	}

}
