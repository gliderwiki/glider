package org.gliderwiki.framework.orm.sql.source;
import org.apache.ibatis.builder.SqlSourceBuilder;
import org.gliderwiki.framework.orm.sql.builder.DynamicSqlBuilder;
import org.gliderwiki.framework.orm.sql.util.AnnotationUtil;
import org.gliderwiki.framework.orm.sql.util.ParserUtil;


public abstract class SelectSqlSource extends DynamicSqlBuilder {

	public SelectSqlSource(SqlSourceBuilder sqlSourceParser, Class<?> clazz) {
		super(sqlSourceParser);
		staticSql = String.format("SELECT %1$s FROM %2$s ",
				ParserUtil.join(AnnotationUtil.getColumnNames(clazz),", "),
				AnnotationUtil.getTableName(clazz));
	}

}
