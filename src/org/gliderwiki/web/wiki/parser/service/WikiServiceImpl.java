/**
 * @FileName  : WikiRegistServiceImpl.java
 * @Project   : NightHawk
 * @Date      : 2012. 7. 4.
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.wiki.parser.service;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.gliderwiki.framework.entity.service.EntityService;
import org.gliderwiki.framework.exception.DBHandleException;
import org.gliderwiki.framework.exception.GliderwikiException;
import org.gliderwiki.framework.util.DateUtil;
import org.gliderwiki.framework.util.FileUploader;
import org.gliderwiki.framework.util.StringUtil;
import org.gliderwiki.util.GliderTagParser;
import org.gliderwiki.web.domain.FavorityType;
import org.gliderwiki.web.domain.WeFavorite;
import org.gliderwiki.web.domain.WeFile;
import org.gliderwiki.web.domain.WeSpace;
import org.gliderwiki.web.domain.WeTemplate;
import org.gliderwiki.web.domain.WeWiki;
import org.gliderwiki.web.domain.WeWikiBak;
import org.gliderwiki.web.domain.WeWikiFile;
import org.gliderwiki.web.domain.WeWikiGraph;
import org.gliderwiki.web.domain.WeWikiLink;
import org.gliderwiki.web.domain.WeWikiLog;
import org.gliderwiki.web.domain.WeWikiNote;
import org.gliderwiki.web.domain.WeWikiSummary;
import org.gliderwiki.web.domain.WeWikiTag;
import org.gliderwiki.web.vo.MemberSessionVo;
import org.gliderwiki.web.wiki.common.service.CommonService;
import org.gliderwiki.web.wiki.parser.dao.WikiDao;
import org.gliderwiki.web.wiki.parser.dao.WikiRegistDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;


/**
 * @author yion
 *
 */
@Service("wikiRegistService")
public class WikiServiceImpl implements WikiService {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	// 트랜잭션 처리 선언
	@Inject
	private PlatformTransactionManager tx;

	@SuppressWarnings("rawtypes")
	@Autowired
	private EntityService entityService;

	@Autowired
	private WikiRegistDao wikiRegistDao;

	@Autowired
	private WikiDao wikiDao;

	@Autowired
	private CommonService commonService;

	/**
	 * 전체 위키를 저장한다. 파일 업로드가 없는 경우 공통 저장으로 이동
	 */
	@SuppressWarnings("unchecked")
	@Override
	public int addWikiAllContents(WeWiki weWiki, WeSpace weSpace, String weTag) throws Throwable {
		return this.addWikiAllContents(weWiki, weSpace, weTag, null, null);
	}

