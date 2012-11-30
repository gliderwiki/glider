/**
 * Target Table : WE_MENU 
 * DESC : 메뉴 테이블에 기본 메뉴를 저장한다.
 * Ver : 1.0
 * Auth : Yion LEE
 * Modify : 
 * Update Date : 
 * Create Date : 2012-09-17
 */

/* 시스템 기본 메뉴 */
insert into `we_menu` (`WE_MENU_IDX`, `WE_MENU_NAME`, `WE_MENU_ORDER_IDX`, `WE_MENU_PARENT_IDX`, `WE_MENU_DEPTH`, `WE_MENU_URL`, `WE_USE_YN`, `WE_MENU_TYPE`, `WE_ACCESS_LEVEL`, `WE_MENU_GROUP`) values('1','Wiki란','1','0','1','','Y','S','0',NULL);
insert into `we_menu` (`WE_MENU_IDX`, `WE_MENU_NAME`, `WE_MENU_ORDER_IDX`, `WE_MENU_PARENT_IDX`, `WE_MENU_DEPTH`, `WE_MENU_URL`, `WE_USE_YN`, `WE_MENU_TYPE`, `WE_ACCESS_LEVEL`, `WE_MENU_GROUP`) values('2','GLiDER소개','2','0','1','','Y','S','0',NULL);
insert into `we_menu` (`WE_MENU_IDX`, `WE_MENU_NAME`, `WE_MENU_ORDER_IDX`, `WE_MENU_PARENT_IDX`, `WE_MENU_DEPTH`, `WE_MENU_URL`, `WE_USE_YN`, `WE_MENU_TYPE`, `WE_ACCESS_LEVEL`, `WE_MENU_GROUP`) values('3','둘러보기','3','0','1','/dashboard','Y','S','0',NULL);
insert into `we_menu` (`WE_MENU_IDX`, `WE_MENU_NAME`, `WE_MENU_ORDER_IDX`, `WE_MENU_PARENT_IDX`, `WE_MENU_DEPTH`, `WE_MENU_URL`, `WE_USE_YN`, `WE_MENU_TYPE`, `WE_ACCESS_LEVEL`, `WE_MENU_GROUP`) values('4','라이센스정책','4','0','1','','Y','S','0',NULL);
insert into `we_menu` (`WE_MENU_IDX`, `WE_MENU_NAME`, `WE_MENU_ORDER_IDX`, `WE_MENU_PARENT_IDX`, `WE_MENU_DEPTH`, `WE_MENU_URL`, `WE_USE_YN`, `WE_MENU_TYPE`, `WE_ACCESS_LEVEL`, `WE_MENU_GROUP`) values('5','다운로드','5','0','1','','Y','S','0',NULL);
insert into `we_menu` (`WE_MENU_IDX`, `WE_MENU_NAME`, `WE_MENU_ORDER_IDX`, `WE_MENU_PARENT_IDX`, `WE_MENU_DEPTH`, `WE_MENU_URL`, `WE_USE_YN`, `WE_MENU_TYPE`, `WE_ACCESS_LEVEL`, `WE_MENU_GROUP`) values('6','Wiki 100% 활용하기','6','0','1','','Y','S','0',NULL);
insert into `we_menu` (`WE_MENU_IDX`, `WE_MENU_NAME`, `WE_MENU_ORDER_IDX`, `WE_MENU_PARENT_IDX`, `WE_MENU_DEPTH`, `WE_MENU_URL`, `WE_USE_YN`, `WE_MENU_TYPE`, `WE_ACCESS_LEVEL`, `WE_MENU_GROUP`) values('7','Wiki 버전정보','7','0','1','','Y','S','0',NULL);
insert into `we_menu` (`WE_MENU_IDX`, `WE_MENU_NAME`, `WE_MENU_ORDER_IDX`, `WE_MENU_PARENT_IDX`, `WE_MENU_DEPTH`, `WE_MENU_URL`, `WE_USE_YN`, `WE_MENU_TYPE`, `WE_ACCESS_LEVEL`, `WE_MENU_GROUP`) values('8','후원하기','8','0','1','','Y','S','0',NULL);

