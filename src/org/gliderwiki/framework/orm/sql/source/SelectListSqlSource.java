package org.gliderwiki.framework.orm.sql.source;

import java.util.List;

import org.apache.ibatis.builder.SqlSourceBuilder;
import org.apache.ibatis.mapping.BoundSql;
import org.gliderwiki.framework.orm.sql.util.AnnotationUtil;
import org.gliderwiki.framework.orm.sql.util.ParserUtil;
import org.gliderwiki.framework.orm.sql.util.Search;


public class SelectListSqlSource extends SelectSqlSource {

	public SelectListSqlSource(SqlSourceBuilder sqlSourceParser, Class<?> clazz) {
		super(sqlSourceParser, clazz);
	}

	public BoundSql getBoundSql(Object parameterObject) {
		Object parameter = parameterObject;
		String orderQuery = null;
		String prefix = "";
		Search search = null;
		if (parameterObject instanceof Search) {
			search = (Search)parameterObject;
			parameter = search.getParameter();
			orderQuery = search.getOrderQuery();
			prefix = "parameter.";
		}
		StringBuilder sb = new StringBuilder(staticSql);
		List names = AnnotationUtil.getNotNullColumnNames(parameter);
		if (names != null && !names.isEmpty())
			sb.append(" WHERE ").append(ParserUtil.join(AnnotationUtil.getNotNullColumnNames(parameter),"%1$s = #{"+prefix+"%1$s}"," AND "));
		if (orderQuery != null)
			sb.append(" ORDER BY ").append(orderQuery);

		String sql = sb.toString(); 
		return getBoundSql(sql,parameterObject);
	}
}
