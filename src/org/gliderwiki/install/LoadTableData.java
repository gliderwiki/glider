/**
 * @FileName  : InitData.java
 * @Project   : NightHawk
 * @Date      : 2012. 9. 25. 
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.install;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ResourceLoaderAware;

/**
 * @author yion
 *
 */
public class LoadTableData {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//전체 테이블 이름을 로드해온다. 
	private Map<Integer, String> allTables;
			
	public LoadTableData() {
	
	}


	/**
	 * 테이블 목록들을 조회해온다. 
	 * @param tableInitPath
	 */
	public String LoadTableData(String tableInitPath, String enc) {
		FileInputStream fis = null;
		InputStreamReader ins = null;
		BufferedReader reader = null;
		String encoding = "";
		try {
			fis = new FileInputStream(tableInitPath+"/table_list.sql");
			ins = new InputStreamReader(fis, enc);
			logger.info("##############################");
			logger.info("FILE encoding : " + ins.getEncoding());
			logger.info("##############################");
			
			encoding = ins.getEncoding();
			
			reader = new BufferedReader(ins);
			int lineNum = 0;
			
			allTables = new HashMap<Integer, String>(); 
			while(true) {
				String line = reader.readLine();
								
				if(line != null || !line.equals("")) {
					allTables.put(lineNum, line);
				} else {
					break;
				}
				
				//System.out.println("lineNum : " + lineNum +" ,    Table : " + line);
				lineNum++;
				
			}
			reader.close();
			
		} catch (Exception e) {
			try {
				reader.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} 
		return encoding;
	}

	public String getTable(Integer idx) {
		return this.allTables.get(idx);
	}
	
	public Map getAllTables() {
		return this.allTables;
	}
}