/* 타이틀 메뉴  */
insert into `we_menu` (`WE_MENU_IDX`, `WE_MENU_NAME`, `WE_MENU_ORDER_IDX`, `WE_MENU_PARENT_IDX`, `WE_MENU_DEPTH`, `WE_MENU_URL`, `WE_USE_YN`, `WE_MENU_TYPE`, `WE_ACCESS_LEVEL`, `WE_MENU_GROUP`) values('9','공간생성하기','1','0','1','/space/form','Y','M','3',NULL);
insert into `we_menu` (`WE_MENU_IDX`, `WE_MENU_NAME`, `WE_MENU_ORDER_IDX`, `WE_MENU_PARENT_IDX`, `WE_MENU_DEPTH`, `WE_MENU_URL`, `WE_USE_YN`, `WE_MENU_TYPE`, `WE_ACCESS_LEVEL`, `WE_MENU_GROUP`) values('10','공간정보','2','0','1','javascript:void(null);','Y','M','0','SPACE');
insert into `we_menu` (`WE_MENU_IDX`, `WE_MENU_NAME`, `WE_MENU_ORDER_IDX`, `WE_MENU_PARENT_IDX`, `WE_MENU_DEPTH`, `WE_MENU_URL`, `WE_USE_YN`, `WE_MENU_TYPE`, `WE_ACCESS_LEVEL`, `WE_MENU_GROUP`) values('11','위키관리','3','0','1','javascript:void(null);','Y','M','0','WIKI');
insert into `we_menu` (`WE_MENU_IDX`, `WE_MENU_NAME`, `WE_MENU_ORDER_IDX`, `WE_MENU_PARENT_IDX`, `WE_MENU_DEPTH`, `WE_MENU_URL`, `WE_USE_YN`, `WE_MENU_TYPE`, `WE_ACCESS_LEVEL`, `WE_MENU_GROUP`) values('12','내 정보','4','0','1','javascript:void(null);','Y','M','0','USER');

/* 서브 메뉴 공간정보 */
insert into `we_menu` (`WE_MENU_NAME`, `WE_MENU_ORDER_IDX`, `WE_MENU_PARENT_IDX`, `WE_MENU_DEPTH`, `WE_MENU_URL`, `WE_USE_YN`, `WE_MENU_TYPE`, `WE_ACCESS_LEVEL`, `WE_MENU_GROUP`) values('전체공간보기','1','10','2','/space','Y','C','0','SPACE');
insert into `we_menu` (`WE_MENU_NAME`, `WE_MENU_ORDER_IDX`, `WE_MENU_PARENT_IDX`, `WE_MENU_DEPTH`, `WE_MENU_URL`, `WE_USE_YN`, `WE_MENU_TYPE`, `WE_ACCESS_LEVEL`, `WE_MENU_GROUP`) values('내가개설한공간','2','10','2','/space/openList','Y','C','3','SPACE');
insert into `we_menu` (`WE_MENU_NAME`, `WE_MENU_ORDER_IDX`, `WE_MENU_PARENT_IDX`, `WE_MENU_DEPTH`, `WE_MENU_URL`, `WE_USE_YN`, `WE_MENU_TYPE`, `WE_ACCESS_LEVEL`, `WE_MENU_GROUP`) values('키워드 목록조회','3','10','2','/space/tag','Y','C','0','SPACE');

/* 서브 메뉴 위키관리정보 */
insert into `we_menu` (`WE_MENU_NAME`, `WE_MENU_ORDER_IDX`, `WE_MENU_PARENT_IDX`, `WE_MENU_DEPTH`, `WE_MENU_URL`, `WE_USE_YN`, `WE_MENU_TYPE`, `WE_ACCESS_LEVEL`, `WE_MENU_GROUP`) values('내 위키목록','1','11','2','/wiki/mywiki','Y','C','1','WIKI');
insert into `we_menu` (`WE_MENU_NAME`, `WE_MENU_ORDER_IDX`, `WE_MENU_PARENT_IDX`, `WE_MENU_DEPTH`, `WE_MENU_URL`, `WE_USE_YN`, `WE_MENU_TYPE`, `WE_ACCESS_LEVEL`, `WE_MENU_GROUP`) values('내 인맥의 위키목록','2','11','2','/wiki/friendwiki','N','C','1','WIKI');
insert into `we_menu` (`WE_MENU_NAME`, `WE_MENU_ORDER_IDX`, `WE_MENU_PARENT_IDX`, `WE_MENU_DEPTH`, `WE_MENU_URL`, `WE_USE_YN`, `WE_MENU_TYPE`, `WE_ACCESS_LEVEL`, `WE_MENU_GROUP`) values('템플릿 생성하기','3','11','2','/template','Y','C','1','WIKI');
insert into `we_menu` (`WE_MENU_NAME`, `WE_MENU_ORDER_IDX`, `WE_MENU_PARENT_IDX`, `WE_MENU_DEPTH`, `WE_MENU_URL`, `WE_USE_YN`, `WE_MENU_TYPE`, `WE_ACCESS_LEVEL`, `WE_MENU_GROUP`) values('템플릿 목록조회','4','11','2','/template/list','Y','C','1','WIKI');

