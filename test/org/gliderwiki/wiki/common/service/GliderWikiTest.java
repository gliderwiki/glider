/**
 * @FileName  : GliderWikiTest.java
 * @Project   : NightHawk
 * @Date      : 2012. 2. 7.
 * @작성자      : @author bluepoet

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.wiki.common.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author bluepoet
 *
 */
public class GliderWikiTest {
	Logger logger = LoggerFactory.getLogger(GliderWikiTest.class);

	@Test
	public void 별표_가운데문자열_처리() {
		String str = "지금은 알 수 없어 *그대aff2*";
		Pattern pattern = Pattern.compile("\\*([0-9a-zA-Z가-힣]*)\\*");
		Matcher match = pattern.matcher(str);

		for(int i=0; match.find(); i++) {
			logger.debug("## 정규식안의 문자열 빼내기 : {}", match.group(1));
		}
	}

	@Test
	public void 별표_볼드로_치환() {
		String str = "지금은 알 수 없어 *그대aff2* 떠나는 내 진심을*fefefefefefe럴마널더22323*";
		Pattern pattern = Pattern.compile("\\*([0-9a-zA-Z가-힣]*)\\*");
		Matcher match = pattern.matcher(str);

		while(match.find()) {
			logger.debug("## 일치하는 문자열 : {}", match.group());
			str = str.replace(match.group(), getHtml(match.group(), EsCharacter.BOLD));
		}

		logger.debug("## 별표를 볼드로 치환한 문자열 : {}", str);
	}

	@Test
	public void 이탤릭체_치환() {
		String str = "이젠 나도 _지쳤어_";
		Pattern p = Pattern.compile("\\_([0-9a-zA-Z가-힣]*)\\_");
		Matcher match = p.matcher(str);

		while(match.find()) {
			logger.debug("## 일치하는 문자열 : {}", match.group());
			str = str.replace(match.group(), getHtml(match.group(), EsCharacter.ITALIC));
		}

		logger.debug("## 언더바를 이탤릭체로 치환한 문자열 : {}", str);
	}

	@Test
	public void 볼드_이탤릭체_함께치환() {
		String str = "이젠 나도 _지쳤어_ 그냥 *힘차게223dfefef* 발돋움 하는거야!!";
		Pattern p = Pattern.compile("\\_([0-9a-zA-Z가-힣]*)\\_");
		Pattern p1 = Pattern.compile("\\*([0-9a-zA-Z가-힣]*)\\*");
		Matcher m = p.matcher(str);
		Matcher m1 = p1.matcher(str);

		while(m.find()) {
			str = str.replace(m.group(), getHtml(m.group(), EsCharacter.ITALIC));
		}

		while(m1.find()) {
			str = str.replace(m1.group(), getHtml(m1.group(), EsCharacter.BOLD));
		}

		logger.debug("## 볼드/이탤릭체 함께 치환한 문자열 : {}", str);
	}

	@Test
	public void 볼드_이탤릭체_함께치환2() {
		String str = "이젠 나도 _지쳤_어_ 그냥 *힘차게2**23dfefef* 발돋움 하는거야!!";
		Pattern p = Pattern.compile("\\_([0-9a-zA-Z가-힣]*)\\_|\\*([0-9a-zA-Z가-힣]*)\\*");
		Matcher m = p.matcher(str);
		String matchingStr  = null;

		while(m.find()) {
			logger.debug("## 일치하는 문자열 : {}", m.group());
			matchingStr = m.group();

			if(matchingStr.contains("_")) {
				str = str.replace(m.group(), getHtml(m.group(), EsCharacter.ITALIC));
			}

			if(matchingStr.contains("*")) {
				str = str.replace(m.group(), getHtml(m.group(), EsCharacter.BOLD));
			}
		}

		logger.debug("## 볼드/이탤릭체 함께 치환한 문자열2 : {}", str);
	}

	public String getHtml(String str, EsCharacter ch) {
		String result = null;

		switch (ch) {
		case BOLD:
			result = "<b>"+replaceChBlank(str,"\\*")+"</b>";
			break;
		case ITALIC:
			result = "<I>"+replaceChBlank(str,"\\_")+"</I>";
			break;
		default:
			break;
		}

		return result;
	}

	public String replaceChBlank(String str, String ch) {
		return str.replaceAll(ch, "");
	}

	public enum EsCharacter {
		BOLD, ITALIC;
	}
}
