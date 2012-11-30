/**
 * @FileName  : SystemConst.java
 * @Project   : NightHawk
 * @Date      : 2012. 4. 6.
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.system;


/**
 * @author yion
 *
 */
public class SystemConst {

	// 태그 출력 갯수
	public static final Integer TAG_MAX_SIZE = 50;

	// 태그 출력 기준 기간
	public static final Integer TAG_SEARCH_DATE = -100;

	// 1년
	public static final Integer TAG_SEARCH_YEAR = -365;

	// 가입신청, 초대내역 1달 조회
	public static final Integer SEACH_MONTH = -30;

	public static final String CALL_SUCCESS = "200";

	public static final String CALL_FAIL = "100";

	public static final Integer NEW_USER_GRADE = 1;

	public static final String WIKI_KEY = "DFtzHGKKiFbdVlVktfrAaCFTNypBvKPOqTinLgyjCIHfJPgWwIaYuvEhxrnKkZiPaFvorJeLXUcQjrsdFDuTsIRxwzTkQQkkRGjvcjtghEIczyZZrAbihhPPChFSoQAE";

	// 내가 작성한 위키가 수정 될 때
	public static final Integer WIKI_EDIT  = 1;

	// 내가 개설한 공간에 새 글이 추가 될 때
	public static final Integer SPACE_NEW_POST = 2;

	// 내 위키를 다른 사람이 즐겨찾기 했을 때
	public static final Integer WIKI_FAVORITE = 3;

	// 내 위키를 다른 사람이 공감했을 때
	public static final Integer WIKI_AGREE = 4;
	
	// 내 위키에 다른 사람의 댓글이 달릴 때
	public static final Integer WI_WIKI_COMMENT = 5;

	// 나를 인맥으로 추가 했을 때
	public static final Integer WIKI_ADD_FRIEND = 6;

	// 내가 개설한 공간에 가입 신청 했을 때
	public static final Integer SPACE_JOIN = 7;

	// 다른 공간에서 나를 초대 했을 때
	public static final Integer SPACE_INVITE = 8;

	public static final String JOIN_MAIL_ERROR = "JOIN_MAIL_ERROR";

	public static final Integer FETCH_LIMIT_ROW = 21;

	public static final String SYSTEM_FILE_DOWNLOAD_PATH = "/resource/data/";
	
	public static final String WIKI_FILE_DOWNLOAD_PATH = "/resource/real/";
		
	public static final String PROPERTY_FULL_PATH = "/WEB-INF/spring/properties/";
	
	public static final String MYSQL_DB_PATH = "/WEB-INF/databases/mysql/";
	
	public static final String MYSQL_DRIVER_NAME = "com.mysql.jdbc.Driver";
	
	public static final String CONFIG_NAME = "config.properties";
	
	public static final String SERVER_DOMAIN = "http://www.gliderwiki.org";
}
