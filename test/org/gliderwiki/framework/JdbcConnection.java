/**
 * @FileName  : JdbcConnection.java
 * @Project   : NightHawk
 * @Date      : 2012. 9. 15. 
 * @작성자      : @author inamhui

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.framework;

import java.sql.DriverManager;
import java.sql.ResultSet;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

/**
 * @author inamhui
 * 
 */
public class JdbcConnection {

	public static void main(String argv[]) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("jdbc 드라이버 로딩 성공");

		try {
			String url = "jdbc:mysql://ip를 입력해라:3306/gliderwiki";
			Connection con = (Connection) DriverManager.getConnection(url, "아뒤", "비밀번호");
			System.out.println("mysql 접속 성공");
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = stmt.executeQuery("select now()");
			System.out.println("Got result:");

			while (rs.next()) {
				String no = rs.getString(1);
				String tblname = rs.getString(1);
				System.out.println(" no = " + no);
				System.out.println(" tblname= " + tblname);
			}

			stmt.close();
			con.close();
		} catch (java.lang.Exception ex) {
			ex.printStackTrace();
		}
	}

}
