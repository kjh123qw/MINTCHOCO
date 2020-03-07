<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
/*
	:: 메인 ::
	담당자 : 유지상
*/
%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MAIN.JSP</title>
<script src="./js/jquery.2.1.3.min.js"></script>
<script src="./js/jquery-ui.min.js"></script>
<script src="./js/jquery.easing.1.3.js"></script>
<script src="./js/jquery.mousewheel.min.js"></script>
<script src="./js/public.js"></script>
<link rel="stylesheet" href="./css/public.css">

<!-- 담당자 js/css -->

<script src="https://use.fontawesome.com/releases/v5.8.0/js/all.js"></script>

<script src="./js/main/main.js"></script>
<link rel="stylesheet" href="./css/main/main.css">

<script src="./js/main/slick.min.js"></script>
<link rel="stylesheet" href="./css/main/slick.css">
<link rel="stylesheet" href="./css/main/slick-theme.css">


<!-- //담당자 js, css -->

</head>
<body>
<%-- <%@ include file="./_header.jsp" %> --%>
<!-- 담당자 내용 -->

	<div id = "headDiv">
		<h2 id = "mainTitle">MINTCHOCO</h2>
		<button id = "menuBtn" onclick="menuOpen();">
        	<span id = "bar" class = "ooo"></span>
    	</button>
    	<input type = "checkbox" id = "menuCheck"/>
	</div>

	<div id = "searchDiv">
		<form name = "searchBar" id = "searchBar" action = "mainSearchMovie.do" method="get">
			<input type = "text" placeholder="Search" id = "searchText" name = "searchText"/>
			<button type="submit" form="searchBar" id = "searchBtn"><i class="fas fa-search"></i></button>
		</form>
	</div>
	
	<div id = "pushDiv1">
		 <div id = "pushDiv1_title">&nbsp;<a>유지상</a>&nbsp;<h3>님에게 추천하는 영화입니다.</h3></div>
		 
		 <div class = "mainMovie"></div>
		 <div class = "mainMovie"></div>
		 <div class = "mainMovie"></div>
		 <div class = "mainMovie"></div>
		 <div class = "mainMovie">
		 	<div class = "movieInfo"></div>
		 </div>

		 
		 
	</div>
	<div id = "pushDiv2">
		<div id = "pushDiv2_title">&nbsp;<a>MIKNTCHOCO</a>&nbsp;<h3>에서 인기많은 영화입니다.</h3></div>
		
		 <div class = "mainMovie"></div>
		 <div class = "mainMovie"></div>
		 <div class = "mainMovie"></div>
		 <div class = "mainMovie"></div>
		 <div class = "mainMovie">
		 	<div class = "movieInfo"></div>
		 </div>
	</div>
	
	
	
	
	<div id = "pushDiv3">
		<div class = "slideMovie">
			<div class = "slideMovieList">1</div>
			<div class = "slideMovieList">2</div>
			<div class = "slideMovieList">3</div>
			<div class = "slideMovieList">4</div>
			<div class = "slideMovieList">5</div>
			<div class = "slideMovieList">6</div>
			<div class = "slideMovieList">7</div>
		</div>
	</div>
	
	

	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	<div id = "menuDiv">
		<div id = "menuList">
			<a>MYPAGE</a>
			<a>MOVIE</a> <!-- 주제별 영화목록 -->
			<a>NOTICE</a> <!-- 공지사항 -->
			<a>LOGOUT</a>
			<div id = "snsBox">
				<a href = "#"  onclick = "snsEvent();"><i class="fab fa-twitter-square"></i></a>
				<a href = "#"  onclick = "snsEvent();"><i class="fab fa-facebook-square"></i></a>
				<a href = "#"  onclick = "snsEvent();"><i class="fab fa-instagram"></i></a>
				<a href = "#"  onclick = "snsEvent();"><i class="fab fa-youtube-square"></i></a>
			</div>
			<p>神奈川県藤沢市辻堂4-1-27レオパレス辻堂アリエッタ / yujisang93@gmail.com</p>
			<p>ALL RIGHTS RESERVED. © 2020 MINTCHOCO</p>
		</div>
	</div>
	<div id = "upBtn" onclick="goTop()"><i class="fas fa-arrow-up"></i></div>



<!-- 담당자 내용 -->
<%@ include file="./_footer.jsp" %>
</body>
</html>