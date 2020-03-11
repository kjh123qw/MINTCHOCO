<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
/*
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
<title> 주제별 영화 목록 </title>
<script src="${ contextPath }/js/jquery.2.1.3.min.js"></script>
<script src="${ contextPath }/js/jquery-ui.min.js"></script>
<script src="${ contextPath }/js/jquery.easing.1.3.js"></script>
<script src="${ contextPath }/js/jquery.mousewheel.min.js"></script>
<script src="${ contextPath }/js/public.js"></script>
<link rel="stylesheet" href="${ contextPath }/css/public.css">
<!-- 담당자 js, css -->

<script src="${ contextPath }/js/movie/rec_list.js"></script>
<link rel="stylesheet" href="${ contextPath }/css/movie/rec_list.css">

<!-- //담당자 js, css -->
</head>

<body>
<jsp:include page="../_header.jsp"></jsp:include>
<!-- 담당자 내용 -->

    <h1><a href="${ contextPath }/movie/recommend.do"> 추천 영화 목록 </a></h1>

	<!-- 관리자일 경우에만 보이는 메뉴 -->
	<div id="updateMenu" style="text-align: center;">
	
		<input type="button" value="영화 정보 입력" onclick="location.href='${ contextPath }/movie/insertForm.do'">
	
	</div>

    <div id="movRec">

		<div class="l-button1" >◀</div>
		<div class="r-button1">▶</div>

		<h3>액션</h3>
		
		<div class="bg">
		
	        <c:forEach var="movie" items="${movieList}">
	        
	        	<c:if test="${movie.movieKind.contains('액션')}">
	        
		            <div id="action" class="movie">
		                <a href="${ contextPath }/movie/detail.do?movieNumber=${ movie.movieNumber }">
		                    <img class="poster" id="${ movie.movieNumber }" src="${ contextPath }/images/mov_poster/${ movie.moviePoster }" alt="${ movie.movieTitle }">
		                </a>
		                <p>
		                	<a href="${ contextPath }/movie/detail.do?movieNumber=${ movie.movieNumber }">${ movie.movieTitle }</a>
		                </p>
		            </div>
	        
	        	</c:if>
	        
	        </c:forEach>
        </div>
		
		<h3>드라마</h3>
		
		<div class="bg">
	        <c:forEach var="movie" items="${movieList}">
	        
	        	<c:if test="${movie.movieKind.contains('드라마')}">
	        
		            <div class="movie">
		                <a href="${ contextPath }/movie/detail.do?movieNumber=${ movie.movieNumber }">
		                    <img class="poster" id="${ movie.movieNumber }" src="${ contextPath }/images/mov_poster/${ movie.moviePoster }" alt="${ movie.movieTitle }">
		                </a>
		                <p>
		                	<a href="${ contextPath }/movie/detail.do?movieNumber=${ movie.movieNumber }">${ movie.movieTitle }</a>
		                </p>
		            </div>
	        
	        	</c:if>
	        
	        </c:forEach>
        </div>
        
        <h3>스릴러</h3>
        
        <div class="bg">
	        <c:forEach var="movie" items="${movieList}">
	        
	        	<c:if test="${movie.movieKind.contains('스릴러')}">
	        
		            <div class="movie">
		                <a href="${ contextPath }/movie/detail.do?movieNumber=${ movie.movieNumber }">
		                    <img class="poster" id="${ movie.movieNumber }" src="${ contextPath }/images/mov_poster/${ movie.moviePoster }" alt="${ movie.movieTitle }">
		                </a>
		                <p>
		                	<a href="${ contextPath }/movie/detail.do?movieNumber=${ movie.movieNumber }">${ movie.movieTitle }</a>
		                </p>
		            </div>
	        
	        	</c:if>
	        
	        </c:forEach>
        </div>

        <h3>애니메이션</h3>
        
        <div class="bg">
	        <c:forEach var="movie" items="${movieList}">
	        
	        	<c:if test="${movie.movieKind.contains('애니메이션')}">
	        
		            <div class="movie">
		                <a href="${ contextPath }/movie/detail.do?movieNumber=${ movie.movieNumber }">
		                    <img class="poster" id="${ movie.movieNumber }" src="${ contextPath }/images/mov_poster/${ movie.moviePoster }" alt="${ movie.movieTitle }">
		                </a>
		                <p>
		                	<a href="${ contextPath }/movie/detail.do?movieNumber=${ movie.movieNumber }">${ movie.movieTitle }</a>
		                </p>
		            </div>
	        
	        	</c:if>
	        
        	</c:forEach>
        </div>

    </div>

<!-- 담당자 내용 -->
<jsp:include page="../_footer.jsp"></jsp:include>
</body>

</html>