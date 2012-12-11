/**
 * @FileName  : GliderTagTest.java
 * @Project   : NightHawk
 * @Date      : 2012. 4. 28. 
 * @작성자      : @author ganji

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.wiki.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.gliderwiki.util.GliderTagPaserUtil;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ganji
 *
 */
public class tagTest {
	Logger logger = LoggerFactory.getLogger(tagTest.class);
	GliderTagParserTest gtp = new GliderTagParserTest();
	/*
	@Test
	public void test(){
		String str = "\r\n"+
					 "asdas\r\n"+
					 "[syntax]내용내용\r\n\r\n내용[syntax]aa\r\n"+
					"다음[syntax]내용2[syntax][syntax]내용3[syntax]";
		
		String patternTxt = "(\\[syntax\\])([\\w\\W]*?)(\\[syntax\\])";
		
		List<String> syntaxList = new ArrayList<String>();
		
		while( GliderTagPaserUtil.getMatchFind(str, patternTxt) ){
			
			// syntax 내용을 잘라내어 보관한다.
			String syntax = GliderTagPaserUtil.getFirstTag(str, patternTxt);
			syntax = GliderTagPaserUtil.getFirstReturnTag(syntax, patternTxt, "$2");
			syntaxList.add(syntax);
			
			// 처리한 syntax을 초기화 시킨다. 
			str = GliderTagPaserUtil.replaceFirstTag(str, patternTxt, "[SONJSsyntax][SONJSsyntax]");
			
		}
		System.out.println( str );
		patternTxt = "(\\[SONJSsyntax\\])([\\w\\W]*?)(\\[SONJSsyntax\\])";
		Integer syntaxCnt = 0;
		while( GliderTagPaserUtil.getMatchFind(str, patternTxt) ){
			// 처리한 syntax을 초기화 시킨다. 
			str = GliderTagPaserUtil.replaceFirstTag(str, patternTxt, "<pre class=\"brush: js\">"+syntaxList.get(syntaxCnt).toString().replaceAll("[$]", "\\\\\\$")+"</pre>");
			syntaxCnt++;
		}
		
		System.out.println( str );
		
		String aa = "a";
		String bb = "";
		for( int i=3; i>0; i-- ){
			bb = "";
			for( int b=0; b<=i; b++ ){
				bb += aa;
			}
			System.out.println(bb);
		}
		
		//\[!a\|(.*?)\]
		//System.out.println(str);
	}
	*/
	public List<Object> h1TagList = new ArrayList<Object>();
	
	@Test
	public void te(){
		String str = "\r\n"+
				 "[%]\r\n"+
				 "||hey|| ahae|| aa ||\r\n"+
				 "|hi|hea|jye|\r\n"+
				 "----hhhhi4\r\n"+
				"다음[syntax]내용2[syntax][syntax]내용3[syntax]";
		
		String patternTxt = "";
		String[] tableStr = str.split("\r\n");
		str = "";
		
		Map<String, String> hTagMap = null;
		for( int i=0; i<tableStr.length; i++ ){
			
			patternTxt = "(?<=\\|\\|)(.*?)(?=\\|\\|)";
			if( GliderTagPaserUtil.getMatchFind(tableStr[i], patternTxt, Pattern.MULTILINE) ){
				String imsi = GliderTagPaserUtil.replaceAllTag(tableStr[i], patternTxt, "tag  $2", Pattern.MULTILINE);
				System.out.println("imsi :: "+imsi);
			}
			
			
			
		}
		
		//System.out.println("str :: "+str);
		
	}
}









