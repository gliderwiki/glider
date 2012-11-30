/**
 * @FileName  : GroupInfoVo.java
 * @Project   : NightHawk
 * @Date      : 2012. 6. 22. 
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.vo;

/**
 * @author yion
 *
 */
public class GroupInfoVo extends BaseObjectBean {

	private Integer weGroupIdx;
	
	private String weGroupName;
	
	private String weGroupType;
	
	private String weGroupOwner;
	
	private String weGroupInfo;
	
	private String weInsDate;
	
	private String weUserNick;

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
	 * @return the weGroupName
	 */
	public String getWeGroupName() {
		return weGroupName;
	}

	/**
	 * @param weGroupName the weGroupName to set
	 */
	public void setWeGroupName(String weGroupName) {
		this.weGroupName = weGroupName;
	}

	/**
	 * @return the weGroupType
	 */
	public String getWeGroupType() {
		return weGroupType;
	}

	/**
	 * @param weGroupType the weGroupType to set
	 */
	public void setWeGroupType(String weGroupType) {
		this.weGroupType = weGroupType;
	}

	/**
	 * @return the weGroupOwner
	 */
	public String getWeGroupOwner() {
		return weGroupOwner;
	}

	/**
	 * @param weGroupOwner the weGroupOwner to set
	 */
	public void setWeGroupOwner(String weGroupOwner) {
		this.weGroupOwner = weGroupOwner;
	}

	/**
	 * @return the weGroupInfo
	 */
	public String getWeGroupInfo() {
		return weGroupInfo;
	}

	/**
	 * @param weGroupInfo the weGroupInfo to set
	 */
	public void setWeGroupInfo(String weGroupInfo) {
		this.weGroupInfo = weGroupInfo;
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
	
	
}
