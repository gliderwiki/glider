/**
 * @FileName  : SingleDatasourceDao.java
 * @Project   : NightHawk
 * @Date      : 2012. 9. 25. 
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 : 싱글 Datasource로 실제 테이블 생성, 데이터 저장, 테이블 드랍을 수행한다. 
 */
package org.gliderwiki.install;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Map;

import org.gliderwiki.web.domain.WeProfile;
import org.gliderwiki.web.domain.WeUser;
import org.gliderwiki.web.system.SystemConst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;


/**
 * @author yion
 *
 */
public class SingleDatasourceDao {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * @param jdbc_url
	 * @param jdbc_id
	 * @param jdbc_pw
	 * @return
	 */
	private SingleConnectionDataSource singleConnectionDS(String jdbc_url, String jdbc_id, String jdbc_pw) {
		SingleConnectionDataSource ds = new SingleConnectionDataSource();
		ds.setDriverClassName(SystemConst.MYSQL_DRIVER_NAME);
		ds.setUrl(jdbc_url);
		ds.setUsername(jdbc_id);
		ds.setPassword(jdbc_pw);
		return ds;
	}
	
	
	/**
	 * 입력 받은 JDBC Connection 정보로 동적으로 DataSource를 할당한다. 
	 * @param jdbc_url
	 * @param jdbc_id
	 * @param jdbc_pw
	 * @return
	 * @throws Throwable
	 */
	public int selectInfoJDBC(String jdbc_url, String jdbc_id, String jdbc_pw) throws Throwable {
		
		int count = 0;
		SingleConnectionDataSource ds = this.singleConnectionDS(jdbc_url, jdbc_id, jdbc_pw);
		
		logger.debug("Datasource Connection : " + ds.toString());
		logger.debug("################# DB 연결 ##################");
		
		JdbcTemplate jt = null;

		try {
			jt = new JdbcTemplate(ds);
			count = jt.queryForInt("SELECT 1");  			
			logger.debug("### count : " + count);
			ds.destroy();
		} catch (Exception e) {
			/*
			 * MySQL이 연결 안될 경우 
			 * org.springframework.jdbc.CannotGetJdbcConnectionException: Could not get JDBC Connection; 
			 * nested exception is com.mysql.jdbc.exceptions.jdbc4.CommunicationsException: Communications link failure
			 * 와 같은 에러가 발생함 
			 * SQLException 일 경우 대부분 아이디 // 패스워드가 잘못된 경우임 
			 */
			logger.debug("#################  DB 연결 Exception !!!!  ##################");
			logger.error("::message ==== " + e.getMessage());
			count = -2;
			e.printStackTrace();
		} finally {
			ds.destroy();
		}
	
		return count;
	}


	/**
	 * 캐릭터 타입에 따른 테이블을 생성한다. 익셉션 발생시 자동으로 테이블을 Drop 해야 한다. 
	 * @param charType
	 * @param allTables
	 * @return
	 */
	public int createTables(String jdbc_url, String jdbc_id, String jdbc_pw, String schema, String charType, Map allTables, String tableInitPath) { 
		// 커넥션 
		SingleConnectionDataSource ds = this.singleConnectionDS(jdbc_url, jdbc_id, jdbc_pw);
		
		int result = 0;
		
		try {
			result = this.runCreateTableScript(ds, tableInitPath, charType, allTables, schema);
			ds.destroy();
		} catch (Exception e) {
			logger.debug("!! 테이블 생성중 에러 발생");
			ds.destroy();
			e.printStackTrace();
		} finally {
			ds.destroy();
		}
		
		return result;
	}
	