	/**
	 * 공통 프로세스 위키 저장
	 */
	@Override
	public int addWikiAllContents(WeWiki weWiki, WeSpace weSpace, String weTag, String[] weFileIdx,
			HttpServletRequest request) throws Throwable {

		TransactionStatus status = tx.getTransaction(new DefaultTransactionDefinition());
		/**
		 * 위키 기본정보 저장 위키링크 저장 - 있을 경우 위키태그 저장 - 있을 경우 위키각주 저장 - 있을 경우 위키 컨텐츠요약
		 * 서머리 저장 - 있을 경우 위키작성로그 저장
		 */

		int currIdx = 0; // 저장될 위키의 시퀀스 번호를 따온다.
		int result = 0;

		int tagSize = 0; // "," 구분자로 split 할것
		String[] arrWeTag = null;
		if (weTag != null && !weTag.equals("")) {
			arrWeTag = weTag.split(",");
			tagSize = arrWeTag.length;
		}

		logger.debug("@@@@@@@@@@\n\n\n" + "" + "weWiki.getWe_wiki_text() : {}", weWiki.getWe_wiki_text());

		try {

			GliderTagParser gtp = new GliderTagParser();

			logger.debug("####### getWe_wiki_text() : {}", weWiki.getWe_wiki_text());

			Map<String, Object> resultMap = null;
			try {
				resultMap = gtp.parserMap(weWiki.getWe_wiki_text());
			} catch (Exception e) {
				e.printStackTrace();
			}

			List<Object> linkList = (List<Object>) resultMap.get("linkTagList");
			List<Object> headList = (List<Object>) resultMap.get("h1TagList");
			List<Object> noteList = (List<Object>) resultMap.get("noteTagList");
			Integer graphCnt = (Integer) resultMap.get("graphCnt");

			logger.debug("#####################################");
			logger.debug("linkList : {}", linkList.toString());
			logger.debug("headList : {}", headList.toString());
			logger.debug("noteList : {}", noteList.toString());
			logger.debug("#####################################");

			// 위키 기본정보 저장
			currIdx = this.getNextWikiIdx();

			logger.debug("###부모 번호가 있는지 : " + weWiki.getWe_wiki_parent_idx());

			// 부모 번호가 있다면 자식 위키 저장하는 로직임
			if (weWiki.getWe_wiki_parent_idx() == null || weWiki.getWe_wiki_parent_idx() == 0) {
				weWiki.setWe_wiki_parent_idx(currIdx); // 현재 입력되는 IDX와 같은 값을
														// 넣어준다.
			}
			weWiki.setWe_wiki_markup(resultMap.get("htmltag").toString()
					.replaceAll("\r\n", "\r\n<br class=\"br\"/>\r\n"));

			result = entityService.insertEntity(weWiki);

			// links 저장
			if (linkList != null) {
				result = this.insertLinks(result, linkList, currIdx, 1);
				if (result < 1) {
					logger.info("***위키저장  에러 롤백!!!! ");
					tx.rollback(status);
				}

			}
			logger.debug("### 링크 저장 후 result : " + result);

			if (headList != null) {
				result = this.insertSummary(result, headList, currIdx, 1);
				if (result < 1) {
					logger.info("***위키저장  에러 롤백!!!! ");
					tx.rollback(status);
				}

			}
			logger.debug("### 섬머리 저장 후 result  : " + result);

			if (noteList != null) {
				result = this.insertNote(result, noteList, currIdx, 1);
				if (result < 1) {
					logger.info("***위키저장 에러 롤백!!!! ");
					tx.rollback(status);
				}

			}
			logger.debug("### 각주 저장 후 result  : " + result);

			if (graphCnt != null && graphCnt != 0) {
				result = this.insertGraph(result, graphCnt, currIdx, 1);
				if (result < 1) {
					logger.info("***위키저장 에러 롤백!!!! ");
					tx.rollback(status);
				}

			}
			logger.debug("## 그래프 저장후 result  :  " + result);

			if (result >= 1) { // 연관 테이블 저장
				// 태그가 있을 경우
				if (tagSize > 0) {
					result = this.insertTags(weWiki, result, tagSize, arrWeTag, currIdx, 1); // 키워드
																								// 저장
					if (result < 1) {
						logger.info("***위키저장 에러 롤백!!!! ");
						tx.rollback(status);
					}
				}

				// 파일이 있을 경우
				if (weFileIdx != null) {
					logger.debug("### currIdx : " + currIdx);
					int retCount = 0;
					retCount = wikiRegistDao.insertArrayFileList(weFileIdx, currIdx, 1); // 최초
																							// 저장시
																							// 리비전은
																							// 1이다.

					logger.debug("### retCount :  " + retCount);

					// 정상적으로 저장이 되었다면 파일을 temp 에서 real 로 옮긴고 기존 we_file 임시 데이터는
					// 삭제한다.
					boolean fileRemove = false;
					WeFile weFile = new WeFile();
					weFile = commonService.getUserFileInfo(Integer.parseInt(weFileIdx[0]));
					String fromFilePath = request.getSession().getServletContext().getRealPath("/resource/temp");
					String toFilePath = request.getSession().getServletContext().getRealPath("/resource/real");
					File fromFile = new File(fromFilePath + weFile.getWe_file_save_path());
					File toFile = new File(toFilePath + weFile.getWe_file_save_path());

					fileRemove = FileUploader.copyTo(fromFile, toFile);

					if (fileRemove) {
						for (int i = 0; i < weFileIdx.length; i++) {
							WeFile delWeFile = new WeFile();
							delWeFile.setWe_file_idx(Integer.parseInt(weFileIdx[i]));
							entityService.deleteEntity(delWeFile);
						}
					}

				}

				// 저장 후 최종적으로 WE_WIKI_LOG 에 기록을 쌓는다 .
				WeWikiLog wikiLog = new WeWikiLog();
				wikiLog.setWe_wiki_idx(currIdx);
				wikiLog.setWe_wiki_revision(1);
				wikiLog.setWe_wiki_status("S");
				wikiLog.setWe_user_idx(weWiki.getWe_ins_user());
				wikiLog.setWe_wiki_action_type("I");

				int rtnLog = wikiDao.insertWikiLog(wikiLog);

				logger.debug("###rtnLog : " + rtnLog);

				result = currIdx;
				tx.commit(status);
			} else {
				result = -1;
				logger.info("위키저장 Error  (result=" + result + "}");
				tx.rollback(status);
				throw new DBHandleException("위키저장 에러", null, result);
			}

		} catch (DBHandleException e) {
			// 익셉션 처리 로직 확인
			result = -2;
			logger.info("***위키저장 Exception " + e.getMessage());
			logger.info("***위키저장 Exception " + e.getCause());
			logger.info("***위키저장 Exception " + e.getStatus());
			tx.rollback(status);
			throw new DBHandleException(e.getMessage(), e.getCause(), result);
		}

		return result;
	}

