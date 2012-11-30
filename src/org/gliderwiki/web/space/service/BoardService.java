/**
 * @FileName  : BoardService.java
 * @Project   : NightHawk
 * @Date      : 2012. 9. 14.
 * @작성자      : @author bluepoet

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.space.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.gliderwiki.framework.entity.service.EntityService;
import org.gliderwiki.framework.exception.GliderwikiException;
import org.gliderwiki.web.domain.WeBbs;
import org.gliderwiki.web.space.dao.BoardDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author bluepoet
 *
 */
@Service("boardService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class BoardService {
	Logger logger = LoggerFactory.getLogger(BoardService.class);

	@Resource(name = "entityService")
	private EntityService entityService;

	@Resource(name = "boardDao")
	private BoardDao boardDao;

	public List<WeBbs> getList(int spaceIdx, int startRow, int blockList) {
		try {
			return boardDao.getList(spaceIdx, startRow, blockList);
		} catch (Throwable e) {
			throw new GliderwikiException("게시판리스트 데이터 가져오는 도중 에러발생!", e);
		}
	}

	public List<WeBbs> getRecentList(int spaceIdx) {
		try {
			return boardDao.getRecentList(spaceIdx);
		} catch (Throwable e) {
			throw new GliderwikiException("공간메인 게시판리스트 데이터 가져오는 도중 에러발생!", e);
		}
	}

	public int getArticleTotalCount(int spaceIdx) {
		try {
			return entityService.getCountEntity(new WeBbs(spaceIdx));
		} catch (Throwable e) {
			throw new GliderwikiException("게시판리스트 전체 데이터갯수 가져오는 도중 에러발생!", e);
		}
	}

	public WeBbs show(Integer bbsIdx) {
		WeBbs weBbs = new WeBbs();
		weBbs.setWe_bbs_idx(bbsIdx);

		try {
			return (WeBbs) entityService.getRowEntity(weBbs);
		} catch (Throwable e) {
			throw new GliderwikiException("게시판 게시물 데이터 가져오는 도중 에러발생!", e);
		}
	}

	@Transactional(readOnly = false)
	public void updateHitCount(Integer bbsIdx) {
		try {
			WeBbs weBbs = new WeBbs();
			weBbs.setWe_bbs_idx(bbsIdx);

			WeBbs updateBbs = (WeBbs) entityService.getRowEntity(weBbs);
			updateBbs.setWe_hit_count(updateBbs.getWe_hit_count() + 1);

			entityService.updateEntity(updateBbs);
		} catch (Throwable e) {
			throw new GliderwikiException("게시판 게시물 조회수 업데이트 가져오는 도중 에러발생!", e);
		}
	}

	@Transactional(readOnly = false)
	public int delete(Integer bbsIdx) {
		try {
			WeBbs weBbs = new WeBbs();
			weBbs.setWe_bbs_idx(bbsIdx);
			WeBbs originWeBbs = (WeBbs) entityService.getRowEntity(weBbs);

			originWeBbs.setWe_use_yn("N");
			return entityService.updateEntity(originWeBbs);
		} catch (Throwable e) {
			throw new GliderwikiException("게시판 게시물 삭제 도중 에러발생!", e);
		}
	}

	@Transactional(readOnly = false)
	public int create(WeBbs weBbs) {
		WeBbs newWeBbs = new WeBbs(weBbs.getWe_space_idx(), weBbs.getWe_user_ip(), weBbs.getWe_bbs_title(),
				weBbs.getWe_bbs_text(), weBbs.getWe_ins_user(), weBbs.getWe_ins_name());

		try {
			return entityService.insertEntity(newWeBbs);
		} catch (Throwable e) {
			throw new GliderwikiException("게시판 게시물 저장 도중 에러발생!", e);
		}
	}

	@Transactional(readOnly = false)
	public int update(WeBbs newWeBbs) {

		try {
			WeBbs weBbs = new WeBbs();
			weBbs.setWe_bbs_idx(newWeBbs.getWe_bbs_idx());
			WeBbs originWeBbs = (WeBbs) entityService.getRowEntity(weBbs);

			originWeBbs.setWe_bbs_title(newWeBbs.getWe_bbs_title());
			originWeBbs.setWe_bbs_text(newWeBbs.getWe_bbs_text());
			originWeBbs.setWe_upd_user(newWeBbs.getWe_upd_user());
			originWeBbs.setWe_upd_date(new Date());

			return entityService.updateEntity(originWeBbs);
		} catch (Throwable e) {
			throw new GliderwikiException("게시판 게시물 수정 도중 에러발생!", e);
		}
	}

}