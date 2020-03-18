package com.kosmo.mintchoco.common;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;

/*
 * 담당자 : 김정호
 */

// 데이터 테이블 형상 관리용 메서드
public class DBVersionManager {
	final String THIS_VERSION = "1.1.4";				// 현재 데이터 베이스 버전 H2 용
	private String chkVerTblSql = "select count(*) as result from information_schema.tables where table_name = 'VERSION'";
	private String verInsertSql = "insert into VERSION(VERSION_ID, CURRENT_VERSION) values('ver', ?)"; // 버전 인서트 sql
	private String verSelSql = "select CURRENT_VERSION from VERSION where VERSION_ID = 'ver'"; // 버전 확인용 sql
	
	private String[] drpSql = { // 기존 테이블 및 시퀀스 삭제 sql
			"drop table VERSION",
			"drop table NOTICE",
			"drop table FAQ",
			"drop table tag_mapping",
			"drop table tag",
			"drop view SEARCH_VIEW",
			"drop view ASSESSMENT_view",
			"drop table ASSESSMENT_EST",
			"drop table ASSESSMENT",
			"drop view FAVORITE_VIEW",
			"drop table favorite",
			"drop table MOVIE",
			"drop table MEMBER",
			"drop sequence MEMBER_SEQ",
			"drop sequence MOVIE_SEQ",
			"drop sequence FAQ_SEQ",
			"drop sequence NOTICE_SEQ",
			"drop sequence ASSESS_SEQ",
			"drop sequence ASSESS_EST_SEQ"
	};

	private String[] delSql = { // 데이터 삭제 sql
			"delete notice",
			"delete faq",
			"delete tag_mapping",
			"delete tag",
			"delete ASSESSMENT_EST",
			"delete ASSESSMENT",
			"delete favorite",
			"delete movie",
			"delete MEMBER"
	};
	
	private String[] selSql = { // 테이블 select sql
			"select * from ASSESSMENT",
			"select * from ASSESSMENT_EST",
			"select * from ASSESSMENT_view",
			"select * from faq",
			"select * from FAVORITE",
			"select * from MEMBER",
			"select * from MEMBER_GRANT",
			"select * from movie",
			"select * from notice",
			"select * from tag",
			"select * from TAG_MAPPING"
	};
	
