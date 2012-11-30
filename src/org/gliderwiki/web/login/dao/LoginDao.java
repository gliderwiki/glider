/**
 * @FileName  : LoginDao.java
 * @Project   : NightHawk
 * @Date      : 2012. 4. 12. 
 * @작성자      : @author inamhui

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.login.dao;

import org.gliderwiki.framework.exception.DBHandleException;
import org.gliderwiki.web.domain.WeUser;
import org.gliderwiki.web.vo.MemberSessionVo;

/**
 * @author inamhui
 *
 */
public interface LoginDao {
	/**
	 * 회원 정보를 조회한다 - 로그인 
	 * @param weUser
	 * @return
	 * @throws Throwable
	 */
	MemberSessionVo getRowWeUserById(WeUser weUser) throws Throwable;

	/**
	 * 회원 인증 여부 필드를 수정한다. - 회원인증페이지 
	 * @param weUser
	 * @return
	 */
	int updateUserAuth(WeUser weUser) throws Throwable;

	/**
	 * @return
	 */
	int getCurrentMailIdx() throws Throwable;
}