	/**
	 * 파일 업로드가 없는 경우 공통 저장 로직으로 이동
	 */
	@SuppressWarnings("unchecked")
	@Override
	public int addSubWikiAllContents(WeWiki weWiki, WeSpace weSpace, String weTag) throws Throwable {
		return this.addSubWikiAllContents(weWiki, weSpace, weTag, null, null);
	}

	/**
	 * 부모글이 있는 위키를 저장한다. 부모글 하위 글 타래 로직은 게시판의 답변 로직과 동일하다. parent_idx - 부모글 번호
	 * (글 그룹), depth_idx - 글의 스텝(깊이), order_idx - 글의 정렬 순서
	 * -------------------------------------------------------------------------
	 * idx, title, parent, order, depth
	 * -------------------------------------------------------------------------
	 * 1, 위키제목입니다. 1, 0, 0 2, 안녕하세요. 2, 0, 0 4, re : 안녕하세요. 2, 1, 1 : 2번의 답변이 될때
	 * 부모 순번을 물고 들어옴 6, re : re : 안녕하세 2, 2, 2 : 중간에 글이 들어오면, 현재 글 보다 같거나 높은
	 * order 값을 +1 로 증가 7, re : re : re : 안녕 2, 3, 3 : 답변의 깊이를 가져와 +1 5, re :
	 * 안녕하세요. 2, 4, 1 3, 글라이더 입니다. 3, 0, 0
	 * -------------------------------------------------------------------------
	 */
	public int addSubWikiAllContents(WeWiki weWiki, WeSpace weSpace, String weTag, String[] weFileIdx,
			HttpServletRequest request) throws Throwable {

		TransactionStatus status = tx.getTransaction(new DefaultTransactionDefinition());

		String maxStep = "";
		String minStep = "";

		// 현재 글기준으로 order가 큰값과 depth가 작거나 같은것중 가장 작은 order 값을 가져온다.
		// 답변 스텝을 구하기 위해 현재 글타래의 가장 작은수를 구해온다.
		minStep = wikiRegistDao.getMinDepthIdx(weWiki);

		// minDepth값이 없으면 Order 값만 증가하는 답변임
		logger.debug("minDepth : {}", minStep);

		int insertResult = 0;
		try {

			if (minStep == null || "".equals(minStep)) {
				// 답변 위키 글타래의 순번증가
				maxStep = wikiRegistDao.getMaxDepthIdx(weWiki); // 제일 밑으로 붙는 형태
																// Order + 1
																//
				logger.debug("제일 밑으로 붙는 형태의 maxDepth : " + maxStep);
				weWiki.setWe_wiki_depth_idx(Integer.parseInt(maxStep));

			} else { // 값이 있으면 Order 가 이미 존재하기 때문에 사이에 끼는 글이므로 depth 를 증가시키고 현재
						// order 와 같거나 높은 값을 +1 처리
				logger.debug("사이에 끼는 타입  minStep : " + minStep);
				// 자신이 속할 글 타래의 depth 값을 증가시키고, 관련 Order 값들을 증가시킴
				int result = wikiRegistDao.updateParentDepthIdx(weWiki, minStep);

				weWiki.setWe_wiki_depth_idx(Integer.parseInt(minStep));

			}

			// depth 처리
			weWiki.setWe_wiki_order_idx(weWiki.getWe_wiki_order_idx() + 1);

			logger.debug("##order 자동증가 : " + weWiki.getWe_wiki_order_idx());

			insertResult = this.addWikiAllContents(weWiki, weSpace, weTag, weFileIdx, request);

			if (insertResult > 0) {
				tx.commit(status);
			} else {
				insertResult = -1;
				logger.info("위키저장 Error  (insertResult=" + insertResult + "}");
				tx.rollback(status);
				throw new DBHandleException("자식 위키저장 에러", null, insertResult);
			}

		} catch (DBHandleException e) {
			// 익셉션 처리 로직 확인
			insertResult = -2;
			logger.info("***자식 위키저장 Exception " + e.getMessage());
			logger.info("***자식 위키저장 Exception " + e.getCause());
			logger.info("***자식 위키저장 Exception " + e.getStatus());
			tx.rollback(status);
			throw new DBHandleException(e.getMessage(), e.getCause(), insertResult);
		}

		return insertResult;

	}

