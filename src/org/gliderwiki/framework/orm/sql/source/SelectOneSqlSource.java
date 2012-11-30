package org.gliderwiki.framework.orm.sql.source;
import org.apache.ibatis.builder.SqlSourceBuilder;
import org.apache.ibatis.mapping.BoundSql;
import org.gliderwiki.framework.orm.sql.util.AnnotationUtil;
import org.gliderwiki.framework.orm.sql.util.ParserUtil;

public class SelectOneSqlSource extends SelectSqlSource {
	
	public SelectOneSqlSource(SqlSourceBuilder sqlSourceParser, Class<?> clazz) {
		super(sqlSourceParser, clazz);
	}

	public BoundSql getBoundSql(Object parameterObject) {
		String sql = staticSql + " WHERE " + 
				ParserUtil.join(AnnotationUtil.getNotNullColumnNames(parameterObject),"%1$s = #{%1$s}"," AND ") +
				" LIMIT 1";
		return getBoundSql(sql,parameterObject);
	}
}
