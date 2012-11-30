/**
 * @FileName  : Temp.java
 * @Project   : NightHawk
 * @Date      : 2011. 12. 25.
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.domain;

import org.gliderwiki.framework.orm.sql.annotation.Column;
import org.gliderwiki.framework.orm.sql.annotation.Table;
import org.gliderwiki.web.vo.BaseObjectBean;

import com.google.gson.annotations.Expose;

/**
 * @author yion
 *
 */
@Table("TEMP")
public class Temp extends BaseObjectBean{

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private static final long serialVersionUID = 1341805372689534733L;

	@Column(primaryKey=true, autoIncrement=true)
	@Expose
	private Integer id;

	@Column(name="name")
	private String name;

}
