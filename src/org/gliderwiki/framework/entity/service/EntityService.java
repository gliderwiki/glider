/**
 * @FileName  : EntityService.java
 * @Project   : NightHawk
 * @Date      : 2011. 12. 25. 
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.framework.entity.service;

import java.io.Serializable;
import java.util.List;

/**
 * @author yion
 *
 */
public interface EntityService<T extends Serializable> {

	
	/**
	 * insert into table from entity
	 * @param domain
	 * @return result
	 * @throws Exception
	 */
	int insertEntity(T domain) throws Throwable;

	
	/**
	 * delete from table entity
	 * @param domain
	 * @return result
	 * @throws Exception
	 */
	int deleteEntity(T domain) throws Throwable;

	/**
	 * update table from entity
	 * @param domain
	 * @return result 
	 * @throws Exception
	 */
	int updateEntity(T domain) throws Throwable;

	/**
	 * get Count rows from entity
	 * @param domain
	 * @return count rows
	 * @throws Exception
	 */
	int getCountEntity(T domain) throws Throwable;
	
	/**
	 * get select row by index key from entity
	 * @param domain
	 * @return T(type)
	 * @throws Exception
	 */
	T getRowEntity(T domain) throws Throwable;
	
	/**
	 * get list rows by condition 
	 * @param domain
	 * @return List
	 * @throws Exception
	 */
	List<T> getListEntity(T domain) throws Throwable;

	/**
	 * get list rows by ordered condition 
	 * @param domain
	 * @param orderQuery
	 * @return
	 * @throws Throwable
	 */
	List<T> getListEntityOrdered(T domain, String orderQuery) throws Throwable;
	
}
