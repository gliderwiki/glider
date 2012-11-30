/**
 * @FileName  : CommonService.java
 * @Project   : NightHawk
 * @Date      : 2012. 7. 4.
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.wiki.common.service;

import java.util.List;

import org.gliderwiki.web.domain.FavorityType;
import org.gliderwiki.web.domain.WeFile;
import org.gliderwiki.web.domain.WeGroupInfo;
import org.gliderwiki.web.domain.WeMenu;
import org.gliderwiki.web.domain.WeProfile;
import org.gliderwiki.web.domain.WeSpace;
import org.gliderwiki.web.domain.WeTemplate;
import org.gliderwiki.web.domain.WeUser;
import org.gliderwiki.web.domain.WeWiki;
import org.gliderwiki.web.domain.WeWikiFile;
import org.gliderwiki.web.domain.WeWikiGraph;
import org.gliderwiki.web.domain.WeWikiLink;
import org.gliderwiki.web.domain.WeWikiNote;
import org.gliderwiki.web.domain.WeWikiSummary;
import org.gliderwiki.web.domain.WeWikiTag;
import org.gliderwiki.web.vo.WikiVo;


/**
 * @author yion
 *
 */
public interface CommonService {

	/**
	 * 키 기반으로 임시 파일 정보를 조회한다.
	 * @param weFileIdx
	 * @return
	 * @throws Throwable
	 */
	public WeFile getUserFileInfo(Integer weFileIdx) throws Throwable;

	
	/**
	 * 위키 파일 정보를 조회한다. 
	 * @param weFileIdx
	 * @return
	 * @throws Throwable
	 */
	public WeWikiFile getWikiFileInfo(Integer weFileIdx) throws Throwable;
	
	/**
	 * 키 기반으로 사용자 기본 정보를 조회한다.
	 * @param weUserIdx
	 * @return
	 * @throws Throwable
	 */
	public WeUser getUserInfo(Integer weUserIdx) throws Throwable;

	/**
	 * 키 기반으로 사용자 프로필 정보를 조회한다.
	 * @param weUserIdx
	 * @return
	 * @throws Throwable
	 */
	public WeProfile getUserProfileInfo(Integer weUserIdx) throws Throwable;

	/**
	 * 키 기반으로 위키 공간 정보를 조회한다.
	 * @param weWikiSpaceIdx
	 * @return
	 * @throws Throwable
	 */
	public WeSpace getWikiSpaceInfoDWR(Integer weWikiSpaceIdx) throws Throwable;

	/**
	 * 키 기반으로 위키 기본 정보를 조회한다.
	 * @param weWikiIdx
	 * @return
	 * @throws Throwable
	 */
	public WeWiki getWikiInfo(Integer weWikiIdx) throws Throwable;

	/**
	 * 공간이나 위키를 즐겨찾기에 추가한다
	 * @param weUserIdx 로그인 사용자 idx
	 * @param type   즐겨찾기 타입(공간:SPACE, 위키:WIKI)
	 * @param addIdx 추가할 공간번호나 위키번호
	 *
	 */
	public int addFavorite(Integer weUserIdx, FavorityType type, int spaceIdx, int wikiIdx) throws Throwable;


	/**
	 * 삭제할 공간 및 위키 즐겨찾기
	 * @param weUserIdx
	 * @param type
	 * @param addIdx
	 * @return
	 * @throws Throwable
	 */
	public int delFavorite(Integer weUserIdx, FavorityType type, Integer addIdx) throws Throwable;

	/**
	 * 삭제할 공간 및 위키 즐겨찾기  DWR
	 * @param weUserIdx
	 * @param type
	 * @param addIdx
	 * @return
	 * @throws Throwable
	 */
	public int delFavoriteDWR(Integer weUserIdx, String type, Integer addIdx) throws Throwable;


	/**
	 * 인맥 끊기
	 * @param weUserIdx
	 * @param targetUserIdx
	 * @return
	 * @throws Throwable
	 */
	public int delRelationDWR(Integer weUserIdx, Integer targetUserIdx) throws Throwable;


