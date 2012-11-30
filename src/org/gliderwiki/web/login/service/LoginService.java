/**
 * @FileName  : LoginService.java
 * @Project   : NightHawk
 * @Date      : 2012. 4. 12.
 * @작성자      : @author inamhui

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.login.service;

import javax.servlet.http.HttpServletRequest;

import org.gliderwiki.web.domain.WeUser;
import org.gliderwiki.web.vo.BaseObjectBean;
import org.gliderwiki.web.vo.MemberSessionVo;


/**
 * @author yion
 *
 */
public interface LoginService {

	/**
	 * 회원 기본, 회원 확장 정보 조회 (로그인 유저)
	 * @param id
	 * @return
	 * @throws Throwable
	 */
	MemberSessionVo getRowWeUserById(String id) throws Throwable;

	/**
	 * 특정 키로 넘어온 값을 암호화한다.
	 * @param passKey
	 * @param passVal
	 * @return
	 * @throws Throwable
	 */
	String getEncryptPassword(String passKey, String passVal) throws Throwable;

	/**
	 * 회원의 메일 주소로 인증페이지를 보낸다.
	 * @param we_user_idx
	 * @param we_user_id
	 */
	int sendMailAuth(WeUser weUser, HttpServletRequest request) throws Throwable;

	/**
	 * 회원 가입 후 인증 페이지로 들어왔을 경우 인증여부 필드를 업데이트 한다.
	 * @param we_user_idx
	 * @return
	 * @throws Throwable
	 */
	int updateUserAuth(String code) throws Throwable;

	/**
	 * 사용자의 패스워드를 찾아온다.
	 * @param userId
	 * @param userName
	 * @return
	 * @throws Throwable
	 */
	BaseObjectBean findUserPassword(String userId, String userNick, HttpServletRequest request) throws Throwable;

	/**
	 * 사용자의 아이디를 찾아온다.
	 * @param userPassword
	 * @param userName
	 * @return
	 * @throws Throwable
	 */
	String findUserIdDWR(String userPassword, String userNick) throws Throwable;


	/**
	 * 로그인하고 최종방문날짜를 업데이트한다.
	 * @param userIdx
	 */
	void updateLastVisitDate(int userIdx);
}
