/**
 * @FileName  : RegexTest.java
 * @Project   : NightHawk
 * @Date      : 2012. 2. 5.
 * @작성자      : @author bluepoet

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.wiki.common.service.fileexport;


import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author bluepoet
 *
 */
public class RegexTest {
	Logger logger = LoggerFactory.getLogger(RegexTest.class);
	public static String url = "http://wiki.kwonnam.pe.kr/dokuwiki#서버_설정_마무리";

	@Test
	public void url_추출_정규식() {

		Pattern urlPattern = Pattern.compile("^(https?):\\/\\/([^:\\/\\s]+)(:([^\\/]*))?((\\/[^\\s/\\/]+)*)?\\/([^#\\s\\?]*)(\\?([^#\\s]*))?(#([가-힣-_]*$))?$");

		Matcher mc = urlPattern.matcher(url);

		if(mc.matches()){
		    for(int i=0;i<=mc.groupCount();i++) {
			    System.out.println("group("+i+") = "+mc.group(i));
		    }

		    assertThat(mc.group(2), is("wiki.kwonnam.pe.kr"));
		}else {
			logger.debug("no found!!");
		}
	}
}