	private String[] creTblSql = { // 테이블 생성 sql
			"create table version(" + 		// 버전 테이블 sql
			"version_id varchar(5) primary key, " + 
			"current_version varchar2(10), " + 
			"update_date date default sysdate" + 
			")",
			
			"CREATE TABLE MEMBER(" +							// 회원 (유지상) 
			"    MEMBER_NUMBER NUMBER(10) PRIMARY KEY, " +		// 시퀀스 회원 번호 // 구분 용도, 세션 저장 
			"    MEMBER_EMAIL VARCHAR2(60) UNIQUE, " +			// 회원 이메일 // 로그인용 
			"    MEMBER_PWD VARCHAR2(200) NOT NULL, " +			// 회원 비밀번호 
			"    MEMBER_NAME VARCHAR2(30) NOT NULL, " +			// 회원 이름 
			"    MEMBER_NICKNAME VARCHAR2(30) UNIQUE, " +		// 회원 별명 // 별명 
			"    MEMBER_AGE NUMBER(3) NOT NULL, " +				// 회원 나이 
			"    MEMBER_GENDER CHAR(1) CHECK (MEMBER_GENDER IN ('M','F')), " + // 회원 성별
			"    MEMBER_INTRODUCE VARCHAR2(150) DEFAULT NULL, " + // 회원 소개 기본값 NULL 
			"    MEMBER_INDATE DATE DEFAULT SYSDATE, " +			// 회원 가입일 
			"    MEMBER_favorite_FLAG CHAR(1) DEFAULT 'Y' CHECK (MEMBER_favorite_FLAG IN ('Y','N') ), " + // 찜하기목록 공개 여부 
			"    MEMBER_ASSESSMENT_FLAG CHAR(1)  DEFAULT 'Y' CHECK (MEMBER_ASSESSMENT_FLAG IN ('Y','N')), " + // 평가 목록 공개 여부 
			"    MEMBER_INFO_FLAG CHAR(1)  DEFAULT 'N' CHECK (MEMBER_INFO_FLAG IN ('Y','N'))" + // 회원정보 공개여부 
			")",
			
			"create table movie(" + 
			"    movie_number number(10) primary key, " + 		// 영화 확인용 고유번호(시퀀스 사용)
			"    movie_poster varchar2(50) NOT NULL, " +		// 영화 이미지 주소 링크 
			"    movie_teaser varchar2(200) NOT NULL, " +		// 영화 티저영상 주소 링크 
			"    movie_title varchar2(100) NOT NULL, " +		// 영화 제목 
			"    movie_kind varchar2(50) NOT NULL, " +			// 영화 장르(' , '로 구분) 
			"    movie_director varchar2(30) NOT NULL, " +		// 영화 감독 
			"    movie_actor varchar2(200) NOT NULL, " +		// 주연 배우(' , '로 구분) 
			"    movie_grade varchar2(10) NOT NULL, " +			// 상영 등급 
			"    movie_time number(5) NOT NULL, " +				// 상영 시간(분) 
			"    movie_date varchar2(10) NOT NULL, " +			// 영화 개봉일 
			"    movie_youtube_url varchar2(200) NOT NULL, " +	// 유튜브 링크 
			"    movie_naver_url varchar2(200) NOT NULL, " +	// 네이버 링크 
			"    movie_indate date default sysdate, " +			// 영화 게시일 
			"    movie_content varchar2(1000) NOT NULL" +		// 영화 줄거리 
			")",
			
			"create table favorite (" +							// 찜한 영화  (장세진) ajax 구현 
			"    favorite_id varchar2(20) primary key, " +		// 번호 MEMBER_number + MOVIE_Number
			"    MEMBER_number NUMBER(10) REFERENCES MEMBER(MEMBER_number) on delete cascade, " + // 추가한 회원번호
			"    MOVIE_Number NUMBER(10) REFERENCES Movie(movie_number) on delete cascade, " + // 영화번호 
			"    favorite_REGDATE date default sysdate" +		// 등록일자 
			")",
			
			"create table ASSESSMENT (" + 						// 평점 (장세진)
			"    ASSESS_id VARCHAR2(20) primary key, " +		// 번호 MEMBER_number + MOVIE_number 
			"    MEMBER_number NUMBER(10)  REFERENCES MEMBER(MEMBER_number) on delete cascade, " + // 추가한 회원번호 
			"    MOVIE_number number(10) REFERENCES Movie(movie_number) on delete cascade, " + // 영화 제목 
			"    ASSESS_CONTENT varchar2(100) not null, " + 	// 내용
			"    ASSESS_STARS NUMBER(2) not null, " +			// 별점 
			"    ASSESS_REGDATE timestamp default sysdate" +			// 등록일자 
			")",
			
			"create table ASSESSMENT_EST (" + 					// 평가한 영화의 좋아요/싫어요(장세진)
			"    ASSESS_EST_id varchar2(20) primary key, " + 	// 번호 MEMBER_number + ASSESS_number
			"    MEMBER_number NUMBER(10) REFERENCES MEMBER(MEMBER_number) on delete cascade, " + // 추가한 회원번호 
			"    ASSESS_id VARCHAR2(20) REFERENCES assessment(ASSESS_id) on delete cascade, " + // 한줄평 번호 
			"    ASSESS_EST CHAR(1) CHECK (ASSESS_EST IN ('L','D')), " + // 좋아요L 싫어요D 
			"    ASSESS_EST_REGDATE timestamp default sysdate" +		// 등록일자 
			")",
			
//			"create or replace view ASSESSMENT_VIEW " +   	// assessment view 좋아요 싫어요 개수가 계산된 view (김정호)
//			"as " + 
//			"select a.ASSESS_ID assess_id, a.MEMBER_NUMBER, m.MEMBER_NICKNAME, a.MOVIE_NUMBER, a.ASSESS_CONTENT, a.ASSESS_STARS, a.ASSESS_REGDATE, nvl(l.est_l, 0) likes, nvl(d.est_d, 0) hates " + 
//			"from ASSESSMENT a " + 
//			"join MEMBER m " + 
//			"on a.MEMBER_NUMBER = m.MEMBER_NUMBER " + 
//			"left outer join (select ASSESS_id, count(ASSESS_EST) est_l from ASSESSMENT_EST where ASSESS_EST = 'L' group by ASSESS_id, ASSESS_EST) l " + 
//			"on a.ASSESS_ID = l.ASSESS_ID " + 
//			"left outer join (select ASSESS_id, count(ASSESS_EST) est_d from ASSESSMENT_EST where ASSESS_EST = 'D' group by ASSESS_id, ASSESS_EST) d " + 
//			"on a.ASSESS_ID = d.ASSESS_ID " + 
//			"group by a.ASSESS_ID",
			
			"create or replace view ASSESSMENT_VIEW " + // assessment view 좋아요 싫어요 개수가 계산된 view (김정호)
			"as " + 
			"select a.ASSESS_ID assess_id, a.MEMBER_NUMBER, mb.MEMBER_NICKNAME, a.MOVIE_NUMBER, mv.MOVIE_POSTER, mv.MOVIE_TITLE, NVL(av.AVGSTAR, 0) STARS, mv.MOVIE_KIND, mv.MOVIE_GRADE, mv.MOVIE_TIME, mv.MOVIE_DATE, a.ASSESS_CONTENT, a.ASSESS_STARS, a.ASSESS_REGDATE, nvl(l.est_l, 0) likes, nvl(d.est_d, 0) hates " + 
			"from ASSESSMENT a " + 
			"join MEMBER mb " + 
			"on a.MEMBER_NUMBER = mb.MEMBER_NUMBER " + 
			"left outer join (select ASSESS_id, count(ASSESS_EST) est_l from ASSESSMENT_EST where ASSESS_EST = 'L' group by ASSESS_id, ASSESS_EST) l " + 
			"on a.ASSESS_ID = l.ASSESS_ID " + 
			"left outer join (select ASSESS_id, count(ASSESS_EST) est_d from ASSESSMENT_EST where ASSESS_EST = 'D' group by ASSESS_id, ASSESS_EST) d " + 
			"on a.ASSESS_ID = d.ASSESS_ID " + 
			"join MOVIE mv " + 
			"on a.MOVIE_NUMBER = mv.MOVIE_NUMBER " +
			"left outer join (select MOVIE_NUMBER ,avg(ASSESS_STARS) AVGSTAR from ASSESSMENT group by MOVIE_NUMBER) av " + 
			"on mv.MOVIE_NUMBER = av.MOVIE_NUMBER " + 
			"group by a.ASSESS_ID",
			
			"create or replace view FAVORITE_VIEW " + 	// favorite view 영화 정보 더해짐(마이페이지 찜목록 용)
			"as " + 
			"select f.FAVORITE_ID, f.MEMBER_NUMBER, f.MOVIE_NUMBER, f.FAVORITE_REGDATE, mv.MOVIE_POSTER, mv.MOVIE_TITLE, mv.MOVIE_KIND, mv.MOVIE_GRADE, mv.MOVIE_TIME, mv.MOVIE_DATE " + 
			"from FAVORITE f " + 
			"join MOVIE mv " + 
			"on f.MOVIE_NUMBER = mv.MOVIE_NUMBER",
			
			"create or replace view SEARCH_VIEW " + 
			"as " + 
			"select mv.MOVIE_NUMBER, mv.MOVIE_POSTER, mv.MOVIE_TITLE, ROUND(NVL(av.AVGSTAR, 0), 1) STARS, mv.MOVIE_KIND, mv.MOVIE_DIRECTOR, mv.MOVIE_ACTOR, mv.MOVIE_GRADE, mv.MOVIE_TIME, mv.MOVIE_DATE, mv.MOVIE_CONTENT " + 
			"from MOVIE mv " + 
			"left outer join (select MOVIE_NUMBER ,avg(ASSESS_STARS) AVGSTAR from ASSESSMENT group by MOVIE_NUMBER) av " + 
			"on mv.MOVIE_NUMBER = av.MOVIE_NUMBER",
			
//			"create table tag (" + 							// 태그  (최원준)
//			"    tag_content varchar2(60) primary key, " +	// 태그 내용 
//			"    MOVIE_number varchar2(60) NOT NULL" +		// 영화번호 
//			")",
			
			"create table tag ( " + 						// 태그  (최원준)
			"tag_content varchar2(60) primary key, " + 		// 태그 내용 
			"MOVIE_number number(10), " + 		// 영화번호 
			"cnt number default 1" + 
			")",
			
			"create table tag_mapping (" + 					// 태그맵핑  (최원준)
			"    tag_content varchar2(60) REFERENCES TAG(tag_content) on delete cascade, " + // 태그 번호 
			"    MOVIE_number NUMBER(10) REFERENCES MOVIE(MOVIE_number) on delete cascade" + // 영화 번호 
			")",
			
			"create table faq (" + 							// FAQ  (박찬영)
			"faq_number number(10) primary key, " + 		// 번호
			"    MEMBER_number NUMBER(10)  REFERENCES MEMBER(MEMBER_number) on delete cascade, " + // 추가한 회원번호 
			"    faq_subject  VARCHAR2(60) NOT NULL, " + 	// FAQ 제목
			"    faq_content  VARCHAR2(2000) NOT NULL, " + 	// FAQ 내용
			"    faq_REGDATE date default sysdate" +		// 등록일자 
			")",
			
			"create table notice (" + 						// 공지사항  (박찬영)
			"    notice_number number(10) primary key, " +	// 번호 
			"    MEMBER_number NUMBER(10)  REFERENCES MEMBER(MEMBER_number) on delete cascade, " + // 추가한 회원번호
			"    notice_title  VARCHAR2(60) NOT NULL, " + 	// 공지사항 제목
			"    notice_content  VARCHAR2(2000) NOT NULL, " + // 공지사항 내용 
			"    notice_cnt  number(10) default 0, " +		// 공지사항 조회수 
			"    notice_REGDATE date default sysdate" +		// 등록일자 
			")"
	};
	