/* 서브 메뉴 내정보 */
insert into `we_menu` (`WE_MENU_NAME`, `WE_MENU_ORDER_IDX`, `WE_MENU_PARENT_IDX`, `WE_MENU_DEPTH`, `WE_MENU_URL`, `WE_USE_YN`, `WE_MENU_TYPE`, `WE_ACCESS_LEVEL`, `WE_MENU_GROUP`) values('프로필 수정','1','12','2','/user/profile','Y','C','1','USER');
insert into `we_menu` (`WE_MENU_NAME`, `WE_MENU_ORDER_IDX`, `WE_MENU_PARENT_IDX`, `WE_MENU_DEPTH`, `WE_MENU_URL`, `WE_USE_YN`, `WE_MENU_TYPE`, `WE_ACCESS_LEVEL`, `WE_MENU_GROUP`) values('활동내역','2','12','2','/user/action','Y','C','1','USER');
insert into `we_menu` (`WE_MENU_NAME`, `WE_MENU_ORDER_IDX`, `WE_MENU_PARENT_IDX`, `WE_MENU_DEPTH`, `WE_MENU_URL`, `WE_USE_YN`, `WE_MENU_TYPE`, `WE_ACCESS_LEVEL`, `WE_MENU_GROUP`) values('알람관리','3','12','2','/user/alarm','Y','C','1','USER');
insert into `we_menu` (`WE_MENU_NAME`, `WE_MENU_ORDER_IDX`, `WE_MENU_PARENT_IDX`, `WE_MENU_DEPTH`, `WE_MENU_URL`, `WE_USE_YN`, `WE_MENU_TYPE`, `WE_ACCESS_LEVEL`, `WE_MENU_GROUP`) values('즐겨찾기목록조회','4','12','2','/user/favorite','Y','C','1','USER');
insert into `we_menu` (`WE_MENU_NAME`, `WE_MENU_ORDER_IDX`, `WE_MENU_PARENT_IDX`, `WE_MENU_DEPTH`, `WE_MENU_URL`, `WE_USE_YN`, `WE_MENU_TYPE`, `WE_ACCESS_LEVEL`, `WE_MENU_GROUP`) values('공간가입/초대목록','5','12','2','/user/myspace','Y','C','1','USER');
insert into `we_menu` (`WE_MENU_NAME`, `WE_MENU_ORDER_IDX`, `WE_MENU_PARENT_IDX`, `WE_MENU_DEPTH`, `WE_MENU_URL`, `WE_USE_YN`, `WE_MENU_TYPE`, `WE_ACCESS_LEVEL`, `WE_MENU_GROUP`) values('내 인맥','6','12','2','/user/connection','Y','C','1','USER');

