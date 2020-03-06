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
<meta charset="UTF-8">
<title> 영화 내용 상세 </title>

<script src="${ contextPath }/js/jquery.2.1.3.min.js"></script>
<script src="${ contextPath }/js/jquery-ui.min.js"></script>
<script src="${ contextPath }/js/jquery.easing.1.3.js"></script>
<script src="${ contextPath }/js/jquery.mousewheel.min.js"></script>
<script src="${ contextPath }/js/public.js"></script>
<link rel="stylesheet" href="${ contextPath }/css/public.css">

<!-- 담당자 js/css -->

<link rel="stylesheet" href="${ contextPath }/css/mov_detail.css">

</head>

<jsp:include page="../_header.jsp"></jsp:include>

<body>

<div id="mov_detail">

	<h1> 영화 내용 상세 </h1>
	
	<!-- 영화 정보 -->
	<div class="movie">
	
    	<table border="1">
	        <!-- ${movie_poster}, ${movie_title}, ${movie_kind}, ${movie_content}, ${movie_directer}, ${movie_actor}, ${movie_yutube_url}, ${movie_naver_url} && <a> ${tag_name} <a>를 forEach로 돌림 -->
			<tr>
			    <td rowspan="6"><a onclick="window.history.go(-1);"><img src="${ contextPath }/images/mov_poster/${ movie.moviePoster }" alt="${ movie.movieTitle }"></a></td>
			    <td width="25%"><h3> ${ movie.movieTitle } </h3></td>
			    <td><h4> 줄거리 </h4></td>
			</tr>
			<tr>
			    <td> 장르 : Zombie</td>
			    <td rowspan="4" width="50%">
					${ movie.movieContent }
			    </td>
				</tr>
			<tr>
				<td> 감독 : ${ movie.movieDirector } </td>
			</tr>
			<tr>
			    <td> 주연 : ${ movie.movieActor } </td>  
			</tr>
			<tr>
				<td>
					<a href="#새벽의 저주">#새벽의 저주</a>
					<a href="#좀비 vs 플랜트">#좀비 vs 플랜트</a>
					<a href="#워킹 데드">#워킹 데드</a>
					<a href="#언데드">#언데드</a>
	           </td>
	           </tr>
			<tr>
			<td></td>
				<td>
					<a href="${ movie.movieYoutubeUrl }">| Youtube Link |</a>
					<a href="${ movie.movieNaverUrl }">| Naver Link |</a> 
				</td>
			</tr>
		</table>
	</div>		
	<hr>
	
	<!-- 영화 티저 -->
	<div class="teaser">
	
		<!-- ${movie_teaser} 를 forEach로 돌림 -->
	    <iframe width="100%" height="100%" src="https://www.youtube.com/embed/${ movie.movieTeaser }" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
	
	</div>

<jsp:include page="../_rating.jsp"></jsp:include>

</div>

</body>

<jsp:include page="../_footer.jsp"></jsp:include>

</html>