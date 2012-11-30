// 데이터베이스 생성 
CREATE DATABASE gliderwiki DEFAULT CHARACTER SET euckr COLLATE euckr_korean_ci;   

CREATE DATABASE gliderwiki DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;   

// 전체 테이블 목록 조회
SELECT 
TABLE_NAME, TABLE_COMMENT 
FROM INFORMATION_SCHEMA.TABLES 
WHERE TABLE_SCHEMA = 'gliderwiki'

// 테이블 생성구문 조회
HOW CREATE TABLE we_user


// 회원 정보 백업 
UPDATE we_user A INNER JOIN we_user_bak B
ON A.we_user_idx = B.we_user_idx
SET a.we_user_name = b.we_user_name
,   a.we_user_nick = b.we_user_nick
,   a.we_user_key = b.we_user_key
,   a.we_user_pwd = b.we_user_pwd
,   a.we_user_auth_yn = 'Y'


INSERT we_profile 
SELECT * FROM we_profile_bak



// 컬럼 조회 
SELECT 
TABLE_NAME, COLUMN_NAME, COLUMN_COMMENT 
FROM INFORMATION_SCHEMA.COLUMNS 
WHERE TABLE_SCHEMA = 'gliderwiki'


// 테이블별 풀 컬럼 조회 
show full columns from we_wiki;

// 버전 확인 
select version();


// 템프 테이블
create table temp( id int(3) not null auto_increment comment '순번',		
                   name  varchar(30) character set utf8 collate utf8_general_ci not null comment '카테고리명',
primary key (id)
);                   
                   
insert into temp( id, name ) values(1,'lee');
insert into temp( id, name ) values(2,'park');
insert into temp( id, name ) values(3,'이남희');

INSERT INTO we_template (we_template_type, we_template_text, we_template_markup, we_template_name, we_use_yn, we_ins_user, we_ins_date)
SELECT 'HTML' , we_wiki_text, we_wiki_markup, we_wiki_title , 'Y', 1, NOW()
FROM we_wiki
WHERE we_wiki_idx IN (7,8)


// 사용자 정보 테스트 
create table we_user_info (
user_idx  int(3) not null auto_increment comment '순번',		
user_id   varchar(30) character set utf8 collate utf8_general_ci not null comment '아이디',
user_name varchar(30) character set utf8 collate utf8_general_ci not null comment '이름 ',
join_date DATETIME comment '등록일',
primary key (user_idx)
)
;


INSERT INTO we_user_info (user_id, user_name) VALUES (
    'admin', '어드민');
    
INSERT INTO we_user_info (user_id, user_name) VALUES (
    'aa@aa.com', 'aa유저');
    



// 테이블 생성

create table we_category (
       we_cate_idx    int(3) not null auto_increment comment '순번',		
       we_cate_name        varchar(30) character set utf8 collate utf8_general_ci not null comment '카테고리명',
       we_caet_desc   text character set utf8 collate utf8_general_ci  not null comment '설명' ,
       use_yn	    char(1) not null default 'y' comment '사용여부',
       reg_dtm     DATETIME comment '등록일',
       upd_dtm     DATETIME comment '수정일',
       --hit                  int(4) comment '조회수' ,
       --recommend            int(7)  default 0 comment '추천' ,
primary key (we_cate_idx)
);



// 추가
ALTER TABLE board ADD usercookie VARCHAR(20)  NULL COMMENT '쿠키'

// 삭제 
ALTER TABLE board DROP usercookie;   
 
// 컬럼 수정  
ALTER TABLE table_name CHANGE old_col_name new_col_name new_type(new_len);  

ALTER TABLE WE_CODE_GROUP MODIFY we_code_grp_id VARCHAR(30) NOT NULL;

// 컬럼 이름 바꾸기 
ALTER TABLE tablename CHANGE colname newcolname INT NOT NULL AUTO_INCREMENT;

// 테이블 이름 바꾸기 
 ALTER TABLE tablename RENAME bbs;
 
// 특정 컬럼 뒤에 새로운 컬럼 추가
ALTER TABLE bbs ADD newcol varchar(10) AFTER num;

// 기존 컬럼을 지우고 맨 앞에 컬럼 추가
ALTER TABLE bbs DROP newcol, ADD newcol VARCHAR(10) FIRST;

// PRIMARY KEY 속성 삭제
ALTER TABLE test DROP PRIMARY KEY;




INSERT INTO board 
SELECT @RNUM:=@RNUM+1 AS RNUM , 'JAVA.JSP', '1263734265250', '', 'admin', '', '', '1111' 
     , 'JAVA', CONCAT('체목테스트', @RNUM) ,'내용', 1, '',  @RNUM, 0, 0, 'N', 'Y'
     , 'Y', 0, 0 , NULL, NULL, NOW(), NULL, '', '', ''

     
     
카피 테이블 생성
CREATE TABLE copy_t ( 
 num         INT(7)  COMMENT '순번' ,
 title       VARCHAR(30) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL DEFAULT '' COMMENT '제목' , 
 postdate             DATETIME COMMENT '등록일'
);

    
데이터 입력
SELECT rnum , '테스트 데이터', NOW()
FROM ( SELECT @RNUM := @RNUM + 1 AS rnum
            FROM ( SELECT @RNUM:=0 ) R, 
                       board  ) AS dt;
    