/**
 * @FileName  : PatchServiceRest.java
 * @Project   : NightHawk
 * @Date      : 2012. 10. 18. 
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.rest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.gliderwiki.rest.service.PatchService;
import org.gliderwiki.util.PropertyUtil;
import org.gliderwiki.web.common.service.EntityService;
import org.gliderwiki.web.domain.WeFunction;
import org.gliderwiki.web.domain.WeInstallUser;
import org.gliderwiki.web.domain.WePatch;
import org.gliderwiki.web.system.SystemConst;
import org.gliderwiki.web.vo.PatchInfoVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author yion
 *
 */
@Controller
public class PatchServiceRest {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@SuppressWarnings("rawtypes")
	@Autowired
	private EntityService entityService;
	
	@Autowired
	private PatchService patchService;
	
	/**
	 * 서버의 버전 정보를 내려준다. 
	 * @param e
	 * @param request
	 * @param response
	 * @return
	 * @throws Throwable
	 */
	@RequestMapping(method=RequestMethod.POST, value="/patchService")
	public @ResponseBody PatchInfoVo patchService(@RequestBody PatchInfoVo patchInfoModel, HttpServletRequest request, HttpServletResponse response) throws Throwable {
		logger.debug("## Rest Call :  " + patchInfoModel.toString());
		//TODOLIST 사용자 정보 인증
		// 사용자 도메인
		// 사용자 이메일
		// 인증키를 받아 회원 DB에 등록되어 있는지 검증한다. 
		
		String serverVersion = PropertyUtil.getVersionProps(request, true);
		
		logger.debug("##serverVersion : " + serverVersion);
		patchInfoModel.setWeServerVerionInfo(serverVersion);
		
		return patchInfoModel;
	}
	
	/**
	 * 버전별 패치 대상 목록을 내려준다. 
	 * @param version
	 * @return
	 * @throws Throwable
	 */
	@RequestMapping(method=RequestMethod.GET, value="/patchList/{version}")
	public @ResponseBody WeFunction[] patchList(@PathVariable("version") String version) throws Throwable {
		logger.debug("##### version : " + version);
		String clientVersion = version.replaceAll("-", ".");
		WeFunction[] arrayFunctionList = null;
		// 버전이 다를 경우 WE_FUNCTION 에서 데이터 가져옴  Y = 확장 기능파일 , N = 패치파일  
		arrayFunctionList = patchService.getCurrentFunctionList(clientVersion, "N");
		
		/*
		 * 
		INSERT INTO `wiki2`.`we_function` 
			(
			`WE_FUNCTION_NAME`, 
			`WE_FUNCTION_DESC`, 
			`WE_FUNCTION_CODE`, 
			`WE_FUNCTION_VER`, 
			`WE_FUNCTION_TYPE`, 
			`WE_USE_YN`, 
			`WE_EXTEND_YN`, 
			`WE_CALL_URL`, 
			`WE_INS_DATE`, 
			`WE_INS_USER`
			)
			VALUES
			(
			'프레임웍 버그 패치', 
			'framework20130302.jar 버그 패치 버전입니다.', 
			'v01', 
			'1.0.1', 
			'LIB', 
			'Y', 
			'N', 
			'/service/', 
			NOW(), 
			1
			);

		 * 
		 * 
		 */
		return arrayFunctionList;
	}
	
