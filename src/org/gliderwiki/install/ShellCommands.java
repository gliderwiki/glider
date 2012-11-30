/**
 * @FileName  : ShellCommands.java
 * @Project   : NightHawk
 * @Date      : 2012. 9. 1. 
 * @작성자      : @author sewooyam

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.install;

import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author sewooyam
 * 
 */
public class ShellCommands {

	static Logger logger = LoggerFactory.getLogger(ShellCommands.class);
	
	public static String execute(String command) {
		StringBuffer output = new StringBuffer();
		Process process = null;
		BufferedReader bufferReader = null;
		Runtime runtime = Runtime.getRuntime();
		String osName = System.getProperty("os.name");

		// 윈도우일 경우
		if (osName.indexOf("Windows") > -1) {
			command = "cmd /c " + command;
		}

		try {
			process = runtime.exec(command);

			logger.debug("#command : " + command);
			
			// shell 실행이 정상 동작
			bufferReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String msg = null;
			while ((msg = bufferReader.readLine()) != null) {
				output.append(msg + System.getProperty("line.separator"));
			}
			bufferReader.close();

		} catch (IOException e) {
			output.append("IOException : " + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				process.destroy();
				if (bufferReader != null)
					bufferReader.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

		return output.toString();
	}

}
