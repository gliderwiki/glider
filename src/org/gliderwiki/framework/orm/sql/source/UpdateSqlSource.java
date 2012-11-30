package org.gliderwiki.framework.orm.sql.source;
import org.apache.ibatis.builder.SqlSourceBuilder;
import org.gliderwiki.framework.orm.sql.builder.StaticSqlBuilder;
import org.gliderwiki.framework.orm.sql.util.AnnotationUtil;
import org.gliderwiki.framework.orm.sql.util.ParserUtil;


public class UpdateSqlSource extends StaticSqlBuilder {

	public UpdateSqlSource(SqlSourceBuilder sqlSourceParser, Class<?> clazz) {
		super();
		String sql = String.format("UPDATE %1$s SET %2$s WHERE %3$s",
				AnnotationUtil.getTableName(clazz),
				ParserUtil.join(AnnotationUtil.getNonPrimaryKeyColumnNames(clazz),"%1$s = #{%1$s}",", "),
				ParserUtil.join(AnnotationUtil.getPrimaryKeyColumnNames(clazz),"%1$s = #{%1$s}"," AND "));
		parse(sqlSourceParser, sql, clazz);
	}

}
