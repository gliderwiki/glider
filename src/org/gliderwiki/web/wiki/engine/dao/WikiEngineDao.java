/**
 * @FileName : WikiEngineDao.java
 * @Project  : NightHawk
 * @Date     : 2012.07.18
 * @작성자   : @author jaeger
 * @변경이력 :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.wiki.engine.dao;

import java.util.List;

import org.gliderwiki.web.domain.WeWiki;
import org.gliderwiki.web.domain.WeWikiBak;



/**
 *
 * @author jaeger
 */
public interface WikiEngineDao {

	/**
	 * 위키 백업 저장
	 *
	 * @param weWiki
	 * @return
	 */
	public int insertWeWikiBak(WeWiki weWiki) throws Throwable;

	/**
	 * @param weWikiBak
	 * @return
	 */
	public List<WeWikiBak> getListWikiRevision(WeWikiBak weWikiBak) throws Throwable;

	/**
	 * @param weWiki
	 * @return
	 */
	public WeWiki getOriginWiki(WeWiki weWiki) throws Throwable;

}