	private String[] creSeqSql = { // 시퀀스 생성 sql
			"create sequence MEMBER_SEQ increment by 1 start with 1 NOCYCLE", // 회원 시퀀스
			"create sequence MOVIE_SEQ increment by 1 start with 1 NOCYCLE", // 영화 시퀀스
			"create sequence FAQ_SEQ increment by 1 start with 1 NOCYCLE", // faq 시퀀스
			"create sequence NOTICE_SEQ increment by 1 start with 1 NOCYCLE", // 공지사항 시퀀스
			"create sequence ASSESS_SEQ increment by 1 start with 1000 NOCYCLE", // faq 시퀀스
			"create sequence ASSESS_EST_SEQ increment by 1 start with 1000 NOCYCLE" // 공지사항 시퀀스
	};
	
	// --- 이하 테스트 데이터 입력 sql ---
	private String[] memberTDBSql = { // 회원 테스트 데이터
			"INSERT INTO MEMBER VALUES (" +
			"MEMBER_SEQ.NEXTVAL, " + 	// 1
			"'admin@gmail.com', " +
			"'" + SecurityUtil.encryptSHA256("admin1234") + "', " +
			"'관리자', " +
			"'관리자', " +
			"32, " +
			"'M', " +
			"DEFAULT, " +				// 회원 소개 기본값 NULL
			"DEFAULT, " +				// 회원 가입일 
			"DEFAULT, " +				// 찜하기목록 공개 여부
			"DEFAULT, " +				// 평가 목록 공개 여부 
			"DEFAULT" +					// 회원정보 공개여부 
			")",
			
			"INSERT INTO MEMBER VALUES (" +
			"MEMBER_SEQ.NEXTVAL, " +	// 2
			"'user01@gmail.com', " +
			"'" + SecurityUtil.encryptSHA256("user1234") + "', " +
			"'유저01', " +
			"'유저01 별명', " +
			"22, " +
			"'M', " +
			"'안녕하세요. 저는 영화를 좋아합니다.', " + // 회원 소개 기본값 NULL
			"DEFAULT, " +				// 회원 가입일 
			"'Y', " +					// 찜하기목록 공개 여부
			"'Y', " +					// 평가 목록 공개 여부 
			"'Y'" +						// 회원정보 공개여부 
			")",
			
			"INSERT INTO MEMBER VALUES (" +
			"MEMBER_SEQ.NEXTVAL, " +
			"'user02@gmail.com', " +
			"'" + SecurityUtil.encryptSHA256("user1234") + "', " +
			"'유저02', " +
			"'유저02 별명', " +
			"26, " +
			"'M', " +
			"'저는 영화를 싫어합니다.', " + 	// 회원 소개 기본값 NULL
			"DEFAULT, " +				// 회원 가입일 
			"'N', " +					// 찜하기목록 공개 여부
			"'N', " +					// 평가 목록 공개 여부 
			"'N'" +						// 회원정보 공개여부 
			")",
			
			"INSERT INTO MEMBER VALUES (" +
			"MEMBER_SEQ.NEXTVAL, " +
			"'user03@gmail.com', " +
			"'" + SecurityUtil.encryptSHA256("user1234") + "', " +
			"'유저03', " +
			"'유저03 별명', " +
			"21, " +
			"'F', " +
			"'저는 영화를 싫어합니다.', " + 	// 회원 소개 기본값 NULL
			"DEFAULT, " +				// 회원 가입일 
			"'N', " +					// 찜하기목록 공개 여부
			"'Y', " +					// 평가 목록 공개 여부 
			"'N'" +						// 회원정보 공개여부 
			")",
			
			"INSERT INTO MEMBER VALUES (" +
			"MEMBER_SEQ.NEXTVAL, " +
			"'user04@gmail.com', " +
			"'" + SecurityUtil.encryptSHA256("user1234") + "', " +
			"'유저04', " +
			"'유저04 별명', " +
			"29, " +
			"'F', " +
			"'저는 영화를 싫어합니다.', " + 	// 회원 소개 기본값 NULL
			"DEFAULT, " +				// 회원 가입일 
			"'Y', " +					// 찜하기목록 공개 여부
			"'N', " +					// 평가 목록 공개 여부 
			"'Y'" +						// 회원정보 공개여부 
			")"
	};
	
