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
<title> update Form </title>
<script src="${ contextPath }/js/jquery.2.1.3.min.js"></script>
<script src="${ contextPath }/js/jquery-ui.min.js"></script>
<script src="${ contextPath }/js/jquery.easing.1.3.js"></script>
<script src="${ contextPath }/js/jquery.mousewheel.min.js"></script>
<script src="${ contextPath }/js/public.js"></script>
<script src="https://use.fontawesome.com/releases/v5.8.0/js/all.js"></script>
<link rel="stylesheet" href="${ contextPath }/css/public.css">
<!-- 담당자 js, css -->

<script src="${ contextPath }/js/movie/mov_update.js"></script>
<link rel="stylesheet" href="${ contextPath }/css/movie/mov_update.css">

<!-- //담당자 js, css -->
</head>
<body>
<jsp:include page="../_header.jsp"></jsp:include>
<!-- 담당자 내용 -->

	<h1> 영화 정보 입력 </h1>

	<div id="movUpdate">

		<form action="update.do" method="post">
		
			<table>
			
				<tr>
					<td colspan="2">
						최대한 자세하게 정보를 입력해주세요.
					</td>
				</tr>
				<tr>
					<td>
						표지 주소
					</td>
					<td>
						<input type="text" name="moviePoster">
					</td>
				</tr>
				<tr>
					<td>
						티저 링크
					</td>
					<td>
						<input type="text" name="movieTeaser">
					</td>
				</tr>
				<tr>
					<td>
						영화 제목
					</td>
					<td>
						<input type="text" name="movieTitle">
					</td>
				</tr>
				<tr>
					<td>
						영화 분류
					</td>
					<td>
						<input type="text" name="movieKind">
					</td>
				</tr>
				<tr>
					<td>
						감독 이름
					</td>
					<td>
						<input type="text" name="movieDirector">
					</td>
				</tr>
				<tr>
					<td>
						주연 배우
					</td>
					<td>
						<input type="text" name="movieActor">
					</td>
				</tr>
				<tr>
					<td>
						상영 등급
					</td>
					<td>
						<input type="text" name="movieGrade">
					</td>
				</tr>
				<tr>
					<td>
						상영 시간
					</td>
					<td>
						<input type="text" name="movieTime">
					</td>
				</tr>
				<tr>
					<td>
						개봉 일자
					</td>
					<td>
						<input type="text" name="movieDate">
					</td>
				</tr>
				<tr>
					<td>
						유튜브 링크
					</td>
					<td>
						<input type="text" name="movieYoutubeUrl">
					</td>
				</tr>
				<tr>
					<td>
						네이버 링크
					</td>
					<td>
						<input type="text" name="movieNaverUrl">
					</td>
				</tr>
				<tr>
					<td>
						영화 내용
					</td>
					<td>
						<input type="text" name="movieContent">
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="입력 완료">
					</td>
				</tr>
				
			</table>
		
		</form>
	
	</div>

<!-- 담당자 내용 -->
<jsp:include page="../_footer.jsp"></jsp:include>
</body>
</html>