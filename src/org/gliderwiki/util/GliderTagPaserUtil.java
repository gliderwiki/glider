/**
 * @FileName  : GliderTagPaserUtil.java
 * @Project   : NightHawk
 * @Date      : 2012. 11. 2. 
 * @작성자      : @author ganji

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.util;

/**
 * @author ganji
 *
 */
public class GliderTagPaserUtil {

	
	//HTML 태그 \r\n 줄바꿈 태그를 BR 태그로 변경
	public static String HTMLBREncode(String str)
	{
	    return str.replaceAll("\r\n", "\r\n<br class=\"br\"/>\r\n");
	}
	 
	//<,>를 &lt;,&gt;로 모두 치환
	public static String ReplaceHTMLSpecialChars(String str)
	{
		str = str.replaceAll("<", "&lt;");
		str = str.replaceAll(">", "&gt;");
		str = str.replaceAll("&", "&amp;");
	    return str;
	}
	
	//$을 치환가능하게 치환
	public static String ReplaceHTMLSpecialCharsDollar(String str)
	{
	    return str.replaceAll("[$]", "\\\\\\$");
	}
	 
	//허용한 HTML 태그를 제외하고 <,>를 &lt;,&gt;로 치환
	public static String ReplaceHTMLAll(String str)
	{
		str = HTMLBREncode(str);
		str = ReplaceHTMLSpecialChars(str);
		return str;
	}
	
	
}
