/**
 * @FileName  : EntityDaoImpl.java
 * @Project   : NightHawk
 * @Date      : 2011. 12. 25. 
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.framework.entity.dao;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.gliderwiki.framework.exception.DBHandleException;
import org.gliderwiki.framework.orm.sql.EntityManager;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


/**
 * @author yion
 *
 */

@Repository("entityDao")
public class EntityDaoImpl <T extends Serializable> extends SqlSessionDaoSupport implements EntityDao<T> {
	Logger logger = Logger.getLogger(this.getClass());
	
	EntityManager entityMgr;
	
	@Autowired
	public void setEntityMgr() {
		entityMgr = new EntityManager(getSqlSession());
	}
	
	@Override
	public int insertEntity(T domain) throws Throwable {
		int result = 0;

		try{
			entityMgr.insert(domain);
			result = 1;		
		} catch(Exception e) {
			result = -1;
			throw new DBHandleException("[SQL Insert Error]" , e.getCause());
		}

		return result;
	}

	
	@Override
	public int deleteEntity(T domain) throws Throwable {
		int result = 0;

		try{
			result = entityMgr.delete(domain);
		} catch(Exception e) {
			result = -1;
			throw new DBHandleException("[SQL delete Error]" , e.getCause());	
		}
		return result;
	}


	@Override
	public int updateEntity(T domain) throws Throwable {
		int result = 0;

		try{
			result = entityMgr.update(domain);
		} catch(Exception e) {
			result = -1;
			throw new DBHandleException("[SQL Update Error]" , e.getCause());
		}
		return result;
	}

	@Override
	public int getCountEntity(T domain) throws Throwable {
		int count = 0;

		try{
			count = entityMgr.count(domain);		
		} catch(Exception e) {
			count = -1;
			throw new DBHandleException("[SQL Count Error]" , e.getCause());
		}
		return count;
	}

	@Override
	public T getRowEntity(T domain) throws Throwable {
		return (T) entityMgr.load(domain);
	}

	@Override
	public List<T> getListEntity(T domain) throws Throwable {
		
		return (List<T>) entityMgr.list(domain);
	}
	
	@Override
	public List<T> getListEntityOrdered(T domain, String orderQuery) throws Throwable {
		
		return (List<T>) entityMgr.list(domain, orderQuery);
	}
	
}
