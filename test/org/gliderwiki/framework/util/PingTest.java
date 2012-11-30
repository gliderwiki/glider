/**
 * @FileName  : PingTest.java
 * @Project   : NightHawk
 * @Date      : 2012. 9. 21. 
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.framework.util;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author yion
 *
 */
public class PingTest {
	
	public static void main(String args[]) {
		String url_ssl = "ssl://smtp.gmail.com";
		String url_tls = "tls://smtp.gmail.com";
		int timeout = 10000;
		InetAddress target;
		try {
			target = InetAddress.getByName(url_ssl);
			System.out.println("Ping test : " + target.isReachable(timeout));
		} catch (UnknownHostException e) {
			System.out.println("##### : " + e.getMessage());
			e.printStackTrace();
		} catch (IOException ie) {
			System.out.println("$$$$ : " + ie.getMessage());
			ie.printStackTrace();
		}
		
		
	}
}	
