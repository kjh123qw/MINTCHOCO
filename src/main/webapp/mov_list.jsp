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
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> 전체 영화 목록 </title>

<script src="./js/jquery.2.1.3.min.js"></script>
<script src="./js/jquery-ui.min.js"></script>
<script src="./js/jquery.easing.1.3.js"></script>
<script src="./js/jquery.mousewheel.min.js"></script>
<script src="./js/public.js"></script>
<link rel="stylesheet" href="./css/public.css">

<!-- 담당자 js/css -->

<link rel="stylesheet" href="./css/mov_public.css">
<link rel="stylesheet" href="./css/mov_list.css">

</head>

<header>
	<jsp:include page="./_header.jsp"></jsp:include>
</header>

<body>

    <h1> 전체 영화 목록 </h1>

    <div id="mov_list">

        <div id="l_button">◀</div>
        <div id="r_button">▶</div>
        
        <div class="bg">
            <div class="movie">
                <a href="mov_detail.jsp">
                    <img src="./images/mov_poster/mov_poster_1.jpg" alt="I_am_A_Hero">
                </a>
                <p> 아이 엠 어 히어로 </p>
            </div>
            <div class="movie">
                <a href="mov_detail.jsp">
                    <img src="./images/mov_poster/mov_poster_2.jpg" alt="Jhone_Wick">
                </a>
                <p> 존 윅 </p>
            </div>
            <div class="movie">
                <a href="mov_detail.jsp">
                    <img src="./images/mov_poster/mov_poster_3.jpg" alt="Kings_Man">
                </a>
                <p> 킹스 맨 </p>
            </div>
            <div class="movie">
                <a href="mov_detail.jsp">
                    <img src="./images/mov_poster/mov_poster_4.jpg" alt="Mechanic">
                </a>
                <p> 메카닉 </p>
            </div>
            <div class="movie">
                <a href="mov_detail.html">
                    <img src="./images/mov_poster/mov_poster_5.jpg" alt="Monsters_Child">
                </a>
                <p> 괴물의 아이 </p>
            </div>
            <div class="movie">
                <a href="mov_detail.html">
                    <img src="./images/mov_poster/mov_poster_6.jpg" alt="Psychic">
                </a>
                <p> 초능력자 </p>
            </div>
            <div class="movie">
                <a href="mov_detail.html">
                    <img src="./images/mov_poster/mov_poster_7.jpg" alt="To_Busan">
                </a>
                <p> 부산행 </p>
            </div>
            <div class="movie">
                <a href="mov_detail.html">
                    <img src="./images/mov_poster/mov_poster_8.jpg" alt="War_Craft">
                </a>
                <p> 워 크래프트 </p>
            </div>
            <div class="movie">
                <a href="mov_detail.html">
                    <img src="./images/mov_poster/mov_poster_9.jpg" alt="Yellow_Sea">
                </a>
                <p> 황해 </p>
            </div>
            <div class="movie">
                <a href="mov_detail.html">
                    <img src="./images/mov_poster/mov_poster_10.jpg" alt="Jhone_Wick_2">
                </a>
                <p> 존윅 2 </p>
            </div>
            <div class="movie">
                <a href="mov_detail.html">
                    <img src="./images/mov_poster/mov_poster_11.jpg" alt="Sicario">
                </a>
                <p> 시카리오 </p>
            </div>
            <div class="movie">
                <a href="mov_detail.html">
                    <img src="./images/mov_poster/mov_poster_12.jpg" alt="Incident">
                </a>
                <p> 인시던트 </p>
            </div>
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

</body>

<footer>
	<jsp:include page="./_footer.jsp"></jsp:include>
</footer>

</html>