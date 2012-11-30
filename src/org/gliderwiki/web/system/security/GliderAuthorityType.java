/**
 * @FileName  : GliderAuthorityType.java
 * @Project   : NightHawk
 * @Date      : 2012. 7. 30.
 * @작성자      : @author bluepoet

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.system.security;

/**
 * @author bluepoet
 *
 */
public enum GliderAuthorityType {
	ROLE_USER("로그인사용자"),
	ROLE_SPACE_ADMIN("공간개설권한자"),
	ROLE_MANAGER("운영관리자"),
	ROLE_SUPER_ADMIN("슈퍼관리자");

	private String description;

	GliderAuthorityType(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
