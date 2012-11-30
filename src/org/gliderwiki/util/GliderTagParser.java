/**
 * @FileName  : GliderTagParser.java
 * @Project   : NightHawk
 * @Date      : 2012. 3. 31. 
 * @작성자      : @author ganji

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.gliderwiki.util.GliderTagParserMethod.EsCharacter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author ganji
 *
 */
public class GliderTagParser {

	Logger logger = LoggerFactory.getLogger(GliderTagParser.class);
	
	@SuppressWarnings("rawtypes")
	public Map<String, Object> parserMap(String str){
		
		
		Map<String, Object> totMap = new HashMap<String, Object>();
		//Map<String, Object> hTagMap = new HashMap<String, Object>();
		
		GliderTagParserMethod tagParserHtml = new GliderTagParserMethod();
		List characherList = tagParserHtml.EsCharacterList();

		//System.out.println( "##원본 문자열 :::{} "+str);
		for( int i=0; i< characherList.size(); i++ ){
			str = tagParserHtml.getHtml(str, (EsCharacter) characherList.get(i));
		}
		
		
		totMap.put("htmltag", str);
		totMap.put("linkTagList", tagParserHtml.linkTagList);
		totMap.put("noteTagList", tagParserHtml.noteTagList);
		totMap.put("h1TagList", tagParserHtml.h1TagList);
		totMap.put("graphCnt", tagParserHtml.graphCnt);
		
		return totMap;
	}
}
