/**
 * @FileName  : TagServiceImpl.java
 * @Project   : NightHawk
 * @Date      : 2012. 6. 19.
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.wiki.keyword.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.gliderwiki.framework.util.DateUtil;
import org.gliderwiki.web.domain.WeSpace;
import org.gliderwiki.web.system.SystemConst;
import org.gliderwiki.web.vo.WikiTagVo;
import org.gliderwiki.web.vo.WikiVo;
import org.gliderwiki.web.wiki.keyword.dao.TagDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.Lists;

/**
 * @author yion
 *
 */
@Service("tagService")
public class TagServiceImpl implements TagService {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private TagDao tagDao;

	@Override
	public List<WikiTagVo> getTagList() throws Throwable {

		// 최근 3개월(-100일) 의 태그 목록을 조회한다.
		Date month = DateUtil.getOffsetDateToDate(DateUtil.getToday(), SystemConst.TAG_SEARCH_DATE);

		// 갯수는 50개로 한정한다.
		Integer maxLimit = SystemConst.TAG_MAX_SIZE;
		List<WikiTagVo> tagList = tagDao.getTagList(month, maxLimit);

		// 갯수가 적을 경우
		if (tagList.size() < 10) {
			// 1년으로 재조회
			month = DateUtil.getOffsetDateToDate(DateUtil.getToday(), SystemConst.TAG_SEARCH_YEAR);
			tagList = tagDao.getTagList(month, maxLimit);
		}

		return tagList;
	}

	@Override
	public List<Integer> getTagNameList(String tagName) throws Throwable {
		List<Integer> wikiListIdx = new ArrayList<Integer>(new HashSet<Integer>(tagDao.getTagNameList(tagName)));
		return wikiListIdx;
	}

	@Override
	public List<WikiVo> getWikiListByTagIdx(List<Integer> wikiListIdx) throws Throwable {
		return tagDao.getWikiListByTagIdx(wikiListIdx);
	}

	@Override
	public List<WeSpace> getSpaceIdxByWikiIdx(List<Integer> wikiListIdx) throws Throwable {
		return tagDao.getSpaceIdxByWikiIdx(wikiListIdx);
	}

	@Override
	public List<WeSpace> getSpaceInfoBySpaceIdx(List<Integer> spaceListIdx) throws Throwable {

		return tagDao.getSpaceInfoBySpaceIdx(spaceListIdx);
	}

	@Override
	public List<WikiVo> createWikiListWithTag(String tagName) throws Throwable {
		List<WikiVo> wikiInfoList = Lists.newArrayList();
		List<Integer> wikiIdxList = getNoDuplicateWikiIdxList(tagName);

		if (!CollectionUtils.isEmpty(wikiIdxList)) {
			wikiInfoList = tagDao.getWikiListByTagIdx(wikiIdxList);
		}

		return wikiInfoList;
	}

	private List<Integer> getNoDuplicateWikiIdxList(String tagName) throws Throwable {
		List<Integer> wikiIdxList = new ArrayList<Integer>(new HashSet<Integer>(tagDao.getTagNameList(tagName)));
		return wikiIdxList;
	}

	@Override
	public List<WeSpace> createSpaceListWithTag(List<WikiVo> wikiInfoList) throws Throwable {
		List<Integer> spaceIdxList = Lists.newArrayList();
		List<WeSpace> spaceInfoList = Lists.newArrayList();

		spaceIdxList = makeSpaceIdxList(wikiInfoList, spaceIdxList);

		if (!CollectionUtils.isEmpty(spaceIdxList)) {
			spaceInfoList = getSpaceInfoBySpaceIdx(spaceIdxList);
		}

		return spaceInfoList;
	}

	private List<Integer> makeSpaceIdxList(List<WikiVo> wikiInfoList, List<Integer> spaceIdxList) {
		if (!CollectionUtils.isEmpty(wikiInfoList)) {
			for (WikiVo wikiVo : wikiInfoList) {
				if (containsSpaceIdxUnderList(spaceIdxList, wikiVo.getWeSpaceIdx())) {
					spaceIdxList.add(wikiVo.getWeSpaceIdx());
				}
			}
		}

		return spaceIdxList;
	}

	private boolean containsSpaceIdxUnderList(List<Integer> spaceIdxList, int spaceIdx) {
		if (spaceIdxList.contains(spaceIdx)) {
			return false;
		}

		return true;
	}
}
