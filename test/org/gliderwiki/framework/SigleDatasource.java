/**
 * @FileName  : SingleConnectionDataSource.java
 * @Project   : NightHawk
 * @Date      : 2012. 9. 25. 
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.framework;

import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * @author yion
 *
 */
public class SigleDatasource {

	public static DataSourceTransactionManager txManager;
	
	public static void main(String [] args) throws SQLException {
		SingleConnectionDataSource ds = new SingleConnectionDataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/wiki");
		ds.setUsername("glider");
		ds.setPassword("glider");
		
		System.out.println("Datasource Connection : " + ds.toString());
		
		
		System.out.println("################# DB 연결 ##################");
		
		JdbcTemplate jt = null;
		
		ds.setAutoCommit(false);
		try {
			jt = new JdbcTemplate(ds);
						
			int result = jt.update("CREATE TABLE employee (	id INT(13),	name VARCHAR(20));");
			System.out.println("Result = " + result);
			result += jt.update("insert into employee (id, name) values (1, 'A')");
			System.out.println("Result = " + result);
			result += jt.update("insert into employee (id, name) values (2, 'B')");
			System.out.println("Result = " + result);
			result += jt.update("insert into employee (id, name) values (3, 'C')");
			System.out.println("Result = " + result);
			result += jt.update("insert into employee (id, name) values (4, 'D')");
			System.out.println("Result = " + result);
			result += jt.update("insert into employee (id, name) values (5, 'E')");
			System.out.println("Result = " + result);
			result += jt.update("insert into employee (id, name, test) values (6, 'F')");
			System.out.println("Result = " + result);

			
			int count = jt.queryForInt("select count(*) from employee");  
			
			System.out.println("### count : " + count);
			
			ds.getConnection().commit();
			System.out.println("커밋 완료");
		} catch (Exception e) { 
			ds.getConnection().rollback();
			System.out.println("익셉션 롤백 ");
			e.printStackTrace();
		} finally {
			ds.getConnection().rollback();
			System.out.println("파이널 롤백 ");
		}	
	
	}
}
