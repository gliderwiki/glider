/**
 * @FileName  : CommonDao.java
 * @Project   : NightHawk
 * @Date      : 2012. 8. 10.
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.wiki.common.dao;

import java.util.List;

import org.gliderwiki.web.domain.WeAddFriend;
import org.gliderwiki.web.domain.WeFavorite;
import org.gliderwiki.web.domain.WeProfile;
import org.gliderwiki.web.vo.WikiVo;
import org.mockito.internal.stubbing.answers.ThrowsException;


/**
 * @author yion
 *
 */
public interface CommonDao {
	
	int updateFavorite(WeFavorite weFavorite) throws Throwable;
	
	/**
	 * @param weFavorite
	 * @return
	 */
	int delFavorite(WeFavorite weFavorite) throws Throwable;

	/**
	 * @param domain
	 * @return
	 */
	int delRelationDWR(WeAddFriend weAddFriend) throws Throwable;

	void notiAllRead(int userIdx) throws Throwable;

	String realNotiView(int userIdx) throws Throwable;

	void changeRealTimeView(int userIdx, String isView) throws Throwable;
	
	/**
	 * @param domain
	 */
	void updateUserPoint(WeProfile domain) throws Throwable;
	
	/**
	 * @param weWikiText
	 * @return
	 */
	List<WikiVo> getWikiSearchList(String wiki_text) throws Throwable;
}
