/**
 * @FileName : WikiEngineDaoImpl.java
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
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;


/**
 *
 * @author jaeger
 */
@Repository("wikiEngineDao")
public class WikiEngineDaoImpl extends SqlSessionDaoSupport implements WikiEngineDao {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 위키 백업 저장
	 *
	 * @param weWiki
	 * @return
	 */
	@Override
	public int insertWeWikiBak(WeWiki weWiki) throws Throwable {
		return getSqlSession().insert("WikiManage.insertWeWikiBak", weWiki);
	}

	@Override
	public List<WeWikiBak> getListWikiRevision(WeWikiBak weWikiBak) throws Throwable {

		return getSqlSession().selectList("WikiManage.getListWikiRevision", weWikiBak);
	}

	@Override
	public WeWiki getOriginWiki(WeWiki weWiki) throws Throwable {

		return (WeWiki) getSqlSession().selectOne("WikiManage.getOriginWiki",  weWiki);
	}

}