	/**
	 * 현재 저장할 위키의 순번을 조회한다.
	 *
	 * @return
	 */
	private int getNextWikiIdx() throws Throwable {
		return wikiRegistDao.getNextWikiIdx();
	}

	/**
	 * 수정 가능한 위키 가져오기. 1.수정이 가능 할 시. 다른 사용자가 수정을 못하도록 we_edit_yn을 N으로 변경 해준다.
	 * 2.수정이 불가능 할 시 throw new WeWikiModifiedNotPermissionException이 발생한다. 현재
	 * 사용안함. - 추후 로직 점검 하여 오픈하도록 한다.
	 *
	 * @param WeWikiForm
	 * @return we_edit_yn = N으로 업데이트된 WeWiki
	 * @throws WeWikiModifiedNotPermissionException
	 */
	@SuppressWarnings("unchecked")
	public WeWiki getWikiForEdit(WeWiki weWiki, MemberSessionVo loginUser) throws Throwable {
		weWiki = (WeWiki) entityService.getRowEntity(weWiki);

		logger.debug("############## : " + weWiki.toString());

		// weWiki.validationWikiModified();
		// weWiki.toggleEditYn();
		//
		// weWiki.setWe_upd_date(new Date());
		// weWiki.setWe_upd_user(loginUser.getWeUserIdx());
		//
		// entityService.updateEntity(weWiki);

		return weWiki;
	}

	/**
	 * 위키 값 가져오기
	 *
	 * @param wiki
	 * @return
	 * @throws Throwable
	 */
	@SuppressWarnings("unchecked")
	public WeWiki getWikiInfo(WeWiki wiki) throws Throwable {
		return (WeWiki) entityService.getRowEntity(wiki);
	}

	/**
	 * 위키 수정 가능하도록 변경
	 *
	 * @param wiki
	 * @return 성공 여부 1:성공, 1이외 실패
	 */
	@SuppressWarnings("unchecked")
	@Override
	public int enableWikiEditor(WeWiki wiki) throws Throwable {
		wiki.setWe_edit_yn("Y");
		wiki.setWe_upd_date(new Date());
		return entityService.updateEntity(wiki);
	}

	@Override
	public List<WeWiki> getWikiList(int spaceIdx) throws Throwable {
		WeWiki searchWiki = new WeWiki();
		searchWiki.setWe_use_yn("Y");
		searchWiki.setWe_space_idx(spaceIdx);

		List<WeWiki> wikiList = (List<WeWiki>) wikiDao.getWikiList(searchWiki);

		return wikiList;
	}

	/**
	 * wiki tags insert
	 *
	 * @param weWiki
	 * @param status
	 * @param result
	 * @param tagSize
	 * @param arrWeTag
	 * @param currIdx
	 * @return
	 * @throws Throwable
	 */
	@SuppressWarnings("unchecked")
	private int insertTags(WeWiki weWiki, int result, int tagSize, String[] arrWeTag, int currIdx, int revision)
			throws Throwable {
		WeWikiTag weWikiTag = new WeWikiTag();
		weWikiTag.setWe_use_yn("Y");
		weWikiTag.setWe_wiki_idx(currIdx);
		weWikiTag.setWe_wiki_revision(revision);
		weWikiTag.setWe_ins_date(DateUtil.getTodayTime());

		for (int index = 0; index < tagSize; index++) {
			weWikiTag.setWe_tag(arrWeTag[index].trim());
			try {
				entityService.insertEntity(weWikiTag);
			} catch (DBHandleException e) {
				result = -3;
				logger.debug("###태그 에러 ");
				throw new DBHandleException("태그저장 에러", e.getCause(), result);
			}
		}
		return result;
	}

