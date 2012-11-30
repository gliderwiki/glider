/**
 * @FileName  : WikiRegistService.java
 * @Project   : NightHawk
 * @Date      : 2012. 7. 4.
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.wiki.parser.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.gliderwiki.web.domain.WeSpace;
import org.gliderwiki.web.domain.WeTemplate;
import org.gliderwiki.web.domain.WeWiki;
import org.gliderwiki.web.domain.WeWikiBak;
import org.gliderwiki.web.vo.MemberSessionVo;


/**
 * @author yion
 *
 */
public interface WikiService {

	/**
	 * 위키의 모든 내용을 저장 한다.
	 * @param weWiki
	 * @param weSpace
	 * @param weTag
	 * @return 저장된 wiki의 id
	 * @throws Throwable
	 */
	int addWikiAllContents(WeWiki weWiki, WeSpace weSpace, String weTag) throws Throwable;

	/**
	 * 첨부 파일 존재 할 때  위키의 모든 내용을 저장 한다.
	 * @param wikiForm
	 * @param weSpace
	 * @param strNull
	 * @param weFileIdx
	 * @return
	 */
	int addWikiAllContents(WeWiki weWiki, WeSpace weSpace, String weTag, String[] weFileIdx, HttpServletRequest request) throws Throwable;


	/**
	 * 자식 위키의 모든 내용을 저장 한다.
	 * @param weWiki
	 * @param weSpace
	 * @param weTag
	 * @return 저장된 wiki의 id
	 * @throws Throwable
	 */
	int addSubWikiAllContents(WeWiki weWiki, WeSpace weSpace, String weTag) throws Throwable;

	/**
	 * 첨부 파일 존재 할 때  자식 위키의 모든 내용을 저장 한다.
	 * @param wikiForm
	 * @param weSpace
	 * @param strNull
	 * @param weFileIdx
	 * @return
	 */
	int addSubWikiAllContents(WeWiki weWiki, WeSpace weSpace, String weTag, String[] weFileIdx, HttpServletRequest request) throws Throwable;



	public WeWiki getWikiForEdit(WeWiki weWiki, MemberSessionVo loginUser) throws Throwable;

	public WeWiki getWikiInfo(WeWiki wiki) throws Throwable;

	public int enableWikiEditor(WeWiki wiki) throws Throwable;

	public List<WeWiki> getWikiList(int spaceIdx) throws Throwable;

	/**
	 * @param weFileIdx
	 */
	public int delWeWikiFile(Integer weFileIdx) throws Throwable;

	/**
	 * 위키의 내용을 백업 후 수정 저장한다. 첨부파일 없음
	 * @param wikiForm
	 * @param weSpace
	 * @param strNull
	 * @return
	 */
	int modifiedWikiAndSaveRevision(WeWiki wikiForm, WeSpace weSpace, String weTag, MemberSessionVo loginUser, HttpServletRequest request) throws Throwable;

	/**
	 * 위키의 내용을 백업 후 첨부파일 정보 저장 및 수정 저장한다.
	 * @param wikiForm
	 * @param weSpace
	 * @param strNull
	 * @param weFileIdx
	 * @param request
	 * @return
	 */
	int modifiedWikiAndSaveRevision(WeWiki wikiForm, WeSpace weSpace, String weTag, MemberSessionVo loginUser, HttpServletRequest request, String[] weFileIdx) throws Throwable;

	/**
	 * @param temp
	 * @return
	 */
	List<WeTemplate> getWeTemplateList(WeTemplate temp)  throws Throwable;

	/**
	 * @param temp
	 * @return
	 */
	List<WeTemplate> getWeTemplateIdx(WeTemplate temp) throws Throwable;


	int addFavorite(int loginUserIdx, int spaceIdx, int wikiIdx);

}
