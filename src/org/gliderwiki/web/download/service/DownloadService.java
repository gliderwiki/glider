/**
 * @FileName  : DownloadService.java
 * @Project   : glider
 * @Date      : 2013. 2. 26. 
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.download.service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yion
 *
 */
public interface DownloadService {
	
	int sendActiveKey(String email, String activeKey, HttpServletRequest request) throws Throwable;
}