	private String[] movieTDBSql = { // 영화 테스트 데이터
			"INSERT INTO MOVIE VALUES( " + 
			"    MOVIE_SEQ.NEXTVAL, " + 
			"    concat(concat('mov_poster_', MOVIE_SEQ.CURRVAL), '.jpg'), " + 
			"    'eOTbdg56eW0', " + 
			"    '아이 엠 어 히어로', " + 
			"    '액션, 모험, 좀비', " + 
			"    '사토 신스케', " + 
			"    '오오지즈미 요, 아리무라 카스미, 나가사와 마사미', " + 
			"    '19', " + 
			"    127, " + 
			"    '2015 년', " + 
			"    'https://www.youtube.com/watch?v=0bO2XDjKOXY', " + 
			"    'https://movie.naver.com/movie/bi/mi/basic.nhn?code=140693', " + 
			"    DEFAULT, " + 
			"    '“인간을 물어뜯어라!”일본 전역을 뒤덮은 정체불명의 바이러스 ‘ZQN’도심 곳곳은 사람을 물어뜯는 감염자들로 인해 대혼란이 이어지고, 우연히 살아남은 ‘히데오’와 몸의 반만 감염된 ‘히로미’는 감염자들을 피해 가까스로 생존자들의 안식처에 다다르게 된다. 하지만, 그 곳에서 예상치 못한 거대한 위협을 만나게 되는데…' " + 
			")",
			
			"INSERT INTO MOVIE VALUES( " + 
			"    MOVIE_SEQ.NEXTVAL, " + 
			"    concat(concat('mov_poster_', MOVIE_SEQ.CURRVAL), '.jpg'), " + 
			"    'FMxbzIThWNA', " + 
			"    '존 윅', " + 
			"    '액션, 모험', " + 
			"    '데이빗 레이치', " + 
			"    '키아누 리브스, 아드리안 팔리키, 윌렘 대포', " + 
			"    '19', " + 
			"    108, " + 
			"    '2014 년', " + 
			"    'https://www.youtube.com/watch?v=jnm-qwt6w_c', " + 
			"    'https://movie.naver.com/movie/bi/mi/basic.nhn?code=123300', " + 
			"    DEFAULT, " + 
			"    '그를 건드리지 말았어야 했다 " + 
			"    상대를 잘못 고른 적들을 향한 통쾌한 복수! " + 
			"    전설이라 불리던 킬러 ‘존 윅’(키아누 리브스)은 사랑하는 여인을 만나 결혼을 하면서 범죄의 세계에서 은퇴한다. 행복도 잠시, 투병 끝에 부인이 세상을 떠나고 그의 앞으로 부인이 죽기 전에 보낸 강아지 한 마리가 선물로 배달된다. 그러던 어느 날, 그의 집에 괴한들이 들이닥치는데...   " + 
			"    더 이상 잃을 것이 없다. 오직 너희만 죽인다!  " + 
			"    건드리지 말아야 할 그의 분노를 잘못 깨웠다.  " + 
			"    받은 것보다 더 돌려주는 통쾌한 복수, 존 윅의 거침없는 복수가 마침내 폭발한다!' " + 
			")",
			
			"INSERT INTO MOVIE VALUES( " + 
			"    MOVIE_SEQ.NEXTVAL, " + 
			"    concat(concat('mov_poster_', MOVIE_SEQ.CURRVAL), '.jpg'), " + 
			"    'QY4o16w6XuU', " + 
			"    '킹스 맨', " + 
			"    '액션, 모험, 드라마', " + 
			"    'Matthew Vaughn', " + 
			"    'Colin Firth, Samuel L. Jackson, Mark Strong, Taron Egerton, Michael Caine', " + 
			"    '19', " + 
			"    129, " + 
			"    '2015 년', " + 
			"    'https://www.youtube.com/watch?v=8vrelSzIcxo', " + 
			"    'https://movie.naver.com/movie/bi/mi/basic.nhn?code=114249', " + 
			"    DEFAULT, " + 
			"    '세상에서 가장 위험한 면접이 시작된다! 높은 IQ, 주니어 체조대회 2년 연속 우승! 그러나 학교 중퇴, 해병대 중도 하차. 동네 패싸움에 직장은 가져본 적도 없이 별볼일 없는 루저로 낙인 찍혔던 ‘그’가 ‘젠틀맨 스파이’로 전격 스카우트 됐다! 전설적 베테랑 요원 해리 하트(콜린 퍼스)는 경찰서에 구치된 에그시(태런 애거튼)를 구제한다. 탁월한 잠재력을 알아본 그는 에그시를 전설적 국제 비밀정보기구 ‘킹스맨’ 면접에 참여시킨다. 아버지 또한 ‘킹스맨’의 촉망 받는 요원이었으나 해리 하트를 살리기 위해 죽었다는 사실을 알게 된 에그시. 목숨을 앗아갈 만큼 위험천만한 훈련을 통과해야 하는 킹스맨 후보들. 최종 멤버 발탁을 눈 앞에 둔 에그시는 최고의 악당 발렌타인(사무엘 L. 잭슨)을 마주하게 되는데… 엑스맨: 퍼스트 클래스 감독과 마블 코믹스 인기작가의 만남! 스파이 액션의 새로운 시대가 열린다!' " + 
			")",
			
			"INSERT INTO MOVIE VALUES( " + 
			"    MOVIE_SEQ.NEXTVAL, " + 
			"    concat(concat('mov_poster_', MOVIE_SEQ.CURRVAL), '.jpg'), " + 
			"    'mFoCCX39rS4', " + 
			"    '메카닉', " + 
			"    '액션, 모험', " + 
			"    '사이먼 웨스트', " + 
			"    '제이슨 스타뎀, 벤 포스터', " + 
			"    '19', " + 
			"    92, " + 
			"    '2011 년', " + 
			"    'https://www.youtube.com/watch?v=wHpI9Vurby8', " + 
			"    'https://movie.naver.com/movie/bi/mi/basic.nhn?code=71769', " + 
			"    DEFAULT, " + 
			"    '아서 비숍(제이슨 스태덤)은 미국 정부의 의뢰를 받고 기술자(메카닉)로 불릴 정도로 목표물을 완벽하게 제거하는 최고의 킬러이다. 어느 날 그의 멘토이자 친한 친구인 해리(도널드 서덜랜드)가 살해되는 사건이 발생하자 그의 아들 스티븐(벤 포스터)이 아서를 찾아와 아버지에 대한 복수를 하겠다며 그에게 최고의 암살 기술을 가르켜 달라고한다. 그렇게 한 팀이 되어 사건을 해결하고 우정을 쌓아 나가지만 죽여야 할 대상이 적이 아닌 자신들이 되면서 알수 없는 위험에 빠지는데...' " + 
			")",
			
			"INSERT INTO MOVIE VALUES( " + 
			"    MOVIE_SEQ.NEXTVAL, " + 
			"    concat(concat('mov_poster_', MOVIE_SEQ.CURRVAL), '.jpg'), " + 
			"    'Brl96EVxixI', " + 
			"    '괴물의 아이', " + 
			"    '액션, 모험, 애니메이션', " + 
			"    '호소다 마모루', " + 
			"    '미야자키 아오이 (어린 큐타 (일본어 목소리) 역), " + 
			"    소메타니 쇼타 (청년 큐타 (일본어 목소리) 역), " + 
			"    아쿠쇼 코지 (쿠마테츠 (일본어 목소리) 역), " + 
			"    히로세 스즈 (카에데 (일본어 목소리) 역)', " + 
			"    '12', " + 
			"    119, " + 
			"    '2015 년', " + 
			"    'https://www.youtube.com/watch?v=uN2KGhIohoQ', " + 
			"    'https://movie.naver.com/movie/bi/mi/basic.nhn?code=134134', " + 
			"    DEFAULT, " + 
			"    '갈 곳을 잃고 시부야의 뒷골목을 배회하던 9살 소년 ‘렌’은 인간 세계로 나온 괴물 ‘쿠마테츠’와 마주치게 되고, 그를 쫓다 우연히 괴물의 세계에 발을 들이게 된다. ‘쿠마테츠’에게 ‘큐타’라는 새로운 이름을 얻게 된 소년은 그의 스승을 자처한 ‘쿠마테츠’와 함께 기묘한 동거를 시작하지만 너무도 다른 그들은 사사건건 부딪힌다. 함께하는 시간이 쌓여갈수록 둘은 서로를 진심으로 아끼며 변해가고, 진정한 가족의 정을 나누게 된다. 하지만 어느 새 훌쩍 커버린 ‘큐타’가 인간 세계에 관심을 갖게 되면서 예상치 못한 사건들이 벌어지기 시작하는데……' " + 
			")",
			
			"INSERT INTO MOVIE VALUES( " + 
			"    MOVIE_SEQ.NEXTVAL, " + 
			"    concat(concat('mov_poster_', MOVIE_SEQ.CURRVAL), '.jpg'), " + 
			"    'daObnmlsn9g', " + 
			"    '초능력자', " + 
			"    '드라마', " + 
			"    '김민석', " + 
			"    '고수, 강동원, 정은채', " + 
			"    '15', " + 
			"    112, " + 
			"    '2010 년', " + 
			"    'https://www.youtube.com/watch?v=Unae4tKxNWg', " + 
			"    'https://movie.naver.com/movie/bi/mi/basic.nhn?code=73344', " + 
			"    DEFAULT, " + 
			"    '세상이 멈췄다. 딱 한놈만 빼고... " + 
			"    규남이 일하는 작고 외진 전당포, \"유토피아\". 돈을 훔치러 들어온 초인이 사람들을 조종하기 시작하지만 초인의 통제를 벗어나 누군가가 힘겹게 움직이기 시작한다. 그 주인공은 바로 초능력이 통하지 않는 단 한 사람은 바로 규남이다.  " + 
			"     당황한 초인은 사람을 죽이고 그 장면은 고스란히 CCTV에 담겨진다. 그 날부터 초인은 자신의 조용한 삶을 한 순간에 날려버릴 결정적 단서를 손에 쥔 규남을, 규남은 자신의 평화로운 일상을 쑥대밭으로 만들어 놓은 초인을 쫓기 시작하는데...  " + 
			"     아무도 초인의 존재를 믿어주지 않는 가운데 홀로 괴물 같은 상대와 싸움을 벌여야 하는 규남과 자신의 능력이 통하지 않는 상대와 싸워야 하는 초인, 두 남자의 피할 수 없는 대결이 시작된다!' " + 
			")",
			
			"INSERT INTO MOVIE VALUES( " + 
			"    MOVIE_SEQ.NEXTVAL, " + 
			"    concat(concat('mov_poster_', MOVIE_SEQ.CURRVAL), '.jpg'), " + 
			"    'wthJe50uFU4', " + 
			"    '부산행', " + 
			"    '액션, 모험, 드라마', " + 
			"    '연상호', " + 
			"    '공유, 정유미, 마동석, 김수안,김의성', " + 
			"    '15', " + 
			"    118, " + 
			"    '2016 년', " + 
			"    'https://movie.naver.com/movie/search/result.nhn?query=%EB%B6%80%EC%82%B0%ED%96%89&section=all&ie=utf8', " + 
			"    'https://movie.naver.com/movie/bi/mi/basic.nhn?code=130966', " + 
			"    DEFAULT, " + 
			"    '정체불명의 바이러스가 전국으로 확산되고 대한민국 긴급재난경보령이 선포된 가운데, 열차에 몸을 실은 사람들은 단 하나의 안전한 도시 부산까지 살아가기 위한 치열한 사투를 벌이게 된다. 서울에서 부산까지의 거리 442KM. 지키고 싶은, 지켜야만 하는 사람들의 극한의 사투!' " + 
			")",
			
			"INSERT INTO MOVIE VALUES( " + 
			"    MOVIE_SEQ.NEXTVAL, " + 
			"    concat(concat('mov_poster_', MOVIE_SEQ.CURRVAL), '.jpg'), " + 
			"    'jRCow6UeRZU', " + 
			"    '워 크래프트', " + 
			"    '액션, 모험', " + 
			"    '던칸 존스', " + 
			"    '트레비스 핌멜, 폴라 패튼, 벤 포스터, 도미닉 쿠버, 로버트 카진스키, 토비 케벨, 벤 슈네처, 다니엘 우', " + 
			"    '12', " + 
			"    123, " + 
			"    '2016 년', " + 
			"    'https://www.youtube.com/watch?v=DXgd-WnYXEk', " + 
			"    'https://movie.naver.com/movie/bi/mi/basic.nhn?code=75006', " + 
			"    DEFAULT, " + 
			"    '평화롭던 세계인 아제로스는 자신들의 영역이 황폐해지자 다른 곳을 식민지로 만들기 위해 찾아온 침입자 오크들로 인해 전쟁의 위기를 맞이한다. 두 세계를 연결하는 포털이 열렸을 때 한쪽은 파괴될 위기에, 또 다른 한쪽은 멸종의 위기에 처한다. 양 진영을 대표하는 두 영웅은 자국의 운명을 걸고 서로 충돌하고, 권력과 희생이 뒤섞인 전쟁 속에서 영웅의 전설이 시작된다. (원제 - Warcraft) - 2016 ' " + 
			")",
			
			"INSERT INTO MOVIE VALUES( " + 
			"    MOVIE_SEQ.NEXTVAL, " + 
			"    concat(concat('mov_poster_', MOVIE_SEQ.CURRVAL), '.jpg'), " + 
			"    'wxYAg7to52A', " + 
			"    '황해', " + 
			"    '범죄, 미스테리, 서스펜스', " + 
			"    '나홍진', " + 
			"    '하정우 (김구남 역), 김윤석 (면정학 역), 조성하 (김태원 역), 이철민 (최성남 역), 곽도원 (김승현 교수 역)', " + 
			"    '19', " + 
			"    141, " + 
			"    '2010 년', " + 
			"    'https://www.youtube.com/watch?v=onHmZ4sTVvw', " + 
			"    'https://movie.naver.com/movie/bi/mi/basic.nhn?code=69986', " + 
			"    DEFAULT, " + 
			"    '황해를 건너 온 남자, 모두가 그를 쫒는다! " + 
			"    연변에서 택시를 모는 구남은 빚더미에 쌓여 구질구질한 일상을 살아간다. 한국으로 돈 벌러 간 아내는 6개월째 소식이 없고, 돈을 불리기 위해 마작판에 드나들지만 항상 잃을 뿐이다. 그러던 어느 날, 살인청부업자 면가에게서 한국 가서 사람 한 명 죽이고 오라는 제안을 받는다. 절박한 현실에서 선택의 여지가 없는 구남은 빚을 갚기 위해, 그리고 아내를 만나기 위해 황해를 건넌다. " + 
			"    매서운 바다를 건너 서울로 온 구남은 틈틈이 살인의 기회를 노리면서 동시에 아내의 행방을 수소문한다. 하지만, 자신의 눈 앞에서 목표물이 살해 당하는 것을 목격한 구남은 살인자 누명을 쓴 채 경찰의 추적을 피해 도망친다. 한편 청부살인을 의뢰한 태원은 모든 증거를 인멸하기 위해 구남을 처리하려 하고, 연변에 있던 면가 또한 황해를 건너와 구남을 쫓기 시작하는데...' " + 
			" )",
			
			"INSERT INTO MOVIE VALUES( " + 
			"    MOVIE_SEQ.NEXTVAL, " + 
			"    concat(concat('mov_poster_', MOVIE_SEQ.CURRVAL), '.jpg'), " + 
			"    'eIyDOXc1fS4', " + 
			"    '존 윅 2', " + 
			"    '액션, 모험, 범죄', " + 
			"    '데이빗 레이치', " + 
			"    '키아누 리브스, 브리짓 모이나한, 루비 로즈', " + 
			"    '19', " + 
			"    123, " + 
			"    '2017 년', " + 
			"    'https://www.youtube.com/watch?v=nlnPjlq83uA', " + 
			"    'https://movie.naver.com/movie/bi/mi/basic.nhn?code=143932', " + 
			"    DEFAULT, " + 
			"    '장전 완료! 준비는 끝났다! " + 
			"    업계 최고의 레전드 킬러 ‘존 윅’은 과거를 뒤로한 채 은퇴를 선언하지만,  " + 
			"    과거 자신의 목숨을 구했던 옛 동료와 피로 맺은 암살자들의 룰에 의해 로마로 향한다.  " + 
			"    ‘국제 암살자 연합’을 탈취하려는 옛 동료의 계획으로 ‘존 윅’은 함정에 빠지게 되고,  " + 
			"    전세계 암살자들의 총구는 그를 향하는데...' " + 
			")",
			
			"INSERT INTO MOVIE VALUES( " + 
			"    MOVIE_SEQ.NEXTVAL, " + 
			"    concat(concat('mov_poster_', MOVIE_SEQ.CURRVAL), '.jpg'), " + 
			"    '2M3tvUeQAVA', " + 
			"    '시카리오', " + 
			"    '미스테리, 서스펜스, 스릴러', " + 
			"    '드니 빌뇌브', " + 
			"    '에밀리 블런트, 베니치오 델 토로', " + 
			"    '19', " + 
			"    121, " + 
			"    '2015 년', " + 
			"    'https://www.youtube.com/watch?v=h3HgMC41t0I', " + 
			"    'https://movie.naver.com/movie/bi/mi/basic.nhn?code=125466', " + 
			"    DEFAULT, " + 
			"    '하나의 작전, 서로 다른 목표 " + 
			"    당신이 믿었던 정의가 파괴된다 " + 
			"    사상 최악의 마약 조직을 소탕하기 위해 미국 국경 무법지대에 모인 FBI요원 케이트(에밀리 블런트)와 CIA 소속의 작전 총 책임자 맷(조슈 브롤린), 그리고 작전의 컨설턴트로 투입된 정체불명의 남자 알레한드로(베니치오 델 토로). 누구도 믿을 수 없는 극한 상황 속, 세 명의 요원들은 서로 다른 목표를 향해 움직인다. ' " + 
			")",
			
			"INSERT INTO MOVIE VALUES( " + 
			"    MOVIE_SEQ.NEXTVAL, " + 
			"    concat(concat('mov_poster_', MOVIE_SEQ.CURRVAL), '.jpg'), " + 
			"    'pwYfmrbdkIw', " + 
			"    '인시던트', " + 
			"    '공상과학, 스릴러', " + 
			"    '아이작 에즈반', " + 
			"    '라울 멘데즈, 움베르토 부스토, 네일레아 노빈드', " + 
			"    '15', " + 
			"    101, " + 
			"    '2014 년', " + 
			"    'https://www.youtube.com/watch?v=SvMT1hdzWqs', " + 
			"    'https://movie.naver.com/movie/bi/mi/basic.nhn?code=130430', " + 
			"    DEFAULT, " + 
			"    '‘어느 날 세상이 뒤집히고 모든 시간 개념이 뒤섞인다?!’ " + 
			"    도둑질을 하며 살아가는 올리버와 카를로스 형제,  " + 
			"    몰리나형사에게 체포되기 직전 강력하게 저항하다 비상 계단으로 도망가지만 카를로스는 형사의 총에 맞는다. 그리고 귀신에 홀린 듯 내려가도 올라가도 출구가 없는 건물에 갇히고 만다. " + 
			"    한편, 휴가를 떠난 샌드라와 두 아이, 그녀의 애인 로베르토...  " + 
			"    로베르토는 천식이 있는 딸에게 실수로 알러지 음식을 먹이고, 설상가상 천식기를 망가뜨린다. 그리고는 20년이나 출퇴근 하던 길에서 헤매며 가족들에게 끔찍한 경험을 겪게 한다. " + 
			"    허나 이 모든것은 결코 우연히 일어난 것이 아니었다. " + 
			"    상상도 못했던 결말이 기다리고 있는 강렬한 이야기가 시작된다.' " + 
			")"
	};
	
