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
import java.util.Properties;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.gliderwiki.rest.service.PatchService;
import org.gliderwiki.util.PropertyUtil;
import org.gliderwiki.web.domain.WeFunction;
import org.gliderwiki.web.domain.WePatch;
import org.gliderwiki.web.system.SystemConst;
import org.gliderwiki.web.vo.PatchInfoVo;
import org.gliderwiki.web.vo.TempUploadVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
public class PatchServiceRest {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
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
		//TODOLIST 사용자 정보 인증 (라이센스 키, 도메인, 이메일) 
		
		logger.debug("#request.getSession().getServletContext().getRealPath : " + request.getSession().getServletContext().getRealPath("/WEB-INF/spring/properties/"));
		
		String svcPath = request.getSession().getServletContext().getRealPath(SystemConst.PROPERTY_FULL_PATH + "version");
		boolean isServer = true;
		
		// 서버의  버전 정보를 가져온다 
		Properties props = PropertyUtil.getVersionPropertyInfo(svcPath, isServer);
		String serverVersion = props.getProperty("version.info");
		
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
	 
	@RequestMapping(method=RequestMethod.POST, value = "/patchList/download/{functionIdx}")
	public void download(@PathVariable("functionIdx") String functionIdx, HttpServletRequest request, HttpServletResponse response) throws Throwable {
		logger.debug("###download !!");
		
		logger.debug("###functionIdx : " + functionIdx);
		
		//html 에서 아래와 같이 호출 함 
		//var funcFileDownload = function(url){
		//$('#patchDownloadForm').attr('method', 'post');
    	//$('#patchDownloadForm').attr('action', '/patchList/download/2');
    	//$('#patchDownloadForm').submit();
    	// }


	 	String filePath = request.getSession().getServletContext().getRealPath("/resource/data/patch/v101");
		String fileName = "framework-20120723.jar";
		
		String fullPath = filePath + "/" + fileName;
	
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
	
	*/
	
	@RequestMapping(method=RequestMethod.POST, value = "/patchList/download/{functionIdx}")
	public @ResponseBody WePatch patchInfo(@PathVariable("functionIdx") String functionIdx, @RequestBody WePatch wePatchModel, HttpServletRequest request, HttpServletResponse response) throws Throwable {
		WePatch domain = patchService.getWePatchInfo(wePatchModel);
		return domain;
	}
}
