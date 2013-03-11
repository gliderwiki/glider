/**
 * @FileName  : GliderTagPaserUtil.java
 * @Project   : NightHawk
 * @Date      : 2012. 11. 2. 
 * @작성자      : @author ganji

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.gliderwiki.util.parser.GliderTagParserMethodBasicTag;
import org.gliderwiki.util.parser.GliderTagParserMethodSpecialTag;

/**
 * @author ganji
 *
 */
public class GliderTagPaserUtil {

	
	/**
	 * HTML 태그 \r\n 줄바꿈 태그를 BR 태그로 변경
	 * @param str
	 * @return
	 */
	public static String HTMLBREncode(String str)
	{
	    return str.replaceAll("\r\n", "\r\n<br class=\"br\"/>\r\n");
	}
	 
	/**
	 * <,>를 &lt;,&gt;로 모두 치환 
	 * @param str
	 * @return
	 */
	public static String ReplaceHTMLSpecialChars(String str)
	{
		str = str.replaceAll("<", "&lt;");
		str = str.replaceAll(">", "&gt;");
		//str = str.replaceAll("&", "&amp;");
	    return str;
	}
	
	/**
	 * $을 치환가능하게 치환
	 * @param str
	 * @return
	 */
	public static String ReplaceHTMLSpecialCharsDollar(String str)
	{
	    return str.replaceAll("[$]", "\\\\\\$");
	}
	 
	/**
	 * 허용한 HTML 태그를 제외하고 <,>를 &lt;,&gt;로 치환
	 * @param str
	 * @return
	 */
	public static String ReplaceHTMLAll(String str)
	{
		str = HTMLBREncode(str);
		str = ReplaceHTMLSpecialChars(str);
		return str;
	}
	
	/**
	 * 패턴에 검색한 태그중 가장 첫번째 tag를 가져온다.
	 * @param str
	 * @param patternTxt
	 * @return
	 */
	public static String getFirstTag(String str, String patternTxt){
		
		Pattern pattern = Pattern.compile(patternTxt);
		Matcher match = pattern.matcher(str);
		
		if ( match.find() ) {
			str = match.group();
		}else{
			str = null;
		}
		
		return str;
	}
	
	/**
	 * 태그를 잘라내서 첫번째 변환된 내용만 발라내서 리턴한다.
	 * @param str
	 * @param patternTxt
	 * @param replacement
	 * @return
	 */
	public static String getFirstReturnTag(String str, String patternTxt, String replacement){
		Pattern pattern = Pattern.compile(patternTxt);
		Matcher match = pattern.matcher(str);
		
		if ( match.find() ) {
			str = match.group();
			match = pattern.matcher(str);
			str = match.replaceFirst(replacement);
		}else{
			str = null;
		}
		
		return str;
	}
	
	/**
	 * 태그를 잘라내서 첫번째 변환된 내용만 발라내서 리턴한다.( Pattern flags를 사용시..)
	 * @param str
	 * @param patternTxt
	 * @param replacement
	 * @param flags
	 * @return
	 */
	public static String getFirstReturnTag(String str, String patternTxt, String replacement, int flags){
		Pattern pattern = Pattern.compile(patternTxt, flags);
		Matcher match = pattern.matcher(str);
		
		if ( match.find() ) {
			str = match.group();
			match = pattern.matcher(str);
			str = match.replaceFirst(replacement);
		}else{
			str = "";
		}
		
		return str;
	}
	
	/**
	 * 태그를 첫번째것만 잘라내서 처리할때 사용하는 메소드
	 * @param str
	 * @param patternTxt
	 * @param replacement
	 * @return
	 */
	public static String replaceFirstTag(String str, String patternTxt, String replacement){
		
		Pattern pattern = Pattern.compile(patternTxt);
		Matcher match = pattern.matcher(str);
		str = match.replaceFirst(replacement);
		
		return str;
	}
	
	/**
	 * 태그를 첫번째것만 잘라내서 처리할때 사용하는 메소드( Pattern flags를 사용시..)
	 * @param str
	 * @param patternTxt
	 * @param replacement
	 * @param flags
	 * @return
	 */
	public static String replaceFirstTag(String str, String patternTxt, String replacement, int flags){
		
		Pattern pattern = Pattern.compile(patternTxt, flags);
		Matcher match = pattern.matcher(str);
		str = match.replaceFirst(replacement);
		
		return str;
	}

	
	/**
	 * 패턴에 검색한 태그를 모두를 변환Txt에 맞게 변환후 리턴한다.
	 * @param str
	 * @param patternTxt
	 * @param replacement
	 * @return
	 */
	public static String replaceAllTag(String str, String patternTxt, String replacement){
		Pattern pattern = Pattern.compile(patternTxt);
		Matcher match = pattern.matcher(str);
		return match.replaceAll(replacement);
	}
	
	/**
	 * 패턴에 검색한 태그를 모두를 변환Txt에 맞게 변환후 리턴한다.( Pattern flags를 사용시..)
	 * @param str
	 * @param patternTxt
	 * @param replacement
	 * @param flags
	 * @return
	 */
	public static String replaceAllTag(String str, String patternTxt, String replacement, int flags){
		Pattern pattern = Pattern.compile(patternTxt, flags);
		Matcher match = pattern.matcher(str);
		return match.replaceAll(replacement);
	}
	
	/**
	 * 패턴에 검색한 태그가 하나이상 있으면 true를 리턴한다.
	 * @param str
	 * @param patternTxt
	 * @return
	 */
	public static boolean getMatchFind(String str, String patternTxt){
		Pattern pattern = Pattern.compile(patternTxt);
		Matcher match = pattern.matcher(str);
		return match.find();
	}
	
	/**
	 * 패턴에 검색한 태그가 하나이상 있으면 true를 리턴한다.( Pattern flags를 사용시..)
	 * @param str
	 * @param patternTxt
	 * @param flags
	 * @return
	 */
	public static boolean getMatchFind(String str, String patternTxt, int flags){
		Pattern pattern = Pattern.compile(patternTxt, flags);
		Matcher match = pattern.matcher(str);
		return match.find();
	}
	
	public static String replaceHtmlToParsing(String str){
		GliderTagParserMethodBasicTag basic = new GliderTagParserMethodBasicTag();
		GliderTagParserMethodSpecialTag tag = new GliderTagParserMethodSpecialTag();
		
		str = tag.getSYNTAX_TO_HTML(str, true);
		str = basic.getHTML_TAG(str);
		str = tag.getSYNTAX_TO_HTML(str, false);
		return str;
	}
	
	
}