	private String[] favrTDBSql = { // 찜하기 테스트 데이터
			"INSERT INTO FAVORITE VALUES(" + // user01@gmail.com 데이터
			"    '21'," + 
			"    2," + 
			"    1," + 
			"    DEFAULT" + 
			")",
			"INSERT INTO FAVORITE VALUES(" + // user01@gmail.com 데이터
			"    '22'," + 
			"    2," + 
			"    2," + 
			"    DEFAULT" + 
			")",
			"INSERT INTO FAVORITE VALUES(" + // user01@gmail.com 데이터
			"    '23'," + 
			"    2," + 
			"    3," + 
			"    DEFAULT" + 
			")"
	};
	
	private String[] asseTDBSql = { // 평점 테스트 데이터
			"INSERT INTO ASSESSMENT values('21', 2, 1, '재밌었습니다.', 10, default)",
			"INSERT INTO ASSESSMENT values('22', 2, 2, '별로...', 2, default)",
			"INSERT INTO ASSESSMENT values('23', 2, 3, '제 스타일은 아니지만...', 7, default)",
			"INSERT INTO ASSESSMENT values('31', 3, 1, '전 8점정도..', 8, default)",
			"INSERT INTO ASSESSMENT values('32', 3, 2, '전 5점정도..', 5, default)",
			"INSERT INTO ASSESSMENT values('33', 3, 3, '전 4점정도..', 4, default)"
	};
	