/* 타이틀 메뉴 공간정보 */
insert into `we_menu` (`WE_MENU_NAME`, `WE_MENU_ORDER_IDX`, `WE_MENU_PARENT_IDX`, `WE_MENU_DEPTH`, `WE_MENU_URL`, `WE_USE_YN`, `WE_MENU_TYPE`, `WE_ACCESS_LEVEL`, `WE_MENU_GROUP`) values('위키생성하기','1','10','1','/wiki/new/','Y','T','1','SPACE');
insert into `we_menu` (`WE_MENU_NAME`, `WE_MENU_ORDER_IDX`, `WE_MENU_PARENT_IDX`, `WE_MENU_DEPTH`, `WE_MENU_URL`, `WE_USE_YN`, `WE_MENU_TYPE`, `WE_ACCESS_LEVEL`, `WE_MENU_GROUP`) values('초대/가입신청관리','2','10','1','/space/joinManage/','Y','T','3','SPACE');
insert into `we_menu` (`WE_MENU_NAME`, `WE_MENU_ORDER_IDX`, `WE_MENU_PARENT_IDX`, `WE_MENU_DEPTH`, `WE_MENU_URL`, `WE_USE_YN`, `WE_MENU_TYPE`, `WE_ACCESS_LEVEL`, `WE_MENU_GROUP`) values('공간정보조회','3','10','1','/space/','Y','T','1','SPACE');
insert into `we_menu` (`WE_MENU_NAME`, `WE_MENU_ORDER_IDX`, `WE_MENU_PARENT_IDX`, `WE_MENU_DEPTH`, `WE_MENU_URL`, `WE_USE_YN`, `WE_MENU_TYPE`, `WE_ACCESS_LEVEL`, `WE_MENU_GROUP`) values('공간정보수정','4','10','1','/space/','Y','T','3','SPACE');
insert into `we_menu` (`WE_MENU_NAME`, `WE_MENU_ORDER_IDX`, `WE_MENU_PARENT_IDX`, `WE_MENU_DEPTH`, `WE_MENU_URL`, `WE_USE_YN`, `WE_MENU_TYPE`, `WE_ACCESS_LEVEL`, `WE_MENU_GROUP`) values('새소식만들기','5','10','1','javascript:void(null);','Y','T','3','SPACE');
insert into `we_menu` (`WE_MENU_NAME`, `WE_MENU_ORDER_IDX`, `WE_MENU_PARENT_IDX`, `WE_MENU_DEPTH`, `WE_MENU_URL`, `WE_USE_YN`, `WE_MENU_TYPE`, `WE_ACCESS_LEVEL`, `WE_MENU_GROUP`) values('일정생성','6','10','1','javascript:void(null);','N','T','3','SPACE');

/* 타이틀 메뉴 위키관리 */
insert into `we_menu` (`WE_MENU_NAME`, `WE_MENU_ORDER_IDX`, `WE_MENU_PARENT_IDX`, `WE_MENU_DEPTH`, `WE_MENU_URL`, `WE_USE_YN`, `WE_MENU_TYPE`, `WE_ACCESS_LEVEL`, `WE_MENU_GROUP`) values('변경내역','1','11','1','/engine/list/','Y','T','1','WIKI');
insert into `we_menu` (`WE_MENU_NAME`, `WE_MENU_ORDER_IDX`, `WE_MENU_PARENT_IDX`, `WE_MENU_DEPTH`, `WE_MENU_URL`, `WE_USE_YN`, `WE_MENU_TYPE`, `WE_ACCESS_LEVEL`, `WE_MENU_GROUP`) values('EXPORT PDF','2','11','1','/exp/pdf/','Y','T','1','WIKI');
insert into `we_menu` (`WE_MENU_NAME`, `WE_MENU_ORDER_IDX`, `WE_MENU_PARENT_IDX`, `WE_MENU_DEPTH`, `WE_MENU_URL`, `WE_USE_YN`, `WE_MENU_TYPE`, `WE_ACCESS_LEVEL`, `WE_MENU_GROUP`) values('위키마크업활용','3','11','1','','Y','T','1','WIKI');


/**
 * Target Table : WE_META_INFO 
 * DESC : 사용자 알람 선택 종류 기본데이터를 저장한다. 
 * Ver : 1.0
 * Auth : Yion LEE
 * Modify : 
 * Update Date : 
 * Create Date : 2012-09-17
 */

