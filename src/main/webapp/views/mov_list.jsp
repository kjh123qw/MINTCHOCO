<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
/*
	담당 : 천세문
*/
%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> MINT CHOCO </title>
<script src="./js/public.js"></script>
<link rel="./css/public.css">

<!-- 담당자 js/css -->
<link rel="stylesheet" href="./css/mov_list.css">

</head>
<body>
<%@ include file="./_header.jsp" %>

<div id="mov_list">

		<hr>

		<h1> 전체 영화 목록 </h1>

        <hr>

        <div class="movie">
            <table>
                <tr>
                    <!-- <td><img src="${movie_poster}></td>를 forEach로 돌림,
                         	총 가로 길이가 100%를 넘겼을 경우 overflow: hidden을 줌 -->
                    <td><a onclick="location.href='mov_detail.jsp'"><img src="./images/mov_poster/1.jpg" alt="I_Am_a_Hero"></a></td>
                    <td><img src="./images/mov_poster/2.jpg" alt="John_Wick"></td>
                    <td><img src="./images/mov_poster/3.jpg" alt="King's_Man"></td>
                    <td><img src="./images/mov_poster/4.jpg" alt="Mechanic"></td>
                    <td><img src="./images/mov_poster/5.jpg" alt="Monster's_Child"></td>
                    <td><img src="./images/mov_poster/6.jpg" alt="Psychic"></td>
                    <td><img src="./images/mov_poster/7.jpg" alt="To_Pusan"></td>
                    <td><img src="./images/mov_poster/8.jpg" alt="War_Craft"></td>
                    <td><img src="./images/mov_poster/9.jpg" alt="Yellow_Sea"></td>
				</tr>
				<tr>
                    <!-- <td><h3> ${movie_title} </h3></td>를 forEach로 돌림 -->
                    <td><h2><a onclick="location.href='mov_detail.jsp'"> 아이 엠 어 히어로 </a></h2></td>
                    <td><h2> 존윅 </h2></td>
                    <td><h2> 킹스맨 </h2></td>
                    <td><h2> 메카닉 </h2></td>
                    <td><h2> 괴물의 아이 </h2></td>
                    <td><h2> 초능력자 </h2></td>
                    <td><h2> 부산행 </h2></td>
                    <td><h2> 워 크래프트 </h2></td>
                    <td><h2> 황해 </h2></td>
            	</tr>
            </table>
        </div>

        <hr>

</div>

<%@ include file="./_footer.jsp" %>