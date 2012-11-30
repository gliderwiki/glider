package org.gliderwiki.framework.orm.sql.builder;
import org.apache.ibatis.builder.SqlSourceBuilder;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.log4j.Logger;

public abstract class DynamicSqlBuilder implements SqlSource {

	private static Logger logger = Logger.getLogger(DynamicSqlBuilder.class);
	
	private SqlSourceBuilder sqlSourceParser;

	protected String staticSql;
	
	public DynamicSqlBuilder(SqlSourceBuilder sqlSourceParser) {
		this.sqlSourceParser = sqlSourceParser;
	}

	public abstract BoundSql getBoundSql(Object parameterObject);
	
	protected BoundSql getBoundSql(String sql, Object parameterObject) {
		SqlSource sqlSource = sqlSourceParser.parse(sql, parameterObject.getClass());
		BoundSql boundSql = sqlSource.getBoundSql(parameterObject);
		return boundSql;
	}
	 
	protected void setStaticSql(String sql) {
		this.staticSql = sql;
	}
}
