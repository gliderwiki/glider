package org.gliderwiki.framework.orm.sql.util;


public class Search {

	public static final String SEARCH_TYPE_SUBJECT = "SUBJECT";
	public static final String SEARCH_TYPE_CONTENT = "CONTENT";
	public static final String SEARCH_TYPE_NICKNAME = "NICKNAME";
	
	private String orderQuery;
	private Object parameter;
	/**
	 * @return the orderQuery
	 */
	public String getOrderQuery() {
		return orderQuery;
	}
	/**
	 * @param orderQuery the orderQuery to set
	 */
	public void setOrderQuery(String orderQuery) {
		this.orderQuery = orderQuery;
	}
	/**
	 * @return the parameter
	 */
	public Object getParameter() {
		return parameter;
	}
	/**
	 * @param parameter the parameter to set
	 */
	public void setParameter(Object parameter) {
		this.parameter = parameter;
	}
	
	
	
	
}
