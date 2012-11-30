/**
 * @FileName  : MetaInfoVo.java
 * @Project   : NightHawk
 * @Date      : 2012. 6. 13. 
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 : 사용자 선택 리스트용 알람 목록 
 */
package org.gliderwiki.web.vo;

/**
 * @author yion
 *
 */
public class MetaInfoVo {

	/**
	 * 메타 인덱스
	 */
	private Integer weMetaIdx;
	
	/**
	 * 메타 타입
	 */
	private String weMetaType;
	
	/**
	 * 메타 업무구분
	 */
	private String weMetaDomain;
	
	/**
	 * 메타 업무 설명
	 */
	private String weMetaDesc;
	
	/**
	 * 메타 테이블 
	 */
	private String weMetaTableName;
	
	/**
	 * 메타 테이블 키 
	 */
	private String weMetaTableKey;
	
	/**
	 * 사용여부
	 */
	private String weUseYn;

	/**
	 * 사용자 선택 여부 
	 */
	private String userCheckYn;
	
	/**
	 * @return the weMetaIdx
	 */
	public Integer getWeMetaIdx() {
		return weMetaIdx;
	}

	/**
	 * @param weMetaIdx the weMetaIdx to set
	 */
	public void setWeMetaIdx(Integer weMetaIdx) {
		this.weMetaIdx = weMetaIdx;
	}

	/**
	 * @return the weMetaType
	 */
	public String getWeMetaType() {
		return weMetaType;
	}

	/**
	 * @param weMetaType the weMetaType to set
	 */
	public void setWeMetaType(String weMetaType) {
		this.weMetaType = weMetaType;
	}

	/**
	 * @return the weMetaDomain
	 */
	public String getWeMetaDomain() {
		return weMetaDomain;
	}

	/**
	 * @param weMetaDomain the weMetaDomain to set
	 */
	public void setWeMetaDomain(String weMetaDomain) {
		this.weMetaDomain = weMetaDomain;
	}

	/**
	 * @return the weMetaDesc
	 */
	public String getWeMetaDesc() {
		return weMetaDesc;
	}

	/**
	 * @param weMetaDesc the weMetaDesc to set
	 */
	public void setWeMetaDesc(String weMetaDesc) {
		this.weMetaDesc = weMetaDesc;
	}

	/**
	 * @return the weMetaTableName
	 */
	public String getWeMetaTableName() {
		return weMetaTableName;
	}

	/**
	 * @param weMetaTableName the weMetaTableName to set
	 */
	public void setWeMetaTableName(String weMetaTableName) {
		this.weMetaTableName = weMetaTableName;
	}

	/**
	 * @return the weMetaTableKey
	 */
	public String getWeMetaTableKey() {
		return weMetaTableKey;
	}

	/**
	 * @param weMetaTableKey the weMetaTableKey to set
	 */
	public void setWeMetaTableKey(String weMetaTableKey) {
		this.weMetaTableKey = weMetaTableKey;
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
	 * @return the userCheckYn
	 */
	public String getUserCheckYn() {
		return userCheckYn;
	}

	/**
	 * @param userCheckYn the userCheckYn to set
	 */
	public void setUserCheckYn(String userCheckYn) {
		this.userCheckYn = userCheckYn;
	}	

	
}
