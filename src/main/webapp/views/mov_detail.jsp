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
<link rel="stylesheet" href="./css/mov_detail.css">

</head>
<body>
<%@ include file="./_header.jsp" %>

<div id="mov_detail">

	<hr>
	
	<h1> 영화 내용 상세 </h1>
	
	<hr>
	
	<!-- 영화 정보 -->
	<div class="movie">
	
    	<table border="1">
	        <!-- ${movie_poster}, ${movie_title}, ${movie_kind}, ${movie_content}, ${movie_directer}, ${movie_actor}, ${movie_yutube_url}, ${movie_naver_url} && <a> ${tag_name} <a>를 forEach로 돌림 -->
			<tr>
			    <td rowspan="6"><a onclick="location.href='mov_list.jsp'"><img src="./images/mov_poster/1.jpg" alt="I_Am_a_Hero"></a></td>
			    <td width="25%"><h3> 아이 엠 어 히어로 </h3></td>
			    <td><h4> 줄거리 </h4></td>
			</tr>
			<tr>
			    <td> 장르 : Zombie</td>
			    <td rowspan="4" width="50%">
			    	<br>“인간을 물어뜯어라!”
		            <br>일본 전역을 뒤덮은 정체불명의 바이러스 ‘ZQN’
		            <br>도심 곳곳은 사람을 물어뜯는 감염자들로 인해 대혼란이 이어지고,
		            <br>우연히 살아남은 ‘히데오’와 몸의 반만 감염된 ‘히로미’는 감염자들을 피해 가까스로 생존자들의 안식처에 다다르게 된다. 하지만,
		            <br> 그 곳에서 예상치 못한 거대한 위협을 만나게 되는데…
			    </td>
				</tr>
			<tr>
				<td> 감독 : 일본인 </td>
			</tr>
			<tr>
			    <td> 주연 : 좀비 </td>  
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
					<a href="youtube.com">| Youtube Link |</a>
					<a href="youtube.com">| Naver Link |</a> 
				</td>
			</tr>
		</table>
	</div>		
	<hr>
	
	<!-- 영화 티저 -->
	<div class="teaser">
	
		<!-- ${movie_teaser} 를 forEach로 돌림 -->
	    <iframe width="100%" height="900" src="https://www.youtube.com/embed/0bO2XDjKOXY" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
	
	</div>

	<hr>

	<div class="rating">
	
	    <!-- <tr> <td> Best or 일반 </td>, <td> ${rating_mincho} </td>, <td> ${rating_comment} </td>, <td> ${user_name} </td> </tr> 를 forEach로 돌림 -->
		<table border="1">
			<tr>
				<td style="width: 15%;"> Best Comment </td>
				<td style="width: 15%;"> <img src="./images/mincho/mincho.jpg" alt="mincho"><img src="./images/mincho/mincho.jpg" alt="mincho"><img src="./images/mincho/mincho.jpg" alt="mincho"><img src="./images/mincho/mincho.jpg" alt="mincho"><img src="./images/mincho/mincho.jpg" alt="mincho"> </td>
				<td style="width: 60%;"> 꼭 봐라 </td>
				<td style="width: 10%;"> 김정메 </td>
			</tr>
			<tr>
				<td style="width: 15%;"> Standard </td>
				<td style="width: 15%;"> <img src="./images/mincho/mincho.jpg" alt="mincho"><img src="./images/mincho/mincho.jpg" alt="mincho"><img src="./images/mincho/mincho.jpg" alt="mincho"><img src="./images/mincho/mincho.jpg" alt="mincho"> </td>
				<td style="width: 60%;"> 그냥 볼만했는데요? </td>
				<td style="width: 10%;"> 최원준 </td>
			</tr>
			<tr>
				<td style="width: 15%;"> Standard </td>
				<td style="width: 15%;"> <img src="./images/mincho/mincho.jpg" alt="mincho"><img src="./images/mincho/mincho.jpg" alt="mincho"> </td>
				<td style="width: 60%;"> 이런걸 누가봐요~ </td>
				<td style="width: 10%;"> 유지상 </td>
			</tr>
			<tr>
				<td style="width: 15%;"> Standard </td>
				<td style="width: 15%;"> <img src="./images/mincho/mincho.jpg" alt="mincho"><img src="./images/mincho/mincho.jpg" alt="mincho"><img src="./images/mincho/mincho.jpg" alt="mincho"> </td>
				<td style="width: 60%;"> 개망작 </td>
				<td style="width: 10%;"> 박찬영 </td>
			</tr>
			<tr>
				<td style="width: 15%;"> Standard </td>
				<td style="width: 15%;"> <img src="./images/mincho/mincho.jpg" alt="mincho"><img src="./images/mincho/mincho.jpg" alt="mincho"><img src="./images/mincho/mincho.jpg" alt="mincho"><img src="./images/mincho/mincho.jpg" alt="mincho"></td>
				<td style="width: 60%;"> 좀비는 추천 </td>
				<td style="width: 10%;"> OOO </td>
			</tr>
			<tr>
				<td style="width: 15%;"> Worst Comment </td>
				<td style="width: 15%;"> <img src="./images/mincho/mincho.jpg" alt="mincho"> </td>
				<td style="width: 60%;"> 동물이 안나오잖아 </td>
				<td style="width: 10%;"> 장XX </td>
			</tr>
		</table>
	
	</div>

	<hr>

</div>

<%@ include file="./_footer.jsp" %>

</body>
</html>