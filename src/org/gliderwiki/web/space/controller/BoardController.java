/**
 * @FileName  : BoardController.java
 * @Project   : NightHawk
 * @Date      : 2012. 9. 14.
 * @작성자      : @author bluepoet

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.space.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.gliderwiki.util.PageNavigation;
import org.gliderwiki.util.RequestManager;
import org.gliderwiki.web.domain.WeBbs;
import org.gliderwiki.web.domain.WeWiki;
import org.gliderwiki.web.space.service.BoardService;
import org.gliderwiki.web.space.service.SpaceService;
import org.gliderwiki.web.system.argumentresolver.LoginUser;
import org.gliderwiki.web.vo.MemberSessionVo;
import org.gliderwiki.web.vo.SpaceInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * @author bluepoet
 *
 */
@Controller
@RequestMapping(value = "/space/{spaceIdx}/board")
public class BoardController {
	private static int BLOCK_LIST = 10;
	private static int BLOCK_PAGE = 10;

	@Resource(name = "boardService")
	private BoardService boardService;

	@Resource(name = "spaceService")
	private SpaceService spaceService;
	
	
	@Autowired
	private RequestManager requestManager;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String list(HttpServletRequest request, @PathVariable("spaceIdx") Integer spaceIdx, @LoginUser MemberSessionVo loginUser, ModelMap model) throws Throwable {
		int nowPage = 1;

		//현재 페이지값 세팅
		if(request.getParameter("page") != null) {
			nowPage = Integer.parseInt(request.getParameter("page"));

			if(nowPage < 1) {
				nowPage = 1;
			}
		}

		int totalCount = boardService.getArticleTotalCount(spaceIdx);
		PageNavigation pageNav = new PageNavigation(nowPage, totalCount, BLOCK_LIST, BLOCK_PAGE);

		// 메뉴에서 필요함 
		SpaceInfoVo spaceInfoVo = spaceService.getSpaceInfo(spaceIdx); 

		model.addAttribute("spaceInfo", spaceInfoVo);	
		model.addAttribute("spaceIdx", spaceIdx);
		// 페이징 관련 뷰에게 넘길 값을 지정한다
		model.addAttribute("pageIsPrev",	pageNav.isPrevPage());	// 이전페이지 블록의 존재유무
		model.addAttribute("pageIsNext",	pageNav.isNextPage());	// 다음페이지 블록의 존재유무
		model.addAttribute("pageStart",		pageNav.getStartPage());// 시작페이지 번호
		model.addAttribute("pageEnd",		pageNav.getEndPage());	// 종료페이지 번호
		model.addAttribute("nowPage",		nowPage);	// 현재페이지 번호
		model.addAttribute("list", boardService.getList(spaceIdx, pageNav.getStartRow(), pageNav.getBlockSize()));

		return "/space/board/list";
	}

	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public String createForm(@PathVariable("spaceIdx") Integer spaceIdx, @LoginUser MemberSessionVo loginUser,
			ModelMap model) {

		String temp = String.valueOf(System.nanoTime());
		String randomKey = temp.substring(temp.length() -8, temp.length()-4);
		
		
		WeBbs weBbs = new WeBbs();
		weBbs.setWe_space_idx(spaceIdx);

		model.addAttribute("spaceIdx", spaceIdx);
		model.addAttribute("WeBbs", weBbs);
		model.addAttribute("randomKey", randomKey);		// 인증키 생성 

		return "/space/board/form";
	}

	@RequestMapping(value = "/{boardIdx}/form", method = RequestMethod.GET)
	public String updateForm(@PathVariable("spaceIdx") Integer spaceIdx, @PathVariable("boardIdx") Integer boardIdx, @LoginUser MemberSessionVo loginUser,
			ModelMap model) {

		model.addAttribute("WeBbs", boardService.show(boardIdx));
		model.addAttribute("spaceIdx", spaceIdx);

		return "/space/board/form";
	}

	@RequestMapping(value = "/{boardIdx}", method = RequestMethod.GET)
	public String show(@PathVariable("boardIdx") Integer boardIdx, @LoginUser MemberSessionVo loginUser, ModelMap model) {
		boardService.updateHitCount(boardIdx);

		model.addAttribute("bbs", boardService.show(boardIdx));

		return "/space/board/show";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(HttpServletRequest request, @LoginUser MemberSessionVo loginUser, @Valid WeBbs weBbs) {

		weBbs.setWe_user_ip(requestManager.getRemoteAddress(request));
		weBbs.setWe_ins_user(loginUser.getWeUserIdx());
		weBbs.setWe_ins_name(loginUser.getWeUserNick());
		boardService.create(weBbs);

		return "redirect:/space/"+weBbs.getWe_space_idx()+"/board";
	}

	@RequestMapping(value = "/create", method = RequestMethod.PUT)
	public String update(@LoginUser MemberSessionVo loginUser, @Valid WeBbs weBbs) {

		boardService.update(weBbs);

		return "redirect:/space/"+weBbs.getWe_space_idx()+"/board";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(@LoginUser MemberSessionVo loginUser, @RequestParam("boardIdx") int boardIdx, @RequestParam("spaceIdx") int spaceIdx) {

		boardService.delete(boardIdx);

		return "redirect:/space/"+spaceIdx+"/board";
	}
}