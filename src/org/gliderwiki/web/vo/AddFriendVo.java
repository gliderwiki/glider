/**
 * @FileName  : AddFriendVo.java
 * @Project   : NightHawk
 * @Date      : 2012. 6. 12. 
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 : 관심 인맥, 내가 추가한 인맥, 나를 추가한 인맥  
 */
package org.gliderwiki.web.vo;

/**
 * @author yion
 *
 */
public class AddFriendVo extends BaseObjectBean {

	/**
	 * 사용자 순번
	 */
	private Integer weUserIdx;
	
	/**
	 * 대상 사용자 순번 
	 */
	private Integer weTargetUserIdx;
	
	
	/**
	 * 사용자 닉네임 
	 */
	private String weUserNick;
	
	/**
	 * 추가일 
	 */
	private String weAddDate;
	
	/**
	 * 사용여부 
	 */
	private String weUseYn;
	
	/**
	 * 삭제일 
	 */
	private String weDelDate;

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
	 * @return the weTargetUserIdx
	 */
	public Integer getWeTargetUserIdx() {
		return weTargetUserIdx;
	}

	/**
	 * @param weTargetUserIdx the weTargetUserIdx to set
	 */
	public void setWeTargetUserIdx(Integer weTargetUserIdx) {
		this.weTargetUserIdx = weTargetUserIdx;
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
	 * @return the weAddDate
	 */
	public String getWeAddDate() {
		return weAddDate;
	}

	/**
	 * @param weAddDate the weAddDate to set
	 */
	public void setWeAddDate(String weAddDate) {
		this.weAddDate = weAddDate;
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

	/**
	 * @return the weDelDate
	 */
	public String getWeDelDate() {
		return weDelDate;
	}

	/**
	 * @param weDelDate the weDelDate to set
	 */
	public void setWeDelDate(String weDelDate) {
		this.weDelDate = weDelDate;
	}
	
	
	
}
