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
<title> 영화 내용 상세 </title>

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

<script src="${ contextPath }/js/movie/rating.js"></script>
<link rel="stylesheet" href="${ contextPath }/css/movie/rating.css">

<!-- //담당자 js/css -->
</head>
<jsp:include page="../_header.jsp"></jsp:include>
<body>

<div id="bg">

<div id="movDetail">

	<h1><a href="${ contextPath }/movie/detail.do?movieNumber=${ movie.movieNumber }"> 영화 내용 상세 </a></h1>
	
	
	
	<div class="loginUser" hidden="hidden">${user.number}</div>
	
	<c:if test="${ user.name.contains('관리자') }">
	
		<!-- 관리자일 경우에만 보이는 메뉴 -->
	
		<div id="updateMenu" style="text-align: center;">
	
			<input type="button" value="영화 정보 수정" onclick="location.href='${ contextPath }/movie/updateForm.do?movieNumber=${ movie.movieNumber }'">
			<input type="button" value="기존 영화 삭제" onclick="location.href='${ contextPath }/movie/deleteForm.do?movieNumber=${ movie.movieNumber }'">
		
		</div>
	
	</c:if>
	
	<!-- 영화 정보 -->
	<div class="movie">
	
		<div class="wrap1">
		
			<div class="poster-image"><a onclick="window.history.go(-1);">
				<img src="${ contextPath }/images/mov_poster/${ movie.moviePoster }" alt="${ movie.movieTitle }"></a>
				<div class="plus-minus">
					<input class="<c:if test="${ checkFavorite == 1 }">checked</c:if>" type="button" value="찜하기!" onclick="location.href='${ contextPath }/movie/favoritePlus.do?movieNumber=${ movie.movieNumber }'">
					<input type="button" value="퉤에엣!" onclick="location.href='${ contextPath }/movie/favoriteMinus.do?movieNumber=${ movie.movieNumber }'">
				</div>
			</div>
			
	    	<table class="table1" border="1">
	    	
				<tr>
				    <td colspan="2"><h3> ${ movie.movieTitle } </h3></td>
				</tr>
				<tr>
					<td width="20%"> 년 도 </td>
					<td> ${ movie.movieDate } </td>
	           	</tr>
				<tr>
					<td> 장 르 </td>
					<td> ${ movie.movieKind } </td>
				</tr>
				<tr>
					<td> 감 독 </td>
					<td> ${ movie.movieDirector } </td>
				</tr>
				<tr>
					<td> 주 연 </td>
					<td> ${ movie.movieActor } </td>
				</tr>
				
			</table>
		
		</div>
		
		<div class="teaser">
	    	<iframe width="100%" height="100%" src="https://www.youtube.com/embed/${ movie.movieTeaser }" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
		</div>
		
    	<table class="table2" border="1">

			<tr>
				<td>
			    	<c:forEach var="tag" items="${ tagList }">
						<a href="${ contextPath }/movie/search.do?searchKeyWord=${ tag.tagContent }">#${ tag.tagContent }</a>
			       	</c:forEach>
				</td>
          	</tr>
          	
			<tr>
				<td>
					<a href="${ movie.movieYoutubeUrl }">| Youtube Link |</a>
					<a href="${ movie.movieNaverUrl }">| Naver Link |</a> 
				</td>
			</tr>
			<tr>
				<td>${ movie.movieContent }</td>
			</tr>
			<tr>
				<td>게시일 : ${ movie.movieIndate }</td>
			</tr>
			
		</table>
	</div>		

</div>

</div>
</body>
<jsp:include page="./_rating.jsp"></jsp:include>
<jsp:include page="../_footer.jsp"></jsp:include>
</html>