	/**
	 * @param ds
	 * @param tableInitPath
	 * @return
	 */
	private int runCreateTableScript(SingleConnectionDataSource ds,	String tableInitPath, String charType, Map allTables, String schema) throws IOException, SQLException {
		StringBuffer command = null;
		int result = 0;
		InputStream ins = null;
		BufferedReader reader = null;
		
		boolean stopOnError = false;	// 에러 발생 여부 
		
		String sqlFileName = "";
		
		logger.debug("#charType : " + charType);
		if(charType.equals("utf-8")) { 
			sqlFileName = "/table_definition_utf-8.sql";
		} else if(charType.equals("utf8_unicode_ci")) {
			sqlFileName = "/table_definition_utf8_unicode_ci.sql";
		} else if(charType.equals("euc-kr")) {
			sqlFileName = "/table_definition_euc-kr.sql";
		}
		
		try {
			ins = new FileInputStream(tableInitPath+sqlFileName);
			reader = new BufferedReader(new InputStreamReader(ins));
			String line = null;
			
			while(true) {
				line = reader.readLine();
				
				if(line != null) {

					if (command == null) {
	                    command = new StringBuffer();
	                }
	                String trimmedLine = line.trim();
	                
	                logger.debug("## trimmedLine : " + trimmedLine);
	                if (trimmedLine.startsWith("--")) {
	                    // 주석은 아무것도 하지 않음 
	                } else if (trimmedLine.length() < 1 || trimmedLine.startsWith("//")) {
	                    // 주석은 아무것도 하지 않음
	                } else if (trimmedLine.length() < 1 || trimmedLine.startsWith("--")) {
	                	// 주석은 아무것도 하지 않음
	                } else if (trimmedLine.endsWith(";")) {
	                	// SQL 문장 종료인 경우 맨 뒤에 라인을 덧붙여서 문장을 완성한다. 
	                    command.append(line.substring(0, line.lastIndexOf(";")));
	                    command.append(" ");
	                    
	                    logger.debug("### SQL 구문 command : " + command.toString());
	                    JdbcTemplate jt = null;
	            		
	            		jt = new JdbcTemplate(ds);
	            			
		                if (!stopOnError) {
		                	jt.update(command.toString());
		                	result++;
		                }
		                
		                
		                logger.debug("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@result : " + result);
	            		command = null;
	            		//Thread.yield();
	                } else {
	                	logger.debug("## 일반 구문 : " + line);
	                    command.append(line);
	                    command.append(" ");
	                }
				} else {
					break;
				}
			}
			
		} catch (IOException e) {
	    	result = -1;
	    	if(reader != null) {
				reader.close();
			}
	    	this.dropTables(ds, allTables, schema);
	        logger.error("Error executing IOException: " + command);
	        e.printStackTrace();
	    } catch (Exception e) {
	    	result = -1;
	    	if(reader != null) {
				reader.close();
			}
	    	this.dropTables(ds, allTables, schema);
	        logger.error("Error executing Exception: " + command);
	        e.printStackTrace();
	    } finally {
	    	if(reader != null) {
				reader.close();
			}
	    }
		return result;
	}


	/**
	 * 내부에서 에러시에 강제로 드랍한다.
	 * @param ds
	 * @param allTables
	 * @return
	 * @throws SQLException
	 */
	public int dropTables(SingleConnectionDataSource ds, Map allTables, String schema) throws SQLException { 
		logger.debug("### 테이블 생성 중 에러가 발생하여 전체 테이블을 드랍함");
		int result = runDropScript(ds, allTables, schema);
		return result;
	}


	/**
	 * 드랍 버튼을 클릭하고 들어온 테이블 드랍 구문 
	 * @param ds
	 * @param allTables
	 * @return
	 * @throws SQLException
	 */
	public int dropTables(String jdbc_url, String jdbc_id, String jdbc_pw, String schema, Map allTables) throws SQLException { 
		logger.debug("### 드랍 버튼을 클릭한 전체 테이블 드랍");
		SingleConnectionDataSource ds = this.singleConnectionDS(jdbc_url, jdbc_id, jdbc_pw);
		
		int result =  runDropScript(ds, allTables, schema);
		
		logger.debug("result : " + result);
		ds.destroy();
		return result;
	}
	
	/**
	 * @param ds
	 * @param allTables
	 * @param schema
	 * @param result
	 * @return
	 */
	public int runDropScript(SingleConnectionDataSource ds, Map allTables, String schema) {
		int result = 0;
		JdbcTemplate jt;
		int tableSize = allTables.size();
		
		try {
			jt = new JdbcTemplate(ds);
			for(int index = 0; index < tableSize; index ++) {
				jt.update("DROP TABLE IF EXISTS " + allTables.get(index));
				result++;
			}
			
		} catch (Exception e) { 
			logger.debug("Table drop error!!!");
			result = -1;
			e.printStackTrace();
		}
		return result;
	}


