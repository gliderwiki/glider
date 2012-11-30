/**
 * @FileName  : GliderWikiFunctions.java
 * @Project   : NightHawk
 * @Date      : 2012. 8. 13.
 * @작성자      : @author bluepoet

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.framework.util;

import java.io.UnsupportedEncodingException;

import org.springframework.web.util.UriUtils;

/**
 * 공통으로 사용하는 함수를 정의한다.
 * @author bluepoet
 *
 */
public class GliderWikiFunctions {
	public static final String DEFAULT_CHAR_ENCODING = "utf-8";

	public static String urlEncode(String string) {
		String encoded;
		try {
			encoded = UriUtils.encodeFragment(string, DEFAULT_CHAR_ENCODING);
		} catch (UnsupportedEncodingException e) {
			throw new IllegalArgumentException(e.getMessage(), e);
		}
		return encoded;
	}
}
