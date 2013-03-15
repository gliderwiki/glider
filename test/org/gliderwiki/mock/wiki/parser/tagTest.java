/**
 * @FileName  : GliderTagTest.java
 * @Project   : NightHawk
 * @Date      : 2012. 4. 28. 
 * @작성자      : @author ganji

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.mock.wiki.parser;

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
	
	@Test
	public void tt(){
		String aa = "\"ASDasd\"  ";
		System.out.println( aa.replaceAll("\"", "&#34;") );
	}
	
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
				 "||row:2#hey|| ahae||  col:4#aa ||\r\n"+
				 "|hi|hea|jye|\r\n"+
				 "----hhhhi4\r\n"+
				"다음[syntax]내용2[syntax][syntax]내용3[syntax]";
		
		String patternTxt = "";
		String[] tableStr = str.split("\r\n");
		str = "";
		
		Integer tableFlag = 0;
		for( int i=0; i<tableStr.length; i++ ){
			
			// || 태그와 | 태그를 확인해서 파싱한다.(기능상 중복기능이기떄문에 사용)
			Integer tableCnt = 0;
			for( int cnt=0; cnt<2; cnt++ ){
				String beforetag = null;
				String aftertag = null;
				String parsing = null;
				if( cnt == 0 ){
					beforetag = "\\\\|\\|";
					aftertag = "\\|\\|";
					parsing = "th";	
				}else{
					beforetag = "\\|";
					aftertag = "\\|";
					parsing = "td";
				}
				
				// || 태그(th)가 있는지 확인해서 있다면 테이블태그를 파싱한다.
				patternTxt = "((^"+beforetag+")(.*)("+aftertag+"))";
				String html = null;
				if( GliderTagPaserUtil.getMatchFind(tableStr[i], patternTxt, Pattern.MULTILINE) ){
					// 문자열에서 파싱할 대상태그만 잘라낸다.
					html = GliderTagPaserUtil.getFirstReturnTag(tableStr[i], patternTxt, "$1", Pattern.MULTILINE);
					
					// 잘라낸대상 태그에서 || 와 || 사이에 있는 태그를 th(tr) html으로 파싱한다.
					// 1. row 속성 태그 파싱
					html = GliderTagPaserUtil.replaceAllTag(html, "(?!"+beforetag+")([\\s]{0,}?)(row:(\\w+)#(.*?))(?="+aftertag+")", "<"+parsing+" rowspan=\"$3\">$1$4</"+parsing+">\n");
					// 2. col 속성 태그 파싱
					html = GliderTagPaserUtil.replaceAllTag(html, "(?!"+beforetag+")([\\s]{0,}?)(col:(\\w+)#(.*?))(?="+aftertag+")", "<"+parsing+" colspan=\"$3\">$1$4</"+parsing+">\n");
					// 3. 일반 속성 태그 파싱
					html = GliderTagPaserUtil.replaceAllTag(html, "(?!"+beforetag+")(.*?)(?="+aftertag+")", "<"+parsing+" class=\"\">$1</"+parsing+">\n");
					// 잘라낸대상 태그에서 || 태그를 삭제한다.
					html = GliderTagPaserUtil.replaceAllTag(html, "("+beforetag+")(?=.*)", "", Pattern.MULTILINE);
					
					html = "<tr>\n"+html+"</tr>\n";
					
					// 파싱이 끝난 태그를 적용한다.
					tableStr[i] = GliderTagPaserUtil.replaceAllTag(tableStr[i], patternTxt, html, Pattern.MULTILINE);
					
					// 테이블태그파싱이 실행이 되면 카운트를 증가한다.
					tableCnt++;
				}
			}
			
			if( tableCnt > 0){
				tableFlag++;	
			}
			
			// 테이블태그가 최초카운팅시 <table>태그를 넣는다.
			if( tableFlag == 1){
				tableStr[i] = "<table>\n"+tableStr[i];
				
			}else if( tableCnt == 0 && tableFlag > 0 ){
				tableStr[i] = "</table>\n"+tableStr[i];
				tableFlag = 0;
			}
			
			str += tableStr[i] + "\r\n";
			
		}
		
		System.out.println("str :: "+str);
		
	}
}









