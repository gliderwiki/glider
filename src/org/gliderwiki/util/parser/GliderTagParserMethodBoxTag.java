/**
 * @FileName  : GliderTagParserMethodBoxTag.java
 * @Project   : NightHawk
 * @Date      : 2012. 11. 21. 
 * @작성자      : @author ganji

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.util.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.gliderwiki.util.GliderTagPaserUtil;

/**
 * @author ganji
 *
 */
public class GliderTagParserMethodBoxTag {

	public List<Map<String, Object>> noteTagList = new ArrayList<Map<String,Object>>();
	
	// [note|각주이름1#각주설명1] 혹은 [note|각주이름2]
	public String getNOTE(String str){
		String patternTxt = "\\[(note\\|)(.*?)(#(.*?))?\\]";
		
		String parserTag = null;
		Map<String, Object> tagMap = null;
		Integer cnt = 0;
		
		while( GliderTagPaserUtil.getMatchFind(str, patternTxt) ){
			cnt = cnt+1;
			tagMap = new HashMap<String, Object>();
			parserTag = GliderTagPaserUtil.getFirstTag(str, patternTxt);
			
			String tag = GliderTagPaserUtil.getFirstReturnTag(parserTag, patternTxt, "$2");
			String tagComment = GliderTagPaserUtil.getFirstReturnTag(parserTag, patternTxt, "$4");
			
			// 각주설명이 없으면 각주이름을 설명에 넣는다.
			if( tagComment == null || "".equals(tagComment) ){
				tagComment = tag;
			}
			String html = "$2<a href=\"#note-"+cnt+"\"><sup id=\"note-sub-"+cnt+"\">"+cnt+"</sup></a>";
			
			str = GliderTagPaserUtil.replaceFirstTag(str, patternTxt, html);
			tagMap.put("tagUrl", tag);
			tagMap.put("tagTitle", tagComment);
			noteTagList.add(tagMap);
		}
		return str;
	}
	
	public String getALERT(String str){
		str = GliderTagPaserUtil.replaceAllTag(str, "\\[alert\\]([\\w\\W]+?)\\[alert\\]", "<div class=\"box-alert\">$1</div>");
		return str;
	}
	
	// [field|필드타이틀]필드내용[field] 혹은 [field]필드내용[field]
	public String getFIELD(String str){
		str = GliderTagPaserUtil.replaceAllTag(str, "\\[field(\\|(.*?))?\\]([\\w\\W]+?)\\[field\\]", "<fieldset class=\"fieldset\">\n<legend class=\"legend\">$2</legend>\n$3\n</fieldset>");
		return str;
	}
	
	public String getINFO(String str){
		str = GliderTagPaserUtil.replaceAllTag(str, "\\[info\\]([\\w\\W]+?)\\[info\\]", "<div class=\"box-check\">$1</div>");
		return str;
	}
	
	public String getBOX(String str){
		str = GliderTagPaserUtil.replaceAllTag(str, "\\[box\\|(.*?)\\]([\\w\\W]+?)\\[box\\]", "<div class=\"box-empty\" style=\"background-color:#$1\">$2</div>");
		return str;
	}

}
