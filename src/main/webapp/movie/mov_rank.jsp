<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
/*
	:: 순위 ::
	담당자 : 최원준
*/
%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="contextPath" value="${ pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>영화 순위</title>
<script src="${ contextPath }/js/jquery.2.1.3.min.js"></script>
<script src="${ contextPath }/js/jquery-ui.min.js"></script>
<script src="${ contextPath }/js/jquery.easing.1.3.js"></script>
<script src="${ contextPath }/js/jquery.mousewheel.min.js"></script>
<script src="${ contextPath }/js/public.js"></script>
<script src="https://use.fontawesome.com/releases/v5.8.0/js/all.js"></script>
<link rel="stylesheet" href="${ contextPath }/css/public.css">
<!-- 담당자 js/css -->

<link rel="stylesheet" href="${ contextPath }/css/movie/mov_rank.css">

<!-- //담당자 js, css -->
</head>
<body>
	<%@ include file="../_header.jsp"%>
	<!-- 담당자 내용 -->
	<table>
		<c:forEach var="movie" items="${movieList}" varStatus="status">
			<c:if test="${ status.count <= 10 }">
				<tr>
					<td>${ status.count }</td>
					<c:choose>
						<c:when test="${ status.count <= 3 }">
							<td><img src="${movie.moviePoster}" alt="${ movie.movieTitle }" width="100px"></td>
							<td>${ movie.movieTitle }</td>
						</c:when>
						<c:otherwise>
							<td></td>
							<td colspan="2">${ movie.movieTitle }</td>
						</c:otherwise>
					</c:choose>
				</tr>
			</c:if>
		</c:forEach>
	</table>
	
	
<%/*
	<div id="mov_list">
		<div class="bg">
			<c:forEach var="movie" items="${movieList}">
				<div class="movie mov_float">
					<img src="${movie.moviePoster}" alt="${ movie.movieTitle }">
					<p>${ movie.movieTitle }</p>
				</div>
			</c:forEach>
			<div class="clear"></div>
		</div>
	</div>
	*/ %>
	<!-- 담당자 내용 -->
	<%@ include file="../_footer.jsp"%>
</body>
</html>