<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
/*
	담당 : 천세문
	
	기본 화면 완성
	
*/
%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="contextPath" value="${ pageContext.request.contextPath }" />

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<meta charset="UTF-8">
<title>DETAILS - ${ movie.movieTitle }</title>

<script src="${ contextPath }/js/jquery.2.1.3.min.js"></script>
<script src="${ contextPath }/js/jquery-ui.min.js"></script>
<script src="${ contextPath }/js/jquery.easing.1.3.js"></script>
<script src="${ contextPath }/js/jquery.mousewheel.min.js"></script>
<script src="${ contextPath }/js/public.js"></script>
<script src="https://use.fontawesome.com/releases/v5.8.0/js/all.js"></script>
<link rel="stylesheet" href="${ contextPath }/css/public.css">
<!-- 담당자 js/css -->

<script src="${ contextPath }/js/movie/mov_detail.js"></script>
<link rel="stylesheet" href="${ contextPath }/css/movie/mov_detail.css">

<script src="${ contextPath }/js/movie/mov_favorite.js"></script>
<script src="${ contextPath }/js/movie/rating.js"></script>
<link rel="stylesheet" href="${ contextPath }/css/movie/rating.css">

<!-- //담당자 js/css -->
</head>
<body>
<jsp:include page="../_header.jsp"></jsp:include>

	<c:if test="${ sessionScope.memberInfo.number == 1 }">
		<!-- 관리자일 경우에만 보이는 메뉴 -->
	
		<div id="updateMenu" style="text-align: center;">
	
			<input type="button" value="영화 정보 수정" onclick="location.href='${ contextPath }/movie/updateForm.do?movieNumber=${ movie.movieNumber }'">
			<input type="button" value="기존 영화 삭제" onclick="location.href='${ contextPath }/movie/deleteForm.do?movieNumber=${ movie.movieNumber }'">
		
		</div>
	
	</c:if>
<div id="movieDetailWrap">
	<div id="movieDetail">
		<div class="img-box">
			<img src="${ movie.moviePoster }" alt="${ movie.movieTitle }">
		</div>
		<div class="info">
			<h3>${ movie.movieTitle }</h3>
			<div class="date-genre">${ movie.movieDate } | ${ movie.movieKind }</div>
			<div class="md-search-score">
				<div class="md-score-bg">
					<div class="md-score-img" style="width: ${ stars * 10 }%"></div>
				</div>
				<div class="md-score-text"> <fmt:formatNumber value="${ stars }" pattern="0.0" /></div>
			</div>
			<div class="director-header">director</div>
			<div class="director">${ movie.movieDirector }</div>
			<div class="actor-header">actor</div>
			<div class="actor">${ movie.movieActor }</div>
		</div>
		<div class="summary">${ movie.movieContent }</div>
		<div class="like-box">
			<div id="likeBtn"><i class="fas fa-heart"></i></div>
		</div>
		<div class="youtube-box">
		<iframe src="https://www.youtube.com/embed/${ movie.movieTeaser }" frameborder="0" allow="accelerometer; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
			<!-- <div class="youtube-link" onclick="location.href='${ movie.movieYoutubeUrl }'">
				${ movie.movieTitle } Youtube
			</div>-->
		</div>
	</div>
	
	<div id="movieRating">
	<c:if test="${ not empty sessionScope.memberInfo }">
		<form method="post" action="${ contextPath }/comment/insert.do" name="commentInsert" id="commentInsertFrm">
			<div class="rating-title">write a comment</div>
			<div id="writeCommentBox">
				<div class="rating-nick">${ sessionScope.memberInfo.nickName }</div>
				<div class="score-text">Score</div>
				<select name="assessStars">
					<option value="1">1
					<option value="2">2
					<option value="3">3
					<option value="4">4
					<option value="5">5
					<option value="6">6
					<option value="7">7
					<option value="8">8
					<option value="9">9
					<option value="10">10
				</select>
				<div class="comment-text">
					<textarea name="assessContent" id="commentContent"></textarea>
				</div>
				<div class="comment-btn">
				  	<input type="submit" value="write" onclick="return checkComment();">
				</div>
			</div>
			<input type="hidden" name="movieNumber" value="${ param.movieNumber }">
		</form>
		<div id="myCommentBoxTitle" class="rating-title">my comment</div>
		<div id="myCommentBox"></div>
		<hr>
	</c:if>
		<div class="rating-title">comment list</div>
		<div id="ratingCommentBox">
		</div>
		<div id="ratingPage">
			&nbsp;&nbsp;<span class="page-arrow"><i class="fas fa-angle-left"></i></span>&nbsp;&nbsp;
			&nbsp;&nbsp;<span class="page-arrow"><i class="fas fa-angle-right"></i></span>&nbsp;&nbsp;
		</div>
	</div>
	
</div>
<input type="hidden" value="${ param.movieNumber }" id="selectedMovieNumber">
<input type="hidden" value="${ sessionScope.memberInfo.number }" id="memberNumber">
<jsp:include page="../_footer.jsp"></jsp:include>
</body>
</html>