	private String[] asesTDBSql = { // 평점 좋아요 테스트 데이터
			"INSERT INTO ASSESSMENT_EST VALUES('321', 3, '21', 'L', DEFAULT)",
			"INSERT INTO ASSESSMENT_EST VALUES('322', 3, '22', 'D', DEFAULT)",
			"INSERT INTO ASSESSMENT_EST VALUES('323', 3, '23', 'L', DEFAULT)",
			"INSERT INTO ASSESSMENT_EST VALUES('421', 4, '21', 'L', DEFAULT)",
			"INSERT INTO ASSESSMENT_EST VALUES('422', 4, '22', 'D', DEFAULT)",
			"INSERT INTO ASSESSMENT_EST VALUES('423', 4, '23', 'L', DEFAULT)",
			"INSERT INTO ASSESSMENT_EST VALUES('521', 5, '21', 'L', DEFAULT)",
			"INSERT INTO ASSESSMENT_EST VALUES('522', 5, '22', 'D', DEFAULT)",
			"INSERT INTO ASSESSMENT_EST VALUES('523', 5, '23', 'D', DEFAULT)"
	};
	
	private String[] faqTDBSql = { // FAQ 테스트 데이터
			"INSERT INTO FAQ VALUES(faq_seq.NEXTVAL, 1, 'TEST FAQ TITLE 1', '1번 항목 테스트 내용입니다.', DEFAULT)",
			"INSERT INTO FAQ VALUES(faq_seq.NEXTVAL, 1, 'TEST FAQ TITLE 2', '2번 항목 테스트 내용입니다.', DEFAULT)",
			"INSERT INTO FAQ VALUES(faq_seq.NEXTVAL, 1, 'TEST FAQ TITLE 3', '3번 항목 테스트 내용입니다.', DEFAULT)"
	};
	
