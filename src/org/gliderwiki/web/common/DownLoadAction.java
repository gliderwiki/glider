/**
 * @FileName  : DownLoadAction.java
 * @Project   : NightHawk
 * @Date      : 2012. 9. 17. 
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.common;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;

/**
 * @author yion
 *
 */
public class DownLoadAction {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public void getFileDownload( String requestedFile, String realFileName, HttpServletResponse response, String type) throws Exception {

		File uFile = new  File(requestedFile) ;
		int fSize = (int) uFile.length();
		
		try{
			BufferedInputStream in = new BufferedInputStream(new FileInputStream(uFile));
			
			//type 셋팅
			String mimetype = type;
						
			if (mimetype == null || mimetype.length() == 0) {
				mimetype = "application/octet-stream;";
		    }
			    			
			response.setBufferSize(fSize);
			response.setContentType(mimetype + "; charset=utf-8");
			//TODOLIST 서버 인지 아닌지 구분해서 아래의 구문을 넣어줄것!
			//response.setHeader("Content-Disposition", "attachment; filename=\""	+ new String(realFileName.getBytes("euc-kr"),"ISO-8859-1") + "\"");
			response.setHeader("Content-Disposition", "attachment; filename=\""	+ new String(realFileName.getBytes("UTF-8"), "ISO-8859-1") + "\"");
			

			
			if (fSize > 0) {
				response.setHeader("Content-Length", "" + fSize);
			}
			
			FileCopyUtils.copy(in, response.getOutputStream());

			in.close();
			
			response.getOutputStream().flush();
			response.getOutputStream().close();
			
		}catch(FileNotFoundException e) {
			logger.debug("############ 지정된 파일을 찾을수 없습니다. File Download ERROR ################");
            e.printStackTrace();
        }catch(IOException e) {
        	logger.debug("############ 스트림 익셉션. File Download ERROR ################");
            e.printStackTrace();
        }
		
	}
}
