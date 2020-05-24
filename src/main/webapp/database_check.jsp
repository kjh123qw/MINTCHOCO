<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
/*
	:: 데이타 베이스 ::
	담당자 : 김정호
<c:if test="${ sessionScope.memberInfo.number != 1 }">
	<c:redirect url="/main.do"></c:redirect>
</c:if>
*/
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>::::: DATABASE CHECK PAGE :::::</title>
<script src="./js/database.js"></script>
<link rel="stylesheet" href="./css/public.css">
<style>
	html {
		background-color: #eee;
	}
	table {
		width: 800px;
		margin: 0 auto;
	}
	h1, h2 { text-align: center; }
	.infotbl th, .infotbl td {
		width: 400px;
		height: 28px;
		font-size: 14px;
	}
	.setuptbl th, .setuptbl td {
		width: 400px;
		height: 70px;
		font-size: 24px;
	}
	.setup {
		width: 400px;
		height: 60px;
		font-size: 24px;
		cursor: pointer;
	}
	.delete {
		width: 300px;
		height: 60px;
		font-size: 24px;
		background-color: red;
		color: white;
		cursor: pointer;
	}
	.crawling {
		width: 300px;
		height: 60px;
		font-size: 24px;
		background-color: deeppink;
		color: white;
		cursor: pointer;
	}
	span {
		font-size: 24px;
	}
	p {
		width: 800px;
		margin: 0 auto;
		padding: 10px;
		border: 1px dotted gray;
		text-align: left;
		background-color: #ddd;
	}
</style>
</head>
<body>
	<h1>DB 체크 페이지입니다.</h1>
	<h2>ご苦労様です。</h2>
	<p>
		이 페이지에서는 데이터 베이스 관리를 할 수 있습니다.<br>
		데이터베이스를 설치하면 <b>데이터 테이블</b>과 <b>테스트 데이터</b>를<br>
		한방에 설치할 수 있습니다.<br>
		데이터가 꼬였을 경우에는 삭제하고 다시 설치해보세요.<br>
		최신버전을 설치하면 기존에 있는 데이터는 전부 날아가기때문에 주의해주세요.<br>
		담당 테이블 구조 변경과 테스트 데이터 입력은 "김정호"님 에게 연락해주세요.<br>
	</p>
	<hr>
	<table border="1" class="infotbl">
		<tr>
			<th colspan="2">
				현재 사용 DB 는 <b>H2(1.4.200)</b> 입니다.
			</th>
		</tr>
		<tr>
			<th>드라이버 클래스</th>
			<td><b>org.h2.Driver</b></td>
		</tr>
		<tr>
			<th>JDBC URL</th>
			<td><b>jdbc:h2:tcp://localhost/~/test</b></td>
		</tr>
		<tr>
			<th>사용자 명</th>
			<td><b>sa</b></td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td><i>[null]</i></td>
		</tr>
	</table>
	<hr>
	<table border="1" class="setuptbl">
				<tr>
					<th>현재 설치된 버전</th>
					<th>최신 릴리즈 버전</th>
				</tr>
				<tr>
					<td style="text-align: center;">${ nowVersion }</td>
					<td style="text-align: center;">${ curVersion }</td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: center;">
						<c:choose>
							<c:when test="${ nowVersion eq curVersion }">
								<span>DB가 최신버전입니다.</span>
							</c:when>
							<c:otherwise>
								<input type="button" value="DB 설치하기" class="setup" onclick="submitSetup();">
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<c:choose>
						<c:when test="${ nowVersion eq 'None' }">
							<td colspan="2" style="text-align: center;">
								<span>DB가 설치되어 있지 않습니다.</span>
							</td>
						</c:when>
						<c:otherwise>
							<td colspan="2" style="text-align: center;">
								<input type="button" value="DB 전체 삭제" class="delete" onclick="submitDelete();">
							</td>
						</c:otherwise>
					</c:choose>
				</tr>
				<tr>
					<td colspan="2" style="text-align: center;">
						<button class="crawling" onclick="location.href='http://localhost:8080/mintchoco/dbCrawling.do'">DB 크롤링</button>
					</td>
				</tr>
	</table>
</body>
</html>