	/**
	 * 링크들 저장
	 *
	 * @param status
	 * @param result
	 * @param linkList
	 * @param currIdx
	 * @return
	 * @throws Throwable
	 */
	@SuppressWarnings("unchecked")
	private int insertLinks(int result, List<Object> linkList, int currIdx, int revision) throws Throwable {
		Map<String, Object> tagMap;
		int linkSize = linkList.size();
		for (int i = 0; i < linkSize; i++) {
			tagMap = (Map<String, Object>) linkList.get(i);
			WeWikiLink link = new WeWikiLink();

			link.setWe_wiki_idx(currIdx);
			link.setWe_wiki_revision(revision); // 최초 저장시 리비전은 무조 1임
			String fullTextLink = "<a href='" + (String) tagMap.get("tagUrl") + "' target='_blank' title='"
					+ (String) tagMap.get("tagTitle").toString().trim() + "'>"
					+ (String) tagMap.get("tagTitle").toString().trim() + "</a>";
			link.setWe_link_text(fullTextLink);
			link.setWe_link_title((String) tagMap.get("tagTitle"));
			link.setWe_link_url((String) tagMap.get("tagUrl"));
			link.setWe_use_yn("Y");

			logger.debug("###################   linkTagList    : " + link.toString());
			logger.debug("###################   htmltag    : " + tagMap.toString());

			// insert()
			try {
				entityService.insertEntity(link);
				result = 1;
			} catch (DBHandleException e) {
				result = -3;
				logger.debug("###링크저장 에러 ");
				throw new DBHandleException("링크저장 에러", e.getCause(), result);
			}
		}
		return result;
	}

	/**
	 * @param status
	 * @param result
	 * @param headList
	 * @param currIdx
	 * @return
	 */
	private int insertSummary(int result, List<Object> headList, int currIdx, int revision) throws Throwable {
		Map<String, Object> tagMap;
		int headSize = headList.size();

		for (int i = 0; i < headSize; i++) {

			tagMap = (Map<String, Object>) headList.get(i);
			WeWikiSummary summary = new WeWikiSummary();

			summary.setWe_wiki_idx(currIdx);
			summary.setWe_wiki_revision(revision); // 최초 저장시 리비전은 무조 1임
			summary.setWe_summary_tag((String) tagMap.get("tag"));
			summary.setWe_summary_title((String) tagMap.get("tagVal"));
			summary.setWe_use_yn("Y");

			logger.debug("###################   headList    : " + headList.toString());
			logger.debug("###################   htmltag    : " + tagMap.toString());

			// insert()
			try {
				entityService.insertEntity(summary);
				result = 1;
			} catch (DBHandleException e) {
				result = -4;
				logger.debug("###섬머리 저장  에러 ");
				throw new DBHandleException("섬머리 저자  에러", e.getCause(), result);
			}
		}
		return result;
	}

	/**
	 * @param status
	 * @param result
	 * @param noteList
	 * @param currIdx
	 * @return
	 */
	private int insertNote(int result, List<Object> noteList, int currIdx, int revision) throws Throwable {
		Map<String, Object> tagMap;
		int noteSize = noteList.size();

		for (int i = 0; i < noteSize; i++) {

			tagMap = (Map<String, Object>) noteList.get(i);
			WeWikiNote note = new WeWikiNote();

			note.setWe_wiki_idx(currIdx);
			note.setWe_wiki_revision(revision); // 최초 저장시 리비전은 무조 1임
			note.setWe_wiki_note_name((String) tagMap.get("tag"));
			note.setWe_wiki_note_desc((String) tagMap.get("tagVal"));
			note.setWe_use_yn("Y");

			logger.debug("###################   noteList    : " + noteList.toString());
			logger.debug("###################   htmltag    : " + tagMap.toString());

			// insert()
			try {
				entityService.insertEntity(note);
				result = 1;
			} catch (DBHandleException e) {
				result = -5;
				logger.debug("###각주저장  에러 ");
				throw new DBHandleException("각주 저장  에러", e.getCause(), result);
			}
		}
		return result;
	}

	/**
	 * @param status
	 * @param result
	 * @param headList
	 * @param currIdx
	 * @return
	 */
	private int insertGraph(int result, Integer graphCnt, int currIdx, int revision) throws Throwable {

		WeWikiGraph weWikiGraph = new WeWikiGraph();

		weWikiGraph.setWe_wiki_idx(currIdx);
		weWikiGraph.setWe_wiki_revision(revision);
		weWikiGraph.setWe_graph_cnt(graphCnt);
		weWikiGraph.setWe_use_yn("Y");

		// insert()
		try {
			entityService.insertEntity(weWikiGraph);
			result = 1;
		} catch (DBHandleException e) {
			result = -4;
			logger.debug("###그래프 저장  에러 ");
			throw new DBHandleException("그래프 저장  에러", e.getCause(), result);
		}

		return result;
	}

	@Override
	public int delWeWikiFile(Integer weFileIdx) throws Throwable {
		return wikiDao.delWeWikiFile(weFileIdx);
	}

