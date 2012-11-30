/**
 * @FileName  : TestExec.java
 * @Project   : NightHawk
 * @Date      : 2012. 7. 21. 
 * @작성자      : @author sewooyam

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.wiki.install;

import java.io.*;

import org.gliderwiki.wiki.common.service.GliderWikiTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author sewooyam
 * 
 */
public class TestExec {
	Logger logger = LoggerFactory.getLogger(TestExec.class);
	
	public TestExec() {

	}

	public TestExec(String cmdline) {
		try {
			logger.debug("#cmd :"+ cmdline +" 명령어가 실행 되었습니다.");
			
			String line;
			Process p = Runtime.getRuntime().exec(cmdline);
			BufferedReader input = new BufferedReader(new InputStreamReader(
					p.getInputStream()));
			while ((line = input.readLine()) != null) {
				System.out.println(line);
			}
			input.close();
		} catch (Exception err) {
			logger.debug("#cmd 실행시 오류가 발생");
			err.printStackTrace();
		}

	}



}
