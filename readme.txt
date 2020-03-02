KOSMO7-fin Project
[NOTICE FILE]

::: 개발환경 :::
 - Java10(설정 되어 있음
 - spring ver 4.2.4 (github 프로젝트에 구축되어 있음)
 - H2 ver 1.4.200 (설치 필요, jar는 프로젝트에 구축되어 있음)
 - jquery ver 2.1.3 (js 파일에 있음)

::: DB 셋팅 방법 :::
1. GIT clone으로 워크스페이스에 프로젝트 다운
2. 서버에 프로젝트(MINTCHOCO)ADD 후 서버 구동
3. /mintchoco/db.do 접속 후 설치

::: 프로젝트 안내 :::
1. 조원 6명
  - 김정호, 박찬영, 유지상, 장세진, 천세문, 최원준

2. 모든 조원은 본 파일("readme.txt")을 숙지하여야 함
  - 본인이 숙지하지 못한것으로 실수를 하여 다른 조원에게 피해를 주지 않도록
  - 못하겠으면 미리 말할 것(기한 다 돼서 말하는 일은 없도록)
  - 다른조의 파트에 참견 금지. 다른조의 파트에 변경이 필요할 경우 리더를 통해서 해결 할 것.

3. 메인 카테고리 : MV-영화, AS-평점, MB-회원, AD-관리자, SR-검색, ET-기타
  - '메인 카테고리'란 홈페이지 전체를 6가지로 세분화 하여 기능상으로 묶은 개념
  - 기능상으로 묶었기 때문에 사이트 맵과 다를 수 있음

4. 업무 분담
  - 0조[김정호]            : 전체 관리 및 디자인 관여, DB 관리 시스템, 영화 순위
  - 1조[유지상, 장세진] : 메인, 로그인, 마이페이지
  - 2조[천세문]            : 영화 상세피이지, 영화 목록
  - 3조[최원준]            : 검색
  - 4조[박찬영]            : 공지사항, FAQ, 서비스 안내

5. 작업 관련
  - 시작시 담당자 란에 본인 이름입력
  - <!-- 담당자 JS, CSS --> 와 <!-- 담당자 JS, CSS --> 사이에 link rel, script 입력
  - <!-- 담당자 내용 --> 와 <!-- // 담당자 내용 --> 사이에 HTML 내용 입력
  - 본인이 허락된 파일 외에는 담당자 허락을 구한 뒤 조장과 상의하여 만들것

6. class, id 네이밍 방법
  - class : 소문자로 작성, 두 단어 이상으로 이루어질 경우 "-"(하이픈) 사용하여 나눔
	ex) member-id, title-goods, title-notice
  - id      : camel case(카멜 표기법) 사용
	ex) headerLogo, contentBox

7. 예약 class, id [사용금지]
  - class
	* common-*
	* header-*
	* footer-*
  - id
	* header
	* contentBox
	* footer
  - id, class
  	* __ (언더바 두개로 시작되는 것)

8. DB table 수정 권한(권한 근거는 해당 테이블에 대한 코드 작성 여부)
  - 김정호 : ASSESSMENT, ASSESSMENT_EST, MOVIE, NOTICE, FAQ, FAVORITE
  - 박찬영 : NOTICE, FAQ
  - 유지상 : MEMBER
  - 장세진 : MEMBER
  - 천세문 : MOVIE
  - 최원준 : TAG, TAG_MAPPING