	/**
	 * 어드민 데이터를 저장하고 기본 데이터들을 추가한다. 
	 * @param jdbc_url
	 * @param jdbc_id
	 * @param jdbc_pw
	 * @param weUser
	 * @param weProfile
	 * @param tableInitPath
	 * @return
	 * @throws SQLException 
	 */
	public int insertInitTableData(String jdbc_url, String jdbc_id, String jdbc_pw, WeUser weUser, WeProfile weProfile, String tableInitPath) throws SQLException {
		SingleConnectionDataSource ds = this.singleConnectionDS(jdbc_url, jdbc_id, jdbc_pw);
		
		int result = 0;
		int userDataSet = 0;	
		int insertDataResult = 0;
		try {
			ds.setAutoCommit(false);	// 트랜잭션 초기화 
			
			// 어드민 기본 데이터를 입력한다. WE_USER, WE_PROFILE
			userDataSet = this.insertAdminInfo(ds, weUser, weProfile);
			
			if(userDataSet == 1) {
				try {
					// 기본 데이터들을 테이블에 저장한다. TODOLIST : 공지사항 및 Static pages 정보들을 추가해야 한다. 
					insertDataResult = this.runInsertScript(ds, tableInitPath);		
					ds.getConnection().commit();		// 기본 데이터 완료 후 커밋 
					result = 1;
				} catch (Exception e) {
					ds.getConnection().rollback();
					result = -2;
					e.printStackTrace();
				}
				logger.debug("####insertDataResult : " + insertDataResult);
			} else {
				ds.getConnection().rollback();
				result = userDataSet;
			}
			ds.destroy();
		} catch (Exception e) {
			ds.getConnection().rollback();
			result = -1;
			logger.debug("!! 테이블 생성중 에러 발생");
			ds.destroy();
			e.printStackTrace();
		} finally {
			ds.destroy();
		}
		
		return result;
	}



	/**
	 * @param ds
	 * @param weUser
	 * @param weProfile
	 * @return
	 */
	private int insertAdminInfo(SingleConnectionDataSource ds, WeUser weUser, WeProfile weProfile) throws SQLException {
		JdbcTemplate jt = null;
		int result = 0;
		
		try {
			jt = new JdbcTemplate(ds);
			String insertUserSQL = "INSERT INTO we_user (WE_USER_ID, WE_USER_NAME, WE_USER_NICK," +
											  " WE_USER_KEY, WE_USER_PWD, WE_USER_AUTH_YN, WE_USER_JOIN_DATE, WE_USER_AUTH_DATE)" +
 								   " VALUES ('" +weUser.getWe_user_id()+ "', '" +weUser.getWe_user_name()+ "', '" +weUser.getWe_user_nick()+"', '" 
											   +weUser.getWe_user_key()+"', '" +weUser.getWe_user_pwd()+"', 'Y', now(), now())";
			result = jt.update(insertUserSQL);
			
			
			String insertProfileSQL = "INSERT INTO we_profile (WE_USER_IDX, WE_USER_EMAIL, WE_USER_SITE, WE_AWAY_YN,"+
											  " WE_GRADE, WE_TECH_YN, WE_POINT, WE_VISIT_DATE, WE_INS_DATE,	WE_NOTI_CHECKED	)" +
								   " VALUES ( 1, '"+weProfile.getWe_user_email()+"', '"+weProfile.getWe_user_site()+"', 'N'" 
								              +", 9, 'Y', '1', now(), now(), 'Y')";
			if(result == 1) {
				jt.update(insertProfileSQL);
			} else {
				result = -2;
			}
			
			logger.debug("##어드민 유저 커밋!");
		} catch (Exception e) { 
			result = -1;
			logger.debug("익셉션 롤백 ");
			e.printStackTrace();
		} 
		return result;
	}
	

