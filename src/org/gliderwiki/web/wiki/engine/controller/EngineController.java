package org.gliderwiki.web.wiki.engine.controller;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.gliderwiki.framework.entity.service.EntityService;
import org.gliderwiki.util.DiffEngine;
import org.gliderwiki.util.DiffMatchPatch.Diff;
import org.gliderwiki.web.domain.WeWiki;
import org.gliderwiki.web.domain.WeWikiBak;
import org.gliderwiki.web.space.service.SpaceService;
import org.gliderwiki.web.vo.SpaceInfoVo;
import org.gliderwiki.web.wiki.engine.service.WikiEngineService;
import org.gliderwiki.web.wiki.parser.service.WikiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 위키 엔진 컨트롤러
 *
 * @author Jaeger
 */
@Controller
public class EngineController {

	private static Logger logger = LoggerFactory.getLogger(EngineController.class);

	@Autowired
	private WikiEngineService wikiEngineService;
	
	@Resource(name = "spaceService")
	private SpaceService spaceService;

	@SuppressWarnings("rawtypes")
	@Autowired
	private EntityService entityService;


	/**
	 * 리비전 리스트
	 *
	 * @param model
	 * @return
	 * @throws Throwable
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/wiki/engine/{wikiIdx}")
	public String list(
				Model model,
//				@LoginUser MemberSessionVo loginUser,
				@PathVariable(value = "wikiIdx" ) int wikiIdx) throws Throwable {
		// 위키 리스트
		List<WeWiki> wikiList = new LinkedList<WeWiki>();

		WeWiki weWiki = new WeWiki();
		weWiki.setWe_wiki_idx(wikiIdx);
		// 위키 조회
		weWiki = wikiEngineService.getOriginWiki(weWiki);

		WeWiki orgWiki = new WeWiki();
		
		String linkWithTitle = "<a href='/wiki/"+wikiIdx+"'>" +weWiki.getWe_wiki_title()+"</a>";
		orgWiki.setWe_wiki_title(linkWithTitle);
		orgWiki.setWe_ins_date(weWiki.getWe_ins_date());
		orgWiki.setWe_ins_user(weWiki.getWe_ins_user());
		orgWiki.setWe_wiki_revision(weWiki.getWe_wiki_revision());
		orgWiki.setWe_edit_text(weWiki.getWe_edit_text());
		orgWiki.setWe_user_nick(weWiki.getWe_user_nick());
		
		// 현재 리비전 위키 조회
		wikiList.add(orgWiki);

		WeWikiBak weWikiBak = new WeWikiBak();
		weWikiBak.setWe_wiki_idx(wikiIdx);
		// 위키 조회
		//List<WeWikiBak> wikiBakList = entityService.getListEntityOrdered(weWikiBak, "we_wiki_revision desc");
		
		
		List<WeWikiBak> wikiBakList = wikiEngineService.getListWikiRevision(weWikiBak);
		
		
		for (WeWikiBak wikiBak : wikiBakList) {
			WeWiki bakWiki = new WeWiki();
			bakWiki.setWe_wiki_title(wikiBak.getWe_wiki_title());
			bakWiki.setWe_ins_date(wikiBak.getWe_ins_date());
			bakWiki.setWe_ins_user(wikiBak.getWe_ins_user());
			bakWiki.setWe_wiki_revision(wikiBak.getWe_wiki_revision());
			bakWiki.setWe_edit_text(wikiBak.getWe_edit_text());
			bakWiki.setWe_user_nick(wikiBak.getWe_user_nick());

			// 백업된 리비전 위키들 조회
			wikiList.add(bakWiki);
		}
		
		model.addAttribute("wikiIdx", wikiIdx);
		model.addAttribute("weWiki", weWiki);
		model.addAttribute("wikiRev", weWiki.getWe_wiki_revision());
		model.addAttribute("wikiList", wikiList);

		return "/revision/diffList";
	}

	
	
	
	
	/**
	 * 위키 내용 비교
	 *
	 * @param model
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/wiki/diff/{wikiIdx}/{wikiRev}/{wikiBakRev}")
	public String diffContent(
						Model model,
//						@LoginUser MemberSessionVo loginUser,
						@PathVariable(value = "wikiIdx" ) int wikiIdx,
						@PathVariable(value = "wikiRev" ) int wikiRev,
						@PathVariable(value = "wikiBakRev") int wikiBakRev) {
		int wikiBaseRev = 0;
		WeWiki weWiki = null;
		WeWikiBak wikiBak = null;
		WeWikiBak weWikiBak = null;

		try {
			WeWiki paramWiki = new WeWiki();
			paramWiki.setWe_wiki_idx(wikiIdx);
//			paramWiki.setWe_space_idx(spaceIdx);
			paramWiki.setWe_wiki_revision(wikiRev);

			// 현재 위키(wikiIdx)의 최신 리비전을 체크한다.
			weWiki = (WeWiki) entityService.getRowEntity(paramWiki);

			// 예전 버전은 위키 테이블(WE_WIKI)에 존재하지 않는다.
			// 그런 경우에는 위키 백업 테이블(WE_WIKI_BAK)을 조회한다.
			if (weWiki == null) {
				wikiBak = new WeWikiBak();
				wikiBak.setWe_wiki_idx(wikiIdx);
				wikiBak.setWe_wiki_revision(wikiRev);
				wikiBak = (WeWikiBak) entityService.getRowEntity(wikiBak);

				// 위키 최신 버전
				weWiki = new WeWiki();
				weWiki.setWe_wiki_title(wikiBak.getWe_wiki_title());
				weWiki.setWe_wiki_text(wikiBak.getWe_wiki_text());
				weWiki.setWe_ins_date(wikiBak.getWe_ins_date());
				weWiki.setWe_wiki_revision(wikiBak.getWe_wiki_revision());
			} else {
				wikiBaseRev = weWiki.getWe_wiki_revision();
			}

			WeWikiBak paramWikiBak = new WeWikiBak();
			paramWikiBak.setWe_wiki_idx(wikiIdx);
			paramWikiBak.setWe_wiki_revision(wikiBakRev);
			// 위키 히스토리 조회
			weWikiBak = (WeWikiBak) entityService.getRowEntity(paramWikiBak);
		} catch (Throwable e) {
			logger.error("### Wiki Content Query Error : {}", e);
		}
		// [E N D]

		DiffEngine diffEngine = new DiffEngine();
		LinkedList<Diff> diffs = diffEngine.diffMain(weWikiBak.getWe_wiki_text(), weWiki.getWe_wiki_text());

		String wikiText = diffEngine.diffInsertHtml(diffs);
		String wikiBakText = diffEngine.diffDeleteHtml(diffs);

		
		model.addAttribute("weWiki", weWiki);
		model.addAttribute("wikiIdx", wikiIdx);
		model.addAttribute("wikiTitle", weWiki.getWe_wiki_title());
		model.addAttribute("wikiBaseRev", wikiBaseRev);
		// 위키 날짜
		model.addAttribute("wikiInsDate", weWiki.getWe_ins_date());
		model.addAttribute("wikiBakInsDate", weWikiBak.getWe_ins_date());
		// 위키 리비전
		model.addAttribute("wikiRevision", weWiki.getWe_wiki_revision());
		model.addAttribute("wikiBakRevision", weWikiBak.getWe_wiki_revision());
		// 위키 본문
		model.addAttribute("wikiText", wikiText);
		model.addAttribute("wikiBakText", wikiBakText);

		return "/revision/diffContent";
	}

	/**
	 * 컨텐츠 내용 교체
	 *
	 * @param model
	 * @return
	 * @throws Throwable
	 */
	@RequestMapping(value = "/wiki/replace/{wikiIdx}/{wikiRev}/{wikiBakRev}")
	public String replace(
						Model model,
//						@LoginUser MemberSessionVo loginUser,
						@PathVariable(value = "wikiIdx") int wikiIdx,
						@PathVariable(value = "wikiRev") int wikiRev,
						@PathVariable(value = "wikiBakRev") int wikiBakRev) throws Throwable {
/*
		=================================================
		 |                    | WE_BAK_IDX       |     |
		 | WE_WIKI_IDX        | WE_WIKI_IDX      | [*] |
		 | WE_SPACE_IDX       |                  |     |
		 | WE_WIKI_PARENT_IDX |                  |     |
		 | WE_WIKI_ORDER_IDX  |                  |     |
		 | WE_WIKI_DEPTH_IDX  |                  |     |
		 | WE_WIKI_TITLE      | WE_WIKI_TITLE    | [*] |
		 | WE_WIKI_TEXT       | WE_WIKI_TEXT     | [*] |
		 | WE_WIKI_MARKUP     | WE_WIKI_MARKUP   | [*] |
		 | WE_WIKI_REVISION   | WE_WIKI_REVISION | [*] |
		 | WE_WIKI_STATUS     | WE_WIKI_STATUS   | [*] |
		 | WE_WIKI_QUOTA      |                  |     |
		 | WE_WIKI_URL        |                  |     |
		 | WE_WIKI_AGREE      |                  |     |
		 | WE_WIKI_VIEW_CNT   |                  |     |
		 | WE_WIKI_PREV       |                  |     |
		 | WE_WIKI_NEXT       |                  |     |
		 | WE_USER_IP         | WE_USER_IP       | [*] |
		 | WE_WIKI_PROTECT    | WE_WIKI_PROTECT  | [*] |
		 | WE_EDIT_TEXT       | WE_EDIT_TEXT     | [*] |
		 | WE_USE_YN          | WE_USE_YN        | [*] |
		 | WE_INS_USER        | WE_INS_USER      | [*] |
		 | WE_INS_DATE        | WE_INS_DATE      | [*] |
		 | WE_UPD_USER        | WE_UPD_USER      | [*] |
		 | WE_UPD_DATE        | WE_UPD_DATE      | [*] |
		 |                    | WE_MOV_DATE      |     |
		 | WE_EDIT_YN         |                  |     |
		=================================================
*/

		// 새로운 위키 리비전 생성
		wikiEngineService.createRevision(wikiIdx, wikiRev, wikiBakRev);

		return "redirect:/wiki/engine/" + wikiIdx;
	}

}
