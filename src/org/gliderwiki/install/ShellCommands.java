/**
 * @FileName  : ShellCommands.java
 * @Project   : NightHawk
 * @Date      : 2012. 9. 1. 
 * @작성자      : @author sewooyam

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.install;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
		
		// chmod는 결과값이 없기 때문에 실행 결과가 정상적으로 반영 되었는지 확인 하려면 stat -c %a 파일명 을 통해 리턴받을 수 있다.
		try {
			process = runtime.exec(command);
			logger.debug("#command : " + command);
			
			// shell 실행이 정상 동작 했을 경우 리턴되는 값(e.g. ls -al 후 나오는 값) 을 읽어온다.
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
				if (bufferReader != null) {
					bufferReader.close();					
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

		return output.toString();
	}

}