	/**
	 * 알람 대상 지시와 대상자를 지정하여 알람을 보낼지 결정한다.
	 * @param we_login_user_idx 등록자 (로그인 세션 유저)
	 * @param we_meta_idx			  (알람 지시 명령어)
	 * @param we_target_user_idx	  (알람 대상 유저)
	 * @param we_wiki_idx	  (위키 순번)
	 * @param we_space_idx	  (공간 순번)
	 * @return
	 * @throws Throwable
	 */
	public int requestAlarmInfo(Integer we_login_user_idx, String we_user_nick, Integer we_meta_idx, Integer we_target_user_idx, Integer we_wiki_idx, Integer we_space_idx) throws Throwable;

	/**
	 * 안 읽은 알림을 모두 읽은것으로 처리한다
	 * @param userIdx
	 * @throws Throwable
	 */
	public void updateAllRead(int userIdx) throws Throwable;


	/**
	 * 실시간알림여부를 조회한다.
	 * @param userIdx
	 * @return
	 * @throws Throwable
	 */
	public String realNotiView(int userIdx);

	/**
	 * 실시간알림여부를 업데이트한다.
	 * @param userIdx
	 * @param isView
	 * @return
	 */
	public void changeRealTimeView(int userIdx, String isView) throws Throwable;

	/**
	 * @param weWikiIdx
	 * @return
	 */
	public List<WeWikiTag> getWeWikiTagList(Integer weWikiIdx, Integer weWikiRevision) throws Throwable;

	/**
	 * @param weWikiIdx
	 * @return
	 */
	public List<WeWikiNote> getWeWikiNoteList(Integer weWikiIdx, Integer weWikiRevision) throws Throwable;

	/**
	 * @param weWikiIdx
	 * @return
	 */
	public List<WeWikiLink> getWeWikiLinkList(Integer weWikiIdx, Integer weWikiRevision) throws Throwable;

	/**
	 * @param weWikiIdx
	 * @return
	 */
	public List<WeWikiFile> getWeWikiFileList(Integer weWikiIdx, Integer weWikiRevision) throws Throwable;

	/**
	 * @param weWikiIdx
	 * @return
	 */
	public List<WeWikiSummary> getWeWikiSummaryList(Integer weWikiIdx, Integer weWikiRevision) throws Throwable;

	/**
	 * @param weWikiIdx
	 * @return
	 */
	public WeWikiGraph getWeWikiGraph(Integer weWikiIdx, Integer weWikiRevision) throws Throwable;
	
	/**
	 * @param weFile
	 * @return
	 */
	public int insertWeFile(WeFile weFile) throws Throwable;


	/**
	 * @param weFileIdx
	 */
	public void delWeFile(Integer weFileIdx) throws Throwable;
	
	/**
	 * 회원 정보 검색 (로그인 유저 제외한 리스트 라이크 검색)
	 * @param loginUserIdx
	 * @param userNick
	 * @param userEmail
	 * @param userName
	 * @return
	 * @throws Throwable
	 */
	public List<WeUser> getWeUserList(Integer loginUserIdx, String userNick, String userEmail, String userName) throws Throwable;

	/**
	 * 그룹 상세 정보를 조회한다. 
	 * @param groupIdx
	 * @return
	 */
	public WeGroupInfo getWeGroupInfo(Integer groupIdx) throws Throwable;

	/**
	 * 위키 템플릿 조회 
	 * @param weTemplateIdx
	 * @return
	 */
	public WeTemplate getTemplateByIdx(Integer weTemplateIdx) throws Throwable;

	/**
	 * 대상 사용자의 포인트를 추가한다. 
	 * @param we_ins_user
	 * @param point
	 */
	public void updateUserPoint(Integer we_ins_user, int point) throws Throwable;
	
	public WeMenu getMenuInfo(Integer we_menu_idx) throws Throwable;


	/**
	 * 위키 본문 검색 
	 * @param weWikiText
	 * @return
	 * @throws Throwable
	 */
	public List<WikiVo> getWikiSearchList(String wiki_text) throws Throwable;
	
}
