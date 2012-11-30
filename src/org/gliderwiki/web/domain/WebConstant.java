/**
 * @FileName  : WebConstant.java
 * @Project   : NightHawk
 * @Date      : 2012. 7. 28.
 * @작성자      : @author bluepoet

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.domain;

/**
 * @author bluepoet
 *
 */
public enum WebConstant {
	SUCCESS("success", "결과성공"),
	DUPLICATE("duplicate", "결과중복"),
	FAIL("fail", "실패");

	private String result;
	private String explain;

	private WebConstant(String result, String explain) {
		this.result = result;
		this.explain = explain;
	}

	public String getResult() {
		return this.result;
	}

	public String getExplain() {
		return this.explain;
	}

	public static WebConstant findByCode(String result) {
		WebConstant[] codes = values();

		for (WebConstant returnCode : codes) {
			if (result.equals(returnCode.getResult())) {
				return returnCode;
			}
		}

		return null;
	}
}