insert into `we_meta_info` (`WE_META_TYPE`, `WE_META_DOMAIN`, `WE_META_DESC`, `WE_META_TABLE_NAME`, `WE_META_TABLE_KEY`, `WE_USE_YN`) values('E','WIKI_EDIT','내가 작성한 위키가 수정될 때 ','WE_WIKI','WE_WIKI_IDX','Y');
insert into `we_meta_info` (`WE_META_TYPE`, `WE_META_DOMAIN`, `WE_META_DESC`, `WE_META_TABLE_NAME`, `WE_META_TABLE_KEY`, `WE_USE_YN`) values('E','SPACE_NEW_POST','내가 개설한 공간에 새 글이 추가 될 때','WE_SPACE','WE_SPACE_IDX','Y');
insert into `we_meta_info` (`WE_META_TYPE`, `WE_META_DOMAIN`, `WE_META_DESC`, `WE_META_TABLE_NAME`, `WE_META_TABLE_KEY`, `WE_USE_YN`) values('E','WIKI_FAVORITE','내 위키를 다른 사람이 즐겨 찾기 했을 때','WE_FAVORITE','WE_WIKI_IDX','Y');
insert into `we_meta_info` (`WE_META_TYPE`, `WE_META_DOMAIN`, `WE_META_DESC`, `WE_META_TABLE_NAME`, `WE_META_TABLE_KEY`, `WE_USE_YN`) values('E','WIKI_AGREE','내 위키를 다른 사람이 공감 했을 때','WE_WIKI','WE_WIKI_IDX','Y');
insert into `we_meta_info` (`WE_META_TYPE`, `WE_META_DOMAIN`, `WE_META_DESC`, `WE_META_TABLE_NAME`, `WE_META_TABLE_KEY`, `WE_USE_YN`) values('E','WIKI_VIEW_COMMENT','내 위키에 다른 사람의 댓글이 달릴 때 ','WI_WIKI_COMMENT','WE_WIKI_IDX','Y');
insert into `we_meta_info` (`WE_META_TYPE`, `WE_META_DOMAIN`, `WE_META_DESC`, `WE_META_TABLE_NAME`, `WE_META_TABLE_KEY`, `WE_USE_YN`) values('E','WIKI_ADD_FRIEND','나를 인맥에 추가 했을 때 ','WE_ADD_FRIEND','WE_TARGET_USER_IDX','Y');
insert into `we_meta_info` (`WE_META_TYPE`, `WE_META_DOMAIN`, `WE_META_DESC`, `WE_META_TABLE_NAME`, `WE_META_TABLE_KEY`, `WE_USE_YN`) values('E','SPACE_JOIN','내가 개설한 공간에 가입 신청 했을때','WE_SPACE_JOIN','WE_SPACE_IDX','Y');
insert into `we_meta_info` (`WE_META_TYPE`, `WE_META_DOMAIN`, `WE_META_DESC`, `WE_META_TABLE_NAME`, `WE_META_TABLE_KEY`, `WE_USE_YN`) values('E','SPACE_INVITE','다른 공간에서 나를 초대했을때','WE_SPACE_JOIN','WE_SPACE_IDX','Y');



/**
 * Target Table : WE_GROUP_INFO 
 * DESC : 글라이더 기본 그룹을 생성한다. 
 * Ver : 1.0
 * Auth : Yion LEE
 * Modify : 
 * Update Date : 
 * Create Date : 2012-09-17
 */
INSERT INTO `we_group_info` (`WE_GROUP_CODE`, `WE_GROUP_NAME`, `WE_GROUP_TYPE`, `WE_REQUIRED_YN`, `WE_GROUP_OWNER`, `WE_GROUP_INFO`, `WE_USE_YN`,`WE_INS_USER`, `WE_INS_DATE`)
VALUES ('REQUIRED-01', '기본그룹', '1', 'Y', '1', '글라이더 위키 기본 그룹(삭제불가) ', 'Y', 1 ,NOW());


/**
 * Target Table : WE_USER 
 * DESC : 글라이더 기본 사용자를  생성한다. (어드민) 
 * Ver : 1.0
 * Auth : Yion LEE
 * Modify : 
 * Update Date : 
 * Create Date : 2012-09-21
 */

INSERT INTO `we_user` 
	(`WE_USER_IDX`, 
	`WE_USER_ID`, 
	`WE_USER_NAME`, 
	`WE_USER_NICK`, 
	`WE_USER_KEY`, 
	`WE_USER_PWD`, 
	`WE_USER_JOIN_DATE`, 
	`WE_USER_AUTH_YN`, 
	`WE_USER_AUTH_DATE`
	)
	VALUES
	(1, 
	'gliderwiki@gliderwiki.org', 
	'어드민', 
	'영자님!', 
	'PtNMgeFxWJrdQZKbPRWlxYePZVKMSvtsaEJrHgUlFFDPbJDdAlDuGZhyNQxanazPwBpBGJZjLIGZyLHKMHYkLEAMmocDojGfQuLjWDnQsZDxYZxqNBsDtdPvtITOsghU', -- 암호화키
	'iaKY69aOyQA=', -- 1111
	NOW(),
	'Y', 
	NOW()
	);

	
