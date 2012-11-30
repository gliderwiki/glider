/**
 * @FileName  : UserProfileService.java
 * @Project   : NightHawk
 * @Date      : 2012. 5. 29. 
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.user.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.gliderwiki.web.domain.WeFile;
import org.gliderwiki.web.vo.BaseObjectBean;
import org.gliderwiki.web.vo.ProfileVo;


/**
 * @author yion
 *
 */
public interface UserProfileService {

	/**
	 * 회원 정보를 수정한다.
	 * 첨부파일이 존재할 경우 파일 핸들러 처리를 한 후 리턴한다. 
	 * 비밀번호가 변경 된 경우 비밀번호까지 업데이트 처리한다.
	 * @param profileVo
	 * @param request
	 * @return
	 * @throws Throwable
	 */
	BaseObjectBean updateUserProfile(ProfileVo profileVo, HttpServletRequest request) throws Throwable;

	/**
	 * @param weUserIdx
	 * @return
	 */
	int updateAwayUser(Integer weUserIdx) throws Throwable;
	
	/**
	 * @param weUserIdx
	 * @return
	 */	
}