	@Override
	public int modifiedWikiAndSaveRevision(WeWiki wikiForm, WeSpace weSpace, String weTag, MemberSessionVo loginUser,
			HttpServletRequest request) throws Throwable {
		return this.modifiedWikiAndSaveRevision(wikiForm, weSpace, weTag, loginUser, request, null);
	}

	@Override
	public int modifiedWikiAndSaveRevision(WeWiki weWiki, WeSpace weSpace, String weTag, MemberSessionVo loginUser,
			HttpServletRequest request, String[] weFileIdx) throws Throwable {
		TransactionStatus status = tx.getTransaction(new DefaultTransactionDefinition());
		// 원본 위키를 가져온다
		logger.debug("#####################################");
		logger.debug("넘어온 weWikiForm" + weWiki.toString());

		WeWiki originalWiki = new WeWiki();

		originalWiki = (WeWiki) commonService.getWikiInfo(weWiki.getWe_wiki_idx());

		int result = 0;

		int tagSize = 0; // "," 구분자로 split 할것
		String[] arrWeTag = null;
		if (weTag != null && !weTag.equals("")) {
			arrWeTag = weTag.split(",");
			tagSize = arrWeTag.length;
		}

		try {
			GliderTagParser gtp = new GliderTagParser();

			logger.debug("####### getWe_wiki_text() : {}", weWiki.getWe_wiki_text());

			Map<String, Object> resultMap = null;
			try {
				resultMap = gtp.parserMap(weWiki.getWe_wiki_text());
			} catch (Exception e) {
				e.printStackTrace();
			}

			List<Object> linkList = (List<Object>) resultMap.get("linkTagList");
			List<Object> headList = (List<Object>) resultMap.get("h1TagList");
			List<Object> noteList = (List<Object>) resultMap.get("noteTagList");
			Integer graphCnt = (Integer) resultMap.get("graphCnt");

			logger.debug("#####################################");
			logger.debug("linkList : {}", linkList.toString());
			logger.debug("headList : {}", headList.toString());
			logger.debug("noteList : {}", noteList.toString());
			logger.debug("#####################################");

			// we_wiki_bak 테이블에 저장

			logger.debug("#####################################");
			logger.debug("저장하기 전 originalWiki : " + originalWiki.toString());

			WeWikiBak wikiBak = new WeWikiBak();
			BeanUtils.copyProperties(originalWiki, wikiBak);

			wikiBak.setWe_edit_text(StringUtil.strNullToSpace(request.getParameter("weEditText"),
					loginUser.getWeUserNick() + "님이 수정")); // 수정사유
			wikiBak.setWe_mov_date(new Date());
			wikiBak.setWe_upd_date(new Date());
			wikiBak.setWe_upd_user(loginUser.getWeUserIdx());

			logger.debug("#####################################");
			logger.debug("저장하기 전 wikiBak : " + wikiBak.toString());

			result = wikiDao.insertSelectedWikiBak(wikiBak);

			int originRevision = originalWiki.getWe_wiki_revision();
			int revision = originalWiki.getWe_wiki_revision() + 1;

			// 업데이트시 추가적으로 필요한 세팅
			originalWiki.setWe_wiki_markup(resultMap.get("htmltag").toString()
					.replaceAll("\r\n", "\r\n<br class=\"br\"/>\r\n"));
			originalWiki.setWe_wiki_title(weWiki.getWe_wiki_title());
			originalWiki.setWe_wiki_text(weWiki.getWe_wiki_text());
			originalWiki.setWe_upd_date(new Date());
			originalWiki.setWe_upd_user(loginUser.getWeUserIdx());
			originalWiki.setWe_edit_text(StringUtil.strNullToSpace(request.getParameter("weEditText"),
					loginUser.getWeUserNick() + "님이 수정")); // 수정사유
			originalWiki.setWe_user_ip(request.getRemoteAddr());
			originalWiki.setWe_wiki_revision(revision);

			logger.debug("##########Wiki 메인정보 업데이트");
			result = entityService.updateEntity(originalWiki);

			WeWikiLink linkCountObj = new WeWikiLink();
			linkCountObj.setWe_wiki_idx(originalWiki.getWe_wiki_idx());
			linkCountObj.setWe_wiki_revision(originRevision);
			linkCountObj.setWe_use_yn("Y");

			int linkCount = entityService.getCountEntity(linkCountObj);
			if (linkCount > 0) {
				logger.debug("### 링크 목록 삭제 업데이트 : " + linkCount);
				result = wikiDao.delWeWikiLink(originalWiki.getWe_wiki_idx(), originRevision, "N");
				logger.debug("##1 result : " + result);
			}

			WeWikiSummary summaryCountObj = new WeWikiSummary();
			summaryCountObj.setWe_wiki_idx(originalWiki.getWe_wiki_idx());
			summaryCountObj.setWe_wiki_revision(originRevision);
			summaryCountObj.setWe_use_yn("Y");

			int summayCount = entityService.getCountEntity(summaryCountObj);
			if (summayCount > 0) {
				logger.debug("### 섬머리 목록 삭제 업데이트 : " + summayCount);
				result = wikiDao.delWeWikiSummary(originalWiki.getWe_wiki_idx(), originRevision, "N");
				logger.debug("##2 result : " + result);
			}

			WeWikiNote noteCountObj = new WeWikiNote();
			noteCountObj.setWe_wiki_idx(originalWiki.getWe_wiki_idx());
			noteCountObj.setWe_wiki_revision(originRevision);
			noteCountObj.setWe_use_yn("Y");

			int noteCount = entityService.getCountEntity(noteCountObj);
			if (noteCount > 0) {
				logger.debug("### 주석  목록 삭제 업데이트 : " + noteCount);
				result = wikiDao.delWeWikiNote(originalWiki.getWe_wiki_idx(), originRevision, "N");
				logger.debug("##3 result : " + result);
			}

			WeWikiTag tagCountObj = new WeWikiTag();
			tagCountObj.setWe_wiki_idx(originalWiki.getWe_wiki_idx());
			tagCountObj.setWe_wiki_revision(originRevision);
			tagCountObj.setWe_use_yn("Y");

			int tagCount = entityService.getCountEntity(tagCountObj);
			if (tagCount > 0) {
				logger.debug("### 태그  목록 삭제 업데이트 : " + tagCount);
				result = wikiDao.delWeWikiTag(originalWiki.getWe_wiki_idx(), originRevision, "N");
				logger.debug("##4 result : " + result);
			}

			WeWikiGraph wikiGraph = new WeWikiGraph();
			wikiGraph.setWe_wiki_idx(originalWiki.getWe_wiki_idx());
			wikiGraph.setWe_wiki_revision(originRevision);
			wikiGraph.setWe_use_yn("Y");

			int graphCount = entityService.getCountEntity(wikiGraph);
			if (graphCount > 0) {
				logger.debug("### 그래프  목록 삭제 업데이트 : " + graphCount);
				result = wikiDao.delWeWikiGraph(originalWiki.getWe_wiki_idx(), originRevision, "N");
				logger.debug("##4 result : " + result);
			}

			// links 저장
			if (linkList != null) {
				result = this.insertLinks(result, linkList, originalWiki.getWe_wiki_idx(), revision);
				if (result < 1) {
					logger.info("***위키수정 에러 롤백!!!! ");
					tx.rollback(status);
				}
			}

			logger.debug("### 링크 저장 후 result : " + result);

			if (headList != null) {
				result = this.insertSummary(result, headList, originalWiki.getWe_wiki_idx(), revision);
				if (result < 1) {
					logger.info("***위키수정 에러 롤백!!!! ");
					tx.rollback(status);
				}
			}

			logger.debug("### 섬머리 저장 후 result  : " + result);

			if (noteList != null) {
				result = this.insertNote(result, noteList, originalWiki.getWe_wiki_idx(), revision);
				if (result < 1) {
					logger.info("***위키수정 에러 롤백!!!! ");
					tx.rollback(status);
				}

			}

			logger.debug("### 각 저장 후 result  : " + result);

			if (graphCnt != null && graphCnt != 0) {
				result = this.insertGraph(result, graphCnt, originalWiki.getWe_wiki_idx(), revision);
				if (result < 1) {
					logger.info("***위키수정 에러 롤백!!!! ");
					tx.rollback(status);
				}
			}

			logger.debug("## 그래프 저장후 result  :  " + result);

			if (tagSize > 0) {
				result = this.insertTags(weWiki, result, tagSize, arrWeTag, originalWiki.getWe_wiki_idx(), revision); // 키워드
																														// 저장
				if (result < 1) {
					logger.info("***위키수정 에러 롤백!!!! ");
					tx.rollback(status);
				}

			}

			if (result >= 1) { // 연관 테이블 저장

				WeWikiFile fileCountObj = new WeWikiFile();
				fileCountObj.setWe_wiki_idx(originalWiki.getWe_wiki_idx());
				fileCountObj.setWe_wiki_revision(originRevision);
				fileCountObj.setWe_use_yn("Y");

				// 기존 파일 목록들은 업데이트 현재 버전으로 업데이트 한다.
				int fileCount = entityService.getCountEntity(fileCountObj);

				if (fileCount > 0) {
					logger.debug("### 파일  목록 버전 업데이트 : " + tagCount);
					result = wikiDao.updateWikiFile(originalWiki.getWe_wiki_idx(), revision, "Y");
					logger.debug("##4 result : " + result);
				}

				// 파일이 있을 경우
				if (weFileIdx != null) {

					int retCount = 0;
					retCount = wikiRegistDao.insertArrayFileList(weFileIdx, originalWiki.getWe_wiki_idx(), revision);

					logger.debug("### retCount :  " + retCount);

					// 정상적으로 저장이 되었다면 파일을 temp 에서 real 로 옮긴고 기존 we_file 임시 데이터는
					// 삭제한다.
					boolean fileRemove = false;
					WeFile weFile = new WeFile();

					weFile = commonService.getUserFileInfo(Integer.parseInt(weFileIdx[0]));

					logger.debug("###weFile : " + weFile.toString());

					String fromFilePath = request.getSession().getServletContext().getRealPath("/resource/temp");
					String toFilePath = request.getSession().getServletContext().getRealPath("/resource/real");

					try {
						File fromFile = new File(fromFilePath + weFile.getWe_file_save_path());
						File toFile = new File(toFilePath + weFile.getWe_file_save_path());

						fileRemove = FileUploader.copyTo(fromFile, toFile);

						if (fileRemove) {
							for (int i = 0; i < weFileIdx.length; i++) {
								WeFile delWeFile = new WeFile();
								delWeFile.setWe_file_idx(Integer.parseInt(weFileIdx[i]));
								entityService.deleteEntity(delWeFile);
							}
						}
					} catch (Exception e) {
						tx.rollback(status);
						result = -1;
						throw new DBHandleException("위키 파일 업로드 에러", null, result);
					}

				}
				// 저장 후 최종적으로 WE_WIKI_LOG 에 기록을 쌓는다 .
				WeWikiLog wikiLog = new WeWikiLog();
				wikiLog.setWe_wiki_idx(originalWiki.getWe_wiki_idx());
				wikiLog.setWe_wiki_revision(revision);
				wikiLog.setWe_wiki_status("S");
				wikiLog.setWe_user_idx(loginUser.getWeUserIdx());
				wikiLog.setWe_wiki_action_type("U");

				int rtnLog = wikiDao.insertWikiLog(wikiLog);

				logger.debug("###rtnLog : " + rtnLog);

				logger.debug("### 최종 저장 후 result  : " + result);

				tx.commit(status);
			} else {
				result = -1;
				logger.info("위키수정 Error  (result=" + result + "}");
				tx.rollback(status);
				throw new DBHandleException("위키수정 에러", null, result);
			}

		} catch (DBHandleException e) {
			// 익셉션 처리 로직 확인
			result = -2;
			logger.info("***위키수정 Exception " + e.getMessage());
			logger.info("***위키수정 Exception " + e.getCause());
			logger.info("***위키수정 Exception " + e.getStatus());
			tx.rollback(status);
			throw new DBHandleException(e.getMessage(), e.getCause(), result);
		}

		return result;
	}

	@Override
	public List<WeTemplate> getWeTemplateList(WeTemplate temp) throws Throwable {

		return wikiDao.getWeTemplateList(temp);
	}

	@Override
	public List<WeTemplate> getWeTemplateIdx(WeTemplate temp) throws Throwable {

		return wikiDao.getWeTemplateIdx(temp);
	}

	@Override
	public int addFavorite(int loginUserIdx, int spaceIdx, int wikiIdx) {

		WeFavorite searchFavorite = new WeFavorite();

		searchFavorite.setWe_favorite_type(FavorityType.WIKI);
		searchFavorite.setWe_space_idx(spaceIdx);
		searchFavorite.setWe_user_idx(loginUserIdx);
		searchFavorite.setWe_wiki_idx(wikiIdx);
		searchFavorite.setWe_use_yn("Y");

		try {
			WeFavorite weFavorite = (WeFavorite) entityService.getRowEntity(searchFavorite);

			if (weFavorite != null) {
				return -1;
			} else {
				return commonService.addFavorite(loginUserIdx, FavorityType.WIKI, spaceIdx, wikiIdx);
			}
		} catch (Throwable e) {
			throw new GliderwikiException(e);
		}
	}
}