/**
 * @FileName  : WePoint.java
 * @Project   : NightHawk
 * @Date      : 2012. 10. 5. 
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 : 회원 포인트 
 */
package org.gliderwiki.web.domain;

/**
 * @author yion
 * REGIST_WIKI : 위키 등록 포인트 
 * UPDATE_WIKI : 위키 수정 포인트
 * ADD_FAVORITE : 즐겨찾기 포인트 
 * AGREE_WIKI : 공감 추가 포인트
 * REPORT_WIKI : 위키 신고 포인트 
 * ADD_FRIEND : 인맥 추가 포인트 
 */
public enum WePoint {
	REGIST_WIKI(10), UPDATE_WIKI(2), ADD_FAVORITE(10), AGREE_WIKI(20), REPORT_WIKI(-30), ADD_FRIEND(5), WIKI_COMMENT(1), USER_VISIT(1);
	
	public int point;
	WePoint(int point) {
		this.point = point;
	}
}