	private String[] noticeTDBSql = { // 공지사항 테스트 데이터
			"INSERT INTO NOTICE VALUES(" + 
			"    notice_seq.NEXTVAL, " + 
			"    1, " + 
			"    'TEST NOTICE TITLE 1', " + 
			"    '1번 항목 공지사항 테스트 내용입니다.', " + 
			"    DEFAULT, " + 
			"    DEFAULT" + 
			")",
			"INSERT INTO NOTICE VALUES(" + 
			"    notice_seq.NEXTVAL, " + 
			"    1, " + 
			"    'TEST NOTICE TITLE 2', " + 
			"    '2번 항목 공지사항 테스트 내용입니다.', " + 
			"    DEFAULT, " + 
			"    DEFAULT" + 
			")",
			"INSERT INTO NOTICE VALUES(" + 
			"    notice_seq.NEXTVAL, " + 
			"    1, " + 
			"    'TEST NOTICE TITLE 3', " + 
			"    '3번 항목 공지사항 테스트 내용입니다.', " + 
			"    DEFAULT, " + 
			"    DEFAULT" + 
			")"
	};
	
	private String[] tagTDBSql = {
			"insert into tag(tag_content, movie_number) values('액션', null)", 
			"insert into tag(tag_content, movie_number) values('모험', null)", 
			"insert into tag(tag_content, movie_number) values('멜로', null)", 
			"insert into tag(tag_content, movie_number) values('스릴러', null)", 
			"insert into tag(tag_content, movie_number) values('재난', null)", 
			"insert into tag(tag_content, movie_number) values('좀비', null)", 
			"insert into tag(tag_content, movie_number) values('SF', null)", 
			"insert into tag(tag_content, movie_number) values('전쟁', null)", 
			"insert into tag(tag_content, movie_number) values('코미디', null)", 
			"insert into tag(tag_content, movie_number, cnt) values('TAG1', 1, 10)", 
			"insert into tag(tag_content, movie_number, cnt) values('TAG2', 1, 15)", 
			"insert into tag(tag_content, movie_number, cnt) values('TAG3', 1, 20)", 
			"insert into tag(tag_content, movie_number, cnt) values('TAG4', 2, 100)", 
			"insert into tag(tag_content, movie_number, cnt) values('TAG5', 2, 5)", 
			"insert into tag(tag_content, movie_number, cnt) values('TAG6', 2, 7)", 
			"insert into tag(tag_content, movie_number, cnt) values('TAG7', 3, 8)", 
			"insert into tag(tag_content, movie_number, cnt) values('TAG8', 3, 10)", 
			"insert into tag(tag_content, movie_number, cnt) values('TAG9', 3, 15)", 
			"insert into tag(tag_content, movie_number, cnt) values('TAG10', 3, 22)"
	};
	
