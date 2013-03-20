package org.gliderwiki.framework.orm.sql;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.builder.SqlSourceBuilder;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.mapping.ResultMapping;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.gliderwiki.framework.orm.sql.source.DeleteSqlSource;
import org.gliderwiki.framework.orm.sql.source.InsertSqlSource;
import org.gliderwiki.framework.orm.sql.source.SelectCountSqlSource;
import org.gliderwiki.framework.orm.sql.source.SelectListSqlSource;
import org.gliderwiki.framework.orm.sql.source.SelectOneSqlSource;
import org.gliderwiki.framework.orm.sql.source.UpdateSqlSource;
import org.gliderwiki.framework.orm.sql.util.Search;
import org.springframework.beans.BeanUtils;


public class EntityManager {

	private static final String PREFIX_INSERT = "DRM_Insert_";
	private static final String PREFIX_DELETE = "DRM_Delete_";
	private static final String PREFIX_UPDATE = "DRM_Update_";
	private static final String PREFIX_LOAD = "DRM_Load_";
	private static final String PREFIX_LIST = "DRM_List_";
	private static final String PREFIX_COUNT = "DRM_Count_";
	
	private static Logger logger = Logger.getLogger(EntityManager.class);
	
	private SqlSession sqlSession;
	private Configuration configuration;
	private SqlSourceBuilder sqlSourceParser;
	
	public <T>T getDao(Class<T> clazz) {
		return getSqlSession().getMapper(clazz);
	}
	
	public SqlSession getSqlSession() {
		return sqlSession;
	}
	
	public EntityManager(SqlSession sqlSession) {
		super();
		this.sqlSession = sqlSession;
		this.configuration = sqlSession.getConfiguration();
		this.sqlSourceParser = new SqlSourceBuilder(configuration);	
	}

	/**
	 * insert object
	 * @param object
	 * @throws Exception
	 */
	public void insert(Object object) throws Exception {
		Class<?> clazz = object.getClass();
		String statementName = PREFIX_INSERT + clazz.getSimpleName();
		if (!configuration.hasStatement(statementName)) {
			
		    addMappedStatement(statementName,new InsertSqlSource(sqlSourceParser,clazz),SqlCommandType.INSERT,null);
		}
		getSqlSession().insert(statementName, object);  
	}
	
	public int update(Object object) {
		Class<?> clazz = object.getClass();
		String statementName = PREFIX_UPDATE + clazz.getSimpleName();
		if (!configuration.hasStatement(statementName)) {
			addMappedStatement(statementName,new UpdateSqlSource(sqlSourceParser,clazz),SqlCommandType.UPDATE,null);
		}
		int result = 0;
		result = getSqlSession().update(statementName, object);
		return result;
	}
	
	public int delete(Object object) {
		Class<?> clazz = object.getClass();
		String statementName = PREFIX_DELETE + clazz.getSimpleName();
		
		
		if (!configuration.hasStatement(statementName)) {
			
			addMappedStatement(statementName,new DeleteSqlSource(sqlSourceParser,clazz),SqlCommandType.DELETE,Integer.class);
		}
		
		int result = 0;
		result = getSqlSession().delete(statementName, object);
		return result;
	}
	
	public Object load(Object object) {
		Class<?> clazz = object.getClass();
		String statementName = PREFIX_LOAD + clazz.getSimpleName();
		if (!configuration.hasStatement(statementName)) {
			addMappedStatement(statementName,new SelectOneSqlSource(sqlSourceParser,clazz),SqlCommandType.SELECT,clazz);
		}
		Object result = getSqlSession().selectOne(statementName, object);
		if (result != null)
			BeanUtils.copyProperties(result, object);
		return result;
	}
	
	public int count(Object object) {
		Class<?> clazz = object.getClass();
		String statementName = PREFIX_COUNT + clazz.getSimpleName();
		
		if (!configuration.hasStatement(statementName)) {
			addMappedStatement(statementName,new SelectCountSqlSource(sqlSourceParser,clazz),SqlCommandType.SELECT,Integer.class);
		}
		Object result = getSqlSession().selectOne(statementName, object);
		return (Integer)result;
	}
	
	public List<?> list(Object object) {
		return list(object,null);
	}
	
	public List<?> list(Object object, String orderQuery) {
		Class<?> clazz = object.getClass();
		String statementName = PREFIX_LIST + clazz.getSimpleName();
		if (!configuration.hasStatement(statementName)) {
			addMappedStatement(statementName,new SelectListSqlSource(sqlSourceParser,clazz),SqlCommandType.SELECT,clazz);
		}
		Search search = new Search();
		search.setParameter(object);
		search.setOrderQuery(orderQuery);
		List list = getSqlSession().selectList(statementName, search);
		
		return list;
	}
	
	private synchronized void addMappedStatement(String statementName, SqlSource sqlSource, SqlCommandType sqlCommandType, Class<?> resultType) {
		
		if (!configuration.hasStatement(statementName)) {
			MappedStatement.Builder statementBuilder = new MappedStatement.Builder(configuration, statementName, sqlSource, sqlCommandType);
			statementBuilder.timeout(configuration.getDefaultStatementTimeout());
			if (resultType != null) {
				
				List<ResultMap> resultMaps = new ArrayList<ResultMap>();
				ResultMap.Builder inlineResultMapBuilder = new ResultMap.Builder(
						configuration,
						statementBuilder.id() + "-Inline",
						resultType,
						new ArrayList<ResultMapping>());
				resultMaps.add(inlineResultMapBuilder.build());
				statementBuilder.resultMaps(resultMaps);
			}
	
			MappedStatement statement = statementBuilder.build();
			configuration.addMappedStatement(statement);
		}
	}

}