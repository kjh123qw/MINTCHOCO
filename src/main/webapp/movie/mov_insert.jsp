<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
/*
	:: 페이지 명 ::
	담당자 : 천세문
*/
%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="contextPath" value="${ pageContext.request.contextPath }" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> 영화 정보 입력 </title>
<script src="${ contextPath }/js/jquery.2.1.3.min.js"></script>
<script src="${ contextPath }/js/jquery-ui.min.js"></script>
<script src="${ contextPath }/js/jquery.easing.1.3.js"></script>
<script src="${ contextPath }/js/jquery.mousewheel.min.js"></script>
<script src="${ contextPath }/js/public.js"></script>
<script src="https://use.fontawesome.com/releases/v5.8.0/js/all.js"></script>
<link rel="stylesheet" href="${ contextPath }/css/public.css">
<!-- 담당자 js, css -->

<script src="${ contextPath }/js/movie/mov_insert.js"></script>
<link rel="stylesheet" href="${ contextPath }/css/movie/mov_insert.css">

<!-- //담당자 js, css -->
</head>
<jsp:include page="../_header.jsp"></jsp:include>
<body>
<!-- 담당자 내용 -->

	<h1><a href="${ contextPath }/movie/insertForm.do"> 영화 정보 입력 </a></h1>

	<div id="movInsert">

		<form action="insert.do" method="post">
		
			<table>
				<tr>
					<td colspan="2">
						<h3> 내용을 빠짐없이 입력 해주시기 바랍니다. </h3>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<h4> Ex) 에 표시된 ''의 안과 같이 입력</h4>
					</td>
				</tr>
				<tr>
					<td>
						
					</td>
					<td>
						<input type="text" hidden="hidden">
					</td>
				</tr>
				<tr>
					<td>
						티저 링크
					</td>
					<td>
						<input type="text" name="movieTeaser" placeholder="Ex) Youtube = 'TtD7xcruYAk' only">
					</td>
				</tr>
				<tr>
					<td>
						영화 제목
					</td>
					<td>
						<input type="text" name="movieTitle" placeholder="Ex) '여신전생 페르소나 3' Korean only">
					</td>
				</tr>
				<tr>
					<td>
						영화 분류
					</td>
					<td>
						<input type="text" name="movieKind" placeholder="Ex) '공상과학, 스릴러, 좀비, 액션'">
					</td>
				</tr>
				<tr>
					<td>
						감독 이름
					</td>
					<td>
						<input type="text" name="movieDirector" placeholder="Ex) '스티븐 모팻, 프랭크 다라본트'">
					</td>
				</tr>
				<tr>
					<td>
						주연 배우
					</td>
					<td>
						<input type="text" name="movieActor" placeholder="Ex) '카토 메구미, 잇시키 이로하'">
					</td>
				</tr>
				<tr>
					<td>
						상영 등급
					</td>
					<td>
						<input type="text" name="movieGrade" placeholder="Ex) 'ALL', '12', '15', '19' only">
					</td>
				</tr>
				<tr>
					<td>
						상영 시간
					</td>
					<td>
						<input type="text" name="movieTime" placeholder="Ex) '114' minute rounds only">
					</td>
				</tr>
				<tr>
					<td>
						개봉 연도
					</td>
					<td>
						<input type="text" name="movieDate" placeholder="Ex) '2020 년' year + 년 only">
					</td>
				</tr>
				<tr>
					<td>
						유튜브 링크
					</td>
					<td>
						<input type="text" name="movieYoutubeUrl" placeholder="Ex) 'https://youtu.be/TtD7xcruYAk'">
					</td>
				</tr>
				<tr>
					<td>
						네이버 링크
					</td>
					<td>
						<input type="text" name="movieNaverUrl" placeholder="Ex) 전체 주소를 입력해주시기 바랍니다.">
					</td>
				</tr>
				<tr>
					<td>
						영화 내용
					</td>
					<td>
						<input type="text" name="movieContent" placeholder="Ex) 개인적인 의견은 삼가 해주시기 바랍니다.">
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input class="button" type="submit" value="입력 완료">
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input class="button" type="button" value="입력 취소" onclick="history.go(-1)">
					</td>
				</tr>
			</table>
		
		</form>
	
	</div>

<!-- 담당자 내용 -->
</body>
<jsp:include page="../_footer.jsp"></jsp:include>
</html>