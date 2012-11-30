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
	
}
