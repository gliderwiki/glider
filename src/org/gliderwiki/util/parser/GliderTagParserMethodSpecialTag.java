/**
 * @FileName  : GliderTagParserMethodSpecialTag.java
 * @Project   : NightHawk
 * @Date      : 2012. 11. 21. 
 * @작성자      : @author ganji

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.util.parser;

import java.util.ArrayList;
import java.util.List;

import org.gliderwiki.util.GliderTagPaserUtil;

/**
 * @author ganji
 *
 */
public class GliderTagParserMethodSpecialTag {

	public List<String> syntaxList = new ArrayList<String>();
	
	/**
	 * 1. falg는 syntax의 처음호출인지 마지막 호출인지 파악. ( flag == true 처음, flag == false 마지막)
	 * 2. 처음호출시에는 syntax 안에 내용을 잘라서 보관
	 * 3. 마지막호출시에는 따로 보관한 내용을 syntax 다시 넣어준다.
	 * @param str
	 * @param falg
	 * @return
	 */
	public String getSYNTAX(String str, Boolean falg ){
		String patternTxt = null;
		
		if( falg ){
			patternTxt = "(\\[syntax\\])([\\w\\W]*?)(\\[syntax\\])";
		}else{
			patternTxt = "(\\[SONJSsyntax\\])([\\w\\W]*?)(\\[SONJSsyntax\\])";
		}
		
		Integer syntaxCnt = 0;
		while( GliderTagPaserUtil.getMatchFind(str, patternTxt) ){
			
			if( falg ){
				// syntax 내용을 잘라내어 보관한다.
				String syntax = GliderTagPaserUtil.getFirstTag(str, patternTxt);
				syntax = GliderTagPaserUtil.getFirstReturnTag(syntax, patternTxt, "$2");
				syntaxList.add(syntax);
				
				// 처리한 syntax을 초기화 시킨다. 
				str = GliderTagPaserUtil.replaceFirstTag(str, patternTxt, "[SONJSsyntax][SONJSsyntax]");
				
			}else{
				// syntax 내용을 다시 붙여 넣는다.  
				str = GliderTagPaserUtil.replaceFirstTag(str, patternTxt, "<pre class=\"brush: js\">"+syntaxList.get(syntaxCnt).toString().replaceAll("[$]", "\\\\\\$")+"</pre>");
				syntaxCnt++;
			}
			
		}
		
		return str;
	}
	
	public String getLINE(String str){
		str = linePaser(str, "#");
		str = linePaser(str, "-");
		return str;
	}
	
	public String getGRAPH(String str){

		return str;
	}
	
	public String getH1(String str){

		return str;
	}
	
	public String getTABLE(String str){

		return str;
	}
	
	/**
	 * 1. str은 wiki 내용
	 * 2. patternMainTxt 는 Line에 사용되는 구분자 하나만! ( ex. #, - )
	 * @param str
	 * @param patternMainTxt
	 * @return
	 */
	private String linePaser(String str, String patternMainTxt){
		
		// wiki 내용을 한줄씩 잘라서 배열에 담는다.
		String[] strLine = str.split("\r\n");
		// patternMainTxt 값에 따른 tag 값을 설정한다.
		String tag = null;
		str = "";
		
		if( "-".equals(patternMainTxt.trim()) ){
			tag = "ol";
		}else{
			tag = "ul";
		}
		
		
		String lvl3 = patternMainTxt+patternMainTxt+patternMainTxt+patternMainTxt;
		String lvl2 = patternMainTxt+patternMainTxt+patternMainTxt;
		String lvl1 = patternMainTxt+patternMainTxt;
		Integer chk = 0;
		String afterPattern = null;
		String beforePattern = null;
		for( int i=0; i<strLine.length; i++ ){
			beforePattern = afterPattern;
			
			if( GliderTagPaserUtil.getMatchFind(strLine[i], lvl3) ){
				afterPattern = lvl3;
				strLine[i] = GliderTagPaserUtil.getFirstReturnTag(strLine[i], "(^"+lvl3+")(.*)", "<li class=\"lv3\">$2</li>");
				
			}else if( GliderTagPaserUtil.getMatchFind(strLine[i], lvl2) ){
				afterPattern = lvl2;
				strLine[i] = GliderTagPaserUtil.getFirstReturnTag(strLine[i], "(^"+lvl2+")(.*)", "<li class=\"lv2\">$2</li>");
				
			}else if( GliderTagPaserUtil.getMatchFind(strLine[i], lvl1) ){
				afterPattern = lvl1;
				strLine[i] = GliderTagPaserUtil.getFirstReturnTag(strLine[i], "(^"+lvl1+")(.*)", "<li class=\"lv1\">$2</li>");
			}else{
				afterPattern = null;
			}
			
			// 최초
			if( afterPattern !=null  && beforePattern == null  ){
				chk ++;
				strLine[i] = "<"+tag+">\n"+strLine[i];
			
			// 하위depth로 내려갈때 태그를 만든다.
			}else if( afterPattern !=null  &&  afterPattern.length() > beforePattern.length() ){
				chk ++;
				strLine[i] = "<"+tag+">\n"+strLine[i];
			
			// 상위depth로 내려갈때 태그를 닫아준다.
			}else if( afterPattern !=null  &&  beforePattern.length() - afterPattern.length() > 0 ){
				Integer depth = beforePattern.length() - afterPattern.length();
				for( int j=0; j< depth; j++){
					chk = chk-1 ;
					strLine[i] = "</"+tag+">\n"+strLine[i];
				}
				
			// Line 태그가 종료시 닫히지 않은 태그를 닫아준다.
			}else if(  afterPattern == null &&  beforePattern != null ){
				for( int j=0; j< chk; j++){
					strLine[i] = "</"+tag+">\n"+strLine[i];
				}
				chk = 0;
			}
			
			str += strLine[i]+"\r\n";
			
		}
		
		return str;
	}
}
