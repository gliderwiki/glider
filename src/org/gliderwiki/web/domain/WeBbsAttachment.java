/**
 * @FileName  : WeBbsAttachment.java
 * @Project   : NightHawk
 * @Date      : 2012. 9. 14.
 * @작성자      : @author bluepoet

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.domain;

import java.io.Serializable;

import org.gliderwiki.framework.orm.sql.annotation.Column;
import org.gliderwiki.framework.orm.sql.annotation.Table;


/**
 * 공간게시판 첨부파일 도메인 클래스
 * @author bluepoet
 *
 */
@Table("WE_BBS_ATTACHMENT")
public class WeBbsAttachment implements Serializable {
	private static final long serialVersionUID = 1657882172455684679L;

	@Column(name = "we_bbs_idx")
	private Integer we_bbs_idx;

	@Column(name = "we_file_idx")
	private Integer we_file_idx;

	@Column(name = "we_use_yn")
	private String we_use_yn;

	public WeBbsAttachment() {}

	/**
	 * @return the we_bbs_idx
	 */
	public Integer getWe_bbs_idx() {
		return we_bbs_idx;
	}

	/**
	 * @param we_bbs_idx the we_bbs_idx to set
	 */
	public void setWe_bbs_idx(Integer we_bbs_idx) {
		this.we_bbs_idx = we_bbs_idx;
	}

	/**
	 * @return the we_file_idx
	 */
	public Integer getWe_file_idx() {
		return we_file_idx;
	}

	/**
	 * @param we_file_idx the we_file_idx to set
	 */
	public void setWe_file_idx(Integer we_file_idx) {
		this.we_file_idx = we_file_idx;
	}

	/**
	 * @return the we_use_yn
	 */
	public String getWe_use_yn() {
		return we_use_yn;
	}

	/**
	 * @param we_use_yn the we_use_yn to set
	 */
	public void setWe_use_yn(String we_use_yn) {
		this.we_use_yn = we_use_yn;
	}


}
