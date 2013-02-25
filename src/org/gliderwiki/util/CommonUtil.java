/**
 * @FileName  : CommonUtil.java
 * @Project   : glider
 * @Date      : 2013. 2. 25. 
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.util;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author yion
 *
 */
public class CommonUtil {
	
	static Logger logger = LoggerFactory.getLogger(CommonUtil.class);

	public static String getClientDomain(HttpServletRequest request) {
		String path = request.getContextPath();
		logger.debug("##path : " + path);
		String getProtocol = request.getScheme();
		logger.debug("##getProtocol : " + getProtocol);
		String getDomain = request.getServerName();
		logger.debug("##getDomain : " + getDomain);
		String getPort = Integer.toString(request.getServerPort());		
		String domain = getProtocol + "://" + getDomain + ":" + getPort + path	+ "/";
		logger.debug("##domain : " + domain );
		return domain;
	}
}
