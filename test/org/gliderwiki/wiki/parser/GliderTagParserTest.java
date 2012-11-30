/**
 * @FileName  : GliderTagParser.java
 * @Project   : NightHawk
 * @Date      : 2012. 3. 31. 
 * @작성자      : @author ganji

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.wiki.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.gliderwiki.wiki.parser.GliderTagParserMethodTest.EsCharacter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * @author ganji
 *
 */
public class GliderTagParserTest {

	Logger logger = LoggerFactory.getLogger(GliderTagParserTest.class);
	
	public List<String> linkTagList = new ArrayList<String>();
	
	@SuppressWarnings("rawtypes")
	public Map<String, Object> parserMap(String str){
		
		//임시  <BR>
		//str = str.replaceAll("\\n", "<br />");
		
		Map<String, Object> totMap = new HashMap<String, Object>();
		
		GliderTagParserMethodTest tagParserHtml = new GliderTagParserMethodTest();
		List characherList = tagParserHtml.EsCharacterList();

		System.out.println( "##원본 문자열 :::{} "+str);
		for( int i=0; i< characherList.size(); i++ ){
			str = tagParserHtml.getHtml(str, (EsCharacter) characherList.get(i));
		}
		
		//임시  <BR>
		str = str.replaceAll("<br />", "<br />\n");
		
		System.out.println( "##결과 문자열 :::{} "+str);
		
		totMap.put("htmltag", str);
		totMap.put("linkTagList", tagParserHtml.linkTagList);
		totMap.put("noteTagList", tagParserHtml.noteTagList);
		totMap.put("h1TagList", tagParserHtml.h1TagList);
		totMap.put("h2TagList", tagParserHtml.h2TagList);
		totMap.put("h3TagList", tagParserHtml.h3TagList);
		
		return totMap;
	}
	
	public String parserTo(String str){
		
/*		GliderTagParserMethodTest tagParserHtml = new GliderTagParserMethodTest();
		List patternList = tagParserHtml.getPattern();
		List characherList = tagParserHtml.EsCharacterList();
		Pattern pattern = null;
		Matcher match = null;
		
		Map<String, Object> totMap = new HashMap<String, Object>();
		
		logger.debug("##원본 문자열 :::{} ",str);		
		for( int i=0; i< patternList.size(); i++ ){
			pattern = Pattern.compile((String) patternList.get(i));
			match = pattern.matcher(str);
			while(match.find()){
				logger.debug("##일치하는 문자열 :::{} ",match.group());
				str = str.replace(match.group().trim(), tagParserHtml.getHtml(match.group().trim(), (EsCharacter) characherList.get(i)));
			}
		}
		logger.debug("##결과 문자열 :::{} ",str);
		//linkTagList = tagParserHtml.linkTagList;
*/		
		return str;
	}
	
	
	
}