	/**
	 * @param ds
	 * @param tableInitPath
	 * @return
	 */
	private int runInsertScript(SingleConnectionDataSource ds, String tableInitPath) throws IOException, SQLException {
		StringBuffer command = null;
		int result = 0;
		InputStream ins = null;
		BufferedReader reader = null;
		
		boolean stopOnError = false;	// 에러 발생 여부 
		
		String sqlFileName = "/data_insert.sql";
		
		
		try {
			ins = new FileInputStream(tableInitPath+sqlFileName);
			reader = new BufferedReader(new InputStreamReader(ins));
			String line = null;
			
			while(true) {
				line = reader.readLine();
				
				if(line != null) {

					if (command == null) {
	                    command = new StringBuffer();
	                }
	                String trimmedLine = line.trim();
	                
	                logger.debug("## trimmedLine : " + trimmedLine);
	                if (trimmedLine.startsWith("--")) {
	                    // 주석은 아무것도 하지 않음 
	                } else if (trimmedLine.length() < 1 || trimmedLine.startsWith("//")) {
	                    // 주석은 아무것도 하지 않음
	                } else if (trimmedLine.length() < 1 || trimmedLine.startsWith("--")) {
	                	// 주석은 아무것도 하지 않음
	                } else if (trimmedLine.endsWith(";")) {
	                	// SQL 문장 종료인 경우 맨 뒤에 라인을 덧붙여서 문장을 완성한다. 
	                    command.append(line.substring(0, line.lastIndexOf(";")));
	                    command.append(" ");
	                    
	                    logger.debug("### SQL 구문 command : " + command.toString());
	                    JdbcTemplate jt = null;
	            		
	            		jt = new JdbcTemplate(ds);
	            			
		                if (!stopOnError) {
		                	jt.update(command.toString());
		                	result++;
		                }	                
		                
		                logger.debug("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@result : " + result);
	            		command = null;
	            		//Thread.yield();
	                } else {
	                	logger.debug("## 일반 구문 : " + line);
	                    command.append(line);
	                    command.append(" ");
	                }
				} else {
					break;
				}
				
			}
			
		} catch (IOException e) {
	    	result = -1;
	    	if(reader != null) {
				reader.close();
			}
	        logger.error("Error executing IOException: " + command);
	        e.printStackTrace();
	    } catch (Exception e) {
	    	result = -1;
	    	if(reader != null) {
				reader.close();
			}
	        logger.error("Error executing Exception: " + command);
	        e.printStackTrace();
	    } finally {
	    	if(reader != null) {
				reader.close();
			}
	    }
		return result;
	}


	/**
	 * @param jdbcUrl
	 * @param jdbcId
	 * @param jdbcPassword
	 * @param jdbcSchema
	 * @param strKor
	 * @throws SQLException 
	 */
	public void insertLogToKor(String jdbcUrl, String jdbcId, String jdbcPassword, String jdbcSchema, String strKor) throws SQLException {
		SingleConnectionDataSource ds = this.singleConnectionDS(jdbcUrl, jdbcId, jdbcPassword);
		
		int result = 0;
		try {
			ds.setAutoCommit(false);	// 트랜잭션 초기화 
			
			result = this.insertKor(ds, strKor);
			logger.debug("### 한글 처리 후 result : "  +result);
			if(result == 1) {
				ds.getConnection().commit();		// 기본 데이터 완료 후 커밋
			} else {
				ds.getConnection().rollback();
			}
			ds.destroy();
		} catch (Exception e) {
			ds.getConnection().rollback();
			logger.debug("!! 테이블 생성중 에러 발생");
			ds.destroy();
			e.printStackTrace();
		} finally {
			ds.destroy();
		}
		
	}


	/**
	 * @param ds
	 * @param strKor
	 * @return
	 */
	private int insertKor(SingleConnectionDataSource ds, String strKor) {
		JdbcTemplate jt = null;
		int result = 0;
		
		try {
			jt = new JdbcTemplate(ds);
			
			String insertUserSQL = "INSERT INTO we_log ( WE_LOG_CODE, WE_LOG_DESC, WE_INS_DATE, WE_INS_USER)" +
											  " VALUES ('TEST', '"+ strKor +"', NOW(), 1 )";
			result = jt.update(insertUserSQL);
						
			logger.debug("##인서트!");
		} catch (Exception e) { 
			result = -1;
			logger.debug("익셉션 ");
			e.printStackTrace();
		} 
		return result;
	}


	/**
	 * @param jdbcUrl
	 * @param jdbcId
	 * @param jdbcPassword
	 * @return
	 */
	public String selectKorLog(String jdbcUrl, String jdbcId, String jdbcPassword) {

		String result = "";
		SingleConnectionDataSource ds = this.singleConnectionDS(jdbcUrl, jdbcId, jdbcPassword);
		
		logger.debug("Datasource Connection : " + ds.toString());
		logger.debug("################# DB 연결 ##################");
		
		JdbcTemplate jt = null;

		try {
			jt = new JdbcTemplate(ds);
			String sql  = "SELECT WE_LOG_DESC from we_log ";
			result = jt.queryForObject(sql, String.class);  			
			ds.destroy();
		} catch (Exception e) {
			logger.debug("#################  DB 연결 Exception !!!!  ##################");
			logger.error("::message ==== " + e.getMessage());
			e.printStackTrace();
		} finally {
			ds.destroy();
		}
	
		return result;
	}
	
	
}
