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
<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title> 기존 영화 삭제 </title>
<script src="${ contextPath }/js/jquery.2.1.3.min.js"></script>
<script src="${ contextPath }/js/jquery-ui.min.js"></script>
<script src="${ contextPath }/js/jquery.easing.1.3.js"></script>
<script src="${ contextPath }/js/jquery.mousewheel.min.js"></script>
<script src="${ contextPath }/js/public.js"></script>
<script src="https://use.fontawesome.com/releases/v5.8.0/js/all.js"></script>
<link rel="stylesheet" href="${ contextPath }/css/public.css">
<!-- 담당자 js, css -->

<script src="${ contextPath }/js/movie/mov_delete.js"></script>
<link rel="stylesheet" href="${ contextPath }/css/movie/mov_delete.css">

<!-- //담당자 js, css -->
</head>
<jsp:include page="../_header.jsp"></jsp:include>
<body>
<!-- 담당자 내용 -->

	<h1><a href="${ contextPath }/movie/deleteForm.do?movieNumber=${ movie.movieNumber }"> 기존 영화 삭제 </a></h1>

	<div id="movDelete">
	
		<form action="delete.do?movieNumber=${ movie.movieNumber }" method="post">
		
			<table>
			
				<tr>
					<td colspan="2">
						<h3> 정말로 이 영화를 삭제하시겠습니까? </h3>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<h4> Ex) 삭제된 영화는 복구되지 않습니다! </h4>
					</td>
				</tr>
				<tr>
					<td>
						
					</td>
					<td>
						<input type="text" name="movieNumber" value="${ movie.movieNumber }" hidden="hidden">
					</td>
				</tr>
				<tr>
					<td>
						티저 링크
					</td>
					<td>
						<input type="text" name="movieTeaser" value="${ movie.movieTeaser }" readonly="readonly">
					</td>
				</tr>
				<tr>
					<td>
						영화 제목
					</td>
					<td>
						<input type="text" name="movieTitle" value="${ movie.movieTitle }" readonly="readonly">
					</td>
				</tr>
				<tr>
					<td>
						영화 분류
					</td>
					<td>
						<input type="text" name="movieKind" value="${ movie.movieKind }" readonly="readonly">
					</td>
				</tr>
				<tr>
					<td>
						감독 이름
					</td>
					<td>
						<input type="text" name="movieDirector" value="${ movie.movieDirector }" readonly="readonly">
					</td>
				</tr>
				<tr>
					<td>
						주연 배우
					</td>
					<td>
						<input type="text" name="movieActor" value="${ movie.movieActor }" readonly="readonly">
					</td>
				</tr>
				<tr>
					<td>
						상영 등급
					</td>
					<td>
						<input type="text" name="movieGrade" value="${ movie.movieGrade }" readonly="readonly">
					</td>
				</tr>
				<tr>
					<td>
						상영 시간
					</td>
					<td>
						<input type="text" name="movieTime" value="${ movie.movieTime }" readonly="readonly">
					</td>
				</tr>
				<tr>
					<td>
						개봉 연도
					</td>
					<td>
						<input type="text" name="movieDate" value="${ movie.movieDate }" readonly="readonly">
					</td>
				</tr>
				<tr>
					<td>
						유튜브 링크
					</td>
					<td>
						<input type="text" name="movieYoutubeUrl" value="${ movie.movieYoutubeUrl }" readonly="readonly">
					</td>
				</tr>
				<tr>
					<td>
						네이버 링크
					</td>
					<td>
						<input type="text" name="movieNaverUrl" value="${ movie.movieNaverUrl }" readonly="readonly">
					</td>
				</tr>
				<tr>
					<td>
						영화 내용
					</td>
					<td>
						<input type="text" name="movieContent" value="${ movie.movieContent }" readonly="readonly">
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input class="button" type="submit" value="삭제 완료">
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input class="button" type="button" value="삭제 취소" onclick="history.go(-1)">
					</td>
				</tr>
			</table>
		
		</form>
	
	</div>

<!-- 담당자 내용 -->
</body>
<jsp:include page="../_footer.jsp"></jsp:include>
</html>