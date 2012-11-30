/**
 * @FileName : WikiEngineService.java
 * @Project  : NightHawk
 * @Date     : 2012.07.18
 * @작성자   : jaeger
 * @변경이력 :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.wiki.engine.service;

import java.util.List;

import org.gliderwiki.web.domain.WeWiki;
import org.gliderwiki.web.domain.WeWikiBak;



/**
 * 위키 엔진 서비스
 *
 * @author JAEGER
 */
public interface WikiEngineService {

	/**
	 * 위키 리비전 생성
	 *
	 * @param wikiRev
	 * @param wikiBakRev
	 * @throws Throwable
	 */
	public void createRevision(int wikiIdx, int wikiRev, int wikiBakRev) throws Throwable;

	/**
	 * 위키 리비전 리스트를 조회한다. 
	 * @param weWikiBak
	 * @return
	 * @throws Throwable
	 */
	public List<WeWikiBak> getListWikiRevision(WeWikiBak weWikiBak) throws Throwable;

	/**
	 * @param weWiki
	 * @return
	 */
	public WeWiki getOriginWiki(WeWiki weWiki) throws Throwable;

}
