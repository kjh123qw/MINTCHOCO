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
<title>FILE_NAME.JSP</title>
<script src="${ contextPath }/js/jquery.2.1.3.min.js"></script>
<script src="${ contextPath }/js/jquery-ui.min.js"></script>
<script src="${ contextPath }/js/jquery.easing.1.3.js"></script>
<script src="${ contextPath }/js/jquery.mousewheel.min.js"></script>
<link rel="stylesheet" href="${ contextPath }/css/public.css">
<!-- 담당자 js, css -->

<script src="${ contextPath }/js/rec_list.js"></script>
<link rel="stylesheet" href="${ contextPath }/css/rec_list.css">

<!-- //담당자 js, css -->
</head>
<body>
<jsp:include page="../_header.jsp"></jsp:include>
<!-- 담당자 내용 -->

    <h1> 전체 영화 목록 </h1>

    <div id="mov_list">

        <div id="l_button">◀</div>
        <div id="r_button">▶</div>
        
        <div class="bg">
	        <c:forEach var="movie" items="${movieList}">
	        
	            <div class="movie">
	                <a href="${ contextPath }/movie/detail.do?movieNumber=${ movie.movieNumber }">
	                    <img src="${ contextPath }/images/mov_poster/${ movie.moviePoster }" alt="${ movie.movieTitle }">
	                </a>
	                    <p> ${ movie.movieTitle } </p>
	            </div>
	        
	        </c:forEach>
        </div>

    </div>

    <div class="mov_button">
        <table>
            <tr>
                <td><a href="#">민초 추천</a></td>
                <td><a href="#">민초의 난</a></td>
                <td><a href="#">최고 인기</a></td>
                <td><a href="#">최근 인기 </a></td>
            </tr>
            <tr>
                <td><a href="#">선호 추천</a></td>
                <td><a href="#">장르 추천</a></td>
                <td><a href="#">친구 추천</a></td>
                <td><a href="#">추가 예정</a></td>
            </tr>
            <tr>
                <td><a href="#">ABC 순</a></td>
                <td><a href="#">가나다 순</a></td>
                <td><a href="#">상영등급 순</a></td>
                <td><a href="#">추가 예정</a></td>
            </tr>
        </table>
    </div>

<!-- 담당자 내용 -->
<jsp:include page="../_footer.jsp"></jsp:include>
</body>
</html>