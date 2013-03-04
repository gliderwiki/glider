/**
 * @FileName  : DownloadServiceImpl.java
 * @Project   : glider
 * @Date      : 2013. 2. 26. 
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.download.service;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.gliderwiki.util.PropertyUtil;
import org.gliderwiki.util.SendMailSMTP;
import org.gliderwiki.web.domain.WeSendMail;
import org.gliderwiki.web.system.SystemConst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author yion
 *
 */
@Service("downloadService")
public class DownloadServiceImpl implements DownloadService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public int sendActiveKey(String email, String activeKey, HttpServletRequest request) throws Throwable {
		int result = 0;

		
		String emailMsgTxt = "안녕하세요. 글라이더 위키 입니다. <br><br>" +
				"글라이더 위키의 Active Key 를 전송하여 드립니다. <br><br>" +
				"Active Key : " + activeKey + 
				"<br><br>Active Key는 글라이더 위키의 Install 시점에 입력해야 합니다.<br><br>" +
				"기타 설치 관련 문의는 아래의 메일주소로 해주시기 바랍니다.<br><br>" +
				"문의 메일 : performizer@gmail.com<br><br>"+
				"본 메일은 발신 전용이므로 반드시 위의 문의 메일 주소를 통해 문의 주시기 바랍니다. "; 	

		String emailTitle = "GLiDER Wiki™ 의  Active Key 정보입니다..";		// 제목
		try {
			SendMailSMTP sender = new SendMailSMTP();
			result = sender.sendSimpleMail(email, emailTitle, emailMsgTxt, request);
		} catch (UnsupportedEncodingException e) {
			result = -2;
		} catch (MessagingException e) {
			result = -3;
		}
		return result;
	}

}
