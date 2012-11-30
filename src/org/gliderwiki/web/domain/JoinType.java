/**
 * @FileName  : JoinType.java
 * @Project   : NightHawk
 * @Date      : 2012. 7. 20. 
 * @작성자      : @author bluepoet

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.domain;

/**
 * @author bluepoet
 *
 */
public enum JoinType {
	INVITE("동료초대"), REQUEST("가입신청");
	
	private String name;
	
	JoinType(String name) {
		this.name = name;
	}
}
