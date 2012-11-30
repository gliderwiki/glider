/**
 * @FileName : WikiEngineServiceImpl.java
 * @Project  : NightHawk
 * @Date     : 2012.07.18
 * @작성자   : jaeger
 * @변경이력 :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.wiki.engine.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.gliderwiki.framework.entity.dao.EntityDao;
import org.gliderwiki.framework.exception.GliderwikiException;
import org.gliderwiki.web.domain.WeWiki;
import org.gliderwiki.web.domain.WeWikiBak;
import org.gliderwiki.web.wiki.engine.dao.WikiEngineDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 *
 * @author JAEGER
 */
@Service("engineService")
public class WikiEngineServiceImpl implements WikiEngineService {

	Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private WikiEngineDao wikiEngineDao;

	@SuppressWarnings("rawtypes")
	@Autowired
	private EntityDao entityDao;


	/**
	 * 위키 리비전 생성
	 *
	 * @param wikiIdx
	 * @param wikiRev
	 * @param wikiBakRev
	 * @throws Throwable
	 */
	@Transactional
	@SuppressWarnings("unchecked")
	@Override
	public void createRevision(int wikiIdx, int wikiRev, int wikiBakRev) throws Throwable {
		WeWiki weWiki = new WeWiki();
		weWiki.setWe_wiki_idx(wikiIdx);
		weWiki.setWe_wiki_revision(wikiRev);
		weWiki  = (WeWiki) entityDao.getRowEntity(weWiki);

		// 위키 테이블에 존재하는 리비전과 위키BAK 테이블에 리비전과 교체를 해야 한다.
		if (weWiki == null) {
			throw new GliderwikiException("### GliderWiki : Wiki Document Not Exists.");
		}

		// WE_WIKI_BAK 테이블에 리비전(히스토리) 저장
		wikiEngineDao.insertWeWikiBak(weWiki);

		WeWikiBak weWikiBak = new WeWikiBak();
		weWikiBak.setWe_wiki_idx(wikiIdx);
		weWikiBak.setWe_wiki_revision(wikiBakRev);
		// WE_WIKI_BAK (위키 히스토리 리비전 조회)
		weWikiBak = (WeWikiBak) entityDao.getRowEntity(weWikiBak);

		// WE_WIKI (위키 현재 리비전 조회)
		weWiki.setWe_wiki_title(weWikiBak.getWe_wiki_title());
		weWiki.setWe_wiki_text(weWikiBak.getWe_wiki_text());
		weWiki.setWe_wiki_markup(weWikiBak.getWe_wiki_markup());
		weWiki.setWe_wiki_revision(weWiki.getWe_wiki_revision() + 1);
		weWiki.setWe_wiki_status(weWikiBak.getWe_wiki_status());
		weWiki.setWe_user_ip(weWikiBak.getWe_user_ip());
		weWiki.setWe_wiki_protect(weWikiBak.getWe_wiki_protect());
		weWiki.setWe_edit_text(weWikiBak.getWe_edit_text());
		weWiki.setWe_use_yn(weWikiBak.getWe_use_yn());
		weWiki.setWe_ins_user(weWikiBak.getWe_ins_user());
		weWiki.setWe_ins_date(weWikiBak.getWe_ins_date());
		weWiki.setWe_upd_user(weWikiBak.getWe_upd_user());
		weWiki.setWe_upd_date(weWikiBak.getWe_upd_date());
		// WE_WIKI (신규 리비전으로 수정)
		entityDao.updateEntity(weWiki);
	}


	@Override
	public List<WeWikiBak> getListWikiRevision(WeWikiBak weWikiBak) throws Throwable {
		
		return wikiEngineDao.getListWikiRevision(weWikiBak);
	}


	@Override
	public WeWiki getOriginWiki(WeWiki weWiki) throws Throwable {
				
		return wikiEngineDao.getOriginWiki(weWiki);
	}

}
