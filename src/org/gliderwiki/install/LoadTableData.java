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

import org.springframework.context.ResourceLoaderAware;

/**
 * @author yion
 *
 */
public class LoadTableData {

	//전체 테이블 이름을 로드해온다. 
	private Map<Integer, String> allTables;
			
	public LoadTableData() {
	
	}


	/**
	 * 테이블 목록들을 조회해온다. 
	 * @param tableInitPath
	 */
	public LoadTableData(String tableInitPath) {
		
		InputStream ins = null;
		BufferedReader reader = null;
		try {
			ins = new FileInputStream(tableInitPath+"/table_list.sql");
		
			reader = new BufferedReader(new InputStreamReader(ins));
			
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
		
	}

	public String getTable(Integer idx) {
		return this.allTables.get(idx);
	}
	
	public Map getAllTables() {
		return this.allTables;
	}
}
