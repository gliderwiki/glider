/**
 * @FileName  : DownloadController.java
 * @Project   : glider
 * @Date      : 2013. 2. 25. 
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.download.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.gliderwiki.web.common.service.EntityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
		logger.debug("### 다운로드 폼 ");
		modelAndView.addObject("version", version);
		modelAndView.setViewName("download/download");
		return modelAndView;
	}
}
