/**
 * @FileName  : GliderTagParserMethodBasicTag.java
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
public class GliderTagParserMethodBasicTag {
	
	public List<Map<String, Object>> linkTagList = new ArrayList<Map<String,Object>>();
	
	public String getBOLD(String str){
		str = GliderTagPaserUtil.replaceAllTag(str, "\\*{2}([\\w\\W]+?)\\*{2}", "<B>$1</B>");
		return str;
	}
	
	public String getITALIC(String str){
		str = GliderTagPaserUtil.replaceAllTag(str, "([^http:])(\\/{2}([\\w\\W]+?)\\/{2})", "$1<I>$3</I>");
		return str;
	}
	
	public String getUNDERLINING(String str){
		str = GliderTagPaserUtil.replaceAllTag(str, "\\_{2}([\\w\\W]+?)\\_{2}", "<U>$1</U>");
		return str;
	}
	
	public String getSUBERSCRIPT(String str){
		str = GliderTagPaserUtil.replaceAllTag(str, "\\[sb\\]([\\w\\W]+?)\\[sb\\]", "<sub>$1</sub>");
		return str;
	}
	
	public String getSUPERSCRIPT(String str){
		str = GliderTagPaserUtil.replaceAllTag(str, "\\[sp\\]([\\w\\W]+?)\\[sp\\]", "<sup>$1</sup>");
		return str;
	}
	
	public String getSTRIKE(String str){
		str = GliderTagPaserUtil.replaceAllTag(str, "\\[d\\]([\\w\\W]+?)\\[d\\]", "<del>$1</del>");
		return str;
	}

	public String getHIPERLINK(String str){
		String patternTxt = "\\[(@:)(.*?)(\\|(.*?))?\\]";
		
		String parserTag = null;
		Map<String, Object> tagMap = null;
		
		while( GliderTagPaserUtil.getMatchFind(str, patternTxt) ){
			tagMap = new HashMap<String, Object>();
			parserTag = GliderTagPaserUtil.getFirstTag(str, patternTxt);
			
			String url = GliderTagPaserUtil.getFirstReturnTag(parserTag, patternTxt, "$2");
			String title = GliderTagPaserUtil.getFirstReturnTag(parserTag, patternTxt, "$4");
			
			// 설명구문이 없으면 url을 대신 넣는다.
			if( title == null || "".equals(title) ){
				title = url;
			}
			String html = "<a href=\""+url+"\">"+title+"</a>";
			
			str = GliderTagPaserUtil.replaceFirstTag(str, patternTxt, html);
			tagMap.put("tagUrl", url);
			tagMap.put("tagTitle", title);
			linkTagList.add(tagMap);
		}
		
		return str;
	}
	
	// [!|http://asdf.com|aaa][!a|left][!s|30|40][!]
	public String getIMG(String str){
		String patternTxt = "\\[!\\|(.*?)(\\|(.*?))?\\](.*?)\\[!\\]";
				
		String parserTag = null;
		while( GliderTagPaserUtil.getMatchFind(str, patternTxt) ){
			parserTag = GliderTagPaserUtil.getFirstTag(str, patternTxt);
			
			// url, 설명
			String url = GliderTagPaserUtil.getFirstReturnTag(parserTag, patternTxt, "$1");
			String title = GliderTagPaserUtil.getFirstReturnTag(parserTag, patternTxt, "$3");
			
			// 정렬
			String patternSub = "\\[!a\\|(.*?)\\]";
			String align = GliderTagPaserUtil.getFirstReturnTag(parserTag, patternSub, "$1");
			// 넓이
			patternSub = "\\[!s\\|(.*?)(\\|(.*?))?\\]";
			String width = GliderTagPaserUtil.getFirstReturnTag(parserTag, patternSub, "$1");
			String height = GliderTagPaserUtil.getFirstReturnTag(parserTag, patternSub, "$3");
			
			String html = null;
			if( "".equals(width) &&  "".equals(height) ){
				html = "<img alt=\""+title+"\" src=\""+url+"\" style=\"display:block; float:"+align+"; margin: 0 auto; clear: both;\" >";
				
			}else if( !"".equals(width) &&  "".equals(height) ){
				html = "<img alt=\""+title+"\" src=\""+url+"\" width=\""+width+"\" style=\"display:block; float:"+align+"; margin: 0 auto; clear: both;\" >";
				
			}else if( "".equals(width) &&  !"".equals(height) ){
				html = "<img alt=\""+title+"\" src=\""+url+"\" height=\""+height+"\" style=\"display:block; float:"+align+"; margin: 0 auto; clear: both;\" >";
				
			}else{
				html = "<img alt=\""+title+"\" src=\""+url+"\" width=\""+width+"\" height=\""+height+"\" style=\"display:block; float:"+align+"; margin: 0 auto; clear: both;\" >";
			}
			
			str = GliderTagPaserUtil.replaceFirstTag(str, patternTxt, html);
			
		}
		return str;
	}
	
	public String getBACKGROUND(String str){
		str = GliderTagPaserUtil.replaceAllTag(str, "\\[bg\\|(.*?)\\]([\\w\\W]+?)\\[bg\\]", "<span style='background:#$1'>$2</span>");
		return str;
	}
	
	public String getCOLOR(String str){
		str = GliderTagPaserUtil.replaceAllTag(str, "\\[color\\|(.*?)\\]([\\w\\W]+?)\\[color\\]", "<font color='#$1'>$2</font>");
		return str;
	}
	
	public String getFONT(String str){
		String patternTxt = "\\[font\\|(.*?)\\]([\\w\\W]+?)\\[font\\]";
		
		String parserTag = null;
		while( GliderTagPaserUtil.getMatchFind(str, patternTxt) ){
			
			parserTag = GliderTagPaserUtil.getFirstTag(str, patternTxt);
			
			String fontTxt = GliderTagPaserUtil.getFirstReturnTag(parserTag, patternTxt, "$1");
			String contents = GliderTagPaserUtil.getFirstReturnTag(parserTag, patternTxt, "$2");
			
			String font = null; 
			if( "돋음".equals(fontTxt) ){
				font = "dotum";
			}else if( "굴림".equals(fontTxt) ){
				font = "gulim";
			}else if( "궁서".equals(fontTxt) ){
				font = "gungsuh";
			}else if( "바탕".equals(fontTxt) ){
				font = "batang";
			}
			
			String html = "<span style=\"font-family:'"+fontTxt+"','"+font+"'\">"+contents+"</span>";
			str = GliderTagPaserUtil.replaceFirstTag(str, patternTxt, html);
		}
		
		return str;
	}
	
	public String getFONTSIZE(String str){
		str = GliderTagPaserUtil.replaceAllTag(str, "\\[size\\|(.*?)\\]([\\w\\W]+?)\\[size\\]", "<span style=\"font-size: $1px;\">$2</span>");
		return str;
	}
	
	public String getFONTALIGN(String str){
		String patternTxt = "\\[align\\:(left|right|center|justify)\\]([\\w\\W]+?)\\[align\\]";
		
		String parserTag = null;
		while( GliderTagPaserUtil.getMatchFind(str, patternTxt) ){
			
			parserTag = GliderTagPaserUtil.getFirstTag(str, patternTxt);
			
			String fontalignTxt = GliderTagPaserUtil.getFirstReturnTag(parserTag, patternTxt, "$1");
			String contents = GliderTagPaserUtil.getFirstReturnTag(parserTag, patternTxt, "$2");
			
			String fontalign = null; 
			if( "left".equals(fontalignTxt) ){
				fontalign = "left";
			}else if( "center".equals(fontalignTxt) ){
				fontalign = "center";
			}else if( "right".equals(fontalignTxt) ){
				fontalign = "right";
			}else if( "justify".equals(fontalignTxt)){
				fontalign = "justify";
			}
			
			String html = "<div style='text-align:"+fontalign+"'>"+contents+"</div>";
			str = GliderTagPaserUtil.replaceFirstTag(str, patternTxt, html);
		}
		
		return str;
	}
	
	public String getHR(String str){
		str = GliderTagPaserUtil.replaceAllTag(str, "\\[hr\\]", "<hr>");
		return str;
	}
	
	public String getBR(String str){
		
		String[] brArray = str.split("\r\n");
		str = "";
		
		for(int i=0; i< brArray.length; i++){
			if( "".equals(brArray[i].trim() ) ){
				str += brArray[i]+"\r\n";
			}else{
				str += brArray[i]+"\n";
			}
		}
		str = str + "\r\n";
		
		return str;
	}
	
	
}
