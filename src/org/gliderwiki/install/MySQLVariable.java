/**
 * @FileName  : MySQLVariable.java
 * @Project   : NightHawk
 * @Date      : Apr 7, 2013 
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.install;

import org.gliderwiki.web.vo.BaseObjectBean;

/**
 * @author yion
 *
 */
public class MySQLVariable extends BaseObjectBean {

	private String variable_name;
	
	private String value;
	
	private int rowCount;
	
	

	/**
	 * @return the rowCount
	 */
	public int getRowCount() {
		return rowCount;
	}

	/**
	 * @param rowCount the rowCount to set
	 */
	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

	/**
	 * @return the variable_name
	 */
	public String getVariable_name() {
		return variable_name;
	}

	/**
	 * @param variable_name the variable_name to set
	 */
	public void setVariable_name(String variable_name) {
		this.variable_name = variable_name;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
	
}
