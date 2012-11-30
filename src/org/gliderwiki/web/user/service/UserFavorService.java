/**
 * @FileName  : UserFavorService.java
 * @Project   : NightHawk
 * @Date      : 2012. 6. 11. 
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 : 사용자 즐겨찾기 
 */
package org.gliderwiki.web.user.service;

import java.util.List;

import org.gliderwiki.web.vo.MemberSessionVo;
import org.gliderwiki.web.vo.WikiFavoriteVo;


/**
 * @author yion
 *
 */
public interface UserFavorService {

	/**
	 * 내 즐겨찾기 목록 (공간)
	 * @param memberSessionVo
	 * @return
	 * @throws Throwable
	 */
	List<WikiFavoriteVo> getMyFavoriteSpaceList(Integer weUserIdx) throws Throwable;
	
	/**
	 * 내 즐겨찾기 목록 (위키)
	 * @param memberSessionVo
	 * @return
	 * @throws Throwable
	 */
	List<WikiFavoriteVo> getMyFavoriteWikiList(Integer weUserIdx) throws Throwable;

}
