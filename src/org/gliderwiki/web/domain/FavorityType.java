/**
 * @FileName  : FavorityType.java
 * @Project   : NightHawk
 * @Date      : 2012. 7. 17.
 * @작성자      : @author bluepoet

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.domain;

import org.directwebremoting.annotations.DataTransferObject;

/**
 * 즐겨찾기 타입
 *
 * @author bluepoet
 *
 */
@DataTransferObject
public enum FavorityType {
	SPACE("공간"), WIKI("위키");

	private String name;

	FavorityType(String name) {
		this.name = name;
	}
}
