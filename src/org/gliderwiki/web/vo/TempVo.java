package org.gliderwiki.web.vo;

import org.directwebremoting.annotations.DataTransferObject;

@DataTransferObject
public class TempVo extends BaseObjectBean {

	/**
	 *
	 */
	private static final long serialVersionUID = 2682495881701176011L;

	private String id;

	private String name;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	public TempVo(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public TempVo() {}
}
