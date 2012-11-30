/**
 * @FileName  : AttachmentCategory.java
 * @Project   : NightHawk
 * @Date      : 2012. 1. 9.
 * @작성자      : @author bluepoet

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.domain;

/**
 * 첨부파일관련 enum 클래스
 * @author bluepoet
 *
 */
public enum AttachmentCategory {
	PUBLIC(new String[]{"jpg","gif","png"}),
	BOARD(new String[]{"jpg","gif"});

	private String[] acceptedExtensions;

	private AttachmentCategory(String[] acceptedExtensions) {
		this.acceptedExtensions = acceptedExtensions;
	}

	public String[] getAttachmentExtensions() {
		return acceptedExtensions;
	}
}
