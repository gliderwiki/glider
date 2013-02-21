/**
 * @FileName  : PropertyUtil.java
 * @Project   : NightHawk
 * @Date      : 2012. 9. 21. 
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.gliderwiki.web.system.SystemConst;

/**
 * @author yion
 *
 */
public class PropertyUtil {

	/**
	 * 메일 전송 정보를 읽어온다. 
	 * @param svcPath
	 * @return
	 * @throws IOException
	 */
	public static Properties getMailPropertyInfo(String svcPath) throws IOException {
		Properties props = null;
		File file = null;
		InputStream ins = null;
		try {
			file = new File(svcPath);
			props = new Properties();
			ins = new FileInputStream(svcPath+"/mail.properties");
			props.load(ins);
			ins.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ins.close();
		}
		return props;
	}
	
	/**
	 * 지정된 properties 파일을 읽어온다.
	 * @param svcPath
	 * @return
	 * @throws IOException
	 */
	public static Properties getPropertyInfo(String svcPath, String propsName) throws IOException {
		Properties props = null;
		File file = null;
		InputStream ins = null;
		try {
			file = new File(svcPath);
			props = new Properties();
			ins = new FileInputStream(svcPath+"/"+propsName);
			props.load(ins);
			ins.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ins.close();
		}
		return props;
	}
	
	/**
	 * 패치 및 확장기능을 위한 버전 정보를 읽어온다. 
	 * @param svcPath
	 * @param isServer
	 * @return
	 * @throws IOException
	 */
	public static Properties getVersionPropertyInfo(String svcPath, boolean isServer) throws IOException {
		Properties props = null;
		File file = null;
		InputStream ins = null;
		try {
			file = new File(svcPath);
			props = new Properties();
			
			if(isServer) {
				ins = new FileInputStream(svcPath+"/server-version.properties"); 
			} else {
				ins = new FileInputStream(svcPath+"/client-version.properties");
			}
			
			props.load(ins);
			ins.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ins.close();
		}
		return props;
	}
	
	
	/**
	 * 현재 버전 정보를 가져온다. isServer 가 true 이면 서버정보, 아니면 클라이언트 정보 
	 * @param request
	 * @param isServer
	 * @return
	 * @throws IOException
	 */
	public static String getVersionProps(HttpServletRequest request, boolean isServer)
			throws IOException {
		String svcPath = request.getSession().getServletContext().getRealPath(SystemConst.PROPERTY_FULL_PATH + "version");
		
		Properties props = getVersionPropertyInfo(svcPath, isServer);
		String version = props.getProperty("version.info");
		return version;
	}
	
}
