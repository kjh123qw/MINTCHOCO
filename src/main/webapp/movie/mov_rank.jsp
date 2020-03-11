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
<title>MOV_RANK.JSP</title>
<script src="${ contextPath }/js/jquery.2.1.3.min.js"></script>
<script src="${ contextPath }/js/jquery-ui.min.js"></script>
<script src="${ contextPath }/js/jquery.easing.1.3.js"></script>
<script src="${ contextPath }/js/jquery.mousewheel.min.js"></script>
<script src="${ contextPath }/js/public.js"></script>
<link rel="stylesheet" href="${ contextPath }/css/public.css">
<!-- 담당자 js/css -->

<link rel="stylesheet" href="${ contextPath }/css/movie/mov_rank.css">

<!-- //담당자 js, css -->
</head>
<body>
	<%@ include file="../_header.jsp"%>
	<!-- 담당자 내용 -->

	<div id="mov_list">
		<div class="bg">
			<c:forEach var="movie" items="${movieList}">
				<center class="movie  mov_float">
					<img src="${movie.moviePoster}" alt="${ movie.movieTitle }">
					<p>${ movie.movieTitle }</p>
				</center>
			</c:forEach>
			<div class="clear"></div>
		</div>

	</div>

	<!-- 담당자 내용 -->
	<%@ include file="../_footer.jsp"%>
</body>
</html>