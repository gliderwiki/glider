/**
 * @FileName  : WikiRegistDao.java
 * @Project   : NightHawk
 * @Date      : 2012. 7. 12.
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.wiki.parser.dao;

import org.gliderwiki.web.domain.WeWiki;

/**
 * @author yion
 *
 */
public interface WikiRegistDao {

	/**
	 * @param weWiki
	 * @return
	 */
	public String getMinDepthIdx(WeWiki weWiki) throws Throwable;

	/**
	 * @param weWiki
	 * @return
	 */
	public String getMaxDepthIdx(WeWiki weWiki) throws Throwable;

	/**
	 * @param weWiki
	 * @param minOrderIdx
	 */
	public int updateParentDepthIdx(WeWiki weWiki, String minDepthIdx) throws Throwable;

	/**
	 * @return
	 */
	public int getNextWikiIdx() throws Throwable;

	/**
	 * @param weFileIdx
	 * @param currIdx
	 * @return
	 */
	public int insertArrayFileList(String[] weFileIdx, int currIdx, int weWikiRevision) throws Throwable;

}
