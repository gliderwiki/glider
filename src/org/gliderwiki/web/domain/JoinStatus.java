/**
 * @FileName  : JoinStatus.java
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
public enum JoinStatus {
	APPROVE(0, "승인"),
	REQUEST(1, "대기"),
	CANCEL(2, "취소"),
	REJECT(3, "거절"),
	REAPPLICATION(4, "재신청");
	
	private int statusCode;
	private String statusName;
	
	JoinStatus(int statusCode, String statusName) {
		this.statusCode = statusCode;
		this.statusName = statusName;
	}
}