	/**
	 * 확장 기능별 패치 대상 목록을 내려준다 
	 * @param version
	 * @return
	 * @throws Throwable
	 */
	@RequestMapping(method=RequestMethod.GET, value="/extentionList/{version}")
	public @ResponseBody WeFunction[] extentionList(@PathVariable("version") String version) throws Throwable {
		logger.debug("##### version : " + version);
		String clientVersion = version.replaceAll("-", ".");
		WeFunction[] arrayFunctionList = null;
		// 버전이 다를 경우 WE_FUNCTION 에서 데이터 가져옴  Y = 확장 기능파일 , N = 패치파일  
		arrayFunctionList = patchService.getCurrentFunctionList(clientVersion, "Y");
		
		return arrayFunctionList;
	}
	
	
	/**
	 * 원격지 클라이언트 브라우저에서 다운로드 클릭시 파일을 다운로드 형태로 넘겨줌 - 추후 사용함 (실시간 패치가 아니고 다운로드형태임)
	 * 글라이더 위키 다운로드시 이용할 예정임
	 * @param functionIdx
	 * @param request
	 * @param response
	 * @throws Throwable
	 */
	@RequestMapping(method=RequestMethod.POST, value = "/patchList/browser/{functionIdx}")
	public void browserDownload(@PathVariable("functionIdx") String functionIdx, HttpServletRequest request, HttpServletResponse response) throws Throwable {
		logger.debug("###browserDownload !!");
		logger.debug("###functionIdx : " + functionIdx);
	
		//html 에서 아래와 같이 호출 함
		//var funcFileDownload = function(url){
		//$('#patchDownloadForm').attr('method', 'post');
		//$('#patchDownloadForm').attr('action', '/patchList/download/2');
		//$('#patchDownloadForm').submit();
		// }
	
	
		String filePath = request.getSession().getServletContext().getRealPath("/resource/data/patch/v101");
		String fileName = "framework-20130302.jar";
		String fullPath = filePath + "/" + fileName;
		logger.debug("##fullPath : " + fullPath);
	    
	    File file = new File(fullPath);
	    response.setContentLength((int)file.length());
	    response.setHeader("Content-Disposition", "attachment; fileName=\""+file.getName()+"\";");
	    response.setHeader("Content-Transfer-Encoding", "binary");
	    ServletOutputStream out = response.getOutputStream();
	
	    FileInputStream fls = null;
	
	    try {
	        fls = new FileInputStream(file);
	        FileCopyUtils.copy(fls, out);
	
	    } finally {
	        if(fls != null) {
	            try {
	                fls.close();
	            } catch(IOException ex) {
	
	            }
	        }
	    }
	    out.flush();
	}
	
	
	@RequestMapping(method=RequestMethod.GET, value = "/patchList/download/{functionIdx}")
	public @ResponseBody File download(
							@PathVariable("functionIdx") String functionIdx, 
							HttpServletRequest request, HttpServletResponse response)
																	throws Throwable {
		logger.debug("#### 서버 파일 다운로드 시작 ####");
		
		String filePath = request.getSession().getServletContext().getRealPath("/resource/data/patch/v101");
		String fileName = "framework-20130302.jar";
		String fullPath = filePath + "/" + fileName;
		logger.debug("##fullPath : " + fullPath);
	    File file = new File(fullPath);
	    
		return file;
	}
	
	
	@RequestMapping(method=RequestMethod.POST, value = "/patchList/getPatchInfo/{functionIdx}")
	public @ResponseBody WePatch patchInfo(@PathVariable("functionIdx") String functionIdx, @RequestBody WePatch wePatchModel, HttpServletRequest request, HttpServletResponse response) throws Throwable {
		WePatch domain = patchService.getWePatchInfo(wePatchModel);
		return domain;
	}
	
	/**
	 * 사용자 인스톨 정보를 저장 혹은 수정 한다.
	 * 사용자 정보는 기본적으로 다운로드 받을 때 저장이 되지만 다른 정보로 인스톨 할 경우 그냥 insert 하고, 
	 * 정보가 있을 경우에는 update 처리 한다. 
	 * @param installVo
	 * @param request
	 * @param response
	 * @return
	 * @throws Throwable
	 */
	@RequestMapping(method=RequestMethod.POST, value="/installAuthUser")
	public @ResponseBody WeInstallUser installAuthUser(@RequestBody WeInstallUser installVo, HttpServletRequest request, HttpServletResponse response) throws Throwable {
		logger.debug("## Rest Call :  " + installVo.toString());
		// 사용자 정보 인증 - 액티브 키와 유저 메일로  건수가 있는지 조회한 후 있으면 update, 없으면 insert를 수행한다. 
		
		WeInstallUser installUser = patchService.getInstallUserInfo(installVo);
		
		if(installUser == null ) {
			// 다운로드 정보가 없으므로 새로 저장함  
			installVo.setWe_install_date(new Date());
			installVo.setWe_company("undefined");
			installVo.setWe_new_yn("Y");
			installVo.setWe_use_purpose("6");
			entityService.insertEntity(installVo);
			
			return installVo;
		} else {
			// update
			installUser.setWe_auth_date(new Date());
			installUser.setWe_auth_yn("Y");
			entityService.updateEntity(installUser);
			
			return installUser;
		}
	}
}
