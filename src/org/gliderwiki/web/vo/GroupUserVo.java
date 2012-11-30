/**
 * @FileName  : GroupUserVo.java
 * @Project   : NightHawk
 * @Date      : 2012. 6. 25. 
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.vo;

/**
 * @author yion
 *
 */
public class GroupUserVo extends BaseObjectBean {
	
	private Integer weUserIdx;
	
	private String weUserId;
	
	private String weUserNick;
	
	private String weInsDate;
	
	private Integer weGroupIdx;
	
	private String weUseYn;
	
	private String weUserName;

	public String getWeUserName() {
		return weUserName;
	}

	public void setWeUserName(String weUserName) {
		this.weUserName = weUserName;
	}

	/**
	 * @return the weUserIdx
	 */
	public Integer getWeUserIdx() {
		return weUserIdx;
	}

	/**
	 * @param weUserIdx the weUserIdx to set
	 */
	public void setWeUserIdx(Integer weUserIdx) {
		this.weUserIdx = weUserIdx;
	}

	/**
	 * @return the weUserId
	 */
	public String getWeUserId() {
		return weUserId;
	}

	/**
	 * @param weUserId the weUserId to set
	 */
	public void setWeUserId(String weUserId) {
		this.weUserId = weUserId;
	}

	/**
	 * @return the weUserNick
	 */
	public String getWeUserNick() {
		return weUserNick;
	}

	/**
	 * @param weUserNick the weUserNick to set
	 */
	public void setWeUserNick(String weUserNick) {
		this.weUserNick = weUserNick;
	}

	/**
	 * @return the weInsDate
	 */
	public String getWeInsDate() {
		return weInsDate;
	}

	/**
	 * @param weInsDate the weInsDate to set
	 */
	public void setWeInsDate(String weInsDate) {
		this.weInsDate = weInsDate;
	}

	/**
	 * @return the weGroupIdx
	 */
	public Integer getWeGroupIdx() {
		return weGroupIdx;
	}

	/**
	 * @param weGroupIdx the weGroupIdx to set
	 */
	public void setWeGroupIdx(Integer weGroupIdx) {
		this.weGroupIdx = weGroupIdx;
	}

	/**
	 * @return the weUseYn
	 */
	public String getWeUseYn() {
		return weUseYn;
	}

	/**
	 * @param weUseYn the weUseYn to set
	 */
	public void setWeUseYn(String weUseYn) {
		this.weUseYn = weUseYn;
	}
	
	
	
}
