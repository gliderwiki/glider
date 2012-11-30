/**
 * @FileName  : PatchController.java
 * @Project   : NightHawk
 * @Date      : 2012. 8. 30. 
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 : GLiDER Wiki 패치 정보, 라이센스 정보, 확장기능관리 , 시스템 공지사항(URLConnection), 
 */
package org.gliderwiki.admin;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.gliderwiki.framework.entity.service.EntityService;
import org.gliderwiki.framework.util.StringUtil;
import org.gliderwiki.install.InstallPropertyUtil;
import org.gliderwiki.util.PropertyUtil;
import org.gliderwiki.web.domain.WeFunction;
import org.gliderwiki.web.domain.WePatch;
import org.gliderwiki.web.system.SystemConst;
import org.gliderwiki.web.vo.JsonResponse;
import org.gliderwiki.web.vo.PatchInfoVo;
import org.gliderwiki.web.vo.JsonResponse.ResponseStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;


/**
 * @author yion
 *
 */
@Controller
public class PatchController {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private EntityService entityService;
	

	@Autowired
    private RestTemplate restTemplate;
	
	
	public static final String REST_SERVER_URL = "http://www.gliderwiki.org";
	//public static final String REST_SERVER_URL = "http://localhost";
	
	
	/**
	 * 클라이언트의 패치정보를 조회한다. 글라이더 위키의 서버측과 통신하여 리스트를 조회해온다. 
	 * @param request
	 * @param response
	 * @param modelAndView
	 * @return
	 * @throws Throwable
	 */
	@RequestMapping(value="/admin/patch", method = RequestMethod.GET)
	public ModelAndView adminPatch(HttpServletRequest request, HttpServletResponse response, ModelAndView modelAndView) throws Throwable {
		logger.debug("### adminLogin ");
		
		String svcPath = request.getSession().getServletContext().getRealPath(SystemConst.PROPERTY_FULL_PATH + "version");
		boolean isServer = false;
		
		// 클라이언트 버전 정보를 가져온다 
		Properties props = PropertyUtil.getVersionPropertyInfo(svcPath, isServer);
		String clientVersion = props.getProperty("version.info");
		
		// 글라이더 서버에 전송하기 위한 기본 정보를 config.properties 에서 조회한다. 
		String configPath = request.getSession().getServletContext().getRealPath(SystemConst.PROPERTY_FULL_PATH + "config");
		Properties config = PropertyUtil.getPropertyInfo(configPath, SystemConst.CONFIG_NAME);
		
		String weDomain = config.getProperty("we.domain");
		String weEmail = config.getProperty("we.email");
		String activeKey = config.getProperty("we.active.key");
		String serverDomain = config.getProperty("server.domain");
		logger.debug("#weDomain : " + weDomain);
		logger.debug("#weEmail : " + weEmail);
		logger.debug("#activeKey : " + activeKey);
		
		PatchInfoVo vo = new PatchInfoVo();
		vo.setWeDomain(weDomain);
		vo.setWeEmail(weEmail);
		vo.setWeActiveKey(activeKey);
		vo.setWeVersionInfo(clientVersion);
		
		// RestTemplate 으로 GLiDER 서버에 연결하여 버전 정보를 리턴받는다. 
		HttpEntity<PatchInfoVo> entity = new HttpEntity<PatchInfoVo>(vo);
		String restUrl = REST_SERVER_URL + "/service/patchService";
		ResponseEntity<PatchInfoVo> entityResponse = restTemplate.postForEntity(restUrl, entity, PatchInfoVo.class);
		
		PatchInfoVo patch = entityResponse.getBody();
		logger.debug("###patch : " + patch.toString());
		
		// version 정보가 1.1.2 와 같은 형식이므로 rest 통신시 parameter로 넘어가지 않음
		// 따라서 - 로 replace 처리 함 
		String version = clientVersion.replaceAll("\\.", "-");  
		
		
		ResponseEntity<WeFunction[]> weFunctionList = null;
		if(!clientVersion.equals(patch.getWeServerVerionInfo())) {
			String listUrl = REST_SERVER_URL +"/service/patchList/" + version;
			logger.debug("###listUrl : " +listUrl);
			weFunctionList = restTemplate.getForEntity(listUrl, WeFunction[].class);
		}
		
		WeFunction[] list = null;
		if(weFunctionList != null) {
			list = weFunctionList.getBody();
		}
		
		
		modelAndView.addObject("menu", "4");
		modelAndView.addObject("serverVersion", patch.getWeServerVerionInfo());
		modelAndView.addObject("clientVersion", clientVersion);
		modelAndView.addObject("list", list);
		modelAndView.setViewName("admin/extension/patchMgr");
		return modelAndView; 
	}
	
	
	/**
	 * 패치를 다운받아 지정된 위치에 파일을 복사한다. 
	 * @param request
	 * @param response
	 * @return
	 * @throws Throwable
	 */
	@RequestMapping(value="/admin/getPatch", method = RequestMethod.POST)
	@ResponseBody
	public JsonResponse getPatch(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		JsonResponse res = new JsonResponse();
		
		String functionIdx = request.getParameter("idx");
		String callUrl = request.getParameter("url");
		
		logger.debug("#### functionIdx : " + functionIdx);
		logger.debug("#### callUrl : " + callUrl);
				
		// Call URL 로 RestClient 를 호출 한다. - 서버의 we_patch 정보를 조회한다
		WePatch restVo = new WePatch();
		restVo.setWe_function_idx(Integer.parseInt(functionIdx));
		
		HttpEntity<WePatch> entity = new HttpEntity<WePatch>(restVo);
		String restUrl = REST_SERVER_URL + callUrl;
		ResponseEntity<WePatch> entityResponse = restTemplate.postForEntity(restUrl, entity, WePatch.class);
		
		// 현재 클릭한 패치 버전의 WePatch 정보를 조회한다. 
		// 패치의 경로, 패치 파일명, 패치 타입, 패치 파일의 로컬 저장 경로를 조회한다. 
		WePatch patchVo = entityResponse.getBody();
		
			
		String filePath = patchVo.getWe_file_path();
		String fileName = patchVo.getWe_file_name();
		String localPath = patchVo.getWe_patch_path();
		String patchType = patchVo.getWe_patch_type();		// 패치 타입에 따라 수행되는 Biz Logic 이 다르다. 
		
		
		
		String fullPath = filePath + "/" + fileName;
	
		// 서버측 파일 전체 경로 
		String listUrl = REST_SERVER_URL+fullPath;
		
		HttpClient httpClient = new DefaultHttpClient() ;
		
		// 로컬 파일 경로 (jar일 경우 WEB-INF / lib 가 된다. 추후 클래스, jsp등도 지원 예정 
		ServletContext context = request.getServletContext();
		String destination = context.getRealPath(localPath) +"/" + fileName;
		
		logger.debug("##destination : " + destination);
		
		logger.debug("##listUrl : " + listUrl);
		
		// GliderWiki 의 패치 파일 경로를 HttpClient 로 연결하여 Stream으로 읽어온다. 
		HttpGet httpGet = new HttpGet(listUrl);
		
		HttpResponse httpResponse = httpClient.execute(httpGet);
		
		// httpEntity가  Spring 라이브러리와 겹치는 문제로 full import 한다. 
		org.apache.http.HttpEntity httpEntity = httpResponse.getEntity();
		
		// 지정된 경로에 쏴준다. 
		if(httpEntity != null) {
			
			try {
				FileOutputStream fos = new FileOutputStream(destination);
				httpEntity.writeTo(fos);
			    fos.close();
			    
			    res.setResult(fileName);
				res.setStatus(ResponseStatus.SUCCESS);
			} catch (Exception e) {
				res.setResult(fileName);
				res.setStatus(ResponseStatus.FAIL);
				e.printStackTrace();
			}
		}
		logger.debug("#### res : " + res.toString());
		return res;
	}
	
	
	/**
	 * 확장 기능을 다운 받아 지정된 위치에 복사한다. 
	 * @param request
	 * @param response
	 * @return
	 * @throws Throwable
	 */
	@RequestMapping(value="/admin/getExtention", method = RequestMethod.POST)
	@ResponseBody
	public JsonResponse getExtention(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		JsonResponse res = new JsonResponse();
		
		String functionIdx = request.getParameter("idx");
		String callUrl = request.getParameter("url");
		
		logger.debug("#### functionIdx : " + functionIdx);
		logger.debug("#### callUrl : " + callUrl);
		
		
		/*
		// Call URL 로 RestClient 를 호출 한다. - 서버의 we_patch 정보를 조회한다
		WePatch restVo = new WePatch();
		restVo.setWe_function_idx(Integer.parseInt(functionIdx));
		
		HttpEntity<WePatch> entity = new HttpEntity<WePatch>(restVo);
		String restUrl = REST_SERVER_URL + callUrl;
		ResponseEntity<WePatch> entityResponse = restTemplate.postForEntity(restUrl, entity, WePatch.class);
		
		// 현재 클릭한 패치 버전의 WePatch 정보를 조회한다. 
		// 패치의 경로, 패치 파일명, 패치 타입, 패치 파일의 로컬 저장 경로를 조회한다. 
		WePatch patchVo = entityResponse.getBody();
		
		
		String filePath = patchVo.getWe_file_path();		//글라이더 위키 서버 파일 경로 	
		String fileName = patchVo.getWe_file_name();		//글라이더 위키 서버 파일 이름 
		String localPath = patchVo.getWe_patch_path();		//설치 경로 - 클라이언트 서버의 지정 경로에 파일을 복사한다. 
		String patchType = patchVo.getWe_patch_type();		//패치 타입에 따라 수행되는 Biz Logic 이 다르다. 
		
		
		
		String fullPath = filePath + "/" + fileName;
		
		// 서버측 파일 전체 경로 
		String listUrl = REST_SERVER_URL+fullPath;
		
		HttpClient httpClient = new DefaultHttpClient() ;
		
		// 로컬 파일 경로 (jar일 경우 WEB-INF / lib 가 된다. 추후 클래스, jsp등도 지원 예정 
		ServletContext context = request.getServletContext();
		String destination = context.getRealPath(localPath) +"/" + fileName;
		
		logger.debug("##destination : " + destination);
		
		logger.debug("##listUrl : " + listUrl);
		
		// GliderWiki 의 패치 파일 경로를 HttpClient 로 연결하여 Stream으로 읽어온다. 
		HttpGet httpGet = new HttpGet(listUrl);
		
		HttpResponse httpResponse = httpClient.execute(httpGet);
		
		// httpEntity가  Spring 라이브러리와 겹치는 문제로 full import 한다. 
		org.apache.http.HttpEntity httpEntity = httpResponse.getEntity();
		
		// 지정된 경로에 쏴준다. 
		if(httpEntity != null) {
			
			try {
				FileOutputStream fos = new FileOutputStream(destination);
				httpEntity.writeTo(fos);
				fos.close();
				
				res.setResult(fileName);
				res.setStatus(ResponseStatus.SUCCESS);
			} catch (Exception e) {
				res.setResult(fileName);
				res.setStatus(ResponseStatus.FAIL);
				e.printStackTrace();
			}
		}
		
		*/
		String outPath = request.getSession().getServletContext().getRealPath("/WEB-INF/views/common/include/");
		String inPath = request.getSession().getServletContext().getRealPath("/resource/data/patch/v102/");
		

		String fileName = "default_header.jsp";
		
		File in = new File(inPath, fileName);
		File out = new File(outPath, fileName);
		
		
		logger.debug("### in : " + in.toString());
		logger.debug("### out : " + out.toString());
		
		try {
			FileCopyUtils.copy(in, out);

			res.setResult(fileName);
			res.setStatus(ResponseStatus.SUCCESS);
		} catch (Exception e) {

			res.setResult(fileName);
			res.setStatus(ResponseStatus.FAIL);
		}
		
		
		
		
		logger.debug("#### res : " + res.toString());
		return res;
	}
	
	
	
	
	@RequestMapping(value="/admin/license", method = { RequestMethod.POST, RequestMethod.GET } )
	public ModelAndView adminLicense(HttpServletRequest request, HttpServletResponse response, ModelAndView modelAndView) throws Throwable {
		String lisence_key = StringUtil.strNull(request.getParameter("lisence_key"));
		
		String configPath = request.getSession().getServletContext().getRealPath(SystemConst.PROPERTY_FULL_PATH + "config");
		Properties config = PropertyUtil.getPropertyInfo(configPath, SystemConst.CONFIG_NAME);
		
		String weDomain = config.getProperty("we.domain");
		String weEmail = config.getProperty("we.email");
		String activeKey = lisence_key; //config.getProperty("we.active.key");
		String serverDomain = config.getProperty("server.domain");
		logger.debug("#weDomain : " + weDomain);
		logger.debug("#weEmail : " + weEmail);
		logger.debug("#activeKey : " + activeKey);
		
		
		if(lisence_key != null && !"".equals(lisence_key)) {
			InstallPropertyUtil property = new InstallPropertyUtil();
			
			// config 프로퍼티를 생성한다. 
			property.CreateCionfigProperties(weEmail, lisence_key, weDomain, configPath);		
			
		} else {
			activeKey = config.getProperty("we.active.key");
		}
		
		logger.debug("### activeKey: " + activeKey);
		modelAndView.addObject("menu", "4");
		modelAndView.addObject("activeKey", activeKey);
		modelAndView.setViewName("admin/extension/licenseMgr");
		return modelAndView; 
	}
	
	
	@RequestMapping(value="/admin/extension", method = RequestMethod.GET)
	public ModelAndView adminExtension(HttpServletRequest request, HttpServletResponse response, ModelAndView modelAndView) throws Throwable {
		logger.debug("### adminExtension ");
		
		String svcPath = request.getSession().getServletContext().getRealPath(SystemConst.PROPERTY_FULL_PATH + "version");
		boolean isServer = false;
		
		// 클라이언트 버전 정보를 가져온다 
		Properties props = PropertyUtil.getVersionPropertyInfo(svcPath, isServer);
		String clientVersion = props.getProperty("version.info");
		
		// 글라이더 서버에 전송하기 위한 기본 정보를 config.properties 에서 조회한다. 
		String configPath = request.getSession().getServletContext().getRealPath(SystemConst.PROPERTY_FULL_PATH + "config");
		Properties config = PropertyUtil.getPropertyInfo(configPath, SystemConst.CONFIG_NAME);
		
		String weDomain = config.getProperty("we.domain");
		String weEmail = config.getProperty("we.email");
		String activeKey = config.getProperty("we.active.key");
		String serverDomain = config.getProperty("server.domain");
		logger.debug("#weDomain : " + weDomain);
		logger.debug("#weEmail : " + weEmail);
		logger.debug("#activeKey : " + activeKey);
		
		PatchInfoVo vo = new PatchInfoVo();
		vo.setWeDomain(weDomain);
		vo.setWeEmail(weEmail);
		vo.setWeActiveKey(activeKey);
		vo.setWeVersionInfo(clientVersion);
		
		// RestTemplate 으로 GLiDER 서버에 연결하여 버전 정보를 리턴받는다. 
		HttpEntity<PatchInfoVo> entity = new HttpEntity<PatchInfoVo>(vo);
		String restUrl = REST_SERVER_URL + "/service/patchService";
		ResponseEntity<PatchInfoVo> entityResponse = restTemplate.postForEntity(restUrl, entity, PatchInfoVo.class);
		
		PatchInfoVo patch = entityResponse.getBody();
		logger.debug("###patch : " + patch.toString());
		
		// version 정보가 1.1.2 와 같은 형식이므로 rest 통신시 parameter로 넘어가지 않음
		// 따라서 - 로 replace 처리 함 
		String version = clientVersion.replaceAll("\\.", "-");  
		
		
		ResponseEntity<WeFunction[]> weFunctionList = null;
		if(!clientVersion.equals(patch.getWeServerVerionInfo())) {
			String listUrl = REST_SERVER_URL +"/service/extentionList/" + version;
			logger.debug("###listUrl : " +listUrl);
			weFunctionList = restTemplate.getForEntity(listUrl, WeFunction[].class);
		}
		
		WeFunction[] list = null;
		if(weFunctionList != null) {
			list = weFunctionList.getBody();
		}
		
		
		modelAndView.addObject("menu", "4");
		modelAndView.addObject("serverVersion", patch.getWeServerVerionInfo());
		modelAndView.addObject("clientVersion", clientVersion);
		modelAndView.addObject("list", list);
		
		modelAndView.addObject("menu", "4");
		modelAndView.setViewName("admin/extension/extensionMgr");
		return modelAndView; 
	}

}
