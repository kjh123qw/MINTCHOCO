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
<script src="${ contextPath }/js/movie/mov_rank.js"></script>

<!-- //담당자 js, css -->
</head>
<body>
	<%@ include file="../_header.jsp"%>
	<!-- 담당자 내용 -->
	<div id="rankWrap">
		<h1>영화 순위</h1>
		<hr>
		<div id="mobMovieList">
			<div id="mobRankHeader">
				<div class="rank-blank-underline"></div>
				<div id="megaBoxBtn" class="rank-header-mega rank-header-selected">메가 박스 순위</div>
				<div id="cgvBtn" class="rank-header-cgv">CGV 순위</div>
				<div class="rank-blank-underline"></div>
			</div>
			<table id="mobMegaList" class="rank-table">
				<c:forEach var="movie" items="${megaMovieList}" varStatus="status">
					<c:if test="${ status.count <= 10 }">
						<tr>
							<th>${ status.count }</th>
							<c:if test="${ status.count <= 3 }">
								<td><img src="${movie.moviePoster}" alt="${ movie.movieTitle }"></td>
								<td>${ movie.movieTitle }</td>
							</c:if>
							<c:if test="${ status.count > 3 }">
								<td colspan="2">${ movie.movieTitle }</td>
							</c:if>
						</tr>
					</c:if>
				</c:forEach>
			</table>
			<table id="mobCgvList" class="rank-table" style="display: none;">
				<c:forEach var="movie" items="${cgvMovieList}" varStatus="status">
					<c:if test="${ status.count <= 10 }">
						<tr>
							<th>${ status.count }</th>
							<c:if test="${ status.count <= 3 }">
								<td><img src="${movie.moviePoster}" alt="${ movie.movieTitle }"></td>
								<td>${ movie.movieTitle }</td>
							</c:if>
							<c:if test="${ status.count > 3 }">
								<td colspan="2">${ movie.movieTitle }</td>
							</c:if>
						</tr>
					</c:if>
				</c:forEach>
			</table>
		</div>
		<div id="movieList" style="display: none;">
			<div id="rankHeader">
				<div id="megaBoxHeader">메가 박스 순위</div>
				<div id="cgvHeader">CGV 순위</div>
			</div>
			<div id="megaList">
				<table class="rank-table">
					<c:forEach var="movie" items="${megaMovieList}" varStatus="status">
						<c:if test="${ status.count <= 10 }">
							<tr>
								<th>${ status.count }</th>
								<c:if test="${ status.count <= 3 }">
									<td><img src="${movie.moviePoster}" alt="${ movie.movieTitle }"></td>
									<td>${ movie.movieTitle }</td>
								</c:if>
								<c:if test="${ status.count > 3 }">
									<td colspan="2">${ movie.movieTitle }</td>
								</c:if>
							</tr>
						</c:if>
					</c:forEach>
				</table>
			</div>
			<div id="cgvList">
				<table class="rank-table">
					<c:forEach var="movie" items="${cgvMovieList}" varStatus="status">
						<c:if test="${ status.count <= 10 }">
							<tr>
								<th>${ status.count }</th>
								<c:if test="${ status.count <= 3 }">
									<td><img src="${movie.moviePoster}" alt="${ movie.movieTitle }"></td>
									<td>${ movie.movieTitle }</td>
								</c:if>
								<c:if test="${ status.count > 3 }">
									<td colspan="2">${ movie.movieTitle }</td>
								</c:if>
							</tr>
						</c:if>
					</c:forEach>
				</table>
			</div>
		</div>
		<hr>
	</div>
	<!-- 담당자 내용 -->
	<%@ include file="../_footer.jsp"%>
</body>
</html>