/**
 * @FileName  : EntityServiceImpl.java
 * @Project   : NightHawk
 * @Date      : 2011. 12. 25. 
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.framework.entity.service;

import java.io.Serializable;
import java.util.List;

import org.directwebremoting.annotations.RemoteProxy;
import org.gliderwiki.framework.entity.dao.EntityDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author yion
 *
 */
@Service("entityService")
@RemoteProxy(name="EntityService")
public class EntityServiceImpl<T extends Serializable> implements EntityService<T> {

	@Autowired
	private EntityDao<T> entityDao;

	@Override
	public int insertEntity(T domain) throws Throwable {
		return entityDao.insertEntity(domain);
	}

	@Override
	public int deleteEntity(T domain) throws Throwable {
		return entityDao.deleteEntity(domain);
	}

	public int updateEntity(T domain) throws Throwable {		
		return entityDao.updateEntity(domain);
	}

	@Override
	public int getCountEntity(T domain) throws Throwable {
		return entityDao.getCountEntity(domain);
	}

	@Override
	public T getRowEntity(T domain) throws Throwable {
		return entityDao.getRowEntity(domain);
	}

	@Override
	public List<T> getListEntity(T domain) throws Throwable {
		return entityDao.getListEntity(domain);
	}

	@Override
	public List<T> getListEntityOrdered(T domain, String orderQuery) throws Throwable {
		return entityDao.getListEntityOrdered(domain, orderQuery);
	}
}
