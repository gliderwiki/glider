/**
 * @FileName  : AuthorityType.java
 * @Project   : NightHawk
 * @Date      : 2012. 7. 2. 
 * @작성자      : @author bluepoet

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.domain;

import org.apache.commons.lang.StringUtils;

/**
 * @author bluepoet
 * 
 */
public enum AuthorityType {
	ALLGROUP("전체사용자허용"), GROUP("그룹선택"), USER("구성원선택");

	private String name;

	AuthorityType(String name) {
		this.name = name;
	}

	public AuthorityType getAuthorityType(String name) {
		AuthorityType[] types = values();
		for (AuthorityType type : types) {
			if (StringUtils.equals(name, type.name)) {
				return type;
			}
		}

		return null;
	}
}