	private String[] tagMappingTDBSql = {
			"insert into tag_mapping values('액션', 1)",
			"insert into tag_mapping values('좀비', 1)",
			"insert into tag_mapping values('재난', 1)",
			"insert into tag_mapping values('스릴러', 1)",
			"insert into tag_mapping values('액션', 2)",
			"insert into tag_mapping values('모험', 2)",
			"insert into tag_mapping values('액션', 3)",
			"insert into tag_mapping values('모험', 3)",
			"insert into tag_mapping values('멜로', 3)",
			"insert into tag_mapping values('SF', 3)"
	};
	
	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;
	
	// 이하 메서드 //
	
	// 최신 버전 가져오기
	public String getCurVer() {
		return THIS_VERSION;
	}
	// 현재 설치 버전 정보 가져오기
	public String getNowVer() {
		String nowVer = "";
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(chkVerTblSql);
			rs = stmt.executeQuery();
			if(rs.next()) {
				if(rs.getInt("result") == 0) {
					nowVer = "None";
				} else {
					stmt = conn.prepareStatement(verSelSql);
					rs = stmt.executeQuery();
					if(rs.next()) {
						nowVer = rs.getString("current_version");
					}
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
			nowVer = "NotRuning";
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		return nowVer;
	}
	
	public void setupDatabase() {
		try {
			conn = JDBCUtil.getConnection();
			Arrays.stream(drpSql).forEach(sql -> {	// drop tables Stream
				try { stmt = conn.prepareStatement(sql); stmt.executeUpdate(); } catch(Exception e) { System.out.println("SQL 실행 안됨 : " + sql); }
			});
			Arrays.stream(creTblSql).forEach(sql -> {	// create tables Stream
				try { stmt = conn.prepareStatement(sql); stmt.executeUpdate(); } catch(Exception e) { System.out.println("SQL 실행 안됨 : " + sql); }
			});
			Arrays.stream(creSeqSql).forEach(sql -> {	// create seq Stream
				try { stmt = conn.prepareStatement(sql); stmt.executeUpdate(); } catch(Exception e) { System.out.println("SQL 실행 안됨 : " + sql); }
			});
			Arrays.stream(memberTDBSql).forEach(sql -> {	// insert memberDB Stream
				try { stmt = conn.prepareStatement(sql); stmt.executeUpdate(); } catch(Exception e) { System.out.println("SQL 실행 안됨 : " + sql); }
			});
			Arrays.stream(movieTDBSql).forEach(sql -> {	// insert movieDB Stream
				try { stmt = conn.prepareStatement(sql); stmt.executeUpdate(); } catch(Exception e) { System.out.println("SQL 실행 안됨 : " + sql); }
			});
			Arrays.stream(favrTDBSql).forEach(sql -> {	// insert favoriteDB Stream
				try { stmt = conn.prepareStatement(sql); stmt.executeUpdate(); } catch(Exception e) { System.out.println("SQL 실행 안됨 : " + sql); }
			});
//			Arrays.stream(asseTDBSql).forEach(sql -> {	// insert assess Stream
//				try { stmt = conn.prepareStatement(sql); stmt.executeUpdate(); } catch(Exception e) { System.out.println("SQL 실행 안됨 : " + sql); }
//			});
//			Arrays.stream(asesTDBSql).forEach(sql -> {	// insert assess_est Stream
//				try { stmt = conn.prepareStatement(sql); stmt.executeUpdate(); } catch(Exception e) { System.out.println("SQL 실행 안됨 : " + sql); }
//			});
			Arrays.stream(faqTDBSql).forEach(sql -> {	// insert faq Stream
				try { stmt = conn.prepareStatement(sql); stmt.executeUpdate(); } catch(Exception e) { System.out.println("SQL 실행 안됨 : " + sql); }
			});
			Arrays.stream(noticeTDBSql).forEach(sql -> {	// insert notice Stream
				try { stmt = conn.prepareStatement(sql); stmt.executeUpdate(); } catch(Exception e) { System.out.println("SQL 실행 안됨 : " + sql); }
			});
			Arrays.stream(tagTDBSql).forEach(sql -> {	// insert notice Stream
				try { stmt = conn.prepareStatement(sql); stmt.executeUpdate(); } catch(Exception e) { System.out.println("SQL 실행 안됨 : " + sql); }
			});
			Arrays.stream(tagMappingTDBSql).forEach(sql -> {	// insert notice Stream
				try { stmt = conn.prepareStatement(sql); stmt.executeUpdate(); } catch(Exception e) { System.out.println("SQL 실행 안됨 : " + sql); }
			});
			
			stmt = conn.prepareStatement(verInsertSql);
			stmt.setString(1, THIS_VERSION);
			stmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
	}
	
	public void dropDatabase() {
		try {
			conn = JDBCUtil.getConnection();
			Arrays.stream(drpSql).forEach(sql -> {	// drop tables Stream
				try { stmt = conn.prepareStatement(sql); stmt.executeUpdate(); } catch(Exception e) { System.out.println("SQL 실행 안됨 : " + sql); }
			});
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
	}
	
}
