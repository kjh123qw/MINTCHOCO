<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf-8"); %>
<% response.setContentType("text/html; charset=utf-8"); %>
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
<script>
	sessionStorage.setItem('contextPath', '${ contextPath }');
</script>
<script src="${ contextPath }/js/search_list.js"></script>
<link rel="stylesheet" href="${ contextPath }/css/search_list.css">
<!-- 담당자 js/css -->
</head>

<body>

<jsp:include page="../_header.jsp"></jsp:include>

	<div id="searchListWrap">
	    <p><b>${ searchKeyWord }</b> 검색 결과 입니다. &lt;<b>${ searchCount }</b> 건&gt;</p>
		<hr>
		<div id="searchResultBox">
			<c:if test="${ not empty searchMovieList }">
				<c:forEach var="searchVO" items="${ searchMovieList }">
					<div class="search-movie-box">
						<div class="search-movie-innerbox" onclick="location.href='${ contextPath }/movie/detail.do?movieNumber=${ searchVO.movieNumber }'">
							<div class="search-title">
								${ searchVO.movieTitle }
							</div>
							<div class="search-score">
								<div class="score-bg">
									<div class="score-img" style="width: ${ searchVO.movieStars * 10 }%"></div>
								</div>
								<div class="score-text">${ searchVO.movieStars }</div>
							</div>
							<div class="search-info-box">
								<div class="search-movie-image">
									<img src="${ contextPath }/images/mov_poster/${ searchVO.moviePoster }" alt="${ searchVO.movieTitle } 포스터">
								</div>
								<div class="search-info-ele-box">
									<div class="search-info-ele">
										${ searchVO.movieDate } | ${ searchVO.movieTime } 분 | ${ searchVO.movieGrade }
									</div>
									<div class="search-info-ele">
										${ searchVO.movieKind }
									</div>
									<div class="search-info-ele">
										감독 : ${ searchVO.movieDirector }
									</div>
									<div class="search-info-ele actor">
										${ searchVO.movieActor }
									</div>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</c:if>
		</div>
	</div>
	<c:if test="${ moreSearchCount > 0 }">
		<div id="searchMoreBtn">
			(${ searchCount - moreSearchCount } / ${ searchCount })
			<c:choose>
				<c:when test="${ moreSearchCount > 5 }">
					5 건 더보기
				</c:when>
				<c:otherwise>
					${ moreSearchCount } 건 더보기
				</c:otherwise>
			</c:choose>
		</div>
	</c:if>
<jsp:include page="../_footer.jsp"></jsp:include>

</body>

</html>