/**
 * Target Table : WE_PROFILE
 * DESC : 글라이더 유저의  프로필 데이터를  생성한다. (어드민) 
 * Ver : 1.0
 * Auth : Yion LEE
 * Modify : 
 * Update Date : 
 * Create Date : 2012-09-21
 */
INSERT INTO .`we_profile` 
	(`WE_USER_IDX`, 
	`WE_USER_EMAIL`, 
	`WE_USER_SITE`, 
	`WE_AWAY_YN`, 
	`WE_GRADE`, 
	`WE_TECH_YN`, 
	`WE_POINT`, 
	`WE_VISIT_DATE`, 
	`WE_INS_DATE`, 
	`WE_NOTI_CHECKED`
	)
	VALUES
	(1, 
	'gliderwiki@gliderwiki.com', 
	'http://www.gliderwiki.org', 
	'N', 
	9, 
	'Y', 
	'1', 
	NOW(), 
	NOW(), 
	'Y'
	);
	

/**
 * Target Table : WE_SPACE 
 * DESC : 글라이더 기본 공간을 생성한다. 
 * Ver : 1.0
 * Auth : Yion LEE
 * Modify : 
 * Update Date : 
 * Create Date : 2012-09-17
 */
INSERT INTO `we_space` (`WE_SPACE_NAME`, `WE_SPACE_DESC`, `WE_VIEW_PRIVACY`, `WE_EDIT_PRIVACY`, `WE_ADMIN_IDX`, `WE_SPACE_EXPOSED`, `WE_USE_YN`, `WE_INS_USER`, `WE_INS_DATE` )
VALUES ( '기본공간', 'GLiDER™ Wiki 기본 공간입니다. 글라이더 위키의 전체적인 사용방법과 위키의 작성방법, 사이트 운영정책에 대해서 설명합니다.', 'ALLGROUP', 'ALLGROUP', '1', 'Y', 'Y',	'1',NOW());

	
/**
 * Target Table : WE_SPACE_IMAGE 
 * DESC : 글라이더 기본 공간을 생성한다. 
 * Ver : 1.0
 * Auth : Yion LEE
 * Modify : 
 * Update Date : 
 * Create Date : 2012-09-17
 */
INSERT INTO `we_space_image` (`WE_SPACE_IMAGE_IDX`,	`WE_SPACE_IDX`,	`WE_USED`,	`WE_INS_DATE`)
	VALUES	(1,	1,	1,	NOW());


/**
 * Target Table : WE_WIKI 
 * DESC : 글라이더 기본 위키를 생성한다.   
 * Ver : 1.0
 * Auth : Yion LEE
 * Modify : 
 * Update Date : 
 * Create Date : 2012-09-17
 */

-- 추후 인서트 합니다. 




-- 인스톨 유저 	
INSERT INTO `we_install_user` 
	(
	`WE_DOMAIN`, 
	`WE_ACTIVE_KEY`, 
	`WE_EMAIL`, 
	`WE_INSTALL_DATE`, 
	`WE_AUTH_YN`
	)
	VALUES
	(
	'http://www.gliderwiki.org', 
	'DFtzHGKKiFbdVlVktfrAaCFTNypBvKPOqTinLgyjCIHfJPgWwIaYuvEhxrnKkZiPaFvorJeLXUcQjrsdFDuTsIRxwzTkQQkkRGjvcjtghEIczyZZrAbihhPPChFSoQAE', 
	'gliderwiki@gliderwiki.org', 
	NOW(), 
	'Y'
	);

	
-- 글라이더 기능 
INSERT INTO `we_function` 
	(
	`WE_FUNCTION_NAME`, 
	`WE_FUNCTION_DESC`, 
	`WE_FUNCTION_CODE`, 
	`WE_FUNCTION_VER`, 
	`WE_FUNCTION_TYPE`, 
	`WE_USE_YN`, 
	`WE_EXTEND_YN`, 	
	`WE_INS_DATE`, 
	`WE_INS_USER`
	)
	VALUES
	(
	'framework', 
	'글라이더 프레임워크 업데이트 버전', 
	'fw', 
	'1.0.1', 
	'LIB', 
	'Y', 
	'Y', 
	NOW(), 
	1
	);


	
	





