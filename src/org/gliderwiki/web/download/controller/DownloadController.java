/**
 * @FileName  : DownloadController.java
 * @Project   : glider
 * @Date      : 2013. 2. 25. 
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.download.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.gliderwiki.framework.exception.GliderwikiException;
import org.gliderwiki.framework.util.GliderFileUtils;
import org.gliderwiki.framework.util.StringUtil;
import org.gliderwiki.util.PropertyUtil;
import org.gliderwiki.web.common.service.EntityService;
import org.gliderwiki.web.domain.AttachmentType;
import org.gliderwiki.web.domain.WeInstallUser;
import org.gliderwiki.web.domain.WeUser;
import org.gliderwiki.web.download.service.DownloadService;
import org.gliderwiki.web.system.SystemConst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author yion
 *
 */
@Controller
public class DownloadController {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@SuppressWarnings("rawtypes")
	@Autowired
	private EntityService entityService;
	
	@Autowired
	private DownloadService downloadService;
	/**
	 * 글라이더 위키 다운로드 페이지 
	 * @param request
	 * @param response
	 * @param modelAndView
	 * @return
	 * @throws Throwable
	 */
	@RequestMapping(value="/download/{version}", method = RequestMethod.GET)
	public ModelAndView downloadView(@PathVariable("version") String version,  ModelAndView modelAndView) throws Throwable {
		// 고객 
		logger.debug("### 다운로드 폼 ");
		//Active Key를 생성 한다. 
		String activeKey = StringUtil.stringBuffersChars(128);		// 128

		logger.debug("activeKey {} " , activeKey);
		
		modelAndView.addObject("version", version);
		modelAndView.addObject("activeKey", activeKey);
		modelAndView.setViewName("download/download");
		return modelAndView;
	}
	
	@RequestMapping(method=RequestMethod.POST, value = "/downloadWiki")
	public void wikiDownLoad(@ModelAttribute("installForm") WeInstallUser weInstallUser, BindingResult result,
			HttpServletRequest request, HttpServletResponse response) throws Throwable {
		logger.debug("### weInstallUser :" + weInstallUser.toString());
		
		int rtnResult = 0;
		//TODO List 테이블에 저장 
		if(weInstallUser.getWe_email() == null || "".equals(weInstallUser.getWe_email())
				|| weInstallUser.getWe_active_key() == null || "".equals(weInstallUser.getWe_active_key())) {
			throw new GliderwikiException("올바른 접근이 아닙니다.");
		}
		//TODO List DB에서 현재 파일 명 조회 
		String fileName = "downloadPack-0226.zip";
		
		try {
			// 인증키 전송 
			rtnResult = downloadService.sendActiveKey(weInstallUser.getWe_email(), weInstallUser.getWe_active_key(), request);
			
			if(rtnResult > 0){ 
				logger.debug("## Install User 데이터 저장 ");
				weInstallUser.setWe_install_date(new Date());
				entityService.insertEntity(weInstallUser);
				GliderFileUtils.downloadFile(request, response,
						new File(request.getSession().getServletContext().getRealPath("/resource/data/gliderwiki/" + fileName)),
						AttachmentType.DOWNLOAD);
			}
			
			
		} catch (Exception e) {
			logger.debug("###Wiki Download Result : " + rtnResult);
			e.printStackTrace();
		}
	}
	
}
