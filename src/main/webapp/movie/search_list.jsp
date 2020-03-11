<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
/*
	:: 검색 결과 화면 ::
	담당 : 김정호
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
<title>검색결과</title>
<script src="${ contextPath }/js/jquery.2.1.3.min.js"></script>
<script src="${ contextPath }/js/jquery-ui.min.js"></script>
<script src="${ contextPath }/js/jquery.easing.1.3.js"></script>
<script src="${ contextPath }/js/jquery.mousewheel.min.js"></script>
<script src="${ contextPath }/js/public.js"></script>
<script src="https://use.fontawesome.com/releases/v5.8.0/js/all.js"></script>
<link rel="stylesheet" href="${ contextPath }/css/public.css">

<!-- 담당자 js/css -->
<script src="${ contextPath }/js/search_list.js"></script>
<link rel="stylesheet" href="${ contextPath }/css/search_list.css">
<!-- 담당자 js/css -->
</head>

<body>

<jsp:include page="../_header.jsp"></jsp:include>

	<div id="searchListWrap">
	    <p><b>${ searchKeyWord }</b> 검색 결과 입니다.</p>
		<hr>
		<div id="testBtn" style="height: 100px; background-color: #aaa; text-align: center; line-height: 100px;">test button</div>
		<hr>
		<c:choose>
			<c:when test="${ not empty searchMovieList }">
				<c:forEach var="searchVO" items="${ searchMovieList }">
					<div class="search-movie-box">
						<div class="search-movie-innerbox">
							<div class="search-movie-image">
								<img src="${ contextPath }/images/mov_poster/${ searchVO.moviePoster }" alt="${ searchVO.movieTitle } 포스터">
							</div>
							<div class="search-info-box">
								<div class="search-info-ele title">
									${ searchVO.movieTitle }
								</div>
								<div class="search-info-ele">
									${ searchVO.movieDate } | ${ searchVO.movieTime } 분 | ${ searchVO.movieGrade }
								</div>
								<div class="search-info-ele">
									${ searchVO.movieKind }
								</div>
								<div class="search-info-ele actor">
									${ searchVO.movieActor }
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<c:forEach var="movieVO" items="${ allMovieList }">
					<div class="search-movie-box">
						<div class="search-movie-innerbox">
							<div class="search-movie-image">
								<img src="${ contextPath }/images/mov_poster/${ movieVO.moviePoster }" alt="${ movieVO.movieTitle } 포스터">
							</div>
							<div class="search-info-box">
								<div class="search-info-ele title">
									${ movieVO.movieTitle }
								</div>
								<div class="search-info-ele">
									${ movieVO.movieDate } | ${ movieVO.movieTime } 분 | ${ movieVO.movieGrade }
								</div>
								<div class="search-info-ele">
									${ movieVO.movieKind }
								</div>
								<div class="search-info-ele actor">
									${ movieVO.movieActor }
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</div>
<jsp:include page="../_footer.jsp"></jsp:include>

</body>

</html>