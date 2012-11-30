/**
 * @FileName  : FileController.java
 * @Project   : NightHawk
 * @Date      : 2012. 2. 9. 
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 : 파일 업로드용 임시 테스트 파일 이므로 추후 삭제하도록 한다. 
 */
package org.gliderwiki.web.filehandle.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.gliderwiki.framework.entity.service.EntityService;
import org.gliderwiki.framework.exception.FilePermitMsgException;
import org.gliderwiki.framework.util.DateUtil;
import org.gliderwiki.framework.util.FileUploader;
import org.gliderwiki.web.domain.WeFile;
import org.gliderwiki.web.domain.WeTmpFile;
import org.gliderwiki.web.domain.WeUser;
import org.gliderwiki.web.vo.TempUploadVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


/**
 * @author yion
 *
 */
@Controller
public class FileController {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Value("#{config['file.maxSize']}")
	String uploadMaxSize;
	
	
	@SuppressWarnings("rawtypes")
	@Autowired
	private EntityService entityService;
	
	/**
	 * 파일 임시 업로드 테스트 
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(value = "/tempupload", method = RequestMethod.GET)
	public ModelAndView tempupload(ModelAndView modelAndView) {
		
		logger.debug("업로드 준비중....");
		
		modelAndView.setViewName("temp/upload/tempUpload");
		return modelAndView;
	}

	
	/**
	 * 파일임시저장 테스트 
	 * @param fileVo
	 * @param request
	 * @param response
	 * @param modelAndView
	 * @return
	 * @throws Throwable
	 */
	@RequestMapping("/registUpload")
	public ModelAndView registUpload(@ModelAttribute("frmFile") TempUploadVo fileVo, 
			HttpServletRequest request, HttpServletResponse response, ModelAndView modelAndView)  throws Throwable {
		
		logger.debug("##root path : " + request.getServletContext().getRealPath("/"));
		logger.debug("##request.getSession() : " + request.getSession().getServletContext().getRealPath("/resource/temp/"));
		String svcPath = request.getSession().getServletContext().getRealPath("/resource/temp/");
		
		
		//업로드 날짜 및 파일명 구성
		String today = DateUtil.getToday();
		
		//TODOLIST : 세션에서 가져온다. 일단 테스트용으로 사용자 순번 1을 통해 등록한다. 
		String weUserIdx = "1";
		String weUserId = "cafeciel";
		
		double maxSize = Double.parseDouble(uploadMaxSize);
				
		TempUploadVo tempFile = null;
		Map<String, String> param = new HashMap<String, String>();
		
		// 파일 객체, 사용자 아이디, 오늘날짜, 파일 업로드 사이즈
		try {
			tempFile = FileUploader.uploadTempFile(fileVo.getFile(), svcPath, weUserIdx, today, maxSize); 			
		} catch (FilePermitMsgException e) {
			logger.debug("###익셉션 메시지 : " +e.getCustomMsg());
			logger.debug("###익셉션 메시지 : " +e.toString());
			param.put("result", "-1");
	    	param.put("msg", "파일 업로드에 실패 하였습니다.");    	
	    	
	    	return new ModelAndView("json_").addObject("param", param);
		} catch (Exception ee) {
			logger.debug("**************************예상치 않은 Exception 발생!!!!");
			ee.printStackTrace();
		}
		
		logger.debug("tempFile : " + tempFile.toString());
	
		if(tempFile.isUploaded()) {
			// DB에 인서트 
			WeFile weFile = new WeFile(); 
			
			weFile.setWe_file_real_name(tempFile.getFileRealName());
			weFile.setWe_file_save_name(tempFile.getFileSaveName());
			weFile.setWe_file_save_path(tempFile.getFilePath());
			weFile.setWe_file_size(tempFile.getFileSize()+"");
			weFile.setWe_file_type(tempFile.getFileType());
			weFile.setWe_thumb_yn(tempFile.getThumbYn());
			weFile.setWe_thumb_name(tempFile.getThumbName());
			weFile.setWe_thumb_path(tempFile.getThumbPath());
			weFile.setWe_user_idx(Integer.parseInt(weUserIdx));
			weFile.setWe_ins_date(DateUtil.getTodayTime());
			weFile.setWe_ins_user(weUserIdx);
			
			int result = entityService.insertEntity(weFile);
			
			logger.debug("### result : " + result);
			
			if(result == 1) {
				
				WeFile retFile = (WeFile)entityService.getRowEntity(weFile);
				
				param.put("result", "1");
		    	param.put("msg", "성공");    	
		    	param.put("realFileName", retFile.getWe_file_real_name());    	
		    	param.put("saveFileName", retFile.getWe_file_save_name());
		    	param.put("filePath", retFile.getWe_file_save_path());
		    	param.put("fileSize", retFile.getWe_file_size()+"");
		    	param.put("tmpsrc", svcPath);
		    	param.put("fileIndex", retFile.getWe_file_idx()+"");		    	
			} else {
				param.put("result", "0");
		    	param.put("msg", "파일 저장에 실패 하였습니다. 다시 시도하세요.");    	
		    	param.put("realFileName", tempFile.getFileRealName());    	
			}
			
		} else {
			param.put("result", "-1");
	    	param.put("msg", "파일 업로드에 실패 하였습니다.");    	
	    	param.put("realFileName", tempFile.getFileRealName());    	
		}
		
		logger.debug("param : "  + param.toString());
		
		return new ModelAndView("json_").addObject("param", param);
	}
	
}
